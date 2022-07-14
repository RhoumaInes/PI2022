package tn.esprit.asi.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.asi.entities.Evenement;
import tn.esprit.asi.entities.Participation;
import tn.esprit.asi.entities.User;
import tn.esprit.asi.reposetories.EvenementRepo;
import tn.esprit.asi.reposetories.ParticipationRepo;
import tn.esprit.asi.reposetories.UserRepo;

@Service
@Slf4j
public class EvenementServiceImpl implements EvenementService {
	@Autowired
	EvenementRepo evenementRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	ParticipationRepo participationRepo;

	@Override
	public List<Evenement> listeAllEvenement() {
		return (List<Evenement>) evenementRepo.findAll();
	}

	@Override
	public Evenement getEventById(Long id) {
		return evenementRepo.findById(id).get();
	}

	@Override
	public Evenement addEvent(Evenement ev) {
		ev.setDateCreation(new Date());
		return evenementRepo.save(ev);
	}

	@Override
	public void deleteEvent(Long id) {
		Evenement ev = getEventById(id);
		if(participationRepo.retrieveParticipationEvent(ev)==null) {
			evenementRepo.delete(ev);
		}
		
	}

	@Override
	public Evenement updateEvent(Evenement ev) {
		return evenementRepo.save(ev);
	}

	@Override
	public void participation(Long idevent, Long idUser) {
		Evenement e = getEventById(idevent);
		User u = userRepo.findById(idUser).get();
		if(participationRepo.retrieveParticipationEvent(e,u)==null) {
			if(placeDispo(idevent)>0) {
				Participation participation = new Participation();
				participation.setUserPart(u);
				participation.setEvenementPart(e);
				participation.setDateParticipation(new Date());
				participation.setStatus(true);
				participation.setAnnulation(false);
				participation.setMontantPaye((float) 0);
				participationRepo.save(participation);
			}
		}	
	}

	/*@Override
	public void annulerParticipation(Long idevent, Long idUser) {
		Evenement e = getEventById(idevent);
		User u = userRepo.findById(idUser).get();
		Participation participation=participationRepo.retrieveParticipationEvent(e,u);
		if(participation!=null) {
			if(participation.getMontantPaye()==null || participation.getMontantPaye()<=0) {
				participationRepo.delete(participation);
			}else {
				participation.setAnnulation(true);
				participationRepo.save(participation);
			}
		}
	}*/

	@Override
	public Long placeDispo(Long idEvent) {
		Evenement e = getEventById(idEvent);
		Long totalNBR= e.getMaxNumberPlaces();
		return (totalNBR-participationRepo.placeDispo(e));
	}

	
}

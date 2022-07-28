package tn.esprit.asi.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.asi.entities.Evenement;
import tn.esprit.asi.entities.Participation;
import tn.esprit.asi.entities.User;
import tn.esprit.asi.reposetories.ParticipationRepo;
import tn.esprit.asi.reposetories.UserRepo;

@Service
@Slf4j
public class ParticipationServiceImpl implements ParticipationService {
	@Autowired
	ParticipationRepo participationRepo;
	@Autowired
	EvenementServiceImpl evenementServiceImpl;
	@Autowired
	UserRepo userRepo;
	
	@Override
	public void resilierParticipation(Long idParticipation) {
		Participation participation= participationRepo.findById(idParticipation).get();
		participation.setStatus(false);
		participationRepo.save(participation);
		
	}

	@Override
	public void supprimerParticipation(Long idParticipation) {
		Participation participation= participationRepo.findById(idParticipation).get();
		if(participation.getStatus()==false) {
			participationRepo.delete(participation);
		}
		
	}

	@Override
	public Boolean annulerParticipation(Long idParticipation) {
		Participation participation= participationRepo.findById(idParticipation).get();
		if(new Date().before(participation.getEvenementPart().getDateDebut())) {
			if(participation.getMontantPaye()==null || participation.getMontantPaye()<=0) {
				participationRepo.delete(participation);
			}else {
				participation.setAnnulation(true);
				participationRepo.save(participation);
			}
			return true;
		}
		return false;
	}

	@Override
	public List<Participation> listParticipationEvent(Long idEvent) {
		Evenement ev = evenementServiceImpl.getEventById(idEvent);
		return participationRepo.retrieveParticipationEvent(ev);
	}

	@Override
	public Long nombreParticipants(Long idEvent) {
		Evenement ev = evenementServiceImpl.getEventById(idEvent);
		return participationRepo.placeDispo(ev);
	}

	@Override
	public Boolean checkParticipation(Long idEvent, Long idUser) {
		Evenement ev = evenementServiceImpl.getEventById(idEvent);
		User u = userRepo.findById(idUser).get();
		if(participationRepo.retrieveParticipationEvent(ev,u)!=null) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean annulerParticipEvent(Long idEvent, Long idUser) {
		Evenement ev = evenementServiceImpl.getEventById(idEvent);
		User u = userRepo.findById(idUser).get();
		Participation part = participationRepo.retrieveParticipationEvent(ev,u);
		return annulerParticipation(part.getIdParticip());
	}

}

package tn.esprit.asi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.asi.entities.Evenement;
import tn.esprit.asi.entities.Participation;
import tn.esprit.asi.reposetories.ParticipationRepo;

@Service
@Slf4j
public class ParticipationServiceImpl implements ParticipationService {
	@Autowired
	ParticipationRepo participationRepo;
	@Autowired
	EvenementServiceImpl evenementServiceImpl;
	
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
	public void annulerParticipation(Long idParticipation) {
		Participation participation= participationRepo.findById(idParticipation).get();
		if(participation.getMontantPaye()==null || participation.getMontantPaye()<=0) {
			participationRepo.delete(participation);
		}else {
			participation.setAnnulation(true);
			participationRepo.save(participation);
		}
		
	}

	@Override
	public List<Participation> listParticipationEvent(Long idEvent) {
		Evenement ev = evenementServiceImpl.getEventById(idEvent);
		return participationRepo.retrieveParticipationEvent(ev);
	}

}

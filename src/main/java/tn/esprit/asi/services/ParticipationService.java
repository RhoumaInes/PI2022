package tn.esprit.asi.services;

import java.util.List;

import tn.esprit.asi.entities.Participation;

public interface ParticipationService {
	void resilierParticipation(Long idParticipation);
	void supprimerParticipation(Long idParticipation);
	Boolean annulerParticipation(Long idParticipation);
	
	List<Participation> listParticipationEvent(Long idEvent);
	Long nombreParticipants(Long idEvent);
	
	Boolean checkParticipation(Long idEvent,Long idUser);
	
	Boolean annulerParticipEvent(Long idEvent,Long idUser);
}

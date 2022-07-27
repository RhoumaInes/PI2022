package tn.esprit.asi.services;

import java.util.List;

import tn.esprit.asi.entities.Evenement;

public interface EvenementService {
	
	List<Evenement> listeAllEvenement();
	
	Evenement getEventById(Long id);
	
	Evenement addEvent(Evenement ev);
	
	Boolean deleteEvent(Long id);
	
	Evenement updateEvent(Evenement ev);
	
	void participation(Long idevent, Long idUser);
	
	//void annulerParticipation(Long idevent, Long idUser);
	
	Long placeDispo(Long idEvent);
	
	

}

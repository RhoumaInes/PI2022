package tn.esprit.asi.services;

import java.util.Set;

import tn.esprit.asi.entities.Evenement;

public interface EvenementService {
	
	Set<Evenement> listeAllEvenement();
	
	Evenement getEventById(Long id);
	
	Evenement addEvent(Evenement ev);
	
	void deleteEvent(Evenement ev);
	
	Evenement updateEvent(Evenement ev);

}

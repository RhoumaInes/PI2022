package tn.esprit.asi.services;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.asi.entities.PublicationForum;


public interface PublicationService {
	
	PublicationForum  ajouterPublication(PublicationForum p,Long id);
	
	List<PublicationForum> listePub();
	
	void Supp(Long id);
	
	PublicationForum modifPup(PublicationForum pub);

}

package tn.esprit.asi.services;

import org.springframework.stereotype.Service;

import tn.esprit.asi.entities.Commentaire;
import tn.esprit.asi.entities.PublicationForum;


public interface CommentaireService {
	
	Commentaire  ajouterCom(Commentaire p,Long idUser, Long IdPub);

}

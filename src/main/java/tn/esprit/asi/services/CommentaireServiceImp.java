package tn.esprit.asi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.asi.entities.Commentaire;
import tn.esprit.asi.entities.PublicationForum;
import tn.esprit.asi.entities.User;
import tn.esprit.asi.reposetories.CommentaireRepository;
import tn.esprit.asi.reposetories.PublicationRepository;
import tn.esprit.asi.reposetories.UserRepo;

@Service
public class CommentaireServiceImp implements CommentaireService{
	
	@Autowired
	PublicationRepository PubRep;
	@Autowired
	UserRepo userRep;
	@Autowired
	CommentaireRepository ComRep;

	@Override
	public Commentaire ajouterCom(Commentaire c, Long idUser, Long IdPub) {
		User user=userRep.findById(idUser).get();
		PublicationForum pub=PubRep.findById(IdPub).get();
		c.setIDUser(user);
		c.setPublication(pub);
		return ComRep.save(c);
		
	}

}

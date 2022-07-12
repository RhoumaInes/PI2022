package tn.esprit.asi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import tn.esprit.asi.entities.PublicationForum;
import tn.esprit.asi.entities.User;
import tn.esprit.asi.reposetories.PublicationRepository;
import tn.esprit.asi.reposetories.UserRepo;

@Service
@Slf4j
public class PublicationServiceImp implements PublicationService{
	@Autowired
	PublicationRepository PubRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Override
	public PublicationForum ajouterPublication(PublicationForum p ,Long id) {
		//PubRepo.save(p);
		System.out.println("this");
		User user=userRepo.findById(id).get();
		System.out.println("this"+user.getIDUser());
		p.setIDUser(user);
		PubRepo.save(p);
		return p;
		}

	@Override
	public List<PublicationForum> listePub() {
		return (List<PublicationForum>) PubRepo.findAll();
	}

	@Override
	public void Supp(Long id) {
		PubRepo.deleteById(id);
		
	}

	@Override
	public PublicationForum modifPup(PublicationForum pub) {
		return PubRepo.save(pub);
		
	}

}

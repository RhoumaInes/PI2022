package tn.esprit.asi.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.asi.entities.Evenement;
import tn.esprit.asi.entities.User;
import tn.esprit.asi.reposetories.EvenementRepo;
import tn.esprit.asi.reposetories.UserRepo;

@Service
@Slf4j
public class EvenementServiceImpl implements EvenementService {
	@Autowired
	EvenementRepo evenementRepo;
	@Autowired
	UserRepo userRepo;

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
		return evenementRepo.save(ev);
	}

	@Override
	public void deleteEvent(Long id) {
		evenementRepo.deleteById(id);
	}

	@Override
	public Evenement updateEvent(Evenement ev) {
		return evenementRepo.save(ev);
	}

	@Override
	public void participation(Long idevent, Long idUser) {
		Evenement e = getEventById(idevent);
		User u = userRepo.findById(idUser).get();
		Set<User> utilisateurs = e.getParticipation();
		utilisateurs.add(u);
		e.setParticipation(utilisateurs);
		evenementRepo.save(e);
		
		
	}

	@Override
	public void annulerParticipation(Long idevent, Long idUser) {
		Evenement e = getEventById(idevent);
		User u = userRepo.findById(idUser).get();
		Set<User> utilisateurs = e.getParticipation();
		utilisateurs.remove(u);
		e.setParticipation(utilisateurs);
		evenementRepo.save(e);
		
	}

}

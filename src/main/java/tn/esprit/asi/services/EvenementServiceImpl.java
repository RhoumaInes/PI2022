package tn.esprit.asi.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.asi.entities.Evenement;
import tn.esprit.asi.reposetories.EvenementRepo;

@Service
@Slf4j
public class EvenementServiceImpl implements EvenementService {
	@Autowired
	EvenementRepo evenementRepo;

	@Override
	public Set<Evenement> listeAllEvenement() {
		return (Set<Evenement>) evenementRepo.findAll();
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
	public void deleteEvent(Evenement ev) {
		evenementRepo.delete(ev);
	}

	@Override
	public Evenement updateEvent(Evenement ev) {
		return evenementRepo.save(ev);
	}

}

package tn.esprit.asi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.asi.entities.Ratting;
import tn.esprit.asi.reposetories.EvenementRepo;
import tn.esprit.asi.reposetories.RattingRepo;
import tn.esprit.asi.reposetories.UserRepo;

@Service
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	RattingRepo rattingRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	EvenementRepo evenementRepo;

	@Override
	public Ratting addEventRating(Ratting r, Long userId, Long eventId) {
		Ratting rating = showEventRating(userId, eventId);
		System.out.print(rating);
		if(rating==null) {
			r.setUserRatting(userRepo.findById(userId).get());
			r.setEventRatting(evenementRepo.findById(eventId).get());
			return rattingRepo.save(r);
		} else {
			rating.setNote(r.getNote());
			return rattingRepo.save(rating);
		}
		
	}


	@Override
	public Ratting showEventRating(Long userId, Long eventId) {
		return rattingRepo.retrieveRattingEvent(evenementRepo.findById(eventId).get(),userRepo.findById(userId).get());
	}


	@Override
	public Float moyRating(Long eventId) {
		return rattingRepo.moyenneRattingEvent(evenementRepo.findById(eventId).get());
	}

}

package tn.esprit.asi.services;

import tn.esprit.asi.entities.Ratting;

public interface RatingService {
	public Ratting addEventRating(Ratting r,Long userId, Long eventId);
	public Ratting showEventRating(Long userId, Long eventId);
	
	public Float moyRating(Long eventId);
}

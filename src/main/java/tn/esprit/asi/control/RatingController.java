package tn.esprit.asi.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.asi.entities.Ratting;
import tn.esprit.asi.services.RatingServiceImpl;

@RestController
@Api(tags = "Rating management")
@RequestMapping("/rating")
public class RatingController {
	@Autowired
	RatingServiceImpl ratingServiceImpl;
	
	@PostMapping("/add-eventRating/{user-id}/{event-id}")
	@ResponseBody
	public Ratting addEventRating(@RequestBody Ratting r, @PathVariable("user-id") Long userId, @PathVariable("event-id") Long eventId) {
		return ratingServiceImpl.addEventRating(r, userId, eventId);
	}
	

}

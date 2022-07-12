package tn.esprit.asi.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.asi.entities.Evenement;
import tn.esprit.asi.services.EvenementServiceImpl;

@RestController
@Api(tags = "Event management")
@RequestMapping("/evenement")
public class EvenementController {
	@Autowired
	EvenementServiceImpl evenementServiceImpl;
	
	@PostMapping("/add-event")
	@ResponseBody
	public Evenement addEvent(@RequestBody Evenement ev)
	{
		return evenementServiceImpl.addEvent(ev);
	}
	
	@GetMapping("/liste-des-evenements")
	@ResponseBody
	public List<Evenement> listingEvents() {
		return evenementServiceImpl.listeAllEvenement();
	}
	
	@GetMapping("/evenement-par-id/{event-id}")
	@ResponseBody
	public Evenement getById(@PathVariable("event-id") Long Id) {
		return evenementServiceImpl.getEventById(Id);
	}
	
	@PutMapping("/update-event")
	@ResponseBody
	public Evenement updateEvent(@RequestBody Evenement ev)
	{
		return evenementServiceImpl.updateEvent(ev);
	}
	
	@PutMapping("/add-participation/{event-id}/{user-id}")
	@ResponseBody
	public void addParticipation(@PathVariable("event-id") Long IdEv, @PathVariable("user-id") Long IdUser)
	{
		evenementServiceImpl.participation(IdEv, IdUser);
	}
	
	@PutMapping("/annuler-participation/{event-id}/{user-id}")
	@ResponseBody
	public void annulerParticipation(@PathVariable("event-id") Long IdEv, @PathVariable("user-id") Long IdUser)
	{
		evenementServiceImpl.annulerParticipation(IdEv,IdUser);
	}
	
	@DeleteMapping("/remove-event/{event-id}")
	@ResponseBody
	public void deleteEvent(@PathVariable("event-id") Long Id) {
		evenementServiceImpl.deleteEvent(Id);
	}
	
	@GetMapping("/place-dispo/{event-id}")
	@ResponseBody
	public Long placeDispon(@PathVariable("event-id") Long Id) {
		return evenementServiceImpl.placeDispo(Id);
	}

}

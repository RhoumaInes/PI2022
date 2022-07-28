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
import tn.esprit.asi.entities.Participation;
import tn.esprit.asi.services.EvenementServiceImpl;
import tn.esprit.asi.services.ParticipationServiceImpl;

@RestController
@Api(tags = "Event management")
@RequestMapping("/evenement")
public class EvenementController {
	@Autowired
	EvenementServiceImpl evenementServiceImpl;
	@Autowired
	ParticipationServiceImpl participationServiceImpl;
	
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
	
	@GetMapping("/liste-des-participants/{event-id}")
	@ResponseBody
	public List<Participation> listingParticip(@PathVariable("event-id") Long Id) {
		return participationServiceImpl.listParticipationEvent(Id);
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
	public Boolean addParticipation(@PathVariable("event-id") Long IdEv, @PathVariable("user-id") Long IdUser)
	{
		return evenementServiceImpl.participation(IdEv, IdUser);
	}
	
	@PutMapping("/annuler-participation/{participation-id}")
	@ResponseBody
	public Boolean annulerParticipation(@PathVariable("participation-id") Long IdPart)
	{
		return participationServiceImpl.annulerParticipation(IdPart);
	}
	
	@PutMapping("/annuler-participation/{event-id}/{user-id}")
	@ResponseBody
	public Boolean annulerParticip(@PathVariable("event-id") Long IdEv, @PathVariable("user-id") Long IdUser)
	{
		return participationServiceImpl.annulerParticipEvent(IdEv,IdUser);
	}
	
	@DeleteMapping("/remove-event/{event-id}")
	@ResponseBody
	public Boolean deleteEvent(@PathVariable("event-id") Long Id) {
		return evenementServiceImpl.deleteEvent(Id);
	}
	
	@GetMapping("/place-dispo/{event-id}")
	@ResponseBody
	public Long placeDispon(@PathVariable("event-id") Long Id) {
		return evenementServiceImpl.placeDispo(Id);
	}
	
	@GetMapping("/nbr-participants/{event-id}")
	@ResponseBody
	public Long nbrParticipants(@PathVariable("event-id") Long Id) {
		return participationServiceImpl.nombreParticipants(Id);
	}
	
	@GetMapping("/check-participation/{event-id}/{user-id}")
	@ResponseBody
	public Boolean checkParticipation(@PathVariable("event-id") Long Id,@PathVariable("user-id") Long userId) {
		return participationServiceImpl.checkParticipation(Id, userId);
	}
	
	@PutMapping("/resilier-participation/{participation-id}")
	@ResponseBody
	public void resilierParticipation(@PathVariable("participation-id") Long IdPart) {
		participationServiceImpl.resilierParticipation(IdPart);
	}
	
	@DeleteMapping("/remove-participant/{participation-id}")
	@ResponseBody
	public void deleteParticipant(@PathVariable("participation-id") Long idParticipation) {
		participationServiceImpl.supprimerParticipation(idParticipation);
	}

}

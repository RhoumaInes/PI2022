package tn.esprit.asi.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.asi.entities.Evenement;
import tn.esprit.asi.services.EvenementServiceImpl;

@RestController
@Api(tags = "Event management")
@RequestMapping("/evenement/")
public class EvenementController {
	@Autowired
	EvenementServiceImpl evenementServiceImpl;
	
	@PostMapping("/add-event")
	@ResponseBody
	public Evenement addEvent(@RequestBody Evenement ev)
	{
		return evenementServiceImpl.addEvent(ev);
	}

}

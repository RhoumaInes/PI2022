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
import lombok.extern.slf4j.Slf4j;
import tn.esprit.asi.entities.Evenement;
import tn.esprit.asi.entities.PublicationForum;
import tn.esprit.asi.services.PublicationServiceImp;

@RestController
@RequestMapping("/Publication")
@Slf4j
public class PublicationController {
	
	@Autowired
	PublicationServiceImp PubSce;
	
	
	//http://localhost:8180/SpringMVC/Publication/add-Pub
	@PostMapping("/add-Pub/{Pub-id}")
	@ResponseBody
	public PublicationForum addPub(@RequestBody PublicationForum pub, @PathVariable("Pub-id") Long PubId)
	{
		return PubSce.ajouterPublication(pub,PubId);
	}
	
	//http://localhost:8180/SpringMVC/Publication/Get
	@GetMapping("/Get")
	@ResponseBody
	public List<PublicationForum> GetPub()
		{
			return  PubSce.listePub();
		}
	//http://localhost:8180/SpringMVC/Publication/Delete
		@DeleteMapping("/Delete/{Pub-id}")
		@ResponseBody
		public void DeletePub(@PathVariable("Pub-id") Long id)
			{
				PubSce.Supp(id);
			}
		
		@PutMapping("/update")
		@ResponseBody
		public PublicationForum updateEvent(@RequestBody PublicationForum pub)
		{
			return PubSce.modifPup(pub);
		}

}

package tn.esprit.asi.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.asi.entities.Commentaire;
import tn.esprit.asi.services.CommentaireServiceImp;

@RestController
@RequestMapping("/Commentaire")
public class CommentaireController {
	
	@Autowired
	CommentaireServiceImp ComSce;

	//http://localhost:8180/SpringMVC/Commentaire/add-Com
	@PostMapping("/add-Com/{Pub-id}/{User-id}")
	@ResponseBody
	public Commentaire addPub(@RequestBody Commentaire com, @PathVariable("Pub-id") Long PubId, @PathVariable("User-id") Long UserId)
	{
		return ComSce.ajouterCom(com, PubId, UserId);
	}
	

}

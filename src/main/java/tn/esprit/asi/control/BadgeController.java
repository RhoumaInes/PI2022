package tn.esprit.asi.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.asi.entities.Badge;
import tn.esprit.asi.entities.User;
import tn.esprit.asi.reposetories.UserRepo;
import tn.esprit.asi.services.BadgeServiceImpl;


@RestController
@RequestMapping("/badge")
public class BadgeController {
	
	@Autowired
	BadgeServiceImpl badgeService;
	@Autowired
	UserRepo userRepo;
	
	@PostMapping("/createBadge/{user-id}")
	@ResponseBody
	public ResponseEntity createBadge(@RequestBody Badge badge, @PathVariable("user-id") Long userId) {
				badge.setRelatedToUser(userRepo.findById(userId).orElse(null));
				badgeService.addBadge(badge);
		    return new ResponseEntity<>(badge, HttpStatus.OK);

	}
	
	@PutMapping("/transformToTrophe")
	@ResponseBody
	public Badge transformToTrophe(@RequestBody Badge badge) {
		badgeService.transformToTrophe(badge);
		return badge;
	}
	
	@GetMapping("/getBadgeByUser/{user-id}")
	@ResponseBody
	public Badge getBadgeByUser(@PathVariable("user-id") Long userId) {
		User user = userRepo.findById(userId).orElse(null);
		return badgeService.getBadgeByUser(user);
	}

}

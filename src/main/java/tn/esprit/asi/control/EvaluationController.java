package tn.esprit.asi.control;

import java.util.List;

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

import io.swagger.annotations.Api;
import tn.esprit.asi.entities.Evaluation;
import tn.esprit.asi.entities.User;
import tn.esprit.asi.reposetories.UserRepo;
import tn.esprit.asi.services.EvaluationServiceImpl;


@RestController
@Api(tags = "Manage Evaluation")
@RequestMapping("/eval")
public class EvaluationController {


	@Autowired
	EvaluationServiceImpl evaluationService;
	@Autowired
	UserRepo userRepo;
	
	@PostMapping("/addEvaluation/{userTo-id}/{userFrom-id}")
	@ResponseBody
	public ResponseEntity addEvaluation(@RequestBody Evaluation eval, @PathVariable("userTo-id") Long userToId, @PathVariable("userFrom-id") Long userFromId) {
			
		
		if(eval.getUserFrom()==null || eval.getUserTo()==null) {
				return ResponseEntity
				        .status(HttpStatus.NOT_FOUND)
				        .body("User is mandatory");
			}else {
				eval.setUserTo(userRepo.findById(userToId).orElse(null));
				eval.setUserFrom(userRepo.findById(userFromId).orElse(null));
				evaluationService.addEval(eval);
			}
		    return new ResponseEntity<>(eval, HttpStatus.OK);

	}

	@PutMapping("/updateEvaluation/{userTo-id}/{userFrom-id}")
	@ResponseBody
	public Evaluation updateEvaluation(@RequestBody Evaluation eval, @PathVariable("userTo-id") Long userToId, @PathVariable("userFrom-id") Long userFromId) {
		eval.setUserTo(userRepo.findById(userToId).orElse(null));
		eval.setUserFrom(userRepo.findById(userFromId).orElse(null));
		evaluationService.updateEval(eval);
		return eval;
	}
	
	@GetMapping("/listOfEvaluations")
	@ResponseBody
	public List<Evaluation> listOfEvaluations() {
		
		return  evaluationService.getEvals();
	}
	
	@GetMapping("/countEvalByUserTo/{userTo-id}")
	@ResponseBody
	public Long countEvalByUserTo(@PathVariable("userTo-id") Long userToId) {
		User userTo = userRepo.findById(userToId).orElse(null);
		return  evaluationService.countEvalByUserTo(userTo);
	}
	
	@GetMapping("/findEvalByUserTo/{userTo-id}")
	@ResponseBody
	public List<Evaluation> findEvalByUserTo(@PathVariable("userTo-id") Long userToId) {
		User userTo = userRepo.findById(userToId).orElse(null);
		return  evaluationService.findEvalByUserTo(userTo);
	}
	@GetMapping("/findEvalByUserFrom/{userFrom-id}")
	@ResponseBody
	public List<Evaluation> findEvalByUserFrom(@PathVariable("userFrom-id") Long userFromId) {
		User userTo = userRepo.findById(userFromId).orElse(null);
		return  evaluationService.findEvalByUserFrom(userTo);
	}
	
	@GetMapping("/evaluationAverageByUserTo/{userTo-id}")
	@ResponseBody
	public Float evaluationAverage(@PathVariable("userTo-id") Long userToId) {
		User userTo = userRepo.findById(userToId).orElse(null);
		return  evaluationService.calculateAverageEvalByUserTo(userTo);
	}

}

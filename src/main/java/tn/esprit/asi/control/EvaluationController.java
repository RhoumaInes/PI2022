package tn.esprit.asi.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.asi.entities.Evaluation;
import tn.esprit.asi.services.EvaluationService;

@RestController
@Api(tags = "Manage Evaluation")
@RequestMapping("/eval")
public class EvaluationController {


	@Autowired
	EvaluationService evaluationService;
	
	@PostMapping("/addEvaluation")
	@ResponseBody
	public Evaluation addEvaluation(@RequestBody Evaluation eval) {
		evaluationService.addEval(eval);
		return eval;
	}

	@PutMapping("/updateEvaluation")
	@ResponseBody
	public Evaluation updateEvaluation(@RequestBody Evaluation eval) {
		evaluationService.addEval(eval);
		return eval;
	}
	
	@GetMapping("/listOfEvaluations")
	@ResponseBody
	public List<Evaluation> listOfEvaluations() {
		
		return  evaluationService.getEvals();
	}

}

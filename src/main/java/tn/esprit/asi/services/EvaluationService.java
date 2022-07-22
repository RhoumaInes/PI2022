package tn.esprit.asi.services;

import java.util.List;


import tn.esprit.asi.entities.Evaluation;
import tn.esprit.asi.entities.User;

public interface EvaluationService {
	Long addEval(Evaluation eval);

	Long updateEval (Evaluation eval);
	
	List<Evaluation> getEvals();
	
	Long countEvalByUserTo(User userTo);
	
	List<Evaluation> findEvalByUserTo(User userTo);
	
	List<Evaluation> findEvalByUserFrom(User userFrom);
	
	Float calculateAverageEvalByUserTo(User userTo);

}

package tn.esprit.asi.services;

import java.util.List;


import tn.esprit.asi.entities.Evaluation;

public interface EvaluationService {
	Long addEval(Evaluation eval);

	Long updateEval (Evaluation eval);
	
	List<Evaluation> getEvals();
}

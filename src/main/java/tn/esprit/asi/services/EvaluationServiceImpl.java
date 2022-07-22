package tn.esprit.asi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.asi.entities.Evaluation;
import tn.esprit.asi.entities.User;
import tn.esprit.asi.reposetories.EvaluationRepo;

@Service
@Slf4j
public class EvaluationServiceImpl implements EvaluationService {
	
	@Autowired
	EvaluationRepo evalRepo;
	
	@Override
	public Long addEval(Evaluation eval) {
		try {
			evalRepo.save(eval);
		}catch (Exception e){
			log.error("error while evaluate profil : "+ e.getMessage());
		}
		log.info("save complete");

		return eval.getId();
	}

	@Override
	public Long updateEval(Evaluation eval) {
		evalRepo.save(eval);
		return eval.getId();
	}

	@Override
	public List<Evaluation> getEvals() {
		return evalRepo.findAll();
	}

	@Override
	public Long countEvalByUserTo(User userTo) {
		return evalRepo.countEvalByUserTo(userTo);
	}

	@Override
	public Float calculateAverageEvalByUserTo(User userTo) {
		return evalRepo.evaluationAverageByUserTo(userTo);
	}

	@Override
	public List<Evaluation> findEvalByUserTo(User userTo) {
		return evalRepo.findEvalByUserTo(userTo);
	}

	@Override
	public List<Evaluation> findEvalByUserFrom(User userFrom) {
		return evalRepo.findEvalByUserFrom(userFrom);
	}

}

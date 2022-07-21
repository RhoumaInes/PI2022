package tn.esprit.asi.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.esprit.asi.control.BadgeController;
import tn.esprit.asi.control.EvaluationController;
import tn.esprit.asi.entities.Badge;
import tn.esprit.asi.services.BadgeServiceImpl;

@Component
@Aspect
public class BadgeCreationAspect {

	@Autowired
	BadgeController badgeController;
	@Autowired
	EvaluationController evalController;
	
	@After("execution(* tn.esprit.asi.control.EvaluationController.addEvaluation(..))")
	public void createBadge(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		Long userId = (Long) args[1];
		Float avg = evalController.evaluationAverage(userId);
		Badge badge= badgeController.getBadgeByUser(userId);
		if(badge!=null && avg == 10) {
			badge.setIsTrophy(true);
			badgeController.transformToTrophe(badge);
		}else if(badge!=null && avg<10) {
			badge.setIsTrophy(false);
			badgeController.transformToTrophe(badge);		}
		else if(badge==null && avg >=5) {
			Badge newBadge = new Badge();
			newBadge.setId(new Long(123));
			newBadge.setIsTrophy(false);
			badgeController.createBadge(newBadge, userId);
		}
		
		
	}
}
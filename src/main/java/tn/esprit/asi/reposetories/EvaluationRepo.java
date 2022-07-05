package tn.esprit.asi.reposetories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tn.esprit.asi.entities.Evaluation;

@Repository
public interface EvaluationRepo extends JpaRepository<Evaluation, Long> {
	
}

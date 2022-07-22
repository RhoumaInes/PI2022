package tn.esprit.asi.reposetories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.asi.entities.Evaluation;
import tn.esprit.asi.entities.User;

@Repository
public interface EvaluationRepo extends JpaRepository<Evaluation, Long> {
	
	@Query("Select "
			+ "count(e) from Evaluation e "
			+ "where e.userTo=:userTo ")
	public Long countEvalByUserTo(@Param("userTo")User userTo);
	
	@Query("Select "
			+ "(e) from Evaluation e "
			+ "where e.userTo=:userTo ")
	public List<Evaluation> findEvalByUserTo(@Param("userTo")User userTo);
	
	@Query("Select "
			+ "AVG(note) from Evaluation e "
			+ "where e.userTo=:userTo ")
	public Float evaluationAverageByUserTo(@Param("userTo")User userTo);
}

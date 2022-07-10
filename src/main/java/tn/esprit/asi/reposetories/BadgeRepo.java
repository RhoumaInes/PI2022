package tn.esprit.asi.reposetories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.asi.entities.Badge;
import tn.esprit.asi.entities.User;

@Repository
public interface BadgeRepo extends JpaRepository<Badge, Long>{
	
	@Query("SELECT b FROM Badge b WHERE b.relatedToUser= :user")
	public Badge retrieveBadgeByUser(@Param("user") User user);
}

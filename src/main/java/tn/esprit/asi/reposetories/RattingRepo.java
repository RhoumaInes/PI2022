package tn.esprit.asi.reposetories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.asi.entities.Evenement;
import tn.esprit.asi.entities.Ratting;
import tn.esprit.asi.entities.User;

public interface RattingRepo extends CrudRepository<Ratting, Long> {
	@Query("SELECT r FROM Ratting r WHERE r.eventRatting= :event AND r.userRatting= :user")
	public Ratting retrieveRattingEvent(@Param("event") Evenement event,@Param("user") User user);
	
	@Query("SELECT avg(r.note) FROM Ratting r WHERE r.eventRatting= :event")
	public Float moyenneRattingEvent(@Param("event") Evenement event);

}

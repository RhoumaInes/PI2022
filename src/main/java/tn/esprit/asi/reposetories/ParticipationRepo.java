package tn.esprit.asi.reposetories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.asi.entities.Evenement;
import tn.esprit.asi.entities.Participation;
import tn.esprit.asi.entities.User;

public interface ParticipationRepo extends CrudRepository<Participation,Long> {
	@Query("SELECT p FROM Participation p WHERE p.evenementPart= :event AND p.userPart= :user")
	public Participation retrieveParticipationEvent(@Param("event") Evenement event,@Param("user") User user);
	
	@Query("SELECT count(p) FROM Participation p WHERE p.evenementPart= :event AND p.annulation<>true AND p.status<>false")
	public Long placeDispo(@Param("event") Evenement event);
	
	@Query("SELECT p FROM Participation p WHERE p.evenementPart= :event")
	public List<Participation> retrieveParticipationEvent(@Param("event") Evenement event);

}

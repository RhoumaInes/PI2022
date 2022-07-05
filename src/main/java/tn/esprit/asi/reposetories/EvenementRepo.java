package tn.esprit.asi.reposetories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.asi.entities.Evenement;

@Repository
public interface EvenementRepo extends CrudRepository<Evenement,Long> {

}

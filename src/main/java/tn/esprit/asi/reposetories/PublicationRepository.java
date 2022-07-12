package tn.esprit.asi.reposetories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.asi.entities.PublicationForum;

@Repository
public interface PublicationRepository extends CrudRepository<PublicationForum, Long> {

}

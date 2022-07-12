package tn.esprit.asi.reposetories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.asi.entities.Commentaire;


@Repository
public interface CommentaireRepository extends CrudRepository<Commentaire, Long>{

}

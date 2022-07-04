package tn.esprit.asi.reposetories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.asi.entities.User;

@Repository
public interface UserRepo extends CrudRepository<User,Long> {

}

package tn.esprit.asi.reposetories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.asi.entities.UserRole;

@Repository
public interface UserRoleRepo extends CrudRepository<UserRole, Long> {
}

package tn.esprit.asi.reposetories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.asi.entities.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.UserName = :UserName OR u.Email = :UserName")
    public User FindUserByUserName(@Param("UserName") String UserName);

    @Query("SELECT u FROM User u WHERE u.UserName = :UserName AND u.Email = :Email AND u.IDUser = :ID")
    public User FindUserByUnique(@Param("ID") Long id, @Param("UserName") String UserName, @Param("Email") String email);
}

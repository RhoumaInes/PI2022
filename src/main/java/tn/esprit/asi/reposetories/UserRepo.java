package tn.esprit.asi.reposetories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.asi.entities.User;
import tn.esprit.asi.entities.UserState;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.UserName = :UserName")
    public User FindUserByUserName(@Param("UserName") String UserName);

    @Query("SELECT u FROM User u WHERE u.UserName = :login OR u.Email=:login")
    public User FindUserByLogin(@Param("login") String login);

    @Query("SELECT u FROM User u WHERE u.Email = :Email")
    public User FindUserByEmail(@Param("Email") String Email);

    @Query("SELECT u FROM User u WHERE u.UserName = :UserName AND u.Email = :Email AND u.IDUser = :ID")
    public User FindUserByUnique(@Param("ID") Long id, @Param("UserName") String UserName, @Param("Email") String email);

    @Query("SELECT u FROM User u WHERE u.EmailVerifyKey = :key AND u.Etat='UNACTIVATED'")
    public User FindUserByEmailVerifyKey(@Param("key") String key);

    @Query("SELECT u FROM User u WHERE u.Email = :Email AND u.Etat='ACTIVATED'")
    public User FindActiveUserByEmail(@Param("Email") String Email);

    @Query("SELECT u FROM User u WHERE u.PasswordResetKey = :key")
    public User FindActiveUserByPasswordkey(@Param("key") String key);

}

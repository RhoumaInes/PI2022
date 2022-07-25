package tn.esprit.asi.services;

import tn.esprit.asi.entities.User;
import tn.esprit.asi.payload.SignUpRequest;

import java.util.List;

public interface IUserService {

    boolean CreateUser(User user, String URL) throws Exception;

    boolean SignUp(SignUpRequest user, String URL) throws Exception;

    boolean CreateAdmin(User user) throws Exception;

    boolean UpdateUser(User user) throws Exception;

    User ToSignInUser(String Login, String Password) throws Exception;

    boolean AlterUserName(Long IDUser, String UserName, String Password) throws Exception;

    boolean AlterEmail(Long IDUser, String Email, String Password) throws Exception;

    boolean AlterPassword(Long IDUser, String NewPassword, String Password) throws Exception;

    boolean ValidateEmail(String key) throws Exception;

    boolean resendEmail(String Login, String URL) throws Exception;

    boolean TryToResetPassword(String Email, String URL) throws Exception;

    public boolean ResetPassword(String NewPassword, String key) throws Exception;

    public User fetchUserByID(Long IDUser);

    List<User> fetch();

    boolean checkEmailAvailability(String Email) throws Exception;

    boolean checkUsernameAvailability(String UserName) throws Exception;

    User fetchUserByLogin(String login);
}

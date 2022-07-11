package tn.esprit.asi.services;

import tn.esprit.asi.entities.User;

public interface IUserService {

    boolean CreateUser(User user, String URL);

    boolean UpdateUser(User user);

    boolean ToSignInUser(String UserName, String Password);

    boolean AlterUserName(Long IDUser, String UserName, String Password);

    boolean AlterEmail(Long IDUser, String Email, String Password);

    boolean AlterPassword(Long IDUser, String NewPassword, String Password);

    boolean ValidateEmail(String key);

    boolean TryToResetPassword(String Email, String URL);

    public boolean ResetPassword(String NewPassword, String key);

    public User fetchUserByID(Long IDUser);
}

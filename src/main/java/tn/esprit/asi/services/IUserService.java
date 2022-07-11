package tn.esprit.asi.services;

import tn.esprit.asi.entities.User;

public interface IUserService {

    boolean CreateUser(User user, String URL) throws Exception;

    boolean CreateAdmin(User user) throws Exception;

    boolean UpdateUser(User user) throws Exception;

    User ToSignInUser(String Login, String Password) throws Exception;

    boolean AlterUserName(Long IDUser, String UserName, String Password) throws Exception;

    boolean AlterEmail(Long IDUser, String Email, String Password) throws Exception;

    boolean AlterPassword(Long IDUser, String NewPassword, String Password) throws Exception;

    boolean ValidateEmail(String key) throws Exception;

    boolean TryToResetPassword(String Email, String URL) throws Exception;

    public boolean ResetPassword(String NewPassword, String key) throws Exception;

    public User fetchUserByID(Long IDUser);
}

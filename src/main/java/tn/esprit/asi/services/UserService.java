package tn.esprit.asi.services;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tn.esprit.asi.Utils.PasswordUtils;
import tn.esprit.asi.entities.User;
import tn.esprit.asi.entities.UserState;
import tn.esprit.asi.reposetories.UserRepo;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment environment;

    @Override
    public boolean CreateUser(User user, String URL) throws Exception {
        //Remove space from username if exist
        user.setUserName(user.getUserName().replaceAll("\\s+", ""));

        if (!EmailValidator.getInstance().isValid(user.getEmail()))
            throw new Exception("Invalid Email");

        if (userRepo.FindUserByUserName(user.getUserName()) != null || userRepo.FindUserByEmail(user.getEmail()) != null)
            throw new Exception("User Exist");

        String randomCode = RandomString.make(64);
        user.setEmailVerifyKey(randomCode);
        user.setDateEmailVerifyKey(new Date());

        String ecryptedPass = PasswordUtils.generateSecurePassword(user.getPassword());
        user.setEncryPassword(ecryptedPass);

        Date dd = new Date();
        user.setDateInsertion(dd);
        user.setDateModification(dd);

        user.setEtat(UserState.UNACTIVATED);

        userRepo.save(user);
        sendVerificationEmail(user, URL);

        return true;
    }

    public boolean CreateAdmin(User user) throws Exception {
        //Remove space from username if exist
        user.setUserName(user.getUserName().replaceAll("\\s+", ""));

        if (!EmailValidator.getInstance().isValid(user.getEmail()))
            throw new Exception("Invalid Email");

        if (userRepo.FindUserByUserName(user.getUserName()) != null || userRepo.FindUserByEmail(user.getEmail()) != null)
            throw new Exception("User Exist");

        String randomCode = RandomString.make(64);
        user.setEmailVerifyKey(randomCode);
        user.setDateEmailVerifyKey(new Date());

        String ecryptedPass = PasswordUtils.generateSecurePassword(user.getPassword());
        user.setEncryPassword(ecryptedPass);

        Date dd = new Date();
        user.setDateInsertion(dd);
        user.setDateModification(dd);

        user.setEtat(UserState.ACTIVATED);

        userRepo.save(user);

        return true;
    }

    @Override
    public boolean UpdateUser(User user) throws Exception {
        //Remove space from username if exist
        user.setUserName(user.getUserName().replaceAll("\\s+", ""));

        User userSaved = userRepo.FindUserByUnique(user.getIDUser(), user.getUserName(), user.getEmail());
        if (userSaved == null) throw new Exception("User Invalid");

        userSaved.setAge(user.getAge());
        userSaved.setDateNaissance(user.getDateNaissance());
        userSaved.setNom(user.getNom());
        userSaved.setPrenom(user.getPrenom());
        userSaved.setPays(user.getPays());
        userSaved.setPosteActuel(user.getPosteActuel());
        userSaved.setSecteur(user.getSecteur());
        userSaved.setTitreProfile(user.getTitreProfile());
        userSaved.setVille(user.getVille());

        Date dd = new Date();
        userSaved.setDateModification(dd);

        userRepo.save(userSaved);

        return true;
    }

    @Override
    public User ToSignInUser(String Login, String Password) throws Exception {
        User user = userRepo.FindUserByLogin(Login);
        if (user == null) throw new Exception("Invalid User");
        if (!PasswordUtils.verifyUserPassword(Password, user.getEncryPassword()))
            throw new Exception("Invalid User");

        return user;
    }

    @Override
    public boolean AlterUserName(Long IDUser, String UserName, String Password) throws Exception {

        User user = userRepo.findById(IDUser).orElse(null);
        if (user == null) throw new Exception("Invalid User");
        if (!PasswordUtils.verifyUserPassword(Password, user.getEncryPassword()))
            throw new Exception("Invalid User");
        user.setUserName(UserName);
        userRepo.save(user);

        return true;
    }

    @Override
    public boolean AlterEmail(Long IDUser, String Email, String Password) throws Exception {

        User user = userRepo.findById(IDUser).orElse(null);
        if (user == null) throw new Exception("Invalid User");
        if (!PasswordUtils.verifyUserPassword(Password, user.getEncryPassword()))
            throw new Exception("Invalid User");
        if (!EmailValidator.getInstance().isValid(Email)) throw new Exception("Invalid Email");
        user.setEmail(Email);
        userRepo.save(user);

        return true;
    }

    @Override
    public boolean AlterPassword(Long IDUser, String NewPassword, String Password) throws Exception {

        User user = userRepo.findById(IDUser).orElse(null);
        if (user == null) throw new Exception("Invalid User");
        if (!PasswordUtils.verifyUserPassword(Password, user.getEncryPassword()))
            throw new Exception("Invalid User");
        String ecryptedPass = PasswordUtils.generateSecurePassword(NewPassword);
        user.setEncryPassword(ecryptedPass);
        //Disconnect all devices
        userRepo.save(user);

        return true;
    }

    public boolean resendEmail(String Login, String URL) throws Exception {
        User user = userRepo.FindUserByLogin(Login);
        if (user == null) throw new Exception("Invalid User");

        if (user.getEtat() != UserState.UNACTIVATED) throw new Exception("User UNAUTHORIZED");

        String randomCode = RandomString.make(64);
        user.setEmailVerifyKey(randomCode);
        user.setDateEmailVerifyKey(new Date());

        userRepo.save(user);
        sendVerificationEmail(user, URL);

        return true;
    }

    private void sendVerificationEmail(User user, String URL) {
        try {
            String toAddress = user.getEmail();
            String fromAddress = environment.getProperty("spring.mail.username");
            String senderName = "Bien etre au ravail";
            String subject = "Veuillez vérifier votre email";
            String verifyURL = URL + "/verify?key=" + user.getEmailVerifyKey();
            String content = "Bonjour " + user.getUserName() + ",<br>"
                    + "Veuillez cliquer sur le lien ci-dessous pour vérifier votre adresse email:<br>"
                    + "<h3><a href=\"" + verifyURL + "\" target=\"_self\">Vérifier</a></h3>"
                    + "Merci,<br>"
                    + "Bien etre au ravail.";

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public boolean ValidateEmail(String key) throws Exception {
        User user = userRepo.FindUserByEmailVerifyKey(key);
        if (user == null) throw new Exception("Invalid User");
        final int MILLI_TO_HOUR = 1000 * 60 * 60;
        if ((new Date().getTime() - user.getDateEmailVerifyKey().getTime()) / MILLI_TO_HOUR > 1)
            throw new Exception("Expired");

        user.setEmailVerifyKey(null);
        user.setDateEmailVerifyKey(null);

        user.setEtat(UserState.ACTIVATED);
        user.setDateModification(new Date());
        userRepo.save(user);

        return true;
    }

    public boolean TryToResetPassword(String Email, String URL) throws Exception {

        if (!EmailValidator.getInstance().isValid(Email)) throw new Exception("Invalid Email");

        User user = userRepo.FindActiveUserByEmail(Email);
        if (user == null) throw new Exception("Invalid User");

        String randomCode = RandomString.make(64);
        user.setPasswordResetKey(randomCode);
        user.setDatePasswordResetKey(new Date());

        userRepo.save(user);
        sendResetEmail(user, URL);

        return true;
    }

    private void sendResetEmail(User user, String URL) {
        try {
            String toAddress = user.getEmail();
            String fromAddress = environment.getProperty("spring.mail.username");
            String senderName = "Bien etre au ravail";
            String subject = "Réinitialisez votre mot de passe";
            String verifyURL = URL + "/reset?key=" + user.getPasswordResetKey();
            String content = "Bonjour " + user.getUserName() + ",<br>"
                    + "Nous avons reçu une demande de réinitialisation de votre mot de passe.<br>"
                    + "<h3><a href=\"" + verifyURL + "\" target=\"_self\">Réinitialiser</a></h3>"
                    + "Merci,<br>"
                    + "Bien etre au ravail.";

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public boolean ResetPassword(String NewPassword, String key) throws Exception {

        User user = userRepo.FindActiveUserByPasswordkey(key);
        if (user == null) throw new Exception("Invalid User");
        String ecryptedPass = PasswordUtils.generateSecurePassword(NewPassword);
        user.setEncryPassword(ecryptedPass);
        user.setDateModification(new Date());
        userRepo.save(user);

        return true;
    }

    public User fetchUserByID(Long IDUser) {
        return userRepo.findById(IDUser).orElse(null);
    }

    public List<User> fetch() {
        return userRepo.FindAllUser();
    }
}

package tn.esprit.asi.services;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tn.esprit.asi.Utils.PasswordUtils;
import tn.esprit.asi.entities.User;
import tn.esprit.asi.entities.UserState;
import tn.esprit.asi.reposetories.UserRepo;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public boolean CheckUser(String UserName) {
        boolean returnedValue = true;

        try {

            User user = userRepo.FindUserByUserName(UserName);
            if (user == null)
                throw new Exception("Invalid User");

        } catch (Exception e) {
            log.error(e.getMessage());
            returnedValue = false;
        }

        return returnedValue;
    }

    @Override
    public boolean CreateUser(User user, String URL) {
        boolean returnedValue = true;

        try {
            //Remove space from username if exist
            user.setUserName(user.getUserName().replaceAll("\\s+", ""));

            if (CheckUser(user.getUserName()) || CheckUser(user.getEmail()))
                throw new Exception("User Exist");

            if (!EmailValidator.getInstance().isValid(user.getEmail()))
                throw new Exception("Invalid Email");

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
        } catch (Exception e) {
            log.error(e.getMessage());
            returnedValue = false;
        }

        return returnedValue;
    }

    @Override
    public boolean UpdateUser(User user) {
        boolean returnedValue = true;

        try {
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

        } catch (Exception e) {
            log.error(e.getMessage());
            returnedValue = false;
        }

        return returnedValue;
    }

    @Override
    public boolean ToSignInUser(String UserName, String Password) {
        boolean returnedValue = true;

        try {

            User user = userRepo.FindUserByUserName(UserName);
            if (user == null) throw new Exception("Invalid User");
            if (!PasswordUtils.verifyUserPassword(Password, user.getEncryPassword()))
                throw new Exception("Invalid User");

        } catch (Exception e) {
            log.error(e.getMessage());
            returnedValue = false;
        }

        return returnedValue;
    }

    @Override
    public boolean AlterUserName(Long IDUser, String UserName, String Password) {
        boolean returnedValue = true;

        try {

            User user = userRepo.findById(IDUser).orElse(null);
            if (user == null) throw new Exception("Invalid User");
            if (!PasswordUtils.verifyUserPassword(Password, user.getEncryPassword()))
                throw new Exception("Invalid User");
            user.setUserName(UserName);
            userRepo.save(user);

        } catch (Exception e) {
            log.error(e.getMessage());
            returnedValue = false;
        }

        return returnedValue;
    }

    @Override
    public boolean AlterEmail(Long IDUser, String Email, String Password) {
        boolean returnedValue = true;

        try {

            User user = userRepo.findById(IDUser).orElse(null);
            if (user == null) throw new Exception("Invalid User");
            if (!PasswordUtils.verifyUserPassword(Password, user.getEncryPassword()))
                throw new Exception("Invalid User");
            if (!EmailValidator.getInstance().isValid(Email)) throw new Exception("Invalid Email");
            user.setEmail(Email);
            userRepo.save(user);

        } catch (Exception e) {
            log.error(e.getMessage());
            returnedValue = false;
        }

        return returnedValue;
    }

    @Override
    public boolean AlterPassword(Long IDUser, String NewPassword, String Password) {
        boolean returnedValue = true;

        try {

            User user = userRepo.findById(IDUser).orElse(null);
            if (user == null) throw new Exception("Invalid User");
            if (!PasswordUtils.verifyUserPassword(Password, user.getEncryPassword()))
                throw new Exception("Invalid User");
            String ecryptedPass = PasswordUtils.generateSecurePassword(NewPassword);
            user.setEncryPassword(ecryptedPass);
            //Disconnect all devices
            userRepo.save(user);

        } catch (Exception e) {
            log.error(e.getMessage());
            returnedValue = false;
        }

        return returnedValue;
    }

    private void sendVerificationEmail(User user, String URL) {
        try {
            String toAddress = user.getEmail();
            String fromAddress = "bienetreautravail@outlook.com";
            String senderName = "Bien etre au ravail";
            String subject = "Veuillez vérifier votre email";
            String content = "Bonjour [[name]],<br>"
                    + "Veuillez cliquer sur le lien ci-dessous pour vérifier votre adresse e-mail:<br>"
                    + "<h3><a href=\"[[URL]]\" target=\"_self\">Vérifier</a></h3>"
                    + "Merci,<br>"
                    + "Bien etre au ravail.";

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);

            content = content.replace("[[name]]", user.getUserName());
            String verifyURL = URL + "/verify?code=" + user.getEmailVerifyKey();

            content = content.replace("[[URL]]", verifyURL);

            helper.setText(content, true);

            mailSender.send(message);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}

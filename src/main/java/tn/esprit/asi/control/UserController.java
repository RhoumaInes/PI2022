package tn.esprit.asi.control;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tn.esprit.asi.Utils.BodyResponse;
import tn.esprit.asi.Utils.ResetPassword;
import tn.esprit.asi.Utils.ResponseStatus;
import tn.esprit.asi.Utils.SignIn;
import tn.esprit.asi.entities.User;
import tn.esprit.asi.entities.UserState;
import tn.esprit.asi.services.IUserService;
import tn.esprit.asi.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/create")
    @ResponseBody
    public BodyResponse<Boolean> createUser(@RequestBody User user, HttpServletRequest request) {
        BodyResponse<Boolean> body = new BodyResponse<>();
        try {
            log.info("user ", user);
            body.setData(userService.CreateUser(user, getSiteURL(request) + "/user"));
            body.setMessage("created");
            body.setStatus(ResponseStatus.DONE);
        } catch (Exception e) {
            log.error(e.getMessage());
            body.setData(false);
            body.setMessage(e.getMessage());
            body.setStatus(ResponseStatus.ERROR);
        }

        return body;
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @PostMapping("/update")
    @ResponseBody
    public BodyResponse<Boolean> updateUser(@RequestBody User user, HttpServletRequest request) {
        BodyResponse<Boolean> body = new BodyResponse<>();
        try {
            body.setData(userService.UpdateUser(user));
            body.setMessage("updated");
            body.setStatus(ResponseStatus.DONE);
        } catch (Exception e) {
            log.error(e.getMessage());
            body.setData(false);
            body.setMessage(e.getMessage());
            body.setStatus(ResponseStatus.ERROR);
        }

        return body;
    }

    @GetMapping("/verify")
    @ResponseBody
    public BodyResponse<Boolean> verifyEmail(@RequestParam("key") String key) {
        BodyResponse<Boolean> body = new BodyResponse<>();
        try {
            body.setData(userService.ValidateEmail(key));
            body.setMessage("validated");
            body.setStatus(ResponseStatus.DONE);
        } catch (Exception e) {
            log.error(e.getMessage());
            body.setData(false);
            body.setMessage(e.getMessage());
            body.setStatus(ResponseStatus.ERROR);
        }

        return body;
    }

    @GetMapping("/fetchbyid/{id}")
    @ResponseBody
    public BodyResponse<User> fetchUser(@PathVariable("id") Long IDUser) {
        BodyResponse<User> body = new BodyResponse<>();
        try {
            body.setData(userService.fetchUserByID(IDUser));
            body.setMessage("ready");
            body.setStatus(ResponseStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            body.setData(null);
            body.setMessage(e.getMessage());
            body.setStatus(ResponseStatus.ERROR);
        }

        return body;
    }

    @GetMapping("/tryresetpassword/{email}")
    @ResponseBody
    public BodyResponse<Boolean> TryToResetPassword(@PathVariable("email") String email, HttpServletRequest request) {
        BodyResponse<Boolean> body = new BodyResponse<>();
        try {
            body.setData(userService.TryToResetPassword(email, ""));
            body.setMessage("reset send");
            body.setStatus(ResponseStatus.DONE);
        } catch (Exception e) {
            log.error(e.getMessage());
            body.setData(false);
            body.setMessage(e.getMessage());
            body.setStatus(ResponseStatus.ERROR);
        }

        return body;
    }

    @PostMapping("/resetpassword")
    @ResponseBody
    public BodyResponse<Boolean> resetPassword(@RequestBody ResetPassword rs) {
        BodyResponse<Boolean> body = new BodyResponse<>();
        try {
            body.setData(userService.ResetPassword(rs.getPassword(), rs.getKey()));
            body.setMessage("reset complete");
            body.setStatus(ResponseStatus.DONE);
        } catch (Exception e) {
            log.error(e.getMessage());
            body.setData(false);
            body.setMessage(e.getMessage());
            body.setStatus(ResponseStatus.ERROR);
        }

        return body;
    }

    @PostMapping("/signin")
    @ResponseBody
    public BodyResponse<Boolean> signin(@RequestBody SignIn signIn) {
        BodyResponse<Boolean> body = new BodyResponse<>();
        try {
            log.info("login", signIn);
            body.setStatus(ResponseStatus.UNACTIVATED);
            User user = userService.ToSignInUser(signIn.getLogin(), signIn.getPassword());
            if (user.getEtat() == UserState.ACTIVATED)
                body.setStatus(ResponseStatus.ACTIVATED);

            body.setData(true);
            body.setMessage("user verified");
        } catch (Exception e) {
            log.error(e.getMessage());
            body.setData(false);
            body.setMessage(e.getMessage());
            body.setStatus(ResponseStatus.ERROR);
        }

        return body;
    }

    @GetMapping("/fetchall")
    @ResponseBody
    public BodyResponse<List<User>> fetchAll(HttpServletRequest request) {
        BodyResponse<List<User>> body = new BodyResponse<>();
        try {
            body.setData(userService.fetch());
            body.setMessage("all users");
            body.setStatus(ResponseStatus.DONE);
        } catch (Exception e) {
            log.error(e.getMessage());
            body.setData(null);
            body.setMessage(e.getMessage());
            body.setStatus(ResponseStatus.ERROR);
        }

        return body;
    }

    @GetMapping("/resendverify/{login}")
    @ResponseBody
    public BodyResponse<Boolean> TryToResentEmail(@PathVariable("login") String login, HttpServletRequest request) {
        BodyResponse<Boolean> body = new BodyResponse<>();
        try {
            body.setData(userService.resendEmail(login, getSiteURL(request) + "/user"));
            body.setMessage("resend");
            body.setStatus(ResponseStatus.DONE);
        } catch (Exception e) {
            log.error(e.getMessage());
            body.setData(false);
            body.setMessage(e.getMessage());
            body.setStatus(ResponseStatus.ERROR);
        }

        return body;
    }
}

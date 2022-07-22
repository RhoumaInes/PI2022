package tn.esprit.asi.control;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.asi.payload.ApiResponse;
import tn.esprit.asi.payload.LoginRequest;
import tn.esprit.asi.payload.ResetPasswordRequest;
import tn.esprit.asi.payload.ResponseStatus;
import tn.esprit.asi.entities.User;
import tn.esprit.asi.entities.UserState;
import tn.esprit.asi.security.JwtTokenProvider;
import tn.esprit.asi.services.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/create")
    @ResponseBody
    public ApiResponse<Boolean> createUser(@RequestBody User user, HttpServletRequest request) {
        ApiResponse<Boolean> body = new ApiResponse<>();
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
    public ApiResponse<Boolean> updateUser(@RequestBody User user, HttpServletRequest request) {
        ApiResponse<Boolean> body = new ApiResponse<>();
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
    public ApiResponse<Boolean> verifyEmail(@RequestParam("key") String key) {
        ApiResponse<Boolean> body = new ApiResponse<>();
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
    public ApiResponse<User> fetchUser(@PathVariable("id") Long IDUser) {
        ApiResponse<User> body = new ApiResponse<>();
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
    public ApiResponse<Boolean> TryToResetPassword(@PathVariable("email") String email, HttpServletRequest request) {
        ApiResponse<Boolean> body = new ApiResponse<>();
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
    public ApiResponse<Boolean> resetPassword(@RequestBody ResetPasswordRequest rs) {
        ApiResponse<Boolean> body = new ApiResponse<>();
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
    public ApiResponse<String> signin(@RequestBody LoginRequest signIn) {
        ApiResponse<String> body = new ApiResponse<>();
        try {

            User user = userService.ToSignInUser(signIn.getLogin(), signIn.getPassword());

            if (user.getEtat() != UserState.ACTIVATED) {
                body.setStatus(ResponseStatus.UNAUTHORIZED);
                body.setData(null);
                return body;
            }

            body.setStatus(ResponseStatus.ACTIVATED);
            String jwt = tokenProvider.generateToken(user.getIDUser());
            body.setData(jwt);
            body.setMessage("connecting");
        } catch (Exception e) {
            log.error(e.getMessage());
            body.setData(null);
            body.setMessage(e.getMessage());
            body.setStatus(ResponseStatus.ERROR);
        }

        return body;
    }

    @GetMapping("/fetchall")
    @ResponseBody
    public ApiResponse<List<User>> fetchAll(HttpServletRequest request) {
        ApiResponse<List<User>> body = new ApiResponse<>();
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
    public ApiResponse<Boolean> TryToResentEmail(@PathVariable("login") String login, HttpServletRequest request) {
        ApiResponse<Boolean> body = new ApiResponse<>();
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

    @GetMapping("/checkusernameavailability/{username}")
    @ResponseBody
    public ApiResponse<Boolean> checkUsernameAvailability(@PathVariable("username") String username, HttpServletRequest request) {
        ApiResponse<Boolean> body = new ApiResponse<>();
        try {
            body.setData(userService.checkUsernameAvailability(username));
            body.setMessage("Available");
            body.setStatus(ResponseStatus.DONE);
        } catch (Exception e) {
            log.error(e.getMessage());
            body.setData(false);
            body.setMessage(e.getMessage());
            body.setStatus(ResponseStatus.ERROR);
        }

        return body;
    }

    @GetMapping("/checkemailavailability/{email}")
    @ResponseBody
    public ApiResponse<Boolean> checkEmailAvailability(@PathVariable("email") String email, HttpServletRequest request) {
        ApiResponse<Boolean> body = new ApiResponse<>();
        try {
            body.setData(userService.checkEmailAvailability(email));
            body.setMessage("Available");
            body.setStatus(ResponseStatus.DONE);
        } catch (Exception e) {
            log.error(e.getMessage());
            body.setData(false);
            body.setMessage(e.getMessage());
            body.setStatus(ResponseStatus.ERROR);
        }

        return body;
    }

    @GetMapping("/fetchbylogin/{login}")
    @ResponseBody
    public ApiResponse<User> fetchUserByLogin(@PathVariable("login") String login) {
        ApiResponse<User> body = new ApiResponse<>();
        try {
            body.setData(userService.fetchUserByLogin(login));
            body.setMessage("Done");
            body.setStatus(ResponseStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            body.setData(null);
            body.setMessage(e.getMessage());
            body.setStatus(ResponseStatus.ERROR);
        }

        return body;
    }
}

package tn.esprit.asi.control;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.asi.payload.*;
import tn.esprit.asi.entities.User;
import tn.esprit.asi.entities.UserState;
import tn.esprit.asi.payload.ResponseStatus;
import tn.esprit.asi.security.JwtTokenProvider;
import tn.esprit.asi.services.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Random;

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

    @PostMapping("/signup")
    @ResponseBody
    public ApiResponse<Boolean> SignUp(@RequestBody SignUpRequest user, HttpServletRequest request) {
        ApiResponse<Boolean> body = new ApiResponse<>();
        try {
            body.setData(userService.SignUp(user, getSiteURL(request) + "/user"));
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
            body.setData(userService.TryToResetPassword(email, "http://localhost:4200/auth/change-password"));
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

    @GetMapping("/verifypasswordkey/{key}")
    @ResponseBody
    public ApiResponse<Boolean> VerifyPasswordkey(@PathVariable("key") String key) {
        ApiResponse<Boolean> body = new ApiResponse<>();
        try {
            body.setData(userService.VerifyPasswordkey(key));
            body.setMessage("All Good");
            body.setStatus(ResponseStatus.OK);
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
    public ApiResponse<SignInResponse> signin(@RequestBody LoginRequest signIn) {
        ApiResponse<SignInResponse> body = new ApiResponse<>();
        try {

            User user = userService.ToSignInUser(signIn.getLogin(), signIn.getPassword());

            if (user.getEtat() == UserState.UNACTIVATED) {
                body.setStatus(ResponseStatus.UNACTIVATED);
                body.setData(null);
                return body;
            }
            if (user.getEtat() == UserState.REMOVED) {
                body.setStatus(ResponseStatus.UNAUTHORIZED);
                body.setData(null);
                return body;
            }

            body.setStatus(ResponseStatus.ACTIVATED);
            String jwt = tokenProvider.generateToken(user.getIDUser());
            body.setData(new SignInResponse(jwt, user.getIDUser(), user.getUserName()));
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

    @PostMapping("/saveprofileimage")
    @ResponseBody
    public ApiResponse<Boolean> uploadProfileImage(@RequestBody ProfileImageRequest rs) {
        ApiResponse<Boolean> body = new ApiResponse<>();
        try {

            String imageDataBytes = rs.getImage().substring(rs.getImage().indexOf(",") + 1);
            byte[] imageByte = Base64.decodeBase64(imageDataBytes);
            String filename = rs.getUsername() + generateRandomString(10) + ".png";

            userService.saveProfileImage(rs.getUsername(), filename);

            String path = "D:\\3CINFOGL\\PI\\BE_BienEtreAuTravail\\src\\main\\webapp\\WEB-INF\\images\\" + filename;
            FileOutputStream outputStream = new FileOutputStream(path);
            outputStream.write(imageByte);
            outputStream.flush();
            outputStream.close();

            body.setData(true);
            body.setMessage("save complete");
            body.setStatus(ResponseStatus.DONE);
        } catch (Exception e) {
            log.error(e.getMessage());
            body.setData(false);
            body.setMessage(e.getMessage());
            body.setStatus(ResponseStatus.ERROR);
        }

        return body;
    }

    @GetMapping("/fetchprofileimage/{username}")
    @ResponseBody
    public ApiResponse<String> fetchProfileImage(@PathVariable("username") String username, HttpServletRequest request) {
        ApiResponse<String> body = new ApiResponse<>();
        try {

            String image = getSiteURL(request) + "/images/" + userService.fetchProfileImage(username);
            body.setData(image);
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

    private String generateRandomString(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }

    @PostMapping("/alterpassword")
    @ResponseBody
    public ApiResponse<Boolean> AlterPassword(@RequestBody AlterPasswordRequest rs) {
        ApiResponse<Boolean> body = new ApiResponse<>();
        try {
            body.setData(userService.AlterPassword(rs.getUsername(), rs.getNewpassword(), rs.getPassword()));
            body.setMessage("alter complete");
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

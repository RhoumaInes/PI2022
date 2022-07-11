package tn.esprit.asi.control;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.asi.Utils.BodyResponse;
import tn.esprit.asi.Utils.ResponseStatus;
import tn.esprit.asi.entities.User;
import tn.esprit.asi.services.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    @ResponseBody
    public BodyResponse<Boolean> createUser(@RequestBody User user, HttpServletRequest request) {
        BodyResponse<Boolean> body = new BodyResponse<>();
        try {
            body.setData(userService.CreateUser(user, getSiteURL(request)));
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
}

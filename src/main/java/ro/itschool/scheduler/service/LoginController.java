package ro.itschool.scheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.itschool.scheduler.rest.model.LoginModel;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String getUser(@RequestBody LoginModel user) {
        if (userService.findUserByUserNameAndPassword(user.getUserName(), user.getPassword()))
            return "AUTHENTICATED!";
        else
            return "NOT AUTHENTICATED!";
    }
}

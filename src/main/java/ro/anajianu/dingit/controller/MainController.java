package ro.anajianu.dingit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.anajianu.dingit.model.User;
import ro.anajianu.dingit.repository.UserRepository;

/**
 * Created by ana on 23/05/2017.
 */
@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/")
    public String showWelcomePage(){
        return "start/start";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String addNewUser(@RequestParam String username, @RequestParam String city) {

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setCity(city);

        userRepository.save(newUser);

        return "Saved";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String performLoginUser(@RequestParam(value = "username") String username,
                                   @RequestParam(value = "password") String password) {
        return "done";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testMethod () {
        return "Test";
    }
}

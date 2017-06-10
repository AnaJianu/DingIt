package ro.anajianu.dingit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.anajianu.dingit.model.User;
import ro.anajianu.dingit.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

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
    public String addNewUser(@RequestParam String fullName,
                             @RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String email,
                             @RequestParam String city,
                             @RequestParam String country) {


        Assert.notNull(username, "You must enter your username");
        Assert.notNull(password, "You must introduce your password");

        User newUser = new User();

        User existingUserByUsername = userRepository.findByUsername(username);
//        maybe searching by password is pointless
        User existingUserByPassword = userRepository.findByPassword(password);

        Map<String, Object> registrationResponse = new HashMap<>();

        if (existingUserByUsername == null && existingUserByPassword == null){

            newUser.setFullName(fullName);
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setEmail(email);
            newUser.setCity(city);
            newUser.setCountry(country);

            registrationResponse.put("success", true);
            registrationResponse.put("message", "Your registration has been successful!");
            userRepository.save(newUser);
        } else {
            registrationResponse.put("success", false);
            registrationResponse.put("message", "User already exists!");
        }


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

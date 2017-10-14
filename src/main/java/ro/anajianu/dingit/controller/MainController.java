package ro.anajianu.dingit.controller;

import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import ro.anajianu.dingit.model.User;
import ro.anajianu.dingit.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public Map<String, Object> addNewUser(@RequestParam("fullName") String fullName,
                                          @RequestParam("username") String username,
                                          @RequestParam("password") String password,
                                          @RequestParam("email") String email,
                                          @RequestParam("city") String city,
                                          @RequestParam("country") String country) {
        User newUser = new User();

        User existingUserByUsername = userRepository.findByUsername(username);
        User existingUserByEmail = userRepository.findByEmail(email);

        Map<String, Object> registrationResponse = new HashMap<>();

        if (existingUserByUsername == null && existingUserByEmail == null){

            newUser.setFullName(fullName);
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setEmail(email);
            newUser.setCity(city);
            newUser.setCountry(country);

            registrationResponse.put("success", true);
            registrationResponse.put("message", "Your registration has been successful!" +
                    " Please press CONTINUE to go to the first page!");
            userRepository.save(newUser);
            return registrationResponse;

        } else if (existingUserByUsername == null && existingUserByEmail != null){
            registrationResponse.put("success", false);
            registrationResponse.put("message", "This email already exists!");
            return registrationResponse;
        } else if (existingUserByUsername != null && existingUserByEmail == null) {
            registrationResponse.put("success", false);
            registrationResponse.put("message", "This username already exists!");
            return registrationResponse;
        }

            registrationResponse.put("success", false);
            registrationResponse.put("message", "You are already registered. " +
                    "Please sign in with your username and password!");

        return registrationResponse;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> performLoginUser(@RequestParam(value = "username") String username,
                                   @RequestParam(value = "password") String password) {

        User existingUserByUsername = userRepository.findByUsername(username);

        Map<String, Object> loginResponse = new HashMap<>();

        boolean success = false;
        if (existingUserByUsername != null && existingUserByUsername.getPassword().equals(password)) {
            success = true;
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("currentUser", existingUserByUsername);
        }

        loginResponse.put("success", success);
        return loginResponse;
    }

    @RequestMapping(value = "/logout")
    public String logoutUser() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.invalidate();

        return "redirect:/";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testMethod () {
        return "Test";
    }
}

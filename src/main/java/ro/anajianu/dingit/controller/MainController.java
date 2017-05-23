package ro.anajianu.dingit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.anajianu.dingit.model.User;
import ro.anajianu.dingit.repository.UserRepository;

/**
 * Created by ana on 23/05/2017.
 */
@Controller
@RequestMapping(path = "/dingit")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String addNewUser(@RequestParam String username, @RequestParam String city) {

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setCity(city);

        userRepository.save(newUser);

        return "Saved";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testMethod () {
        return "Test";
    }
}

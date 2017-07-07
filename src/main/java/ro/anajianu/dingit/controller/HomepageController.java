package ro.anajianu.dingit.controller;

import org.hibernate.ObjectDeletedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.anajianu.dingit.model.User;
import ro.anajianu.dingit.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ana on 25/06/2017.
 */

@Controller
@RequestMapping(value = "/home")
public class HomepageController {
    private static final Logger logger = LoggerFactory.getLogger(HomepageController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/")
    public String showHomepage() {
        return "homepage/homepage";
    }

    @RequestMapping(value = "/user/questions")
    @ResponseBody
    public Map<String, Object> getAllQuestionsForCurrentUser () {
        Map<String, Object> result = new HashMap<>();
        return result;
    }

}

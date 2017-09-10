package ro.anajianu.dingit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import ro.anajianu.dingit.model.Advice;
import ro.anajianu.dingit.model.Question;
import ro.anajianu.dingit.model.User;
import ro.anajianu.dingit.repository.AdviceRepository;
import ro.anajianu.dingit.repository.QuestionRepository;
import ro.anajianu.dingit.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AdviceRepository adviceRepository;

    @RequestMapping(value = "")
    public String showHomepage(Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser != null && currentUser.getId() != null) {
            List<Question> allQuestionsForCurrentUser = questionRepository.findByUserId(currentUser.getId());
            List<Advice> allAdvicesForCurrentUser = adviceRepository.findAllByUserId(currentUser.getId());
//        ModelAndView modelAndView = new ModelAndView();
            model.addAttribute("userQuestions", allQuestionsForCurrentUser);
            model.addAttribute("userAdvices", allAdvicesForCurrentUser);
            model.addAttribute("currentUserFullName", currentUser.getFullName());
        }

        return "homepage/homepage";
    }

//    @RequestMapping(value = "/my/questions")
//    public String getAllQuestionsForCurrentUser () {
//
//        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        HttpSession session = attr.getRequest().getSession(true);
//        User currentUser = (User) session.getAttribute("currentUser");
//
//        List<Question> allQuestionsForCurrentUser = questionRepository.findByUserId(currentUser.getId());
//
////        TODO: build a dynamic table
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("userQuestions", allQuestionsForCurrentUser);
//        return "user/questions";
//    }
//
//    @RequestMapping(value = "/my/advices")
//    @ResponseBody
//    public  Map<String, Object> getAllAnswersForCurrentUser () {
//        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        HttpSession session = attr.getRequest().getSession(true);
//        User currentUser = (User) session.getAttribute("currentUser");
//        List<Advice> allAdvicesForCurrentUser = adviceRepository.findAllByUserId(currentUser.getId());
//
//        Map<String, Object> result = new HashMap<>();
//        return result;
//    }

}

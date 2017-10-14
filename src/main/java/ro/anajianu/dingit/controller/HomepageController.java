package ro.anajianu.dingit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        User currentUser = getUserFromSession();

        if (currentUser != null && currentUser.getId() != null) {
            List<Question> allQuestionsForCurrentUser = questionRepository.findByUserId(currentUser.getId());
            List<Advice> allAdvicesForCurrentUser = adviceRepository.findAllByUserId(currentUser.getId());
            model.addAttribute("userQuestions", allQuestionsForCurrentUser);
            model.addAttribute("userAdvices", allAdvicesForCurrentUser);
            model.addAttribute("currentUserFullName", currentUser.getFullName());
        }

        return "homepage/homepage";
    }

    @RequestMapping(value = "/addQuestion")
    public String addQuestionForCurrentUser (Model model) {
        User currentUser = getUserFromSession();
        model.addAttribute("currentUserFullName", currentUser.getFullName());
        model.addAttribute("questionToAdd", new Question());

        return "addQuestion/addQuestion";
    }

    @RequestMapping(value = "/saveQuestion", method = RequestMethod.POST)
    public String saveQuestionForCurrentUser(Question question) {
        User currentUser = getUserFromSession();
        question.setUser(currentUser);

        questionRepository.save(question);

        return "redirect:/home";
    }

    private User getUserFromSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        return (User) session.getAttribute("currentUser");
    }
}

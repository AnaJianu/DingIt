package ro.anajianu.dingit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ro.anajianu.dingit.model.Advice;
import ro.anajianu.dingit.model.Question;
import ro.anajianu.dingit.model.User;
import ro.anajianu.dingit.repository.AdviceRepository;
import ro.anajianu.dingit.repository.QuestionRepository;
import ro.anajianu.dingit.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by ana on 10/09/2017.
 */
@Controller
@RequestMapping(value = "/allQuestions")
public class AllQuestionsController {

    private static final Logger logger = LoggerFactory.getLogger(HomepageController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AdviceRepository adviceRepository;

    @RequestMapping(value = "")
    public String showAllQuestionsPage(Model model) {
        User currentUser = getUserFromSession();
        model.addAttribute("currentUserFullName", currentUser.getFullName());

        Iterable<Question> allQuestions = questionRepository.findAll();
        model.addAttribute("allQuestions", allQuestions);
        return "allQuestions/allQuestions";
    }

    @RequestMapping(value = "/showOneQuestion/{questionId}")
    public String showOneQuestionPage(@PathVariable Long questionId, Model model) {
        User currentUser = getUserFromSession();
        model.addAttribute("currentUserFullName", currentUser.getFullName());

        Question questionById = questionRepository.findQuestionById(questionId);
        model.addAttribute("questionText", questionById.getQuestionText());

        List<Advice> allAdvicesByQuestionId = adviceRepository.findAllByQuestionId(questionId);
        model.addAttribute("advicesForCurrentQuestion", allAdvicesByQuestionId);

        return "entireQuestionPage/entireQuestionPage";
    }

    private User getUserFromSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        return (User) session.getAttribute("currentUser");
    }
}

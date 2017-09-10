package ro.anajianu.dingit.repository;

import org.springframework.data.repository.CrudRepository;
import ro.anajianu.dingit.model.Question;

import java.util.List;

/**
 * Created by ana on 13/08/2017.
 */
public interface QuestionRepository extends CrudRepository<Question, Long> {

    List<Question> findByUserId(Long userId);

    Question findQuestionById(Long questionId);
}

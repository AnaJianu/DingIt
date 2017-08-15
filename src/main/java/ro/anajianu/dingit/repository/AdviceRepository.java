package ro.anajianu.dingit.repository;

import org.springframework.data.repository.CrudRepository;
import ro.anajianu.dingit.model.Advice;

import java.util.List;

/**
 * Created by ana on 15/08/2017.
 */
public interface AdviceRepository extends CrudRepository<Advice, Long> {

    List<Advice> findAllByUserId(Long userId);
}

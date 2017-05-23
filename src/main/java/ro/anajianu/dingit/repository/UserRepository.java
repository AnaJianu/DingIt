package ro.anajianu.dingit.repository;

import org.springframework.data.repository.CrudRepository;
import ro.anajianu.dingit.model.User;

/**
 * Created by ana on 23/05/2017.
 */
public interface UserRepository extends CrudRepository<User, Long>{

}

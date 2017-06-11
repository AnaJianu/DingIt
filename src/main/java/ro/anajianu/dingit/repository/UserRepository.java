package ro.anajianu.dingit.repository;

import org.springframework.data.repository.CrudRepository;
import ro.anajianu.dingit.model.User;

import java.util.Optional;

/**
 * Created by ana on 23/05/2017.
 */
public interface UserRepository extends CrudRepository<User, Long>{

    User findByUsername(String username);

    User findByPassword(String password);

    User findByEmail(String email);
}

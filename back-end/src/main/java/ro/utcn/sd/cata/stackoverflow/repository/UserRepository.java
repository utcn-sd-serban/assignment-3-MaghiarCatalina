package ro.utcn.sd.cata.stackoverflow.repository;

import ro.utcn.sd.cata.stackoverflow.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    List<User> findAll();

    void remove(User user);

    Optional<User> findById(Integer id);

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

}

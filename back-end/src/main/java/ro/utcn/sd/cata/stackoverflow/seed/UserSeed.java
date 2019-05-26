package ro.utcn.sd.cata.stackoverflow.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.cata.stackoverflow.entity.User;
import ro.utcn.sd.cata.stackoverflow.repository.RepositoryFactory;
import ro.utcn.sd.cata.stackoverflow.repository.UserRepository;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserSeed implements CommandLineRunner {
    private final RepositoryFactory repositoryFactory;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        UserRepository userRepository = repositoryFactory.createUserRepository();
        if(userRepository.findAll().isEmpty()){
            userRepository.save(new User(null,"Cata",passwordEncoder.encode("1234")));
        }
    }
}

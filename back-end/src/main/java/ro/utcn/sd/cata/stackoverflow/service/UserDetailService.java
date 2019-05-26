package ro.utcn.sd.cata.stackoverflow.service;

import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.cata.stackoverflow.exception.UserNotFoundException;
import ro.utcn.sd.cata.stackoverflow.repository.RepositoryFactory;
import ro.utcn.sd.cata.stackoverflow.repository.UserRepository;

import java.util.Collections;

@Service
@Data
public class UserDetailService implements UserDetailsService {
    private final RepositoryFactory factory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ro.utcn.sd.cata.stackoverflow.entity.User user = factory.createUserRepository().findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        return new User(user.getUsername(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Transactional
    public ro.utcn.sd.cata.stackoverflow.entity.User loadCurrentUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return factory.createUserRepository().findByUsername(name).orElseThrow(UserNotFoundException::new);
    }
}

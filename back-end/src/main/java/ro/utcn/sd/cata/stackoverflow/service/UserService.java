package ro.utcn.sd.cata.stackoverflow.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.cata.stackoverflow.dto.UserDTO;
import ro.utcn.sd.cata.stackoverflow.entity.User;
import ro.utcn.sd.cata.stackoverflow.exception.UserNotFoundException;
import ro.utcn.sd.cata.stackoverflow.repository.RepositoryFactory;


@Service
@RequiredArgsConstructor
public class UserService {
    private final RepositoryFactory repositoryFactory;
    private final ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(ModelMapper modelMapper, RepositoryFactory repositoryFactory) {
        this.modelMapper = modelMapper;
        this.repositoryFactory = repositoryFactory;
    }

    @Transactional
    public UserDTO addUser(UserDTO userDTO) {
        User newUser = new User();
        UserDTO addedUserDTO = new UserDTO();
        newUser.setId(null);
        modelMapper.map(userDTO, newUser);
        User addedUser = repositoryFactory.createUserRepository().save(newUser);
        modelMapper.map(addedUser, addedUserDTO);
        return addedUserDTO;
    }

    @Transactional
    public void removeUser(Integer id) {
        repositoryFactory.createUserRepository().remove(repositoryFactory.createUserRepository()
                .findById(id).orElseThrow(UserNotFoundException::new));
    }

    @Transactional
    public UserDTO findUserByUsernamePassword(UserDTO userDTO) {

        User foundUser = repositoryFactory.createUserRepository()
                .findByUsername(userDTO.getUsername())
                .orElseThrow(UserNotFoundException::new);
        if(passwordEncoder.matches(foundUser.getPassword(),userDTO.getPassword())) {
            UserDTO foundUserDTO = new UserDTO();
            modelMapper.map(foundUser, foundUserDTO);
            return foundUserDTO;
        }
        else throw new UserNotFoundException();

    }

    @Transactional
    public User findUserByUsername(String username) {
        User foundUser;
        foundUser = repositoryFactory.createUserRepository().findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        return foundUser;
    }

}

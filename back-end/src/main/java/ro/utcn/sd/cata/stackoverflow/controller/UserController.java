package ro.utcn.sd.cata.stackoverflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.utcn.sd.cata.stackoverflow.dto.UserDTO;
import ro.utcn.sd.cata.stackoverflow.entity.User;
import ro.utcn.sd.cata.stackoverflow.service.UserDetailService;
import ro.utcn.sd.cata.stackoverflow.service.UserService;

@RestController
public class UserController {
    private final UserService userService;
    private final UserDetailService userDetailService;

    @Autowired
    public UserController(UserService userService, UserDetailService userDetailService) {
        this.userService = userService;
        this.userDetailService = userDetailService;
    }

//    @PostMapping("/users")
//    public UserDTO addUser(@RequestBody UserDTO user) {
//        return userService.addUser(user);
//    }

    @GetMapping("/")
    public User readCurrent() {
        return userDetailService.loadCurrentUser();
    }

    @PostMapping("/")
    public UserDTO login(@RequestBody UserDTO userDTO){
        return userService.findUserByUsernamePassword(userDTO);
    }
}

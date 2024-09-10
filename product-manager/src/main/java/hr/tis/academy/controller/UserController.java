package hr.tis.academy.controller;
import hr.tis.academy.dto.UserDto;
import hr.tis.academy.mapper.UserMapper;
import hr.tis.academy.model.User;
import hr.tis.academy.repository.UserRepository;
import hr.tis.academy.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserMapper userMapper;

    private final UserService userService;

    private final UserRepository userRepository;

    public UserController(UserService userService, UserMapper userMapper, UserRepository userRepository) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<Long> postUser(@RequestBody UserDto userDto){
        userService.addUser(userDto);
        User user = userRepository.findByEmail(userDto.email());
        return new ResponseEntity<>(user.getUserId(), HttpStatus.CREATED);
    }


}

package hr.tis.academy.service.impl;

import hr.tis.academy.dto.UserDto;
import hr.tis.academy.mapper.UserMapper;
import hr.tis.academy.repository.UserRepository;
import hr.tis.academy.repository.exception.UserAlreadyExistsException;
import hr.tis.academy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void addUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.email()) != null) {
            throw new UserAlreadyExistsException("Vec postoji korisnik s tim emailom");
        } else if (!validateEmail(userDto.email())) {
            throw new UserAlreadyExistsException("Neispravan email");
        }
        userRepository.save(userMapper.toUserEntity(userDto));
    }

    private boolean validateEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

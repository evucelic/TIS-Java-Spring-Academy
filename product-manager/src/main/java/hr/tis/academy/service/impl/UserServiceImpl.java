package hr.tis.academy.service.impl;

import hr.tis.academy.model.User;
import hr.tis.academy.repository.UserRepository;
import hr.tis.academy.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }
}

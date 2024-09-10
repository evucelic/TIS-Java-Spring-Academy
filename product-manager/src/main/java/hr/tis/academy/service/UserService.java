package hr.tis.academy.service;

import hr.tis.academy.dto.UserDto;
import hr.tis.academy.model.User;


public interface UserService {

    void addUser(UserDto userDto);
}

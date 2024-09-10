package hr.tis.academy.service.impl;

import hr.tis.academy.dto.FavoritesResponse;
import hr.tis.academy.dto.UserDto;
import hr.tis.academy.mapper.UserMapper;
import hr.tis.academy.model.Attraction;
import hr.tis.academy.repository.AttractionRepository;
import hr.tis.academy.repository.UserRepository;
import hr.tis.academy.repository.exception.AttractionNotFoundException;
import hr.tis.academy.repository.exception.UserAlreadyExistsException;
import hr.tis.academy.repository.exception.UserNotFoundException;
import hr.tis.academy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AttractionRepository attractionRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AttractionRepository attractionRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.attractionRepository = attractionRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void addUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.email()) != null) {
            throw new UserAlreadyExistsException("User with that email already exists");
        } else if (!validateEmail(userDto.email())) {
            throw new UserAlreadyExistsException("Invalid email format");
        }
        userRepository.save(userMapper.toUserEntity(userDto));
    }

    @Override
    public void addFavorite(FavoritesResponse favoritesResponse, Long userId) {
        Attraction existingAttraction = attractionRepository.findByAttractionName(favoritesResponse.attractionName());
        if (userRepository.findByUserId(userId) == null) {
            throw new UserNotFoundException("User with that ID does not exist");
        } else if (existingAttraction == null) {
            throw new AttractionNotFoundException("Attraction with that name does not exist");
        }

        //userRepository.findByUserId(userId).addToList(attractionRepository.findByAttractionName(favoritesResponse.attractionName()));
        userRepository.insertUserFavourites(existingAttraction.getAttractionId(), userId);
    }

    private boolean validateEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

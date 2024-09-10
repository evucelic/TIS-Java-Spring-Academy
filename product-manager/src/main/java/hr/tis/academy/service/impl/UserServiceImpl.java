package hr.tis.academy.service.impl;

import hr.tis.academy.dto.FavoritesResponse;
import hr.tis.academy.dto.UserDto;
import hr.tis.academy.mapper.UserMapper;
import hr.tis.academy.model.Attraction;
import hr.tis.academy.model.User;
import hr.tis.academy.repository.AttractionRepository;
import hr.tis.academy.repository.UserRepository;
import hr.tis.academy.repository.exception.AttractionNotFoundException;
import hr.tis.academy.repository.exception.FavoriteAlreadyExistsException;
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
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " does not exist"));

        Attraction attraction = attractionRepository.findByAttractionName(favoritesResponse.attractionName());
        if (attraction == null) {
            throw new AttractionNotFoundException("Attraction with name " + favoritesResponse.attractionName() + " does not exist");
        }

        if (user.getAttractionsUser().contains(attraction)) {
            throw new FavoriteAlreadyExistsException();
        }

        user.getAttractionsUser().add(attraction);

        attraction.getFavoritedByUsers().add(user);

        // Posto je many to many, u listu od Attraction spremamo usera koji je favoriteao, u listu od User spremamo attraction koji su favoriteali
        // TLDR dvostrana veza
        userRepository.save(user);
        attractionRepository.save(attraction);
    }

    private boolean validateEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

package hr.tis.academy.service;

import hr.tis.academy.dto.FavoritesResponse;
import hr.tis.academy.dto.UserDto;


public interface UserService {

    void addUser(UserDto userDto);

    void addFavorite(FavoritesResponse favoritesResponse, Long userId);
}

package hr.tis.academy.service;

import hr.tis.academy.dto.FavoritesResponse;
import hr.tis.academy.dto.ListFavoritesResponse;
import hr.tis.academy.dto.UserDto;
import hr.tis.academy.model.User;


public interface UserService {

    void addUser(UserDto userDto);

    void addFavorite(FavoritesResponse favoritesResponse, Long userId);

    UserDto getUser(Long user_id);

    ListFavoritesResponse getFavorites(Long user_id);
}
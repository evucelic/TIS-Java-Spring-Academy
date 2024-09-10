package hr.tis.academy.repository;

import hr.tis.academy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByNameAndEmail(String name, String email);

    User findByEmail(String email);

    User findByUserId(Long userId);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO SIGHTSEEING.USER_FAVORITES (ATTRACTION_ID, USER_ID) VALUES (?, ?)")
    void insertUserFavourites(Long attractionId, Long userId);
}

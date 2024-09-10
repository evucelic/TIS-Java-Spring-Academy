package hr.tis.academy.repository;

import hr.tis.academy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByNameAndEmail(String name, String email);
}

package mycode.flashwork2.users.repository;

import mycode.flashwork2.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    List<User> findAll();


    Optional<User>findByEmail(String email);


    boolean existsByEmail(String email);
}

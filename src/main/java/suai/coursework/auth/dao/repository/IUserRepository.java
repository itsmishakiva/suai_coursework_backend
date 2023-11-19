package suai.coursework.auth.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suai.coursework.auth.domain.models.users.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
    List<User> findAllBy();

    Optional<User> findByUsername(String username);
}

package suai.coursework.auth.dao.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import suai.coursework.auth.domain.models.AuthUser;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<AuthUser, Integer> {

    Boolean existsByUsername(String username);

    Optional<AuthUser> findByUsername(String username);

}



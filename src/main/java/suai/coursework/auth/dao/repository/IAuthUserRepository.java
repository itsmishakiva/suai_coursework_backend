package suai.coursework.auth.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suai.coursework.auth.domain.models.auth.AuthUser;

import java.util.Optional;

public interface IAuthUserRepository extends JpaRepository<AuthUser, Integer> {

    Boolean existsByUsername(String username);

    Optional<AuthUser> findByUsername(String username);

}



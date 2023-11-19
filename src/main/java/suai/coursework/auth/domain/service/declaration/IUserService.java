package suai.coursework.auth.domain.service.declaration;

import org.springframework.http.ResponseEntity;

public interface IUserService  {
    ResponseEntity<?> getUserById(Integer id);

    ResponseEntity<?> get();

    ResponseEntity<?> getUserByUsername(String username);
}

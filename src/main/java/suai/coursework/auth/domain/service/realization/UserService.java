package suai.coursework.auth.domain.service.realization;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import suai.coursework.auth.dao.repository.IUserRepository;
import suai.coursework.auth.domain.models.users.User;
import suai.coursework.auth.domain.service.declaration.IUserService;
import suai.coursework.auth.security.JwtUtilities;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final JwtUtilities jwtUtilities;

    @Override
    public ResponseEntity<?> getMyself(String authHeader) {
        String token = jwtUtilities.getTokenFromHeader(authHeader);
        String username = jwtUtilities.extractUsername(token);
        return getUserByUsername(username);
    }

    @Override
    public ResponseEntity<?> getUserById(Integer id) {
        if (id == null) return new ResponseEntity<>("ID should not be null", HttpStatus.SEE_OTHER);
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.SEE_OTHER);
        }
        User user = optional.get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUserByUsername(String username) {
        if (username == null) return new ResponseEntity<>("Username should not be null", HttpStatus.SEE_OTHER);
        Optional<User> optional = userRepository.findByUsername(username);
        if (optional.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.SEE_OTHER);
        }
        User user = optional.get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> get() {
        List<User> users = userRepository.findAllBy();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}

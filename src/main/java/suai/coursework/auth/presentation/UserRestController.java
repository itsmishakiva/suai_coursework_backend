package suai.coursework.auth.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suai.coursework.auth.dao.dto.auth.LoginDto;
import suai.coursework.auth.dao.dto.auth.RegisterDto;
import suai.coursework.auth.domain.service.declaration.IAuthUserService;
import suai.coursework.auth.domain.service.declaration.IUserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping()
    public ResponseEntity<?> get() {
        return userService.get();
    }

    @GetMapping("/u/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
}

package suai.coursework.auth.presentation;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suai.coursework.auth.domain.service.declaration.IAuthUserService;
import suai.coursework.auth.dao.dto.auth.LoginDto;
import suai.coursework.auth.dao.dto.auth.RegisterDto;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthUserRestController {


    private final IAuthUserService authUserService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody RegisterDto registerDto) {
        return authUserService.signup(registerDto);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody LoginDto loginDto) {
        return authUserService.auth(loginDto);
    }
}

package suai.coursework.auth.presentation;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suai.coursework.auth.domain.service.IAuthUserService;
import suai.coursework.auth.dao.dto.LoginDto;
import suai.coursework.auth.dao.dto.RegisterDto;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthUserRestController {


    private final IAuthUserService iAuthUserService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody RegisterDto registerDto) {
        return iAuthUserService.signup(registerDto);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody LoginDto loginDto) {
        return iAuthUserService.auth(loginDto);
    }
}

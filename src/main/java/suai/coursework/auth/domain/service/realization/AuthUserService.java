package suai.coursework.auth.domain.service.realization;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import suai.coursework.auth.config.RandomColorGenerator;
import suai.coursework.auth.dao.dto.auth.BearerToken;
import suai.coursework.auth.dao.dto.auth.LoginDto;
import suai.coursework.auth.dao.dto.auth.RegisterDto;
import suai.coursework.auth.dao.repository.IUserRepository;
import suai.coursework.auth.domain.models.auth.AuthUser;
import suai.coursework.auth.domain.models.auth.Role;
import suai.coursework.auth.domain.models.auth.RoleName;
import suai.coursework.auth.dao.repository.IRoleRepository;
import suai.coursework.auth.dao.repository.IAuthUserRepository;
import suai.coursework.auth.domain.models.users.User;
import suai.coursework.auth.domain.service.declaration.IAuthUserService;
import suai.coursework.auth.security.JwtUtilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class AuthUserService implements IAuthUserService {

    private final AuthenticationManager authenticationManager;
    private final IAuthUserRepository authUserRepository;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilities jwtUtilities;


    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public ResponseEntity<?> signup(RegisterDto registerDto) {
        if (authUserRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is already taken !", HttpStatus.SEE_OTHER);
        } else {
            AuthUser authUser = new AuthUser();
            authUser.setUsername(registerDto.getUsername());
            authUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            Role role = roleRepository.findByRoleName(RoleName.USER);
            authUser.setRoles(Collections.singletonList(role));
            authUserRepository.save(authUser);
            User user = new User();
            user.setUsername(registerDto.getUsername());
            user.setAvatarColor(RandomColorGenerator.generateColor(0x49));
            userRepository.save(user);
            String token = jwtUtilities.generateToken(registerDto.getUsername(), Collections.singletonList(role.getRoleName()));
            return new ResponseEntity<>(new BearerToken(token, "Bearer "), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> auth(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthUser authUser = authUserRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<String> rolesNames = new ArrayList<>();
        authUser.getRoles().forEach(r -> rolesNames.add(r.getRoleName()));
        String token = jwtUtilities.generateToken(authUser.getUsername(), rolesNames);;
        return new ResponseEntity<>(new BearerToken(token, "Bearer "), HttpStatus.OK);
    }

}


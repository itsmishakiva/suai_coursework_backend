package suai.coursework.auth.domain.service;

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
import suai.coursework.auth.dao.dto.BearerToken;
import suai.coursework.auth.dao.dto.LoginDto;
import suai.coursework.auth.dao.dto.RegisterDto;
import suai.coursework.auth.domain.models.AuthUser;
import suai.coursework.auth.domain.models.Role;
import suai.coursework.auth.domain.models.RoleName;
import suai.coursework.auth.dao.persistence.IRoleRepository;
import suai.coursework.auth.dao.persistence.IUserRepository;
import suai.coursework.auth.security.JwtUtilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class AuthUserService implements IAuthUserService {

    private final AuthenticationManager authenticationManager;
    private final IUserRepository iUserRepository;
    private final IRoleRepository iRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilities jwtUtilities;


    @Override
    public void saveRole(Role role) {
        iRoleRepository.save(role);
    }

    @Override
    public ResponseEntity<?> signup(RegisterDto registerDto) {
        if (iUserRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is already taken !", HttpStatus.SEE_OTHER);
        } else {
            AuthUser authUser = new AuthUser();
            authUser.setUsername(registerDto.getUsername());
            authUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            Role role = iRoleRepository.findByRoleName(RoleName.USER);
            authUser.setRoles(Collections.singletonList(role));
            iUserRepository.save(authUser);
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
        AuthUser authUser = iUserRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<String> rolesNames = new ArrayList<>();
        authUser.getRoles().forEach(r -> rolesNames.add(r.getRoleName()));
        String token = jwtUtilities.generateToken(authUser.getUsername(), rolesNames);;
        return new ResponseEntity<>(new BearerToken(token, "Bearer "), HttpStatus.OK);
    }

}


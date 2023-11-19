package suai.coursework.auth.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import suai.coursework.auth.dao.repository.IAuthUserRepository;


@Component
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final IAuthUserRepository iAuthUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return iAuthUserRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found !"));
    }
}

package suai.coursework.auth.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import suai.coursework.auth.dao.persistence.IUserRepository;


@Component
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final IUserRepository iUserRepository ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return iUserRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found !"));
    }
}

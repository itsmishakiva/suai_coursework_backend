package suai.coursework.auth.domain.service.declaration;


import org.springframework.http.ResponseEntity;
import suai.coursework.auth.dao.dto.auth.LoginDto;
import suai.coursework.auth.dao.dto.auth.RegisterDto;
import suai.coursework.auth.domain.models.auth.Role;


public interface IAuthUserService {

   ResponseEntity<?> auth(LoginDto loginDto);
   ResponseEntity<?> signup(RegisterDto registerDto);
   void saveRole(Role role);
}

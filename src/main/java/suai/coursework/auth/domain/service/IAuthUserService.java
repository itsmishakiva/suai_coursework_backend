package suai.coursework.auth.domain.service;


import org.springframework.http.ResponseEntity;
import suai.coursework.auth.dao.dto.LoginDto;
import suai.coursework.auth.dao.dto.RegisterDto;
import suai.coursework.auth.domain.models.Role;


public interface IAuthUserService {

   ResponseEntity<?> auth(LoginDto loginDto);
   ResponseEntity<?> signup(RegisterDto registerDto);
   void saveRole(Role role);
}

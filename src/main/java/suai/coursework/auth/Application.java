package suai.coursework.auth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import suai.coursework.auth.domain.service.IAuthUserService;
import suai.coursework.auth.domain.models.Role;
import suai.coursework.auth.domain.models.RoleName;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    CommandLineRunner run(IAuthUserService iAuthUserService) {
        return args ->
                iAuthUserService.saveRole(new Role(RoleName.USER));
    }

}


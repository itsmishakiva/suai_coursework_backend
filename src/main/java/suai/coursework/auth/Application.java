package suai.coursework.auth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import suai.coursework.auth.dao.dto.task.TaskGroupDto;
import suai.coursework.auth.dao.dto.task.TaskStatusDto;
import suai.coursework.auth.dao.dto.task.TaskTypeDto;
import suai.coursework.auth.domain.service.declaration.IAuthUserService;
import suai.coursework.auth.domain.models.auth.Role;
import suai.coursework.auth.domain.models.auth.RoleName;
import suai.coursework.auth.domain.service.declaration.ITaskParamsService;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    CommandLineRunner run(IAuthUserService authUserService, ITaskParamsService taskParamsService) {
        return args -> {
            taskParamsService.createStatus(new TaskStatusDto("В работе"));
            taskParamsService.createStatus(new TaskStatusDto("Сделано"));
            taskParamsService.createGroup(new TaskGroupDto("Фронтенд", 0xFFC077D2L));
            taskParamsService.createGroup(new TaskGroupDto("Бэкенд", 0xFF7796D2L));
            taskParamsService.createGroup(new TaskGroupDto("Дизайн", 0xFF77D286L));
            taskParamsService.createType(new TaskTypeDto("Баг", 0xFFCC5050L));
            taskParamsService.createType(new TaskTypeDto("Фича", 0xFFC2CC50L));
            authUserService.saveRole(new Role(RoleName.USER));
        };
    }

}


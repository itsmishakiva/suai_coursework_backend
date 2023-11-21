package suai.coursework.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import suai.coursework.auth.dao.dto.task.TaskDto;
import suai.coursework.auth.dao.repository.ITaskListRepository;
import suai.coursework.auth.dao.repository.IUserRepository;
import suai.coursework.auth.domain.models.users.User;
import suai.coursework.auth.domain.service.realization.TaskListService;
import suai.coursework.auth.domain.service.realization.UserService;
import suai.coursework.auth.security.JwtUtilities;

import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableAutoConfiguration
@ContextConfiguration(classes = {IUserRepository.class, ITaskListRepository.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceTests {

    @MockBean
    private JwtUtilities jwtUtilities;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ITaskListRepository taskListRepository;

    @Test
    public void userServiceTest() throws Exception {
        userRepository.save(new User(1, "Admin", 0xFF123445L));
        UserService userService = new UserService(userRepository, jwtUtilities);
        var result = userService.getUserById(1);
        assert ((User) result.getBody()).getUsername().equals("Admin");
    }

    @Test
    public void taskListServiceTest() throws Exception {
        TaskListService taskListService = new TaskListService(taskListRepository, userRepository);
        taskListService.post(new TaskDto("title", null, 1, null, null, null));
        var result = taskListService.get();
        assert ((List)((HashMap) result.getBody()).get("tasks")).size() == 1;
    }

}
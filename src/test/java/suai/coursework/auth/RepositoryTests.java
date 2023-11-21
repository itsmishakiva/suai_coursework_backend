package suai.coursework.auth;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import suai.coursework.auth.dao.repository.ITaskListRepository;
import suai.coursework.auth.dao.repository.IUserRepository;
import suai.coursework.auth.domain.models.task.Task;
import suai.coursework.auth.domain.models.users.User;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableAutoConfiguration
@ContextConfiguration(classes = {IUserRepository.class, ITaskListRepository.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTests {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ITaskListRepository taskListRepository;

    @Test
    public void userRepoSaveAndUsernameTest() throws Exception {
        this.userRepository.save(new User(1, "Admin", 0xFF123445L));
        User user = this.userRepository.findByUsername("Admin").get();
        assert (user.getUsername()).equals("Admin");
    }

    @Test
    public void userRepoSaveAndIdTest() throws Exception {
        this.userRepository.save(new User(1, "Admin", 0xFF123445L));
        User user1 = this.userRepository.findById(1).get();
        assert (user1.getUsername()).equals("Admin");
    }

    @Test
    public void userRepoSaveAndDeleteTest() throws Exception {
        this.userRepository.save(new User(1, "Admin", 0xFF123445L));
        this.userRepository.deleteById(1);
        Optional<User> user1 = this.userRepository.findById(1);
        assert (user1.isEmpty());
    }

    @Test
    public void taskListSaveAndIdTest() throws Exception {
        this.taskListRepository.save(new Task(1, "Admin", 1));
        Task task = this.taskListRepository.findById(1).get();
        assert (task.getId()).equals(1);
    }

    public void taskRepoSaveAndDeleteTest() throws Exception {
        this.taskListRepository.save(new Task(1, "Admin", 1));
        this.taskListRepository.deleteById(1);
        Optional<Task> task = this.taskListRepository.findById(1);
        assert (task.isEmpty());
    }

}
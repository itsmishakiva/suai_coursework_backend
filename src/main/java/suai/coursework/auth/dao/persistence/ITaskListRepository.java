package suai.coursework.auth.dao.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import suai.coursework.auth.domain.models.task.Task;

import java.util.List;

public interface ITaskListRepository extends JpaRepository<Task, Integer>  {
    List<Task> findAllBy();

}

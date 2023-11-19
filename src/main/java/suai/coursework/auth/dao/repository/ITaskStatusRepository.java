package suai.coursework.auth.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suai.coursework.auth.domain.models.task.TaskStatus;

import java.util.List;

public interface ITaskStatusRepository extends JpaRepository<TaskStatus, Integer> {
    List<TaskStatus> findAllBy();

    TaskStatus findByLabel(String label);

    boolean existsByLabel(String label);
}

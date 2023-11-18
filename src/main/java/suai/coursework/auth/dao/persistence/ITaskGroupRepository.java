package suai.coursework.auth.dao.persistence;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import suai.coursework.auth.domain.models.task.TaskGroup;
import suai.coursework.auth.domain.models.task.TaskType;

import java.util.List;

public interface ITaskGroupRepository extends JpaRepository<TaskGroup, Integer> {
    List<TaskGroup> findAllBy();

    TaskGroup findByLabel(String label);

    boolean existsByLabel(String label);
}

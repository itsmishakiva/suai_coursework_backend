package suai.coursework.auth.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suai.coursework.auth.domain.models.task.TaskType;

import java.util.List;

public interface ITaskTypeRepository extends JpaRepository<TaskType, Integer> {
    List<TaskType> findAllBy();

    TaskType findByLabel(String label);

    boolean existsByLabel(String label);
}

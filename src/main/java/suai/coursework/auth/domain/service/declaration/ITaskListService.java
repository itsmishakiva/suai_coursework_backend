package suai.coursework.auth.domain.service.declaration;

import org.springframework.http.ResponseEntity;
import suai.coursework.auth.dao.dto.task.TaskDto;

public interface ITaskListService {
    ResponseEntity<?> get();

    ResponseEntity<?> post(TaskDto taskDto);

    ResponseEntity<?> put(Integer id, TaskDto taskDto);

    ResponseEntity<?> delete(Integer id);

    ResponseEntity<?> getById(Integer id);
}

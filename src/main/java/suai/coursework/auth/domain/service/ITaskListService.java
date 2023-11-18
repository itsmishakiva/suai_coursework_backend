package suai.coursework.auth.domain.service;

import org.springframework.http.ResponseEntity;
import suai.coursework.auth.dao.dto.TaskDto;
import suai.coursework.auth.domain.models.task.Task;

import java.util.List;

public interface ITaskListService {
    ResponseEntity<?> get();

    ResponseEntity<?> post(TaskDto taskDto);

    ResponseEntity<?> put(TaskDto taskDto);
}

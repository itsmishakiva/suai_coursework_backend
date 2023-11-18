package suai.coursework.auth.domain.service;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestBody;
import suai.coursework.auth.dao.dto.TaskDto;
import suai.coursework.auth.dao.persistence.ITaskListRepository;
import suai.coursework.auth.domain.models.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskListService implements ITaskListService {

    private final ITaskListRepository taskListRepository;

    @Override
    public ResponseEntity<?> get() {
        List<Task> tasks = taskListRepository.findAllBy();
        HashMap<String, Object> body = new HashMap<>();
        body.put("tasks", tasks);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> post(TaskDto taskDto) {
        Task task = new Task();
        return setTaskData(taskDto, task);
    }

    @Override
    public ResponseEntity<?> put(TaskDto taskDto) {
        if (taskDto.getId() == null) return new ResponseEntity<>("Empty ID parameter", HttpStatus.BAD_REQUEST);
        Optional<Task> optional = taskListRepository.findById(taskDto.getId());
        if (optional.isEmpty()) {
            return new ResponseEntity<>("ID not found", HttpStatus.NO_CONTENT);
        }
        Task task = optional.get();
        task.setId(taskDto.getId());
        return setTaskData(taskDto, task);
    }

    @NonNull
    private ResponseEntity<?> setTaskData(TaskDto taskDto, Task task) {
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setTypeId(taskDto.getTypeId());
        task.setGroupId(taskDto.getGroupId());
        task.setStatusId(taskDto.getStatusId());
        taskListRepository.save(task);
        HashMap<String, Object> body = new HashMap<>();
        body.put("task", task);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}

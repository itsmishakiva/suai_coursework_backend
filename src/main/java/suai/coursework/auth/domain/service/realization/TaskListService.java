package suai.coursework.auth.domain.service.realization;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import suai.coursework.auth.dao.dto.task.TaskDto;
import suai.coursework.auth.dao.dto.task.TaskPresentationDto;
import suai.coursework.auth.dao.repository.ITaskListRepository;
import suai.coursework.auth.dao.repository.IUserRepository;
import suai.coursework.auth.domain.models.task.Task;
import suai.coursework.auth.domain.models.users.User;
import suai.coursework.auth.domain.service.declaration.ITaskListService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskListService implements ITaskListService {

    private final ITaskListRepository taskListRepository;
    private final IUserRepository userRepository;

    @Override
    public ResponseEntity<?> get() {
        List<Task> tasks = taskListRepository.findAllBy();
        List<TaskPresentationDto> taskDtos = new ArrayList<>();
        for (var task : tasks) {
            if (task.getUserId() == null) {
                taskDtos.add(
                        new TaskPresentationDto(task.getId(), task.getTitle(), task.getDescription(), task.getStatusId(), task.getTypeId(), task.getGroupId(), null)
                );
                continue;
            }
            Optional<User> optional = userRepository.findById(task.getUserId());
            User user = null;
            if (optional.isPresent()) user = optional.get();
            taskDtos.add(
                    new TaskPresentationDto(task.getId(), task.getTitle(), task.getDescription(), task.getStatusId(), task.getTypeId(), task.getGroupId(), user)
            );
        }
        HashMap<String, Object> body = new HashMap<>();
        body.put("tasks", taskDtos);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getById(Integer id) {
        Optional<Task> optionalTask = taskListRepository.findById(id);
        if (optionalTask.isEmpty()) return new ResponseEntity<>("Not found", HttpStatus.NO_CONTENT);
        Task task = optionalTask.get();
        User user = null;
        if (task.getUserId() != null) {
            Optional<User> optional = userRepository.findById(task.getUserId());
            if (optional.isPresent()) user = optional.get();
        }
        TaskPresentationDto taskDto = new TaskPresentationDto(task.getId(), task.getTitle(), task.getDescription(), task.getStatusId(), task.getTypeId(), task.getGroupId(), user);
        HashMap<String, Object> body = new HashMap<>();
        body.put("task", task);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> post(TaskDto taskDto) {
        Task task = new Task();
        return setTaskData(taskDto, task);
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        try {
            taskListRepository.deleteById(id);
        } catch (Throwable e) {
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> put(Integer id, TaskDto taskDto) {
        Optional<Task> optional = taskListRepository.findById(id);
        if (optional.isEmpty()) {
            return new ResponseEntity<>("ID not found", HttpStatus.NO_CONTENT);
        }
        Task task = optional.get();
        task.setId(id);
        return setTaskData(taskDto, task);
    }

    @NonNull
    private ResponseEntity<?> setTaskData(TaskDto taskDto, Task task) {
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setTypeId(taskDto.getTypeId());
        task.setGroupId(taskDto.getGroupId());
        task.setStatusId(taskDto.getStatusId());
        task.setUserId(taskDto.getUserId());
        taskListRepository.save(task);
        HashMap<String, Object> body = new HashMap<>();
        body.put("task", task);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}

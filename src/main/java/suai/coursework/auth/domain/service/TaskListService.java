package suai.coursework.auth.domain.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import suai.coursework.auth.dao.persistence.ITaskListRepository;
import suai.coursework.auth.domain.models.task.Task;

import java.util.HashMap;
import java.util.List;

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
}

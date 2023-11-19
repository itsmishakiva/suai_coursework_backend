package suai.coursework.auth.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suai.coursework.auth.dao.dto.task.TaskDto;
import suai.coursework.auth.domain.service.declaration.ITaskListService;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskListRestController {


    private final ITaskListService taskListService;

    @GetMapping
    public ResponseEntity<?> get() {
        return taskListService.get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return taskListService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody TaskDto taskDto) {
        return taskListService.post(taskDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Integer id, @RequestBody TaskDto taskDto) {
        return taskListService.put(id, taskDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return taskListService.delete(id);
    }

}

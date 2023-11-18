package suai.coursework.auth.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suai.coursework.auth.dao.dto.LoginDto;
import suai.coursework.auth.dao.dto.TaskDto;
import suai.coursework.auth.domain.service.ITaskListService;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskListRestController {


    private final ITaskListService iTaskListService;

    @GetMapping
    public ResponseEntity<?> get() {
        return iTaskListService.get();
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody TaskDto taskDto) {
        return iTaskListService.post(taskDto);
    }

    @PutMapping
    public ResponseEntity<?> put(@RequestBody TaskDto taskDto) {
        return iTaskListService.put(taskDto);
    }

}

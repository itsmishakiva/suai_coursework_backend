package suai.coursework.auth.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suai.coursework.auth.dao.dto.TaskGroupDto;
import suai.coursework.auth.dao.dto.TaskStatusDto;
import suai.coursework.auth.dao.dto.TaskTypeDto;
import suai.coursework.auth.domain.service.ITaskParamsService;

@RestController
@RequestMapping("/taskparams")
@RequiredArgsConstructor
public class TaskParamsRestController {
    private final ITaskParamsService taskParamsService;

    @GetMapping("/statuses")
    public ResponseEntity<?> getStatuses() {
        return taskParamsService.getStatuses();
    }

    @GetMapping("/types")
    public ResponseEntity<?> getTypes() {
        return taskParamsService.getTypes();
    }

    @GetMapping("/groups")
    public ResponseEntity<?> getGroups() {
        return taskParamsService.getGroups();
    }

    @PostMapping("/statuses")
    public ResponseEntity<?> createStatus(@RequestBody TaskStatusDto status) {
        return taskParamsService.createStatus(status);
    }

    @PostMapping("/types")
    public ResponseEntity<?> createType(@RequestBody TaskTypeDto type) {
        return taskParamsService.createType(type);
    }

    @PostMapping("/groups")
    public ResponseEntity<?> createGroup(@RequestBody TaskGroupDto group) {
        return taskParamsService.createGroup(group);
    }


}

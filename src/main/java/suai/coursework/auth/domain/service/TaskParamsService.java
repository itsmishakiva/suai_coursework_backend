package suai.coursework.auth.domain.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import suai.coursework.auth.dao.dto.TaskGroupDto;
import suai.coursework.auth.dao.dto.TaskStatusDto;
import suai.coursework.auth.dao.dto.TaskTypeDto;
import suai.coursework.auth.dao.persistence.ITaskGroupRepository;
import suai.coursework.auth.dao.persistence.ITaskStatusRepository;
import suai.coursework.auth.dao.persistence.ITaskTypeRepository;
import suai.coursework.auth.domain.models.task.TaskGroup;
import suai.coursework.auth.domain.models.task.TaskStatus;
import suai.coursework.auth.domain.models.task.TaskType;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskParamsService implements ITaskParamsService {

    private final ITaskTypeRepository taskTypeRepository;
    private final ITaskStatusRepository taskStatusRepository;
    private final ITaskGroupRepository taskGroupRepository;

    @Override
    public ResponseEntity<?> getTypes() {
        List<TaskType> tasks = taskTypeRepository.findAllBy();
        HashMap<String, Object> body = new HashMap<>();
        body.put("types", tasks);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getGroups() {
        List<TaskGroup> tasks = taskGroupRepository.findAllBy();
        HashMap<String, Object> body = new HashMap<>();
        body.put("groups", tasks);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getStatuses() {
        List<TaskStatus> tasks = taskStatusRepository.findAllBy();
        HashMap<String, Object> body = new HashMap<>();
        body.put("statuses", tasks);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createType(TaskTypeDto typeDto) {
        if (taskStatusRepository.existsByLabel(typeDto.getLabel())) {
            return new ResponseEntity<>("Type with this label already exists", HttpStatus.SEE_OTHER);
        }
        TaskType type = new TaskType();
        type.setLabel(typeDto.getLabel());
        type.setColor(typeDto.getColor());
        taskTypeRepository.save(type);
        HashMap<String, Object> body = new HashMap<>();
        body.put("type", type);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createGroup(TaskGroupDto groupDto) {
        if (taskStatusRepository.existsByLabel(groupDto.getLabel())) {
            return new ResponseEntity<>("Group with this label already exists", HttpStatus.SEE_OTHER);
        }
        TaskGroup group = new TaskGroup();
        group.setLabel(groupDto.getLabel());
        group.setColor(groupDto.getColor());
        taskGroupRepository.save(group);
        HashMap<String, Object> body = new HashMap<>();
        body.put("group", group);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createStatus(TaskStatusDto statusDto) {
        if (taskStatusRepository.existsByLabel(statusDto.getLabel())) {
            return new ResponseEntity<>("Status with this label already exists", HttpStatus.SEE_OTHER);
        }
        TaskStatus status = new TaskStatus();
        status.setLabel(statusDto.getLabel());
        taskStatusRepository.save(status);
        HashMap<String, Object> body = new HashMap<>();
        body.put("status", status);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}

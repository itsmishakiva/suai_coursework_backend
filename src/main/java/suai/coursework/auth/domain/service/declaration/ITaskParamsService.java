package suai.coursework.auth.domain.service.declaration;

import org.springframework.http.ResponseEntity;
import suai.coursework.auth.dao.dto.task.TaskGroupDto;
import suai.coursework.auth.dao.dto.task.TaskStatusDto;
import suai.coursework.auth.dao.dto.task.TaskTypeDto;

public interface ITaskParamsService {

    ResponseEntity<?> getTypes();

    ResponseEntity<?> getGroups();

    ResponseEntity<?> getStatuses();

    ResponseEntity<?> createType(TaskTypeDto typeDto);

    ResponseEntity<?> createGroup(TaskGroupDto groupDto);

    ResponseEntity<?> createStatus(TaskStatusDto statusDto);
}

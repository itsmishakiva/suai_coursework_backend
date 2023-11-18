package suai.coursework.auth.domain.service;

import org.springframework.http.ResponseEntity;
import suai.coursework.auth.dao.dto.TaskDto;
import suai.coursework.auth.dao.dto.TaskGroupDto;
import suai.coursework.auth.dao.dto.TaskStatusDto;
import suai.coursework.auth.dao.dto.TaskTypeDto;

public interface ITaskParamsService {

    ResponseEntity<?> getTypes();

    ResponseEntity<?> getGroups();

    ResponseEntity<?> getStatuses();

    ResponseEntity<?> createType(TaskTypeDto typeDto);

    ResponseEntity<?> createGroup(TaskGroupDto groupDto);

    ResponseEntity<?> createStatus(TaskStatusDto statusDto);
}

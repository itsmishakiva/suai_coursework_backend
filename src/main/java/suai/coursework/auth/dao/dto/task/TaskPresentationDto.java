package suai.coursework.auth.dao.dto.task;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import suai.coursework.auth.domain.models.users.User;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequiredArgsConstructor
public class TaskPresentationDto {
    private int id;
    private String title;
    private String description;
    private Integer statusId;
    private Integer typeId;
    private Integer groupId;
    private User user;
}

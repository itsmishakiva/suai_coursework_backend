package suai.coursework.auth.dao.dto.task;


import lombok.*;
import lombok.experimental.FieldDefaults;
import suai.coursework.auth.domain.models.users.User;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequiredArgsConstructor
public class TaskDto {
    private String title;
    private String description;
    private Integer statusId;
    private Integer typeId;
    private Integer groupId;
    private Integer userId;
}

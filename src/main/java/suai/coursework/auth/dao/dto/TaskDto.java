package suai.coursework.auth.dao.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

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
    @Nullable private Integer id;
}

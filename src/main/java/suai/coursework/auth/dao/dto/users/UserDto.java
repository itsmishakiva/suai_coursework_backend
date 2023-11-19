package suai.coursework.auth.dao.dto.users;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {
    private String username;
    private Long avatarColor;
    private String avatarPath;
    private String name;
    private String surname;
    private String statusInfo;
    private String description;
    private String email;
}

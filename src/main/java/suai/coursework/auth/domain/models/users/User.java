package suai.coursework.auth.domain.models.users;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;


@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull Integer id;
    @NonNull String username;
    @NonNull Long avatarColor;
    @Nullable String avatarPath;
    @Nullable String name;
    @Nullable String surname;
    @Nullable String statusInfo;
    @Nullable String description;
    @Nullable String email;
}
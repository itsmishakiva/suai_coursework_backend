package suai.coursework.auth.domain.models.task;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    Integer id;
    @NonNull
    String title;
    @Nullable
    String description;
    @NonNull
    Integer statusId;
    @Nullable
    Integer typeId;
    @Nullable
    Integer groupId;
}
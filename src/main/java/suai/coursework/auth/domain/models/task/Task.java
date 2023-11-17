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
@Table(name = "tasks")
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    Integer id;
    @NonNull
    TaskStatus taskStatus;
    @Nullable
    TaskType taskType;
    @Nullable
    TaskGroup taskGroup;
    int color;
}
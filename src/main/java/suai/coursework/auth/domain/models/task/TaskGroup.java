package suai.coursework.auth.domain.models.task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskGroup implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    Integer id;
    @NonNull String label;
    @NonNull Long color;
}
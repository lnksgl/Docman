package spring.post;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Entity
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class    Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    Long id;
    @NotBlank
    @Column
    String title;
    @Lob
    @Column
    @NonNull
    String content;
    @Column
    Instant createdOn;
    @Column
    Instant updatedOn;
    @Column
    @NotBlank
    String username;
    @Column
    String category;
}

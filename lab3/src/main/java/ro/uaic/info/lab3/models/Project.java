package ro.uaic.info.lab3.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    private UUID id;
    private String name;
    private String description;
    private LocalDate deadline;
    private String category;
}

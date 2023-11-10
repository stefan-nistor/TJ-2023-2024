package ro.uaic.info.lab3.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "projects", schema = "public")
@AllArgsConstructor
public class Project {
    @Id
    private UUID id;
    private String name;
    private String description;
    private LocalDate deadline;
    private String category;
}

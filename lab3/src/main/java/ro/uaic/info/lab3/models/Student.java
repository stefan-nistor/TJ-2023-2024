package ro.uaic.info.lab3.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Student {

    @Id
    private UUID id;
    private String firstName;
    private String lastName;

    @OneToMany
    private List<Project> projectList;
}

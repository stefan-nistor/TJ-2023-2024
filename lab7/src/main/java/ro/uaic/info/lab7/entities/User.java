package ro.uaic.info.lab7.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "User.findByUsername",
query = "SELECT u from User u WHERE u.username = :username" )
public class User extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String password;
    private Role role;

    @OneToMany
    private List<Preference> preferenceList;
}

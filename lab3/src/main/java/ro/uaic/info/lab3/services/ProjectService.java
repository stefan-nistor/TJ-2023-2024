package ro.uaic.info.lab3.services;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import ro.uaic.info.lab3.models.Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class ProjectService {

    private static final String DB_URL = "jdbc:postgresql://localhost:5433/tjlabs";

    private List<Project> getProjects(Connection conn) throws SQLException {
        ResultSet resultSet = conn.createStatement().executeQuery("SELECT * from projects");
        List<Project> result = new ArrayList<>();
        while (resultSet.next()) {
            Project project = Project.builder()
                    .name(resultSet.getString("name"))
                    .description(resultSet.getString("description"))
                    .category(resultSet.getString("category"))
                    .deadline(resultSet.getDate("deadline").toLocalDate())
                    .build();
            result.add(project);
        }
        return result;
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, "postgres", "admin");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }


}

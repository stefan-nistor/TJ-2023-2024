package ro.uaic.info.lab3.services;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import ro.uaic.info.lab3.models.Project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ManagedBean
@RequestScoped
public class ProjectService {

    private static final Logger LOGGER = Logger.getLogger(ProjectService.class.getName());
    private static final String DB_URL = "jdbc:postgresql://localhost:5433/tjlabs";
    private static final String SQL_SELECT_ALL_PROJECT = "SELECT * from public.projects";
    private static final String SQL_INSERT_PROJECT = "INSERT INTO projects (name, description, category, deadline) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_PROJECT = "UPDATE projects SET name=?, description=?, category=?, deadline=? WHERE id=?";
    private static final String SQL_DELETE_PROJECT = "DELETE FROM projects WHERE id=?";

    private static PreparedStatement prepareStatement(Connection conn, String query, Project project) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, project.getName());
        ps.setString(2, project.getDescription());
        ps.setString(3, project.getCategory());
        ps.setDate(4, Date.valueOf(project.getDeadline()));
        return ps;
    }

    private static void closeConnections(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            LOGGER.severe("Caught exception at SQL close Connection" + e.getMessage());
        }
    }

    public List<Project> getProjects() throws SQLException {
        Connection conn = getConnection();
        ResultSet resultSet = conn.createStatement().executeQuery(SQL_SELECT_ALL_PROJECT);
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
        closeConnections(resultSet, null, conn);
        return result;
    }

    public void createProject(Project project) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = prepareStatement(conn, SQL_INSERT_PROJECT, project);
        ps.executeUpdate();
        closeConnections(null, ps, conn);
    }

    public void updateProject(Project project) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = prepareStatement(conn, SQL_UPDATE_PROJECT, project);
        ps.setObject(5, project.getId());
        ps.executeUpdate();
        closeConnections(null, ps, conn);
    }

    public void deleteProject(UUID id) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_DELETE_PROJECT);
        ps.setObject(1, id);
        ps.executeUpdate();
        closeConnections(null, ps, conn);
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, "postgres", "admin");
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.severe("Caught exception at SQL get Connection: " + e.getMessage());
        }
        return conn;
    }
}
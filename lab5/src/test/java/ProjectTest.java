import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ro.uaic.info.lab4.entities.Project;
import ro.uaic.info.lab4.repositories.ProjectRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ProjectTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private ProjectRepository projectRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() {
        Project project = new Project();
        projectRepository.save(project);
        verify(entityManager, times(1)).persist(project);
    }

    @Test
    public void testFindById() {
        Project project = new Project();
        when(entityManager.find(Project.class, 1L)).thenReturn(project);
        Optional<Project> foundProject = projectRepository.findById(1L);
        verify(entityManager, times(1)).find(Project.class, 1L);
        assertTrue(foundProject.isPresent());
        assertEquals(project, foundProject.get());
    }

}
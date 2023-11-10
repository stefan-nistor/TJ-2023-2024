package ro.uaic.info.lab4;

import ro.uaic.info.lab4.repositories.ProjectRepository;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class ProjectService implements Serializable {

    @Inject
    private ProjectRepository projectRepository;

}

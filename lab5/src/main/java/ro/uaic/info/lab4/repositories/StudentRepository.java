package ro.uaic.info.lab4.repositories;

import jakarta.transaction.Transactional;
import ro.uaic.info.lab4.entities.Student;

@Transactional
public class StudentRepository extends DataRepository<Student, Long>{

    protected StudentRepository(Class<Student> entityClass) {
        super(entityClass);
    }

}

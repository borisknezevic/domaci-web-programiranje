package vezbe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vezbe.demo.model.Employee;
import vezbe.demo.model.Project;

import java.util.Set;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}

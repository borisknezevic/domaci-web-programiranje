package vezbe.demo.service;

import org.springframework.stereotype.Service;
import vezbe.demo.model.Employee;
import vezbe.demo.model.Project;
import vezbe.demo.repository.EmployeeRepository;
import vezbe.demo.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;



    public ProjectService(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Project> getProjects(Long userId) {
        Employee employee = employeeRepository.getById(userId);
        List<Project> projectList = new ArrayList<>();
        projectList = projectRepository.findByEmployees(employee);

        if(projectList == null){
            System.out.println("Nema projekata za trazenog korisnika");
        }
        return projectList;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}

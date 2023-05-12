package vezbe.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vezbe.demo.dto.ProjectDto;
import vezbe.demo.model.Employee;
import vezbe.demo.model.Project;
import vezbe.demo.service.EmployeeService;
import vezbe.demo.service.ProjectService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProjectRestController {

    private final EmployeeService employeeService;
    private final ProjectService projectService;

    public ProjectRestController(EmployeeService employeeService, ProjectService projectService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @GetMapping("/api/projects")
    public ResponseEntity<List<ProjectDto>> getProjects(HttpSession session){

        Employee loggedEmployee = (Employee) session.getAttribute("employee");
        List<Project> projectList = new ArrayList<>();
        if(loggedEmployee == null) {
            projectList = projectService.getAllProjects();
            System.out.println("Nema sesije");
        } else {
            System.out.println(loggedEmployee);
            projectList = projectService.getProjects(loggedEmployee.getId());
        }


        List<ProjectDto> response = projectList
                .stream()
                .map(project ->
                        new ProjectDto(project.getId(),project.getName(),project.getDeadline()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

}

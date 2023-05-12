package vezbe.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vezbe.demo.dto.ProjectDto;
import vezbe.demo.model.Employee;
import vezbe.demo.model.Project;
import vezbe.demo.service.ProjectService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProjectBasicController {


    private final ProjectService projectService;

    public ProjectBasicController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public String getProjects(Model model, HttpSession session){
        List<Project> projects = new ArrayList<>();
        Employee loggedEmployee = (Employee) session.getAttribute("employee");
        if(loggedEmployee == null) {
            System.out.println("Nema sesije");
            projects = projectService.getAllProjects();
        } else {
            projects = projectService.getProjects(loggedEmployee.getId());
            System.out.println(loggedEmployee);
        }
        List<ProjectDto> projectList = projects.stream().map(project -> new ProjectDto(project.getId(),project.getName(),project.getDeadline())).collect(Collectors.toList());

        model.addAttribute("projects", projectList);



        return "projects.html";

    }
}

package vezbe.demo.dto;

import vezbe.demo.model.Department;
import vezbe.demo.model.Employee;
import vezbe.demo.model.Project;
import java.util.HashSet;
import java.util.Set;

public class EmployeeDto {

    private Long id;

    private String firstname;

    private String lastname;

    private String position;

    private Department department;

    private Set<Project> projects = new HashSet<>();

    public EmployeeDto() {
    }

    public EmployeeDto(Long id, String firstname, String lastname, String position, Department department, Set<Project> projects) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.position = position;
        this.department = department;
        this.projects = projects;
    }

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.firstname = employee.getFirstname();
        this.lastname = employee.getLastname();
        this.position = employee.getPosition();
        this.department = employee.getDepartment();
        this.projects = employee.getProjects();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}

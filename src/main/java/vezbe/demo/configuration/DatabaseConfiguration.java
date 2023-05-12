package vezbe.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vezbe.demo.model.Company;
import vezbe.demo.model.Department;
import vezbe.demo.model.Employee;
import vezbe.demo.model.Project;
import vezbe.demo.repository.CompanyRepository;
import vezbe.demo.repository.DepartmentRepository;
import vezbe.demo.repository.EmployeeRepository;
import vezbe.demo.repository.ProjectRepository;

import java.util.*;

@Configuration
public class DatabaseConfiguration {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Bean
    public boolean instantiate(){
        Company company = new Company("Company 1", "Some address 123");

        companyRepository.save(company);

        Department department1 = new Department("first department");
        Department department2 = new Department("second department");

        department1.setCompany(company);
        department2.setCompany(company);
        departmentRepository.saveAll(
                List.of(department1, department2)
        );

        Employee pera = new Employee(
                "peraperic","pera123","Pera", "Peric", "Rukovodilac", department1
        );
        Employee mika = new Employee(
                "mikamikic", "mika123","Mika", "Mikic", "Menadzer", department1
        );
        Employee zika = new Employee(
                "zikazikic","zika123","Zika", "Zikic", "Radnik", department2
        );

        employeeRepository.saveAll(
                List.of(pera, mika, zika)
        );

        Project project1 = new Project(
                "Projekat 1", new Date(125, Calendar.JULY, 4)
        );

        Project project2 = new Project(
                "Projekat 2", new Date(129, Calendar.DECEMBER, 3)
        );

        projectRepository.saveAll(
                List.of(project1, project2)
        );

        mika.getProjects().add(project1);
        mika.getProjects().add(project2);

        zika.getProjects().add(project2);

        employeeRepository.save(mika);
        employeeRepository.save(zika);

        return true;
    }
}
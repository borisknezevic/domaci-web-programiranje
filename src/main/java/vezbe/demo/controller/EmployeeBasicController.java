package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.dto.LoginDto;
import vezbe.demo.model.Employee;
import vezbe.demo.service.EmployeeService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EmployeeBasicController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/hello")
    public String welcome(){
        return "index.html";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login-form";
    }

    @GetMapping("/login-form")
    public String login(Model model) {
        LoginDto loginDto = new LoginDto();
        model.addAttribute("login", loginDto);
        return "login.html";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto, HttpSession session){
        // proverimo da li su podaci validni
        if(loginDto.getUsername().isEmpty() || loginDto.getPassword().isEmpty())
            return "redirect:/login-form";

        Employee employee = employeeService.login(loginDto.getUsername(), loginDto.getPassword());
        if (employee == null)
            return "redirect:/login-form";

        session.setAttribute("employee", employee);
        return "redirect:/hello";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login-form";
    }

    @GetMapping("/employees")
    public String getEmployees(Model model, HttpSession session){
        List<Employee> employeeList = employeeService.findAll();
        model.addAttribute("employees", employeeList);

        Employee loggedEmployee = (Employee) session.getAttribute("employee");
        if(loggedEmployee == null) {
            System.out.println("Nema sesije");
        } else {
            System.out.println(loggedEmployee);
        }

        return "employees.html";
    }

    @GetMapping("/employees/{id}")
    public String getEmployee(@PathVariable(name = "id") Long id, Model model, HttpSession session){
        Employee employee = employeeService.findOne(id);
        model.addAttribute("employee", employee);
        return "employee.html";
    }

    @GetMapping("/add-employee")
    public String addEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "add-employee.html";
    }

    @PostMapping("/save-employee")
    public String saveEmployee(@ModelAttribute Employee employee) {
        this.employeeService.save(employee);
        return "redirect:/employees";
    }
}

package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.model.Employee;
import vezbe.demo.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee findOne(Long id){
        Optional<Employee> foundEmployee = employeeRepository.findById(id);
        if (foundEmployee.isPresent())
            return foundEmployee.get();

        return null;
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee login(String username, String password) {
        Employee employee = employeeRepository.getByUsername(username);
        if(employee == null || !employee.getPassword().equals(password))
            return null;
        return  employee;
    }
}

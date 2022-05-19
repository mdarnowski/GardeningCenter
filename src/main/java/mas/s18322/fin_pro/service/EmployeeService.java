package mas.s18322.fin_pro.service;

import lombok.RequiredArgsConstructor;
import mas.s18322.fin_pro.model.emps.Employee;
import mas.s18322.fin_pro.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    /***
     * Selects customer with specified id
     * @param id id of a customer
     * @return customer with specified id
     */

    public Employee getById(long id){
        List<Employee> res = new ArrayList<>();
        Optional<Employee> cus = employeeRepository.findById(id);
        return cus.orElse(null);
    }
}

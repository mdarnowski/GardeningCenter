package mas.s18322.fin_pro.repository;
import mas.s18322.fin_pro.model.emps.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}

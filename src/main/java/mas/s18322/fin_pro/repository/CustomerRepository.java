package mas.s18322.fin_pro.repository;

import mas.s18322.fin_pro.model.customers.Customer;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CustomerRepository  extends CrudRepository<Customer, Long> {
    /***
     * Selects customers with surname starting with input parameter
     * @param surname the starting characters of surname
     * @return list of customers with surname starting with 'name'
     */
    List<Customer> findAllBySurnameStartingWith(String surname);
}

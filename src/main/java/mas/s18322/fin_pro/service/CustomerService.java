package mas.s18322.fin_pro.service;

import lombok.RequiredArgsConstructor;
import mas.s18322.fin_pro.model.customers.Customer;
import mas.s18322.fin_pro.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    /***
     * Select all customer from database
     * @return all customers in the database
     */
    public List<Customer> getAllCustomers(){
        Iterable<Customer> all = customerRepository.findAll();
        List<Customer> res = new ArrayList<>();
        all.forEach(res::add);
        return res;
    }

    /***
     * Selects customers with surname starting with input parameter
     * @param surname the starting characters of customer's surname
     * @return list of customers with surname starting with 'surname'
     */
    public List<Customer> getAllCustomersBySurname(String surname){
        Iterable<Customer> all = customerRepository.findAllBySurnameStartingWith(surname);
        List<Customer> res = new ArrayList<>();
        all.forEach(res::add);
        return res;
    }

    /***
     * Obtains customer list containing customer with specified id
     * @param id customer's id
     * @return list of customers
     */
    public List<Customer> getListById(long id){
        List<Customer> res = new ArrayList<>();
        Optional<Customer> cus = customerRepository.findById(id);
        cus.ifPresent(res::add);
        return res;
    }
}

package mas.s18322.fin_pro.model.customers.discount;

import lombok.*;
import mas.s18322.fin_pro.model.customers.Customer;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class EmployeesDiscount extends CustomersDiscount{
    @OneToMany(mappedBy = "disEmployees", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Set<Customer> customers = new HashSet<>();
}

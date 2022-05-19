package mas.s18322.fin_pro.model.emps;

import com.sun.istack.Nullable;
import lombok.*;
import lombok.experimental.SuperBuilder;
import mas.s18322.fin_pro.model.customers.Customer;
import mas.s18322.fin_pro.model.product.Transaction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public class Employee extends Customer {
    @Nullable
    private String note;
    @OneToMany(mappedBy = "concludes", fetch = FetchType.LAZY)
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private Set<Transaction> transactions = new HashSet<>();


}

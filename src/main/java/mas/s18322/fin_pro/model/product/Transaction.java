package mas.s18322.fin_pro.model.product;

import com.sun.istack.NotNull;
import lombok.*;
import mas.s18322.fin_pro.model.customers.Customer;
import mas.s18322.fin_pro.model.emps.Employee;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee registers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer concludes;

    private LocalDate date;

    @OneToMany(mappedBy = "trans", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Shopping> shoppingBags = new HashSet<>();
}

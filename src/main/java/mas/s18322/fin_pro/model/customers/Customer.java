package mas.s18322.fin_pro.model.customers;

import lombok.*;
import lombok.experimental.SuperBuilder;
import mas.s18322.fin_pro.model.customers.discount.EmployeesDiscount;
import mas.s18322.fin_pro.model.customers.discount.SeniorsDiscount;
import mas.s18322.fin_pro.model.customers.discount.StudentsDiscount;
import mas.s18322.fin_pro.model.product.Transaction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SuperBuilder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotBlank(message = "Name is mandatory")
    @Size(min =2, max =100)
    private String name;

    @NotBlank(message = "Surname is mandatory")
    @Size(min =2, max =100)
    private String surname;

    @ManyToOne()
    @ToString.Exclude
    @JoinColumn(name = "disEmployees_id")
    private EmployeesDiscount disEmployees;

    @ManyToOne()
    @ToString.Exclude
    @JoinColumn(name = "disSeniors_id")
    private SeniorsDiscount disSeniors;

    @ManyToOne()
    @ToString.Exclude
    @JoinColumn(name = "disStudents_id")
    private StudentsDiscount disStudents;

    @OneToOne(cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JoinColumn(name = "details_id")
    private Details details;

    @OneToMany(mappedBy = "registers", fetch = FetchType.LAZY)
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Transaction> transactions = new HashSet<>();


}

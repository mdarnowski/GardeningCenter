package mas.s18322.fin_pro.model.customers.discount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class CustomersDiscount {
    private Double percentage;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
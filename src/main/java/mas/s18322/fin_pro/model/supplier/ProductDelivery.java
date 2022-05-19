package mas.s18322.fin_pro.model.supplier;

import lombok.*;
import mas.s18322.fin_pro.model.product.Product;
import mas.s18322.fin_pro.model.product.Transaction;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDelivery {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @NotNull
    private Product prod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id", nullable = false)
    @NotNull
    private Delivery delivered;

    @Size(min = 1)
    private int amount;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}

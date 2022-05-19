package mas.s18322.fin_pro.model.product;

import lombok.*;
import mas.s18322.fin_pro.model.supplier.ProductDelivery;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "bought", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private Set<Shopping> shoppingBags = new HashSet<>();

    @OneToMany(mappedBy = "prod", fetch = FetchType.LAZY)
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private Set<ProductDelivery> productDeliveries = new HashSet<>();

    @NotNull
    private Type type;

    @NotNull
    private String name;

    @NotNull
    private int price;
}

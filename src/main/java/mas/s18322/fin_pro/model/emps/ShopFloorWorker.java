package mas.s18322.fin_pro.model.emps;

import lombok.*;
import lombok.experimental.SuperBuilder;
import mas.s18322.fin_pro.model.workshifts.WorkingShopFloor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@SuperBuilder
public class ShopFloorWorker extends Employee{
    @OneToMany(mappedBy = "shopFloorWorker", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<WorkingShopFloor> parShopFlor = new HashSet<>();
}

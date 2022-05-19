package mas.s18322.fin_pro.model.workshifts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mas.s18322.fin_pro.model.emps.ShopFloorWorker;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkingShopFloor {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopFloorWorker_id", nullable = false)
    @NotNull
    private ShopFloorWorker shopFloorWorker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workShift_id", nullable = false)
    @NotNull
    private WorkShift floorWorkShift;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}

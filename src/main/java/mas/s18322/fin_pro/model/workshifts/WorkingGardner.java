package mas.s18322.fin_pro.model.workshifts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mas.s18322.fin_pro.model.emps.Gardner;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkingGardner {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gardner_id", nullable = false)
    @NotNull
    private Gardner gardner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workShift_id", nullable = false)
    @NotNull
    private WorkShift gardnerWorkShift;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}

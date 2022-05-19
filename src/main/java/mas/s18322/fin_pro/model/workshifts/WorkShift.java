package mas.s18322.fin_pro.model.workshifts;

import lombok.*;
import mas.s18322.fin_pro.model.emps.Gardner;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkShift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "workday_id", nullable = false, updatable = false)
    private Workday day;

    @OneToMany(mappedBy = "gardnerWorkShift")
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<WorkingGardner> gardners = new HashSet<>();

    @OneToMany(mappedBy = "floorWorkShift")
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<WorkingShopFloor> shopFloorWorkers = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "participationGardner_id")
    @Setter(AccessLevel.NONE)
    private Gardner manager;

    /***
     * Sets the manager of the work shift if he is working in that work shift
     * @param manager manager of the work shift
     */
    public void setManager(Gardner manager) {
        if(gardners.stream().anyMatch(p -> p.getGardner().equals(manager)))
            this.manager = manager;
    }


}

package mas.s18322.fin_pro.model.workshifts;

import lombok.*;

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
public class Workday {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "workweek_id", nullable = false, updatable = false)
    private Workweek week;

    @OneToMany(mappedBy = "day", fetch = FetchType.EAGER, cascade =  CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<WorkShift> workShifts = new HashSet<>();
}

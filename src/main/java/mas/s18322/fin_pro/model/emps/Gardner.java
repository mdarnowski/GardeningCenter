package mas.s18322.fin_pro.model.emps;

import lombok.*;
import lombok.experimental.SuperBuilder;
import mas.s18322.fin_pro.model.workshifts.WorkingGardner;
import mas.s18322.fin_pro.model.workshifts.WorkShift;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@SuperBuilder
public class Gardner extends Employee{

    @OneToMany(mappedBy = "gardner", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<WorkingGardner> gardnerPar = new HashSet<>();

    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<WorkShift> manages = new HashSet<>();

}

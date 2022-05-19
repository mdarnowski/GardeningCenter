package mas.s18322.fin_pro.model.customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Details {
    @NotBlank(message = "Email is mandatory")
    @Size(min = 2, max = 100)
    private String email;

    @NotBlank(message = "TellNr is mandatory")
    private Integer tellNr;

    @NotBlank(message = "birthdate is mandatory")
    private LocalDate birthdate;

    @ElementCollection
    @CollectionTable(name = "details_schools", joinColumns = @JoinColumn(name = "details_id"))
    private Set<String> schools = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}

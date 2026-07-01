package netflix.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import netflix.enums.GenrePersonne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Acteur {
    private long id;
    private String nom;
    private String prenom;
    private int age;
    private GenrePersonne genre;

}

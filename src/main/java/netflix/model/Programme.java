package netflix.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import netflix.enums.Genre;
import netflix.enums.Langue;
import netflix.enums.Pays;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Programme {
    private long id;
    private String titre;
    private List<Note> note;
    private Genre genre;
    private String synopsis;
    private long nombreDeVue;
    private LocalDate dateSortie;
    private List<Acteur> acteurs;
    private Studio studio;
    private int restrictionDAge;
    private List<Langue> languesDisponible;
    private Pays pays;

    public double voirNoteMoyenne() {
        if (note == null || note.isEmpty()) {
            return 0;
        }

        return note.stream()
                .mapToInt(Note::getNote)
                .average()
                .orElse(0);
    }

}
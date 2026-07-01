package netflix.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Saison {
    private long id;
    private List<Episode> episodes;
    private List<Note> notes;
    private LocalDate dateDebutDeSortie;
    private LocalDate dateFinDeSortie;

    public int compterNombreEpisode() {
        if (episodes == null) {
            return 0;
        }

        return episodes.size();
    }

    public double voirNoteMoyenne() {
        if (notes == null || notes.isEmpty()) {
            return 0;
        }

        return notes.stream()
                .mapToInt(Note::getNote)
                .average()
                .orElse(0);
    }

}
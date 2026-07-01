package netflix.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Episode {

    private long id;
    private String titre;
    private List<Note> note;
    private LocalDate dateSortie;
    private Duration duree;

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
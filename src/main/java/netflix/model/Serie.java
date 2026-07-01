package netflix.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Serie extends Programme {
    private List<Saison> saisons;

    public int compterNombreTotalSaison() {
        if (saisons == null) {
            return 0;
        }
        return saisons.size();
    }

    public int compterNombreTotalEpisode() {
        if (saisons == null) {
            return 0;
        }

        return saisons.stream()
                .mapToInt(Saison::compterNombreEpisode)
                .sum();
    }

}
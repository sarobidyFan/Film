package netflix.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionVisionnage {
    private Long id;
    private LocalDateTime dateHeure;
    private Programme programme;
    private Utilisateur organisateur;
    private List<Utilisateur> participants;

    public void ajouterParticipant(Utilisateur utilisateur) {
        participants.add(utilisateur);
    }
    
    public void supprimerParticipant(Utilisateur utilisateur) {
        participants.remove(utilisateur);
    }

    public int nombreParticipants() {
        return participants.size();
    }

}
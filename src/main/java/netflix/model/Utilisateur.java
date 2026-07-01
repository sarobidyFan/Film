package netflix.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Utilisateur {
    private long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String motDePasse;
    private List<Programme> historique;
    private List<Programme> watchList;

    public void regarderProgramme(Programme programme) {
        historique.add(programme);
        programme.setNombreDeVue(programme.getNombreDeVue() + 1);

    }

    public Programme rechercherProgramme(List<Programme> programmes, String titre) {
        return programmes.stream()
                .filter(programme -> programme.getTitre().equalsIgnoreCase(titre))
                .findFirst()
                .orElse(null);

    }


    public List<Programme> ajouterDansWatchList(Programme programme) {
        watchList.add(programme);
        return watchList;

    }

    public Note noterProgramme(Programme programme, int note, String commentaire) {
        Note nouvelleNote = new Note();
        nouvelleNote.setNote(note);
        nouvelleNote.setCommentaire(commentaire);
        programme.getNote().add(nouvelleNote);

        return nouvelleNote;

    }

    public Note noterSaison(Saison saison, int note, String commentaire) {
        Note nouvelleNote = new Note();
        nouvelleNote.setNote(note);
        nouvelleNote.setCommentaire(commentaire);
        saison.getNotes().add(nouvelleNote);

        return nouvelleNote;

    }

    public Note noterEpisode(Episode episode, int note, String commentaire) {
        Note nouvelleNote = new Note();
        nouvelleNote.setNote(note);
        nouvelleNote.setCommentaire(commentaire);
        episode.getNote().add(nouvelleNote);

        return nouvelleNote;

    }

    public SessionVisionnage planifierVisionnage(
            Programme programme,
            List<Utilisateur> invites,
            LocalDateTime dateHeure) {

        return new SessionVisionnage(
                null,
                dateHeure,
                programme,
                this,
                invites
        );
    }

}

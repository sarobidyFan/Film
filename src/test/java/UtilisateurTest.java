import netflix.enums.Genre;
import netflix.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilisateurTest {

    private Utilisateur utilisateur;
    private Film film;
    private Saison saison;
    private Episode episode;

    @BeforeEach
    void setUp() {

        utilisateur = Utilisateur.builder()
                .id(1L)
                .nom("Jean")
                .prenom("Rakoto")
                .dateNaissance(LocalDate.of(2000, 1, 1))
                .motDePasse("1234")
                .historique(new ArrayList<>())
                .watchList(new ArrayList<>())
                .build();

        film = Film.builder()
                .id(1L)
                .titre("Avatar")
                .genre(Genre.ACTION)
                .note(new ArrayList<>())
                .nombreDeVue(0)
                .duree(Duration.ofHours(3))
                .build();

        saison = Saison.builder()
                .notes(new ArrayList<>())
                .episodes(new ArrayList<>())
                .build();

        episode = Episode.builder()
                .note(new ArrayList<>())
                .build();
    }

    @Test
    void regarderProgramme() {
        utilisateur.regarderProgramme(film);

        assertEquals(1, utilisateur.getHistorique().size());
        assertEquals(1, film.getNombreDeVue());
    }

    @Test
    void rechercherProgramme() {
        List<Programme> programmes = List.of(film);

        Programme resultat = utilisateur.rechercherProgramme(programmes, "Avatar");

        assertEquals(film, resultat);
    }

    @Test
    void ajouterDansWatchList() {
        List<Programme> watchList = utilisateur.ajouterDansWatchList(film);

        assertEquals(1, watchList.size());
        assertTrue(watchList.contains(film));
    }

    @Test
    void noterProgramme() {
        Note note = utilisateur.noterProgramme(film, 5, "Excellent");

        assertEquals(5, note.getNote());
        assertEquals(1, film.getNote().size());
    }

    @Test
    void noterSaison() {
        utilisateur.noterSaison(saison, 4, "Très bien");

        assertEquals(1, saison.getNotes().size());
    }

    @Test
    void noterEpisode() {
        utilisateur.noterEpisode(episode, 3, "Bien");

        assertEquals(1, episode.getNote().size());
    }

    @Test
    void planifierVisionnage() {

        List<Utilisateur> invites = List.of(utilisateur);

        SessionVisionnage session = utilisateur.planifierVisionnage(
                film,
                invites,
                LocalDateTime.of(2026, 7, 1, 20, 0)
        );

        assertEquals(utilisateur, session.getOrganisateur());
        assertEquals(film, session.getProgramme());
        assertEquals(invites, session.getParticipants());
    }
}
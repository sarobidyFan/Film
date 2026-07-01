import netflix.enums.TypeAbonnement;
import netflix.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    private Admin admin;
    private Plateforme plateforme;
    private Film film;
    private Serie serie;
    private Saison saison;
    private Episode episode;
    private Abonnement abonnementActif;
    private Abonnement abonnementInactif;

    @BeforeEach
    void setUp() {

        admin = Admin.builder()
                .id(1L)
                .nom("Admin")
                .prenom("Netflix")
                .historique(new ArrayList<>())
                .watchList(new ArrayList<>())
                .build();

        film = Film.builder().build();
        serie = Serie.builder().saisons(new ArrayList<>()).build();
        saison = Saison.builder().episodes(new ArrayList<>()).build();
        episode = Episode.builder().build();

        abonnementActif = new Abonnement(
                1L,
                TypeAbonnement.SIMPLE,
                new ArrayList<>(),
                LocalDate.now(),
                LocalDate.now().plusDays(30)
        );

        abonnementInactif = new Abonnement(
                2L,
                TypeAbonnement.PREMIUM,
                new ArrayList<>(),
                LocalDate.now().minusMonths(2),
                LocalDate.now().minusDays(1)
        );

        plateforme = Plateforme.builder()
                .films(new ArrayList<>())
                .series(new ArrayList<>())
                .abonnements(new ArrayList<>(List.of(abonnementActif, abonnementInactif)))
                .build();

        plateforme.calculerRevenuTotal();
    }

    @Test
    void voirRevenuTotal() {
        assertEquals(80000, admin.voirRevenuTotal(plateforme));
    }

    @Test
    void compterAbonnements() {
        assertEquals(2, admin.compterAbonnements(plateforme));
    }

    @Test
    void voirSiUnAbonnementEstActif() {
        assertTrue(admin.voirSiUnAbonnementEstActif(abonnementActif));
        assertFalse(admin.voirSiUnAbonnementEstActif(abonnementInactif));
    }

    @Test
    void compterAbonnementsActifs() {
        assertEquals(1, admin.compterAbonnementsActifs(plateforme));
    }

    @Test
    void compterAbonnementsInactifs() {
        assertEquals(1, admin.compterAbonnementsInactifs(plateforme));
    }

    @Test
    void ajouterFilm() {
        admin.ajouterFilm(plateforme, film);

        assertTrue(plateforme.getFilms().contains(film));
    }

    @Test
    void ajouterSerie() {
        admin.ajouterSerie(plateforme, serie);

        assertTrue(plateforme.getSeries().contains(serie));
    }

    @Test
    void ajouterUneSaison() {
        admin.ajouterUneSaison(serie, saison);

        assertTrue(serie.getSaisons().contains(saison));
    }

    @Test
    void ajouterUnEpisode() {
        admin.ajouterUnEpisode(saison, episode);

        assertTrue(saison.getEpisodes().contains(episode));
    }

    @Test
    void supprimerFilm() {
        plateforme.getFilms().add(film);

        admin.supprimerFilm(plateforme, film);

        assertFalse(plateforme.getFilms().contains(film));
    }

    @Test
    void supprimerSerie() {
        plateforme.getSeries().add(serie);

        admin.supprimerSerie(plateforme, serie);

        assertFalse(plateforme.getSeries().contains(serie));
    }

    @Test
    void supprimerUneSaison() {
        serie.getSaisons().add(saison);

        admin.supprimerUneSaison(serie, saison);

        assertFalse(serie.getSaisons().contains(saison));
    }

    @Test
    void supprimerUnEpisode() {
        saison.getEpisodes().add(episode);

        admin.supprimerUnEpisode(saison, episode);

        assertFalse(saison.getEpisodes().contains(episode));
    }
}
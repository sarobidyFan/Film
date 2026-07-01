import netflix.enums.Genre;
import netflix.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static netflix.enums.Genre.*;
import static netflix.enums.Pays.*;
import static netflix.enums.TypeAbonnement.SIMPLE;
import static org.junit.jupiter.api.Assertions.*;

class PlateformeTest {

    private Plateforme plateforme;
    private Film film1;
    private Film film2;
    private Serie serie;
    private Abonnement abonnement;

    @BeforeEach
    void setUp() {

        film1 = Film.builder()
                .titre("Avatar")
                .genre(ACTION)
                .nombreDeVue(100)
                .dateSortie(LocalDate.of(2022,12,14))
                .acteurs(new ArrayList<>())
                .note(new ArrayList<>())
                .languesDisponible(new ArrayList<>())
                .duree(Duration.ofHours(3))
                .pays(ETATS_UNIS)
                .build();

        film2 = Film.builder()
                .titre("Titanic")
                .genre(Genre.DRAME)
                .nombreDeVue(50)
                .dateSortie(LocalDate.now().plusDays(10))
                .acteurs(new ArrayList<>())
                .note(new ArrayList<>())
                .languesDisponible(new ArrayList<>())
                .duree(Duration.ofHours(3))
                .pays(ETATS_UNIS)
                .build();

        serie = Serie.builder()
                .titre("Dark")
                .nombreDeVue(200)
                .note(new ArrayList<>())
                .acteurs(new ArrayList<>())
                .saisons(new ArrayList<>())
                .languesDisponible(new ArrayList<>())
                .dateSortie(LocalDate.now())
                .build();

        abonnement = new Abonnement(
                1L,
                SIMPLE,
                new ArrayList<>(),
                LocalDate.now(),
                LocalDate.now().plusMonths(1)
        );

        plateforme = Plateforme.builder()
                .films(new ArrayList<>(List.of(film1, film2)))
                .series(new ArrayList<>(List.of(serie)))
                .abonnements(new ArrayList<>())
                .revenuTotal(0)
                .build();
    }

    @Test
    void ajouterAbonnement() {

        plateforme.ajouterAbonnement(abonnement);

        assertEquals(1, plateforme.getAbonnements().size());
        assertEquals(20000, plateforme.getRevenuTotal());
    }

    @Test
    void supprimerAbonnement() {

        plateforme.ajouterAbonnement(abonnement);

        plateforme.supprimerAbonnement(abonnement);

        assertTrue(plateforme.getAbonnements().isEmpty());
        assertEquals(0, plateforme.getRevenuTotal());
    }

    @Test
    void calculerRevenuTotal() {

        plateforme.ajouterAbonnement(abonnement);

        assertEquals(20000, plateforme.getRevenuTotal());
    }

    @Test
    void voirFilmsLesPlusVus() {

        List<Film> resultat = plateforme.voirFilmsLesPlusVus();

        assertEquals(film1, resultat.get(0));
    }

    @Test
    void voirFilmsParGenre() {

        List<Film> resultat = plateforme.voirFilmsParGenre(ACTION);

        assertEquals(1, resultat.size());
        assertEquals(film1, resultat.get(0));
    }

    @Test
    void voirFilmsParPays() {

        List<Film> resultat = plateforme.voirFilmsParPays(ETATS_UNIS);

        assertEquals(2, resultat.size());
    }

    @Test
    void voirFilmsBientotDisponibles() {

        List<Film> resultat = plateforme.voirFilmsBientotDisponibles();

        assertTrue(resultat.contains(film2));
    }

    @Test
    void voirSeriesLesPlusVues() {

        List<Serie> resultat = plateforme.voirSeriesLesPlusVues();

        assertEquals(serie, resultat.get(0));
    }
}

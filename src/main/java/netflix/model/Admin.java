package netflix.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Admin extends Utilisateur {

    public long voirRevenuTotal(Plateforme plateforme) {
        return plateforme.getRevenuTotal();
    }

    public long compterAbonnements(Plateforme plateforme) {
        return plateforme.getAbonnements().size();
    }

    public boolean voirSiUnAbonnementEstActif(Abonnement abonnement) {
        return abonnement.estActif();
    }

    public long compterAbonnementsActifs(Plateforme plateforme) {
        return plateforme.getAbonnements()
                .stream()
                .filter(this::voirSiUnAbonnementEstActif)
                .count();
    }

    public long compterAbonnementsInactifs(Plateforme plateforme) {
        return plateforme.getAbonnements()
                .stream()
                .filter(abonnement -> !voirSiUnAbonnementEstActif(abonnement))
                .count();
    }

    public void ajouterFilm(Plateforme plateforme, Film film) {
        plateforme.getFilms().add(film);
    }

    public void ajouterSerie(Plateforme plateforme, Serie serie) {
        plateforme.getSeries().add(serie);
    }

    public void ajouterUneSaison(Serie serie, Saison saison) {
        serie.getSaisons().add(saison);
    }

    public void ajouterUnEpisode(Saison saison, Episode episode) {
        saison.getEpisodes().add(episode);
    }

    public void supprimerFilm(Plateforme plateforme, Film film) {
        plateforme.getFilms().remove(film);
    }

    public void supprimerSerie(Plateforme plateforme, Serie serie) {
        plateforme.getSeries().remove(serie);
    }

    public void supprimerUneSaison(Serie serie, Saison saison) {
        serie.getSaisons().remove(saison);
    }

    public void supprimerUnEpisode(Saison saison, Episode episode) {
        saison.getEpisodes().remove(episode);
    }

}
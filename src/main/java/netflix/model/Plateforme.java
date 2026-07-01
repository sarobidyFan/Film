package netflix.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import netflix.enums.Genre;
import netflix.enums.Pays;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plateforme {
    private List<Film> films ;
    private List<Serie> series ;
    private List<Abonnement> abonnements ;

    private long revenuTotal;

    public void ajouterAbonnement(Abonnement abonnement) {
        abonnements.add(abonnement);
        calculerRevenuTotal();
    }

    public void supprimerAbonnement(Abonnement abonnement) {
        abonnements.remove(abonnement);
        calculerRevenuTotal();
    }

    public void calculerRevenuTotal() {
        revenuTotal = abonnements.stream()
                .mapToLong(Abonnement::getPrix)
                .sum();
    }

    public List<Film> voirFilmsLesPlusVus() {
        return films.stream()
                .sorted(Comparator.comparingLong(Film::getNombreDeVue).reversed())
                .limit(10)
                .toList();
    }

    public List<Film> voirFilmsLesMieuxNotes() {
        return films.stream()
                .sorted(Comparator.comparingDouble(Film::voirNoteMoyenne).reversed())
                .limit(10)
                .toList();
    }

    public List<Film> voirFilmsLesPlusSousCotes() {
        return films.stream()
                .sorted(
                        Comparator.comparingDouble(Film::voirNoteMoyenne)
                                .reversed()
                                .thenComparingLong(Film::getNombreDeVue)
                )
                .limit(10)
                .toList();
    }

    public List<Film> voirNouveauxFilms() {
        return films.stream()
                .sorted(Comparator.comparing(Film::getDateSortie).reversed())
                .limit(10)
                .toList();
    }

    public List<Film> voirFilmsParActeur(Acteur acteur) {
        return films.stream()
                .filter(film -> film.getActeurs().contains(acteur))
                .limit(10)
                .toList();
    }
    public List<Film> voirFilmsParGenre(Genre genre) {
        return films.stream()
                .filter(film -> film.getGenre() == genre)
                .limit(10)
                .toList();
    }

    public List<Film> voirFilmsParStudio(Studio studio) {
        return films.stream()
                .filter(film -> film.getStudio().equals(studio))
                .limit(10)
                .toList();
    }

    public List<Film> voirFilmsParPays(Pays pays) {
        return films.stream()
                .filter(film -> film.getPays().equals(pays))
                .limit(10)
                .toList();
    }

    public List<Film> voirFilmsBientotDisponibles() {
        return films.stream()
                .filter(film -> film.getDateSortie().isAfter(LocalDate.now()))
                .limit(10)
                .toList();

    }

    public List<Serie> voirSeriesLesPlusVues() {
        return series.stream()
                .sorted(Comparator.comparingLong(Serie::getNombreDeVue).reversed())
                .limit(10)
                .toList();
    }

    public List<Serie> voirSeriesLesMieuxNotees() {
        return series.stream()
                .sorted(Comparator.comparingDouble(Serie::voirNoteMoyenne).reversed())
                .limit(10)
                .toList();
    }

    public List<Serie> voirSeriesLesPlusSousCotees() {
        return series.stream()
                .sorted(
                        Comparator.comparingDouble(Serie::voirNoteMoyenne)
                                .reversed()
                                .thenComparingLong(Serie::getNombreDeVue)
                )
                .limit(10)
                .toList();
    }

    public List<Serie> voirNouvellesSeries() {
        return series.stream()
                .sorted(Comparator.comparing(Serie::getDateSortie).reversed())
                .limit(10)
                .toList();
    }

    public List<Serie> voirSeriesParActeur(Acteur acteur) {
        return series.stream()
                .filter(serie -> serie.getActeurs().contains(acteur))
                .limit(10)
                .toList();
    }

    public List<Serie> voirSeriesParGenre(Genre genre) {
        return series.stream()
                .filter(serie -> serie.getGenre() == genre)
                .limit(10)
                .toList();
    }

    public List<Serie> voirSeriesParStudio(Studio studio) {
        return series.stream()
                .filter(serie -> serie.getStudio().equals(studio))
                .limit(10)
                .toList();
    }

    public List<Serie> voirSeriesParPays(Pays pays) {
        return series.stream()
                .filter(serie -> serie.getPays().equals(pays))
                .limit(10)
                .toList();
    }

    public List<Serie> voirSeriesBientotDisponibles() {
        return series.stream()
                .filter(serie -> serie.getDateSortie().isAfter(LocalDate.now()))
                .limit(10)
                .toList();
    }

}
package netflix.model;

import lombok.*;
import netflix.enums.TypeAbonnement;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class Abonnement {

    private long id;
    private TypeAbonnement typeAbonnement;
    private List<Utilisateur> utilisateurs = new ArrayList<>();
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private long prix;
    public Abonnement(long id,
                      TypeAbonnement typeAbonnement,
                      List<Utilisateur> utilisateurs,
                      LocalDate dateDebut,
                      LocalDate dateFin) {

        this.id = id;
        this.typeAbonnement = typeAbonnement;
        this.utilisateurs = utilisateurs;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;

        switch (typeAbonnement) {

            case SIMPLE:
                this.prix = 20000;
                break;

            case FAMILLE:
                this.prix = 40000;
                break;

            case PREMIUM:
                this.prix = 60000;
                break;

            default:
                this.prix = 0;
        }
    }

    public boolean estActif() {
        return dateFin != null && dateFin.isAfter(LocalDate.now());
    }

}
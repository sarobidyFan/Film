package netflix.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import netflix.enums.TypeAbonnement;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Client extends Utilisateur {
    private Plateforme plateforme;
    private String email;
    private Abonnement abonnement;
    private CompteBancaire compteBancaire;

    public boolean payerAbonnement() {
        if (abonnement == null || compteBancaire == null) {
            return false;
        }
        this.plateforme.setRevenuTotal(plateforme.getRevenuTotal() + abonnement.getPrix());
        return compteBancaire.payer(abonnement.getPrix());
    }

    public Abonnement changerTypeAbonnement(TypeAbonnement nouveauType) {
        abonnement.setTypeAbonnement(nouveauType);
        switch (nouveauType) {
            case SIMPLE:
                abonnement.setPrix(20000);
                break;

            case FAMILLE:
                abonnement.setPrix(40000);
                break;

            case PREMIUM:
                abonnement.setPrix(60000);
                break;
        }
        return abonnement;
    }

}
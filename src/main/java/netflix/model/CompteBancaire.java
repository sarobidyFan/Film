package netflix.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CompteBancaire {
    private long numeroCompte;
    private double solde;

    public boolean payer(long montant) {
        if (solde >= montant) {
            solde -= montant;
            return true;
        }
        return false;
    }

    public void crediter(double montant) {
        solde += montant;
    }

}
import netflix.enums.TypeAbonnement;
import netflix.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static netflix.enums.TypeAbonnement.*;
import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    private Client client;
    private Plateforme plateforme;
    private Abonnement abonnement;
    private CompteBancaire compteBancaire;

    @BeforeEach
    void setUp() {

        plateforme = Plateforme.builder()
                .films(new ArrayList<>())
                .series(new ArrayList<>())
                .abonnements(new ArrayList<>())
                .revenuTotal(0)
                .build();

        abonnement = new Abonnement(
                1L,
                SIMPLE,
                new ArrayList<>(),
                LocalDate.now(),
                LocalDate.now().plusMonths(1)
        );

        compteBancaire = CompteBancaire.builder()
                .solde(100000)
                .build();

        client = Client.builder()
                .id(1L)
                .nom("Rakoto")
                .prenom("Jean")
                .plateforme(plateforme)
                .email("jean@gmail.com")
                .abonnement(abonnement)
                .compteBancaire(compteBancaire)
                .build();
    }

    @Test
    void payerAbonnement() {
        assertTrue(client.payerAbonnement());
        assertEquals(20000, plateforme.getRevenuTotal());
    }

    @Test
    void payerAbonnementSansAbonnement() {

        Client clientSansAbonnement = Client.builder()
                .plateforme(plateforme)
                .compteBancaire(compteBancaire)
                .build();

        assertFalse(clientSansAbonnement.payerAbonnement());
    }

    @Test
    void changerTypeAbonnementSimpleVersPremium() {

        Abonnement resultat = client.changerTypeAbonnement(TypeAbonnement.PREMIUM);

        assertEquals(TypeAbonnement.PREMIUM, resultat.getTypeAbonnement());
        assertEquals(60000, resultat.getPrix());
    }

    @Test
    void changerTypeAbonnementFamille() {

        Abonnement resultat = client.changerTypeAbonnement(TypeAbonnement.FAMILLE);

        assertEquals(TypeAbonnement.FAMILLE, resultat.getTypeAbonnement());
        assertEquals(40000, resultat.getPrix());
    }
}
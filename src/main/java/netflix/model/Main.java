package netflix.model;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\n1. Afficher film");
            System.out.println("2. Afficher Abonnement");
            System.out.println("3. Quitter");
            System.out.print("Ton choix : ");
            choix = sc.nextInt();

            switch (choix) {
                case 1:
                    afficherFilms();
                    break;
                case 2:
                    afficherAbonnements();
                    break;
                case 3:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 3);

        sc.close();
    }

    public static void afficherFilms() {
        String sql = "SELECT p.id, p.titre, p.synopsis, p.genre, p.nombre_de_vue, " +
                "p.date_sortie, p.restriction_age, p.pays, f.duree " +
                "FROM programme p JOIN film f ON p.id = f.id";

        try (Connection con = ConnexionBD.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getLong("id") + " - "
                        + rs.getString("titre") + " ("
                        + rs.getString("genre") + ", "
                        + rs.getString("pays") + ", "
                        + rs.getDate("date_sortie") + ") - "
                        + "Durée: " + rs.getString("duree") + " - "
                        + "Vues: " + rs.getLong("nombre_de_vue") + " - "
                        + "Restriction: -" + rs.getInt("restriction_age") + "an(s)");
            }

        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public static void afficherAbonnements() {
        String sql = "SELECT a.id, a.type, a.date_debut, a.date_fin, a.prix, " +
                "c.nom, c.prenom " +
                "FROM abonnement a " +
                "LEFT JOIN client c ON a.client_id = c.id";

        try (Connection con = ConnexionBD.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nomClient = rs.getString("nom");
                String prenomClient = rs.getString("prenom");
                String client = (nomClient != null) ? prenomClient + " " + nomClient : "Aucun client";

                System.out.println(rs.getLong("id") + " - "
                        + client + " - "
                        + rs.getString("type") + " ("
                        + rs.getDate("date_debut") + " -> "
                        + rs.getDate("date_fin") + ") - "
                        + rs.getBigDecimal("prix") + "Ar" +
                        "");
            }

        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
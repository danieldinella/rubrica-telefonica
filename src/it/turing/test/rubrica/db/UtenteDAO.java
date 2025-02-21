package it.turing.test.rubrica.db;

import java.sql.*;

/**
 * Classe per la gestione dell'autenticazione degli utenti nel database.
 */
public class UtenteDAO {

    /**
     * Verifica se le credenziali di accesso fornite corrispondono a un utente registrato.
     * 
     * @param username Il nome utente da verificare.
     * @param password La password associata all'utente.
     * @return {@code true} se le credenziali sono valide, {@code false} altrimenti.
     */
    public static boolean verificaCredenziali(String username, String password) {
        String query = "SELECT 1 FROM utenti WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Se esiste almeno una riga, le credenziali sono corrette
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

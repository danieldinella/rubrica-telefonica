package it.turing.test.rubrica.db;

import it.turing.test.rubrica.model.Persona;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe per la gestione delle operazioni CRUD sulla tabella "persone" nel database.
 */
public class PersonaDAO {

    /**
     * Inserisce una nuova persona nel database.
     * 
     * @param persona L'oggetto {@link Persona} da salvare.
     * @return L'ID generato della persona salvata, oppure -1 in caso di errore.
     */
    public int insertPersona(Persona persona) {
        String query = "INSERT INTO persone (nome, cognome, indirizzo, telefono, eta) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, persona.getNome());
            stmt.setString(2, persona.getCognome());
            stmt.setString(3, persona.getIndirizzo());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getEta());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Restituisce l'ID generato
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Errore
    }

    /**
     * Recupera tutte le persone presenti nel database.
     * 
     * @return Una lista di oggetti {@link Persona} con i dati delle persone.
     */
    public ArrayList<Persona> getAllPersone() {
        ArrayList<Persona> persone = new ArrayList<>();
        String query = "SELECT * FROM persone";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Persona p = new Persona(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("indirizzo"),
                    rs.getString("telefono"),
                    rs.getInt("eta")
                );
                persone.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persone;
    }

    /**
     * Aggiorna i dati di una persona nel database.
     * 
     * @param persona L'oggetto {@link Persona} con i nuovi dati da aggiornare.
     */
    public void updatePersona(Persona persona) {
        String sql = "UPDATE persone SET nome = ?, cognome = ?, telefono = ?, indirizzo = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, persona.getNome());
            stmt.setString(2, persona.getCognome());
            stmt.setString(3, persona.getTelefono());
            stmt.setString(4, persona.getIndirizzo());
            stmt.setInt(5, persona.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina una persona dal database in base al suo ID.
     * 
     * @param id L'ID della persona da eliminare.
     */
    public void deletePersona(int id) {
        String sql = "DELETE FROM persone WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Verifica se un numero di telefono esiste già nel database.
     * 
     * Questo metodo esegue una query di ricerca nella tabella `persone` per verificare se il numero di telefono
     * passato come parametro è già presente. Se il numero è già associato a un altro record, il metodo restituirà
     * `true`, altrimenti restituirà `false`.
     * 
     * @param telefono Il numero di telefono da verificare.
     * @return `true` se il numero di telefono esiste già nel database, `false` altrimenti.
     * @throws SQLException Se si verifica un errore durante l'esecuzione della query SQL.
     */
    public static boolean esisteTelefono(String telefono) {
        String query = "SELECT COUNT(*) FROM persone WHERE telefono = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, telefono);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;  // Se esiste almeno una persona con quel telefono
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

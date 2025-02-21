package it.turing.test.rubrica.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Classe per la gestione della connessione al database.
 * Carica le credenziali da un file di configurazione e fornisce un metodo per ottenere una connessione.
 */
public class DatabaseManager {
    private static final String PROPERTIES_FILE = "credenziali_database.properties";
    private static String url;
    private static String username;
    private static String password;

    /**
     * Blocco statico che carica le credenziali del database dal file di configurazione.
     * Inizializza l'URL di connessione, l'username e la password.
     */
    static {
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
            Properties properties = new Properties();
            properties.load(fis);
            String host = properties.getProperty("host", "localhost");
            String port = properties.getProperty("port", "3306");
            String dbName = properties.getProperty("database", "rubrica");
            username = properties.getProperty("username", "root");
            password = properties.getProperty("password", "");
            url = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?serverTimezone=UTC";
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore nel caricamento delle credenziali del database.");
        }
    }

    /**
     * Restituisce una connessione al database utilizzando i parametri caricati.
     * 
     * @return Oggetto {@link Connection} per interagire con il database.
     * @throws SQLException Se si verifica un errore durante la connessione.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}

package it.turing.test.rubrica.installer;

import java.io.*;
import java.sql.*;
import java.util.Properties;

/**
 * Classe responsabile dell'installazione del database per la Rubrica Telefonica.
 * Crea il database e le tabelle necessarie utilizzando uno script SQL.
 */
public class Installer {
    /**
     * Metodo principale che avvia il processo di installazione del database.
     * Carica le credenziali dal file di configurazione e crea il database e le tabelle.
     * 
     * @param args Argomenti della riga di comando (non utilizzati).
     */
    public static void main(String[] args) {
        System.out.println("Avvio installazione della Rubrica Telefonica...");

        // Carica le credenziali dal file properties
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("credenziali_database.properties")) {
            props.load(fis);
        } catch (IOException e) {
            System.err.println("Errore: impossibile leggere il file delle credenziali.");
            return;
        }

        String dbUser = props.getProperty("username");
        String dbPassword = props.getProperty("password");
        String dbHost = props.getProperty("ip-server-mysql", "localhost");
        String dbPort = props.getProperty("porta", "3306");
        String dbUrl = "jdbc:mysql://" + dbHost + ":" + dbPort + "/";

        // Connessione a MySQL
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            System.out.println("Connessione a MySQL riuscita!");

            // Creazione del database e delle tabelle
            executeSQLFile(conn, "schema_database.sql");
            System.out.println("Database e tabelle create con successo!");
        } catch (SQLException e) {
            System.err.println("Errore durante l'installazione: " + e.getMessage());
        }
    }

    /**
     * Esegue un file SQL per creare il database e le relative tabelle.
     * 
     * @param conn     Connessione al database.
     * @param filePath Percorso del file SQL da eseguire.
     */
    private static void executeSQLFile(Connection conn, String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             Statement stmt = conn.createStatement()) {
            String line;
            StringBuilder sql = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sql.append(line);
                if (line.trim().endsWith(";")) {
                    stmt.execute(sql.toString());
                    sql.setLength(0);
                }
            }
        } catch (IOException | SQLException e) {
            System.err.println("Errore nell'esecuzione dello script SQL: " + e.getMessage());
        }
    }
}

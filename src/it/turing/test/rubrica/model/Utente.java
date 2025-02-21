package it.turing.test.rubrica.model;

/**
 * Classe che rappresenta un utente del sistema.
 */
public class Utente {
    private String username;
    private String password;

    /**
     * Costruttore per creare un nuovo utente.
     *
     * @param username Nome utente.
     * @param password Password dell'utente.
     */
    public Utente(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Restituisce il nome utente.
     *
     * @return Username dell'utente.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Imposta il nome utente.
     *
     * @param username Nuovo nome utente.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Restituisce la password dell'utente.
     *
     * @return Password dell'utente.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta la password dell'utente.
     *
     * @param password Nuova password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

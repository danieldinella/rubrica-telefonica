package it.turing.test.rubrica.model;

/**
 * Classe che rappresenta una persona all'interno della rubrica.
 */
public class Persona {
    private String nome;
    private String cognome;
    private String indirizzo;
    private String telefono;
    private int eta;
    private int id = -1;

    /**
     * Costruttore con ID.
     * 
     * @param id       Identificativo univoco della persona nel database.
     * @param n        Nome della persona.
     * @param cn       Cognome della persona.
     * @param i        Indirizzo della persona.
     * @param t        Numero di telefono della persona.
     * @param e        Età della persona.
     */
    public Persona(int id, String n, String cn, String i, String t, int e) {
        this.id = id;
        this.nome = n;
        this.cognome = cn;
        this.indirizzo = i;
        this.telefono = t;
        this.eta = e;
    }

    /**
     * Costruttore senza ID, usato per nuove persone non ancora inserite nel database.
     * 
     * @param n  Nome della persona.
     * @param cn Cognome della persona.
     * @param i  Indirizzo della persona.
     * @param t  Numero di telefono della persona.
     * @param e  Età della persona.
     */
    public Persona(String n, String cn, String i, String t, int e) {
        this.nome = n;
        this.cognome = cn;
        this.indirizzo = i;
        this.telefono = t;
        this.eta = e;
    }

    /**
     * Imposta l'ID della persona.
     * 
     * @param id Identificativo univoco della persona.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Imposta il nome della persona.
     * 
     * @param n Nome della persona.
     */
    public void setNome(String n) {
        this.nome = n;
    }

    /**
     * Imposta il cognome della persona.
     * 
     * @param cn Cognome della persona.
     */
    public void setCognome(String cn) {
        this.cognome = cn;
    }

    /**
     * Imposta l'indirizzo della persona.
     * 
     * @param i Indirizzo della persona.
     */
    public void setIndirizzo(String i) {
        this.indirizzo = i;
    }

    /**
     * Imposta il numero di telefono della persona.
     * 
     * @param t Numero di telefono della persona.
     */
    public void setTelefono(String t) {
        this.telefono = t;
    }

    /**
     * Imposta l'età della persona.
     * 
     * @param e Età della persona.
     */
    public void setEta(int e) {
        this.eta = e;
    }

    /**
     * Restituisce l'ID della persona.
     * 
     * @return Identificativo univoco della persona.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Restituisce il nome della persona.
     * 
     * @return Nome della persona.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce il cognome della persona.
     * 
     * @return Cognome della persona.
     */
    public String getCognome() {
        return this.cognome;
    }

    /**
     * Restituisce l'indirizzo della persona.
     * 
     * @return Indirizzo della persona.
     */
    public String getIndirizzo() {
        return this.indirizzo;
    }

    /**
     * Restituisce il numero di telefono della persona.
     * 
     * @return Numero di telefono della persona.
     */
    public String getTelefono() {
        return this.telefono;
    }

    /**
     * Restituisce l'età della persona.
     * 
     * @return Età della persona.
     */
    public int getEta() {
        return this.eta;
    }
}

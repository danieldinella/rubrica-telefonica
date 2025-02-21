package it.turing.test.rubrica.model;

import java.util.ArrayList;

/**
 * Classe che gestisce l'elenco delle persone nella rubrica.
 */
public class Rubrica {
    private static ArrayList<Persona> persone = new ArrayList<>();

    /**
     * Imposta l'elenco delle persone nella rubrica.
     * 
     * @param ps Lista di persone da inserire nella rubrica.
     */
    public static void setRubrica(ArrayList<Persona> ps) {
        persone = ps;
    }

    /**
     * Aggiunge una persona alla rubrica.
     * 
     * @param p Persona da aggiungere.
     */
    public static void addPersona(Persona p) {
        persone.add(p);
    }

    /**
     * Rimuove una persona dalla rubrica in base all'indice.
     * 
     * @param index Indice della persona da rimuovere.
     */
    public static void removePersona(int index) {
        persone.remove(index);
    }

    /**
     * Aggiorna una persona esistente nella rubrica.
     * 
     * @param index Indice della persona da aggiornare.
     * @param p     Nuovi dati della persona.
     */
    public static void updatePersona(int index, Persona p) {
        persone.set(index, p);
    }

    /**
     * Restituisce una persona dalla rubrica in base all'indice.
     * 
     * @param index Indice della persona richiesta.
     * @return Persona corrispondente all'indice specificato.
     */
    public static Persona getPersona(int index) {
        return persone.get(index);
    }

    /**
     * Restituisce l'elenco di tutte le persone nella rubrica.
     * 
     * @return Lista di persone presenti nella rubrica.
     */
    public static ArrayList<Persona> getPersone() {
        return persone;
    }

    /**
     * Restituisce il numero totale di persone nella rubrica.
     * 
     * @return Numero di persone presenti nella rubrica.
     */
    public static int getSize() {
        return persone.size();
    }
}

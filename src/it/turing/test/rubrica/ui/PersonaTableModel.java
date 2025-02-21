package it.turing.test.rubrica.ui;

import it.turing.test.rubrica.model.*;
import javax.swing.table.AbstractTableModel;

/**
 * Modello della tabella per la visualizzazione delle persone nella rubrica.
 * Gestisce la struttura e i dati della tabella utilizzando le informazioni della classe {@link Rubrica}.
 */
public class PersonaTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private final String[] colonne = {"Nome", "Cognome", "Telefono"};

    /**
     * Restituisce il numero di righe della tabella, corrispondente al numero di persone nella rubrica.
     *
     * @return Il numero di persone presenti nella rubrica.
     */
    @Override
    public int getRowCount() {
        return Rubrica.getSize();
    }

    /**
     * Restituisce il numero di colonne della tabella.
     *
     * @return Il numero di colonne.
     */
    @Override
    public int getColumnCount() {
        return colonne.length;
    }

    /**
     * Restituisce il nome della colonna in base all'indice.
     *
     * @param col L'indice della colonna.
     * @return Il nome della colonna corrispondente.
     */
    @Override
    public String getColumnName(int col) {
        return colonne[col];
    }

    /**
     * Restituisce il valore da visualizzare in una determinata cella della tabella.
     *
     * @param rowIndex    L'indice della riga.
     * @param columnIndex L'indice della colonna.
     * @return Il valore corrispondente alla cella specificata.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Persona p = Rubrica.getPersona(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getNome();
            case 1 -> p.getCognome();
            case 2 -> p.getTelefono();
            default -> null;
        };
    }
}

package it.turing.test.rubrica.ui;

import it.turing.test.rubrica.model.Persona;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PersonaTableModel extends AbstractTableModel {
    private final String[] colonne = {"Nome", "Cognome", "Telefono"};
    private List<Persona> persone;

    public PersonaTableModel(List<Persona> persone) {
        this.persone = persone;
    }

    @Override
    public int getRowCount() {
        return persone.size();
    }

    @Override
    public int getColumnCount() {
        return colonne.length;
    }

    @Override
    public String getColumnName(int col) {
        return colonne[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Persona p = persone.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getNome();
            case 1 -> p.getCognome();
            case 2 -> p.getTelefono();
            default -> null;
        };
    }
}

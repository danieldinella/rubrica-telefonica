package it.turing.test.rubrica.ui;

import it.turing.test.rubrica.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private JTable table;
    private PersonaTableModel tableModel;
    private JButton btnNuovo, btnModifica, btnElimina;

    public MainFrame() {
        setTitle("Rubrica Telefonica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        tableModel = new PersonaTableModel(Rubrica.getPersone());
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Pannello inferiore con i bottoni
        JPanel panelBottoni = new JPanel();
        btnNuovo = new JButton("Nuovo");
        btnModifica = new JButton("Modifica");
        btnElimina = new JButton("Elimina");

        panelBottoni.add(btnNuovo);
        panelBottoni.add(btnModifica);
        panelBottoni.add(btnElimina);
        add(panelBottoni, BorderLayout.SOUTH);

        // Eventi Bottoni
        btnNuovo.addActionListener(e -> apriEditorPersona(null));
        btnModifica.addActionListener(e -> modificaPersona());
        btnElimina.addActionListener(e -> eliminaPersona());
    }

    private void apriEditorPersona(Persona persona) {
        EditorPersonaDialog dialog = new EditorPersonaDialog(this, persona);
        dialog.setVisible(true);

        if (dialog.isSalvato()) {
            Persona p = dialog.getPersona();
            if (persona == null) {
                Rubrica.addPersona(p);
            } else {
                int index = table.getSelectedRow();
                Rubrica.updatePersona(index, p);
            }
            tableModel.fireTableDataChanged();
        }
    }

    private void modificaPersona() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleziona una persona da modificare!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Persona persona = Rubrica.getPersona(selectedRow);
        apriEditorPersona(persona);
    }

    private void eliminaPersona() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleziona una persona da eliminare!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Persona persona = Rubrica.getPersona(selectedRow);
        int conferma = JOptionPane.showConfirmDialog(this, "Eliminare la persona " + persona.getNome() + " " + persona.getCognome() + "?", "Conferma", JOptionPane.YES_NO_OPTION);
        if (conferma == JOptionPane.YES_OPTION) {
            Rubrica.removePersona(selectedRow);
            tableModel.fireTableDataChanged();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}

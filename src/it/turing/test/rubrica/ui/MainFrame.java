package it.turing.test.rubrica.ui;

import it.turing.test.rubrica.db.PersonaDAO;
import it.turing.test.rubrica.model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Classe principale dell'applicazione Rubrica Telefonica.
 * Gestisce l'interfaccia utente principale con una tabella delle persone
 * e una barra degli strumenti per aggiungere, modificare ed eliminare contatti.
 */
public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable table;
    private PersonaTableModel tableModel;
    private JButton btnNuovo, btnModifica, btnElimina;
    private PersonaDAO personaDAO; // DAO per interagire con il database
    private JToolBar toolBar; // Barra degli strumenti

    /**
     * Costruttore della finestra principale.
     * Inizializza l'interfaccia utente e carica i dati dal database.
     */
    public MainFrame() {
        setTitle("Rubrica Telefonica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        personaDAO = new PersonaDAO(); // Inizializzazione DAO
        tableModel = new PersonaTableModel();
        table = new JTable(tableModel);

        // Recupera i dati dal database all'avvio
        Rubrica.setRubrica(personaDAO.getAllPersone());
        tableModel.fireTableDataChanged();

        // Creazione della barra degli strumenti
        creaToolBar();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Crea la barra degli strumenti (JToolBar) e aggiunge i pulsanti con icone.
     */
    private void creaToolBar() {
        toolBar = new JToolBar();
        add(toolBar, BorderLayout.NORTH); // Posiziona la toolbar in alto

        // Creazione dei pulsanti con icone
        btnNuovo = creaBottone("Nuovo", "resources/add.png");
        btnModifica = creaBottone("Modifica", "resources/edit.png");
        btnElimina = creaBottone("Elimina", "resources/delete.png");

        // Aggiunta dei pulsanti alla toolbar
        toolBar.add(btnNuovo);
        toolBar.add(btnModifica);
        toolBar.add(btnElimina);

        // Eventi Bottoni
        btnNuovo.addActionListener(_ -> apriEditorPersona(null));
        btnModifica.addActionListener(_ -> modificaPersona());
        btnElimina.addActionListener(_ -> eliminaPersona());
    }

    /**
     * Crea un pulsante con testo e icona.
     *
     * @param testo        Testo del pulsante
     * @param percorsoIcona Percorso dell'icona da caricare
     * @return JButton con testo e icona
     */
    private JButton creaBottone(String testo, String percorsoIcona) {
        JButton bottone = new JButton(testo);
        ImageIcon icon = new ImageIcon(percorsoIcona); // Carica l'icona
        bottone.setIcon(icon);
        return bottone;
    }

    /**
     * Apre l'EditorPersonaDialog per aggiungere o modificare una persona.
     *
     * @param persona Persona da modificare (null se si sta creando una nuova)
     */
    private void apriEditorPersona(Persona persona) {
        EditorPersonaDialog dialog = new EditorPersonaDialog(this, persona);
        dialog.setVisible(true);

        if (dialog.isSalvato()) {
            Persona p = dialog.getPersona();

            if (persona == null) {
                // Nuova persona → Inserisci nel database
                Rubrica.addPersona(p);
            } else {
                // Persona esistente → Modifica nel database
                p.setId(persona.getId());
                int index = table.getSelectedRow();
                Rubrica.updatePersona(index, p);
            }
            tableModel.fireTableDataChanged();
        }
    }

    /**
     * Modifica la persona selezionata nella tabella.
     */
    private void modificaPersona() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleziona una persona da modificare!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Persona persona = Rubrica.getPersona(selectedRow);
        apriEditorPersona(persona);
    }

    /**
     * Elimina la persona selezionata nella tabella previa conferma.
     */
    private void eliminaPersona() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleziona una persona da eliminare!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Persona persona = Rubrica.getPersona(selectedRow);
        int conferma = JOptionPane.showConfirmDialog(this,
                "Eliminare la persona " + persona.getNome() + " " + persona.getCognome() + "?",
                "Conferma", JOptionPane.YES_NO_OPTION);

        if (conferma == JOptionPane.YES_OPTION) {
            personaDAO.deletePersona(persona.getId()); // Eliminazione dal database
            Rubrica.removePersona(selectedRow); // Eliminazione dalla lista locale
            tableModel.fireTableDataChanged();
        }
    }
}

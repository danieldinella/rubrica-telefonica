package it.turing.test.rubrica.ui;

import it.turing.test.rubrica.model.*;
import it.turing.test.rubrica.db.PersonaDAO;

import javax.swing.*;
import java.awt.*;

/**
 * Finestra di dialogo per l'inserimento e la modifica dei dati di una persona.
 */
public class EditorPersonaDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTextField txtNome, txtCognome, txtIndirizzo, txtTelefono, txtEta;
    private boolean salvato = false;
    private Persona persona;

    /**
     * Costruttore della finestra di dialogo per la gestione di una persona.
     *
     * @param parent  Il frame principale dell'applicazione.
     * @param persona L'oggetto Persona da modificare o null per crearne una nuova.
     */
    public EditorPersonaDialog(JFrame parent, Persona persona) {
        super(parent, "Editor Persona", true);
        this.persona = (persona != null) ? persona : new Persona("", "", "", "", 0);
        
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Creazione della toolbar
        JToolBar toolBar = new JToolBar();
        JButton btnSalva = new JButton("Salva", new ImageIcon("resources/save.png"));
        JButton btnAnnulla = new JButton("Annulla", new ImageIcon("resources/cancel.png"));
        toolBar.add(btnSalva);
        toolBar.add(btnAnnulla);
        add(toolBar, BorderLayout.NORTH);

        // Creazione del form
        JPanel panelForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtNome = new JTextField(this.persona.getNome(), 15);
        txtCognome = new JTextField(this.persona.getCognome(), 15);
        txtIndirizzo = new JTextField(this.persona.getIndirizzo(), 15);
        txtTelefono = new JTextField(this.persona.getTelefono(), 15);
        txtEta = new JTextField(String.valueOf(this.persona.getEta()), 5);

        aggiungiCampo(panelForm, "Nome:", txtNome, gbc);
        aggiungiCampo(panelForm, "Cognome:", txtCognome, gbc);
        aggiungiCampo(panelForm, "Indirizzo:", txtIndirizzo, gbc);
        aggiungiCampo(panelForm, "Telefono:", txtTelefono, gbc);
        aggiungiCampo(panelForm, "Età:", txtEta, gbc);

        add(panelForm, BorderLayout.CENTER);

        btnSalva.addActionListener(_ -> salvaPersona());
        btnAnnulla.addActionListener(_ -> dispose());
    }

    /**
     * Aggiunge un campo di input al pannello del form.
     *
     * @param panel     Il pannello a cui aggiungere il campo.
     * @param label     L'etichetta associata al campo di input.
     * @param textField Il campo di input testuale.
     * @param gbc       Il layout manager constraints per la disposizione degli elementi.
     */
    private void aggiungiCampo(JPanel panel, String label, JTextField textField, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(textField, gbc);
    }

    /**
     * Salva i dati della persona nel database dopo aver validato i campi di input.
     */
    private void salvaPersona() {
        if (!validaNomeCognome(txtNome.getText())) {
            JOptionPane.showMessageDialog(this, "Nome non valido! Deve contenere solo lettere. Esempio: Mario", 
                                          "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!validaNomeCognome(txtCognome.getText())) {
            JOptionPane.showMessageDialog(this, "Cognome non valido! Deve contenere solo lettere. Esempio: Rossi", 
                                          "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!validaIndirizzo(txtIndirizzo.getText())) {
            JOptionPane.showMessageDialog(this, "Indirizzo non valido! Esempio: Via Roma, 10", 
                                          "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!validaTelefono(txtTelefono.getText())) {
            JOptionPane.showMessageDialog(this, "Numero di telefono non valido! Esempio: 3331234567", 
                                          "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (PersonaDAO.esisteTelefono(txtTelefono.getText())) {
            JOptionPane.showMessageDialog(this, "Il numero di telefono esiste già nel database!", 
                                          "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int eta;
        try {
            eta = Integer.parseInt(txtEta.getText());
            if (!validaEta(eta)) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Età non valida! Deve essere un numero tra 0 e 120.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Aggiorna l'oggetto Persona con i nuovi dati
        persona.setNome(txtNome.getText());
        persona.setCognome(txtCognome.getText());
        persona.setIndirizzo(txtIndirizzo.getText());
        persona.setTelefono(txtTelefono.getText());
        persona.setEta(eta);

        // **Salvataggio nel database**
        PersonaDAO personaDAO = new PersonaDAO();
        if (persona.getId() < 0) {
            int id = personaDAO.insertPersona(persona); // Ottenere ID dal database
            if (id > 0) {
                persona.setId(id);
                salvato = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Errore durante il salvataggio nel database!", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            personaDAO.updatePersona(persona);
            salvato = true;
            dispose();
        }
    }

    /**
     * Restituisce se la persona è stata salvata correttamente.
     *
     * @return true se i dati sono stati salvati, false altrimenti.
     */
    public boolean isSalvato() {
        return salvato;
    }

    /**
     * Restituisce l'oggetto Persona associato alla finestra di dialogo.
     *
     * @return L'oggetto Persona.
     */
    public Persona getPersona() {
        return this.persona;
    }

    // Metodi di validazione...

    /**
     * Valida il nome o il cognome.
     * Il nome e il cognome devono iniziare con una lettera maiuscola e contenere solo lettere.
     *
     * @param nome Il nome o cognome da validare.
     * @return true se il nome è valido, false altrimenti.
     */
    private boolean validaNomeCognome(String nome) {
        return nome.matches("^[A-Z][a-zàèéìòù']+(?:\\s[A-Z][a-zàèéìòù']+)*$");
    }

    /**
     * Valida il formato dell'indirizzo.
     * L'indirizzo deve iniziare con "Via", "Viale", "Piazza", "Corso" o "Strada" seguito da un nome e un numero civico.
     *
     * @param indirizzo L'indirizzo da validare.
     * @return true se l'indirizzo è valido, false altrimenti.
     */
    private boolean validaIndirizzo(String indirizzo) {
        return indirizzo.matches("^(Via|Viale|Piazza|Corso|Strada)\\s[A-Z][a-zA-Z\\s]+,\\s?\\d+$");
    }

    /**
     * Valida il numero di telefono.
     * Il numero di telefono deve contenere solo cifre e può includere un prefisso internazionale opzionale.
     *
     * @param telefono Il numero di telefono da validare.
     * @return true se il numero è valido, false altrimenti.
     */
    private boolean validaTelefono(String telefono) {
        return telefono.matches("^\\+?\\d{9,15}$");
    }

    /**
     * Valida l'età.
     * L'età deve essere un numero compreso tra 0 e 120.
     *
     * @param eta L'età da validare.
     * @return true se l'età è valida, false altrimenti.
     */
    private boolean validaEta(int eta) {
        return eta >= 0 && eta <= 120;
    }
}

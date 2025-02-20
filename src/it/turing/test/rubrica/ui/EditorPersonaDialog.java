package it.turing.test.rubrica.ui;

import it.turing.test.rubrica.model.Persona;
import javax.swing.*;
import java.awt.*;

public class EditorPersonaDialog extends JDialog {
    private JTextField txtNome, txtCognome, txtIndirizzo, txtTelefono, txtEta;
    private boolean salvato = false;
    private Persona persona;

    public EditorPersonaDialog(JFrame parent, Persona persona) {
        super(parent, "Editor Persona", true);
        this.persona = (persona != null) ? persona : new Persona("", "", "", "", 0);

        setLayout(new GridLayout(6, 2));
        setSize(300, 250);
        setLocationRelativeTo(parent);

        add(new JLabel("Nome:"));
        txtNome = new JTextField(this.persona.getNome());
        add(txtNome);

        add(new JLabel("Cognome:"));
        txtCognome = new JTextField(this.persona.getCognome());
        add(txtCognome);

        add(new JLabel("Indirizzo:"));
        txtIndirizzo = new JTextField(this.persona.getIndirizzo());
        add(txtIndirizzo);

        add(new JLabel("Telefono:"));
        txtTelefono = new JTextField(this.persona.getTelefono());
        add(txtTelefono);

        add(new JLabel("Età:"));
        txtEta = new JTextField(String.valueOf(this.persona.getEta()));
        add(txtEta);

        JButton btnSalva = new JButton("Salva");
        JButton btnAnnulla = new JButton("Annulla");
        add(btnSalva);
        add(btnAnnulla);

        btnSalva.addActionListener(e -> salvaPersona());
        btnAnnulla.addActionListener(e -> dispose());
    }

    private void salvaPersona() {
        persona.setNome(txtNome.getText());
        persona.setCognome(txtCognome.getText());
        persona.setIndirizzo(txtIndirizzo.getText());
        persona.setTelefono(txtTelefono.getText());
        persona.setEta(Integer.parseInt(txtEta.getText()));

        salvato = true;
        dispose();
    }

    public boolean isSalvato() {
        return salvato;
    }

    public Persona getPersona() {
        return persona;
    }
}

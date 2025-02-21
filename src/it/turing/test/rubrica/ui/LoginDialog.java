package it.turing.test.rubrica.ui;

import it.turing.test.rubrica.db.UtenteDAO;

import javax.swing.*;
import java.awt.*;

/**
 * Finestra di dialogo per il login dell'utente.
 */
public class LoginDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private boolean autenticato = false;

    /**
     * Costruttore della finestra di dialogo per il login.
     *
     * @param parent Il frame principale dell'applicazione.
     */
    public LoginDialog(JFrame parent) {
        super(parent, "Login", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Creazione del pannello centrale con il layout GridLayout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Username:"));
        txtUsername = new JTextField();
        panel.add(txtUsername);

        panel.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        // Aggiungere il pannello alla finestra
        add(panel, BorderLayout.CENTER);

        // Creazione della JToolBar e aggiunta dei pulsanti
        JToolBar toolBar = new JToolBar();
        JButton btnLogin = new JButton("Login",new ImageIcon("resources/login.png"));
        JButton btnAnnulla = new JButton("Annulla", new ImageIcon("resources/cancel.png"));

        // Aggiungere i pulsanti alla JToolBar
        toolBar.add(btnLogin);
        toolBar.add(btnAnnulla);

        // Aggiungere la JToolBar alla finestra
        add(toolBar, BorderLayout.SOUTH);

        // Impostazione delle azioni dei pulsanti
        btnLogin.addActionListener(_ -> autentica());
        btnAnnulla.addActionListener(_ -> System.exit(0));
    }

    /**
     * Autentica l'utente controllando username e password nel database.
     * Se le credenziali sono corrette, la finestra si chiude e l'utente è considerato autenticato.
     * Altrimenti, viene mostrato un messaggio di errore.
     */
    private void autentica() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (UtenteDAO.verificaCredenziali(username, password)) {
            autenticato = true;
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Login errato!", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Restituisce se l'utente è stato autenticato correttamente.
     *
     * @return true se il login ha avuto successo, false altrimenti.
     */
    public boolean isAutenticato() {
        return autenticato;
    }
}

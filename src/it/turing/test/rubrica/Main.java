package it.turing.test.rubrica;

import it.turing.test.rubrica.db.PersonaDAO;
import it.turing.test.rubrica.model.*;
import it.turing.test.rubrica.ui.LoginDialog;
import it.turing.test.rubrica.ui.MainFrame;

import javax.swing.*;

/**
 * Classe principale dell'applicazione della rubrica telefonica.
 * Gestisce l'avvio dell'applicazione, mostrando prima la finestra di login
 * e successivamente l'interfaccia principale se l'autenticazione ha successo.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginDialog loginDialog = new LoginDialog(null);
            loginDialog.setVisible(true);

            if (loginDialog.isAutenticato()) {
                // Recupera le persone dal database e le imposta nella rubrica
                PersonaDAO personaDAO = new PersonaDAO();
                Rubrica.setRubrica(personaDAO.getAllPersone());

                // Avvia la finestra principale
                new MainFrame();
            } else {
                System.exit(0); // Chiude l'applicazione se il login fallisce
            }
        });
    }
}

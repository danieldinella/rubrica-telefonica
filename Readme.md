# Rubrica - Gestione Contatti

## Requisiti
Per eseguire l'applicazione, è necessario avere installati i seguenti software:
- **Java** (versione 8 o superiore)
- **MySQL** (versione compatibile con il driver JDBC utilizzato)

## Configurazione
1. Modificare il file delle **credenziali del database** con le proprie impostazioni.
   - Assicurarsi che il database sia raggiungibile e che le credenziali siano corrette.

## Installazione ed Esecuzione
### 1. Installazione del Database
Prima di eseguire l'applicazione, è necessario installare il database:
```sh
java -jar installer.jar
```
Questo comando creerà le tabelle necessarie e configurerà il database per l'uso.

### 2. Avvio dell'Applicazione
Dopo aver installato il database, avviare l'applicazione principale:
```sh
java -jar rubrica.jar
```

## Accesso
L'applicazione è preconfigurata con un utente di test:
- **Username:** `admin`
- **Password:** `password123`

Dopo l'accesso, sarà possibile gestire i contatti direttamente dall'interfaccia utente.

## Supporto
Per problemi o richieste di supporto, contattare lo sviluppatore o aprire un'issue nel repository GitHub.


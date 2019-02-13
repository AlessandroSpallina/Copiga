1) PERSISTENZA
	- Classe DataSource per l'accesso alla base di dati
	- Classi DAO per la gestione della persistenza degli oggetti del dominio
	- Classe GeneratoreID per la generazione di ID identificativi per la persistenza dei dati
	- Operazioni CRUD (per le operazioni di inserimento implementate 2 versioni, una che utilizza 
	  il generatore di ID (GID) e una che utilizza le chiavi autogenerate dal dbms (AGK)) 
	  
2) DOMINIO
	- Introdotte le categorie di clienti 
	- Introdotta la classe GestoreClienti, che si occupa della gestione dei clienti e delle relative 
	  categorie. 
	- Classi ID per gestire i codici identificatori degli oggetti del dominio
	- Classe Prezzo per rappresentare l'importo dei tipi di colazione, componenti, ordini, ...
	- Introdotti una serie di casi d'uso aggiuntivi per la creazione, cancellazione, ricerca ed 
	  aggiornamento degli oggetti persistenti. 
	  
3) INTERFACCIA E CONTROLLO
	- Interfaccia testuale e comandi relativi, in particolare per la gestione delle operazioni CRUD
	- Menu' per la gestione dei tipi di colazione
	- Menu' per la gestione degli ordini
	- Menu' per la gestione delle categorie di clienti
	- Menu' per la gestione dei clienti
	- Menu' per la gestione delle descrizioni delle componenti
	- Menu' per la gestione delle modalita' di servizio
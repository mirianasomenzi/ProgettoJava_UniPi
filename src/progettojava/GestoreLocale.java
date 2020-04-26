package progettojava;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import progettojava.Affitto.Catering;
import progettojava.Affitto.CateringAnimazione;

//classe per la gestione del locale contenente il metodo main e il metodo per la stampa del menu
public class GestoreLocale { 
    //metodo main contenente il menu organizzato secondo le possibili scelte dell'utente e il richiamo alla classe GestorePrenotazioni
	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		boolean ok;
		int scelta;
		GestorePrenotazioni gestorePrenotazioni;
		do {
			gestorePrenotazioni = new GestorePrenotazioni (); 
			ok = true;
			try {
				do {
					stampaMenu();
					scelta = input.nextInt(); 	 
						switch (scelta) {
					    case 1:
					    	gestorePrenotazioni.aggiungiPrenotazione(); 
					    	break;
					    case 2:
					    	gestorePrenotazioni.visualizzaPrenotazioni(); 
					    	break;
					    case 3:
					    	gestorePrenotazioni.stampaCatering(true); 
					    	break;
					    case 4:
					    	gestorePrenotazioni.stampaCatering(false);
					    	break;
					    case 5:
						    gestorePrenotazioni.primaDataDisponibile();					
					       break;
					    case 6:
					    	gestorePrenotazioni.visualizzaCliente(); 
					    	break;
					    case 7:
					    	gestorePrenotazioni.eliminaPrenotazione();
					    	break;
					    case 8:
					    	gestorePrenotazioni.salvaPrenotazioni();
					    	break;
					    case 9: 
					    	gestorePrenotazioni.importaPrenotazioni();
					    	break;
					    case 10:
					    	System.out.println("Hai scelto di uscire dal programma, alla prossima!");
					    	break;
					    default:
					    	System.out.println("Valore non valido");
						}
				}
						while (scelta !=10);
				}
			    catch (InputMismatchException e) {
				System.out.println("Valore non valido...");
				input.nextLine();
				System.out.println ("...ritenta!");
				ok = false;
			    }
		} while (!ok);
	}
	// metodo per stampare il menu
	public static void stampaMenu() {
		System.out.println("Menu programma:\n"
				+ "1 = aggiungere nuove prenotazioni \n"
				+ "2 = visualizzare elenco prenotazioni in ordine di data \n"
				+ "3 = visualizzare prenotazioni che prevedono catering \n"
				+ "4 = visualizzare prenotazioni che prevedono animazione \n"
				+ "5 = visualizzare prima data disponibile a partire dal giorno corrente \n"
				+ "6 = visualizzare prenotazioni effettuate da cliente selezionato \n"
				+ "7 = eliminare prenotazioni \n"
				+ "8 = salvare prenotazioni \n"
				+ "9 = importare prenotazioni salvate \n"
				+ "10 = esci \n");
		System.out.println("Inserisci numero : ");
		
	}
}

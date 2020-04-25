package progettojava;
import java.util.Scanner;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.InputMismatchException; 
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import progettojava.Affitto.Catering;
import progettojava.Affitto.CateringAnimazione;

public class GestoreLocale {

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		boolean ok;
		int scelta;
		GestorePrenotazioni gestorePrenotazioni;
		//stampaMenu(); // stampare il menu 
		do {
			gestorePrenotazioni = new GestorePrenotazioni (); // creato oggetto gestorePrenotazioni
			stampaMenu();
			ok = true;
			try { // aggiunto il try catch
				scelta = input.nextInt(); //  acquisisco input	 
				while (scelta !=10)	{
				switch (scelta) {
					    case 1:
					    	gestorePrenotazioni.aggiungiPrenotazione(); //richiamo il metodo aggiungiPrenotazione che si trova in GestorePrenotazioni
					    	break;
					    case 2:
					    	gestorePrenotazioni.visualizzaPrenotazioni(); // richiamo il metodo visualizzaPrenotazioni 
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
					    	gestorePrenotazioni.visualizzaCliente(); // richiamo il metodo visualizzaCliente
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
					    	System.out.println("Funzione non ancora implementata");
				// default da togliere quando avremo implementato tutte le funzioni
					
					}
					
					stampaMenu();
					scelta = input.nextInt(); // prima di tornare nel while mi richiede cosa voglio fare stampando di nuovo il menu
				}
			}
			
			    catch (InputMismatchException e) {
				System.out.println("Valore non valido...");
				input.nextLine();
				System.out.println ("...ritenta!");
				ok = false;
			}
		} while (!ok);
	}
		
	 // creazione menu del programma  
	// \n serve per far andare a capo (new line)
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

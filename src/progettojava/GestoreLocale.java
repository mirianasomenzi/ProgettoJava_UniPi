package progettojava;
import java.util.Scanner;
import java.util.Vector;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import progettojava.Affitto.Affitto;
import progettojava.Affitto.Catering;
import progettojava.Affitto.CateringAnimazione;

public class GestoreLocale {

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		GestorePrenotazioni gestorePrenotazioni = new GestorePrenotazioni (); // creato oggetto gestorePrenotazioni
		stampaMenu(); // stampare il menu 
		int scelta = input.nextInt(); //  acquisisco input
		while (scelta !=8) {
			switch (scelta) {
			case 1:
				gestorePrenotazioni.aggiungiPrenotazione(); //richiamo il metodo aggiungiPrenotazione che si trova in GestorePrenotazioni
				break;
			//case 2:
			//case 3:
			//case 4:
			//case 5:
			//case 6:
			//case 7:
			//case 8: commentati perchè non ancora implementate queste funzioni
			default:
				System.out.println("Funzione non ancora implementata");
				// default da togliere quando avremo implementato tutte le funzioni
			}
		stampaMenu();
		scelta = input.nextInt(); // prima di tornare nel while mi richiede cosa voglio fare stampando di nuovo il menu
		}
	}
	 // creazione menu del programma  
	// \n serve per far andare a capo ( new line)
	public static void stampaMenu() {
		System.out.println("Menu programma:\n"
				+ "1 = aggiungere nuove prenotazioni \n"
				+ "2 = visualizzare elenco prenotazioni in ordine di data \n"
				+ "3 = visualizzare prenotazioni che prevedono catering \n"
				+ "4 = visualizzare prenotazioni che prevedono animazione \n"
				+ "5 = visualizzare prima data disponibile a partire dal giorno corrente \n"
				+ "6 = visualizzare prenotazioni effettuate da cliente selezionato \n"
				+ "7 = eliminare prenotazioni \n"
				+ "8 = esci \n");
		System.out.println("Inserisci numero : ");
		
	}
}

package progettojava;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.Vector;

import progettojava.Affitto.Affitto;
import progettojava.Affitto.Catering;
import progettojava.Affitto.CateringAnimazione;

public class GestorePrenotazioni { // creato una nuova classe dove poter gestire le prenotazioni (quindi le richieste che il prof fa
	                               // nella consegna es. aggiungere prenotazione, eliminarla, visualizzare ecc) perchè facendo 
	                               // riferimento a metodi non statici non potevano stare nel main che è static 
	
	private Vector <Date> calendario = new Vector<Date> ();// vettore di date, cambiato di posizione perchè in questo modo è accessibile da
	                                                       // tutti i metodi e non viene sovrascritto ogni volta, e private così è
	                                                       // modificabile solo dalla classe GestorePrenotazioni
	public GestorePrenotazioni() {
		
	}
	ciao // da cancellare   
	public void aggiungiPrenotazione () { // non static perchè fa riferimento a metodi non statici es. il calendario che cambia
			Scanner input = new Scanner (System.in);	    
			CateringAnimazione nuovocateringanimazione = new CateringAnimazione();
			Affitto nuovoaffitto = new Affitto();
			Catering nuovocatering = new Catering();
			Locale.setDefault(Locale.ITALIAN); // settare data in italiano 
			System.out.println("In che giorno vuoi organizzare il compleanno? (inserisci data gg/mm/yy) ");

			String stringaData = input.nextLine();
			//converto la stringa in un oggetto della classe Date
			//tutto questo ci conviene metterlo in un metodo ausiliario?
			//oppure in affitto che modifichiamo in Prenotazione e affitto dal momento che ogni prenotazione prevede un affitto?
			try {
				
				DateFormat data = DateFormat.getDateInstance(DateFormat.SHORT);
				// questo metodo permette di fare controlli sulla stringa inserita prima di convertirla
				data.setLenient(false);
				
				Date d = data.parse(stringaData);
			    
			    if (!calendario.contains(d)) { // se il calendario non contiene la data selezionata
			    	System.out.println("Data disponibile");
			    	calendario.add(d);
			    	 if (calendario.contains(d)) {
					    	System.out.println("Data prenotazione confermata!"); 
			    	 }	    	 
			    } 			    
			    else  
			    	System.out.println ("Data occupata");   
			}     
		
			catch (ParseException  e) {
				System.out.println ("Formato data non valido");
			}
		}
}

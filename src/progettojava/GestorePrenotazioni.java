package progettojava;

import java.text.DateFormat;
import java.util.Collections;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.Vector;

import progettojava.Affitto.Catering;
import progettojava.Affitto.CateringAnimazione;

public class GestorePrenotazioni { // creato una nuova classe dove poter gestire le prenotazioni (quindi le richieste che il prof fa
	                               // nella consegna es. aggiungere prenotazione, eliminarla, visualizzare ecc) perch� facendo 
	                               // riferimento a metodi non statici non potevano stare nel main che � static 
	
	private Vector <Date> calendario = new Vector<Date> ();// vettore di date, cambiato di posizione perch� in questo modo � accessibile da
	                                                       // tutti i metodi e non viene sovrascritto ogni volta, e private cos� �
	                                                       // modificabile solo dalla classe GestorePrenotazioni
	public GestorePrenotazioni() {
	
	}
	  
	public void aggiungiPrenotazione () { // non static perch� fa riferimento a metodi non statici es. il calendario che cambia
			Scanner input = new Scanner (System.in);	    
			CateringAnimazione nuovocateringanimazione = new CateringAnimazione();
			Catering nuovocatering = new Catering();
			Locale.setDefault(Locale.ITALIAN); // settare data in italiano, DA RIVEDERE
			System.out.println("In che giorno vuoi organizzare il compleanno? (inserisci data gg/mm/aa) ");
			//converto la stringa in un oggetto della classe Date
			try {
				String stringaData = input.nextLine();
				DateFormat data = DateFormat.getDateInstance(DateFormat.SHORT);
				// questo metodo permette di fare controlli sulla stringa inserita prima di convertirla
				data.setLenient(false);
				Date d = data.parse(stringaData);
			    
			    if (!calendario.contains(d)) { // se il calendario non contiene la data selezionata
			    	System.out.println("Data disponibile");
			    	calendario.add(d);
			    	 if (calendario.contains(d)) {
					    	System.out.println("Data prenotazione confermata!"); 
			    	        System.out.println ("Scegli una forma di prenotazione: \n" 
			    			 + " 1 = semplice affitto del locale \n"
			    			 + " 2 = affitto locale + catering \n"
			    			 + " 3 = affitto locale + catering + animazione \n");
			    	 }
			    	 int sceltaPrenotazione = input.nextInt();
			    	// while ( sceltaPrenotazione != 3) {
			    		 switch (sceltaPrenotazione ) {
			    		 case 1: 
			    			 System.out.println ("Prenotazione affitto effettuata!"); 
			    			 break; 
			    		 case 2: 
			    			 nuovocatering.gestioneCatering (); // faccio riferimento a un metodo non statico per cui dopo aver
			    			                                    // dichiarato l'oggetto alla riga 26/27 faccio oggetto.metodo();
			    			 break;
			    		 case 3 : 
			    			 nuovocateringanimazione.gestioneCateringAnimazione();
			    			 break;
			    		 default : System.out.println ("Valore errato"); 
			    		 // inserire eccezione se inserisce valore non valido
			    		 
			    		 }
			    } 			    
			    else  
			    	System.out.println ("Data occupata");   
			}     
		
			catch (ParseException  e) {
				System.out.println ("Formato data non valido");
				input.nextLine();
				System.out.println ("Ritenta...");
			}
		}
	public void visualizzaPrenotazioni () {
	
		Collections.sort(calendario); // metodo sort di Collections per poter ordinare le date presenti nel vettore 
		//System.out.println(calendario);
		for (Date d:calendario)
			System.out.println(d);
	}
	}


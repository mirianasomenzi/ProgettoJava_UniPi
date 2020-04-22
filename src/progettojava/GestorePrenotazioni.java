package progettojava;

import java.text.DateFormat;
import java.util.Collections;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Scanner;
//import java.util.Vector;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;//treeMap
import java.util.Set;
import java.util.Vector;

import progettojava.Affitto.Affitto;
import progettojava.Affitto.Catering;
import progettojava.Affitto.CateringAnimazione;

public class GestorePrenotazioni { // creato una nuova classe dove poter gestire le prenotazioni (quindi le richieste che il prof fa
	                               // nella consegna es. aggiungere prenotazione, eliminarla, visualizzare ecc) perch� facendo 
	                               // riferimento a metodi non statici non potevano stare nel main che � static 
	private HashMap <Date, String > calendario = new HashMap <Date, String> (); // usato HashMap per avere un dizionario composto da 
	// dalle date ( key) e valori (nomi)
	private HashMap <String, Vector> registro = new HashMap <String, Vector> ();
	private ArrayList <Date> dateOrdinate = new ArrayList <Date> (calendario.keySet ()); 
	Scanner input = new Scanner (System.in);
	
	public GestorePrenotazioni() {
		
		Locale.setDefault(Locale.ITALIAN); // settare data in italiano, DA RIVEDERE
	}
	  
	public void aggiungiPrenotazione () { // non static perch� fa riferimento a metodi non statici es. il calendario che cambia
				    
			try {  
				System.out.println ("Inserire nominativo: ");
				Scanner input = new Scanner (System.in);
				String nome = input.nextLine();	
				// acquisizione stringa e controllo della stessa
				System.out.println("In che giorno vuoi organizzare il compleanno? (inserisci data gg/mm/aa) ");
				String stringaData = input.nextLine();
				DateFormat data = DateFormat.getDateInstance(DateFormat.SHORT);
				data.setLenient(false);
				Date d = data.parse(stringaData);
				if (!calendario.containsKey(d)) { // se il calendario non contiene la data selezionata
			    	System.out.println("Data disponibile");
			    				
			    	// associo il nome alla data 
			    	calendario.put(d, nome);

	    	        System.out.println ("Scegli una forma di prenotazione: \n" 
	    			 + " 1 = semplice affitto del locale \n"
	    			 + " 2 = affitto locale + catering \n"
	    			 + " 3 = affitto locale + catering + animazione \n");
			    	 
	    	        /* prenotazione unica con forme diverse, quindi prenotazione � una
                     * variabile che include i vari tipi di prenotazione possibili
                     */
	    	         Affitto prenotazione = null; // creo variabile prenotazione di tipo affitto (superclasse)
			    	 int sceltaPrenotazione = input.nextInt();
			    		 switch (sceltaPrenotazione ) {
			    		 case 1: 
			    			 prenotazione = new Affitto (nome,d); //creo affitto passandogli come parametri il nome cliente e la data  	                                       
			    			 System.out.println ("Prenotazione affitto effettuata!"); 
			    			 break; 
			    		 case 2: 
			    			 prenotazione = new Catering (nome,d);
			    			 System.out.println("Prenotazione affitto con catering effettuata!");
			    			 break;
			    		 case 3 : 
			    			 prenotazione = new CateringAnimazione (nome,d);
			    			 System.out.println("Prenotazione affitto con catering e animazione effettuata!");
			    			 break;
			    		 default : System.out.println ("Valore errato"); 
			    		 // inserire eccezione se inserisce valore non valido
			    		 }
			    		 
			    		 /* esiste la persona nel registro?
			    		  * 1a non esiste persona -> creare vettore nuovo, inserire la prenotazione appena creata e inserirlo in
			    		  * registro
			    		  * 1b esiste -> prendere prenotazione da registro, aggiungere prenotazione appena creata, reinserire in
			    		  * registro
			    		*/ 
			    		if (!registro.containsKey(nome)) {
			    			Vector <Affitto> prenotazioniCliente = new Vector <Affitto>();
			    			prenotazioniCliente.add(prenotazione);
			    			registro.put(nome, prenotazioniCliente);
			    		} else {
			    			Vector <Affitto> prenotazioniEsistenti = registro.get(nome);
			    			prenotazioniEsistenti.add(prenotazione);
			    			registro.replace(nome, prenotazioniEsistenti);	
			    			}
			    		
			    		calendario.put(d, nome); // update del calendario 
			    } else  
			    	System.out.println ("Data occupata");   
			}     
		
			catch (ParseException  e) {
				System.out.println ("Formato data non valido");
				input.nextLine();
				System.out.println ("Ritenta...");
			}
		}
	
	// metodo per estrarre le date(key), unirle in una lista e stamparle ordinate  
	public void visualizzaPrenotazioni() {
		 ArrayList <Date> dateOrdinate = new ArrayList <Date> (calendario.keySet ()); 
		 Collections.sort(dateOrdinate);
		 for (Date d : dateOrdinate) // per ogni data d che sta dentro dateordinate
				System.out.println(d);
	}
	
	
	public void stampaCatering (boolean stampaAncheCatering) {
		//itero ogni entry (coppia chiave-valore) del registro 
		for(Entry<String, Vector> entry : registro.entrySet()) {
			// estraggo il vettore delle prenotazioni
			Vector<Affitto> prenotazioni = entry.getValue();
			// itero il vettore delle prenotazioni (una persona pu� averne pi� di una)
				for (Affitto prenotazione : prenotazioni) {
					if (prenotazione instanceof CateringAnimazione) {
						System.out.println("Catering con Animazione: \n" + prenotazione);	
					} else if (prenotazione instanceof Catering && stampaAncheCatering) { 
						System.out.println("Catering: \n"+ prenotazione);						
					}
				}
			
		}
	}
			
	public void visualizzaCliente () {
		System.out.println("Inserisci cliente: ");
		Scanner input = new Scanner (System.in);
		String cliente= input.nextLine();
		Vector <Affitto> prenotazioniEsistenti = cercaSubstringCliente(cliente);
		if (prenotazioniEsistenti!= null) {
			for(Affitto prenotazione : prenotazioniEsistenti) {
				System.out.println(prenotazione);
			}
		}
	}
	public Vector <Affitto> cercaSubstringCliente (String cliente) {
		// da questo metodo voglio ottenere il vettore delle prenotazioni
		if (registro.containsKey(cliente)) { // primo caso, registro contiene cliente
			return registro.get(cliente); //valore associato al cliente
		} else {
			Set <String> nomiRegistro = registro.keySet(); //prende tutte le chiavi, quindi i nomi, dal registro
		    for (String nomeEsatto : nomiRegistro) {    // per ogni stringa nomeEsatto presente nel registro
		    		if (nomeEsatto.contains(cliente)) { // se nomeEsatto contiene cliente
		    			return registro.get(nomeEsatto); // ritorna valore associato al cliente 
		    		}
		    }
		}
		System.out.println("Cliente inesistente");
		return null; //restituisce null quando non c'� corrispondenza
		}
	
	public void primaDataDisponibile (){
		try {
			
			Calendar c = Calendar.getInstance(); // calendar impostato al giorno di oggi
		    Date giorno = c.getTime(); //oggetto di tipo data che salva il giorno di oggi del calendar
			SimpleDateFormat convertitoreDataStringa = new SimpleDateFormat("dd/MM/yy");		
	    	Set <Date> insiemeDate = calendario.keySet();
	    	DateFormat convertitoreStringaData = DateFormat.getDateInstance(DateFormat.SHORT); 
			convertitoreStringaData.setLenient(false);
		    boolean trovato = false; //flag
		    while (!trovato) {
		    	String strGiornata = convertitoreDataStringa.format(c.getTime()); //creo oggetto stringa in formato dd/MM/yy della data
		    	giorno = convertitoreStringaData.parse(strGiornata); //creo oggetto data partendo dalla stringa 
		    	if (!insiemeDate.contains(giorno)) {
		    		System.out.println("Prima data disponibile: "+ strGiornata);
		    		trovato = true;	         
		    	} else 
		    		c.add(Calendar.DATE, 1);  // dice al calendario di aggiungere un giorno
		    }	
		} catch (ParseException e) {
			e.printStackTrace(); // d� la traccia dell'eccezione lanciata
			
		}
	}
}
		
				
		
			
			
	
	
	
	
	


package progettojava;

import java.text.DateFormat;
import java.util.Collections;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Scanner;
//import java.util.Vector;
import java.util.ArrayList;
import java.util.HashMap;//treeMap
import java.util.Set;
import java.util.Vector;

import progettojava.Affitto.Affitto;
import progettojava.Affitto.Catering;
import progettojava.Affitto.CateringAnimazione;

public class GestorePrenotazioni { // creato una nuova classe dove poter gestire le prenotazioni (quindi le richieste che il prof fa
	                               // nella consegna es. aggiungere prenotazione, eliminarla, visualizzare ecc) perchè facendo 
	                               // riferimento a metodi non statici non potevano stare nel main che è static 
	
	//private Vector <Date> calendario = new Vector<Date> ();// vettore di date, cambiato di posizione perchè in questo modo è accessibile da
	                                                    // tutti i metodi e non viene sovrascritto ogni volta, e private così è
	                                                       // modificabile solo dalla classe GestorePrenotazioni
	private HashMap <Date, String > calendario = new HashMap <Date, String> (); // usato HashMap per avere un dizionario composto da 
	// dalle date ( key) e valori (nomi)
	private HashMap <String, Vector> registro = new HashMap <String, Vector> ();
	
	public GestorePrenotazioni() {
		Locale.setDefault(Locale.ITALIAN); // settare data in italiano, DA RIVEDERE
	}
	  
	public void aggiungiPrenotazione () { // non static perchè fa riferimento a metodi non statici es. il calendario che cambia
			Scanner input = new Scanner (System.in);	    
			try {  
				System.out.println ("Inserire nominativo: ");
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
			    	 
	    	        /* prenotazione unica con forme diverse, quindi prenotazione è una
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
			    			//if (registro.containsKey(nome)) {
			    			Vector <Affitto> prenotazioniEsistenti = registro.get(nome);
			    			prenotazioniEsistenti.add(prenotazione);
			    			registro.replace(nome, prenotazioniEsistenti);	
			    			}		

			    }else  
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
		 Set <Date> keyset= calendario.keySet();
		 ArrayList <Date> dateordinate = new ArrayList <Date> (calendario.keySet ()); 
		 Collections.sort(dateordinate);
		 for (Date d : dateordinate) // per ogni data d che sta dentro dateordinate
				System.out.println(d);
	}
	
	public void stampaCatering () {
		//itero ogni entry (coppia chiave-valore) del registro 
		for(Entry<String, Vector> entry : registro.entrySet()) {
			// estraggo il vettore delle prenotazioni
			Vector<Affitto> prenotazioni = entry.getValue();
			// itero il vettore delle prenotazioni (una persona può averne più di una)
				for (Affitto prenotazione : prenotazioni) {
					if (prenotazione instanceof CateringAnimazione) {
						System.out.println("La seguente prenotazione è di tipo CateringAnimazione: " + prenotazione.getData());	
					} else if (prenotazione instanceof Catering) {
						System.out.println("La seguente prenotazione è di tipo Catering: "+ prenotazione.getData());						
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
			System.out.println(prenotazione.getData());
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
		return null; //restituisce null quando non c'è corrispondenza
		}
	}
	
	
	


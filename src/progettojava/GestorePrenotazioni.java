package progettojava;

import java.io.*;
import java.text.DateFormat;
import java.util.Collections;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;
import progettojava.Affitto.Affitto;
import progettojava.Affitto.Catering;
import progettojava.Affitto.CateringAnimazione;

public class GestorePrenotazioni  implements Serializable { // creato una nuova classe dove poter gestire le prenotazioni (quindi le richieste che il prof fa
	static final long serialVersionUID = 1;                 // nella consegna es. aggiungere prenotazione, eliminarla, visualizzare ecc) perchè facendo 
	                                                       // riferimento a metodi non statici non potevano stare nel main che è static 
	private HashMap <Date, String > calendario = new HashMap <Date, String> (); // usato HashMap per avere un dizionario composto da 
	// dalle date ( key) e valori (nomi)
	private HashMap <String, Vector> registro = new HashMap <String, Vector> ();
	//private ArrayList <Date> dateOrdinate = new ArrayList <Date> (calendario.keySet ()); 
	Scanner input = new Scanner (System.in);
	
	public GestorePrenotazioni() {
		
		Locale.setDefault(Locale.ITALIAN); // settare data in italiano, DA RIVEDERE
	}
	  
	public void aggiungiPrenotazione () { // non static perchè fa riferimento a metodi non statici es. il calendario che cambia
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
				//input.nextLine();
				System.out.println ("Ritenta...");
			}
		}
	
	// metodo per estrarre le date(key), unirle in una lista e stamparle ordinate  
	public void visualizzaPrenotazioni() {
		 Vector <Date> dateOrdinate = new Vector <Date> (calendario.keySet ()); 
		 SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy");
		 Collections.sort(dateOrdinate);
		 if (!dateOrdinate.isEmpty()) {
			System.out.println ("Ecco l'elenco delle prenotazioni in ordine di data: ");
			for (Date d : dateOrdinate) {// per ogni data d che sta dentro dateordinate
				 System.out.println(dateFormat.format(d));
			}
		 } else 
			 System.out.println ("Non sono ancora presenti prenotazioni!");
	}
	
	
	public void stampaCatering (boolean stampaAncheCatering) {
		//itero ogni entry (coppia chiave-valore) del registro 
		for(Entry<String, Vector> entry : registro.entrySet()) {
			// estraggo il vettore delle prenotazioni
			Vector<Affitto> prenotazioni = entry.getValue();
			// itero il vettore delle prenotazioni (una persona può averne più di una)
				for (Affitto prenotazione : prenotazioni) {
					if (prenotazione instanceof CateringAnimazione) {
						System.out.println("Catering con Animazione: \n" + prenotazione);	
					} else if (prenotazione instanceof Catering && stampaAncheCatering) { 
						System.out.println("Catering: \n"+ prenotazione);						
					}
				}
			
		}
	}
	
	public void eliminaPrenotazione() {
		boolean ok = true;
		do {
			System.out.println ("Quale prenotazione vuoi eliminare?(Inserisci data formato dd/mm/aa");
			ok = true;
			try {
				String strData = input.nextLine();
				DateFormat convertitoreStringaData= DateFormat.getDateInstance(DateFormat.SHORT);
				convertitoreStringaData.setLenient(false);
				Date dataEliminata = convertitoreStringaData.parse(strData);
				Set <Date> insiemeDate = calendario.keySet();
				if (calendario.containsKey(dataEliminata)) {
					String nome = calendario.get(dataEliminata); // prendo anche nome così ho la key per registro
					Vector<Affitto> prenotazioni = registro.get(nome); // estraggo il vettore delle prenotazioni
					for (Affitto prenotazione: prenotazioni) {
						if (prenotazione.getDate().equals(dataEliminata)) {// prima esegue prenotazione.getDate e col contenuto ossia una data fa il confronto con quella da eliminare
							prenotazioni.remove(prenotazione);
							registro.replace(nome, prenotazioni);
							calendario.remove(dataEliminata);
							System.out.println ("Data eliminata!");
							return;
						}	
					}
				} else 
					System.out.println ("Data non presente!");
			} catch (ParseException e) {
			System.out.println ("Formato data non valido!\n" + "Ritenta...");
			//input.nextLine();
			ok = false;
			}	
		} while	(!ok);	
	}
		
		
	
			
	public void visualizzaCliente () {
		System.out.println("Inserisci cliente: ");
		//Scanner input = new Scanner (System.in);
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
		return null; //restituisce null quando non c'è corrispondenza
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
			e.printStackTrace(); // dà la traccia dell'eccezione lanciata
			
		}
	}
	
	public void salvaPrenotazioni () {
		try {
			ObjectOutputStream out = new ObjectOutputStream ( new BufferedOutputStream( new FileOutputStream("prenotazioni.dat")));
			out.writeObject(calendario);
			out.writeObject(registro);
			out.close();	
			
			
		} catch (IOException e) {
			System.out.println ("Errore di I/O");
			System.out.println(e);
		}
		System.out.println("Prenotazioni salvate!");
		}
	
	public void importaPrenotazioni () {
		HashMap <Date,String> h2 = null;
		HashMap <String, Vector> h3 = null;
		try {
			ObjectInputStream in = new ObjectInputStream ( new BufferedInputStream ( new FileInputStream("prenotazioni.dat")));
			h2 = (HashMap) in.readObject();
			h3 = (HashMap) in.readObject();
			in.close();
		} catch (ClassNotFoundException e) {
			System.out.println("PROBLEMA (manca oggetto nel file)");
			System.out.println(e);
		} catch (IOException e) {
			System.out.println("ERRORE di I/O");
			System.out.println(e);
		}
		System.out.println(h3);
		}
		
	}

		
				
		
			
			
	
	
	
	
	


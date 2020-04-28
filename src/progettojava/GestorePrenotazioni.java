package progettojava;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import progettojava.Affitto.Affitto;
import progettojava.Affitto.Catering;
import progettojava.Affitto.CateringAnimazione;
// classe per la gestione delle prenotazioni 
// classe che implementa serializable per la serializzazione
public class GestorePrenotazioni  implements Serializable { 
	static final long serialVersionUID = 1; 
	//HashMap creato per avere un dizionario composto dalle date (come key) e dai nomi ( come valori)
	private HashMap <Date, String > calendario = new HashMap <Date, String> ();  
	//HashMap creato per avere un dizionario di prenotazioni (come valori) associate al cliente (come key)
	private HashMap <String, Vector> registro = new HashMap <String, Vector> ();
	
	//costruttore di default 

	// metodo per aggiungere una prenotazione
	public void aggiungiPrenotazione () { 
		try {
			System.out.println ("Inserire nominativo: ");
			Scanner input = new Scanner (System.in);
			String nome = input.nextLine();	
			System.out.println("In che giorno vuoi organizzare il compleanno? (inserisci data gg/mm/aa) ");
			String stringaData = input.nextLine();
			//scelta del formato della data
			DateFormat data = DateFormat.getDateInstance(DateFormat.SHORT);
			//per controllare la validità delle date
			data.setLenient(false);
			//per convertire la stringa in input in oggetto di tipo Date
			Date d = data.parse(stringaData);
			//se il calendario non contiene la data selezionata
			if (!calendario.containsKey(d)) { 
				System.out.println("Data disponibile");
				boolean ok;
				//variabile prenotazione di tipo affitto che include le possibili prenotazioni    
				Affitto prenotazione = null; 
				// associo il nome alla data 
				calendario.put(d, nome);
			do {
				ok = true;
				System.out.println ("Scegli una forma di prenotazione: \n" 
	    			 + " 1 = semplice affitto del locale \n"
	    			 + " 2 = affitto locale + catering \n"
	    			 + " 3 = affitto locale + catering + animazione \n");
			    	
				System.out.println("Inserisci numero");
				int sceltaPrenotazione = input.nextInt();
				switch (sceltaPrenotazione ) {
			    		 case 1: 
			    			 prenotazione = new Affitto (nome,d);  	                                       
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
			    		 ok = false;
			    		 }
			} while (!ok);
			if (!ok) throw new InputMismatchException();
			
				        //se la persona non è presente nel registro
			    		if (!registro.containsKey(nome)) {
			    			//creazione nuovo vettore dove inserire la prenotazione appena creata
			    			Vector <Affitto> prenotazioniCliente = new Vector <Affitto>();
			    			prenotazioniCliente.add(prenotazione);
			    			//inserire prenotazione appena creata in registro associandola al nome
			    			registro.put(nome, prenotazioniCliente);
			    		} else {
			    			//se la persona è già presente, prendere la prenotazione a lei associata dal registro
			    			Vector <Affitto> prenotazioniEsistenti = registro.get(nome);
			    			//aggiungere la prenotazione appena creata
			    			prenotazioniEsistenti.add(prenotazione);
			    			// reinserire la prenotazione modificata nel registro
			    			registro.replace(nome, prenotazioniEsistenti);	
			    			}
			    		//update anche dell'HashMap calendario
			    		calendario.put(d, nome); 
			    } else  
			    	System.out.println ("Data occupata");   
			}     
		
			catch (ParseException  e) {
				System.out.println ("Formato data non valido... ritenta");
			}
		}
	
	// metodo per visualizzare in maniera ordinata le prenotazioni  
	public void visualizzaPrenotazioni() {
		//estrarre le date (key) da calendario e inserirle in un vettore di tipo Date 
		 Vector <Date> dateOrdinate = new Vector <Date> (calendario.keySet ()); 
		 SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy");
		//ordinare le date 
		 Collections.sort(dateOrdinate);
		 if (!dateOrdinate.isEmpty()) {
			System.out.println ("Ecco l'elenco delle prenotazioni in ordine di data: ");
			for (Date d : dateOrdinate) {
				 System.out.println(dateFormat.format(d));
			}
		 } else 
			 System.out.println ("Non sono ancora presenti prenotazioni!");
	}
	// metodo per stampare le prenotazioni di tipo Catering e CateringAnimazione
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
					//}  
						//System.out.println("Non sono ancora presenti prenotazioni di questo tipo");
					}
					}
			
		}
	}
	//metodo per eliminare una prenotazione
	public void eliminaPrenotazione() {
		boolean ok = true;
		Scanner input = new Scanner (System.in);
		do {
			System.out.println ("Quale prenotazione vuoi eliminare?(Inserisci data formato dd/mm/aa)");
			ok = true;
			try {
				String strData = input.nextLine();
				DateFormat convertitoreStringaData= DateFormat.getDateInstance(DateFormat.SHORT);
				convertitoreStringaData.setLenient(false);
				Date dataEliminata = convertitoreStringaData.parse(strData);
				Set <Date> insiemeDate = calendario.keySet();
				if (calendario.containsKey(dataEliminata)) {
					//prendo anche il nome così da avere la key per l'hashmap registro
					String nome = calendario.get(dataEliminata); 
					//estraggo il vettore delle prenotazioni da registo
					Vector<Affitto> prenotazioni = registro.get(nome); 
					for (Affitto prenotazione: prenotazioni) {
						//prima viene eseguito .getDate e confronta la data ottenuta con la data da eliminare 
						if (prenotazione.getDate().equals(dataEliminata)) {
							prenotazioni.remove(prenotazione);
							registro.replace(nome, prenotazioni);
							//rimuove la data anche da calendario
							calendario.remove(dataEliminata);
							System.out.println ("Data eliminata!");
							return;
						}	
					}
				} else 
					System.out.println ("Data non presente!");
			} catch (ParseException e) {
			System.out.println ("Formato data non valido!\n" + "Ritenta...");
			ok = false;
			}	
		} while	(!ok);	
	}
    // metodo per visualizzare la prenotazione partendo dal nome del cliente
	public void visualizzaCliente () {
		try {
			System.out.println("Inserisci cliente: ");
			Scanner input = new Scanner (System.in);
			String cliente= input.nextLine();
			// richiamiamo il metodo cercaSubstringCliente
			Vector <Affitto> prenotazioniEsistenti = cercaSubstringCliente(cliente);
			if (prenotazioniEsistenti!= null) {
				for(Affitto prenotazione : prenotazioniEsistenti) {
					System.out.println(prenotazione);
				}
			}
		} catch (InputMismatchException e) {
		}
	}
	
	//metodo per visualizzare le prenotazioni partendo da una porzione di nome del cliente
	public Vector <Affitto> cercaSubstringCliente (String cliente) {
		//se il registro contiene cliente
		if (registro.containsKey(cliente)) { 
            //prendo valore associato al cliente
			return registro.get(cliente); 
		} else {
			//se registro non contiene cliente prendo tutti i nomi (key)
			Set <String> nomiRegistro = registro.keySet(); 
			//per ogni stringa nomeEsatto presente in registro
		    for (String nomeEsatto : nomiRegistro) { 
		    	//se nomeEsatto contiene cliente
		    		if (nomeEsatto.contains(cliente)) { 
		    			//ritorna valore associato al cliente 
		    			return registro.get(nomeEsatto); 
		    		}
		    }
		}
		System.out.println("Cliente inesistente");
		return null;
		}
	// metodo per visualizzare la prima data disponibile a partire dal giorno corrente 
	public void primaDataDisponibile (){
		try {
			//calendar impostato al giorno corrente 
			Calendar c = Calendar.getInstance(); 
			//oggetto di tipo date che salva il giorno corrente 
		    Date giorno = c.getTime(); 
			SimpleDateFormat convertitoreDataStringa = new SimpleDateFormat("dd/MM/yy");		
	    	Set <Date> insiemeDate = calendario.keySet();
	    	DateFormat convertitoreStringaData = DateFormat.getDateInstance(DateFormat.SHORT); 
			convertitoreStringaData.setLenient(false);
		    boolean trovato = false; 
		    while (!trovato) {
		    	//creo oggetto stringa in formato dd/MM/yy corrispondente alla data
		    	String strGiornata = convertitoreDataStringa.format(c.getTime()); 
		    	//creo oggetto data partendo dalla stringa 
		    	giorno = convertitoreStringaData.parse(strGiornata); 
		    	if (!insiemeDate.contains(giorno)) {
		    		System.out.println("Prima data disponibile: "+ strGiornata);
		    		trovato = true;	         
		    	} else 
		    		//aggiunge un giorno al calendario
		    		c.add(Calendar.DATE, 1);  
		    }	
		} catch (ParseException e) {
			System.out.println ("Formato data non valido");
		}
	}
	//metodo per salvare le prenotazioni 
	public void salvaPrenotazioni () {
		try {
			ObjectOutputStream out = new ObjectOutputStream ( new BufferedOutputStream( new FileOutputStream("prenotazioni.dat")));
			//salviamo entrambi gli hashmap
			out.writeObject(calendario);
			out.writeObject(registro);
			out.close();		
		} catch (IOException e) {
			System.out.println ("Errore di I/O");
			System.out.println(e);
		}
		System.out.println("Prenotazioni salvate!");
		}
	//metodo per importare le prenotazioni salvate 
	public void importaPrenotazioni () {
		try {
			ObjectInputStream in = new ObjectInputStream ( new BufferedInputStream ( new FileInputStream("prenotazioni.dat")));
			//importo le prenotazioni salvate aggiornando entrambi gli hashmap
			this.calendario = (HashMap) in.readObject();
			this.registro = (HashMap) in.readObject();
			in.close();
			
		} catch (ClassNotFoundException e) {
			System.out.println("PROBLEMA (manca oggetto nel file)");
			System.out.println(e);
		} catch (InvalidClassException e) {
		} catch (IOException e) {
			System.out.println("ERRORE di I/O");
			System.out.println(e);
		}
		System.out.println(registro);
		}
		
	}

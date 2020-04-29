package progettojava.Affitto;
import java.util.*;
import java.text.SimpleDateFormat;
//classe contenente la prenotazione del locale con opzione catering e animazione
// CateringAnimazione sottoclasse della classe Catering
public class CateringAnimazione extends Catering {
	//creazione variabile per salvare i tipi di animazione
	String tipoAnimazione;

	//costruttore
	public CateringAnimazione(String nome, Date d) {
		super (nome, d);
		Scanner input = new Scanner (System.in);

		boolean ok;
		do {
			System.out.println("Che tipo di animazione desideri?\n"
				+ " 1 = organizzazione di giochi \n"
				+ " 2 = spettacolo di magia \n"
				+ " 3 = spettacolo di burattini \n " +
					"Inserisci numero: ");
		    ok = true;

			try {
				int sceltaAnimazione = input.nextInt();
				switch (sceltaAnimazione) {
				    case 1:
				    	//utilizzato this. in modo da salvare le scelte nella variabile apposita
				    	//utilizzo di this. perchè tipoAnimazione è dell'oggetto
				    	this.tipoAnimazione = "organizzazione di giochi"; 
				    	break;
				    case 2:
				    	this.tipoAnimazione= "spettacolo di magia";
				    	break;
				    case 3:
					   this.tipoAnimazione = "spettacolo di burattini";
					   break;
				    default:
				    	ok = false;
				    	System.out.println("Scelta non valida");
				}
			} catch ( InputMismatchException e) {
				System.out.println ("Valore non valido... ritenta: ");
				input.nextLine();
				ok = false;
			}	
		} while (!ok);
	}

	// metodo per stampare il tipo di animazione
	public String getTipoAnimazione() {
		return this.tipoAnimazione;
	}

    // metodo per stampare i dati aggiuntivi del tipo di prenotazione
	public String toString () {
		String string = "Data prenotazione: "+ this.getData() + "\nNome cliente: " + this.cliente + "\nNumero bambini: "+ this.bambini + "\nTipo animazione: "+ this.tipoAnimazione+"\n---";
		return string;		 
	}
}
	

		
	

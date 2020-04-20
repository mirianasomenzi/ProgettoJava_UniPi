package progettojava.Affitto;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import  java.util.InputMismatchException;
public class CateringAnimazione extends Catering {
	String tipoAnimazione; // creo variabile per salvare i tipi di animazione
	
	public CateringAnimazione(String nome, Date d) {
		// usare super per richiamare il costruttore della superclasse
		super (nome, d);
		// aggiunto la domanda sul numero di bambini presenti essendo sia catering sia animazione 
		Scanner input= new Scanner (System.in);
		int sceltaAnimazione;
		boolean ok;
		do {
			System.out.println("Che tipo di animazione desideri?\n"
				+ " 1 = organizzazione di giochi \n"
				+ " 2 = spettacolo di magia \n"
				+ " 3 = spettacolo di burattini \n");
			System.out.println ("Inserisci numero");
		    ok = true;
			try {
				sceltaAnimazione = input.nextInt();
				switch (sceltaAnimazione) {
				    case 1:
				    	this.tipoAnimazione = "organizzazione di giochi"; // this perchè tipoAnimazione è dell'oggetto, ho salvato la
					                                                  // scelta numero 1 nella variabile apposita
				    	break;
				    case 2:
				    	this.tipoAnimazione= "spettacolo di magia";
				    	break;
				    case 3:
					   this.tipoAnimazione = "spettacolo di burattini";
					   break;
				    default:
				    	ok= false;
				    	System.out.println("Scelta non valida");
				}
			} catch ( InputMismatchException e) {
				input.nextLine();
				System.out.println ("Valore non valido... \n" + "...ritenta!");
				ok = false;
			}	
		} while (!ok);
	}	
 // metodo per stampare i dati aggiuntivi del tipo di prenotazione	
	public String toString () {
		SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy");
		String string = "Data prenotazione: "+ dateFormat.format(this.data) + "\nNumero bambini: "+ this.bambini + "\nTipo animazione: "+ this.tipoAnimazione+"\n---";
		return string;		 
	}
}
	

		
	

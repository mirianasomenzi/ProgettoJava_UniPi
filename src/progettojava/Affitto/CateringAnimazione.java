package progettojava.Affitto;
import java.util.Scanner;
import java.util.Date;
import  java.util.InputMismatchException;
public class CateringAnimazione extends Catering {
	String tipoAnimazione; // creo variabile per salvare i tipi di animazione
	
	public CateringAnimazione(String nome, Date d) {
		// usare super per richiamare il costruttore della superclasse
		super (nome, d);
		//System.out.println("Quanti bambini sono previsti?");
		//super.bambini = input.nextInt(); // super.bambini perchè richiamo bambini dalla super classe Catering 
		// aggiunto la domanda sul numero di bambini presenti essendo sia catering sia animazione 
		Scanner input= new Scanner (System.in);
		System.out.println("Che tipo di animazione desideri?\n"
				+ " 1 = organizzazione di giochi \n"
				+ " 2 = spettacolo di magia \n"
				+ " 3 = spettacolo di burattini \n"); // inserire errore 
		try {
			int sceltaAnimazione = input.nextInt();
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
					System.out.println("Scelta non valida");
			}
			
			
		} catch ( InputMismatchException e) {
			input.nextLine();
			System.out.println ("Valore non valido... \n" + "...ritenta!");
			}
		}
 // metodo per stampare i dati aggiuntivi del tipo di prenotazione	
	public String toString () {
		String string = "Data della prenotazione: "+this.data + "Numero di bambini: "+ this.bambini + "Tipo di animazione: "+ this.tipoAnimazione;
		return string;		
	}
}
	

		
	

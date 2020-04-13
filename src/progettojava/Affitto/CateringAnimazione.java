package progettojava.Affitto;
import java.util.Scanner;
import  java.util.InputMismatchException;
public class CateringAnimazione extends Catering {

	public CateringAnimazione() {
		// usare super per richiamare il costruttore della superclasse
		
	}
	
	public void gestioneCateringAnimazione() { //tolto static e messa la lettera minuscola
		System.out.println("Quanti bambini sono previsti?");
		Scanner input= new Scanner (System.in);
		super.bambini = input.nextInt(); // super.bambini perchè richiamo bambini dalla super classe Catering 
		// aggiunto la domanda sul numero di bambini presenti essendo sia catering sia animazione 
		
		System.out.println("Che tipo di animazione desideri?\n"
				+ " 1 = organizzazione di giochi \n"
				+ " 2 = spettacolo di magia \n"
				+ " 3 = spettacolo di burattini \n"); // inserire errore 
		try {
			int SceltaAnimazione = input.nextInt();
			if ((SceltaAnimazione <1) || (SceltaAnimazione > 3)) {
			System.out.println ("Numero non valido"); 
		    } else {
		    	System.out.println ("Prenotazione effettuata!");
		    }
		} catch ( InputMismatchException e) {
			input.nextLine();
			System.out.println ("Valore non valido... \n" + "...ritenta!");
			}
			
	}
}	

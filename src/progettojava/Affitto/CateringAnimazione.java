package progettojava.Affitto;
import java.util.Scanner;
import  java.util.InputMismatchException;
public class CateringAnimazione extends Catering {
	public CateringAnimazione() {
		// usare super per richiamare il costruttore della superclasse
		
	}
	
	public static void GestioneCateringAnimazione() {
		System.out.println("Che tipo di animazione desideri?\n"
				+ " 1 = organizzazione di giochi \n"
				+ " 2 = spettacolo di magia \n"
				+ " 3 = spettacolo di burattini \n"); // inserire errore 
		Scanner input = new Scanner(System.in);
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

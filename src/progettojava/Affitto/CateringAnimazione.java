package progettojava.Affitto;
import java.util.Scanner;
public class CateringAnimazione extends Catering {
	public CateringAnimazione() {
		// usare super per richiamare il costruttore della superclasse
		
	}
	
	public static int GestioneCateringAnimazione() {
		System.out.println("Che tipo di animazione desideri?\n"
				+ " 1 = organizzazione di giochi \n"
				+ " 2 = spettacolo di magia \n"
				+ " 3 = spettacolo di burattini \n"); // inserire errore 
		Scanner input = new Scanner(System.in);
		int SceltaAnimazione = input.nextInt();
		return SceltaAnimazione;
	}

}

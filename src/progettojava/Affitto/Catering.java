package progettojava.Affitto;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Catering  {
	private int bambini;
    //costruttore
	public Catering () {
		
		
	}
	public static void GestioneCatering() {
		
	System.out.println ("Quanti bambini sono previsti?");
	Scanner input= new Scanner (System.in);
	try {
		int bambini = input.nextInt();
		System.out.println ("Prenotazione effettuata!");
	//return bambini;
	} catch (InputMismatchException e) {
		System.out.println ("Valore non valido...\n" + "ritenta");
	}
	}
	

}

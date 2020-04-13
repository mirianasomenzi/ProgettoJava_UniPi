package progettojava.Affitto;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Catering  {
	protected int bambini; // cambiato da private a protected così la sottoclasse CateringAnimazione può accedere
    //costruttore
	public Catering () {
		
		
	}
	public void gestioneCatering() { //tolto static e messa la minuscola
		
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

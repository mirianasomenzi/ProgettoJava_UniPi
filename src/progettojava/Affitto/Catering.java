package progettojava.Affitto;
import java.util.Scanner;
public class Catering  {
	private int bambini;
    //costruttore
	public Catering () {
		
		
	}
	public static int GestioneCatering() {
		
	System.out.println ("Quanti bambini sono previsti?");
	Scanner input= new Scanner (System.in);
	int bambini = input.nextInt();
	return bambini;
	}
	

}

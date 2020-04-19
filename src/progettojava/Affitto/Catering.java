package progettojava.Affitto;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Catering extends Affitto {
	protected int bambini; // cambiato da private a protected così la sottoclasse CateringAnimazione può accedere
    
	//costruttore
	public Catering (String nome, Date d) { // passo string nome da GestorePrenotazioni al costruttore
		super(nome, d); // ho richiamato il metodo della superclasse Affitto per salvare il nome
		System.out.println ("Quanti bambini sono previsti?");
		Scanner input= new Scanner (System.in);
		try {
			this.bambini = input.nextInt(); // this. perchè faccio riferimento alla variabile dell'oggetto e, in questo modo, salvo 
			                                // il numero di bambini, sennò andava perso
			System.out.println ("Prenotazione effettuata!");
		} catch (InputMismatchException e) {
			System.out.println ("Valore non valido...\n" + "ritenta");
		  }
		
	}
	 // metodo per stampare i dati aggiuntivi del tipo di prenotazione	
		public String toString () {
			String string = "Data della prenotazione: "+this.data + "Numero di bambini: "+ this.bambini;
			return string;		
		}
}
	



package progettojava.Affitto;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Catering extends Affitto {
	protected int bambini; // cambiato da private a protected cos� la sottoclasse CateringAnimazione pu� accedere
    //costruttore
	public Catering (String nome, Date d) { // passo string nome da GestorePrenotazioni al costruttore
		super(nome, d);
		// ho richiamato il metodo della superclasse Affitto per salvare il nome
		boolean ok;
		do {
			System.out.println ("Quanti bambini sono previsti?");
			Scanner input= new Scanner (System.in);
			ok = true;
			try {
				this.bambini = input.nextInt(); // this. perch� faccio riferimento alla variabile dell'oggetto e, in questo modo, salvo 
			
			                                // il numero di bambini, senn� andava perso
			   
		    }
			catch (InputMismatchException e) {
				System.out.println ("Valore non valido...");
				input.nextLine();
				System.out.println ("ritenta!");
				ok = false;
			}
		} while (!ok);	
	}
	 // metodo per stampare i dati aggiuntivi del tipo di prenotazione	
	public String toString () {
		SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy");
		String string = "Data prenotazione: "+ dateFormat.format(this.data) +"\nNumero bambini: "+ this.bambini+"\n---";
		return string;		
		}
}
	



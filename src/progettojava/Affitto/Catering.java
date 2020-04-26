package progettojava.Affitto;
import java.text.SimpleDateFormat;
import java.util.*;
//classe contenente la prenotazione del locale con opzione catering 
//Catering è sottoclasse della classe Affitto
public class Catering extends Affitto {
	//protected cosicchè la sottoclasse CateringAnimazione possa accedere alla variabile 
	protected int bambini; 
    //costruttore
	public Catering (String nome, Date d) { 
		//richiamo il metodo della superclasse Affitto per salvare le informazioni relative alla prenotazione 
		super(nome, d);
		boolean ok;
		do {
			System.out.println ("Quanti bambini sono previsti?");
			Scanner input= new Scanner (System.in);
			ok = true;
			try {
				//utilizzato this. perchè facciamo riferimento alla variabile dell'oggetto in modo da salvare anche il n. di bambini
				this.bambini = input.nextInt(); 
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
	



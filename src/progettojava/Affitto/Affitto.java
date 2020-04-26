// creato un nuovo package contenente la classe Affitto e le sue sottoclassi 
package progettojava.Affitto;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
//classe contenente la prenotazione semplice del locale (data della prenotazione e nome del cliente)
//la classe affitto implementa Serializable per la serializzazione 
public class Affitto implements Serializable {
		static final long serialVersionUID = 1;
	//protected cosicchè le variabili possano essere utilizzabili nel package che contiene la classe e le sue sottoclassi
	protected String cliente; 
	protected Date data;
	// private cosìcchè l'oggetto possa essere utilizzato solo all'interno della classe
	private Object dateFormat;
	//costruttore
	public Affitto(String nome, Date d) {
		//associo il cliente e la data ai clienti e date importati da gestorePrenotazione
		this.cliente = nome; 
		this.data = d;
	}
    //metodo che restituisce un valore di tipo String della data
	public String getData () { 
		return this.data.toString();
	}
	//metodo che restituisce un valore di tipo Date della data
	public Date getDate () {
		return this.data;
	}
	 // metodo per stampare i dati aggiuntivi del tipo di prenotazione	
		public String toString () {
			SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy");
			String string = "Data prenotazione: "+ dateFormat.format(this.data)+"\n---";
			return string;		
		}
}

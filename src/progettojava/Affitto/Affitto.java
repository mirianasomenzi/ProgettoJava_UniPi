package progettojava.Affitto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Affitto {
	protected String cliente; 
	protected Date data;
	private Object dateFormat;
	
	public Affitto(String nome, Date d) {
		this.cliente = nome; // associo il cliente al nome importato da gestorePrenotazione 
		this.data = d;
	}

	public String getData () { //metodo che mi restituisce un valore di tipo string
		return this.data.toString();
		
	}
	
	public Date getDate () {
		return this.data;
	}
	
	
	 // metodo per stampare i dati aggiuntivi del tipo di prenotazione	
		public String toString () {
			//ho cambiato il formato della data
			SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy");
			String string = "Data prenotazione: "+ dateFormat.format(this.data)+"\n---";
			return string;		
		}
}
 // ho ricreato la classe Affitto in modo che anche l'affitto semplice venisse salvato da qualche parte e gli ho passato il parametro
//nome  dal gestorePrenotazioni dove viene inserito così che venga salvato tutto 
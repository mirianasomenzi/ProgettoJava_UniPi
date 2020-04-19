package progettojava.Affitto;

import java.util.Date;

public class Affitto {
	protected String cliente; 
	protected Date data;
	public Affitto(String nome, Date d) {
		this.cliente = nome; // associo il cliente al nome importato da gestorePrenotazione 
		this.data = d;
	}

	public String getData () { //metodo che mi restituisce un valore di tipo string
		return this.data.toString();
		
	}
	 // metodo per stampare i dati aggiuntivi del tipo di prenotazione	
		public String toString () {
			String string = "Data della prenotazione: "+this.data;
			return string;		
		}
}
 // ho ricreato la classe Affitto in modo che anche l'affitto semplice venisse salvato da qualche parte e gli ho passato il parametro
//nome  dal gestorePrenotazioni dove viene inserito così che venga salvato tutto 
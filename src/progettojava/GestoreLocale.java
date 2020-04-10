package progettojava;
import java.util.Scanner;
import java.util.Vector;
import java.util.Date;
//import java.util.Calendar.Builder;
import java.text.DateFormat;//aggiunto
import java.lang.IllegalArgumentException;
import java.text.ParseException;//aggiunto
import progettojava.Affitto.Affitto;
import progettojava.Affitto.Catering;
import progettojava.Affitto.CateringAnimazione;

public class GestoreLocale {

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		Vector <Integer> calendario = new Vector<Integer> ();
		//Calendar builder = new Calendar.Builder().setCalendarType("iso8601").build();
		//public Calendar.Builder setDate(int anno, int mese, int giorno);
		CateringAnimazione nuovocateringanimazione = new CateringAnimazione();
		Affitto nuovoaffitto = new Affitto();
		Catering nuovocatering = new Catering();
		
		System.out.println("In che giorno vuoi organizzare il compleanno? (inserisci data gg/mm/yy) ");
		
		String StringaData = input.nextLine();
		//converto la stringa in un oggetto della classe Date
		//tutto questo ci conviene metterlo in un metodo ausiliario?
		//oppure in affitto che modifichiamo in Prenotazione e affitto dal momento che ogni prenotazione prevede un affitto?
		try {
			DateFormat data = DateFormat.getDateInstance(DateFormat.SHORT);
			// questo metodo permette di fare controlli sulla stringa inserita prima di convertirla
			data.setLenient(false);
			
			Date d = data.parse(StringaData);
			
		} 
		catch (IllegalArgumentException e) { //non so se sia utile questa eccezione e come gestirle meglio
		} 
		catch (ParseException  e) {
			System.out.println ("Formato data non valido");
		}
		
	}

}

// Calendar.Builder   setDate(int year, int month, int dayOfMonth) 
//vector per memorizzare le date inserite dall'utente perchè non possiamo sapere
//in anticipo quante saranno, in modo da poter poi verificare se una data è libera o no
// n1 = chiedere all'utente di inserire i dati per verificare la disponibilità, se data disponibile passa a affitto se
// non disponibile scegli altra data 
// if v. isEmpty())
// 
package progettojava.Affitto;

import java.text.SimpleDateFormat;
import java.util.*;

// Classe contenente la prenotazione del locale con opzione catering
// Catering è sottoclasse della classe Affitto
public class Catering extends Affitto {
    //protected cosicchè la sottoclasse CateringAnimazione possa accedere alla variabile
    protected int bambini;

    //costruttore
    public Catering(String nome, Date d) {
        //richiamo il metodo della superclasse Affitto per salvare le informazioni relative alla prenotazione
        super(nome, d);

        boolean ok;
        do {
            System.out.println("Quanti bambini sono previsti?");
            Scanner input = new Scanner(System.in);
            ok = true;
            try {
                int n = input.nextInt();

                // se il numero di bambini è negativo, lancia un'eccezione in modo tale che si possa reinserire il valore
                if (n < 0) {
                    throw new InputMismatchException();
                } else {
                    //facciamo riferimento alla variabile dell'oggetto in modo da salvare anche il n. di bambini
                    this.bambini = n;
                }
            } catch (InputMismatchException e) {
                System.out.println("Valore non valido... ritenta: ");
                ok = false;
            }
        } while (!ok);
    }

    //metodo che restituisce il numero di bambini
    public int getBambini() {
        return this.bambini;
    }

    // metodo per stampare i dati aggiuntivi del tipo di prenotazione
    public String toString() {
        String string = "Data prenotazione: " + this.getData() + "\nNome cliente: " + this.cliente + "\nNumero bambini: " + this.bambini + "\n---";
        return string;
    }
}
	



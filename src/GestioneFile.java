import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author SUHAIL AOUAD
 */
public class GestioneFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //1)LETTURA
        //Questa classe serve a leggere da un file a mia scelta, se non lo trova genererà un eccezzione

        Lettore lettore = new Lettore("user.json");
        lettore.start();
        try {
            lettore.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //2)ELABORAZIONE
        String username = null;
        String password = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Inserisci l'username");
            username = br.readLine();  
            System.out.println("Inserisci la password");
             password = br.readLine(); 
        } catch (IOException e) {
            System.err.println("Errore durante la lettura dell'input: " + e.getMessage());
        }
        
        //3) SCRITTURA
         //Questa classe serve a scrivere su un file a mia scelta, il secondo argomento sarà un messaggio
        Scrittore scrittore = new Scrittore("output.csv",username+";"+password);
        Thread threadScrittore = new Thread(scrittore);
        threadScrittore.start();
        try {
            threadScrittore.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Copiatura
        //Questa classe legge il contenuto del primo file (argomento) e lo scrive nel secondo
        Copiatore c=new Copiatore("output.csv", "copia.csv");
        c.start();
        try {
            c.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
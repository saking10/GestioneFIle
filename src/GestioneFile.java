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
            username = br.readLine();  // Legge una riga dall'input
            System.out.println("Inserisci la password");
             password = br.readLine(); // Legge una riga dall'input
        } catch (IOException e) {
            System.err.println("Errore durante la lettura dell'input: " + e.getMessage());
        }
        
        //3) SCRITTURA
        Scrittore scrittore = new Scrittore("output.csv",username+";"+password);
        Thread threadScrittore = new Thread(scrittore);
        threadScrittore.start();
        try {
            threadScrittore.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
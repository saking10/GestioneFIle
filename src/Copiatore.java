import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author SUHAIL AOUAD
 */

 // appunti questa classe serve a copiare da un file (il primo argomento del costruttore) in un file a mia scelta (il secondo argomento del costruttore)

public class Copiatore extends Thread {
    String nomeFileDaCopiare;
    String nomeFileFinale;
   
    
    public Copiatore(String nomeFileDaCopiare,String nomeFileFinale){
         this.nomeFileDaCopiare=nomeFileDaCopiare;
       this.nomeFileFinale=nomeFileFinale;
    }

    // aggiunge ogni carattere letto e lo aggiunge al file reader, poi quando non trova più righe va a capo

    public String leggi(){
        StringBuilder br= new StringBuilder();
        FileReader fr;
        int i; 
        try { 
            //1) apro il file
            fr = new FileReader(nomeFileDaCopiare);
            //2) leggo carattere per carattere e lo stampo 
            while ((i=fr.read()) != -1)
                br.append(((char) i));
            
            br.append(("\n\r"));
            //3) chiudo il file
            fr.close();
        } catch (IOException ex) {
            System.err.println("Errore in lettura!");
        }
        return br.toString();
    }
    
    
    public void copia(){
        //Scrivo il contenuto del file copiato su un file a mia scelta
        Scrittore scrittore = new Scrittore(nomeFileFinale,this.leggi());
        Thread threadScrittore = new Thread(scrittore);
        threadScrittore.start();
         try {
            threadScrittore.join();
        } catch (InterruptedException ex) {
            System.err.println("Errore nel metodo join()");
        } 
    }
    

    @Override
    public void run(){
        copia();
    }
}

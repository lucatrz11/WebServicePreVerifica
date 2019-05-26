/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumerprova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author trezzi_luca
 */
public class ConsumerProva {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        // TODO code application logic here
        Consumer webService = new Consumer();
        List<Rubrica> rubrica = null;
        Parser dom = new Parser();
        String file = "entry.xml";//file per fare il parse dopo la get
        String file1 = "entry1.xml";//file con body per la post

        boolean fine = false;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        String scelta = "";
        while (!fine) {
            System.out.println("Cosa vuoi fare?[1=Visualizza informazioni, 2=Inserisci contatto, 5=Esci]");
            scelta = inFromUser.readLine();
            if (scelta.equals("1")) {
                System.out.println("Inserisci nome: ");
                String nome = inFromUser.readLine();
                System.out.println("Vuoi la descrizione: ");
                String descr = inFromUser.readLine();
                webService.getInfoProva(nome, descr);
                String result = webService.getResultInfo();
                File.SalvaSuFile(result);
                rubrica = dom.parseDocumentProva(file);
                for (Rubrica p : rubrica) {
                    System.out.println("Informazioni: " + p.toString());
                }
            } else if (scelta.equals("2")) {
                System.out.println("Scegli file: ");
                String f = inFromUser.readLine();
                webService.postInfo(file1);
                System.out.println("Elemento inserito");
            } else if (scelta.equals("5")) {
                fine = true;
            }
        }

    }

}

//String name = "gigi";
//String descr = "si";

/*webService.getInfoProva(name, descr);
        String result = webService.getResultInfo();
        //System.out.println(result);
        File.SalvaSuFile(result);
        rubrica = dom.parseDocumentProva(file);
        for (Rubrica p : rubrica) {
            System.out.println("Informazioni: " + p.toString());
        }*/
        /*webService.postInfo(file1);
        System.out.println("Elemento inserito");*/

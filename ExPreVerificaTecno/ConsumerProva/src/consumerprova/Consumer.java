/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumerprova;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author trezzi_luca
 */
public class Consumer {

    private String result;
    // http://www.gerriquez.com/web-service-comuni-italiani.html
    //https://script.google.com/macros/s/AKfycbwl2cR8y5xzeqn7S2aBboXZ7fqiFBPX9hK4yLJZjOqLDw13xUKS/exec?
    private String prefix = "http://localhost:8080/WSProva/";
    //http://localhost/Trezzi/Orari/index.php?
    //    //tecnologieorari.altervista.org/index.php?

    Consumer() {
        result = "";
    }

    public String[] getResult() {
        String[] arr = result.split(";");
        return arr;
    }

    public String getResultInfo() {
        return result;
    }

    public int postInfo(String file1) {
        int status = 0;
        String line;
        //File mioFile=new File();

        try {
            URL serverURL;
            HttpURLConnection service = null;
            BufferedReader input;
            BufferedWriter output;

            /*String url = prefix
                    + URLEncoder.encode(name, "UTF-8") + "?"
                    + URLEncoder.encode("number", "UTF-8") + "="
                    + URLEncoder.encode(number, "UTF-8");*/
            String url = prefix
                    + URLEncoder.encode("phonebook", "UTF-8");
            serverURL = new URL(url);
            service = (HttpURLConnection) serverURL.openConnection();

            // impostazione header richiesta
            service.setRequestProperty("Host", "localhost:8080");
            service.setRequestProperty("Content-type", "application/xml");
            service.setRequestProperty("Accept-Charset", "UTF-8");
            service.setRequestProperty("Accept", "application/xml");//application/text

            // impostazione metodo di richiesta POST
            service.setRequestMethod("POST");
            // attivazione ricezione
            service.setDoInput(true);
            // attivazione trasmissione
            service.setDoOutput(true);
            // connessione al web-service
            // verifica stato risposta
            // apertura stream di ricezione da risorsa web
            //input = new BufferedReader(new InputStreamReader(service.getInputStream(), "UTF-8"));
            input = new BufferedReader(new FileReader("entry1.xml"));
            output = new BufferedWriter(new OutputStreamWriter(service.getOutputStream(), "UTF-8"));
            // ciclo di lettura da web e scrittura in result
            while ((line = input.readLine()) != null) {
                output.write(line);
                output.newLine();
            }

            service.connect();
            output.flush();
            output.close();
            input.close();

            status = service.getResponseCode();
            if (status != 200) {
                return status; // non OK
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public int getInfoProva(String user, String descr) {
        int status = 0;
        result = "";
        try {
            URL serverURL;
            HttpURLConnection service;
            BufferedReader input;

            String url = prefix
                    + URLEncoder.encode(user, "UTF-8") + "?"
                    + URLEncoder.encode("descr", "UTF-8") + "="
                    + URLEncoder.encode(descr, "UTF-8");
            serverURL = new URL(url);
            service = (HttpURLConnection) serverURL.openConnection();
            // impostazione header richiesta
            service.setRequestProperty("Host", "localhost:8080");
            service.setRequestProperty("Accept", "application/text");
            service.setRequestProperty("Accept-Charset", "UTF-8");
            // impostazione metodo di richiesta GET
            service.setRequestMethod("GET");
            // attivazione ricezione
            service.setDoInput(true);
            // connessione al web-service
            service.connect();
            // verifica stato risposta
            status = service.getResponseCode();
            if (status != 200) {
                return status; // non OK
            }
            // apertura stream di ricezione da risorsa web
            input = new BufferedReader(new InputStreamReader(service.getInputStream(), "UTF-8"));
            // ciclo di lettura da web e scrittura in result
            String line;
            while ((line = input.readLine()) != null) {
                result += line + "\n";
            }
            input.close();

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    void printResult() {
        String[] arrOfStr = result.split("\",\"");

        for (int i = 0; i < arrOfStr.length; i++) {
            System.out.println(arrOfStr[i]);
        }
    }
}

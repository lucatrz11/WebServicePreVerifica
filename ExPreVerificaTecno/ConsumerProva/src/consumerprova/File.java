/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumerprova;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author trezzi_luca
 */
public class File {

    public File() {
    }

    public static void SalvaSuFile(String s) throws IOException {
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter("entry.xml", true))) {
            writer.write(s);
        }
    }

    public static String LeggiDaFile() throws FileNotFoundException, IOException {
        String s = "";

        BufferedReader file
                = new BufferedReader(new FileReader("entry.xml"));

        String riga = file.readLine();
        while (riga != null) {

            s += riga;
            riga = file.readLine();
        }

        return s;
    }

    public static void SovrascriviFile(String s) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("entry.xml"))) {
            writer.write(s);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumerprova;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author trezzi_luca
 */
public class Parser {

    private List rubrica;//creo una lista di produzioni
    //File f=new File();

    public Parser() {
        rubrica = new ArrayList();
    }

    public List parseDocumentProva(String filename) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document document;
        Element root, element;
        NodeList nodelist;
        Rubrica rub;
        // creazione dell’albero DOM dal documento XML
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        //File.SalvaSuFile(filename);

        document = builder.parse(filename);
        //root = document.getDocumentElement();
        /*if(root.getNodeValue()=="entry"){
            System.out.println("Ciao");
        }
        String r=root.getTagName();
        //tagName("persona") per ciclo
        //root.get*/
        nodelist = document.getElementsByTagName("entry");
        //nodelist = root.getElementsByTagName("entry");
        //nodelist = root.getElementsByTagName("name");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                element = (Element) nodelist.item(i);
                rub = getProduzioneProva(element);
                rubrica.add(rub);
            }

        }
        return rubrica;
    }

    public Rubrica getProduzioneProva(Element element) { //estrae il contenuto del tag specificato
        NodeList nodelist;
        Element el;
        String name = "";
        String number = "";
        String description = "";
        Rubrica rub;

        nodelist = element.getElementsByTagName("name");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                el = (Element) nodelist.item(i);
                String nome = getTextValue(el);
                //nome = nome.substring(1, nome.length() - 1);
                name = nome;
            }
        }
        nodelist = element.getElementsByTagName("number");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                el = (Element) nodelist.item(i);
                String num = getTextValue(el);
                number = num;

            }
        }
        nodelist = element.getElementsByTagName("description");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                el = (Element) nodelist.item(i);
                String desc = getTextValue(el);
                description = desc;
            }
        }

        rub = new Rubrica(name, number, description);
        return rub;
    }

    private String getTextValue(Element element) {//estare il valore testuale
        String valore = null;
        if (element != null) {
            Node el = element.getFirstChild();
            if (el != null) {
                valore = el.getNodeValue();
            }
        }
        return valore;
    }

    /*public List parseDocument(String filename) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document document;
        Element root, element;
        NodeList nodelist;
        Produzione produzione;
        // creazione dell’albero DOM dal documento XML
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        document = builder.parse(filename);
        root = document.getDocumentElement();
        nodelist = root.getElementsByTagName("row");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                element = (Element) nodelist.item(i);
                produzione = getProduzione(element);
                produzioni.add(produzione);
            }
        }
        return produzioni;
    }

    public Produzione getProduzione(Element element) { //estrae il contenuto del tag specificato
        NodeList nodelist;
        Element el;
        String anno = "";
        String codice = "";
        String regione = "";
        int oliveSuperficie = 0;
        int oliveSuperficieProduzione = 0;
        int oliveProduzioneQuintali = 0;
        int oliveTavolaProdRaccQuintali = 0;
        int oliveTavolaProdQuintali = 0;
        int oliveOlioProdQuintali = 0;
        double olivePressResaProd = 0.0;
        int olivePressProd = 0;
        Produzione prod;

        nodelist = element.getElementsByTagName("anno");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                el = (Element) nodelist.item(i);
                String annoP = getTextValue(el);
                annoP = annoP.substring(1, annoP.length() - 1);
                anno = annoP;
            }
        }
        nodelist = element.getElementsByTagName("regioni");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                el = (Element) nodelist.item(i);
                String reg = getTextValue(el);
                reg = reg.substring(1, reg.length() - 1);
                regione = reg;

            }
        }
        nodelist = element.getElementsByTagName("codice");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                el = (Element) nodelist.item(i);
                String cod = getTextValue(el);
                cod = cod.substring(1, cod.length() - 1);
                codice = cod;
            }
        }
        nodelist = element.getElementsByTagName("olive_superficie__ha_");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                el = (Element) nodelist.item(i);
                oliveSuperficie = Integer.parseInt(getTextValue(el));
            }
        }

        nodelist = element.getElementsByTagName("olive_superficie_in_produzione__ha_");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                el = (Element) nodelist.item(i);
                oliveSuperficieProduzione = Integer.parseInt(getTextValue(el));
            }
        }

        nodelist = element.getElementsByTagName("olive_produzione__quintali_");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                el = (Element) nodelist.item(i);
                oliveProduzioneQuintali = Integer.parseInt(getTextValue(el));
            }
        }

        nodelist = element.getElementsByTagName("olive_da_tavola_produzione_raccolta__quintali_");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                el = (Element) nodelist.item(i);
                oliveTavolaProdRaccQuintali = Integer.parseInt(getTextValue(el));
            }
        }

        nodelist = element.getElementsByTagName("olive_da_tavola_produzione__quintali_");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                el = (Element) nodelist.item(i);
                oliveTavolaProdQuintali = Integer.parseInt(getTextValue(el));
            }
        }

        nodelist = element.getElementsByTagName("olive_da_olio_produzione__quintali_");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                el = (Element) nodelist.item(i);
                oliveOlioProdQuintali = Integer.parseInt(getTextValue(el));
            }
        }

        nodelist = element.getElementsByTagName("olio_di_pressione_resa_di_produzione__quintali_");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                el = (Element) nodelist.item(i);
                olivePressResaProd = Double.parseDouble(getTextValue(el));
            }
        }

        nodelist = element.getElementsByTagName("olio_di_pressione_produzione__quintali_");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                el = (Element) nodelist.item(i);
                olivePressProd = Integer.parseInt(getTextValue(el));
            }
        }

        prod = new Produzione(anno, codice, regione, oliveSuperficie, oliveSuperficieProduzione, oliveProduzioneQuintali, oliveTavolaProdRaccQuintali, oliveTavolaProdQuintali, oliveOlioProdQuintali, olivePressResaProd, olivePressProd);
        return prod;

    }

    private String getTextValue(Element element) {//estare il valore testuale
        String valore = null;
        if (element != null) {
            Node el = element.getFirstChild();
            if (el != null) {
                valore = el.getNodeValue();
            }
        }
        return valore;
    }*/
}

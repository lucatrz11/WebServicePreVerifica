/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumerprova;

/**
 *
 * @author trezzi_luca
 */
public class Rubrica {

    private String name = "";
    private String number = "";
    private String descr = "";

    public Rubrica() {

    }

    public Rubrica(String name, String number, String descr) {
        this.name = name;
        this.number = number;
        this.descr = descr;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String toString() {
        String s = "";
        s += name + ";" + number + ";" + descr;
        return s;
    }

}

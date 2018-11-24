package ohtu.verkkokauppa;

import java.util.ArrayList;

public class Ostoskori {

    ArrayList<T> tuotteet;

    public Ostoskori() {
        tuotteet = new ArrayList<T>();
    }

    public void lisaa(T t) {
        tuotteet.add(t);
    }

    public void poista(T t) {
        tuotteet.remove(t);
    }

    public int hinta() {
        int hinta = 0;

        for (T tuote : tuotteet) {
            hinta += tuote.getHinta();
        }

        return hinta;
    }
}

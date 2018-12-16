package ohtu.kivipaperisakset;

// Tuomari pitää kirjaa ensimmäisen ja toisen pelaajan pisteistä sekä tasapelien määrästä.
import java.util.HashMap;
import java.util.Map;

public class Tuomari {

    private int ekanPisteet;
    private int tokanPisteet;
    private int tasapelit;
    private static Map<String, String> ekanVoitto;

    public Tuomari() {
        this.ekanPisteet = 0;
        this.tokanPisteet = 0;
        this.tasapelit = 0;
        ekanVoitto = new HashMap<>();
        ekanVoitto.put("k", "s");
        ekanVoitto.put("s", "p");
        ekanVoitto.put("p", "k");
    }

    public void kirjaaSiirto(String ekanSiirto, String tokanSiirto) {
        if (tasapeli(ekanSiirto, tokanSiirto)) {
            tasapelit++;
        } else if (ekaVoittaa(ekanSiirto, tokanSiirto)) {
            ekanPisteet++;
        } else {
            tokanPisteet++;
        }
    }

    // sisäinen metodi, jolla tarkastetaan tuliko tasapeli
    private static boolean tasapeli(String eka, String toka) {
        return eka.equals(toka);
    }

    // sisäinen metodi joka tarkastaa voittaako eka pelaaja tokan
    private static boolean ekaVoittaa(String eka, String toka) {
//        if ("k".equals(eka) && "s".equals(toka)) {
//            return true;
//        } else if ("s".equals(eka) && "p".equals(toka)) {
//            return true;
//        } else if ("p".equals(eka) && "k".equals(toka)) {
//            return true;
//        }
//
//        return false;
        return ekanVoitto.get(eka).equals(toka);
    }

    @Override
    public String toString() {
        String s = "Pelitilanne: " + ekanPisteet + " - " + tokanPisteet + "\n"
                + "Tasapelit: " + tasapelit;
        return s;
    }
}

package ohtu.kivipaperisakset;

import java.util.HashMap;
import java.util.Map;
import ohtu.kivipaperisakset.peli.KPSTekoaly;
import ohtu.kivipaperisakset.peli.KPSParempiTekoaly;
import ohtu.kivipaperisakset.peli.KPSPelaajaVsPelaaja;
import java.util.Scanner;
import ohtu.kivipaperisakset.IO.IO;
import ohtu.kivipaperisakset.IO.CmdIO;
import ohtu.kivipaperisakset.peli.KPS;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);
    private static Map<String, KPS> valikko;

    public static void main(String[] args) {

        valikko = new HashMap<>();

        valikko.put("a", KPS.kaksinpeli(new CmdIO()));
        valikko.put("b", KPS.helppoTekoalyPeli(new CmdIO()));
        valikko.put("c", KPS.vaikeaTekoalyPeli(new CmdIO()));

        // KPS kps=new KPS();
        String vastaus = "a";
        while (valikko.get(vastaus)!=null) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            vastaus = scanner.nextLine();
            System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");

            KPS peli = valikko.get(vastaus);
           if(peli!=null) peli.pelaa(new Tuomari());
           // peli.pelaa(new Tuomari());
            
//            if (vastaus.endsWith("a")) {
//                System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
////                KPSPelaajaVsPelaaja kaksinpeli = new KPSPelaajaVsPelaaja();
//                KPS kaksinpeli = KPS.kaksinpeli();
//                kaksinpeli.pelaa();
//            } else if (vastaus.endsWith("b")) {
//                System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
////                KPSTekoaly yksinpeli = new KPSTekoaly();
//                KPS yksinpeli = KPS.helppoTekoalyPeli();
//                yksinpeli.pelaa();
//            } else if (vastaus.endsWith("c")) {
//                System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
////                KPSParempiTekoaly pahaYksinpeli = new KPSParempiTekoaly();
//                KPS pahaYksinpeli = KPS.vaikeaTekoalyPeli();
//                pahaYksinpeli.pelaa();
//            } else {
//                break;
//            }

//        }
        }

    }
}

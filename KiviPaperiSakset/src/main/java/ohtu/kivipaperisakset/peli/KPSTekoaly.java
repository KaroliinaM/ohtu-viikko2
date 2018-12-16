package ohtu.kivipaperisakset.peli;

import java.util.Scanner;
import ohtu.kivipaperisakset.IO.IO;
import ohtu.kivipaperisakset.pelaaja.Tekoaly;
import ohtu.kivipaperisakset.Tuomari;
import ohtu.kivipaperisakset.pelaaja.Pelaaja;

public class KPSTekoaly extends KPS {

    private static final Scanner scanner = new Scanner(System.in);
    private Pelaaja pelaaja;

    public KPSTekoaly(IO io, Pelaaja pelaaja) {
        this.io = io;
        this.pelaaja = pelaaja;
    }

//    @Override
//    public void pelaa(Tuomari tuomari) {
//        Tekoaly tekoaly = new Tekoaly();
//        //      String ekanSiirto=ensimmainenSiirto();
//
//        System.out.print("Ensimmäisen pelaajan siirto: ");
//        String ekanSiirto = scanner.nextLine();
//        String tokanSiirto;
//
//        tokanSiirto = tekoaly.annaSiirto();
//        System.out.println("Tietokone valitsi: " + tokanSiirto);
//
//        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
//            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
//            System.out.println(tuomari);
//            System.out.println();
//
//            System.out.print("Ensimmäisen pelaajan siirto: ");
//            ekanSiirto = scanner.nextLine();
//
//            tokanSiirto = tekoaly.annaSiirto();
//            System.out.println("Tietokone valitsi: " + tokanSiirto);
//            tekoaly.asetaSiirto(ekanSiirto);
//
//        }
//
//        System.out.println();
//        System.out.println("Kiitos!");
//        System.out.println(tuomari);
//    }
//
//    private static boolean onkoOkSiirto(String siirto) {
//        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
//    }

    @Override
    protected String vastustajanSiirto(String ekanSiirto) {
           String tokanSiirto = pelaaja.siirra(ekanSiirto);
        return tokanSiirto;
    }
}

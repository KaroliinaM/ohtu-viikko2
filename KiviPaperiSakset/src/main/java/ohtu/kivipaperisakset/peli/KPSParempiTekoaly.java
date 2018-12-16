package ohtu.kivipaperisakset.peli;

import java.util.Scanner;

import java.util.Scanner;
import ohtu.kivipaperisakset.IO.IO;
import ohtu.kivipaperisakset.pelaaja.TekoalyParannettu;
import ohtu.kivipaperisakset.pelaaja.Pelaaja;
import ohtu.kivipaperisakset.Tuomari;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KPS {

    public KPSParempiTekoaly(IO io, Pelaaja pelaaja) {
        this.io = io;
        this.pelaaja = pelaaja;
    }
    private static final Scanner scanner = new Scanner(System.in);

//    @Override
//    public void pelaa(Tuomari tuomari) {
//        //  Tuomari tuomari = new Tuomari();
//        //      TekoalyParannettu tekoaly = new TekoalyParannettu(20);
//        String ekanSiirto = ensimmainenSiirto();
////        System.out.print("Ensimmäisen pelaajan siirto: ");
////        String ekanSiirto = scanner.nextLine();
//        String tokanSiirto = pelaaja.siirra(ekanSiirto);
////        String tokanSiirto;
////        tokanSiirto=toinenSiirto(pelaaja);
//
////        tokanSiirto = tekoaly.annaSiirto();
////        System.out.println("Tietokone valitsi: " + tokanSiirto);
//        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
//            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
//            io.printLine(tuomari.toString());
//            io.printLine("");
//
////            System.out.print("Ensimmäisen pelaajan siirto: ");
////            ekanSiirto = scanner.nextLine();
//            ekanSiirto = ensimmainenSiirto();
//
//            tokanSiirto = pelaaja.siirra(ekanSiirto);
//            // tokanSiirto=toinenSiirto(pelaaja);
//
////            tokanSiirto = tekoaly.annaSiirto();
////            System.out.println("Tietokone valitsi: " + tokanSiirto);
//            // tekoaly.asetaSiirto(ekanSiirto);
//        }
//
//        io.printLine("");
//        io.printLine("Kiitos!");
//        io.printLine(tuomari.toString());
//    }

//    private static boolean onkoOkSiirto(String siirto) {
//        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
//    }
////    private String toinenSiirto(Pelaaja tekoaly) {
////                  String  tokanSiirto = tekoaly.annaSiirto();
////            System.out.println("Tietokone valitsi: " + tokanSiirto);
////            return tokanSiirto;
////    }

    @Override
    protected String vastustajanSiirto(String ekanSiirto) {
        String tokanSiirto = pelaaja.siirra(ekanSiirto);
        return tokanSiirto;
    }
}

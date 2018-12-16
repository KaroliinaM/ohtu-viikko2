package ohtu.kivipaperisakset.peli;

import java.util.Scanner;
import ohtu.kivipaperisakset.IO.IO;
import ohtu.kivipaperisakset.Tuomari;
import ohtu.kivipaperisakset.pelaaja.Pelaaja;

public class KPSPelaajaVsPelaaja  extends KPS {

    private Scanner scanne = new Scanner(System.in);
    private Pelaaja pelaaja;

    public KPSPelaajaVsPelaaja(IO io, Pelaaja pelaaja) {
        this.io=io;
        this.pelaaja=pelaaja;
        
    }

//    @Override
//    public void pelaa(Tuomari tuomari) {
//        
//     //   String ekanSiirto=ensimmainenSiirto();
//
////        System.out.println("Ensimmäisen pelaajan siirto: ");
////        String ekanSiirto = scanne.nextLine();
//String ekanSiirto = ensimmainenSiirto();
//String tokanSiirto=pelaaja.siirra(ekanSiirto);
////        System.out.println("Toisen pelaajan siirto: ");
////        String tokanSiirto = scanne.nextLine();
//
//        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
//            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
//            System.out.println(tuomari);
//            System.out.println();
//
//            System.out.print("Ensimmäisen pelaajan siirto: ");
//            ekanSiirto = scanne.nextLine();
//            
//            System.out.print("Toisen pelaajan siirto: ");
//            tokanSiirto = scanne.nextLine();
//        }
//
//        System.out.println();
//        System.out.println("Kiitos!");
//        System.out.println(tuomari);
//    }

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
    @Override
    protected String vastustajanSiirto(String ekanSiirto) {
        String tokanSiirto = pelaaja.siirra(ekanSiirto);
        return tokanSiirto;
    }
}
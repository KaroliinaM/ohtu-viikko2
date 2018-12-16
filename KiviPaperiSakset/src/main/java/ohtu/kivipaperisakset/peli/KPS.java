/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset.peli;

import java.util.Scanner;
import ohtu.kivipaperisakset.IO.IO;
import ohtu.kivipaperisakset.Tuomari;
import ohtu.kivipaperisakset.pelaaja.Pelaaja;
import ohtu.kivipaperisakset.pelaaja.Tekoaly;
import ohtu.kivipaperisakset.pelaaja.TekoalyParannettu;
import ohtu.kivipaperisakset.pelaaja.Vastustaja;

/**
 *
 * @author kape
 */
public class KPS {

    protected static final Scanner scanner = new Scanner(System.in);

    protected static IO io;
    protected static Pelaaja pelaaja;
    protected Tuomari tuomari;
    

//    public KPS(IO io, Pelaaja pelaaja) {
//        KPS.io = io;
//        KPS.pelaaja = pelaaja;
//    }

    public static KPS kaksinpeli(IO io) {
        System.out.println("ihminen");
        return new KPSPelaajaVsPelaaja(io, new Vastustaja());
    }

    public static KPS helppoTekoalyPeli(IO io) {
        System.out.println("helppo");
        return new KPSTekoaly(io, new Tekoaly());
    }

    public static KPS vaikeaTekoalyPeli(IO io) {
        System.out.println("vaikea");
      //  return new KPSTekoaly(io, new Tekoaly());
        return new KPSParempiTekoaly(io, new TekoalyParannettu(20));
    }

    public void pelaa(Tuomari tuomari) {
        while (true) {
        String ekanSiirto = ensimmainenSiirto();
        if(!onkoOkSiirto(ekanSiirto)) break;

        //String tokanSiirto = pelaaja.siirra(ekanSiirto);
        String tokanSiirto = vastustajanSiirto(ekanSiirto);
        if(!onkoOkSiirto(tokanSiirto)) break;
   //     while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            io.printLine(tuomari.toString());
            io.printLine("");
//
//            ekanSiirto = ensimmainenSiirto();
//            
//            tokanSiirto =vastustajanSiirto(ekanSiirto);

            //tokanSiirto = pelaaja.siirra(ekanSiirto);
        }

        io.printLine("");
        io.printLine("Kiitos!");
        io.printLine(tuomari.toString());
    }

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    protected String ensimmainenSiirto() {
        io.printLine("Ensimmäisen pelaajan siirto: ");
        String ekanSiirto = io.readLine();
        return ekanSiirto;
    }
    protected String vastustajanSiirto(String ekanSiirto) {
        String tokanSiirto = pelaaja.siirra(ekanSiirto);
        return tokanSiirto;
    }
}

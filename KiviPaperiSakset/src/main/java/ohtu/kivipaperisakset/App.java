/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import ohtu.kivipaperisakset.IO.CmdIO;
import ohtu.kivipaperisakset.peli.KPS;

/**
 *
 * @author kape
 */
public class App {

    private static final Scanner scanner = new Scanner(System.in);
    private static Map<String, KPS> valikko = new HashMap<>();

    public void run(Tuomari tuomari) {
        valikko.put("a", KPS.kaksinpeli(new CmdIO()));
        valikko.put("b", KPS.helppoTekoalyPeli(new CmdIO()));
        valikko.put("c", KPS.vaikeaTekoalyPeli(new CmdIO()));

        // KPS kps=new KPS();
        String vastaus = "";
        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            vastaus = scanner.nextLine();
            System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");

            KPS peli = valikko.get(vastaus);
            //      KPS peli=KPS.kaksinpeli(new CmdIO());

            peli.pelaa(tuomari);
        }

    }
}

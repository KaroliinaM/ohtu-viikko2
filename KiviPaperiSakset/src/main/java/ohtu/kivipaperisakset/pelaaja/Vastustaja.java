/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset.pelaaja;

import java.util.Scanner;

/**
 *
 * @author kape
 */
public class Vastustaja implements Pelaaja{
    Scanner scanner=new Scanner(System.in);
    
    public Vastustaja() {
        System.out.println("luotu vastustaja");
    }

    @Override
    public String siirra(String siirto) {
            System.out.println("Toisen pelaajan siirto: ");
            String tokanSiirto = scanner.nextLine();
            return tokanSiirto;
    }
    
    



    
}

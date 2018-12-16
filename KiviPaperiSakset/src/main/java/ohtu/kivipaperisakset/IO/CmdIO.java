/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset.IO;

import java.util.Scanner;

/**
 *
 * @author kape
 */
public class CmdIO implements IO{
    
    Scanner scanner=new Scanner(System.in);

    @Override
    public String readLine() {
        String s=scanner.nextLine();
        return s;
    }

    @Override
    public void printLine(String line) {
        System.out.println(line);
    }
    
}

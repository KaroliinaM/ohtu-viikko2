/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

import java.util.ArrayList;

/**
 *
 * @author kape
 */
public interface KP {
    
    void lisaaTapahtuma(String tapahtuma);
    ArrayList<String> getTapahtumat();
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin.komennot;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import laskin.Sovelluslogiikka;

/**
 *
 * @author kape
 */
public class Erotus extends Komento {
    private int arvo;

    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

//    private TextField tuloskentta;
//    private TextField syotekentta;
//    private Button nollaa;
//    private Button undo;
//    private Sovelluslogiikka sovellus;
//
//    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
//
//        this.tuloskentta = tuloskentta;
//        this.syotekentta = syotekentta;
//        this.nollaa = nollaa;
//        this.undo = undo;
//        this.sovellus = sovellus;
//    }
    @Override
    public void suorita() {
        arvo = getSyote();
        sovellus.miinus(arvo);

        getTulos();
        enableOrDisableButtons();
//        int laskunTulos = sovellus.tulos();
//
//        syotekentta.setText("");
//        tuloskentta.setText("" + laskunTulos);
//
//        if (laskunTulos == 0) {
//            nollaa.disableProperty().set(true);
//        } else {
//            nollaa.disableProperty().set(false);
//        }
//        undo.disableProperty().set(false);
    }

    @Override
    public void peru() {
        sovellus.plus(arvo);
        getTulos();
        enableOrDisableButtons();
    }

}

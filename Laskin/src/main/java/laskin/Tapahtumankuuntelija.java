package laskin;

import java.util.HashMap;
import java.util.Map;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import laskin.komennot.Erotus;
import laskin.komennot.Komento;
import laskin.komennot.Nollaa;
import laskin.komennot.Summa;

public class Tapahtumankuuntelija implements EventHandler {
    private Komento summa;
    private Komento erotus;
    private Komento nollaus;
    private TextField tuloskentta; 
    private TextField syotekentta; 
    private Button plus;
    private Button miinus;
    private Button nollaa;
    private Button undo;
    private Sovelluslogiikka sovellus;
    
    private Map<Button, Komento> komennot;
    private Komento edellinen = null;

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        komennot = new HashMap<>();
        komennot.put(plus, new Summa(tuloskentta, syotekentta,  nollaa, undo, sovellus) );
        komennot.put(miinus, new Erotus(tuloskentta, syotekentta, nollaa, undo, sovellus) );
        komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta,  nollaa, undo, sovellus) );
//        this.summa=new Summa(tuloskentta, syotekentta,  nollaa, undo, sovellus);
//        this.erotus=new Erotus(tuloskentta, syotekentta,  nollaa, undo, sovellus);
//        this.nollaus=new Nollaa(tuloskentta, syotekentta,  nollaa, undo, sovellus);
//        this.plus = plus;
//        this.miinus = miinus;
//        this.nollaa = nollaa;
        
        
        
    }
    
    @Override
    public void handle(Event event) {
        if ( event.getTarget() != undo ) {
            Komento komento = komennot.get((Button)event.getTarget());
            komento.suorita();
            edellinen = komento;
        } else {
            edellinen.peru();
            edellinen = null;
        }                  
    }
//        int arvo = 0;
// 
//        try {
//            arvo = Integer.parseInt(syotekentta.getText());
//        } catch (Exception e) {
//        }

 
//        if (event.getTarget() == plus) {
//            summa.suorita();
//            //sovellus.plus(arvo);
//        } else if (event.getTarget() == miinus) {
//            erotus.suorita();
////            sovellus.miinus(arvo);
//        } else if (event.getTarget() == nollaa) {
//            nollaus.suorita();
//            //sovellus.nollaa();
//        } else {
//            System.out.println("undo pressed");
//        }
        

        


}

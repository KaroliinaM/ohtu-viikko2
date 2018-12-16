package ohtu.kivipaperisakset.pelaaja;

import java.util.HashMap;
import java.util.Map;

public class Tekoaly  implements Pelaaja{

    int siirto;
    
    private Map<Integer, String> siirrot;

    public Tekoaly() {
        siirto = 0;
        siirrot=new HashMap<>();
        siirrot.put(0, "k");
        siirrot.put(1, "p");
        siirrot.put(2, "s");
    }
    
            

    public String annaSiirto() {
        siirto++;
        siirto = siirto % 3;
        return siirrot.get(siirto);

    }

//    public void asetaSiirto(String ekanSiirto) {
//        // ei tehdä mitään 
//    }
    @Override
    public String siirra(String ekanSiirto) {
        String tokanSiirto = annaSiirto();
            System.out.println("Helppo Tietokone valitsi: " + tokanSiirto);
           // asetaSiirto(ekanSiirto);
            return tokanSiirto;
    }
}

 
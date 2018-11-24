package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Kauppa {

    private V varasto;
    private P pankki;
    private Ostoskori ostoskori;
    private VG viitegeneraattori;
    private KP kirjanpito;
    private String kaupanTili;

//    public Kauppa() {
//        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");
//        varasto=ctx.getBean(Varasto.class);
//        //varasto = new Varasto(new Kirjanpito());
//        pankki=ctx.getBean(Pankki.class);
////        pankki = new Pankki(new Kirjanpito());
//        viitegeneraattori=ctx.getBean(Viitegeneraattori.class);
////        viitegeneraattori = new Viitegeneraattori();
//        kirjanpito=ctx.getBean(KP.class);
//
//        kaupanTili = "33333-44455";
//    }
    @Autowired
    public Kauppa(Varasto v, Pankki p, Viitegeneraattori vg){
        varasto=v;
        pankki=p;
        viitegeneraattori=vg;
        
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        T t = varasto.haeTuote(id);
        ostoskori.poista(t);
        varasto.palautaVarastoon(t);
        
    }

    public void lisaaKoriin(int id) {
        if (varasto.saldo(id)>0) {
            T t = varasto.haeTuote(id);             
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();
        
        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}

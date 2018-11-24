package ohtu.verkkokauppa;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class KauppaTest {

    Kauppa kauppa;

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq(null), eq(5));
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenTekoOnnistuuKahdellaTuotteella() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 7));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);
        k.tilimaksu("Aarne", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("Aarne"), eq(42), eq("12345"), eq(null), eq(12));
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostosOnnistuuKahdellaSamallaTuotteella() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);
        k.tilimaksu("sami", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("sami"), eq(42), eq("12345"), eq(null), eq(10));
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
        @Test
    public void ostoEiOnnistuJosTuoteOnLoppu() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 7));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);
        k.tilimaksu("hanna", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("hanna"), eq(42), eq("12345"), eq(null), eq(5));
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    
}
//    public void kutsutaanPankkia() {
//        // luodaan kaksi mock-olioa
//        Pankki mockPankki = mock(Pankki.class);
//        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
//
//        // injektoidaan ne kaupalle normaalien olioiden tapaan
//        kauppa = new Kauppa(mockPankki, mockViite);
//
//        kauppa.aloitaOstokset();
//        kauppa.lisaaOstos(5);
//        kauppa.lisaaOstos(5);
////        kauppa.maksa("1111");
//
//        // varmistetaan pankilta että sen metodia maksa on kutsuttu
//        verify(mockPankki).maksa(anyString(), anyInt(), anyInt());
//        // kutsussa olevein parametrien arvoilla ei testissä ole väliä
//        // kokeile muuttaa koodia siten että testi menee rikki!        
//    }
//
//    @Test
//    public void kutsutaanPankkiaOikeallaTilinumerolla() {
//        Pankki mockPankki = mock(Pankki.class);
//        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
//
//        kauppa = new Kauppa(mockPankki, mockViite);
//
//        kauppa.aloitaOstokset();
//        kauppa.lisaaOstos(5);
//        kauppa.lisaaOstos(5);
//        kauppa.maksa("111");
//
//        // tällä kertaa vaaditaan että ensimmäisen parametrin arvo on oikea
//        verify(mockPankki).maksa(eq("1111"), anyInt(), anyInt());
//        // kokeile jälleen rikkoa koodi
//    }
//
//    @Test
//    public void kutsutaanPankkiaOikeallaTilinumerollaJaSummalla() {
//        Pankki mockPankki = mock(Pankki.class);
//        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
//
//        kauppa = new Kauppa(mockPankki, mockViite);
//
//        kauppa.aloitaOstokset();
//        kauppa.lisaaOstos(5);
//        kauppa.lisaaOstos(4);
//        kauppa.maksa("1111");
//
//        // nyt vaadimme myös toisen parametrin arvon olevan oikea
//        verify(mockPankki).maksa(eq("1111"), eq(10), anyInt());
//    }
//
//    @Test
//    public void kaytetaanMaksussaPalautettuaViiteta() {
//        Pankki mockPankki = mock(Pankki.class);
//        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
//        
//        // määrittelemme minkä arvon viitegeneraattori palauttaa kun sen metodia
//        // seuraava() kutsutaan
//        when(mockViite.seruaava()).thenReturn(54);
//
//        kauppa = new Kauppa(mockPankki, mockViite);
//
//        kauppa.aloitaOstokset();
//        kauppa.lisaaOstos(5);
//        kauppa.lisaaOstos(5);
//        kauppa.maksa("1111");
//
//        // nyt kaksi ensimmäistä parametria saa olla arvoiltaan mitä sattuu
//        // kolmannen on oltava sama mikä mock-olion määriteltiin palauttavan
//        verify(mockPankki).maksa(anyString(), anyInt(), eq(55));
//    }
//
//    @Test
//    public void pyydetaanUusiViiteJokaiseenMaksuun() {
//        Pankki mockPankki = mock(Pankki.class);
//        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
//
//        kauppa = new Kauppa(mockPankki, mockViite);
//
//        kauppa.aloitaOstokset();
//        kauppa.lisaaOstos(5);
//        kauppa.maksa("1111");
//
//        // tarkistetaan että tässä vaiheessa viitegeneraattorin metodia seuraava()
//        // on kutsuttu kerran
//        verify(mockViite, times(1)).seruaava();
//
//        kauppa.aloitaOstokset();
//        kauppa.lisaaOstos(1);
//        kauppa.maksa("1234");
//
//        // tarkistetaan että tässä vaiheessa viitegeneraattorin metodia seuraava()
//        // on kutsuttu kaksi kertaa
//        verify(mockViite, times(2)).seruaava();
//
//        kauppa.aloitaOstokset();
//        kauppa.lisaaOstos(3);
//        kauppa.maksa("4444");
//
//        // tarkistetaan että tässä vaiheessa viitegeneraattorin metodia seuraava()
//        // on kutsuttu kolme kertaa        
//        verify(mockViite, times(3)).seruaava();
//    }
//
//    @Test
//    public void kaytetaanPerakkaistenViitekutsujenArvoja() {
//        Pankki mockPankki = mock(Pankki.class);
//        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
//        // määritellään että metodi palauttaa ensimmäisellä kutsukerralla 1, toisella 2 
//        // ja kolmannella 3
//        when(mockViite.seruaava()).
//                thenReturn(1).
//                thenReturn(2).
//                thenReturn(3);
//
//        kauppa = new Kauppa(mockPankki, mockViite);
//
//        kauppa.aloitaOstokset();
//        kauppa.lisaaOstos(5);
//        kauppa.maksa("1111");
//
//        // varmistetaan, että nyt käytössä ensimmäisenä pyydetty viite
//        verify(mockPankki).maksa(anyString(), anyInt(), eq(1));
//        
//        kauppa.aloitaOstokset();
//        kauppa.lisaaOstos(1);
//        kauppa.maksa("1222");
//
//        // ... toisena pyydetty viite
//        verify(mockPankki).maksa(anyString(), anyInt(), eq(2));   
//        
//        kauppa.aloitaOstokset();
//        kauppa.lisaaOstos(1);
//        kauppa.maksa("4321");
//
//        // ... ja kolmantena pyydetty viite        
//        verify(mockPankki).maksa(anyString(), anyInt(), eq(3));           
//
//    }
//}
//package ohtu.verkkokauppa;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.FileSystemXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Kauppa {
//
//    private V varasto;
//    private P pankki;
//    private Ostoskori ostoskori;
//    private VG viitegeneraattori;
//    private KP kirjanpito;
//    private String kaupanTili;
//
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
//    @Autowired
//    public Kauppa(Varasto v, Pankki p, Viitegeneraattori vg){
//        varasto=v;
//        pankki=p;
//        viitegeneraattori=vg;
//        
//    }
//
//    public void aloitaAsiointi() {
//        ostoskori = new Ostoskori();
//    }
//
//    public void poistaKorista(int id) {
//        T t = varasto.haeTuote(id); 
//        varasto.palautaVarastoon(t);
//    }
//
//    public void lisaaKoriin(int id) {
//        if (varasto.saldo(id)>0) {
//            T t = varasto.haeTuote(id);             
//            ostoskori.lisaa(t);
//            varasto.otaVarastosta(t);
//        }
//    }
//
//    public boolean tilimaksu(String nimi, String tiliNumero) {
//        int viite = viitegeneraattori.uusi();
//        int summa = ostoskori.hinta();
//        
//        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
//    }
//
//}

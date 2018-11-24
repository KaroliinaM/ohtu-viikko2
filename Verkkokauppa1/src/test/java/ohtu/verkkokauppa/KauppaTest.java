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

        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);

        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 7));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("Aarne", "12345");

        verify(pankki).tilisiirto(eq("Aarne"), eq(42), eq("12345"), eq(null), eq(12));

    }

    @Test
    public void ostosOnnistuuKahdellaSamallaTuotteella() {

        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);

        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("sami", "12345");

        verify(pankki).tilisiirto(eq("sami"), eq(42), eq("12345"), eq(null), eq(10));

    }

    @Test
    public void ostoEiOnnistuJosTuoteOnLoppu() {

        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);

        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 7));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("hanna", "12345");

        verify(pankki).tilisiirto(eq("hanna"), eq(42), eq("12345"), eq(null), eq(5));

    }

    @Test
    public void AsioinninAloittaminenNollaaOstoskorin() {
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(15);
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(15);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "omenat", 4));
        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("anne", "4444");
        verify(pankki).tilisiirto(eq("anne"), eq(15), eq("4444"), eq(null), eq(4));

    }

    @Test
    public void maksuunPyydetaanAinaUusiViite() {
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(15);
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(12);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Appelsiinit", 4));
        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("n", "555");
        verify(viite, times(1)).uusi();
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("m", "7777");
        verify(viite, times(2)).uusi();
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("f", "7774");
        verify(viite, times(3)).uusi();

    }
    @Test
    public void ostoksenPoistokoristaOnnistuu() {
        Pankki pankki=mock(Pankki.class);
        Viitegeneraattori viite=mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(12);
        Varasto varasto=mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(12);
        Tuote t=new Tuote(1, "pelmenit", 10);
        when(varasto.haeTuote(1)).thenReturn(t);
        Kauppa k=new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.poistaKorista(1);
        k.tilimaksu("n", "11111");
        verify(varasto, times(1)).palautaVarastoon(t);
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), eq(null), eq(10));
    }

}

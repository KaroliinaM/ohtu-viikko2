package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    private int kasvatuskoko;
    private int[] syotetytLuvut;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm = 0;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        LukutaulukonAlustus(KAPASITEETTI);
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti > 0) {
            LukutaulukonAlustus(kapasiteetti);
            this.kasvatuskoko = OLETUSKASVATUS;
        } else {
            throw new IndexOutOfBoundsException("Väärä syöte");
        }

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti > 0 && kasvatuskoko > 0) {
            LukutaulukonAlustus(kapasiteetti);
            this.kasvatuskoko = kasvatuskoko;
        } else {
            throw new IndexOutOfBoundsException("Väärä syöte");
        }

    }

    public boolean lisaa(int luku) {

        if (!kuuluu(luku)) {
            lisaaTaulukkoon(luku);
            if (alkioidenLkm == syotetytLuvut.length) {
                kasvataTaulukkoa();
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        return alkioPaikassa(luku) != -1;
    }

    public boolean poista(int luku) {
        int kohta = alkioPaikassa(luku);
        int apu;

        if (kohta != -1) {
            for (int j = kohta; j < alkioidenLkm - 1; j++) {
                apu = syotetytLuvut[j];
                syotetytLuvut[j] = syotetytLuvut[j + 1];
                syotetytLuvut[j + 1] = apu;
            }
            alkioidenLkm--;
            return true;
        }

        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, alkioidenLkm);

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String tuloste = "{";
        for (int i = 0; i < alkioidenLkm; i++) {
            tuloste += syotetytLuvut[i];
            if (i < (alkioidenLkm - 1)) {
                tuloste += ", ";
            }

        }
        tuloste += "}";
        return tuloste;

    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        kopioiTaulukko(syotetytLuvut, taulu);
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = muodostaJoukko(a, b, "yhdiste");
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = muodostaJoukko(a, b, "leikkaus");
        return y;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = muodostaJoukko(a, b, "erotus");

        return z;
    }

    private static IntJoukko muodostaJoukko(IntJoukko a, IntJoukko b, String operaatio) {
        IntJoukko z = new IntJoukko();
        int[] aJoukonTaulu = a.toIntArray();
        int[] bJoukonTaulu = b.toIntArray();

        if (operaatio.equals("yhdiste")) {
            z.lisaaTaulukko(aJoukonTaulu);
            z.lisaaTaulukko(bJoukonTaulu);
        }
        if (operaatio.equals("erotus")) {
            z.lisaaTaulukko(aJoukonTaulu);
            z.poistaTaulukko(bJoukonTaulu);
        }
        if (operaatio.equals("leikkaus")) {
            z.lisääMikäliJoukossa(aJoukonTaulu, b);
        }

        return z;

    }

    public void lisaaTaulukko(int[] taulukko) {
        for (int i = 0; i < taulukko.length; i++) {
            this.lisaa(taulukko[i]);
        }
    }

    public void poistaTaulukko(int[] taulukko) {
        for (int i = 0; i < taulukko.length; i++) {
            this.poista(taulukko[i]);
        }
    }

    public void lisääMikäliJoukossa(int[] taulukko, IntJoukko joukko) {
        for (int i = 0; i < taulukko.length; i++) {
            if (joukko.kuuluu(taulukko[i])) {
                this.lisaa(taulukko[i]);
            }
        }
    }

    private void LukutaulukonAlustus(int kapasiteetti) {
        syotetytLuvut = new int[kapasiteetti];
        for (int i = 0; i < syotetytLuvut.length; i++) {
            syotetytLuvut[i] = 0;
        }

    }

    public void lisaaTaulukkoon(int luku) {
        syotetytLuvut[alkioidenLkm] = luku;
        alkioidenLkm++;
    }

    private void kasvataTaulukkoa() {
        int[] taulukkoOld = syotetytLuvut;
        syotetytLuvut = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(taulukkoOld, syotetytLuvut);
    }

    private int alkioPaikassa(int luku) {
        int paikka = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == syotetytLuvut[i]) {
                paikka = i;
                break;
            }
        }
        return paikka;
    }
}

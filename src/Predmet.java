public class Predmet {
    private String nazev;
    private String popis;
    private boolean prenositelny;
    private String prostor;
    private String pouziti;

    public Predmet(String nazev, String popis, boolean prenositelny, String prostor, String pouziti) {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelny = prenositelny;
        this.prostor = prostor;
        this.pouziti = pouziti;
    }

    public String getNazev() { return nazev; }
    public String getPopis() { return popis; }
    public boolean jePrenositelny() { return prenositelny; }
    public String getProstor() { return prostor; }
    public String getPouziti() { return pouziti; }
}
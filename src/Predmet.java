public class Predmet {
    private String nazev;
    private String popis;
    private String ucel;
    private boolean prenositelny;
    private String prostor;
    private String pouziti;

    public Predmet() {
    }

    public Predmet(String nazev, String popis, boolean prenositelny, String prostor, String pouziti) {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelny = prenositelny;
        this.prostor = prostor;
        this.pouziti = pouziti;
    }

    public String getNazev() { return nazev; }
    public String getPopis() { return popis; }

    public String getUcel() {
        return ucel;
    }

    public boolean jePrenositelny() { return prenositelny; }
    public String getProstor() { return prostor; }
    public String getPouziti() { return pouziti; }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public void setUcel(String ucel) {
        this.ucel = ucel;
    }

    public void setPrenositelny(boolean prenositelny) {
        this.prenositelny = prenositelny;
    }

    public void setProstor(String prostor) {
        this.prostor = prostor;
    }

    public void setPouziti(String pouziti) {
        this.pouziti = pouziti;
    }
}
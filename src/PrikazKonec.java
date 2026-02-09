public class PrikazKonec implements IPrikaz {
    private Hra hra;
    public PrikazKonec(Hra hra) { this.hra = hra; }
    public String getNazev() { return "konec"; }
    public String provedPrikaz(String... parametry) {
        hra.setKonecHry(true);
        return "Hra skoncila.";
    }
}

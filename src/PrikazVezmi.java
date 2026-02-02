public class PrikazVezmi implements IPrikaz {
    private Hra hra;
    public PrikazVezmi(Hra hra) { this.hra = hra; }
    public String getNazev() { return "vezmi"; }
    public String provedPrikaz(String... parametry) { return ""; }
}

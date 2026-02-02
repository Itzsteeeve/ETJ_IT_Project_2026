public class PrikazJdi implements IPrikaz {
    private Hra hra;
    public PrikazJdi(Hra hra) { this.hra = hra; }
    public String getNazev() { return "jdi"; }
    public String provedPrikaz(String... parametry) { return ""; }
}

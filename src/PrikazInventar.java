public class PrikazInventar implements IPrikaz {
    private Inventar inv;
    public PrikazInventar(Inventar inv) { this.inv = inv; }
    public String getNazev() { return "inventar"; }

    public String provedPrikaz(String... parametry) {
        return "inventar: " + inv.getSeznamVeci();
    }
}

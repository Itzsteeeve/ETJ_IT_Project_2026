public class PrikazNapoveda implements IPrikaz {
    private SeznamPrikazu seznam;
    public PrikazNapoveda(SeznamPrikazu seznam) { this.seznam = seznam; }
    public String getNazev() { return "napoveda"; }
    public String provedPrikaz(String... parametry) {
        return "Dostupne prikazy: " + seznam.vratNazvyPrikazu();
    }
}

public class PrikazPomoc implements IPrikaz {
    private SeznamPrikazu sp;
    public PrikazPomoc(SeznamPrikazu sp) { this.sp = sp; }
    public String getNazev() { return "pomoc"; }
    public String provedPrikaz(String... parametry) { return ""; }
}

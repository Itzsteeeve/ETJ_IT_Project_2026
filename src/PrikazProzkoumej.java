public class PrikazProzkoumej implements IPrikaz {
    private HerniPlan plan;
    public PrikazProzkoumej(HerniPlan plan) { this.plan = plan; }
    public String getNazev() { return "prozkoumej"; }
    public String provedPrikaz(String... parametry) { return ""; }
}

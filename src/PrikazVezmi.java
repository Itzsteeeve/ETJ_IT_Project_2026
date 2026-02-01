public class PrikazVezmi implements IPrikaz {
    private HerniPlan plan;
    public PrikazVezmi(HerniPlan plan) { this.plan = plan; }
    public String getNazev() { return "vezmi"; }
    public String provedPrikaz(String... parametry) { return ""; }
}

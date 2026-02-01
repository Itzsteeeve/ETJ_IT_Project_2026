public class PrikazMluv implements IPrikaz {
    private HerniPlan plan;
    public PrikazMluv(HerniPlan plan) { this.plan = plan; }
    public String getNazev() { return "mluv"; }
    public String provedPrikaz(String... parametry) { return ""; }
}

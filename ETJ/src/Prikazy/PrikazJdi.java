package Prikazy;

public class PrikazJdi implements IPrikaz {
    private HerniPlan plan;
    public PrikazJdi(HerniPlan plan) { this.plan = plan; }
    public String getNazev() { return "jdi"; }
    public String provedPrikaz(String... parametry) { return ""; }
}

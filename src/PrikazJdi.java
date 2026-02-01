public class PrikazJdi implements IPrikaz {
    private HerniPlan plan;
    public PrikazJdi(HerniPlan plan) { this.plan = plan; }
    public String getNazev() { return "jdi"; }
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Kam mám jít? Musíš zadat název místnosti.";
        }
        String cil = parametry[0];
        Prostor aktual = plan.getAktualniProstor();
        Prostor novy = aktual.vratSousedniProstor(cil);
        if (novy == null) {
            return "Tam odsud nejdeš: '"+ cil +"' není dostupné z tohoto.";
        }
        plan.setAktualniProstor(novy);
        return "Přesunuto do: "+novy.getNazev()+" - "+novy.getPopis();
    }
}

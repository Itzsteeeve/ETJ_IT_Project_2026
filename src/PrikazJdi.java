public class PrikazJdi implements IPrikaz {
    private Hra hra;
    public PrikazJdi(Hra hra) { this.hra = hra; }
    public String getNazev() { return "jdi"; }
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Musíš zadat název místnosti.";
        }
        String cil = parametry[0];
        Prostor novy = hra.getProstory().get(cil);
        if (novy == null) {
            return "Prostor '" + cil + "' neexistuje.";
        }
        if (!novy.getPatro().equals(hra.getAktualniProstor().getPatro())) {
            return "Nemůžeš jít do jiného patra.";
        }
        hra.setAktualniProstor(novy);
        return "Přesunuto do: "+novy.getNazev()+" - "+novy.getPopis();
    }
}

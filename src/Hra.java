public class Hra {
    private SeznamPrikazu platnePrikazy;
    private HerniPlan herniPlan;
    private boolean konecHry = false;

    public Hra() {
        this.herniPlan = new HerniPlan();
        this.platnePrikazy = new SeznamPrikazu();
        pripravPrikazy();
    }

    private void pripravPrikazy() {
        // vložení základních příkazů
        this.platnePrikazy.vlozPrikaz(new PrikazJdi(this.herniPlan));
        this.platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        this.platnePrikazy.vlozPrikaz(new PrikazNapoveda());
        this.platnePrikazy.vlozPrikaz(new PrikazInventar(this.herniPlan.getInventar()));
        this.platnePrikazy.vlozPrikaz(new PrikazVezmi(this.herniPlan));
    }

    public String zpracujPrikaz(String radek) {
        if (radek == null || radek.trim().isEmpty()) return "Neplatný příkaz.";
        String[] parts = radek.trim().split("\\s+", 2);
        String prikaz = parts[0];
        String args = parts.length > 1 ? parts[1] : "";

        IPrikaz p = platnePrikazy.vratPrikaz(prikaz);
        if (p == null) return "Neznámý příkaz: " + prikaz;
        if (args.isEmpty()) {
            return p.provedPrikaz();
        } else {
            return p.provedPrikaz(args.split("\\s+"));
        }
    }

    public boolean jeKonec() { return konecHry; }
    public void setKonecHry(boolean stav) { this.konecHry = stav; }
    public HerniPlan getHerniPlan() { return herniPlan; }
}
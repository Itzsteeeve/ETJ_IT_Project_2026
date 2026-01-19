package Prikazy;

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
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazVezmi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPouzij(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazMluv(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazInventar(herniPlan.getInventar()));
        platnePrikazy.vlozPrikaz(new PrikazNapoveda());
        platnePrikazy.vlozPrikaz(new PrikazPomoc(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
    }

    public String zpracujPrikaz(String radek) {
        // Implementace analýzy řetězce a vyvolání příkazu
        return "";
    }

    public boolean jeKonec() { return konecHry; }
    public void setKonecHry(boolean stav) { this.konecHry = stav; }
    public HerniPlan getHerniPlan() { return herniPlan; }
}
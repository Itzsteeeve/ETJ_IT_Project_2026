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

    }

    public String zpracujPrikaz(String radek) {
        return "";
    }

    public boolean jeKonec() { return konecHry; }
    public void setKonecHry(boolean stav) { this.konecHry = stav; }
    public HerniPlan getHerniPlan() { return herniPlan; }
}
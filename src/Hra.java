import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hra {
    private SeznamPrikazu platnePrikazy;
    private Map<String, Prostor> prostory;
    private Prostor aktualniProstor;
    private Inventar inventar;
    private boolean konecHry = false;

    public Hra() {
        this.inventar = new Inventar();
        this.prostory = HerniPlan.nactiProstory();
        nastavAktualniProstor();
        nactiAPriradPostavy();
        nactiAPriradPredmety();

        this.platnePrikazy = new SeznamPrikazu();
        pripravPrikazy();
    }

    private void nastavAktualniProstor() {
        if (this.prostory.containsKey("vrátnice")) {
            this.aktualniProstor = this.prostory.get("vrátnice");
        } else if (!this.prostory.isEmpty()) {
            this.aktualniProstor = this.prostory.values().iterator().next();
        }
    }

    private void nactiAPriradPostavy() {
        Map<String, Postava> postavy = HerniPlan.nactiPostavy();
        if (this.prostory.containsKey("bufet") && postavy.containsKey("bufeták")) {
            this.prostory.get("bufet").setPostava(postavy.get("bufeták"));
        }
        if (this.prostory.containsKey("vrátnice") && postavy.containsKey("uklízečka")) {
            this.prostory.get("vrátnice").setPostava(postavy.get("uklízečka"));
        }
        if (this.prostory.containsKey("kancelář") && postavy.containsKey("školník")) {
            this.prostory.get("kancelář").setPostava(postavy.get("školník"));
        }
    }

    private void nactiAPriradPredmety() {
        Map<String, Predmet> predmety = HerniPlan.nactiPredmety();
        for (Predmet p : predmety.values()) {
            Prostor pr = this.prostory.get(p.getProstor());
            if (pr != null) {
                pr.vlozPredmet(p);
            }
        }
    }

    private void pripravPrikazy() {
        this.platnePrikazy.vlozPrikaz(new PrikazJdi(this));
        this.platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        this.platnePrikazy.vlozPrikaz(new PrikazNapoveda(this.platnePrikazy));
        this.platnePrikazy.vlozPrikaz(new PrikazPomoc(this.platnePrikazy));
        this.platnePrikazy.vlozPrikaz(new PrikazInventar(this.inventar));
        this.platnePrikazy.vlozPrikaz(new PrikazVezmi(this));
        this.platnePrikazy.vlozPrikaz(new PrikazMluv(this));
        this.platnePrikazy.vlozPrikaz(new PrikazProzkoumej(this));
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
    public Prostor getAktualniProstor() { return aktualniProstor; }
    public void setAktualniProstor(Prostor p) { this.aktualniProstor = p; }
    public Inventar getInventar() { return inventar; }
    public Map<String, Prostor> getProstory() { return prostory; }
}
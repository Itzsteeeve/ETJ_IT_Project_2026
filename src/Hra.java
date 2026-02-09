import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Hra {
    private SeznamPrikazu platnePrikazy;
    private Map<String, Prostor> prostory;
    private Prostor aktualniProstor;
    private Inventar inventar;
    private boolean konecHry = false;

    public Hra() {
        this.inventar = new Inventar();
        this.prostory = HerniPlan.zalozProstory();
        nastavAktualniProstor();
        nactiAPriradPostavy();
        nactiAPriradPredmety();

        this.platnePrikazy = new SeznamPrikazu();
        pripravPrikazy();
    }

    private void nastavAktualniProstor() {
        if (this.prostory.containsKey("vratnice")) {
            this.aktualniProstor = this.prostory.get("vratnice");
        } else if (!this.prostory.isEmpty()) {
            this.aktualniProstor = this.prostory.values().iterator().next();
        }
    }

    private void nactiAPriradPostavy() {
        Map<String, Postava> postavy = HerniPlan.nactiPostavy();

        if (this.prostory.containsKey("bufet") && postavy.containsKey("bufetak")) {
            this.prostory.get("bufet").setPostava(postavy.get("bufetak"));
        }
        if (this.prostory.containsKey("vratnice") && postavy.containsKey("skolnik")) {
            this.prostory.get("vratnice").setPostava(postavy.get("skolnik"));
        }
        if (this.prostory.containsKey("kancelar") && postavy.containsKey("uklizecka")) {
            this.prostory.get("kancelar").setPostava(postavy.get("uklizecka"));
        }
    }

    private void nactiAPriradPredmety() {
        Map<String, Predmet> predmety = HerniPlan.nactiPredmety();

        String[] mista = {"suplik", "skrin", "stul"};
        Random random = new Random();

        for (Predmet p : predmety.values()) {
            Prostor pr = this.prostory.get(p.getProstor());
            if (pr == null) continue;

            String misto = mista[random.nextInt(mista.length)];
            pr.vlozPredmetDoMista(misto, p);
        }
    }

    private void pripravPrikazy() {
        this.platnePrikazy.vlozPrikaz(new PrikazJdi(this));
        this.platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        this.platnePrikazy.vlozPrikaz(new PrikazNapoveda(this.platnePrikazy));
        this.platnePrikazy.vlozPrikaz(new PrikazMistnosti(this));
        this.platnePrikazy.vlozPrikaz(new PrikazInventar(this.inventar));
        this.platnePrikazy.vlozPrikaz(new PrikazVezmi(this));
        this.platnePrikazy.vlozPrikaz(new PrikazMluv(this));
        this.platnePrikazy.vlozPrikaz(new PrikazProzkoumej(this));
    }

    public String zpracujPrikaz(String radek) {
        if (radek == null || radek.trim().isEmpty()) return "neplatny prikaz.";
        String[] parts = radek.trim().split("\\s+", 2);
        String prikaz = parts[0];
        String args = parts.length > 1 ? parts[1] : "";

        IPrikaz p = platnePrikazy.vratPrikaz(prikaz);
        if (p == null) {
            return "neznamy prikaz: " + prikaz;
        }
        if (args.isEmpty()) {
            return p.provedPrikaz();
        } else {
            return p.provedPrikaz(args.split("\\s+"));
        }
    }

    public void hraj() {
        System.out.println("=== vitej ve hre escape the jecna ===");
        System.out.println("napis 'napoveda' pro seznam prikazu.");

        if (getAktualniProstor() != null) {
            System.out.println("start: " + getAktualniProstor().getNazev() + " (" + getAktualniProstor().getPatro() + ")");
            System.out.println(getAktualniProstor().getPopis());
        } else {
            System.out.println("nepodarilo se nastavit startovni prostor.");
        }

        Scanner sc = new Scanner(System.in);
        while (!jeKonec()) {
            System.out.print("> ");
            if (!sc.hasNextLine()) break;
            String radek = sc.nextLine();

            String odpoved = zpracujPrikaz(radek);
            if (odpoved != null && !odpoved.isEmpty()) {
                System.out.println(odpoved);
            }

            if (!jeKonec() && getAktualniProstor() != null) {
                System.out.println("aktualne jsi v: " + getAktualniProstor().getNazev() + " (" + getAktualniProstor().getPatro() + ")");
            }
        }

        System.out.println("konec hry.");
    }

    public boolean jeKonec() {
        return konecHry;
    }
    public void setKonecHry(boolean stav) {
        this.konecHry = stav;
    }
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    public void setAktualniProstor(Prostor p) {
        this.aktualniProstor = p;
    }
    public Inventar getInventar() {
        return inventar;
    }
    public Map<String, Prostor> getProstory() {
        return prostory;
    }
}
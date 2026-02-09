import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Hra {
    private SeznamPrikazu platnePrikazy;
    private Map<String, Prostor> prostory;
    private Prostor aktualniProstor;
    private Inventar inventar;
    private boolean konecHry = false;
    private boolean cteckaOpravena = false;
    private boolean hesloZjisteno = false;
    private boolean zadniVychodPromazany = false;
    private final Random random = new Random();

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
        if (this.prostory.containsKey("vratnice") && postavy.containsKey("uklizecka")) {
            this.prostory.get("vratnice").setPostava(postavy.get("uklizecka"));
        }
        if (this.prostory.containsKey("kancelar") && postavy.containsKey("skolnik")) {
            this.prostory.get("kancelar").setPostava(postavy.get("skolnik"));
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

    private String pouzijPredmet(String nazevPredmetu) {
        if (nazevPredmetu == null || nazevPredmetu.trim().isEmpty()) {
            return "musis zadat nazev predmetu.";
        }

        nazevPredmetu = nazevPredmetu.trim();
        Predmet p = inventar.get(nazevPredmetu);
        if (p == null) {
            return "tento predmet nemas v inventari.";
        }

        String misto = aktualniProstor != null ? aktualniProstor.getNazev() : "";

        if (nazevPredmetu.equals("lahvicka s lihem")) {
            if (inventar.obsahuje("spinava karta spravce")) {
                inventar.odeber("lahvicka s lihem");
                inventar.odeber("spinava karta spravce");
                inventar.vloz(new Predmet("cista karta spravce", "cista karta spravce", true, "", "oficialni"));
                return "vycistil jsi kartu spravce. mas: cista karta spravce";
            }
            return "nemas spinavou kartu spravce.";
        }

        if (nazevPredmetu.equals("cista karta spravce")) {
            if (misto.equals("ucebna 8")) {
                inventar.odeber("cista karta spravce");
                hesloZjisteno = true;
                if (!inventar.obsahuje("reditelovo heslo")) {
                    inventar.vloz(new Predmet("reditelovo heslo", "kod z pc: JECNA_KEBAB_2026", true, "", "oficialni"));
                }
                return "na pc se objevil kod: JECNA_KEBAB_2026. do inventare bylo pridano: reditelovo heslo";
            }
            return "tady to nejde pouzit. zkus ucebna 8.";
        }

        if (nazevPredmetu.equals("reditelovo heslo")) {
            if (!hesloZjisteno) {
                return "nejdriv zjisti heslo v ucebna 8.";
            }
            if (misto.equals("kancelar")) {
                inventar.odeber("reditelovo heslo");
                if (!inventar.obsahuje("neaktivni isic")) {
                    inventar.vloz(new Predmet("neaktivni isic", "neaktivni isic", true, "", "oficialni"));
                }
                return "otevrel jsi trezor a vzal: neaktivni isic";
            }
            return "tady heslo nevyuzijes. zkus kancelar.";
        }

        if (nazevPredmetu.equals("aktivator terminalu")) {
            if (!inventar.obsahuje("neaktivni isic")) {
                return "nemas neaktivni isic.";
            }
            if (misto.equals("sporovna")) {
                inventar.odeber("aktivator terminalu");
                inventar.odeber("neaktivni isic");
                if (!inventar.obsahuje("autorizovany isic")) {
                    inventar.vloz(new Predmet("autorizovany isic", "autorizovany isic", true, "", "oficialni"));
                }
                return "aktivoval jsi isic. mas: autorizovany isic";
            }
            return "aktivator terminalu pouzij ve sporovna.";
        }

        if (nazevPredmetu.equals("nahradni cipova ctecka")) {
            if (misto.equals("vratnice")) {
                inventar.odeber("nahradni cipova ctecka");
                cteckaOpravena = true;
                return "opravil jsi ctecku u vchodu.";
            }
            return "ctecku opravis u vratnice.";
        }

        if (nazevPredmetu.equals("autorizovany isic")) {
            if (!cteckaOpravena) {
                return "nejdriv oprav ctecku u vratnice (pouzij nahradni cipova ctecka).";
            }
            if (misto.equals("vratnice")) {
                inventar.odeber("autorizovany isic");
                konecHry = true;
                return "odemkl jsi hlavni dvere a utekl. vyhral jsi!";
            }
            return "autorizovany isic musis pouzit u vratnice.";
        }



        if (nazevPredmetu.equals("mince")) {
            if (misto.equals("bufet")) {
                inventar.odeber("mince");
                return "hodil jsi minci do automatu.";
            }
            return "minci pouzij v bufet.";
        }

        if (nazevPredmetu.equals("horky caj")) {
            if (misto.equals("jidelna") || misto.equals("kancelar") || misto.equals("sporovna")) {
                inventar.odeber("horky caj");
                return "vylil jsi caj na ctecku. dvere se otevrely.";
            }
            return "horky caj pouzij u dveri v 1. patre (napr. jidelna/kancelar/sporovna).";
        }

        if (nazevPredmetu.equals("tiche boty")) {
            inventar.odeber("tiche boty");
            return "obul sis tiche boty.";
        }

        if (nazevPredmetu.equals("olejnicka")) {
            inventar.odeber("olejnicka");
            zadniVychodPromazany = true;
            return "promazal jsi panty u zadniho vychodu. pujde otevrit potichu.";
        }

        if (nazevPredmetu.equals("rezave pacidlo")) {
            if (!zadniVychodPromazany) {
                return "kdyz to vypacis nasilu, bude to delat hluk. nejdriv pouzij olejnicka.";
            }
            inventar.odeber("rezave pacidlo");
            konecHry = true;
            return "vypacil jsi zadni vychod a utekl. vyhral jsi alternativni cestou!";
        }

        return "tento predmet tady nejde pouzit.";
    }

    public String zpracujPrikaz(String radek) {
        if (radek == null || radek.trim().isEmpty()) return "neplatny prikaz.";

        radek = radek.trim();
        if (radek.startsWith("pouzij")) {
            String[] partsPouzij = radek.split("\\s+", 2);
            String nazev = partsPouzij.length > 1 ? partsPouzij[1] : "";
            return pouzijPredmet(nazev);
        }

        String[] parts = radek.split("\\s+", 2);
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

    public String zkontrolujEncounterPoVstupu() {
        if (aktualniProstor == null) return "";
        Postava postava = aktualniProstor.getPostava();
        if (postava == null) return "";

        if (random.nextInt(20) != 0) {
            return "";
        }

        String jmeno = postava.getJmeno();
        String veta = (postava.getVety() != null && !postava.getVety().isEmpty())
                ? postava.getVety().get(random.nextInt(postava.getVety().size()))
                : "hej ty!";

        if ("bufetak".equalsIgnoreCase(jmeno)) {
            return "bufetak na tebe zavola: " + veta;
        }

        String obrannyPredmet;
        if ("skolnik".equalsIgnoreCase(jmeno)) {
            obrannyPredmet = "stary tahak";
        } else if ("uklizecka".equalsIgnoreCase(jmeno)) {
            obrannyPredmet = "ztracena penezenka";
        } else {
            return jmeno + " na tebe promluvi: " + veta;
        }

        if (inventar.obsahuje(obrannyPredmet)) {
            inventar.odeber(obrannyPredmet);
            return jmeno + " na tebe promluvi: " + veta + "\n" +
                    "mel jsi pomocny predmet '" + obrannyPredmet + "' -> prezil jsi.";
        }

        konecHry = true;
        return jmeno + " na tebe promluvi: " + veta + "\n" +
                "nemas pomocny predmet '" + obrannyPredmet + "' -> zemrel jsi.";
    }
}

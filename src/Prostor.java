import java.util.HashMap;
import java.util.Map;

public class Prostor {
    private String nazev;
    private String popis;
    private String patro;
    private Map<String, Prostor> vychody = new HashMap<>();
    private Map<String, Predmet> predmety = new HashMap<>();
    private Postava npc;

    private Map<String, Predmet> mistaObsah = new HashMap<>();

    public Prostor() {
        this.vychody = new HashMap<>();
        this.predmety = new HashMap<>();
        if (mistaObsah == null) mistaObsah = new HashMap<>();
    }

    public Prostor(String nazev, String popis, String patro) {
        this.nazev = nazev;
        this.popis = popis;
        this.patro = patro;
        if (mistaObsah == null) mistaObsah = new HashMap<>();
    }

    private boolean jeZnameMisto(String key) {
        return "suplik".equals(key) || "skrin".equals(key) || "stul".equals(key);
    }

    private String zpravaNicNeni(String key) {
        if ("stul".equals(key)) return "na stole nic nelezi.";
        if ("skrin".equals(key)) return "ve skrini nic neni.";
        if ("suplik".equals(key)) return "v supliku nic neni.";
        return "nic tam neni.";
    }

    @Override
    public String toString() {
        return "Prostor{" +
                "nazev='" + nazev + '\'' +
                ", popis='" + popis + '\'' +
                ", patro='" + patro + '\'' +
                ", predmety=" + predmety +
                '}';
    }

    public void setVychod(Prostor vedlejsi) { vychody.put(vedlejsi.getNazev(), vedlejsi); }
    public Prostor vratSousedniProstor(String nazev) { return vychody.get(nazev); }
    public void vlozPredmet(Predmet p) { predmety.put(p.getNazev(), p); }
    public Predmet odeberPredmet(String nazev) { return predmety.remove(nazev); }
    public void setPostava(Postava p) { this.npc = p; }
    public Postava getPostava() { return npc; }
    public String getNazev() { return nazev; }
    public String getPopis() { return popis; }
    public String getPatro() { return patro; }

    public void vlozPredmetDoMista(String misto, Predmet predmet) {
        if (misto == null || predmet == null) return;
        if (mistaObsah == null) mistaObsah = new HashMap<>();

        String key = misto.trim().toLowerCase();
        if (!jeZnameMisto(key)) return;
        mistaObsah.put(key, predmet);
    }

    /**
     * prozkoumej konkretni misto v prostoru (suplik, skrin, stul) a pokud tam je predmet, vloz ho do prostoru a odeber z mista
     * @param misto - nazev mista k prozkoumani (suplik, skrin, stul)
     * @return textovy popis vysledku prozkoumavani mista, pokud misto neni znamo, vraci se chybova hlaska,
     * pokud misto je prazdne, vraci se zprava o prazdnem miste, pokud misto obsahuje predmet, vraci se zprava o nalezeni predmetu a instrukce jak ho sebrat
     */

    public String prozkoumejMisto(String misto) {
        if (misto == null) return "";
        if (mistaObsah == null) mistaObsah = new HashMap<>();

        String key = misto.trim().toLowerCase();
        if (!jeZnameMisto(key)) {
            return "takove misto tu neni. muzes prozkoumat: suplik, skrin, stul";
        }

        Predmet nalez = mistaObsah.get(key);
        if (nalez == null) {
            return zpravaNicNeni(key);
        }

        mistaObsah.put(key, null);
        vlozPredmet(nalez);
        return "nasel jsi predmet: " + nalez.getNazev() + ".\n" +
                "muzes ho sebrat prikazem: vezmi " + nalez.getNazev();
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public void setPatro(String patro) {
        this.patro = patro;
    }
}
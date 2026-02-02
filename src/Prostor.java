import java.util.HashMap;
import java.util.Map;

public class Prostor {
    private String nazev;
    private String popis;
    private String patro;
    private Map<String, Prostor> vychody = new HashMap<>();
    private Map<String, Predmet> predmety = new HashMap<>();
    private Postava npc;

    // Jackson potřebuje bezparametrický konstruktor
    public Prostor() {
        this.vychody = new HashMap<>();
        this.predmety = new HashMap<>();
    }

    public Prostor(String nazev, String popis, String patro) {
        this.nazev = nazev;
        this.popis = popis;
        this.patro = patro;
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
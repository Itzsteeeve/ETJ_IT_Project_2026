import java.util.HashMap;
import java.util.Map;

public class Prostor {
    private String nazev;
    private String popis;
    private String patro;
    private Map<String, Prostor> vychody = new HashMap<>();
    private Map<String, Predmet> predmety = new HashMap<>();

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
                ", vychody=" + vychody +
                ", predmety=" + predmety +
                '}';
    }

    public void setVychod(Prostor vedlejsi) { vychody.put(vedlejsi.getNazev(), vedlejsi); }
    public Prostor vratSousedniProstor(String nazev) { return vychody.get(nazev); }
    public void vlozPredmet(Predmet p) { predmety.put(p.getNazev(), p); }
    public Predmet odeberPredmet(String nazev) { return predmety.remove(nazev); }
    public String getNazev() { return nazev; }
    public String getPopis() { return popis; }
    public String getPatro() { return patro; }
}
import java.util.HashMap;
import java.util.Map;

public class Prostor {
    private String nazev;
    private String popis;
    private Map<String, Prostor> vychody = new HashMap<>();
    private Map<String, Predmet> predmety = new HashMap<>();
    private Postava npc;

    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
    }

    public void setVychod(Prostor vedlejsi) { vychody.put(vedlejsi.getNazev(), vedlejsi); }
    public Prostor vratSousedniProstor(String nazev) { return vychody.get(nazev); }
    public void vlozPredmet(Predmet p) { predmety.put(p.getNazev(), p); }
    public Predmet odeberPredmet(String nazev) { return predmety.remove(nazev); }
    public void setPostava(Postava p) { this.npc = p; }
    public Postava getPostava() { return npc; }
    public String getNazev() { return nazev; }
}
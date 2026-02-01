import java.util.Map;
import java.util.HashMap;

public class SeznamPrikazu {
    private Map<String, IPrikaz> mapaPrikazu = new HashMap<>();
    public void vlozPrikaz(IPrikaz p) { mapaPrikazu.put(p.getNazev(), p); }
    public IPrikaz vratPrikaz(String nazev) { return mapaPrikazu.get(nazev); }
    public String vratNazvyPrikazu() { return String.join(", ", mapaPrikazu.keySet()); }
}
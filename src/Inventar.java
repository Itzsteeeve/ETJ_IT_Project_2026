import java.util.HashMap;
import java.util.Map;

public class Inventar {
    private Map<String, Predmet> veci = new HashMap<>();

    public boolean vloz(Predmet p) {
        String typ = p.getPouziti();
        int countOficialniAlternativni = 0;
        int countPomocny = 0;
        for (Predmet pred : veci.values()) {
            String t = pred.getPouziti();
            if (t.equals("pomocny")) {
                countPomocny++;
            } else if (t.equals("oficialni") || t.equals("alternativni")) {
                countOficialniAlternativni++;
            }
        }
        if (typ.equals("pomocny")) {
            if (countPomocny >= 1) {
                return false;
            }
        } else if (typ.equals("oficialni") || typ.equals("alternativni")) {
            if (countOficialniAlternativni >= 3) {
                return false;
            }
        }
        veci.put(p.getNazev(), p);
        return true;
    }

    public Predmet odeber(String nazev) {
        return veci.remove(nazev);
    }

    public String getSeznamVeci() {
        if (veci.isEmpty()) {
            return "prázdný";
        }
        return String.join(", ", veci.keySet());
    }
}
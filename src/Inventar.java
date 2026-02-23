import java.util.HashMap;
import java.util.Map;

public class Inventar {
    private Map<String, Predmet> veci = new HashMap<>();

    /**
     * vlozi predmet do inventare, pokud neprekroci limit pro dany typ
     * @param p - predmet k vlozeni
     * @return true, pokud se predmet vlozil, false pokud prekrocil limit
     */

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

    public Predmet get(String nazev) {
        return veci.get(nazev);
    }

    public boolean obsahuje(String nazev) {
        return veci.containsKey(nazev);
    }

    public String getSeznamVeci() {
        if (veci.isEmpty()) {
            return "prazdny";
        }
        return String.join(", ", veci.keySet());
    }
}
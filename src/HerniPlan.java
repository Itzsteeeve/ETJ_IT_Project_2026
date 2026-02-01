import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class HerniPlan {
    private Prostor aktualniProstor;
    private Inventar inventar;

    public HerniPlan() {
        this.inventar = new Inventar();
        zalozProstory();
    }

    private void zalozProstory() {
        try (InputStream input = new FileInputStream("resource/Mistnosti.json")) {
            ObjectMapper mapper = new ObjectMapper();
            Map[] rooms = mapper.readValue(input, Map[].class);

            Map<String, Prostor> prostory = new HashMap<>();

            if (rooms != null) {
                for (Map raw : rooms) {
                    Object n = raw.get("nazev");
                    if (n == null) continue;
                    String nazev = n.toString();
                    Object p = raw.get("popis");
                    Object patroObj = raw.get("patro");
                    String popis = p == null ? "" : p.toString();
                    String patro = patroObj == null ? "" : patroObj.toString();
                    prostory.put(nazev, new Prostor(nazev, popis, patro));
                }
            }

            for (Prostor p1 : prostory.values()) {
                for (Prostor p2 : prostory.values()) {
                    if (!p1.getNazev().equals(p2.getNazev()) && p1.getPatro().equals(p2.getPatro())) {
                        p1.setVychod(p2);
                    }
                }
            }

            if (prostory.containsKey("vrátnice")) {
                this.aktualniProstor = prostory.get("vrátnice");
            } else if (!prostory.isEmpty()) {
                this.aktualniProstor = prostory.values().iterator().next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Prostor getAktualniProstor() { return aktualniProstor; }
    public void setAktualniProstor(Prostor p) { this.aktualniProstor = p; }
    public Inventar getInventar() { return inventar; }
}
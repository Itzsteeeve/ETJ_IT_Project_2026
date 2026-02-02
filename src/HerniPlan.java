import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class HerniPlan {

    public static Map<String, Postava> nactiPostavy() {
        Map<String, Postava> postavy = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try (FileReader reader = new FileReader("resource/Postavy.json")) {
            Postava[] nactene = mapper.readValue(reader, Postava[].class);

            if (nactene != null) {
                for (Postava p : nactene) {
                    if (p == null || p.getJmeno() == null) continue;
                    postavy.put(p.getJmeno(), p);
                }
            }
        } catch (Exception e) {
            System.err.println("Chyba při načítání postav: " + e.getMessage());
        }

        return postavy;
    }

    public static Map<String, Prostor> zalozProstory() {
        Map<String, Prostor> prostory = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try (FileReader reader = new FileReader("resource/Mistnosti.json")) {
            Prostor[] nactene = mapper.readValue(reader, Prostor[].class);

            if (nactene != null) {
                for (Prostor p : nactene) {
                    if (p == null || p.getNazev() == null) continue;
                    prostory.put(p.getNazev(), p);
                }
            }
        } catch (Exception e) {
            System.err.println("Chyba při načítání místností: " + e.getMessage());
        }

        if (prostory.isEmpty()) {
            System.err.println("Nebyly načteny žádné místnosti z JSONu.");
        }

        return prostory;
    }

    public static Map<String, Predmet> nactiPredmety() {
        Map<String, Predmet> predmety = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try (FileReader reader = new FileReader("resource/Predmety.json")) {
            Predmet[] nactene = mapper.readValue(reader, Predmet[].class);

            if (nactene != null) {
                for (Predmet p : nactene) {
                    if (p == null || p.getNazev() == null) continue;
                    predmety.put(p.getNazev(), p);
                }
            }
        } catch (Exception e) {
            System.err.println("Chyba při načítání předmětů: " + e.getMessage());
        }

        return predmety;
    }
}
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HerniPlan {

    public static Map<String, Postava> nactiPostavy() {
        Map<String, Postava> postavy = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream input = new FileInputStream("resource/Postavy.json")) {
            @SuppressWarnings("unchecked")
            Map<String, Object>[] characters = (Map<String, Object>[]) mapper.readValue(input, Map[].class);

            if (characters != null) {
                for (Map<String, Object> raw : characters) {
                    Object n = raw.get("jmeno");
                    if (n == null) continue;
                    String jmeno = n.toString();
                    Object p = raw.get("popis");
                    String popis = p == null ? "" : p.toString();
                    Object v = raw.get("vety");
                    List<String> vety = new ArrayList<>();
                    if (v instanceof List) {
                        for (Object veta : (List<?>) v) {
                            vety.add(veta.toString());
                        }
                    }
                    postavy.put(jmeno, new Postava(jmeno, popis, vety));
                }
            }
        } catch (Exception e) {
            System.err.println("Chyba při načítání postav: " + e.getMessage());
        }
        return postavy;
    }

    public static Map<String, Prostor> nactiProstory() {
        Map<String, Prostor> prostory = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        try (InputStream input = new FileInputStream("resource/Mistnosti.json")) {
            @SuppressWarnings("unchecked")
            Map<String, Object>[] rooms = (Map<String, Object>[]) mapper.readValue(input, Map[].class);

            if (rooms != null) {
                for (Map<String, Object> raw : rooms) {
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
        } catch (Exception e) {
            System.err.println("Chyba při načítání" + e.getMessage());
        }

        if (prostory.isEmpty()) {
            System.err.println("Nebyly načteny žádné místnosti z JSONu.");
        }

        return prostory;
    }

    public static Map<String, Predmet> nactiPredmety() {
        Map<String, Predmet> predmety = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream input = new FileInputStream("resource/Predmety.json")) {
            @SuppressWarnings("unchecked")
            Map<String, Object>[] items = (Map<String, Object>[]) mapper.readValue(input, Map[].class);

            if (items != null) {
                for (Map<String, Object> raw : items) {
                    Object n = raw.get("nazev");
                    if (n == null) continue;
                    String nazev = n.toString();
                    Object p = raw.get("popis");
                    String popis = p == null ? "" : p.toString();
                    Object pr = raw.get("prenositelny");
                    boolean prenositelny = pr != null && Boolean.parseBoolean(pr.toString());
                    Object pro = raw.get("prostor");
                    String prostor = pro == null ? "" : pro.toString();
                    Object c = raw.get("pouziti");
                    String pouziti = c == null ? "" : c.toString();
                    predmety.put(nazev, new Predmet(nazev, popis, prenositelny, prostor, pouziti));
                }
            }
        } catch (Exception e) {
            System.err.println("Chyba při načítání předmětů: " + e.getMessage());
        }
        return predmety;
    }
}
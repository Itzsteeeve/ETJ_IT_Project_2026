import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class tests {

    @Test
    void inventarVloz() {
        Inventar inv = new Inventar();
        Predmet p = new Predmet("klic", "", true, "", "oficialni");

        assertTrue(inv.vloz(p));
        assertTrue(inv.obsahuje("klic"));
    }

    @Test
    void inventarLimit3() {
        Inventar inv = new Inventar();

        assertTrue(inv.vloz(new Predmet("a", "", true, "", "oficialni")));
        assertTrue(inv.vloz(new Predmet("b", "", true, "", "alternativni")));
        assertTrue(inv.vloz(new Predmet("c", "", true, "", "oficialni")));

        assertFalse(inv.vloz(new Predmet("d", "", true, "", "alternativni")));
    }

    @Test
    void prostorProzkoumejSuplik() {
        Prostor p = new Prostor("x", "y", "z");
        p.vlozPredmetDoMista("suplik", new Predmet("klic", "", true, "", "oficialni"));

        String msg = p.prozkoumejMisto("suplik");
        assertNotNull(msg);
        assertTrue(msg.contains("klic"));

        Predmet vzaty = p.odeberPredmet("klic");
        assertNotNull(vzaty);

        assertEquals("v supliku nic neni.", p.prozkoumejMisto("suplik"));
    }

    @Test
    void nacteniPredmetuZJsonu() {
        Map<String, Predmet> predmety = HerniPlan.nactiPredmety();
        assertNotNull(predmety);
        assertFalse(predmety.isEmpty());

        Predmet any = predmety.values().iterator().next();
        assertNotNull(any);
        assertNotNull(any.getNazev());
    }

    @Test
    void inventarLimit1Pomocny() {
        Inventar inv = new Inventar();

        assertTrue(inv.vloz(new Predmet("pom1", "", true, "", "pomocny")));
        assertFalse(inv.vloz(new Predmet("pom2", "", true, "", "pomocny")));
        assertTrue(inv.obsahuje("pom1"));
        assertFalse(inv.obsahuje("pom2"));
    }

    @Test
    void prostorNeznameMisto() {
        Prostor p = new Prostor("x", "y", "z");
        assertEquals("takove misto tu neni. muzes prozkoumat: suplik, skrin, stul", p.prozkoumejMisto("postel"));
    }

    @Test
    void hraNeznamyPrikaz() {
        Hra hra = new Hra();
        assertEquals("neznamy prikaz: blabla", hra.zpracujPrikaz("blabla"));
    }
}

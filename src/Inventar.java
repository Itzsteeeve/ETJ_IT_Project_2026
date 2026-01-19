package Prikazy;

import java.util.HashMap;
import java.util.Map;

public class Inventar {
    private Map<String, Predmet> veci = new HashMap<>();
    private final int MAX_KAPACITA = 5;

    public boolean vloz(Predmet p) { return false; }
    public Predmet odeber(String nazev) { return null; }
    public String getSeznamVeci() { return ""; }
}
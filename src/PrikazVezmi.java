public class PrikazVezmi implements IPrikaz {
    private Hra hra;
    public PrikazVezmi(Hra hra) { this.hra = hra; }
    public String getNazev() { return "vezmi"; }

    /**
     * Provadi prikaz "vezmi". Pokusi se vzit predmet z aktualniho prostoru a vlozit ho do inventare.
     * @param parametry - ocekava se jeden parametr, ktery je nazev predmetu, ktery chce hrac vzit. Pokud je parametru 0, vraci se chybova hlaska.
     *                 Pokud je parametru vice, berou se jako jeden nazev predmetu (spojene mezery jsou ignorovany).
     * @return textovy popis vysledku pokusu o vziti predmetu
     */

    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Zadej nazev predmetu.";
        }

        String nazev;
        if (parametry.length == 1) {
            nazev = parametry[0];
        } else {
            nazev = String.join(" ", parametry);
        }
        nazev = nazev.trim();

        Prostor aktual = hra.getAktualniProstor();
        Predmet p = aktual.odeberPredmet(nazev);
        if (p == null) {
            return "Predmet '" + nazev + "' zde neni.";
        }
        if (!p.jePrenositelny()) {
            aktual.vlozPredmet(p);
            return "Predmet '" + nazev + "' nelze vzit.";
        }
        Inventar inv = hra.getInventar();
        if (inv.vloz(p)) {
            return "Vzal jsi: " + nazev;
        } else {
            aktual.vlozPredmet(p);
            return "Nemuzes vzit vice predmetu tohoto typu.";
        }
    }
}

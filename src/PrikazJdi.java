public class PrikazJdi implements IPrikaz {
    private Hra hra;
    public PrikazJdi(Hra hra) { this.hra = hra; }
    public String getNazev() { return "jdi"; }

    /**
     * Provede příkaz "jdi". Přesune hráče do jiné místnosti, pokud existuje.
     * @param parametry - název cílové místnosti (může být více slov)
     * @return textový popis výsledku pokusu o přesun, včetně popisu nové místnosti a případných encounterů
     */

    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "musis zadat nazev mistnosti.";
        }

        String cil;
        if (parametry.length == 1) {
            cil = parametry[0];
        } else {
            cil = String.join(" ", parametry);
        }
        cil = cil.trim();

        Prostor novy = null;
        for (Prostor p : hra.getProstory().values()) {
            if (p == null || p.getNazev() == null) continue;
            if (p.getNazev().equalsIgnoreCase(cil)) {
                novy = p;
                break;
            }
        }

        if (novy == null) {
            return "prostor '" + cil + "' neexistuje.";
        }

        hra.setAktualniProstor(novy);
        String res = "presunuto do: " + novy.getNazev() + " - " + novy.getPopis();

        String encounter = hra.zkontrolujEncounterPoVstupu();
        if (encounter != null && !encounter.isEmpty()) {
            res += "\n" + encounter;
        }

        return res;
    }
}

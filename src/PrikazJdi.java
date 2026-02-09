public class PrikazJdi implements IPrikaz {
    private Hra hra;
    public PrikazJdi(Hra hra) { this.hra = hra; }
    public String getNazev() { return "jdi"; }

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
        return "presunuto do: " + novy.getNazev() + " - " + novy.getPopis();
    }


}

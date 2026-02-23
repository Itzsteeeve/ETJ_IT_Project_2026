public class PrikazProzkoumej implements IPrikaz {
    private Hra hra;
    public PrikazProzkoumej(Hra hra) { this.hra = hra; }
    public String getNazev() { return "prozkoumej"; }

    /**
     * Prozkoumej aktualni prostor, nebo konkretni misto v prostoru (suplik, skrin, stul).
     * @param parametry - pokud je zadano, prozkouma konkretni misto, jinak prozkouma cely prostor
     * @return textovy popis aktualniho prostoru nebo konkretniho mista
     */

    public String provedPrikaz(String... parametry) {
        Prostor aktual = hra.getAktualniProstor();
        if (aktual == null) return "nejsi v zadnem prostoru.";

        if (parametry != null && parametry.length > 0 && parametry[0] != null && !parametry[0].trim().isEmpty()) {
            String misto = parametry[0];
            return aktual.prozkoumejMisto(misto);
        }

        String result = "jsi v: " + aktual.getNazev() + " (" + aktual.getPatro() + ")\n";
        result += "popis: " + aktual.getPopis() + "\n";
        result += "muzes prozkoumat: suplik, skrin, stul\n";
        result += "pouziti: prozkoumej suplik/skrin/stul\n";

        Postava postava = aktual.getPostava();
        if (postava != null) {
            result += "zde je: " + postava.getJmeno() + "\n";
        }
        return result;
    }
}

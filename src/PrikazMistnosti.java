public class PrikazMistnosti implements IPrikaz {
    private final Hra hra;

    public PrikazMistnosti(Hra hra) {
        this.hra = hra;
    }

    @Override
    public String getNazev() {
        return "mistnosti";
    }

    /**
     * Vypíše všechny místnosti v budově, seřazené podle pater.
     * @param sb StringBuilder pro sestavení výsledného textu
     * @param patroNazev název patra, pro které se mají místnosti vypisovat
     */

    private void vypisPatro(StringBuilder sb, String patroNazev) {
        sb.append("\n").append(patroNazev).append(":\n");
        for (Prostor p : hra.getProstory().values()) {
            if (p == null) continue;
            if (p.getPatro() == null) continue;

            if (p.getPatro().equalsIgnoreCase(patroNazev)) {
                sb.append("- ").append(p.getNazev()).append("\n");
            }
        }
    }


    @Override
    public String provedPrikaz(String... parametry) {
        StringBuilder sb = new StringBuilder();
        sb.append("mistnosti v budove (podle pater):\n");

        vypisPatro(sb, "prizemi");
        vypisPatro(sb, "1. patro");
        vypisPatro(sb, "2. patro");
        vypisPatro(sb, "3. patro");
        vypisPatro(sb, "4. patro");

        return sb.toString().trim();
    }
}

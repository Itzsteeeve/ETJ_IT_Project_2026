public class PrikazVezmi implements IPrikaz {
    private Hra hra;
    public PrikazVezmi(Hra hra) { this.hra = hra; }
    public String getNazev() { return "vezmi"; }
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Zadej název předmětu.";
        }
        String nazev = parametry[0];
        Prostor aktual = hra.getAktualniProstor();
        Predmet p = aktual.odeberPredmet(nazev);
        if (p == null) {
            return "Předmět '" + nazev + "' zde není.";
        }
        if (!p.jePrenositelny()) {
            aktual.vlozPredmet(p);
            return "Předmět '" + nazev + "' nelze vzít.";
        }
        Inventar inv = hra.getInventar();
        if (inv.vloz(p)) {
            return "Vzal jsi: " + nazev;
        } else {
            aktual.vlozPredmet(p);
            return "Nemůžeš vzít více předmětů tohoto typu. ";
        }
    }
}

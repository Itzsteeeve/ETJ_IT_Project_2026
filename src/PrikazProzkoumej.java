public class PrikazProzkoumej implements IPrikaz {
    private Hra hra;
    public PrikazProzkoumej(Hra hra) { this.hra = hra; }
    public String getNazev() { return "prozkoumej"; }
    public String provedPrikaz(String... parametry) {
        Prostor aktual = hra.getAktualniProstor();
        String result = "Jsi v: " + aktual.getNazev()+" "+aktual.getPatro();
        result += "Popis: " + aktual.getPopis() + "\n";
        Postava postava = aktual.getPostava();
        if (postava != null) {
            result += "Zde je: " + postava.getJmeno() + "\n";
        }
        return result;
    }
}

public class PrikazMluv implements IPrikaz {
    private Hra hra;
    public PrikazMluv(Hra hra) { this.hra = hra; }
    public String getNazev() { return "mluv"; }

    public String provedPrikaz(String... parametry) {
        Prostor aktual = hra.getAktualniProstor();
        Postava postava = aktual.getPostava();
        if (postava == null) {
            return "v tomto prostoru neni zadna postava";
        }
        return postava.getJmeno() + " rika: " + String.join(" ", postava.getVety());
    }
}

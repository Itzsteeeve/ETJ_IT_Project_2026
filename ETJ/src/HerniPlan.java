package Prikazy;

public class HerniPlan {
    private Prostor aktualniProstor;
    private Inventar inventar;

    public HerniPlan() {
        this.inventar = new Inventar();
        zalozProstory();
    }

    private void zalozProstory() {
    }

    public Prostor getAktualniProstor() { return aktualniProstor; }
    public void setAktualniProstor(Prostor p) { this.aktualniProstor = p; }
    public Inventar getInventar() { return inventar; }
}
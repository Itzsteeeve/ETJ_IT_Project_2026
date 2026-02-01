public class Main {
    public static void main(String[] args) {
        Hra hra = new Hra();

        System.out.println("--- Automatický test HerniPlan / jdi ---");

        HerniPlan plan = hra.getHerniPlan();
        Prostor start = plan.getAktualniProstor();
        System.out.println("Aktuální prostor: " + start.getNazev());

    }
}

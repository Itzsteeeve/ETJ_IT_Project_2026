import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Hra hra = new Hra();

        Map<String,Postava> postavy = HerniPlan.nactiPostavy();

        System.out.println(postavy);

        Postava skolnik = postavy.get("školník");
        Postava uklizecka = postavy.get("uklízečka");
        Postava bufetak = postavy.get("bufeták");

        System.out.println(skolnik);
        System.out.println(uklizecka);
        System.out.println(bufetak);

        Map<String,Prostor> prostory = HerniPlan.nactiProstory();
        System.out.println(prostory);

        Prostor ucebna26 = prostory.get("Učebna 26");

        System.out.println(ucebna26);


    }
}

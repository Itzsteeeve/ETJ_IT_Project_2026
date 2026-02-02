import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Hra hra = new Hra();


        Prostor start = hra.getAktualniProstor();
        String vysledek = hra.zpracujPrikaz("prozkoumej");
        String vysledek1 = hra.zpracujPrikaz("mluv");
        String vysledek2 = hra.zpracujPrikaz("napoveda");
        System.out.println("Aktuální prostor: " + start.getNazev() + " (" + start.getPatro() + ")");

        
        System.out.println("Příkaz 'prozkoumej': " + vysledek.replace("\n", " | "));


        System.out.println("Příkaz 'mluv': " + vysledek1);

        
        System.out.println("Příkaz 'napoveda': " + vysledek2);




    }
}

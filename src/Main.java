package Prikazy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hra hra = new Hra();
        Scanner scanner = new Scanner(System.in);


        // Hlavní smyčka hry
        while (!hra.jeKonec()) {
            System.out.print("> ");
            String radek = scanner.nextLine();

            String vysledek = hra.zpracujPrikaz(radek);
            System.out.println(vysledek);
        }

        System.out.println("Děkujeme, že jsi si zahrál Útěk ze SPŠE Ječná. Nashledanou!");
        scanner.close();
    }
}

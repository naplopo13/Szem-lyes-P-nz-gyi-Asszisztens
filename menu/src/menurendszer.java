import java.util.Scanner;

public class Menurendszer {
    private static final PenzugySzolgaltato szerviz = new PenzugySzolgaltato();
    private static final Scanner sc = new Scanner(System.in);

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        int valasztas = -1;
        while (valasztas != 0) {
            KijelzoTiszta();
            System.out.println("=== SZEMÉLYES PÉNZÜGYI ASSZISZTENS ===");
            System.out.println("1. Új tranzakció hozzáadása");
            System.out.println("2. Költségvetési limit beállítása");
            System.out.println("3. Havi összesítés és ASCII riport");
            System.out.println("0. Kilépés");
            System.out.print("Válassz menüpontot: ");

            valasztas = beolvasInt();

            switch (valasztas) {
                case 1 -> tranzakcioMenu();
                case 2 -> limitMenu();
                case 3 -> riportMenu();
                case 0 -> System.out.println("Köszönjük, hogy használta a programot!");
                default -> System.out.println(RED + "Érvénytelen menüpont!" + RESET);
            }
            if (valasztas != 0) {
                System.out.println("\nNyomj Enter-t a folytatáshoz...");
                sc.nextLine();
            }
        }
    }
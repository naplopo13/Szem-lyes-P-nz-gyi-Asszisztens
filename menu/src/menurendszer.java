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

    private static void tranzakcioMenu() {
        System.out.print("Összeg (Ft): ");
        double osszeg = beolvasDouble();
        System.out.print("Dátum (pl. 2026-06-02): ");
        String datum = sc.nextLine();
        System.out.print("Kategória (pl. kaja, szorakozozas): ");
        String kategoria = sc.nextLine().toLowerCase();
        System.out.print("Megjegyzés: ");
        String megjegyzes = sc.nextLine();

        szerviz.ujTranzakcio(osszeg, datum, kategoria, megjegyzes);
        System.out.println(GREEN + "Tranzakció sikeresen rögzítve!" + RESET);

        if (szerviz.isLimitTullepve(kategoria)) {
            System.out.println(RED + "FIGYELMEZTETÉS: A(z) '" + kategoria + "' kategória limitjét túllépted!" + RESET);
        }
    }

    private static void limitMenu() {
        System.out.print("Kategória neve: ");
        String kategoria = sc.nextLine().toLowerCase();
        System.out.print("Havi limit összege (Ft): ");
        double limit = beolvasDouble();

        szerviz.ujLimit(kategoria, limit);
        System.out.println(GREEN + "Limit sikeresen beállítva!" + RESET);
    }

    private static void riportMenu() {
        System.out.println("\n=== HAVI RIPORT & ASCII DIAGRAMOK ===");
        System.out.println("Összes költés: " + YELLOW + szerviz.getOsszKoltseg() + " Ft" + RESET);
        System.out.println("----------------------------------------");

        for (String kat : szerviz.getMindenKategoria()) {
            if (szerviz.isLimitTullepve(kat)) {
                System.out.println(RED + szerviz.getAsciiDiagram(kat) + " [TÚLLÉPVE!]" + RESET);
            } else {
                System.out.println(GREEN + szerviz.getAsciiDiagram(kat) + RESET);
            }
        }
    }

    private static int beolvasInt() {
        while (true) {
            try {
                int szam = Integer.parseInt(sc.nextLine());
                return szam;
            } catch (NumberFormatException e) {
                System.out.print(RED + "Hibás formátum! Kérlek számot adj meg: " + RESET);
            }
        }
    }

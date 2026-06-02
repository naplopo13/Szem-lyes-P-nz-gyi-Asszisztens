import java.util.Scanner;

public class Menurendszer {
    private static final PenzugySzolgaltato szerviz = new PenzugySzolgaltato();
    private static final Scanner sc = new Scanner(System.in);

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
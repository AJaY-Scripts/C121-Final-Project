package utils;

import java.util.Scanner;


public class InputHelper {

    private static Scanner sc = new Scanner(System.in);

    public static String getString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    public static int getInt(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // returns -1 if invalid
        }
    }

    public static double getDouble(String prompt) {
        System.out.print(prompt);
        try {
            return Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1.0;
        }
    }
}
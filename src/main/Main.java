package main;
import models.*;
import services.LibraryManager;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Person> userList = new ArrayList<>();
    static LibraryManager libraryManager = new LibraryManager();

    public static void main(String[] args) {
        setupAccounts();
        System.out.println("==========================================");
        System.out.println("     WELCOME TO LIBRARY MANAGEMENT SYSTEM");
        System.out.println("==========================================");
        int choice = -1;

        do {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Student");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                choice = -1;
                continue;}

            switch (choice) {
                case 1: adminLogin(); break;
                case 2: studentLogin(); break;
                case 0: System.out.println("Thank you! Goodbye."); break;
                default: System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    // setup hardcoded accounts
    private static void setupAccounts() {
        // admin
        userList.add(new Admin("A001", "Adrian John Manaloto",  "aj123", "Library Services"));


        // students
        userList.add(new Student("S001", "Jade Aundrey Balingit", "jade123", "BSCPE", 3));
        userList.add(new Student("S002", "Kim Heart Maluntag",     "kim123",    "BSCE", 2));
        userList.add(new Student("S003", "Franz Joseph Fausto",    "franz123",    "BSABE", 4));
        userList.add(new Student("S004", "Ian Joel Maniago",   "ian123",    "BSGEO", 1));
    }

    // admin login
    private static void adminLogin() {
        System.out.println("\n-- Admin Login --");
        System.out.print("Admin ID  : ");
        String id = sc.nextLine().trim();
        System.out.print("Password  : ");
        String pass = sc.nextLine().trim();

        for (Person p : userList) {
            if (p instanceof Admin
                    && p.getPersonId().equals(id)
                    && p.checkPassword(pass)) {

                System.out.println("\nLogin successful! Welcome, " + p.getName() + "!");
                libraryManager.showAdminMenu((Admin) p);
                return;}
        }
        System.out.println("Invalid admin ID or password. Try again.");}

    // student login
    private static void studentLogin() {
        System.out.println("\n-- Student Login --");
        System.out.print("Student ID: ");
        String id = sc.nextLine().trim();
        System.out.print("Password  : ");
        String pass = sc.nextLine().trim();

        for (Person p : userList) {
            if (p instanceof Student
                    && p.getPersonId().equals(id)
                    && p.checkPassword(pass)) {

                System.out.println("\nLogin successful! Welcome, " + p.getName() + "!");
                libraryManager.showStudentMenu((Student) p);
                return;}
        }
        System.out.println("Invalid student ID or password. Try again.");}
}
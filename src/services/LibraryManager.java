package services;

import exceptions.ItemUnavailableException;
import models.*;

import java.util.ArrayList;
import java.util.Scanner;


public class LibraryManager {

    private ArrayList<LibraryItem> itemList;
    private BorrowManager borrowManager;
    private FileHandler fileHandler;
    private Scanner sc;


    private int bookCounter;
    private int magCounter;
    private int ebookCounter;
    private int rpCounter;

    public LibraryManager() {
        itemList     = new ArrayList<>();
        borrowManager = new BorrowManager();
        fileHandler  = new FileHandler();
        sc           = new Scanner(System.in);


        loadDefaultItems();
    }

    // sample items
    private void loadDefaultItems() {
        itemList.add(new Book("B001", "Java Programming", "Herbert Schildt", 2019, "Technology", 1300));
        itemList.add(new Book("B002", "The Alchemist", "Paulo Coelho", 1988, "Fiction", 208));
        itemList.add(new Book("B003", "Intro to Algorithms", "Thomas Cormen", 2009, "CS", 1292));
        itemList.add(new Magazine("M001", "National Geographic", "Various Authors", 2023, "Issue 45", "Science"));
        itemList.add(new Magazine("M002", "TIME Magazine", "Various Authors", 2024, "Issue 12", "News"));
        itemList.add(new EBook("E001", "Clean Code", "Robert Martin", 2008, "PDF", 2.5));
        itemList.add(new EBook("E002", "Design Patterns", "Gang of Four", 1994, "EPUB", 3.1));
        itemList.add(new ResearchPaper("R001", "Machine Learning Overview", "Andrew Ng", 2020, "IEEE", "AI"));
        itemList.add(new ResearchPaper("R002", "Quantum Computing Basics", "IBM Research", 2022, "Nature", "Physics"));

        bookCounter  = 4;
        magCounter   = 3;
        ebookCounter = 3;
        rpCounter    = 3;
    }

    // ==================== ADMIN MENU ====================

    public void showAdminMenu(Admin admin) {
        int choice = -1;

        do {
            System.out.println("\n========================================");
            System.out.println("       ADMIN PANEL - " + admin.getName());
            System.out.println("========================================");
            System.out.println(" 1.  View All Items");
            System.out.println(" 2.  Add Book");
            System.out.println(" 3.  Add Magazine");
            System.out.println(" 4.  Add EBook");
            System.out.println(" 5.  Add Research Paper");
            System.out.println(" 6.  Remove Item");
            System.out.println(" 7.  Search Item");
            System.out.println(" 8.  View All Borrow History");
            System.out.println(" 9.  Save Records to File");
            System.out.println(" 10. Load Records from File");
            System.out.println(" 0.  Logout");
            System.out.println("========================================");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
                choice = -1;
                continue;
            }

            switch (choice) {
                case 1:  viewAllItems(); break;
                case 2:  addBook(); break;
                case 3:  addMagazine(); break;
                case 4:  addEBook(); break;
                case 5:  addResearchPaper(); break;
                case 6:  removeItem(); break;
                case 7:  searchItem(); break;
                case 8:  borrowManager.displayAllHistory(); break;
                case 9:  saveToFile(); break;
                case 10: loadFromFile(); break;
                case 0:  System.out.println("Logging out..."); break;
                default: System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);
    }

    // ==================== STUDENT MENU ====================

    public void showStudentMenu(Student student) {
        int choice = -1;

        do {
            System.out.println("\n========================================");
            System.out.println("   STUDENT MENU - " + student.getName());
            System.out.println("========================================");
            System.out.println(" 1. View All Items");
            System.out.println(" 2. Borrow Item");
            System.out.println(" 3. Return Item");
            System.out.println(" 4. Search Item");
            System.out.println(" 5. My Borrow History");
            System.out.println(" 0. Logout");
            System.out.println("========================================");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                choice = -1;
                continue;
            }

            switch (choice) {
                case 1: viewAllItems(); break;
                case 2: borrowItem(student); break;
                case 3: returnItem(student); break;
                case 4: searchItem(); break;
                case 5: borrowManager.displayStudentHistory(student.getPersonId()); break;
                case 0: System.out.println("Logging out..."); break;
                default: System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    // ==================== CRUD OPERATIONS ====================

    // view all items
    private void viewAllItems() {
        if (itemList.isEmpty()) {
            System.out.println("No items in the library.");
            return;
        }
        System.out.println("\n--- Library Collection (" + itemList.size() + " items) ---");
        for (LibraryItem item : itemList) {
            item.displayInfo(); // polymorphism here
        }
        System.out.println("--------------------------------------------------");
    }

    // add book
    private void addBook() {
        System.out.println("\n-- Add New Book --");

        System.out.print("Title  : ");
        String title = sc.nextLine().trim();

        System.out.print("Author : ");
        String author = sc.nextLine().trim();

        int year = 0;
        System.out.print("Year   : ");
        try { year = Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException e) { System.out.println("Invalid year. Cancelled."); return; }

        System.out.print("Genre  : ");
        String genre = sc.nextLine().trim();

        int pages = 0;
        System.out.print("Pages  : ");
        try { pages = Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException e) { System.out.println("Invalid page count. Cancelled."); return; }

        String newId = "B" + String.format("%03d", bookCounter++);
        Book newBook = new Book(newId, title, author, year, genre, pages);
        itemList.add(newBook);
        System.out.println("Book added! Assigned ID: " + newId);
    }

    // add magazine
    private void addMagazine() {
        System.out.println("\n-- Add New Magazine --");

        System.out.print("Title     : ");
        String title = sc.nextLine().trim();

        System.out.print("Publisher : ");
        String author = sc.nextLine().trim();

        int year = 0;
        System.out.print("Year      : ");
        try { year = Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException e) { System.out.println("Invalid year. Cancelled."); return; }

        System.out.print("Issue No  : ");
        String issue = sc.nextLine().trim();

        System.out.print("Category  : ");
        String category = sc.nextLine().trim();

        String newId = "M" + String.format("%03d", magCounter++);
        Magazine mag = new Magazine(newId, title, author, year, issue, category);
        itemList.add(mag);
        System.out.println("Magazine added! Assigned ID: " + newId);
    }

    // add ebook
    private void addEBook() {
        System.out.println("\n-- Add New EBook --");

        System.out.print("Title       : ");
        String title = sc.nextLine().trim();

        System.out.print("Author      : ");
        String author = sc.nextLine().trim();

        int year = 0;
        System.out.print("Year        : ");
        try { year = Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException e) { System.out.println("Invalid year. Cancelled."); return; }

        System.out.print("Format      : ");
        String format = sc.nextLine().trim();

        double size = 0;
        System.out.print("File Size MB: ");
        try { size = Double.parseDouble(sc.nextLine().trim()); }
        catch (NumberFormatException e) { System.out.println("Invalid size. Cancelled."); return; }

        String newId = "E" + String.format("%03d", ebookCounter++);
        EBook ebook = new EBook(newId, title, author, year, format, size);
        itemList.add(ebook);
        System.out.println("EBook added! Assigned ID: " + newId);
    }

    // add research paper
    private void addResearchPaper() {
        System.out.println("\n-- Add New Research Paper --");

        System.out.print("Title         : ");
        String title = sc.nextLine().trim();

        System.out.print("Author        : ");
        String author = sc.nextLine().trim();

        int year = 0;
        System.out.print("Year          : ");
        try { year = Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException e) { System.out.println("Invalid year. Cancelled."); return; }

        System.out.print("Journal       : ");
        String journal = sc.nextLine().trim();

        System.out.print("Field of Study: ");
        String field = sc.nextLine().trim();

        String newId = "R" + String.format("%03d", rpCounter++);
        ResearchPaper rp = new ResearchPaper(newId, title, author, year, journal, field);
        itemList.add(rp);
        System.out.println("Research Paper added! Assigned ID: " + newId);
    }

    // remove item
    private void removeItem() {
        System.out.print("Enter item ID to remove: ");
        String id = sc.nextLine().trim();

        LibraryItem target = findById(id);

        if (target == null) {
            System.out.println("Item not found with ID: " + id);
            return;
        }

        if (!target.isAvailable()) {
            System.out.println("Cannot remove item while it is currently borrowed.");
            return;
        }

        itemList.remove(target);
        System.out.println("Removed: " + target.getTitle() + " [" + target.getItemId() + "]");
    }

    // search item
    private void searchItem() {
        System.out.print("Search (title or author): ");
        String keyword = sc.nextLine().toLowerCase().trim();

        if (keyword.isEmpty()) {
            System.out.println("No keyword entered.");
            return;
        }

        boolean found = false;
        System.out.println("\n--- Search Results for: \"" + keyword + "\" ---");

        for (LibraryItem item : itemList) {
            if (item.getTitle().toLowerCase().contains(keyword)
                    || item.getAuthor().toLowerCase().contains(keyword)) {
                item.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No items matched your search.");
        }
    }

    // ==================== BORROW / RETURN ====================

    // borrow item
    private void borrowItem(Student student) {
        System.out.print("Enter item ID to borrow: ");
        String id = sc.nextLine().trim();

        LibraryItem target = findById(id);
        if (target == null) {
            System.out.println("Item not found.");
            return;
        }

        // check if student already borrowed this
        if (borrowManager.hasBorrowed(student.getPersonId(), id)) {
            System.out.println("You already have this item borrowed.");
            return;
        }

        try {
            borrowManager.borrowItem(target, student);
        } catch (ItemUnavailableException e) {
            System.out.println("Cannot borrow: " + e.getMessage());
        }
    }

    // return item
    private void returnItem(Student student) {
        System.out.print("Enter item ID to return: ");
        String id = sc.nextLine().trim();

        LibraryItem target = findById(id);
        if (target == null) {
            System.out.println("Item not found.");
            return;
        }

        if (!borrowManager.hasBorrowed(student.getPersonId(), id)) {
            System.out.println("You don't have an active borrow for this item.");
            return;
        }

        borrowManager.returnItem(target, student.getPersonId());
    }

    // ==================== FILE OPERATIONS ====================

    // save to file
    private void saveToFile() {
        fileHandler.saveItems(itemList);
        fileHandler.saveBorrowRecords(borrowManager.getBorrowHistory());
        System.out.println("All data saved successfully.");
    }

    // load from file
    private void loadFromFile() {
        ArrayList<LibraryItem> loaded = fileHandler.loadItems();
        if (!loaded.isEmpty()) {
            itemList = loaded;
            System.out.println("Item list updated from file.");
        }

        ArrayList<BorrowRecord> recs = fileHandler.loadBorrowRecords();
        if (!recs.isEmpty()) {
            borrowManager.setBorrowHistory(recs);
        }
    }

    // ==================== HELPER ====================

    // find item by id
    private LibraryItem findById(String id) {
        for (LibraryItem item : itemList) {
            if (item.getItemId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }
}
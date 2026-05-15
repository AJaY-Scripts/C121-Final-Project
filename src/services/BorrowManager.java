package services;

import exceptions.ItemUnavailableException;
import models.*;

import java.util.ArrayList;

public class BorrowManager {

    private ArrayList<BorrowRecord> borrowHistory;
    private int recCounter;

    public BorrowManager() {
        borrowHistory = new ArrayList<>();
        recCounter    = 1;
    }

    public void setBorrowHistory(ArrayList<BorrowRecord> history) {
        this.borrowHistory = history;
        this.recCounter    = history.size() + 1;
    }

    public ArrayList<BorrowRecord> getBorrowHistory() {
        return borrowHistory;
    }


    public void borrowItem(LibraryItem item, Person person) throws ItemUnavailableException {
        if (!item.isAvailable()) {
            throw new ItemUnavailableException(
                    "Sorry, '" + item.getTitle() + "' is currently not available for borrowing."
            );
        }

        item.borrowItem(person.getName());

        String recId = "REC" + String.format("%03d", recCounter++);
        BorrowRecord newRec = new BorrowRecord(
                recId,
                person.getPersonId(),
                person.getName(),
                item.getItemId(),
                item.getTitle(),
                item.getItemType()
        );
        borrowHistory.add(newRec);

        System.out.println("Success! You borrowed: " + item.getTitle());
        System.out.println("Record ID: " + recId);
    }


    public boolean returnItem(LibraryItem item, String borrowerId) {
        for (BorrowRecord rec : borrowHistory) {
            if (rec.getItemId().equals(item.getItemId())
                    && rec.getBorrowerId().equals(borrowerId)
                    && !rec.isReturned()) {

                rec.markReturned();
                item.returnItem();
                System.out.println("Successfully returned: " + item.getTitle());
                return true;
            }
        }
        System.out.println("No active borrow record found for this item.");
        return false;
    }


    public void displayAllHistory() {
        if (borrowHistory.isEmpty()) {
            System.out.println("No borrow history yet.");
            return;
        }
        System.out.println("\n--- All Borrow History (" + borrowHistory.size() + " records) ---");
        for (BorrowRecord rec : borrowHistory) {
            rec.displayRecord();
        }
    }


    public void displayStudentHistory(String studentId) {
        System.out.println("\n--- Your Borrow History ---");
        boolean found = false;
        for (BorrowRecord rec : borrowHistory) {
            if (rec.getBorrowerId().equals(studentId)) {
                rec.displayRecord();
                found = true;
            }
        }
        if (!found) {
            System.out.println("You have no borrow history.");
        }
    }


    public boolean hasBorrowed(String borrowerId, String itemId) {
        for (BorrowRecord rec : borrowHistory) {
            if (rec.getBorrowerId().equals(borrowerId)
                    && rec.getItemId().equals(itemId)
                    && !rec.isReturned()) {
                return true;
            }
        }
        return false;
    }
}
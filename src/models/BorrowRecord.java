package models;

import java.time.LocalDate;

public class BorrowRecord {

    private String recordId;
    private String borrowerId;
    private String borrowerName;
    private String itemId;
    private String itemTitle;
    private String itemType;
    private String borrowDate;
    private String returnDate;
    private boolean isReturned;

    public BorrowRecord(String recordId, String borrowerId, String borrowerName,
                        String itemId, String itemTitle, String itemType) {
        this.recordId     = recordId;
        this.borrowerId   = borrowerId;
        this.borrowerName = borrowerName;
        this.itemId       = itemId;
        this.itemTitle    = itemTitle;
        this.itemType     = itemType;
        this.borrowDate   = LocalDate.now().toString();
        this.returnDate   = "N/A";
        this.isReturned   = false;
    }

    public BorrowRecord(String recordId, String borrowerId, String borrowerName,
                        String itemId, String itemTitle, String itemType,
                        String borrowDate, String returnDate, boolean isReturned) {
        this.recordId     = recordId;
        this.borrowerId   = borrowerId;
        this.borrowerName = borrowerName;
        this.itemId       = itemId;
        this.itemTitle    = itemTitle;
        this.itemType     = itemType;
        this.borrowDate   = borrowDate;
        this.returnDate   = returnDate;
        this.isReturned   = isReturned;
    }


    public String getRecordId()     { return recordId; }
    public String getBorrowerId()   { return borrowerId; }
    public String getBorrowerName() { return borrowerName; }
    public String getItemId()       { return itemId; }
    public String getItemTitle()    { return itemTitle; }
    public String getItemType()     { return itemType; }
    public String getBorrowDate()   { return borrowDate; }
    public String getReturnDate()   { return returnDate; }
    public boolean isReturned()     { return isReturned; }


    public void markReturned() {
        this.isReturned = true;
        this.returnDate = LocalDate.now().toString();
    }


    public void displayRecord() {
        System.out.println("  RecordID: " + recordId
                + " | Borrower: " + borrowerName + " (" + borrowerId + ")"
                + " | Item: "     + itemTitle + " [" + itemType + "]"
                + " | Borrowed: " + borrowDate
                + " | Returned: " + returnDate
                + " | Status: "   + (isReturned ? "Returned" : "Not Yet Returned"));
    }


    public String toFileString() {
        return recordId + "|" + borrowerId + "|" + borrowerName + "|"
                + itemId  + "|" + itemTitle  + "|" + itemType     + "|"
                + borrowDate + "|" + returnDate + "|" + isReturned;
    }
}
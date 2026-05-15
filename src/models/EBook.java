package models;

public class EBook extends LibraryItem {

    private String format;   // PDF, EPUB, etc
    private double fileSize; // size in MB

    public EBook(String itemId, String title, String author, int year, String format, double fileSize) {
        super(itemId, title, author, year);
        this.format   = format;
        this.fileSize = fileSize;
    }

    public String getFormat()    { return format; }
    public double getFileSize()  { return fileSize; }

    @Override
    public String getItemType() {
        return "EBook";
    }

    @Override
    public void displayInfo() {
        System.out.println("  [EBOOK]    ID: " + getItemId()
                + " | Title: "    + getTitle()
                + " | Author: "   + getAuthor()
                + " | Year: "     + getYear()
                + " | Format: "   + format
                + " | Size: "     + fileSize + "MB"
                + " | Status: "   + (isAvailable() ? "Available" : "Borrowed"));
    }

    @Override
    public String toFileString() {
        return "EBook|" + super.toFileString() + "|" + format + "|" + fileSize;
    }
}
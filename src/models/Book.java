package models;

// Book class - extends LibraryItem
public class Book extends LibraryItem {

    private String genre;
    private int totalPages;

    public Book(String itemId, String title, String author, int year, String genre, int totalPages) {
        super(itemId, title, author, year);
        this.genre = genre;
        this.totalPages = totalPages;
    }

    public String getGenre()     { return genre; }
    public int getTotalPages()   { return totalPages; }
    public void setGenre(String genre)         { this.genre = genre; }
    public void setTotalPages(int totalPages)  { this.totalPages = totalPages; }

    @Override
    public String getItemType() {
        return "Book";
    }

    // display info - overriding abstract method
    @Override
    public void displayInfo() {
        System.out.println("  [BOOK]     ID: " + getItemId()
                + " | Title: "  + getTitle()
                + " | Author: " + getAuthor()
                + " | Year: "   + getYear()
                + " | Genre: "  + genre
                + " | Pages: "  + totalPages
                + " | Status: " + (isAvailable() ? "Available" : "Borrowed"));
    }

    @Override
    public String toFileString() {
        return "Book|" + super.toFileString() + "|" + genre + "|" + totalPages;
    }
}
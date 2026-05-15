package models;

public class Magazine extends LibraryItem {

    private String issueNo;
    private String category;

    public Magazine(String itemId, String title, String author, int year, String issueNo, String category) {
        super(itemId, title, author, year);
        this.issueNo   = issueNo;
        this.category  = category;}

    public String getIssueNo()   { return issueNo; }
    public String getCategory()  { return category; }

    @Override
    public String getItemType() {
        return "Magazine";}

    @Override
    public void displayInfo() {
        System.out.println("  [MAGAZINE] ID: " + getItemId()
                + " | Title: "    + getTitle()
                + " | Author: "   + getAuthor()
                + " | Year: "     + getYear()
                + " | Issue: "    + issueNo
                + " | Category: " + category
                + " | Status: "   + (isAvailable() ? "Available" : "Borrowed"));}

    @Override
    public String toFileString() {
        return "Magazine|" + super.toFileString() + "|" + issueNo + "|" + category;}}
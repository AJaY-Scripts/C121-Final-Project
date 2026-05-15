package models;

public class ResearchPaper extends LibraryItem {

    private String journal;
    private String fieldOfStudy;

    public ResearchPaper(String itemId, String title, String author, int year, String journal, String fieldOfStudy) {
        super(itemId, title, author, year);
        this.journal      = journal;
        this.fieldOfStudy = fieldOfStudy;}

    public String getJournal()       { return journal; }
    public String getFieldOfStudy()  { return fieldOfStudy; }

    @Override
    public String getItemType() {
        return "ResearchPaper";}

    @Override
    public void displayInfo() {
        System.out.println("  [RESEARCH] ID: " + getItemId()
                + " | Title: "   + getTitle()
                + " | Author: "  + getAuthor()
                + " | Year: "    + getYear()
                + " | Journal: " + journal
                + " | Field: "   + fieldOfStudy
                + " | Status: "  + (isAvailable() ? "Available" : "Borrowed"));}

    @Override
    public String toFileString() {
        return "ResearchPaper|" + super.toFileString() + "|" + journal + "|" + fieldOfStudy;}}
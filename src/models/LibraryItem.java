package models;

import interfaces.Borrowable;

public abstract class LibraryItem implements Borrowable {

    private String itemId;
    private String title;
    private String author;
    private int year;
    private boolean available;


    public LibraryItem(String itemId, String title, String author, int year) {
        this.itemId = itemId;
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = true;
    }


    public String getItemId()  { return itemId; }
    public String getTitle()   { return title; }
    public String getAuthor()  { return author; }
    public int getYear()       { return year; }


    public void setTitle(String title)       { this.title = title; }
    public void setAuthor(String author)     { this.author = author; }
    public void setYear(int year)            { this.year = year; }
    public void setAvailable(boolean val)    { this.available = val; }


    @Override
    public boolean isAvailable() { return available; }

    @Override
    public void borrowItem(String borrowerName) {
        this.available = false;
    }

    @Override
    public void returnItem() {
        this.available = true;
    }


    public abstract String getItemType();
    public abstract void displayInfo();


    public String toFileString() {
        return itemId + "|" + title + "|" + author + "|" + year + "|" + available;
    }
}
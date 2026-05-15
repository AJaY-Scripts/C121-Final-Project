package interfaces;

public interface Borrowable {

    void borrowItem(String borrowerName);
    void returnItem();
    boolean isAvailable();
    String getItemId();
    String getTitle();
}
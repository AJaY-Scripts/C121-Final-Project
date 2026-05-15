package services;

import models.*;

import java.io.*;
import java.util.ArrayList;


public class FileHandler {

    private static final String ITEMS_FILE   = "library_items.txt";
    private static final String RECORDS_FILE = "borrow_records.txt";


    public void saveItems(ArrayList<LibraryItem> items) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ITEMS_FILE));

            for (LibraryItem item : items) {
                bw.write(item.toFileString());
                bw.newLine();
            }
            bw.close();
            System.out.println("[File] Items saved successfully -> " + ITEMS_FILE);

        } catch (IOException e) {
            System.out.println("[Error] Could not save items: " + e.getMessage());}
    }


    public ArrayList<LibraryItem> loadItems() {
        ArrayList<LibraryItem> items = new ArrayList<>();
        File file = new File(ITEMS_FILE);

        if (!file.exists()) {
            System.out.println("[File] No saved items file found.");
            return items;}

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("\\|");

                if (parts.length < 6) {
                    System.out.println("[File] Skipping bad line: " + line);
                    continue;}

                String type = parts[0];


                switch (type) {
                    case "Book":
                        if (parts.length >= 8) {
                            Book b = new Book(parts[1], parts[2], parts[3],
                                    Integer.parseInt(parts[4]), parts[6], Integer.parseInt(parts[7]));
                            b.setAvailable(Boolean.parseBoolean(parts[5]));
                            items.add(b);}
                        break;

                    case "Magazine":
                        if (parts.length >= 8) {
                            Magazine m = new Magazine(parts[1], parts[2], parts[3],
                                    Integer.parseInt(parts[4]), parts[6], parts[7]);
                            m.setAvailable(Boolean.parseBoolean(parts[5]));
                            items.add(m);}
                        break;

                    case "EBook":
                        if (parts.length >= 8) {
                            EBook e = new EBook(parts[1], parts[2], parts[3],
                                    Integer.parseInt(parts[4]), parts[6], Double.parseDouble(parts[7]));
                            e.setAvailable(Boolean.parseBoolean(parts[5]));
                            items.add(e);}
                        break;

                    case "ResearchPaper":
                        if (parts.length >= 8) {
                            ResearchPaper rp = new ResearchPaper(parts[1], parts[2], parts[3],
                                    Integer.parseInt(parts[4]), parts[6], parts[7]);
                            rp.setAvailable(Boolean.parseBoolean(parts[5]));
                            items.add(rp);}
                        break;

                    default:
                        System.out.println("[File] Unknown item type: " + type);
                }
            }
            br.close();
            System.out.println("[File] " + items.size() + " items loaded from " + ITEMS_FILE);

        } catch (IOException e) {
            System.out.println("[Error] Could not read items file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("[Error] Parsing error in items file: " + e.getMessage());}

        return items;
    }


    public void saveBorrowRecords(ArrayList<BorrowRecord> records) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(RECORDS_FILE));

            for (BorrowRecord rec : records) {
                bw.write(rec.toFileString());
                bw.newLine();
            }
            bw.close();
            System.out.println("[File] Borrow records saved -> " + RECORDS_FILE);
        } catch (IOException e) {
            System.out.println("[Error] Could not save records: " + e.getMessage());}
    }

    public ArrayList<BorrowRecord> loadBorrowRecords() {
        ArrayList<BorrowRecord> records = new ArrayList<>();
        File file = new File(RECORDS_FILE);

        if (!file.exists()) {
            return records;}

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("\\|");
                if (parts.length < 9) continue;

                BorrowRecord rec = new BorrowRecord(
                        parts[0], parts[1], parts[2],
                        parts[3], parts[4], parts[5],
                        parts[6], parts[7], Boolean.parseBoolean(parts[8])
                );
                records.add(rec);}
            br.close();
            System.out.println("[File] " + records.size() + " borrow records loaded.");
        } catch (IOException e) {
            System.out.println("[Error] Could not read records file: " + e.getMessage());}

        return records;}
}
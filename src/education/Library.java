package education;

import interfaces.Notifiable;

import java.util.*;

public class Library implements Notifiable {
    private Map<String, Boolean> books;
    public Library() {
        books = new HashMap<>();
        addBook("Introduction to Algorithms");
        addBook("Clean Code");
        addBook("Artificial Intelligence: A Modern Approach");
    }

    public void addBook(String book) {
        books.put(book, true);
    }

    public void removeBook(String book) {
        books.remove(book);
    }

    public boolean isBookAvailable(String book) {
        return books.getOrDefault(book, false);
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Library sends notification: " + message);
    }

    @Override
    public void receiveNotification(String message) {
        System.out.println("Library received notification: " + message);
    }

    public void viewBooks() {
        System.out.println("Available books:");
        for (Map.Entry<String, Boolean> entry : books.entrySet()) {
            String bookName = entry.getKey();
            boolean isAvailable = entry.getValue();
            System.out.println(bookName + (isAvailable ? " (Available)" : " (Not Available)"));
        }
    }

}
package education;

import java.util.*;

public class Library {
    private Map<String, Boolean> books;
    public Library() {
        books = new HashMap<>();
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
}
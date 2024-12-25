package users;

import education.Library;

public class Librarian extends User {
    private Library library;

    public Librarian(String IIN, String name, String surname, String email, String password, Library library) {
        super(IIN, name, surname, email, password);
        this.library = library;
    }

    public void lendBookToStudent(String book) {
        if (library.isBookAvailable(book)) {
            library.removeBook(book);
            System.out.println("Book lent to student: " + book);
        } else {
            System.out.println("Book not available: " + book);
        }
    }

    public void returnBookFromStudent(String book) {
        library.addBook(book);
        System.out.println("Book returned by student: " + book);
    }

    public Library getLibrary() {
        return library;
    }
    public void viewBooks() {
        library.viewBooks();
    }


}
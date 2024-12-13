package users;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private List<String> logFiles;

    public Admin(String IIN, String name, String surname, String email, String password) {
        super(IIN, name, surname, email, password);
        this.logFiles = new ArrayList<>();
    }

    // Methods
    public void addUser(User user) {
        System.out.println("User " + user.getName() + " added successfully.");
        logFiles.add("Added user: " + user.getName());
    }

    public void removeUser(User user) {
        System.out.println("User " + user.getName() + " removed successfully.");
        logFiles.add("Removed user: " + user.getName());
    }

    public void updateUser(User user, String newName, String newEmail) {
        user.setName(newName);
        user.setEmail(newEmail);
        System.out.println("User " + user.getName() + " updated successfully.");
        logFiles.add("Updated user: " + user.getName());
    }

    public void viewLogFiles() {
        System.out.println("Log Files:");
        for (String log : logFiles) {
            System.out.println(log);
        }
    }
}
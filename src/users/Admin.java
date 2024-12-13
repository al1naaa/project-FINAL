package users;

import interfaces.Notifiable;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User implements Notifiable {
    private List<String> logFiles;
    private List<String> notifications = new ArrayList<>();

    public Admin(String IIN, String name, String surname, String email, String password) {
        super(IIN, name, surname, email, password);
        this.logFiles = new ArrayList<>();
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Admin sends notification: " + message);
    }

    @Override
    public void receiveNotification(String message) {
        notifications.add(message);
        System.out.println("New notification for Admin: " + message);
    }

    public List<String> getNotifications() {
        return notifications;
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
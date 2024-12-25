package users;

import communication.Message;
import communication.LocalizationManager;
import database.UserService;
import communication.News;
import interfaces.Messagable;

import java.util.List;
import java.util.Scanner;


public class Admin extends User implements Messagable {
    private UserService userService;
    private LocalizationManager localizationManager;

    public Admin(String IIN, String name, String surname, String email, String password, LocalizationManager localizationManager) {
        super(IIN, name, surname, email, password);
        this.userService = new UserService();
        this.localizationManager = localizationManager;
    }


    public void addUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(localizationManager.getMessage("enter_iin") + " ");
        String IIN = scanner.nextLine();
        System.out.print(localizationManager.getMessage("enter_name") + " ");
        String name = scanner.nextLine();
        System.out.print(localizationManager.getMessage("enter_surname") + " ");
        String surname = scanner.nextLine();
        System.out.print(localizationManager.getMessage("enter_email") + " ");
        String email = scanner.nextLine();
        System.out.print(localizationManager.getMessage("enter_password") + " ");
        String password = scanner.nextLine();
        System.out.print(localizationManager.getMessage("enter_role") + " ");
        String role = scanner.nextLine().toLowerCase();

        userService.signUp(IIN, name, surname, email, password, role);
        System.out.println(localizationManager.getMessage("user_added") + ": " + name);
    }


    public void removeUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(localizationManager.getMessage("enter_email_to_remove") + " ");
        String email = scanner.nextLine();
        userService.removeUserByEmail(email);
        System.out.println(localizationManager.getMessage("user_removed"));
    }


    public void updateUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(localizationManager.getMessage("enter_email_to_update") + " ");
        String email = scanner.nextLine();
        System.out.print(localizationManager.getMessage("enter_new_name") + " ");
        String newName = scanner.nextLine();
        System.out.print(localizationManager.getMessage("enter_new_email") + " ");
        String newEmail = scanner.nextLine();

        userService.updateUser(email, newName, newEmail);
        System.out.println(localizationManager.getMessage("user_updated"));
    }


    @Override
    public void sendMessage(String receiverEmail, String body) {
        userService.sendMessage(this.getEmail(), receiverEmail, body);
        System.out.println(localizationManager.getMessage("message_sent_to") + ": " + receiverEmail);
    }

    @Override
    public List<Message> viewMessages() {
        List<Message> messages = userService.getMessages(this.getEmail());
        System.out.println(localizationManager.getMessage("your_messages") + ":");
        for (Message message : messages) {
            System.out.println(message.displayMessage());
        }
        return messages;
    }



    public void viewNews(List<News> newsList) {
        newsList.forEach(News::displayNews);
    }

    public void addNews() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(localizationManager.getMessage("enter_news_title") + " ");
        String title = scanner.nextLine();
        System.out.print(localizationManager.getMessage("enter_news_content") + " ");
        String content = scanner.nextLine();

        News news = new News(title, content, "General");
        news.addNews(title, content); // Сохранение в базу данных
        System.out.println(localizationManager.getMessage("news_added"));
    }

}

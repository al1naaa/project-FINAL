package patterns;

public class NotificationDecorator extends UserDecorator {
    public NotificationDecorator(UserComponent user) {
        super(user);
    }

    @Override
    public void login() {
        super.login();
        sendNotification("You have successfully logged in.");
    }

    private void sendNotification(String message) {
        System.out.println("Notification: " + message);
    }
}

package patterns;

public class LoggingDecorator extends UserDecorator {
    public LoggingDecorator(UserComponent user) {
        super(user);
    }

    @Override
    public void login() {
        super.login();
        logAction("login");
    }

    @Override
    public void logout() {
        super.logout();
        logAction("logout");
    }

    private void logAction(String action) {
        System.out.println("Action logged: " + action);
    }
}

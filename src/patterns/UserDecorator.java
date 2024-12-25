package patterns;

public abstract class UserDecorator implements UserComponent {
    protected UserComponent decoratedUser;

    public UserDecorator(UserComponent user) {
        this.decoratedUser = user;
    }

    @Override
    public void login() {
        decoratedUser.login();
    }

    @Override
    public void logout() {
        decoratedUser.logout();
    }

    @Override
    public void viewProfile() {
        decoratedUser.viewProfile();
    }
}

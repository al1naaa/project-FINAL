package users;

public abstract class User {
    private String IIN;
    private String name;
    private String surname;
    private String email;
    private String password;

    // Constructors
    public User(String IIN, String name, String surname, String email, String password) {
        this.IIN = IIN;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public User(String userID, String name, String email) {
    }

    public String getIIN() {
        return IIN;
    }

    public void setIIN(String IIN) {
        this.IIN = IIN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Common operations for all users
    public void login() {
        System.out.println(name + " logged in.");
    }

    public void logout() {
        System.out.println(name + " logged out.");
    }

    public void viewProfile() {
        System.out.println("Profile Details:");
        System.out.println("Name: " + name + " " + surname);
        System.out.println("Email: " + email);
        System.out.println("IIN: " + IIN);
    }

    public void printUserDetails(){
        System.out.println("Name: " + name + " " + surname);
        System.out.println("Email: " + email);
    }

    // Abstract method to be implemented by extended classes
    //public abstract void doSomeSpecificOperations();
    // NEEDS MODIFICATIONS - Admin, Librarian, Researcher, Student, Teacher!!!
}
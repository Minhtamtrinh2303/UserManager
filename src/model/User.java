package model;
import utils.Validator;
import java.util.List;
import java.util.Scanner;

public class User {

    private String username;
    private String email;
    private String password;

    //constructor
    public User() { }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    //Register information for User
    public void inputUser(List<User> userList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter User Information for Register");

        boolean isValid;
        do {
            System.out.print("Username: ");
            this.username = sc.nextLine().trim();
            isValid = Validator.userNameIsValid(this.username, userList );
        } while (!isValid);

        do {
            System.out.print("Email: ");
            this.email = sc.nextLine().trim();
            isValid = Validator.emailIsValid(this.email, userList);
        } while (!isValid);

        do {
            System.out.print("Password: ");
            this.password = sc.nextLine().trim();
            isValid = Validator.passIsValid(this.password);
        } while (!isValid);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "model.User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

package manager;

import model.User;
import sun.applet.Main;
import utils.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManager {

    private User currentUser;
    //Register
    public void register(ArrayList<User> users) {
        User user = new User();
        user.inputUser(users); // register information for user
        users.add(user); // add user to the list
        System.out.println("Register Success");
    }
    //Login
    public boolean login(Scanner sc, ArrayList<User> users) {
        String username;
        String password;

        System.out.println("Enter User Information for Login");
        int d = 0;
        // valid username already exits in the list or not
        do {
            System.out.print("Enter Username:  ");
            username = sc.nextLine().trim();
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    d = 1;
                    break;
                }
            }
            if ( d == 0 ) {
                System.out.println("Username is not available, try again");
            }
        } while ( d==0 );

        System.out.print("Enter Password: ");
        password = sc.nextLine().trim();
        //valid whether password matches te username you have just entered
        d = 0;
        for (int i = 0; i < users.size(); i++) {
            currentUser = users.get(i);
            if (currentUser.getUsername().equals(username) && currentUser.getPassword().equals(password)) {
                d = 1;
                break;
            }
        }

        if ( d==0 ) {
            System.out.println("Password is not match");
            return false;
        }

        System.out.println("Login Success");
        System.out.println("Congratulation" +username);
        return true;
    }
    //forget Password
    public void forgetPassword(Scanner sc, ArrayList<User> users) {
        do {
            System.out.print("Enter the email: ");
            String email = sc.nextLine().trim();

            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                if (user.getEmail().equals(email)) {
                    String newPass;
                    boolean isValid;
                    do {
                        System.out.print("Enter new Password: ");
                        newPass = sc.nextLine().trim();
                        isValid = Validator.passIsValid(newPass);
                    } while (!isValid);
                    user.setPassword(newPass);
                    System.out.println("New Password has been saved");
                    return;
                } else {
                    System.out.println("The account does not exist.");
                }
            }
        } while(true);
    }
    //change Username
    public void changeUserName(Scanner sc, ArrayList<User> users) {
        String newUsername;
        boolean isValid;
        do {
            System.out.print("Enter new Username: ");
            newUsername = sc.nextLine().trim();
            isValid = Validator.userNameIsValid(newUsername, users);
        } while (!isValid);

        currentUser.setUsername(newUsername);
        System.out.println("Change Username Success");
    }
    //change Email
    public void changeEmail(Scanner sc, ArrayList<User> users) {
        String newEmail;
        boolean isValid;
        do {
            System.out.println("Enter new Email: ");
            newEmail = sc.nextLine().trim();
            isValid = Validator.emailIsValid(newEmail, users);
        } while (!isValid);

        currentUser.setUsername(newEmail);
        System.out.println("Change Email Success");

    }
    //change Password
    public void changePassword(Scanner sc) {
        String newPass;
        boolean isValid;
        do {
            System.out.print("Enter new Password: ");
            newPass = sc.next().trim();
            isValid = Validator.passIsValid(newPass);
        } while (!isValid);

        currentUser.setPassword(newPass);
        System.out.println("Change Password Success");
    }

}



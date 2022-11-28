package utils;
import model.User;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validator {

    public static boolean userNameIsValid(String username, List<User> userList) {
        // to register account, username cannot be left blank
        if (username.length() == 0) {
            System.out.println("Username cannot be left blank, try again");
            return false;
        }
        // to register another account, username cannot be available
        for (User user : userList) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                System.out.println("The Username is available, try again");
                return false;
            }
        }

        return true;
    }

    public static boolean emailIsValid(String email, List<User> userList) {
        // use regex to validate email
        String emailPattern =
                "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
        // to register another account, email cannot be available before
        for (User user: userList) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                System.out.println("The email is available, try again");
                return false;
            }
        }
        // valid email + email cannot be available before
        boolean isValid = Pattern.matches(emailPattern, email);
        if (!isValid) {
            System.out.println("Email is not valid, try again");
            return false;
        }

        return true;
    }

    public static boolean passIsValid(String pass) {
        //password length from 7 to 15 characters
        String passPattern = "^\\w{7,15}$";
        //cannot check one capital character, one special character

        boolean isValid = Pattern.matches(passPattern, pass);
        if (!isValid) {
            System.out.println("Password is not valid, try again");
        }
        return isValid;
    }

    public boolean isValidNumber(String choice) {
        try {
            int numInt = Integer.parseInt(choice);
            if (numInt < 0){
                System.out.println("Input wrong, please try again");
                return false;
            }
            return numInt > 0;
        }catch (Exception e){
            System.out.println("Invalid number, please re-enter");
            return false;
        }
    }

    public int getInt(Scanner sc) {
        int value = 0;
        boolean isValid = false;
        do {
            try {
                value = sc.nextInt();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid number, please re-enter");
                sc.nextLine();
            }
        } while (!isValid);
        return value;
    }
}

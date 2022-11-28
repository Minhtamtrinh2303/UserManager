package utils;

import manager.UserManager;
import model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuUtil {

    Validator validate = new Validator();
    UserManager userManager = new UserManager();

    public void printLoginMenu() {
        System.out.println("1 - Login");
        System.out.println("2 - Register");
        System.out.println("3 - Exit Program");
    }

    public void printLoginAgainMenu() {
        System.out.println("1 - Login Again");
        System.out.println("2 - Forgot Password");
        System.out.println("0 - Exit Program");
    }

    public void printMainMenu() {
        System.out.println("1 - Change Username");
        System.out.println("2 - Change Email");
        System.out.println("3 - Change Password");
        System.out.println("4 - Logout");
        System.out.println("0 - Exit Program");
    }

    public String chooseFunctionLogin(Scanner sc, ArrayList<User> users) {
        System.out.println("Please select the function below: ");
        String choice;
        do {
            choice = sc.nextLine().trim();
            //Validate if user has account before
            if (Integer.parseInt(choice) == 1 && users.size() == 0) {
                System.out.println("You need to register first, try again.");
                continue;
            }
            //validate number function
            if (validate.isValidNumber(choice) && Integer.parseInt(choice) <=3){
                break;
            }
            System.out.println("Invalid number, try again.");
        } while (true);

        return choice;
    }

    public void logicHandle(Scanner sc, String functionChoiceLogin, ArrayList<User> users) {
        switch (functionChoiceLogin) {
            case "1":
                login(sc, users);
                break;
            case "2":
                userManager.register(users);
                break;
            case "3":
                System.out.println("Program is finished");
                System.exit(0);
        }
    }

    private void login(Scanner sc, ArrayList<User> users) {
        boolean loginSuccess = userManager.login(sc, users);
        boolean isLogout = false;
        // User logins successfully
        if (loginSuccess) {
            int a;
            do {
                printMainMenu();
                do {
                    a = validate.getInt(sc);
                    if ((a < 0) || (a > 4)) {
                        System.out.println("You must enter a number from 0 to 4.");
                    }
                } while ((a < 0) || (a > 4));
                handleLoginSuccess(sc, a, users); // switch case
            } while (a!=4);
        } else {
            int a;
            do {
                if (!isLogout) { // true
                    printLoginAgainMenu();
                    do {
                        a = validate.getInt(sc);
                        if ((a < 0) || (a > 2))
                            System.out.println("You must enter a number from 0 to 2");
                    } while ((a < 0) || (a > 2));

                    switch (a) {
                        case 1:
                            loginSuccess = userManager.login(sc, users);
                            if (loginSuccess) {
                                int b;
                                do {
                                    printMainMenu();
                                    do {
                                        b = validate.getInt(sc);
                                        if ((b < 0) || (b > 4))
                                            System.out.println("You must enter a number from 0 to 4");
                                    } while ((b < 0) || (b > 4));
                                    handleLoginSuccess(sc, b, users); // switch case
                                } while (b != 4);
                            }
                            break;
                        case 2:
                            userManager.forgetPassword(sc, users);
                            break;
                        case 0:
                            System.out.println("Program is finished");
                            System.exit(0);
                    }
                }
            } while (!isLogout);
        }
    }

    private void handleLoginSuccess(Scanner sc, int choice, ArrayList<User> users) {
        switch (choice) {
            case 1:
                userManager.changeUserName(sc, users);
                break;
            case 2:
                userManager.changeEmail(sc, users);
                break;
            case 3:
                userManager.changePassword(sc);
                break;
            case 4:
                System.out.println("Logout Success");
                break;
            case 0:
                System.out.println("Program is finished");
                System.exit(0);
        }
    }
}

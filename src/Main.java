
import manager.UserManager;
import model.User;
import utils.MenuUtil;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<User> users = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MenuUtil menuUtil = new MenuUtil();
        /**
         * When starting the program, user has 3 functions
         * 1 - Login
         * 2 - Register
         * 3 - Exit Program
         */
        do {
            menuUtil.printLoginMenu();
            //Validate chooseFunction
            String functionChoiceLogin = menuUtil.chooseFunctionLogin(sc, users);
            menuUtil.logicHandle(sc, functionChoiceLogin, users);
        } while(true);
    }
}

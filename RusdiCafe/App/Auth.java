package App;

import java.util.Scanner;

public class Auth {

    private final String USERNAME = "admin";
    private final String PASSWORD = "12345";

    private Scanner scan = new Scanner(System.in);

    public boolean login() {
        System.out.println("=== LOGIN ===");
        System.out.print("Username : ");
        String user = scan.nextLine();

        System.out.print("Password : ");
        String pass = scan.nextLine();

        if (user.equals(USERNAME) && pass.equals(PASSWORD)) {
            return true;
        } else {
            System.out.println("Username atau Password salah!");
            return false; 
        }
    }
}

import java.io.*;
import java.util.*;

public class ATMwithlogin {
    static int withdraw;
    static int balance;
    static int deposit;
    static final String CREDENTIALS_FILE = "credentials.txt";
    static String loggedInUsername;

    private static void register() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Register with a username: ");
        String username = sc.nextLine();
        System.out.print("Register with a password: ");
        String password = sc.nextLine();

        try (FileWriter fw = new FileWriter(CREDENTIALS_FILE, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(username);
            out.println(password);
            out.println(0); // Initial balance set to 0
            System.out.println("Registration successful!\n");
        } catch (IOException e) {
            System.out.println("An error occurred during registration.");
            e.printStackTrace();
        }
    }

    private static boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        try (FileReader fr = new FileReader(CREDENTIALS_FILE);
                BufferedReader br = new BufferedReader(fr)) {
            String storedUsername;
            String storedPassword;
            String storedBalance;
            boolean userFound = false;

            while ((storedUsername = br.readLine()) != null
                    && (storedPassword = br.readLine()) != null
                    && (storedBalance = br.readLine()) != null) {
                if (username.equals(storedUsername) && password.equals(storedPassword)) {
                    System.out.println("Login successful!\n");
                    loggedInUsername = storedUsername;
                    balance = Integer.parseInt(storedBalance);
                    System.out.println("Your balance is: " + balance + "\n");
                    userFound = true;
                    break;
                }
            }

            if (!userFound) {
                System.out.println("User not found. Please try again.\n");
                return false;
            }

            return true;
        } catch (IOException e) {
            System.out.println("An error occurred during login.");
            e.printStackTrace();
            return false;
        }
    }

    private static void updateBalanceInFile() {
        File inputFile = new File(CREDENTIALS_FILE);
        File tempFile = new File("temp_credentials.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
                PrintWriter pw = new PrintWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String username = line;
                String password = br.readLine();
                String userBalance = br.readLine();

                if (username.equals(loggedInUsername)) {
                    pw.println(username);
                    pw.println(password);
                    pw.println(balance);
                } else {
                    pw.println(username);
                    pw.println(password);
                    pw.println(userBalance);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while updating the balance.");
            e.printStackTrace();
        }

        if (!inputFile.delete()) {
            System.out.println("Could not delete file");
            return;
        }

        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename file");
        }
    }

    private static void withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the amount you want to withdraw: ");
        withdraw = sc.nextInt();

        if (balance >= withdraw) {
            balance -= withdraw;
            System.out.println("Please collect your money");
            updateBalanceInFile();
        } else {
            System.out.println("Insufficient balance");
        }
        System.out.println(" ");
    }

    private static void deposit() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the amount you want to deposit: ");
        deposit = sc.nextInt();
        balance += deposit;

        System.out.println("Deposit successful");
        updateBalanceInFile();
        System.out.println(" ");
    }

    private static void balance() {
        System.out.println("Your balance is: " + balance);
        System.out.println(" ");
    }

    private static void exit() {
        System.out.println("Thank you for using our ATM");
        System.exit(0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("***Welcome to ATM***");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.print("Choose the operation which you want to perform: ");
            int initialChoice = sc.nextInt();
            sc.nextLine(); // Consume newline

            if (initialChoice == 1) {
                register();
            }

            boolean loggedIn = false;
            while (!loggedIn) {
                loggedIn = login();
                if (!loggedIn) {
                    System.out.println("Returning to the main page...\n");
                    break;
                }
            }

            if (loggedIn) {
                while (true) {
                    System.out.println("1. Withdraw");
                    System.out.println("2. Deposit");
                    System.out.println("3. Balance");
                    System.out.println("4. Exit");
                    System.out.print("Choose the operation which you want to perform: ");

                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            withdraw();
                            break;
                        case 2:
                            deposit();
                            break;
                        case 3:
                            balance();
                            break;
                        case 4:
                            exit();
                            break;
                        default:
                            System.out.println("Invalid choice, please try again.");
                            break;
                    }
                }
            }
        }
    }
}

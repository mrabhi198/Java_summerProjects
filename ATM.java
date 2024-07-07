package Course.Projects;

import java.util.*;

public class ATM {
    static int withdraw;
    static int balance;

    private static void Withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the amount you want ot Withdraw: ");
        withdraw = sc.nextInt();

        if (balance >= withdraw) {
            balance = balance - withdraw;
            System.out.println("Please collect your Money");
        } else {
            System.out.println("Insufficient Balance");
        }
    }

    private static void Deposite() {
        System.out.print("Enter the amount you want to Deposite: ");

    }

    private static void Balance() {
        System.out.print("Your Balance are: ");
    }

    private static void Exit() {
        System.out.print("Thank you for using our ATM");
        System.exit(0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("***Welcome to ATM***");
        while (true) {
            System.out.println("1. Withdraw");
            System.out.println("2. Deposite");
            System.out.println("3. Balance");
            System.out.println("4. Exit");
            System.out.print("Choose the operation which you want to perform: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    Withdraw();
                    break;
                case 2:
                    Deposite();
                    break;
                case 3:
                    Balance();
                    break;
                case 4:
                    Exit();
                    break;
            }
        }

    }
}

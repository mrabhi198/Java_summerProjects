import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMwithGUI {
    private static int balance;
    private JLabel balanceLabel;
    private JTextField amountField;
    private JButton withdrawBtn, depositBtn, balanceBtn, exitBtn;
    private JFrame frame;

    public ATMwithGUI(JFrame frame) {
        this.frame = frame;
        GUI();
    }

    private void GUI() {
        frame.setTitle("ATM Simulation");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        balanceLabel = new JLabel("Balance: " + balance);
        amountField = new JTextField();

        withdrawBtn = new JButton("Withdraw");
        depositBtn = new JButton("Deposit");
        balanceBtn = new JButton("Balance");
        exitBtn = new JButton("Exit");

        withdrawBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        depositBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        balanceBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                balance();
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });

        frame.add(new JLabel("Enter Amount: "));
        frame.add(amountField);
        frame.add(withdrawBtn);
        frame.add(depositBtn);
        frame.add(balanceBtn);
        frame.add(exitBtn);
        frame.add(balanceLabel);

        frame.setVisible(true);
    }

    private void withdraw() {
        int amount = Integer.parseInt(amountField.getText());
        if (balance >= amount) {
            balance -= amount;
            JOptionPane.showMessageDialog(frame, "Please collect your money");
        } else {
            JOptionPane.showMessageDialog(frame, "Insufficient Balance");
        }
        amountField.setText("");
        updateBalance();
    }

    private void deposit() {
        int amount = Integer.parseInt(amountField.getText());
        balance += amount;
        JOptionPane.showMessageDialog(frame, "Deposit Successful");
        amountField.setText("");
        updateBalance();
    }

    private void balance() {
        JOptionPane.showMessageDialog(frame, "Your Balance is: " + balance);
    }

    private void exit() {
        JOptionPane.showMessageDialog(frame, "Thank you for using our ATM");
        System.exit(0);
    }

    private void updateBalance() {
        balanceLabel.setText("Balance: " + balance);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                new ATMwithGUI(frame);
            }
        });
    }
}


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewATM extends JFrame implements ActionListener {
    private static int balance;
    
    private JLabel balanceLabel;
    private JTextField amountField;
    private JButton withdrawButton, depositeButton, balanceButton, exitButton;

    public ATM() {
        setTitle("ATM Simulation");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        balanceLabel = new JLabel("Balance: " + balance);
        amountField = new JTextField();
        
        withdrawButton = new JButton("Withdraw");
        depositeButton = new JButton("Deposite");
        balanceButton = new JButton("Balance");
        exitButton = new JButton("Exit");

        withdrawButton.addActionListener(this);
        depositeButton.addActionListener(this);
        balanceButton.addActionListener(this);
        exitButton.addActionListener(this);

        add(new JLabel("Enter Amount: "));
        add(amountField);
        add(withdrawButton);
        add(depositeButton);
        add(balanceButton);
        add(exitButton);
        add(balanceLabel);

        setVisible(true);
    }

    private void Withdraw() {
        int amount = Integer.parseInt(amountField.getText());
        if (balance >= amount) {
            balance -= amount;
            JOptionPane.showMessageDialog(this, "Please collect your money");
        } else {
            JOptionPane.showMessageDialog(this, "Insufficient Balance");
        }
        amountField.setText("");
        updateBalance();
    }

    private void Deposite() {
        int amount = Integer.parseInt(amountField.getText());
        balance += amount;
        JOptionPane.showMessageDialog(this, "Deposite Successful");
        amountField.setText("");
        updateBalance();
    }

    private void Balance() {
        JOptionPane.showMessageDialog(this, "Your Balance is: " + balance);
    }

    private void Exit() {
        JOptionPane.showMessageDialog(this, "Thank you for using our ATM");
        System.exit(0);
    }

    private void updateBalance() {
        balanceLabel.setText("Balance: " + balance);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdrawButton) {
            Withdraw();
        } else if (e.getSource() == depositeButton) {
            Deposite();
        } else if (e.getSource() == balanceButton) {
            Balance();
        } else if (e.getSource() == exitButton) {
            Exit();
        }
    }

    public static void main(String[] args) {
        new ATM();
    }
}

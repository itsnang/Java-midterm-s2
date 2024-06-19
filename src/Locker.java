import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LockerApplication {
    private static String savedPassword = null;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Locker Application");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel instructionLabel = new JLabel("Enter your passcode");
        JPasswordField passwordField = new JPasswordField(30);
        topPanel.add(instructionLabel, BorderLayout.NORTH);
        topPanel.add(passwordField, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(4, 3, 5, 5));
        JButton[] numberButtons = new JButton[10];
        for (int i = 1; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            bottomPanel.add(numberButtons[i]);
            int finalI = i;
            numberButtons[i].addActionListener(e -> {
                passwordField.setText(passwordField.getText() + finalI);
            });
        }

        JButton enterButton = new JButton("Enter");
        JButton zeroButton = new JButton("0");
        JButton clearButton = new JButton("Clear");
        bottomPanel.add(enterButton);
        bottomPanel.add(zeroButton);
        bottomPanel.add(clearButton);

        zeroButton.addActionListener(e -> passwordField.setText(passwordField.getText() + "0"));

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.CENTER);

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputPassword = new String(passwordField.getPassword());

                if (savedPassword == null) {
                    // Set the password first time
                    savedPassword = inputPassword;
                    JOptionPane.showMessageDialog(frame, "Password Set.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    instructionLabel.setText("Password set. Enter your password to unlock:");
                } else {
                    // Verify the password
                    if (savedPassword.equals(inputPassword)) {
                        JOptionPane.showMessageDialog(frame, "Correct Password!", "Info",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Incorrect Password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

                passwordField.setText("");
            }
        });
        clearButton.addActionListener(e -> passwordField.setText(""));




        frame.setVisible(true);
    }
}
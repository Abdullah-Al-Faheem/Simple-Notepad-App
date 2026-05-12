package notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registrationPage extends JFrame {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton createAccountButton;
    private JTextField textField2;
    private JPanel registration;
    private JButton signInButton;
    private JLabel status;

    public registrationPage()
    {
        setContentPane(registration);
        setTitle("Registration Page");
        setSize(1200, 860);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage loginPage = new LoginPage();
                loginPage.setVisible(true);
                dispose();
            }
        });
        getRootPane().setDefaultButton(createAccountButton);

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String email = textField2.getText();
                String password = String.valueOf(passwordField1.getPassword());
                String password2 = String.valueOf(passwordField2.getPassword());

                if(password.equals(password2) && !username.isEmpty() && !email.isEmpty())
                {

                    boolean isRegistered = Database.registerUser(username, email, password);
                    if (isRegistered) {
                        JOptionPane.showMessageDialog(null, "User registered successfully!");
                        LoginPage loginPage = new LoginPage();
                        loginPage.setVisible(true);
                        dispose();

                    } else {
                        status.setText("Registration failed. Please try again.");
                        status.setForeground(Color.RED);
                    }
                }
                else if(!password.equals(password2))
                {
                    status.setText("Passwords do not match");
                    status.setForeground(Color.RED);
                }
                else
                {
                    status.setText("Inappropriate fields");
                    status.setForeground(Color.RED);
                }
            }
        });
    }
}

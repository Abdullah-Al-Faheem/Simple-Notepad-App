package notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame
{
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel LoginPage;
    private JButton REGISTERButton;
    private JButton LOGINButton;
    private JLabel prompt;
    private JButton EXITButton;

    public int currentUserId;


    public LoginPage()
    {
        setContentPane(LoginPage);
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 860);
        setLocationRelativeTo(null);
        setVisible(true);

        getRootPane().setDefaultButton(LOGINButton);



        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = String.valueOf(passwordField1.getPassword());

//                if(username.equals("admin") && password.equals("admin"))
//                {
//                    Notepad notepad = new Notepad();
//                    notepad.setVisible(true);
//                }
                currentUserId = Database.getUserId(username, password);
                if (currentUserId != -1) {
                        dispose();Notepad notepad = new Notepad();
                        notepad.setVisible(true);
                        dispose();
//                    HomePage homePage = new HomePage(currentUserId);
//                    homePage.setVisible(true);
//                    dispose();
                } else {
                    prompt.setText("Invalid Username or Password");
                    prompt.setForeground(Color.RED);
                }
            }
        });


        REGISTERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrationPage registrationPage = new registrationPage();
                registrationPage.setVisible(true);
                dispose();
            }
        });
        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args)
    {
        LoginPage frame = new LoginPage();

    }
}


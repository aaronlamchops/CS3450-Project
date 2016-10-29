package project;

import javax.swing.*;

/**
 * Created by Rulon and Aaron on 10/27/16.
 */
public class LogInScrn extends JFrame {

    String userString;      //Intermediate value stored from the Username JTextField
    String passString;      //Intermediate value stored from the Password JTextField

    int userInt;            //the toInt() result of the intermediate value
    int passInt;            //the toInt() result of the intermediate value
    int resultInt;          //The resulting status of the function; position [0] in the returned Object array

    userAccounts ua = new userAccounts();           //Position [1] in the returned Object array
    JTextField un = new JTextField();
    JTextField pw = new JPasswordField();

    public Object[] showLogin() {
        Object[] contents = {
                "Please enter your username:", un,
                "Please enter your password:", pw
        };

        int input = JOptionPane.showConfirmDialog(null, contents, "Login", JOptionPane.OK_CANCEL_OPTION);

        if (input == JOptionPane.OK_OPTION && !un.getText().equals("") && !pw.getText().equals("")) {
            userString = un.getText();
            passString = pw.getText();

            userInt = Integer.parseInt(userString);
            passInt = Integer.parseInt(passString);
            boolean loginStatus = ua.loginCheck(userInt, passInt);
            if (loginStatus) {
                JOptionPane.showConfirmDialog(null, "Login success!", "Success", JOptionPane.OK_OPTION);
                Object [] result = {1, ua};
                return result;
            } else {
                JOptionPane.showMessageDialog(null, "Please check your login credentials\nand try again.", "Login Failure",
                        JOptionPane.OK_OPTION);
            }

        } else if (input == JOptionPane.CANCEL_OPTION) {
            System.exit(-1);
        }
        Object [] result = {0, ua};
        return result;
    }
}
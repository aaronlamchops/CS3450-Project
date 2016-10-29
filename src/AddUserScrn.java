package project;

import javax.swing.*;

/**
 * Created by peppa on 10/27/16.
 */
public class AddUserScrn extends JFrame {



    String userTypeString;
    String userName;

    String [] userTypeSet = {"cashier", "admin", "analyst"};

    int userInt;            //the toInt() result of the intermediate value
    int passInt;            //the toInt() result of the intermediate value
    //int resultInt;          //The resulting status of the function; position [0] in the returned Object array



    userAccounts ua = new userAccounts();           //Position [1] in the returned Object array
    JTextField uid = new JTextField();
    JTextField un = new JTextField();
    JTextField pw = new JPasswordField();
    JComboBox ut = new JComboBox(userTypeSet);


    public void showAddUser() {
        //TODO: Check for preceding 0s in both UID and user passwords
        Object[] contents = {
                "Please enter the new user's ID (numbers only):", uid,
                "Please enter the new user's name:", un,
                "Please enter the new user's password (numbers only):", pw,
                "Please choose the new user's account type:", ut
        };

        int input = JOptionPane.showConfirmDialog(null, contents, "Add User", JOptionPane.OK_CANCEL_OPTION);

        if (input == JOptionPane.OK_OPTION && !un.getText().equals("") && !pw.getText().equals("")) {


            userTypeString = (String)ut.getSelectedItem();
            userName = un.getText();

            userInt = Integer.parseInt(uid.getText());
            passInt = Integer.parseInt(pw.getText());
            ua.setAll(userInt, userName, passInt, userTypeString);
            if (ua.GUIInsertIntoTable(ua)) {

                JOptionPane.showMessageDialog(null, "Addition success!", "Success", JOptionPane.OK_OPTION);

                return;
            } else {
                JOptionPane.showMessageDialog(null, "Please check for duplicate user IDs\n" +
                                "and try again.", "User Addition Failure",
                        JOptionPane.OK_OPTION);
            }

        } else if (input == JOptionPane.CANCEL_OPTION) {
            System.exit(-1);
        }

        return;
    }
}
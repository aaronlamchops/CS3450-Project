package project;

import javax.swing.*;
import java.util.Vector;

/*
 * Created by peppa on 10/27/16.

*/
public class EditUserScrn extends JFrame {


    String userTypeString;
    String userName;

    String[] userTypeSet = {"cashier", "admin", "analyst"};





    int userInt;            //the toInt() result of the intermediate value
    int passInt;            //the toInt() result of the intermediate value
    //int resultInt;          //The resulting status of the function; position [0] in the returned Object array


    userAccounts ua = new userAccounts();           //Position [1] in the returned Object array

    Vector userIDSet = new Vector(ua.returnUIDS());

    JTextField un = new JTextField();
    JTextField pw = new JPasswordField();

    JComboBox ut = new JComboBox(userTypeSet);
    JComboBox uid = new JComboBox(userIDSet);

    public userAccounts showEditUser(userAccounts uaInitial) {
        Object[] contents = {
                "Please choose the user's ID:", uid,
                "Please choose the user's account type:", ut,
                "Please edit the user's name:", un,
                "Please edit the  user's password (numbers only):", pw

        };

        int input = JOptionPane.showConfirmDialog(null, contents, "Add User", JOptionPane.OK_CANCEL_OPTION);

        if (input == JOptionPane.OK_OPTION && !un.getText().equals("") && !pw.getText().equals("")) {

            //String firstNumUID = uid.getText().substring(0,1);
            String firstNumPass = pw.getText().substring(0,1);

            if (firstNumPass.equals("0")){
                JOptionPane.showMessageDialog(null, "Please do not enter passcodes\nthat start with 0.",
                        "Input error", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                userTypeString = (String) ut.getSelectedItem();
                userName = un.getText();

                userInt = (Integer)(uid.getSelectedItem());
                passInt = Integer.parseInt(pw.getText());
                ua.setAll(userInt, userName, passInt, userTypeString);
                if (ua.GUIEditUser(ua)) {

                    JOptionPane.showMessageDialog(null, "Edit success!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    if (uaInitial.userId == (Integer)uid.getSelectedItem()) {
                        JOptionPane.showMessageDialog(null, "As you have recently edited your own account,\n" +
                                        "please re-enter your login information to continue using the grocery system.", "Please log in again",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    return ua;
                } else {
                    JOptionPane.showMessageDialog(null, "Please check for duplicate user IDs\n" +
                                    "and try again.", "User Addition Failure",
                            JOptionPane.ERROR_MESSAGE);

                }
            }

        }

        return ua;
    }
}

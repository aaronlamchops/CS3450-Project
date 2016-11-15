package project;

import javax.swing.*;
import java.util.Vector;

/**
 * Created by peppa on 10/27/16.
 */
public class DeleteUserScrn extends JFrame {


    String userTypeString;
    String userName;

    userAccounts ua = new userAccounts();

    Vector UIDSet = ua.returnUIDS();

    int userInt;            //the toInt() result of the intermediate value
    int passInt;            //the toInt() result of the intermediate value

    JComboBox uid = new JComboBox(UIDSet);


    public void showRemoveUser(userAccounts dontRemoveMeThough) {
        Object[] contents = {
                "Please select the ID of the user\nthat is to be deleted:", uid
        };

        if (!UIDSet.isEmpty()) {

            int input = JOptionPane.showConfirmDialog(null, contents, "Remove User", JOptionPane.OK_CANCEL_OPTION);

            if (input == JOptionPane.OK_OPTION && (Integer)uid.getSelectedItem() != dontRemoveMeThough.userId) {

                ua.setAll(userInt, userName, passInt, userTypeString);
                if (ua.GUIDeleteUser((Integer)uid.getSelectedItem())) {

                    JOptionPane.showMessageDialog(null, "User removal successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    return;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "User removal failed!!", "Success", JOptionPane.WARNING_MESSAGE);

                }

            }
            else if (input == JOptionPane.OK_OPTION && (Integer)uid.getSelectedItem() == dontRemoveMeThough.userId) {
                JOptionPane.showMessageDialog(null, "Please don't delete yourself.\n" +
                        "Try again.", "User Removal Failure", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }
    }
}
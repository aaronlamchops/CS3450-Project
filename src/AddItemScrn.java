package project;

import javax.swing.*;

import static jdk.nashorn.internal.objects.NativeString.substring;

/**
 * Created by peppa on 10/27/16.
 */
public class AddItemScrn extends JFrame {

    Item newItem = new Item();
    InventoryMan im = new InventoryMan();

    JTextField itN = new JTextField();
    JTextField itD = new JTextField();
    JTextField itP = new JTextField();
    JTextField itW = new JTextField();
    JTextField itQ = new JTextField();

    public void showAddItem() {
        Object[] contents = {
                "Please enter the new item's name", itN,
                "Please enter the new item's distributor:", itD,
                "Please enter the new item's price (Format: DD.cc,\nwhere D = dollars, c = cents):", itP,
                "Please enter the new item's weight and its unit of weight:", itW,
                "Please enter the new item's quantity (Numbers only, please):", itQ
        };

        int input = JOptionPane.showConfirmDialog(null, contents, "Add User", JOptionPane.OK_CANCEL_OPTION);

        if (input == JOptionPane.OK_OPTION && !itN.getText().equals("") && !itD.getText().equals("") &&
                !itP.getText().equals("") && !itW.getText().equals("") && !itQ.getText().equals("")) {

            String weightSubStr = substring(itW.getText(),0,1);
            double priceEntry = Double.parseDouble(itP.getText());
            double qtyEntry = Double.parseDouble(itQ.getText());



            if (priceEntry <= 0.0 || qtyEntry <= 0.0){
                JOptionPane.showMessageDialog(null, "Please do not enter numerical values of 0 or less.",
                        "Input error", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (weightSubStr.equals("-"))
            {
                JOptionPane.showMessageDialog(null, "Please do not enter negative weights.",
                        "Input error", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                newItem.setItems(itN.getText(), qtyEntry, priceEntry, itD.getText(), itW.getText());
                if (im.uploadNewItem(newItem)) {

                    JOptionPane.showMessageDialog(null, "Addition success!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "Please check for duplicate item name/distributor pairs\n" +
                                    "and try again.", "Item Addition Failure",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        }

        return;
    }
}
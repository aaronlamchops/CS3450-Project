package project;

import javax.swing.*;
import java.awt.event.*;

public class LogInScrn extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField UserNameEntry;
    private JPasswordField UserPWEntry;
    private JLabel UNLabel;
    private JLabel PWDLabel;
    private JLabel welcomeDialogLabel;

    public LogInScrn() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private String onOK() {
        String user = UserNameEntry.getText();
        char[] pwd = UserPWEntry.getPassword();

        String pwdCompare = pwd.toString();


        dispose();
        return null;
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

//    public static void main(String[] args) {
//        LogInScrn dialog = new LogInScrn();
//        dialog.pack();
//        dialog.setVisible(true);
//        System.exit(0);
//    }
}

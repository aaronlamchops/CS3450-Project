package project;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainScreen extends JFrame {
    private JPanel rootPanel;
    private JTabbedPane TabPane;
    private JPanel CheckedItemsPanel;
    private JPanel ItemPricePanel;
    private JList ItemPriceList;
    private JButton checkOutButton;
    private JButton voidLastItemButton;
    private JPanel CheckoutTab;
    private JPanel ButtonPanelReal;
    private JTextArea ReceiptIDField;
    private JButton searchButton;
    private JPanel RetMgmt;
    private JPanel WelcomeScrn;
    private JPanel InvMgmt;
    private JPanel UserCtrl;
    private JList SearchResults;
    private JPanel ResultsWindow;
    private JRadioButton InvItemRadioButton;
    private JRadioButton InvDistRadioButton;
    private JRadioButton receiptNumberRadioButton;
    private JRadioButton custNameRadioButton;
    private JButton logOutButton;
    private JRadioButton cardRadioButton;
    private JRadioButton cashRadioButton;
    private JRadioButton checkRadioButton;
    private JPasswordField CardDigitSet1;
    private JPasswordField CardDigitSet2;
    private JPasswordField CardDigitSet3;
    private JTextField CardDigitSet4;
    private JButton addNewUserButt;
    private JButton removeUserButt;
    private JButton editUserButt;
    private JButton addNewItemButton;
    private JButton modifyItemButton;
    private JButton addItemButton;
    private JTextField ReceiptNoText;
    private JTextField DateText;
    private JList ItemPurchaseList;
    private JList RetSearchResList;
    private JScrollPane RetSearchResSP;
    private JLabel NavInstructionLabel;
    private JLabel WelcomeLbl;
    private JLabel SecondaryNavInstLabel;
    private JPanel LogOutPanel;
    private JScrollPane ItemPurchScrollPane;
    private JScrollPane ItemPriceListSP;
    private JPanel PmtTypePanel;
    private JLabel CardNumPromptLabel;
    private JToolBar RetSearchBar;
    private JLabel RetSearchLabel;
    private JPanel RetSearchResPanel;
    private JPanel SecondaryRetSearchResPanel;
    private JList SecondRetSearchResList;
    private JScrollPane SecondRetSearchResSP;
    private JToolBar InvSearchBar;
    private JLabel InvSearchLbl;
    private JTextArea InvSearchTermText;
    private JButton InvSearchButt;
    private JScrollPane SearchResultsSP;
    private JPanel InventoryButtPanel;
    private JPanel MCCWelcomePanel;
    private JLabel MCCTitleText;
    private JLabel MCCNavText;
    private JPanel MCCButtPanel;


    public MainScreen() {

        super("Mr. Smith's Grocery");

        setContentPane(rootPanel);

        setPreferredSize(new Dimension(1200, 800));

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LogInScrn li = new LogInScrn();

        final userAccounts ua = new userAccounts();

        Object [] returnedFromLogin = {};
        int loginSuccess = 0;
        while (loginSuccess == 0) {

            returnedFromLogin = (li.showLogin());

            loginSuccess = (Integer)returnedFromLogin[0];
            ua.setEq((userAccounts)returnedFromLogin[1]);


            if (loginSuccess == 1)
                setVisible(true);
        }

        cardRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardDigitSet1.setEditable(true);
                CardDigitSet2.setEditable(true);
                CardDigitSet3.setEditable(true);
                CardDigitSet4.setEditable(true);
            }
        });
        cashRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardDigitSet1.setEditable(false);
                CardDigitSet2.setEditable(false);
                CardDigitSet3.setEditable(false);
                CardDigitSet4.setEditable(false);
            }
        });
        checkRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardDigitSet1.setEditable(false);
                CardDigitSet2.setEditable(false);
                CardDigitSet3.setEditable(false);
                CardDigitSet4.setEditable(false);
            }
        });
        CardDigitSet1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (CardDigitSet1.getText().length() >= 4)        //Limits text to 4 characters
                    e.consume();
            }
        });
        CardDigitSet2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (CardDigitSet2.getText().length() >= 4)
                    e.consume();
            }
        });
        CardDigitSet3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (CardDigitSet3.getText().length() >= 4)
                    e.consume();
            }
        });
        CardDigitSet4.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (CardDigitSet4.getText().length() >= 4)
                    e.consume();
            }
        });


        TabPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (TabPane.getSelectedIndex() == 4 && !ua.accType.equals("admin")) {
                    JOptionPane.showMessageDialog(TabPane,
                            "This tab is for the manager's eyes only.\nPlease log in as a manager and try again." +
                            "\nCurrent account type: " +ua.accType,
                            "Get Outta Here!",
                            JOptionPane.ERROR_MESSAGE);
                    TabPane.setSelectedIndex(0);
                }
                if (TabPane.getSelectedIndex() == 1) {
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    Date dateobj = new Date();
                    DateText.setText(df.format(dateobj));
                }

            }
        });
        SearchResults.addComponentListener(new ComponentAdapter() {
        });
        SearchResults.addFocusListener(new FocusAdapter() {
        });
        addNewUserButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUserScrn aus = new AddUserScrn();

                aus.showAddUser();


            }
        });
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(null, "HERE!");
            }
        });

    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout(0, 0));
        final JToolBar toolBar1 = new JToolBar();
        rootPanel.add(toolBar1, BorderLayout.NORTH);
        TabPane = new JTabbedPane();
        rootPanel.add(TabPane, BorderLayout.CENTER);
        WelcomeScrn = new JPanel();
        WelcomeScrn.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        TabPane.addTab("Main Menu", WelcomeScrn);
        final JLabel label1 = new JLabel();
        label1.setFont(new Font("Source Code Pro", Font.ITALIC, 14));
        label1.setText("Please navigate the interface using the tabs above.");
        WelcomeScrn.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setFont(new Font("Source Code Pro", Font.BOLD, 26));
        label2.setText("Welcome!");
        WelcomeScrn.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Alternatively, you may log out using the button below. ");
        WelcomeScrn.add(label3, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        WelcomeScrn.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        logOutButton = new JButton();
        logOutButton.setHorizontalTextPosition(11);
        logOutButton.setText("Log Out");
        panel1.add(logOutButton);
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        WelcomeScrn.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        WelcomeScrn.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        CheckoutTab = new JPanel();
        CheckoutTab.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), -1, -1));
        TabPane.addTab("Checkout", CheckoutTab);
        CheckedItemsPanel = new JPanel();
        CheckedItemsPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        CheckoutTab.add(CheckedItemsPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(164, 244), null, 0, false));
        CheckedItemsPanel.setBorder(BorderFactory.createTitledBorder("Checked Items"));
        final JScrollPane scrollPane1 = new JScrollPane();
        CheckedItemsPanel.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        ItemPurchaseList = new JList();
        scrollPane1.setViewportView(ItemPurchaseList);
        ItemPricePanel = new JPanel();
        ItemPricePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        CheckoutTab.add(ItemPricePanel, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(164, 244), null, 0, false));
        ItemPricePanel.setBorder(BorderFactory.createTitledBorder("Item Price"));
        final JScrollPane scrollPane2 = new JScrollPane();
        ItemPricePanel.add(scrollPane2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        ItemPriceList = new JList();
        scrollPane2.setViewportView(ItemPriceList);
        ButtonPanelReal = new JPanel();
        ButtonPanelReal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        CheckoutTab.add(ButtonPanelReal, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHEAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        checkOutButton = new JButton();
        checkOutButton.setText("Check Out");
        ButtonPanelReal.add(checkOutButton);
        voidLastItemButton = new JButton();
        voidLastItemButton.setText("Void Last Item");
        voidLastItemButton.setVerticalAlignment(3);
        ButtonPanelReal.add(voidLastItemButton);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 7, new Insets(0, 0, 0, 0), -1, -1));
        CheckoutTab.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        cardRadioButton = new JRadioButton();
        cardRadioButton.setText("Card");
        panel2.add(cardRadioButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cashRadioButton = new JRadioButton();
        cashRadioButton.setText("Cash");
        panel2.add(cashRadioButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 6, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkRadioButton = new JRadioButton();
        checkRadioButton.setText("Check");
        panel2.add(checkRadioButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 6, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Enter Card Number: ");
        panel2.add(label4, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CardDigitSet2 = new JPasswordField();
        panel2.add(CardDigitSet2, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        CardDigitSet3 = new JPasswordField();
        panel2.add(CardDigitSet3, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        CardDigitSet4 = new JTextField();
        panel2.add(CardDigitSet4, new com.intellij.uiDesigner.core.GridConstraints(0, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        CardDigitSet1 = new JPasswordField();
        panel2.add(CardDigitSet1, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        RetMgmt = new JPanel();
        RetMgmt.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        TabPane.addTab("Return Mgmt.", RetMgmt);
        final JToolBar toolBar2 = new JToolBar();
        RetMgmt.add(toolBar2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Search Term:");
        toolBar2.add(label5);
        ReceiptIDField = new JTextArea();
        ReceiptIDField.setText("");
        toolBar2.add(ReceiptIDField);
        custNameRadioButton = new JRadioButton();
        custNameRadioButton.setText("Cust. Name");
        toolBar2.add(custNameRadioButton);
        receiptNumberRadioButton = new JRadioButton();
        receiptNumberRadioButton.setText("Receipt Number");
        toolBar2.add(receiptNumberRadioButton);
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        toolBar2.add(spacer3);
        searchButton = new JButton();
        searchButton.setText("Search");
        toolBar2.add(searchButton);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        RetMgmt.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        RetMgmt.add(panel4, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        InvMgmt = new JPanel();
        InvMgmt.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        TabPane.addTab("Inventory", InvMgmt);
        final JToolBar toolBar3 = new JToolBar();
        InvMgmt.add(toolBar3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Search Term:");
        toolBar3.add(label6);
        final JTextArea textArea1 = new JTextArea();
        textArea1.setText("");
        toolBar3.add(textArea1);
        InvItemRadioButton = new JRadioButton();
        InvItemRadioButton.setText("Item");
        toolBar3.add(InvItemRadioButton);
        InvDistRadioButton = new JRadioButton();
        InvDistRadioButton.setText("Distributor");
        toolBar3.add(InvDistRadioButton);
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        toolBar3.add(spacer4);
        final JButton button1 = new JButton();
        button1.setText("Search");
        toolBar3.add(button1);
        ResultsWindow = new JPanel();
        ResultsWindow.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        InvMgmt.add(ResultsWindow, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane3 = new JScrollPane();
        ResultsWindow.add(scrollPane3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        SearchResults = new JList();
        
      final DefaultListModel listModel;
      listModel = new DefaultListModel();
      SearchResults = new JList(listModel);
      
      
      //button listener for search in inventory
      final InventoryMan inventory = new InventoryMan();
      button1.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {     
             //inventory.searchItem(textArea1.getText());  
          	String searchItem = textArea1.getText();
          	System.out.println(inventory.searchItem(searchItem));
          	listModel.addElement(inventory.searchItem(searchItem));
          }
       });
      
      scrollPane3.setViewportView(SearchResults);
      
      UserCtrl = new JPanel();
        
        
//        scrollPane3.setViewportView(SearchResults);
//        UserCtrl = new JPanel();
        UserCtrl.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        TabPane.addTab("User Ctrl.", UserCtrl);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        UserCtrl.add(panel5, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setFont(new Font("Source Code Pro", Font.BOLD, 48));
        label7.setText("Manager Control Center");
        label7.setVerticalAlignment(1);
        panel5.add(label7, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Please select the action you'd like to perform from the choices below: ");
        label8.setVerticalTextPosition(0);
        panel5.add(label8, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        UserCtrl.add(panel6, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addNewUserButt = new JButton();
        addNewUserButt.setText("Add New User");
        panel6.add(addNewUserButt);
        removeUserButt = new JButton();
        removeUserButt.setText("Remove User");
        panel6.add(removeUserButt);
        editUserButt = new JButton();
        editUserButt.setText("Edit User");
        panel6.add(editUserButt);
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        rootPanel.add(panel7, BorderLayout.SOUTH);
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(cardRadioButton);
        buttonGroup.add(cashRadioButton);
        buttonGroup.add(checkRadioButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}

//final DefaultListModel listModel;
//listModel = new DefaultListModel();
//SearchResults = new JList(listModel);
//
//
////button listener for search in inventory
//final InventoryMan inventory = new InventoryMan();
//button1.addActionListener(new ActionListener() {
//    public void actionPerformed(ActionEvent e) {     
//       //inventory.searchItem(textArea1.getText());  
//    	String searchItem = textArea1.getText();
//    	System.out.println(inventory.searchItem(searchItem));
//    	listModel.addElement(inventory.searchItem(searchItem));
//    }
// });
//
//scrollPane3.setViewportView(SearchResults);
//
//UserCtrl = new JPanel();
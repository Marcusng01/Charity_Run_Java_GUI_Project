package charity_applet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;

public class ReceiptPanel {
    private CharityRunApplet applet;
    String[] reassurance = {
    		"You will receive a confirmation message from us shortly. Please ensure that all details are correct before the event starts.",
    		"If you have any enquiries, we encourge you contact us via our email of phone number. We will be sure to reply within 24 hours.",
    		"Please be assured that we do not share your personal details to any unauthorised party. Any information found in this receipt is only for bookkeeping purposes.",
    };
	private String[] images = {
			"image/page5/email.png",
			"image/page5/message.png",
			"image/page5/chatting.png",
	};
    
    public ReceiptPanel(CharityRunApplet a) {
        applet = a;
    }

    public JPanel generate() {
        JPanel receiptPanel = new JPanel();
        receiptPanel.setBackground(applet.grey);
        
    	//Header (create using applet function)
        JPanel header = applet.createHeaderPanel("RECEIPT");
        receiptPanel.add(header);

        // Body
        JPanel body = createBodyPanel();
        receiptPanel.add(body);
        
        // Footer
        JPanel footer = createFooterPanel();
        receiptPanel.add(footer);

        return receiptPanel;
    }

    private JPanel createBodyPanel() {
        JPanel body = new JPanel(new FlowLayout());
        body.setPreferredSize(new Dimension(600, 480));
        body.setBackground(applet.brightGrey);
        
        // Add FormPanel information
        body.add(createFormPanelInfo());
        
        // Add CategoryPanel information
        body.add(createCategoryPanelInfo());

        // Add MerchPanel information
        body.add(createMerchPanelInfo());
        
        // Add Merchandise Total
        body.add(createTotalPanel());
        
        // Add reassurance panel
        body.add(createReassurancePanel());
        applet.setBackground(applet.brightGrey);
        return body;
    }

    private JPanel createFormPanelInfo() {
        JPanel formInfoPanel = new JPanel();
        formInfoPanel.setPreferredSize(new Dimension(350, 120));
        formInfoPanel.setBackground(applet.brightGrey);
        
        //Create header for form data
        JLabel headerLabel = new JLabel("Runner: ");
        headerLabel.setPreferredSize(new Dimension(310, 20));
        Font headerFont = new Font(Font.DIALOG, Font.BOLD, 20);
        headerLabel.setFont(headerFont);
        formInfoPanel.add(headerLabel);

        //font for lables
        Font labelFont = new Font(Font.DIALOG, Font.PLAIN, 16);

        //Create FULL NAME labels from form data
        String name = applet.formData[0];
        JLabel nameLabel = new JLabel("Name:" + name);
        nameLabel.setPreferredSize(new Dimension(310, 20));
        nameLabel.setFont(labelFont);
        formInfoPanel.add(nameLabel);

        //Create EMAIL labels from form data
        String email = applet.formData[3];
        JLabel emailLabel = new JLabel("Email: " + email);
        emailLabel.setPreferredSize(new Dimension(310, 20));
        emailLabel.setFont(labelFont);
        formInfoPanel.add(emailLabel);

        //Create PHONE NUMBER labels from form data
        String phone = applet.formData[4];
        JLabel phoneLabel = new JLabel("Phone: " + phone);
        phoneLabel.setPreferredSize(new Dimension(310, 20));
        phoneLabel.setFont(labelFont);
        formInfoPanel.add(phoneLabel);
        
        return formInfoPanel;
    }

    private JPanel createCategoryPanelInfo() {
        JPanel categoryInfoPanel = new JPanel(new FlowLayout());
        categoryInfoPanel.setPreferredSize(new Dimension(230, 120));
        categoryInfoPanel.setBackground(applet.brightGrey);
        
        // Retrieve category data
        String category = applet.categoryData;
        
        //Create header for form data
        JLabel headerLabel = new JLabel("Category:");
        Font headerFont = new Font(Font.DIALOG, Font.BOLD, 20);
        headerLabel.setFont(headerFont);
        categoryInfoPanel.add(headerLabel);

        // Create label for category data
        JLabel categoryLabel = new JLabel(category);
        headerLabel.setPreferredSize(new Dimension(190, 25));
        categoryLabel.setPreferredSize(new Dimension(190, 60));
        categoryLabel.setHorizontalAlignment(JLabel.CENTER);
        Font labelFont = new Font(Font.DIALOG, Font.PLAIN, 20);
        categoryLabel.setFont(labelFont);
        categoryInfoPanel.add(categoryLabel);
        
        return categoryInfoPanel;
    }

    private JPanel createMerchPanelInfo() {
        JPanel merchInfoPanel = new JPanel(new BorderLayout());
        merchInfoPanel.setPreferredSize(new Dimension(585, 135));
        merchInfoPanel.setBackground(applet.brightGrey);
        
        // Create table for merchandise data
        JTable merchTable = createMerchandiseTable();
        merchInfoPanel.add(new JScrollPane(merchTable), BorderLayout.CENTER);
        
        return merchInfoPanel;
    }
    
    private JTable createMerchandiseTable() {
        // Retrieve merchandise data
        boolean[] merchandiseData = applet.merchandiseData;
        String[] columnNames = { "Merchandise", "Purchased", "Price (RM)" };
        String[][] rowData = {
        		//Uses ... ? ... : ... Ternary operator to shorten if else statement.
        		//merchandiseData[0] ? "Yes" : "No" means that, if the merchandise was selected, the column will show yes, otherwise, show no. 
                { "T-Shirt", merchandiseData[0] ? "Yes" : "No", merchandiseData[0] ? "20" : "0" },
                { "Bottle", merchandiseData[1] ? "Yes" : "No", merchandiseData[1] ? "30" : "0" },
                { "Mug", merchandiseData[2] ? "Yes" : "No", merchandiseData[2] ? "15" : "0" },
                { "Bag", merchandiseData[3] ? "Yes" : "No", merchandiseData[3] ? "10" : "0" },
                { "Medal", merchandiseData[4] ? "Yes" : "No", merchandiseData[4] ? "20" : "0" },
                { "Hat", merchandiseData[5] ? "Yes" : "No", merchandiseData[5] ? "20" : "0" },
                { "Raffle", merchandiseData[6] ? "Yes" : "No", merchandiseData[6] ? "10" : "0" }
        };
        
        //Create merch table
        JTable merchTable = new JTable(rowData, columnNames);
        //Create table model for merch table
        DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames) {
        	
        	//Added serial version UID to remove warning that eclipse was giving
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //Set the model for merch table 
        merchTable.setModel(tableModel);
        return merchTable;
    }
    
    private JLabel createTotalPanel() {
        // Retrieve merchandise data
        boolean[] merchandiseData = applet.merchandiseData;
        // Calculate total price using calculateTotalPrice()
        String totalPrice = calculateTotalPrice(merchandiseData);
        
        // Create label for total price
        JLabel totalPriceLabel = new JLabel("Total: RM " + totalPrice);
        totalPriceLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        totalPriceLabel.setPreferredSize(new Dimension(585, 30));
        totalPriceLabel.setHorizontalAlignment(JLabel.RIGHT);
        
        return totalPriceLabel;
    }

    private String calculateTotalPrice(boolean[] merchandiseData) {
        double totalPrice = 0;

        if (merchandiseData[0]) 
        	totalPrice += 20;
        if (merchandiseData[1]) 
        	totalPrice += 30;
        if (merchandiseData[2]) 
        	totalPrice += 15;
        if (merchandiseData[3]) 
        	totalPrice += 10;
        if (merchandiseData[4]) 
        	totalPrice += 20;
        if (merchandiseData[5]) 
        	totalPrice += 20;
        if (merchandiseData[6]) 
        	totalPrice += 10;
        
        //Format total price to 2dp string
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String formattedTotalPrice = decimalFormat.format(totalPrice);

        return formattedTotalPrice;
    }
    
    private JPanel createReassurancePanel() {
    	JPanel reassurancePanel = new JPanel(new FlowLayout());
    	reassurancePanel.setPreferredSize(new Dimension(585, 170));
    	reassurancePanel.setBackground(applet.black);
    	
    	//There are 3 rows of reassurance messages
    	for (int i=0; i<3; i++) {
    		//create row panel for each message
    		JPanel row = new JPanel(new FlowLayout());
    		row.setPreferredSize(new Dimension(585, 50));
    		row.setBackground(applet.brightGrey);
    		
    		//create icon for each reassurance message
            ImageIcon icon = new ImageIcon(images[i]);
            JLabel iconLabel = new JLabel(icon);
            iconLabel.setPreferredSize(new Dimension(50, 35));
        	row.add(iconLabel);

    		//Create reassurance text box
        	JEditorPane reassuranceText = applet.createTextBox(reassurance[i]);
        	reassuranceText.setPreferredSize(new Dimension(500, 35));
        	reassuranceText.setBackground(applet.brightGrey);
        	row.add(reassuranceText);
        	
        	//Add row to reassurance panel
        	reassurancePanel.add(row);
    	}

    	return reassurancePanel;
    }

    private JPanel createFooterPanel() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setPreferredSize(new Dimension(600, 50));
        footer.setBackground(applet.grey);

        // Create Next button
        JButton nextPageButton = createNextButton();
        footer.add(nextPageButton,BorderLayout.EAST);
        
        return footer;
    }

    private JButton createNextButton() {
        JButton nextPageButton = new JButton("EXIT");
        nextPageButton.setPreferredSize(new Dimension(100, 40));
        nextPageButton.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
        
        nextPageButton.addActionListener(e -> {
            // Get the top-level parent window
            Window window = SwingUtilities.getWindowAncestor(nextPageButton);
            if (window != null) {
                // Close the window
                window.dispose();
            }
        });
        return nextPageButton;
    }
}

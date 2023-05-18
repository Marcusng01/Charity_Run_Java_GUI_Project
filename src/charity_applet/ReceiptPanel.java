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

        //retrieve form data
        String name = applet.formData[0];
        String email = applet.formData[7];
        String phone = applet.formData[8];
        
        //Create header for form data
        JLabel headerLabel = new JLabel("Runner: ");
        //Create labels for form data
        JLabel nameLabel = new JLabel("Name:" + name);
        JLabel emailLabel = new JLabel("Email: " + email);
        JLabel phoneLabel = new JLabel("Phone: " + phone);
        
        //Set size for header
        headerLabel.setPreferredSize(new Dimension(310, 20));
        //Set size for labels
        nameLabel.setPreferredSize(new Dimension(310, 20));
        emailLabel.setPreferredSize(new Dimension(310, 20));
        phoneLabel.setPreferredSize(new Dimension(310, 20));

        //Set font for header
        Font headerFont = new Font(Font.DIALOG, Font.BOLD, 20);
        headerLabel.setFont(headerFont);
        //set font for labels
        Font labelFont = new Font(Font.DIALOG, Font.PLAIN, 16);
        nameLabel.setFont(labelFont);
        emailLabel.setFont(labelFont);
        phoneLabel.setFont(labelFont);
        
        // Add header and labels to form info panel
        formInfoPanel.add(headerLabel);
        formInfoPanel.add(nameLabel);
        formInfoPanel.add(emailLabel);
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
        // Create label for category data
        JLabel categoryLabel = new JLabel(category);
        
        //Set size and alignment for labels
        headerLabel.setPreferredSize(new Dimension(190, 25));
        categoryLabel.setPreferredSize(new Dimension(190, 60));
        categoryLabel.setHorizontalAlignment(JLabel.CENTER);
        
        //Set font for header
        Font headerFont = new Font(Font.DIALOG, Font.BOLD, 20);
        headerLabel.setFont(headerFont);
        // Set font and alignment for label
        Font labelFont = new Font(Font.DIALOG, Font.PLAIN, 20);
        categoryLabel.setFont(labelFont);
        
        //Add header to category info panel
        categoryInfoPanel.add(headerLabel);
        // Add label to category info panel
        categoryInfoPanel.add(categoryLabel);
        
        return categoryInfoPanel;
    }

    private JPanel createMerchPanelInfo() {
        JPanel merchInfoPanel = new JPanel(new BorderLayout());
        merchInfoPanel.setPreferredSize(new Dimension(585, 135));
        merchInfoPanel.setBackground(applet.brightGrey);
        
        // Create table for merchandise data
        JTable merchTable = createMerchandiseTable();
        
        // Add table to merch info panel
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
    		//Create reassurance text box
        	JEditorPane reassuranceText = applet.createTextBox(reassurance[i]);
        	reassuranceText.setPreferredSize(new Dimension(500, 35));
        	reassuranceText.setBackground(applet.brightGrey);
        	
        	//Add icon and textbox to row
        	row.add(iconLabel);
        	row.add(reassuranceText);
        	//Add row to reassurance panel
        	reassurancePanel.add(row);
    	}

    	return reassurancePanel;
    }

    private JPanel createFooterPanel() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setPreferredSize(new Dimension(600, 50));
        footer.setBackground(applet.grey);

        // Create Next button
        JButton nextPageButton = createNextButton();
        footer.add(nextPageButton);
        
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

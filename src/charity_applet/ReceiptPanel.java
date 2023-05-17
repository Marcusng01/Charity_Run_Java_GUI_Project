package charity_applet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ReceiptPanel {
    private CharityRunApplet applet;
    private final int NEXT_PANEL_NUMBER = 5;
    
    public ReceiptPanel(CharityRunApplet a) {
        applet = a;
    }

    public JPanel generate() {
        JPanel receiptPanel = new JPanel();
        receiptPanel.setBackground(applet.grey);
        receiptPanel.setLayout(new BorderLayout());
        
        // Header
        JPanel header = createHeaderPanel("RECEIPT");
        receiptPanel.add(header, BorderLayout.NORTH);

        // Body
        JPanel body = createBodyPanel();
        receiptPanel.add(body, BorderLayout.CENTER);
        
        // Footer
        JPanel footer = createFooterPanel();
        receiptPanel.add(footer, BorderLayout.SOUTH);

        return receiptPanel;
    }

    private JPanel createHeaderPanel(String title) {
        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(600, 100));
        header.setBackground(applet.grey);
        
        // Label for header
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        header.add(titleLabel, BorderLayout.CENTER);
        
        // Logo
        ImageIcon logo = new ImageIcon("image/page3/logo.png");
        JLabel logoLabel = new JLabel(logo);
        header.add(logoLabel, BorderLayout.EAST);
        
        // Spacer to make the title appear in the middle
        JLabel spacer = new JLabel();
        spacer.setPreferredSize(new Dimension(logo.getIconWidth(), logo.getIconHeight()));
        header.add(spacer, BorderLayout.WEST);

        return header;
    }
    
    private JPanel createBodyPanel() {
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBackground(applet.brightGrey);
        body.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Add FormPanel information
        body.add(createFormPanelInfo());
        
        // Add CategoryPanel information
        body.add(createCategoryPanelInfo());
        
        // Add MerchPanel information
        body.add(createMerchPanelInfo());
        
        return body;
    }

    private JPanel createFormPanelInfo() {
        JPanel formInfoPanel = new JPanel();
        formInfoPanel.setPreferredSize(new Dimension(600, 150));
        formInfoPanel.setBackground(applet.brightGrey);
        
        // Retrieve form data
        String name = applet.formData[0];
        String email = applet.formData[7];
        String phone = applet.formData[8];
        
        // Create labels for form data
        JLabel nameLabel = new JLabel("Name: " + name);
        JLabel emailLabel = new JLabel("Email: " + email);
        JLabel phoneLabel = new JLabel("Phone: " + phone);
        
        // Set font and alignment for labels
        Font labelFont = new Font(Font.DIALOG, Font.BOLD, 20);
        nameLabel.setFont(labelFont);
        emailLabel.setFont(labelFont);
        phoneLabel.setFont(labelFont);
        
        // Add labels to form info panel
        formInfoPanel.add(nameLabel);
        formInfoPanel.add(emailLabel);
        formInfoPanel.add(phoneLabel);
        
        return formInfoPanel;
    }

    private JPanel createCategoryPanelInfo() {
        JPanel categoryInfoPanel = new JPanel();
        categoryInfoPanel.setPreferredSize(new Dimension(600, 100));
        categoryInfoPanel.setBackground(applet.brightGrey);
        
        // Retrieve category data
        String category = applet.categoryData;
        
        // Create label for category data
        JLabel categoryLabel = new JLabel("Category: " + category);
        
        // Set font and alignment for label
        Font labelFont = new Font(Font.DIALOG, Font.BOLD, 20);
        categoryLabel.setFont(labelFont);
        
        // Add label to category info panel
        categoryInfoPanel.add(categoryLabel);
        
        return categoryInfoPanel;
    }

    private JPanel createMerchPanelInfo() {
        JPanel merchInfoPanel = new JPanel(new BorderLayout());
        merchInfoPanel.setPreferredSize(new Dimension(600, 300));
        merchInfoPanel.setBackground(applet.brightGrey);

        // Retrieve merchandise data
        boolean[] merchandiseData = applet.merchandiseData;
        
        // Create table for merchandise data
        JTable merchTable = createMerchandiseTable(merchandiseData);
        
        // Add table to merch info panel
        merchInfoPanel.add(new JScrollPane(merchTable), BorderLayout.CENTER);
        
        return merchInfoPanel;
    }
    
    private JTable createMerchandiseTable(boolean[] merchandiseData) {
        String[] columnNames = { "Merchandise", "Purchased", "Price (RM)" };
        Object[][] rowData = {
                { "T-Shirt", merchandiseData[0] ? "Yes" : "No", merchandiseData[0] ? "20" : "" },
                { "Bottle", merchandiseData[1] ? "Yes" : "No", merchandiseData[1] ? "30" : "" },
                { "Mug", merchandiseData[2] ? "Yes" : "No", merchandiseData[2] ? "15" : "" },
                { "Bag", merchandiseData[3] ? "Yes" : "No", merchandiseData[3] ? "10" : "" },
                { "Medal", merchandiseData[4] ? "Yes" : "No", merchandiseData[4] ? "20" : "" },
                { "Hat", merchandiseData[5] ? "Yes" : "No", merchandiseData[5] ? "20" : "" },
                { "Raffle", merchandiseData[6] ? "Yes" : "No", merchandiseData[6] ? "10" : "" }
        };
        
        JTable merchTable = new JTable(rowData, columnNames);
        DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        merchTable.setModel(tableModel);
        merchTable.setFillsViewportHeight(true);
        
        return merchTable;
    }
    
    private JPanel createFooterPanel() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setPreferredSize(new Dimension(600, 50));
        footer.setBackground(applet.brightGrey);
        
        // Calculate the total price
        double totalPrice = calculateTotalPrice(applet.merchandiseData);
        
        // Create label for total price
        JLabel totalPriceLabel = new JLabel("Total: RM " + totalPrice);
        totalPriceLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        
        // Add label to footer panel
        footer.add(totalPriceLabel);
        
        // Create Next button
        JButton nextPageButton = createNextButton();
        footer.add(nextPageButton);
        
        return footer;
    }

    private JButton createNextButton() {
        JButton nextPageButton = new JButton("Next");
        nextPageButton.setPreferredSize(new Dimension(100, 40));
        nextPageButton.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
        nextPageButton.addActionListener(e -> {
            applet.showPanel(NEXT_PANEL_NUMBER);
        });
        return nextPageButton;
    }
    
    private double calculateTotalPrice(boolean[] merchandiseData) {
        double totalPrice = 0;
        
        if (merchandiseData[0]) totalPrice += 20;
        if (merchandiseData[1]) totalPrice += 30;
        if (merchandiseData[2]) totalPrice += 15;
        if (merchandiseData[3]) totalPrice += 10;
        if (merchandiseData[4]) totalPrice += 20;
        if (merchandiseData[5]) totalPrice += 20;
        if (merchandiseData[6]) totalPrice += 10;
        
        return totalPrice;
    }
}

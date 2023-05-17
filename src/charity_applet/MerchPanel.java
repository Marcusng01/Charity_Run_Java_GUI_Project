package charity_applet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;

public class MerchPanel {
    private CharityRunApplet applet;
    private final int NEXT_PANEL_NUMBER = 4;
	private JPanel[] row = new JPanel[3];
	private String[][] images = {
			{
				"image/page4/t-shirt.png",
				"image/page4/waterbottle.png",
				"image/page4/mug.jpg",
			},
			{
				"image/page4/bag.png",
				"image/page4/medal.jpg",
				"image/page4/hat.png",
			}
	};
	
	private String[][] itemName = {
			{
				"T-Shirt (RM20)",
				"Bottle (RM30)",
				"Mug (RM15)",
			},
			{
				"Bag (RM10)",
				"Medal (RM20)",
				"Hat (RM20)",
			}
	};
    
    public MerchPanel(CharityRunApplet a) {
    	applet = a;
    }
    
    public JPanel generate() {
    	JPanel merchPanel = new JPanel();
    	merchPanel.setBackground(applet.grey);
    	
    	//Header
    	JPanel header = new JPanel(new BorderLayout());
    	header.setPreferredSize(new Dimension(600, 100));
    	header.setBackground(applet.grey);
    	//Label for header
    	JLabel category = new JLabel("MERCHANDISE");
    	category.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
    	category.setHorizontalAlignment(JLabel.CENTER);
    	header.add(category, BorderLayout.CENTER);
    	//Logo
        ImageIcon logo = new ImageIcon("image/page3/logo.png");
        JLabel logoLabel = new JLabel(logo);
        header.add(logoLabel, BorderLayout.EAST);
        //spacer to make "CATEGORY" appear in the middle
        JLabel spacer = new JLabel();
        spacer.setPreferredSize(new Dimension(logo.getIconWidth(), logo.getIconHeight()));
        header.add(spacer, BorderLayout.WEST);
        merchPanel.add(header);
    	
    	//Body
    	JPanel body = new JPanel(new FlowLayout());
    	body.setPreferredSize(new Dimension(600, 500)); 
    	body.setBackground(applet.brightGrey);
    	for (int i=0; i<2; i++) {
    		row[i] = new JPanel(new FlowLayout());
    		row[i].setBackground(applet.brightGrey);
    		row[i].setPreferredSize(new Dimension(570, 180)); 
    		JPanel[] column = new JPanel[3];
        	for (int j=0; j<3; j++) {
        		column[i] = new JPanel(new BorderLayout());
        		column[i].setBackground(applet.brightGrey);
        		column[i].setPreferredSize(new Dimension(175, 180));
        		//Check box to select merchandise
        		JCheckBox checkbox = new JCheckBox(itemName[i][j]);
        		checkbox.setBackground(applet.brightGrey);
        		checkbox.setPreferredSize(new Dimension(150, 20));
        		checkbox.setFont(new Font(Font.DIALOG, Font.BOLD, 17));
        		checkbox.setHorizontalAlignment(JLabel.CENTER);
                column[i].add(checkbox, BorderLayout.NORTH);
                //Image for merchandise
                ImageIcon item = new ImageIcon(images[i][j]);
                JLabel itemLabel = new JLabel(item);
                column[i].add(itemLabel, BorderLayout.CENTER);
        		row[i].add(column[i]);
        	}
        	body.add(row[i]);
    	}
    	
    	
    	//last row with raffle and submit button
    	row[2] = new JPanel(new FlowLayout());
		row[2].setPreferredSize(new Dimension(570, 120));
		row[2].setBackground(applet.brightGrey);
		//rafflePanel
		JPanel rafflePanel = new JPanel(new BorderLayout());
		rafflePanel.setBackground(applet.brightGrey);
		rafflePanel.setPreferredSize(new Dimension(175, 110));
		//raffleCheckBox
		JCheckBox raffleCheckbox = new JCheckBox("Raffle (RM10)");
		raffleCheckbox.setBackground(applet.brightGrey);
		raffleCheckbox.setPreferredSize(new Dimension(150, 20));
		raffleCheckbox.setFont(new Font(Font.DIALOG, Font.BOLD, 17));
		raffleCheckbox.setHorizontalAlignment(JLabel.CENTER);
		rafflePanel.add(raffleCheckbox, BorderLayout.NORTH);
        //Image for merchandise
        ImageIcon raffleImage = new ImageIcon("image/page4/raffle.png");
        JLabel raffleImageLabel = new JLabel(raffleImage);
        rafflePanel.add(raffleImageLabel, BorderLayout.CENTER);
		row[2].add(rafflePanel);
        //spacer between raffle and next button
        JLabel spacer2 = new JLabel();
        spacer2.setPreferredSize(new Dimension(175, 110));
        row[2].add(spacer2);
		//Next button
		JButton nextPageButton = createNextButton();
		row[2].add(nextPageButton);
		body.add(row[2]);
		merchPanel.add(body);
		//JButton nextPageButton = createNextButton();
		//merchPanel.add(nextPageButton);
		return merchPanel;
    }
    
    private JButton createNextButton() {
        JButton nextPageButton = new JButton("Next");
        nextPageButton.setPreferredSize(new Dimension(175, 50));
        nextPageButton.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
        nextPageButton.addActionListener(e -> {
        	
            // Check for selected checkboxes
            boolean isAnyCheckboxSelected = false;
            boolean[] merchandiseData = new boolean[7];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    JCheckBox checkbox = getCheckbox(i, j);
                    merchandiseData[i*3+j] = checkbox.isSelected();
                    if (checkbox.isSelected()) {
                        isAnyCheckboxSelected = true;
                    }
                }
            }

            // If no checkbox is selected, show error message
            if (!isAnyCheckboxSelected) {
                JOptionPane.showMessageDialog(null, "Please select at least one merchandise!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            // Else, save the data into the applet and go to the next panel
            else {
                applet.merchandiseData = merchandiseData;
                applet.showPanel(NEXT_PANEL_NUMBER);
            }
        });
        return nextPageButton;
    }

    private JCheckBox getCheckbox(int r, int c) {
        return (JCheckBox) ((JPanel) row[r].getComponent(c)).getComponent(0);
    }

}

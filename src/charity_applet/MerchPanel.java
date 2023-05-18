package charity_applet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;

public class MerchPanel {
    private CharityRunApplet applet;
    private final int NEXT_PANEL_NUMBER = 4;
    //Stores panels for the merchandise for data retrieval
	private JPanel[] merchPanels = new JPanel[7];
	private String[]images = {
			"image/page4/t-shirt.png",
			"image/page4/waterbottle.png",
			"image/page4/mug.jpg",
			"image/page4/bag.png",
			"image/page4/medal.jpg",
			"image/page4/hat.png",
			"image/page4/raffle.png",
		};
	
	private String[] itemName = {
			"T-Shirt (RM20)",
			"Bottle (RM30)",
			"Mug (RM15)",
			"Bag (RM10)",
			"Medal (RM20)",
			"Hat (RM20)",
			"Raffle (RM10)",
		};
    
    public MerchPanel(CharityRunApplet a) {
    	applet = a;
    }
    
    public JPanel generate() {
    	JPanel merchPanel = new JPanel();
    	merchPanel.setBackground(applet.grey);
    	
    	//Header (create using applet function)
        JPanel header = applet.createHeaderPanel("MERCHANDISE");
        merchPanel.add(header);
    	
    	//Body
        JPanel body = createBodyPanel();
        merchPanel.add(body);

		return merchPanel;
    }

	private JPanel createBodyPanel() {
    	JPanel body = new JPanel(new FlowLayout());
    	body.setPreferredSize(new Dimension(600, 480)); 
    	body.setBackground(applet.brightGrey);
    	
    	//Add all 7 merchandise
    	for (int i=0; i<7; i++) {
    		JPanel singleMerchandise = createSingleMerchandisePanel(i);
    		body.add(singleMerchandise);
    		// Store merchandise panel into merchPanels for data retrieva
    		merchPanels[i] = singleMerchandise;	
        }
    	
        //spacer between raffle and next button
        JLabel spacer2 = new JLabel();
        spacer2.setPreferredSize(new Dimension(175, 110));
        body.add(spacer2);
        
		//Next button
		JButton nextPageButton = createNextButton();
		body.add(nextPageButton);
    	
    	return body;
	}

	private JPanel createSingleMerchandisePanel(int index) {
		//Create panel for merchandise
		JPanel singleMerchandise = new JPanel(new BorderLayout());
		singleMerchandise.setBackground(applet.brightGrey);
		//Special case for raffle
		if (index == 6)
			singleMerchandise.setPreferredSize(new Dimension(175, 110));
		else
			singleMerchandise.setPreferredSize(new Dimension(175, 180));
		
		//Check box to select merchandise
		//Retrieve label for checkbox from itemName attribute
		JCheckBox checkbox = new JCheckBox(itemName[index]);
		checkbox.setBackground(applet.brightGrey);
		checkbox.setPreferredSize(new Dimension(150, 20));
		checkbox.setFont(new Font(Font.DIALOG, Font.BOLD, 17));
		checkbox.setHorizontalAlignment(JLabel.CENTER);
		singleMerchandise.add(checkbox, BorderLayout.NORTH);
        
        //Image for merchandise
		//Retrieve image for checkbox from images attribute
        ImageIcon item = new ImageIcon(images[index]);
        JLabel itemLabel = new JLabel(item);
        singleMerchandise.add(itemLabel, BorderLayout.CENTER);
        
        return singleMerchandise;
	}


	private JButton createNextButton() {
        JButton nextPageButton = new JButton("Next");
        nextPageButton.setPreferredSize(new Dimension(175, 50));
        nextPageButton.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
        
        //When click, the button will...
        nextPageButton.addActionListener(e -> {
            // Retrieve data
            boolean[] merchandiseData = retrieveMerchandiseData();
            // If no checkbox is selected, show error message
            if (isInvalid(merchandiseData)) {
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


	private boolean[] retrieveMerchandiseData(){        
        boolean[] merchandiseData = new boolean[7];
        for (int i = 0; i < 7; i++) {
            JCheckBox checkbox = (JCheckBox) ((JPanel) merchPanels[i]).getComponent(0);
            merchandiseData[i] = checkbox.isSelected();
        }
        return merchandiseData;
	}

	private boolean isInvalid(boolean[] merchandiseData) {
		boolean invalid = true;
        //Loop through entire array, string by string
		for (boolean value : merchandiseData) {
        	//if a selected merchandise is found
		    if (value) {
		        invalid = false;	//Data is now valid, so set invalid to false
		        break;				//No need to check if other merchandises are selected
		    }
		}		
		return invalid;
	}
}

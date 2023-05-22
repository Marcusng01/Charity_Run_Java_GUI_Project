package charity_applet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class MerchPanel {
    private CharityRunApplet applet;
    private final int NEXT_PANEL_NUMBER = 4;
    //Stores check boxes for the merchandise for data retrieval
	private JCheckBox[] checkBoxes = new JCheckBox[7];
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
        }
    	
        //Add text between raffle and next button
        JEditorPane information = applet.createTextBox("<div style='padding: 15px;'>Reminder:<br>T-Shirts and raffles<br>are compulsory :)</div>");
        information.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));	//Set font
        information.setPreferredSize(new Dimension(175, 110));
        information.setBackground(applet.brightGrey);
        body.add(information);
        
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

		//Check box to select merchandise (store in checkboxes attribute to retrieve input in the future)
		checkBoxes[index] = new JCheckBox(itemName[index]);		//Retrieve label for check box from itemName attribute
		checkBoxes[index].setBackground(applet.brightGrey);
		checkBoxes[index].setPreferredSize(new Dimension(150, 20));
		checkBoxes[index].setFont(new Font(Font.DIALOG, Font.BOLD, 17));
		checkBoxes[index].setHorizontalAlignment(JLabel.CENTER);
		if (index == 0 || index == 6) {
			setAlwaysSelected(checkBoxes[index]);
		}
		singleMerchandise.add(checkBoxes[index], BorderLayout.NORTH);

        //Image for merchandise
        ImageIcon item = new ImageIcon(images[index]);		//Retrieve image for check box from images attribute
        JLabel itemLabel = new JLabel(item);
        singleMerchandise.add(itemLabel, BorderLayout.CENTER);

        return singleMerchandise;
	}


	private void setAlwaysSelected(JCheckBox checkBox) {
		checkBox.setSelected(true);
		checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Set the checkbox state to selected regardless of user interaction
                checkBox.setSelected(true);
            }
        });
	}

	private JButton createNextButton() {
        JButton nextPageButton = new JButton("Next");
        nextPageButton.setPreferredSize(new Dimension(175, 50));
        nextPageButton.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
        
        //When click, the button will...
        nextPageButton.addActionListener(e -> {
            // Retrieve data
            boolean[] merchandiseData = retrieveMerchandiseData();
            // If no check box is selected, show error message
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
            JCheckBox checkbox = (JCheckBox) checkBoxes[i];
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

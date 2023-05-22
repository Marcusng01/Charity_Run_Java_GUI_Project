package charity_applet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;

public class CategoryPanel {
    private CharityRunApplet applet;
    private final int NEXT_PANEL_NUMBER = 3;
	private JRadioButton[] radioButtons = new JRadioButton[3];
	private String[] images = {
			"image/page3/3km.png",
			"image/page3/7km.png",
			"image/page3/10km.png"
	};
	private String[] categories = {
			"3km",
			"7km",
			"10km"
	};
    
    public CategoryPanel(CharityRunApplet a) {
    	applet = a;
    }
    
    public JPanel generate() {
    	JPanel categoryPanel = new JPanel();
    	categoryPanel.setBackground(applet.grey);

    	//Header (create using applet function)
        JPanel header = applet.createHeaderPanel("CATEGORY");
        categoryPanel.add(header);
    	
    	//Body
    	JPanel body = createBodyPanel();
    	categoryPanel.add(body);

		return categoryPanel;
    }
    
	private JPanel createBodyPanel() {
		JPanel body= new JPanel(new FlowLayout());
    	body.setPreferredSize(new Dimension(600, 510));
    	body.setBackground(applet.brightGrey);
    	
    	//Radio Panel for all categories
    	JPanel radioPanel = createCategoryRadioPanel();
    	body.add(radioPanel);

    	//Next Button
    	JPanel buttonPanel = createButtonPanel();
		body.add(buttonPanel);
		
		return body;
	}

	private JPanel createCategoryRadioPanel() {
        JPanel radioPanel = new JPanel(new FlowLayout());
        radioPanel.setBackground(applet.brightGrey);
        radioPanel.setPreferredSize(new Dimension(600, 440));
    	//There are 3 radio buttons. One for each category
        ButtonGroup categoryGroup = new ButtonGroup();
    	for (int i=0; i<3; i++) {
        	//Create and store radio panel in radio[] attribute
    		JPanel radio = new JPanel(new BorderLayout());
    		radio.setBackground(applet.brightGrey);
    		radio.setPreferredSize(new Dimension(500, 140));
    		
    		//Create image for radio panel
            ImageIcon map = new ImageIcon(images[i]);
            JLabel mapLabel = new JLabel(map);
            mapLabel.setPreferredSize(new Dimension(300, 130));
            radio.add(mapLabel, BorderLayout.WEST);
            
            //Create radio button for radio panel
            radioButtons[i] = new JRadioButton(categories[i]);
            radioButtons[i].setBackground(applet.brightGrey);
            radioButtons[i].setFont(new Font(Font.DIALOG, Font.BOLD, 30));
            categoryGroup.add(radioButtons[i]); //Add the radio button to the same category
            radio.add(radioButtons[i], BorderLayout.EAST);
            
            radioPanel.add(radio);
    	}		
    	return radioPanel;
	}
	
	//Create a border layout to place button on right side
	//Without this function, button will appear in the middle.
	//Because the parent body panel has FlowLayout
	private JPanel createButtonPanel() {
    	JPanel buttonPanel = new JPanel(new BorderLayout());
    	buttonPanel.setPreferredSize(new Dimension(575, 50));
    	buttonPanel.setBackground(applet.brightGrey);
		JButton nextPageButton = createNextButton();
		//Place button to the right
		buttonPanel.add(nextPageButton,BorderLayout.EAST);
		return buttonPanel;
	}

	private JButton createNextButton() {
		JButton nextPageButton = new JButton("Next");
		nextPageButton.setPreferredSize(new Dimension(100,50));
		nextPageButton.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
		
        //When click, the button will...
		nextPageButton.addActionListener( e-> {
            // Retrieve data
			String categoryData = retrieveCategoryData();
            // If data is invalid, show error message
            if (isInvalid(categoryData)) {
            	//Display error message
                JOptionPane.showMessageDialog(null, "Please Select a Category!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            // Else save the data into applet go to the next panel
            else {
                applet.categoryData = categoryData;
                applet.showPanel(NEXT_PANEL_NUMBER);
            }
		});
	    return nextPageButton;
	}
	
	//Created for consistency's sake with formPanel and merchPanel
    private boolean isInvalid(String categoryData) {
		return categoryData=="";
	}

	private String retrieveCategoryData() {
    	//Retrieve radio button for 3km, 7km, 10km
		JRadioButton km3 = (JRadioButton) radioButtons[0];
		JRadioButton km7 = (JRadioButton) radioButtons[1];
		JRadioButton km10 = (JRadioButton) radioButtons[2];
		
		//Check for selected button
		if (km3.isSelected()) {
			return "3km";
		} else if (km7.isSelected()) {
			return "7km";
		} else if (km10.isSelected()){
			return "10km";
		}
		//Return empty string if none is selected
		return "";
    }
}
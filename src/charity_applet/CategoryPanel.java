package charity_applet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;

public class CategoryPanel {
    private CharityRunApplet applet;
    private final int NEXT_PANEL_NUMBER = 3;
	private JPanel[] radio = new JPanel[3];
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
    	
    	
    	//Header
    	JPanel header = new JPanel(new BorderLayout());
    	header.setPreferredSize(new Dimension(600, 100));
    	header.setBackground(applet.grey);
    	//Label for header
    	JLabel category = new JLabel("CATEGORY");
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
    	categoryPanel.add(header);
    	
    	
    	//Body
    	JPanel body = new JPanel(new FlowLayout());
    	body.setPreferredSize(new Dimension(600, 500)); 
    	body.setBackground(applet.brightGrey);
        ButtonGroup categoryGroup = new ButtonGroup();
    	for (int i=0; i<3; i++) {
    		radio[i] = new JPanel(new BorderLayout());
    		radio[i].setBackground(applet.brightGrey);
    		radio[i].setPreferredSize(new Dimension(500, 140));
    		
            ImageIcon map = new ImageIcon(images[i]);
            JLabel mapLabel = new JLabel(map);
            mapLabel.setPreferredSize(new Dimension(300, 130));
            radio[i].add(mapLabel, BorderLayout.WEST);
            
            JRadioButton radioButton = new JRadioButton(categories[i]);

            radioButton.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
            categoryGroup.add(radioButton);
            radio[i].add(radioButton, BorderLayout.EAST);
            
            body.add(radio[i]);
    	}
    	JPanel buttonPanel = new JPanel(new BorderLayout());
    	buttonPanel.setPreferredSize(new Dimension(575, 50));
    	buttonPanel.setBackground(applet.brightGrey);
		JButton nextPageButton = createNextButton();
		buttonPanel.add(nextPageButton,BorderLayout.EAST);
		body.add(buttonPanel);
    	categoryPanel.add(body);

		return categoryPanel;
    }
    
	private JButton createNextButton() {
		JButton nextPageButton = new JButton("Next");
		nextPageButton.setPreferredSize(new Dimension(100,50));
		nextPageButton.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
		nextPageButton.addActionListener( e-> {

            // If data is invalid, show error message
			String category = retrieveCategory();
            if (category=="") {
                JOptionPane.showMessageDialog(null, "Please Select a Category!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            // Else save the data into applet go to the next panel
            else {
                applet.categoryData = category;
                applet.showPanel(NEXT_PANEL_NUMBER);
            }
		});
	    return nextPageButton;
	}
    private String retrieveCategory() {
    	String category = "";
		JRadioButton km3 = (JRadioButton) radio[0].getComponent(1);
		JRadioButton km7 = (JRadioButton) radio[1].getComponent(1);
		JRadioButton km10 = (JRadioButton) radio[2].getComponent(1);

		if (km3.isSelected()) {
			category = "3km";
		} else if (km7.isSelected()) {
			category = "7km";
		} else if (km10.isSelected()){
			category = "10km";
		}
		return category;
    }


}
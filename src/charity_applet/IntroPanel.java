package charity_applet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.*;

public class IntroPanel{
    private CharityRunApplet applet;
    private final int NEXT_PANEL_NUMBER = 1;
    private String aboutUs= "<html style='text-align: justify;'>"
    		+"Charity Run 2023 is a meaningful event organized by UTHM students with the aim of raising funds to support the victims affected by the devastating floods. We extend a warm invitation to all individuals who are passionate about making a difference through running!"
    		+ "</html>";
    		
    public IntroPanel(CharityRunApplet a) {
    	applet = a;
    }
    
    public JPanel generate() {
    	JPanel introPanel = new JPanel(new FlowLayout());
    	introPanel.setBackground(applet.grey);
    	
        // Header
        JPanel header = createHeaderPanel();
        introPanel.add(header);

        // Body
        JPanel body = createBodyPanel();
        introPanel.add(body);
        
        // Footer
        JPanel footer = createFooterPanel();
        introPanel.add(footer);

        return introPanel;

    }
	

    
	private JPanel createHeaderPanel() {
    	JPanel header = new JPanel(new BorderLayout());
    	header.setPreferredSize(new Dimension(600, 250));
    	header.setBackground(applet.grey);
    	
    	//Get image from folder
        ImageIcon logo = new ImageIcon("image/page1/logo.png");
        JLabel logoLabel = new JLabel(logo);
        
        //Add image to header panel
        header.add(logoLabel,BorderLayout.CENTER);
        
        return header;
	}

	private JPanel createBodyPanel() {
    	JPanel body = new JPanel(new FlowLayout());
    	body.setBackground(applet.grey);
    	
    	//Create text box using applet function
        JEditorPane aboutUsTextBox = applet.createTextBox(aboutUs);
        aboutUsTextBox.setPreferredSize(new Dimension(400,200));		//Set size
        aboutUsTextBox.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));	//Set font
        aboutUsTextBox.setBackground(applet.grey);						//Set background color
        
        //Add textbox to body panel
    	body.add(aboutUsTextBox);
    	
    	return body;
	}

	private JPanel createFooterPanel() {
    	JPanel footer = new JPanel(new FlowLayout());
    	footer.setPreferredSize(new Dimension(400,200));
    	footer.setBackground(applet.grey);
    	
    	//Create next page button
		JButton nextPageButton = createNextButton();
		//Add next page button to footer panel
		footer.add(nextPageButton,BorderLayout.CENTER);
		
    	return footer;
	}

	private JButton createNextButton() {
		JButton nextPageButton = new JButton("Register Now!");
		nextPageButton.setPreferredSize(new Dimension(200,100));		//Set size
		nextPageButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));	//Set font
		nextPageButton.addActionListener( e-> {							//Show next page
			applet.showPanel(NEXT_PANEL_NUMBER);
			
		});
	    return nextPageButton;
	}
}
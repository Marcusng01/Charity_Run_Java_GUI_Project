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
    	
    	//Header
    	JPanel header = new JPanel(new BorderLayout());
    	header.setPreferredSize(new Dimension(600, 250));
    	header.setBackground(applet.grey);
        ImageIcon logo = new ImageIcon("image/page1/logo.png");
        JLabel logoLabel = new JLabel(logo);
        header.add(logoLabel,BorderLayout.CENTER);
        introPanel.add(header);

    	
    	//Body
    	JPanel body = new JPanel(new FlowLayout());
    	body.setBackground(applet.grey);
        JEditorPane aboutUsTextBox = applet.createTextBox(aboutUs);
        aboutUsTextBox.setPreferredSize(new Dimension(400,200));
        aboutUsTextBox.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
    	body.add(aboutUsTextBox);
    	introPanel.add(body);
        
    	//Footer
    	JPanel footer = new JPanel(new FlowLayout());
    	footer.setPreferredSize(new Dimension(400,200));
    	footer.setBackground(applet.grey);
		JButton nextPageButton = createNextButton();
		footer.add(nextPageButton,BorderLayout.CENTER);
    	introPanel.add(footer);
    	
    	return introPanel;
    }
	

    
	private JButton createNextButton() {
		JButton nextPageButton = new JButton("Register Now!");
		nextPageButton.setPreferredSize(new Dimension(200,100));
		nextPageButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
		nextPageButton.addActionListener( e-> {
			applet.showPanel(NEXT_PANEL_NUMBER);
			
		});
	    return nextPageButton;
	}
}
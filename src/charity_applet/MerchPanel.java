package charity_applet;

import javax.swing.*;

public class MerchPanel {
    private CharityRunApplet applet;
    private final int NEXT_PANEL_NUMBER = 4;
    
    public MerchPanel(CharityRunApplet a) {
    	applet = a;
    }
    
    public JPanel generate() {
    	JPanel merchPanel = new JPanel();
    	
		JButton nextPageButton = createNextButton();
		merchPanel.add(nextPageButton);
		return merchPanel;
    }
    
	private JButton createNextButton() {
		JButton nextPageButton = new JButton("Next");
		nextPageButton.addActionListener( e-> {
			applet.showPanel(NEXT_PANEL_NUMBER);
		});
	    return nextPageButton;
	}
}

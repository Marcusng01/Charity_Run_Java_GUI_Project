package charity_applet;

import javax.swing.*;

public class CategoryPanel {
    private CharityRunApplet applet;
    private final int NEXT_PANEL_NUMBER = 3;
    
    public CategoryPanel(CharityRunApplet a) {
    	applet = a;
    }
    
    public JPanel generate() {
    	JPanel categoryPanel = new JPanel();
    	
		JButton nextPageButton = createNextButton();
		categoryPanel.add(nextPageButton);
		return categoryPanel;
    }
    
	private JButton createNextButton() {
		JButton nextPageButton = new JButton("Next");
		nextPageButton.addActionListener( e-> {
			applet.showPanel(NEXT_PANEL_NUMBER);
		});
	    return nextPageButton;
	}
}
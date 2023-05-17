package charity_applet;

import javax.swing.*;

public class IntroPanel{
    private CharityRunApplet applet;
    private final int NEXT_PANEL_NUMBER = 1;
    
    public IntroPanel(CharityRunApplet a) {
    	applet = a;
    }
    
    public JPanel generate() {
    	JPanel introPanel = new JPanel();
    	
		JButton nextPageButton = new JButton("Next");
		nextPageButton.addActionListener( e-> {
			applet.showPanel(NEXT_PANEL_NUMBER);
		});
		
		introPanel.add(nextPageButton);
		return introPanel;
    }
	
}

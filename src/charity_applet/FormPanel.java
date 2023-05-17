package charity_applet;

import javax.swing.*;

public class FormPanel {
    private CharityRunApplet applet;
    private final int NEXT_PANEL_NUMBER = 2;
    
    public FormPanel(CharityRunApplet a) {
    	applet = a;
    }
    
    public JPanel generate() {
    	JPanel formPanel = new JPanel();
    	
		JButton nextPageButton = new JButton("Next");
		nextPageButton.addActionListener( e-> {
			applet.showPanel(NEXT_PANEL_NUMBER);
		});
		
		formPanel.add(nextPageButton);
		return formPanel;
    }
}

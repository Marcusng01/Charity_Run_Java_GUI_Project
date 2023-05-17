package charity_applet;

import javax.swing.*;

public class ReceiptPanel {
    private CharityRunApplet applet;
    private final int NEXT_PANEL_NUMBER = 5;
    
    public ReceiptPanel(CharityRunApplet a) {
    	applet = a;
    }
    
    public JPanel generate() {
    	JPanel receiptPanel = new JPanel();
    	
		JButton nextPageButton = new JButton("Next");
		nextPageButton.addActionListener( e-> {
			applet.showPanel(NEXT_PANEL_NUMBER);
		});
		
		receiptPanel.add(nextPageButton);
		return receiptPanel;
    }
}

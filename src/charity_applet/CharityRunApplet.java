package charity_applet;

import java.awt.Color;

import javax.swing.*;

public class CharityRunApplet extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel introPanel;
    protected JPanel formPanel;
    protected JPanel categorypanel;
    protected JPanel merchPanel;
    protected JPanel receiptPanel;
    protected Color grey = Color.decode("#cbcdbf");
    protected Color brightGrey = Color.decode("#f0ebe5");
    
    protected String[] formData; 
    protected String categoryData;
    protected String[] merchandiseData; 
    
    public CharityRunApplet() {
        introPanel = new IntroPanel(this).generate();
        formPanel = new FormPanel(this).generate();
        categorypanel = new CategoryPanel(this).generate();
        merchPanel = new MerchPanel(this).generate();
        receiptPanel = new ReceiptPanel(this).generate();
        
    	setTitle("Welcome to Charity Run 2023!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);

        showPanel(0); // Display the first panel

    }

    protected void showPanel(int PanelNumber) {
        getContentPane().removeAll();
        switch (PanelNumber) {
	        case 0:
	        	getContentPane().add(introPanel);
	            break;
	        case 1:
	        	getContentPane().add(formPanel);
	            break;
	        case 2:
	        	getContentPane().add(categorypanel);
	            break;
	        case 3:
	        	getContentPane().add(merchPanel);
	            break;
	        case 4:
	        	getContentPane().add(receiptPanel);
	            break;
	        default:
	            System.out.println("Invalid panel number.");
	            break;
        }
        revalidate();
        repaint();
    }
    
    //Function for all panels
    protected JEditorPane createTextBox(String text) {
    	JEditorPane textBox = new JEditorPane();
    	textBox.setContentType("text/html"); // Set content type to HTML
    	textBox.setEditable(false); // Disable editing
    	textBox.setText(text);
    	textBox.setBorder(null);
    	textBox.setBackground(grey);
    	textBox.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE); //Ensure that the font applies
		return textBox;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	CharityRunApplet app = new CharityRunApplet();
            app.setVisible(true);
        });
    }
}

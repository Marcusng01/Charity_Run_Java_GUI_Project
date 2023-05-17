package charity_applet;

import java.awt.Color;

import javax.swing.*;

public class CharityRunApplet extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel introPanel;
    protected JPanel formPanel;
    protected JPanel merchPanel;
    protected JPanel categorypanel;
    protected JPanel receiptPanel;
    protected String[] formData; 
    protected Color grey = Color.decode("#cbcdbf");
    protected Color brightGrey = Color.decode("#f0ebe5");
    
    public CharityRunApplet() {
        introPanel = new IntroPanel(this).generate();
        formPanel = new FormPanel(this).generate();
        merchPanel = new MerchPanel(this).generate();
        categorypanel = new CategoryPanel(this).generate();
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
	        	getContentPane().add(merchPanel);
	            break;
	        case 3:
	        	getContentPane().add(categorypanel);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	CharityRunApplet app = new CharityRunApplet();
            app.setVisible(true);
        });
    }
}

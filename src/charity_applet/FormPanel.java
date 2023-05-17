package charity_applet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.*;

public class FormPanel {
    private final int NEXT_PANEL_NUMBER = 2;
    private String[] labels = {
            "FULL NAME",
            "GENDER",
            "ADDRESS 1",
            "ADDRESS 2",
            "CITY",
            "POSTCODE",
            "STATE",
            "EMAIL",
            "PHONE NUMBER",
    };

    private String[] states = {
    	    "Johor",
    	    "Kedah",
    	    "Kelantan",
    	    "Melaka",
    	    "Negeri Sembilan",
    	    "Pahang",
    	    "Perak",
    	    "Perlis",
    	    "Pulau Pinang",
    	    "Sabah",
    	    "Sarawak",
    	    "Selangor",
    	    "Terengganu",
    	    "Wilayah Persekutuan Kuala Lumpur",
    	    "Wilayah Persekutuan Labuan",
    	    "Wilayah Persekutuan Putrajaya"
    	};

    private Color grey = Color.decode("#cbcdbf");
    private Color brightGrey = Color.decode("#f0ebe5");

    private CharityRunApplet applet;
    private JPanel[] formPanels = new JPanel[9];

    public FormPanel(CharityRunApplet applet) {
        this.applet = applet;
    }

    public JPanel generate() {
        JPanel formPanel = new JPanel(new FlowLayout());
        formPanel.setBackground(grey);

        JPanel container = new JPanel(new FlowLayout());
        container.setPreferredSize(new Dimension(300, 600));
        container.setBackground(brightGrey);

        // Form Header
        JPanel header = new JPanel(new FlowLayout());
        header.setPreferredSize(new Dimension(300, 50));
        JLabel headerLabel = new JLabel("<html><u><big>RUNNER DETAILS<big></u></html>");
        headerLabel.setFont(headerLabel.getFont().deriveFont(Font.BOLD));
        header.setBackground(brightGrey);
        header.add(headerLabel);
        container.add(header);

        // Form Panels
        for (int i = 0; i < 9; i++) {
        	formPanels[i] = new JPanel(new FlowLayout());
        	formPanels[i].setPreferredSize(new Dimension(300, 50));
        	formPanels[i].setBackground(brightGrey);

            JLabel label = new JLabel(labels[i]);
            label.setPreferredSize(new Dimension(250, 20));
            formPanels[i].add(label);

            if (i == 1) {
                JPanel radioPanel = createGenderPanel();
                formPanels[i].add(radioPanel);
            } else if(i == 6) {
            	JComboBox<String> stateComboBox = new JComboBox<>(states);
            	stateComboBox.setPreferredSize(new Dimension(250,20));
				formPanels[i].add(stateComboBox,BorderLayout.EAST);
            } else {
                JTextField text = new JTextField();
                text.setPreferredSize(new Dimension(250, 20));
                formPanels[i].add(text);
            }

            container.add(formPanels[i]);
        }

        JPanel footer = new JPanel(new FlowLayout());
        footer.setPreferredSize(new Dimension(300, 50));
        footer.setBackground(brightGrey);
        JButton nextPageButton = createNextButton();
        footer.add(nextPageButton);
        container.add(footer);

        formPanel.add(container);
        return formPanel;
    }

    private JPanel createGenderPanel() {
        JRadioButton femaleRadio = new JRadioButton("FEMALE");
        femaleRadio.setPreferredSize(new Dimension(100, 20));
        JRadioButton maleRadio = new JRadioButton("MALE");
        maleRadio.setPreferredSize(new Dimension(100, 20));
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(femaleRadio);
        genderGroup.add(maleRadio);

        JPanel radioPanel = new JPanel(new BorderLayout());
        radioPanel.setPreferredSize(new Dimension(200, 20));
        radioPanel.add(maleRadio, BorderLayout.WEST);
        radioPanel.add(femaleRadio, BorderLayout.EAST);

        return radioPanel;
    }

    private String errorString(String[] d) {
    	String error = "Error, input is invalid for: \n";
        for (int i = 0; i < 9; i++) {
        	if (d[i]=="")
        		error += labels[i] + "\n";
        }
    	return error ;
    	
    }
    
    private JButton createNextButton() {
        JButton nextPageButton = new JButton("Next");
        nextPageButton.addActionListener(e -> {
        	//Retrieve data and check if it contains empty string
        	//Empty string means that the input is invalid
        	String[] data = retrieveData();
        	boolean containsEmptyString = false;
        	for (String str : data) {
        	    if (str.equals("")) {
        	        containsEmptyString = true;
        	        break;
        	    }
        	}
        	//If data is invalid, show error message
        	if(containsEmptyString){
        		JOptionPane.showMessageDialog(null, errorString(data), "Error", JOptionPane.ERROR_MESSAGE);
        	}
        	//Else save the data into applet go to next panel 
        	else {
        		applet.formData = data;
                applet.showPanel(NEXT_PANEL_NUMBER);
        	}
        });
        return nextPageButton;
    }
    
    private String[] retrieveData() {
        String[] data = new String[9];
        for (int i = 0; i < 9; i++) {
        	//Check if radio either Male or Female radio button is selected
        	if (i==1) {
        		JPanel radioPanel = (JPanel) formPanels[i].getComponent(1);
        		JRadioButton maleButton = (JRadioButton) radioPanel.getComponent(0);
        		JRadioButton femaleButton = (JRadioButton) radioPanel.getComponent(1);
        		
        		if (maleButton.isSelected()) {
        			data[i]=("male");
        		} else if (femaleButton.isSelected()) {
        			data[i]=("female");
        		} else {
        			data[i]=("");
        		}
        		
    		}
        	//Check ComboBox
        	else if (i==6){
        		//This suppression is okay, because formPanel number 6 component 1 is hard coded to be the combo box
        		@SuppressWarnings("unchecked")
				JComboBox<String> component = (JComboBox<String>) formPanels[i].getComponent(1);
				JComboBox<String> stateComboBox = component;
				data[i]=(stateComboBox.getSelectedItem().toString() );
        	}
        	//Check the other text fields
        	else {
            	JTextField textField = (JTextField) formPanels[i].getComponent(1);
            	String text = textField.getText().trim();    	
                switch (i) {
                	//Check if Full Name contains only alphabets and spaces
    		        case 0:
    		        	if(!text.matches("[a-zA-Z\\s]+")) {
    		        		text = "";
    		        	}
    		            break;
		            //Check if Address 1 and 2 contains only alphabets, numbers, spaces and hyphens
    		        case 2:
    		        	if(!text.matches("[a-zA-Z0-9\\s\\-,]+")) {
    		        		text = "";
    		        	}
    		            break;
    		        case 3:
    		        	if(!text.matches("[a-zA-Z0-9\\s\\-,]+")) {
    		        		text = "";
    		        	}
    		            break;
    		        //Check if City contains only alphabets and spaces
    		        case 4:
    		        	if(!text.matches("[a-zA-Z\\s]+")) {
    		        		text = "";
    		        	}
    		            break;
		            //Check if Post Code is a string with 5 digits
    		        case 5:
    		        	if(!text.matches("\\d{5}")) {
    		        		text = "";        		
    		        	}
    		            break;
		            //Check if email follows the format (Alphanumeric with . _ % + - symbols)@(Alphanumeric with . _).(2 or more alphabets)
    		        //Example: my1.user2_name3%is4+very5-long6@student.uthm.edu.my
    		        case 7:
      		        	if(!text.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
    		        		text = "";
    		        	}
    		            break;
    		        //Check if Post Code is a string with 10 or 11 digits
    		        case 8:
      		        	if(!text.matches("\\d{10,11}")) {
    		        		text = "";
    		        	}
    		            break;
    		        default:
    		            System.out.println("Invalid panel number.");
    		            break;
                }
                data[i]=(text);
        	}
        }
		return data;
    }
}


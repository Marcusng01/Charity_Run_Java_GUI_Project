package charity_applet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;

public class FormPanel {
    private CharityRunApplet applet;
    private final int NEXT_PANEL_NUMBER = 2;
    private String[] labels = {
            "FULL NAME",
            "GENDER",
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
    
    //Stores panels for all forms for data retrieval
    private JPanel[] formPanels = new JPanel[5];

    public FormPanel(CharityRunApplet applet) {
        this.applet = applet;
    }

    public JPanel generate() {
        JPanel formPanel = new JPanel(new FlowLayout());
        formPanel.setBackground(applet.grey);
        
        // Container for form
        JPanel container = createContainerPanel();
        formPanel.add(container);
        return formPanel;
    }

    private JPanel createContainerPanel() {
        JPanel container = new JPanel(new FlowLayout());
        container.setPreferredSize(new Dimension(300, 400));
        container.setBackground(applet.brightGrey);

        // Header
        JPanel header = createHeaderPanel();
        container.add(header);

        // Body
        JPanel body = createBodyPanel();
        container.add(body);
        
        // Footer
        JPanel footer = createFooterPanel();
        container.add(footer);

		return container;
	}
    
	private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new FlowLayout());
        header.setPreferredSize(new Dimension(300, 50));
        
        //Create the header label
        JLabel headerLabel = new JLabel("<html><u><big>RUNNER DETAILS<big></u></html>");
        headerLabel.setFont(headerLabel.getFont().deriveFont(Font.BOLD));
        header.setBackground(applet.brightGrey);
        header.add(headerLabel);
        
        return header;
	}

	private JPanel createBodyPanel() {
		JPanel body= new JPanel(new FlowLayout());
		body.setPreferredSize(new Dimension(300, 280));
		body.setBackground(applet.brightGrey);

		//There are 8 form panels. One for each field
        for (int i = 0; i < 5; i++) {
        	//Create and store form panel in formPanels[] attribute
            formPanels[i] = new JPanel(new FlowLayout());
            formPanels[i].setPreferredSize(new Dimension(300, 50));
            formPanels[i].setBackground(applet.brightGrey);

            //Create label for form panel
            JLabel label = new JLabel(labels[i]);
            label.setPreferredSize(new Dimension(250, 20));
            formPanels[i].add(label);

            //Create input for form panel
            if (i == 1) {
                //Create radio panel with male and female buttons 
                JPanel radioPanel = createGenderRadioPanel();
                formPanels[i].add(radioPanel);
            } else if (i == 2) {
            	//Create combo box, get list of Malaysian states from state attribute
                JComboBox<String> stateComboBox = new JComboBox<>(states);
                stateComboBox.setPreferredSize(new Dimension(250, 20));
                formPanels[i].add(stateComboBox, BorderLayout.EAST);
            } else {
                //Create text field for remaining 6 fields
                JTextField textField = new JTextField();
                textField.setPreferredSize(new Dimension(250, 20));
                formPanels[i].add(textField);
            }
            
            //Add the label and corresponding input
            body.add(formPanels[i]);
        }		
        return body;
	}

	private JPanel createGenderRadioPanel() {
        JPanel radioPanel = new JPanel(new BorderLayout());
        radioPanel.setPreferredSize(new Dimension(200, 20));

        //Create female radio button
        JRadioButton femaleRadio = new JRadioButton("FEMALE");
        femaleRadio.setPreferredSize(new Dimension(100, 20));
        femaleRadio.setBackground(applet.brightGrey);
		//Create male radio button
        JRadioButton maleRadio = new JRadioButton("MALE");
        maleRadio.setPreferredSize(new Dimension(100, 20));
        maleRadio.setBackground(applet.brightGrey);
        //Add male and female radio button to same group
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(femaleRadio);
        genderGroup.add(maleRadio);

        //Add male and female radio button to radio panel
        radioPanel.add(maleRadio, BorderLayout.WEST);
        radioPanel.add(femaleRadio, BorderLayout.EAST);

        return radioPanel;
    }
	
	private JPanel createFooterPanel() {
        JPanel footer = new JPanel(new FlowLayout());
        footer.setPreferredSize(new Dimension(300, 50));
        footer.setBackground(applet.brightGrey);
        
        // Create Next button
        JButton nextPageButton = createNextButton();
        footer.add(nextPageButton);
        return footer;
	}

    private JButton createNextButton() {
        JButton nextPageButton = new JButton("Next");
        //When click, the button will...
        nextPageButton.addActionListener(e -> {
            // Retrieve data
        	String[] formData = retrieveFormData();
            // If data is invalid, show error message
            if (isInvalid(formData)) {
            	//Generate error message based on invalid fields
            	String error = errorString(formData);
            	//Display error message
                JOptionPane.showMessageDialog(null, error , "Error", JOptionPane.ERROR_MESSAGE);
            }
            // Else save the data into applet and go to the next panel
            else {
                applet.formData = formData;
                applet.showPanel(NEXT_PANEL_NUMBER);
            }
        });
        return nextPageButton;
    }

    private String[] retrieveFormData() {
        String[] data = new String[5];
        for (int i = 0; i < 5; i++) {
        	//Special case for Gender Radio Button
            if (i == 1) {
            	//Validate and store data from Gender Radio Button
                data[i] = validateDataRadio();
            } 
        	//Special case for Combo Box
            else if (i == 2) {
            	//Validate and store data from Combo Box
                data[i] = validateDataComboBox();
            } 
            //Normal case for other text fields
            else {
                data[i] = validateDataText(i);
            }
        }
        return data;
    }

	private String validateDataText(int index) {
    	//Retrieve corresponding text field
        JTextField textField = (JTextField) formPanels[index].getComponent(1);
        //Retrieve text content from text field, also trim off any trailing white spaces
        String text = textField.getText().trim();
        switch (index) {
        	//FULL NAME
	        case 0:
	        	//Alphabetical and Spaces ONLY
	            if (!text.matches("[a-zA-Z\\s]+"))
	                text = "";
	            break;
	            
	        //EMAIL
	        case 3:
	        	//Must be in the following format ONLY:
	        	//(Alphanumeric with dots, underscore, percentage, plus, hyphen) @ (Alphanumeric with dots or hyphens) . (2 or more alphabets)
	        	//Example: user_name.01@gmail.com
	            if (!text.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
	                text = "";
	            }
	            break;
	            
		    //PHONE NUMBER
	        case 4:
	        	//10 or 11 digits ONLY
	            if (!text.matches("\\d{10,11}")) {
	                text = "";
	            }
	            break;
	            
	        default:
	            System.out.println("Invalid panel number.");
                text = "";
	            break;
	    }
	    return text;
	    //Note that text = "" when the conditions is not met
    	//This will cause isIncomplete(data) to be true, triggering an error pop up
	}

	private String validateDataRadio() {
    	//Retrieve radio panel, male radio button and female radio button
        JPanel radioPanel = (JPanel) formPanels[1].getComponent(1);
        JRadioButton maleButton = (JRadioButton) radioPanel.getComponent(0);
        JRadioButton femaleButton = (JRadioButton) radioPanel.getComponent(1);
        
		//Check for selected button
        if (maleButton.isSelected()) {
            return "male";
        } else if (femaleButton.isSelected()) {
        	return "female";
        } else {
    		//Return empty string if none is selected
        	//This will cause isIncomplete(data) to be true, triggering an error pop up
        	return "";
        }
	}
	
	private String validateDataComboBox() {
    	//Suppress warning because getComponent(1) will definitely return a combo box.
        @SuppressWarnings("unchecked")
        
        //Retrieve combo box
        JComboBox<String> stateComboBox = (JComboBox<String>) formPanels[2].getComponent(1);
        
        //Retrieve selection and convert to string
        return stateComboBox.getSelectedItem().toString();
	}
	
	private boolean isInvalid(String[] data) {
        boolean invalid = false;
        //Loop through entire array, string by string
        for (String str : data) {
        	//if an empty string is found, it means that the data is invalid
            if (str.isEmpty()) {
            	invalid = true;	//Set incomplete to true
                break;			//No need to check other indexes
            }
        }
        return invalid;
	}
	
    private String errorString(String[] data) {
    	//Error message starts with "Error, input is invalid for:\n"
        StringBuilder error = new StringBuilder("Error, input is invalid for:\n");
        //Loop through entire data array, using index
        for (int i = 0; i < 5; i++) {
        	//When missing data is found, 
            if (data[i].isEmpty()) {
            	//append the error message with label corresponding to missing data's index
                error.append(labels[i]).append("\n");
            }
        }
        return error.toString();
    }
}
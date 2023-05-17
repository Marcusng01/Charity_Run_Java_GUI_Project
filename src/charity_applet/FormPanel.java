package charity_applet;

import java.awt.BorderLayout;
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



    private CharityRunApplet applet;
    private JPanel[] formPanels = new JPanel[9];

    public FormPanel(CharityRunApplet applet) {
        this.applet = applet;
    }

    public JPanel generate() {
        JPanel formPanel = new JPanel(new FlowLayout());
        formPanel.setBackground(applet.grey);

        // Container for contrasting bright grey color
        JPanel container = new JPanel(new FlowLayout());
        container.setPreferredSize(new Dimension(300, 600));
        container.setBackground(applet.brightGrey);

        // Form Header
        JPanel header = new JPanel(new FlowLayout());
        header.setPreferredSize(new Dimension(300, 50));
        JLabel headerLabel = new JLabel("<html><u><big>RUNNER DETAILS<big></u></html>");
        headerLabel.setFont(headerLabel.getFont().deriveFont(Font.BOLD));
        header.setBackground(applet.brightGrey);
        header.add(headerLabel);
        container.add(header);

        // Form Panels (Body)
        for (int i = 0; i < 9; i++) {
            formPanels[i] = new JPanel(new FlowLayout());
            formPanels[i].setPreferredSize(new Dimension(300, 50));
            formPanels[i].setBackground(applet.brightGrey);

            JLabel label = new JLabel(labels[i]);
            label.setPreferredSize(new Dimension(250, 20));
            formPanels[i].add(label);

            if (i == 1) {
                JPanel radioPanel = createGenderPanel();
                formPanels[i].add(radioPanel);
            } else if (i == 6) {
                JComboBox<String> stateComboBox = new JComboBox<>(states);
                stateComboBox.setPreferredSize(new Dimension(250, 20));
                formPanels[i].add(stateComboBox, BorderLayout.EAST);
            } else {
                JTextField textField = new JTextField();
                textField.setPreferredSize(new Dimension(250, 20));
                formPanels[i].add(textField);
            }

            container.add(formPanels[i]);
        }

        //Footer
        JPanel footer = new JPanel(new FlowLayout());
        footer.setPreferredSize(new Dimension(300, 50));
        footer.setBackground(applet.brightGrey);
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

    private String errorString(String[] data) {
        StringBuilder error = new StringBuilder("Error, input is invalid for:\n");
        for (int i = 0; i < 9; i++) {
            if (data[i].isEmpty()) {
                error.append(labels[i]).append("\n");
            }
        }
        return error.toString();
    }

    private JButton createNextButton() {
        JButton nextPageButton = new JButton("Next");
        nextPageButton.addActionListener(e -> {
            // Retrieve data and check if it contains empty string
            // Empty string means that the input is invalid
            String[] data = retrieveData();
            boolean containsEmptyString = false;
            for (String str : data) {
                if (str.isEmpty()) {
                    containsEmptyString = true;
                    break;
                }
            }
            // If data is invalid, show error message
            if (containsEmptyString) {
                JOptionPane.showMessageDialog(null, errorString(data), "Error", JOptionPane.ERROR_MESSAGE);
            }
            // Else save the data into applet go to the next panel
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
            if (i == 1) {
                JPanel radioPanel = (JPanel) formPanels[i].getComponent(1);
                JRadioButton maleButton = (JRadioButton) radioPanel.getComponent(0);
                JRadioButton femaleButton = (JRadioButton) radioPanel.getComponent(1);

                if (maleButton.isSelected()) {
                    data[i] = "male";
                } else if (femaleButton.isSelected()) {
                    data[i] = "female";
                } else {
                    data[i] = "";
                }
            } else if (i == 6) {
                @SuppressWarnings("unchecked")
                JComboBox<String> stateComboBox = (JComboBox<String>) formPanels[i].getComponent(1);
                data[i] = stateComboBox.getSelectedItem().toString();
            } else {
                JTextField textField = (JTextField) formPanels[i].getComponent(1);
                String text = textField.getText().trim();
                switch (i) {
                    case 0:
                        if (!text.matches("[a-zA-Z\\s]+")) {
                            text = "";
                        }
                        break;
                    case 2:
                    case 3:
                        if (!text.matches("[a-zA-Z0-9\\s\\-,]+")) {
                            text = "";
                        }
                        break;
                    case 4:
                        if (!text.matches("[a-zA-Z\\s]+")) {
                            text = "";
                        }
                        break;
                    case 5:
                        if (!text.matches("\\d{5}")) {
                            text = "";
                        }
                        break;
                    case 7:
                        if (!text.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                            text = "";
                        }
                        break;
                    case 8:
                        if (!text.matches("\\d{10,11}")) {
                            text = "";
                        }
                        break;
                    default:
                        System.out.println("Invalid panel number.");
                        break;
                }
                data[i] = text;
            }
        }
        return data;
    }
}
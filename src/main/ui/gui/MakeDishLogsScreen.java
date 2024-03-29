package ui.gui;

import model.DishLog;
import model.ListOfDishLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

// Screen where user can create a new Dish Log, which will be added to their list of Dish Logs.
public class MakeDishLogsScreen extends Screen implements PropertyChangeListener, ActionListener {

    private String name = "";
    private String restaurant = "";
    private double price = 0.00;
    private int enjoymentLevel = 1;
    private String favourite = "no";

    private JButton addButton;

    private JLabel nameLabel;
    private JLabel restaurantLabel;
    private JLabel priceLabel;
    private JLabel enjoymentLevelLabel;
    private JLabel favouriteLabel;

    private JFormattedTextField nameField;
    private JFormattedTextField restaurantField;
    private JFormattedTextField priceField;
    private JFormattedTextField enjoymentLevelField;
    private JFormattedTextField favouriteField;

    private NumberFormat priceFormat;

    // EFFECTS: sets up screen for user to create new Dish Logs
    public MakeDishLogsScreen(ListOfDishLog listOfDishLog) {
        super(listOfDishLog);

        setUpLabels();
        setUpTextFields();
        pairLabelsAndTextFields();
        setUpAddButton();

        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        setUpPanels();
    }

    // MODIFIES: this
    // EFFECTS: pairs labels and text fields
    private void pairLabelsAndTextFields() {
        nameLabel.setLabelFor(nameField);
        restaurantLabel.setLabelFor(restaurantField);
        priceLabel.setLabelFor(priceField);
        enjoymentLevelLabel.setLabelFor(enjoymentLevelField);
        favouriteLabel.setLabelFor(favouriteField);
    }

    // MODIFIES: this
    // EFFECTS: sets up labels
    private void setUpLabels() {
        nameLabel = new JLabel("Name:");
        restaurantLabel = new JLabel("Restaurant:");
        priceLabel = new JLabel("Price");
        enjoymentLevelLabel = new JLabel("Enjoyment Level (1 to 5):");
        favouriteLabel = new JLabel("Favourite (yes or no):");
    }

    // MODIFIES: this
    // EFFECTS: sets up text fields
    private void setUpTextFields() {
        nameField = new JFormattedTextField();
        nameField.setText(name);
        setTextFieldProperty(nameField);

        restaurantField = new JFormattedTextField();
        restaurantField.setText(restaurant);
        setTextFieldProperty(restaurantField);

        priceFormat = NumberFormat.getNumberInstance();
        priceFormat.setMinimumFractionDigits(2);
        priceField = new JFormattedTextField(priceFormat);
        priceField.setValue(new Double(price));
        setTextFieldProperty(priceField);

        enjoymentLevelField = new JFormattedTextField();
        enjoymentLevelField.setValue(new Integer(enjoymentLevel));
        setTextFieldProperty(enjoymentLevelField);

        favouriteField = new JFormattedTextField();
        favouriteField.setText(favourite);
        setTextFieldProperty(favouriteField);
    }

    // MODIFIES: this
    // EFFECTS: sets up column amount and action listener for field
    private void setTextFieldProperty(JFormattedTextField field) {
        field.setColumns(15);
        field.addPropertyChangeListener("value", this);
    }

    // MODIFIES: this
    // EFFECTS: sets up label panel
    private JPanel setUpLabelPanel() {
        JPanel labelPane = new JPanel(new GridLayout(0,1));
        labelPane.add(nameLabel);
        labelPane.add(restaurantLabel);
        labelPane.add(priceLabel);
        labelPane.add(enjoymentLevelLabel);
        labelPane.add(favouriteLabel);
        return labelPane;
    }

    // MODIFIES: this
    // EFFECTS: sets up and displays panels
    private void setUpPanels() {
        JPanel labelPanel = setUpLabelPanel();
        JPanel textFieldPanel = setUpTextFieldPanel();
        JPanel buttonPanel = setUpButtonPanel();

        add(labelPanel, BorderLayout.LINE_START);
        add(textFieldPanel, BorderLayout.LINE_END);
        add(buttonPanel, BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: sets up text field panel
    private JPanel setUpTextFieldPanel() {
        JPanel fieldPanel = new JPanel(new GridLayout(0,1));
        fieldPanel.add(nameField);
        fieldPanel.add(restaurantField);
        fieldPanel.add(priceField);
        fieldPanel.add(enjoymentLevelField);
        fieldPanel.add(favouriteField);
        return fieldPanel;
    }

    // MODIFIES: this
    // EFFECTS: sets up button panel
    private JPanel setUpButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(addButton, BorderLayout.CENTER);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        return buttonPanel;
    }

    // MODIFIES: this
    // EFFECTS: sets up "add" button
    private void setUpAddButton() {
        addButton = new JButton("Add");
        addButton.setActionCommand("add");
        addButton.addActionListener(this);
        addButton.setEnabled(true);
        addButton.setToolTipText("Add this Dish Log to the list of Dish Logs");
    }

    // MODIFIES: this
    // EFFECTS: updates the field when value changes
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        Object source = e.getSource();
        if (source == nameField) {
            name = nameField.getText().toString();
        } else if (source == restaurantField) {
            restaurant = restaurantField.getText().toString();
        } else if (source == priceField) {
            price = ((Number)priceField.getValue()).doubleValue();
        } else if (source == enjoymentLevelField) {
            enjoymentLevel = ((Number)enjoymentLevelField.getValue()).intValue();
        } else if (source == favouriteField) {
            favourite = favouriteField.getText().toString();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the Dish Log to listOfDishLog if "add" button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean fav = false;
        if (name.isEmpty()) {
            name = "n/a";
        }
        if (restaurant.isEmpty()) {
            restaurant = "n/a";
        }
        if (price < 0) {
            price = 0;
        }
        if (enjoymentLevel < 1) {
            enjoymentLevel = 1;
        } else if (enjoymentLevel > 5) {
            enjoymentLevel = 5;
        }
        if (favourite.toLowerCase().equals("yes")) {
            fav = true;
        }
        if (e.getActionCommand().equals("add")) {
            DishLog dishLog = new DishLog(name, restaurant, price, enjoymentLevel, fav);
            listOfDishLog.addDishLog(dishLog);
        }
    }
}

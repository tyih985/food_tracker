package ui.gui;

import model.DishLog;
import model.ListOfDishLog;
import persistence.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// HomeScreen of the GUI
// This is the first screen the user will see when starting up the application.
// They will be able to save data to file and load data from file from this screen.
public class HomeScreen extends Screen {

    private static final String JSON_STORE = "./data/listOfDishLog.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private BufferedImage homeGraphic;
    private Image img;

    private JLabel picLabel;
    private JPanel textPanel;
    private JPanel buttonPanel;

    private JLabel title;
    private JLabel subtitle;

    private JButton saveButton;
    private JButton loadButton;

    private JLabel saveSuccess;
    private JLabel loadSuccess;

    // MODIFIES: this
    // EFFECTS: home screen for user to save data to file and load data from file
    public HomeScreen(ListOfDishLog listOfDishLog) {
        super(listOfDishLog);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setLayout(new GridLayout(3, 1));
        setBorder(BorderFactory.createEmptyBorder(40, 40, 10, 40));

        setUpGraphic();
        setUpTextPanel();
        setUpButtonPanel();
    }

    // MODIFIES: this
    // EFFECTS: sets up and displays graphic
    private void setUpGraphic() {
        try {
            homeGraphic = ImageIO.read(new File("src/main/ui/gui/images/home_graphic.png"));
            img = homeGraphic.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
            picLabel = new JLabel(new ImageIcon(img));
            add(picLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up and displays text
    private void setUpTextPanel() {
        textPanel = new JPanel(new GridLayout(2, 1));

        title = new JLabel("Dish Log App", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(new Color(83, 171, 150));

        subtitle = new JLabel("Would you like to load / save your data?", JLabel.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.ITALIC, 16));

        textPanel.add(title);
        textPanel.add(subtitle);
        add(textPanel);
    }

    // MODIFIES: this
    // EFFECTS: sets up and displays buttons and success messages
    private void setUpButtonPanel() {
        saveButton = new JButton("Save");
        saveButton.setToolTipText("Save data to file");
        loadButton = new JButton("Load previous data");
        loadButton.setToolTipText("Load data from file");

        saveSuccess = new JLabel("Saved data successfully!", SwingConstants.CENTER);
        saveSuccess.setForeground(new Color(83, 171, 150));
        saveSuccess.setVisible(false);
        loadSuccess = new JLabel("Loaded data successfully!", SwingConstants.CENTER);
        loadSuccess.setForeground(new Color(83, 171, 150));
        loadSuccess.setVisible(false);

        buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(saveSuccess);
        buttonPanel.add(loadSuccess);

        saveActionListener(saveButton, saveSuccess);
        loadActionListener(loadButton, loadSuccess);

        add(buttonPanel);
    }

    // MODIFIES: this
    // EFFECTS: loads data from file
    private void loadActionListener(JButton loadButton, JLabel loadSuccess) {
        loadButton.addActionListener(e -> {
            if (e.getSource() == loadButton) {
                try {
                    ListOfDishLog data = jsonReader.read();
                    if (!listOfDishLog.isEmpty()) {
                        listOfDishLog.getListOfDishLog().clear();
                    }
                    for (DishLog dishLog : data.getListOfDishLog()) {
                        listOfDishLog.addDishLog(dishLog);
                    }
                    loadSuccess.setVisible(true);
                } catch (IOException exception) {
                    System.out.println("\nUnable to read from file: " + JSON_STORE + "\n");
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: saves data to file
    private void saveActionListener(JButton saveButton, JLabel saveSuccess) {
        saveButton.addActionListener(e -> {
            if (e.getSource() == saveButton) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(listOfDishLog);
                    jsonWriter.close();
                    saveSuccess.setVisible(true);
                } catch (FileNotFoundException exception) {
                    System.out.println("\nUnable to write to file: " + JSON_STORE + "\n");
                }
            }
        });
    }
}

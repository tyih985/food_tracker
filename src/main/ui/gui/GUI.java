package ui.gui;

import model.ListOfDishLog;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

// Dish Log app in GUI.
public class GUI extends JFrame {

    public static final String JSON_STORE = "./data/listOfDishLog.json";
    private JsonWriter jsonWriter;

    public static final int WIDTH = 500;
    public static final int HEIGHT = 700;
    public static final Color backgroundColor = new Color(133, 232, 208);

    private static JTabbedPane tabbedPane;

    protected ListOfDishLog listOfDishLog;

    protected JPanel homeScreenTab;
    protected JPanel makeDishLogsScreen;
    protected JPanel dishLogsScreen;

    // EFFECTS: init
    public static void main(String[] args) {
        new GUI();
    }

    // MODIFIES: this
    // EFFECTS: runs the Dish Log App in GUI
    private GUI() {
        super("Dish Log App");
        listOfDishLog = new ListOfDishLog();
        jsonWriter = new JsonWriter(JSON_STORE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUpTabs();
        askToSaveOnExit();

        setSize(WIDTH, HEIGHT);
        getContentPane().setBackground(backgroundColor);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: asks user if they want to save data to file when closing application
    private void askToSaveOnExit() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Would you like to save data to file before quitting?",
                        "Save Reminder", JOptionPane.YES_NO_CANCEL_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    try {
                        jsonWriter.open();
                        jsonWriter.write(listOfDishLog);
                        jsonWriter.close();
                        System.out.println("\nSaved Dish Logs to " + JSON_STORE + "\n");
                        System.exit(0);
                    } catch (FileNotFoundException e) {
                        System.out.println("\nUnable to write to file: " + JSON_STORE + "\n");
                    }
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: sets up tabs to switch between screens
    public void setUpTabs() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setTabPlacement(JTabbedPane.TOP);
        add(tabbedPane);

        homeScreenTab = new HomeScreen(listOfDishLog);
        makeDishLogsScreen = new MakeDishLogsScreen(listOfDishLog);
        dishLogsScreen = new DishLogsScreen(listOfDishLog);

        tabbedPane.add(homeScreenTab, 0);
        tabbedPane.setTitleAt(0, "Home");

        tabbedPane.add(makeDishLogsScreen, 1);
        tabbedPane.setTitleAt(1, "Make Dish Logs");

        tabbedPane.add(dishLogsScreen, 2);
        tabbedPane.setTitleAt(2, "Dish Logs");
    }
}
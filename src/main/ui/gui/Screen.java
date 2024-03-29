package ui.gui;

import model.ListOfDishLog;

import javax.swing.*;
import java.awt.*;

// Abstract class for the different screens in GUI.
public abstract class Screen extends JPanel {
    protected ListOfDishLog listOfDishLog;

    // EFFECTS: default to border layout
    //          makes given listOfDishLog this instance's listOfDishLog
    public Screen(ListOfDishLog listOfDishLog) {
        super(new BorderLayout());
        this.listOfDishLog = listOfDishLog;
    }
}

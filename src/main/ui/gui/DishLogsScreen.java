package ui.gui;

import model.DishLog;
import model.ListOfDishLog;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Screen where the user will be able to view their Dish Logs.
// They can also remove Dish Logs from their list if they wish.
public class DishLogsScreen extends Screen implements ActionListener, ListSelectionListener {

    private JList list;
    private DefaultListModel listModel;
    private JScrollPane listScrollPane;
    private JPanel buttonPane;

    private JButton refreshButton;
    private JButton removeButton;

    // EFFECTS: sets up screen for user to view / edit their list of Dish Logs
    public DishLogsScreen(ListOfDishLog listOfDishLog) {
        super(listOfDishLog);

        setUpScrollPanel();
        setUpButtonPanel();
    }

    // MODIFIES: this
    // EFFECTS: sets up scroll panel that contains listOfDishLog
    private void setUpScrollPanel() {
        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        refreshList();
        list.setSelectedIndex(0);
        listScrollPane = new JScrollPane(list);
        add(listScrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: sets up refresh and remove buttons
    private void setUpButtonPanel() {
        refreshButton = new JButton("Refresh");
        refreshButton.setActionCommand("refresh");
        refreshButton.addActionListener(this);
        refreshButton.setEnabled(true);
        refreshButton.setToolTipText("Refresh the list of Dish Logs");

        removeButton = new JButton("Remove");
        removeButton.setActionCommand("remove");
        removeButton.addActionListener(this);
        removeButton.setEnabled(true);
        removeButton.setToolTipText("Remove selected Dish Log from the list of Dish Logs");

        buttonPane = new JPanel();
        buttonPane.setLayout(new BorderLayout());
        buttonPane.add(refreshButton, BorderLayout.LINE_START);
        buttonPane.add(removeButton, BorderLayout.LINE_END);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 50));

        add(buttonPane, BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: removes selected Dish Log if "remove" button pressed
    //          refreshes listOfDishLog if "refresh" button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("remove")) {
            if (listModel.getSize() != 0) {
                int index = list.getSelectedIndex();
                listModel.remove(index);
                listOfDishLog.removeDishLog(index);

                if (index == listModel.getSize()) {
                    index--;
                }
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        } else if (e.getActionCommand().equals("refresh")) {
            refreshList();
        }
    }

    // MODIFIES: this
    // EFFECTS: refreshes listOfDishLog
    private void refreshList() {
        listModel.removeAllElements();
        for (DishLog dishLog : listOfDishLog.getListOfDishLog()) {
            listModel.addElement(dishLog.printReport());
        }
        list.setSelectedIndex(listModel.size() - 1);
    }

    // EFFECTS: does nothing
    @Override
    public void valueChanged(ListSelectionEvent e) {
    }

}

package model;

import java.util.ArrayList;
import java.util.List;

// ListOfDishLog represents past dishes the user has logged.
// User's DishLogs are stored in ListOfDishLog as List<DishLog>.
public class ListOfDishLog {
    private List<DishLog> listOfDishLog;

    // EFFECTS: Makes a new ListOfDishLog with a listOfDishLog that is empty
    public ListOfDishLog() {
        this.listOfDishLog = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds DishLog at end of listOfDishLog
    public void addDishLog(DishLog dishLog) {
        listOfDishLog.add(dishLog);
    }

    // REQUIRES: !listOfDishLog.isEmpty() and index in [0, listOfDishLog.size())
    // MODIFIES: this
    // EFFECTS: removes DishLog at given index from listOfDishLog
    public void removeDishLog(int index) {
        listOfDishLog.remove(index);
    }

    // EFFECTS: returns List<DishLog> with DishLog(s) in listOfDishLog that contain given name (not case-sensitive)
    public List<DishLog> filterByName(String name) {
        List<DishLog> filteredListOfDishLog = new ArrayList<>();
        for (DishLog dishLog : listOfDishLog) {
            if (dishLog.getName().toLowerCase().contains(name.toLowerCase())) {
                filteredListOfDishLog.add(dishLog);
            }
        }
        return filteredListOfDishLog;
    }

    // EFFECTS: returns List<DishLog> with DishLog(s) in listOfDishLog that have given restaurant (not case-sensitive)
    public List<DishLog> filterByRestaurant(String restaurant) {
        List<DishLog> filteredListOfDishLog = new ArrayList<>();
        for (DishLog dishLog : listOfDishLog) {
            if (dishLog.getRestaurant().equalsIgnoreCase(restaurant)) {
                filteredListOfDishLog.add(dishLog);
            }
        }
        return filteredListOfDishLog;
    }

    // EFFECTS: returns List<DishLog> with DishLog(s) in listOfDishLog that have price in range [lower, upper]
    public List<DishLog> filterByPrice(double lowerBound, double upperBound) {
        List<DishLog> filteredListOfDishLog = new ArrayList<>();
        for (DishLog dishLog : listOfDishLog) {
            if (lowerBound <= dishLog.getPrice() && dishLog.getPrice() <= upperBound) {
                filteredListOfDishLog.add(dishLog);
            }
        }
        return filteredListOfDishLog;
    }

    // EFFECTS: returns List<DishLog> with DishLog(s) in listOfDishLog that have given enjoymentLevel
    public List<DishLog> filterByEnjoymentLevel(int enjoymentLevel) {
        List<DishLog> filteredListOfDishLog = new ArrayList<>();
        for (DishLog dishLog : listOfDishLog) {
            if (dishLog.getEnjoymentLevel() == enjoymentLevel) {
                filteredListOfDishLog.add(dishLog);
            }
        }
        return filteredListOfDishLog;
    }

    // EFFECTS: returns List<DishLog> with DishLog(s) in listOfDishLog that are favourites
    public List<DishLog> filterByFavourite() {
        List<DishLog> filteredListOfDishLog = new ArrayList<>();
        for (DishLog dishLog : listOfDishLog) {
            if (dishLog.getFavourite()) {
                filteredListOfDishLog.add(dishLog);
            }
        }
        return filteredListOfDishLog;
    }

    // EFFECTS: returns DishLog in listOfDishLog at given index
    public DishLog getDishLog(int index) {
        return this.listOfDishLog.get(index);
    }

    // EFFECTS: returns true if listOfDishLog is empty
    public boolean isEmpty() {
        return this.listOfDishLog.isEmpty();
    }

    // EFFECTS: return listOfDishLog
    public List<DishLog> getListOfDishLog() {
        return listOfDishLog;
    }
}

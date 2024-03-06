package ui;

import model.DishLog;
import model.ListOfDishLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Dish log application
public class DishLogApp {
    private static final String JSON_STORE = "./data/listOfDishLog.json";
    private ListOfDishLog listOfDishLog;
    private Scanner input;
    private ArrayList<Integer> validEnjoymentLevels;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the dish log application
    public DishLogApp() {
        runDishLogApp();
    }

    // MODIFIES: this
    // EFFECTS: user input is recorded as a request
    private void runDishLogApp() {
        boolean appRunning = true;
        String request;

        init();
        offerLoad();

        while (appRunning) {
            viewMainMenu();
            request = input.next();
            request = request.toLowerCase();

            if (request.equals("q")) {
                appRunning = false;
            } else {
                selectMainMenu(request);
            }
        }

        offerSave();
        System.out.println("Application closed.");
    }

    // MODIFIES: this
    // EFFECTS: initializes listOfDishLog and input
    private void init() {
        listOfDishLog = new ListOfDishLog();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        validEnjoymentLevels = new ArrayList<>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        for (int i = 1; i <= 5; i++) {
            validEnjoymentLevels.add(i);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads listOfDishLog from file if user chooses to do so
    private void offerLoad() {
        boolean yesOrNoEntered = false;
        String yesOrNo;

        while (!yesOrNoEntered) {
            System.out.println("Would you like to load your Dish Logs from file? (yes/no)");

            yesOrNo = input.next();
            yesOrNo = yesOrNo.toLowerCase();

            if (yesOrNo.equals("yes") || yesOrNo.equals("y")) {
                loadDishLogs();
                yesOrNoEntered = true;
            } else if (yesOrNo.equals("no") || yesOrNo.equals("n")) {
                yesOrNoEntered = true;
            } else {
                System.out.println("\nInvalid response. Please try again.\n");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: saves listOfDishLog to file if user chooses to do so
    private void offerSave() {
        boolean yesOrNoEntered = false;
        String yesOrNo;

        while (!yesOrNoEntered) {
            System.out.println("\nWould you like to save your Dish Logs to file before closing the application?"
                    + " (yes/no)");

            yesOrNo = input.next();
            yesOrNo = yesOrNo.toLowerCase();

            if (yesOrNo.equals("yes") || yesOrNo.equals("y")) {
                saveDishLogs();
                yesOrNoEntered = true;
            } else if (yesOrNo.equals("no") || yesOrNo.equals("n")) {
                System.out.println();
                yesOrNoEntered = true;
            } else {
                System.out.println("\nInvalid response. Please try again.");
            }
        }
    }

    // EFFECTS: presents main menu of application features the user can request
    private void viewMainMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\tc --> create new Dish Log");
        System.out.println("\tv --> view past Dish Logs");
        System.out.println("\ts -> save Dish Logs to file");
        System.out.println("\tl -> load Dish Logs from file");
        System.out.println("\tq --> quit application");
    }

    // EFFECTS: presents menu of list features the user can request
    private void viewListMenu() {
        System.out.println("\nWould you like to do anything else?");
        System.out.println("\tf --> filter my Dish Logs");
        System.out.println("\te --> edit past Dish Logs");
        System.out.println("\tq --> go back");
    }

    // EFFECTS: presents menu of filter list features the user can request
    private void viewFilterListMenu() {
        System.out.println("\nWhat would you like to filter by?");
        System.out.println("\tn --> name");
        System.out.println("\tr --> restaurant");
        System.out.println("\tp --> price");
        System.out.println("\te --> enjoyment level");
        System.out.println("\tf --> favourites");
        System.out.println("\tq --> go back");
    }

    // EFFECTS: presents menu of edit list features the user can request
    private void viewEditMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\te --> edit Dish Log information");
        System.out.println("\td --> delete a Dish Log");
        System.out.println("\tq --> go back");
    }

    // EFFECTS: presents menu of edit Dish Log features the user can request
    private void viewEditDishLogMenu() {
        System.out.println("What information would you like to change?");
        System.out.println("\tn --> name");
        System.out.println("\tr --> restaurant");
        System.out.println("\tp --> price");
        System.out.println("\te --> enjoyment level");
        System.out.println("\tf --> favourites");
        System.out.println("\tq --> go back");
    }

    // REQUIRES: !(request == null)
    // MODIFIES: this
    // EFFECTS: processes user request from main menu
    private void selectMainMenu(String request) {
        if (request.equals("c")) {
            createDishLog();
        } else if (request.equals("v")) {
            viewDishLogs();
        } else if (request.equals("s")) {
            saveDishLogs();
        } else if (request.equals("l")) {
            loadDishLogs();
        } else {
            System.out.println("\nInvalid request. Please try again.");
        }
    }

    // EFFECTS: saves listOfDishLog to file
    private void saveDishLogs() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfDishLog);
            jsonWriter.close();
            System.out.println("\nSaved Dish Logs to " + JSON_STORE + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("\nUnable to write to file: " + JSON_STORE + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads listOfDishLog from file
    private void loadDishLogs() {
        try {
            listOfDishLog = jsonReader.read();
            System.out.println("\nLoaded Dish Logs from " + JSON_STORE + "\n");
        } catch (IOException e) {
            System.out.println("\nUnable to read from file: " + JSON_STORE + "\n");
        }
    }

    // REQUIRES: !(request == null)
    // MODIFIES: this
    // EFFECTS: processes user request from viewListMenu
    private void selectViewListMenu(String request) {
        if (request.equals("f")) {
            filterDishLogs();
        } else if (request.equals("e")) {
            editDishLogs();
        } else {
            System.out.println("\nInvalid request. Please try again.");
        }
    }

    // REQUIRES: !(request == null)
    // EFFECTS: processes user request from filterViewListMenu
    private void selectFilterViewListMenu(String request) {
        if (request.equals("n")) {
            filterDishLogsByName();
        } else if (request.equals("r")) {
            filterDishLogsByRestaurant();
        } else if (request.equals("p")) {
            filterDishLogsByPrice();
        } else if (request.equals("e")) {
            filterDishLogsByEnjoymentLevel();
        } else if (request.equals("f")) {
            filterDishLogsByFavourites();
        } else {
            System.out.println("\nInvalid request. Please try again.");
        }
    }

    // REQUIRES: !(request == null)
    // MODIFIES: this
    // EFFECTS: processes user request from edit menu
    private void selectEditMenu(String request) {
        if (request.equals("e")) {
            requestEditDishLog();
        } else if (request.equals("d")) {
            removeDishLog();
        } else {
            System.out.println("\nInvalid request. Please try again.");
        }
    }

    // REQUIRES: !(request == null) && listOfDishLog.getListOfDishLog().contains(requestedDishLog)
    // MODIFIES: this
    // EFFECTS: processes user request from edit Dish Log menu
    private void selectEditDishLogMenu(String request, DishLog requestedDishLog) {
        if (request.equals("n")) {
            setName(requestedDishLog);
            System.out.println("\nDish log edited successfully!");
        } else if (request.equals("r")) {
            setRestaurant(requestedDishLog);
            System.out.println("\nDish log edited successfully!");
        } else if (request.equals("p")) {
            setPrice(requestedDishLog);
            System.out.println("\nDish log edited successfully!");
        } else if (request.equals("e")) {
            setEnjoymentLevel(requestedDishLog);
            System.out.println("\nDish log edited successfully!");
        } else if (request.equals("f")) {
            setFavouriteStatus(requestedDishLog);
            System.out.println("\nDish log edited successfully!");
        } else {
            System.out.println("\nInvalid request. Please try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates new DishLog and adds it to this.listOfDishLog
    private void createDishLog() {
        DishLog dishLog = new DishLog("", "", 0, 1, false);
        System.out.println("\nPlease document the following in your new Dish Log:");
        setName(dishLog);
        setRestaurant(dishLog);
        setPrice(dishLog);
        setEnjoymentLevel(dishLog);
        setFavouriteStatus(dishLog);
        listOfDishLog.addDishLog(dishLog);
        System.out.println("\nDish Log complete!");
    }

    // EFFECTS: sets name of dish entered by user
    private void setName(DishLog dishLog) {
        boolean nameWasEntered = false;
        String name = new String();

        while (!nameWasEntered) {
            System.out.println("\nWhat is the name of this dish?");

            name = input.next();

            if (name.isEmpty()) {
                System.out.println("\nInvalid name. Please try again.");
            } else {
                nameWasEntered = true;
            }
        }
        dishLog.setName(name);
    }

    // EFFECTS: sets restaurant of dish entered by user
    private void setRestaurant(DishLog dishLog) {
        boolean restaurantWasEntered = false;
        String restaurant = new String();

        while (!restaurantWasEntered) {
            System.out.println("\nWhat is the name of the restaurant that prepared this dish?");

            restaurant = input.next();

            if (restaurant.isEmpty()) {
                System.out.println("\nInvalid name. Please try again.");
            } else {
                restaurantWasEntered = true;
            }
        }
        dishLog.setRestaurant(restaurant);
    }

    // EFFECTS: sets price of dish entered by user
    private void setPrice(DishLog dishLog) {
        boolean priceWasEntered = false;
        String priceInput;
        double price = 0;

        while (!priceWasEntered) {
            System.out.println("\nWhat is the price of this dish?");
            System.out.print("$");

            try {
                priceInput = input.next();
                price = Double.parseDouble(priceInput);

                if (price < 0) {
                    throw new Exception();
                } else {
                    priceWasEntered = true;
                }

            } catch (Exception e) {
                System.out.println("\nInvalid price. Please try again.");
            }
        }
        dishLog.setPrice(price);
    }

    // EFFECTS: sets enjoyment level of dish entered by user
    private void setEnjoymentLevel(DishLog dishLog) {
        int enjoymentLevel = 0;

        while (true) {
            System.out.println("\nHow much did you enjoy this dish? (on a scale from 1 to 5)");
            try {
                enjoymentLevel = inputEnjoymentLevel();
                break;
            } catch (Exception e) {
                System.out.println("\nInvalid enjoyment level. Please try again.");
            }
        }
        dishLog.setEnjoymentLevel(enjoymentLevel);
    }

    // EFFECTS: sets favourite status of dish entered by user
    private void setFavouriteStatus(DishLog dishLog) {
        boolean favouriteStatusEntered = false;
        boolean favouriteStatus = false;

        String yesOrNo;

        while (!favouriteStatusEntered) {
            System.out.println("\nWould you like to favourite this dish? (yes/no)");

            yesOrNo = input.next();
            yesOrNo = yesOrNo.toLowerCase();

            if (yesOrNo.equals("yes") || yesOrNo.equals("y")) {
                favouriteStatusEntered = true;
                favouriteStatus = true;
            } else if (yesOrNo.equals("no") || yesOrNo.equals("n")) {
                favouriteStatusEntered = true;
                favouriteStatus = false;
            } else {
                System.out.println("\nInvalid response. Please try again.");
            }
        }
        dishLog.setFavourite(favouriteStatus);
    }

    // MODIFIES: this
    // EFFECTS: presents this.listOfDishLog.getListOfDishLog() and viewListMenu
    private void viewDishLogs() {
        String request;
        if (listOfDishLog.isEmpty()) {
            System.out.println("\nNo Dish Logs made yet.");
        } else {
            viewList(listOfDishLog.getListOfDishLog());

            while (true) {
                viewListMenu();
                request = input.next();
                request = request.toLowerCase();

                if (request.equals("q")) {
                    break;
                } else {
                    selectViewListMenu(request);
                }
            }
        }
    }

    // EFFECTS: prints the names of each DishLog in given listOfDishLog
    private void viewList(List<DishLog> listOfDishLog) {
        if (listOfDishLog.isEmpty()) {
            System.out.println("\nNo Dish Logs match given filter.");
        } else {
            System.out.println("\nDish Logs:");
            for (DishLog dishLog : listOfDishLog) {
                int num = this.listOfDishLog.getListOfDishLog().indexOf(dishLog) + 1;
                String favourite;
                if (dishLog.getFavourite()) {
                    favourite = "favourite";
                } else {
                    favourite = "unfavourite";
                }
                System.out.println("\tDish Log " + num + ": " + dishLog.getName() + " (name), "
                        + dishLog.getRestaurant() + " (restaurant), $" + dishLog.getPrice() + ", enjoyment level "
                        + dishLog.getEnjoymentLevel() + ", " + favourite);
            }
        }
    }

    // EFFECTS: presents chosen filtered list of user's Dish Logs
    private void filterDishLogs() {
        String request;

        while (true) {
            viewFilterListMenu();
            request = input.next();
            request = request.toLowerCase();

            if (request.equals("q")) {
                break;
            } else {
                selectFilterViewListMenu(request);
            }
        }
    }

    // EFFECTS: presents list of Dish Logs filtered by given name
    private void filterDishLogsByName() {
        System.out.println("Please enter the name of the dishes you would like to filter by:");
        String name = input.next();

        viewList(listOfDishLog.filterByName(name));
    }

    // EFFECTS: presents list of Dish Logs filtered by given restaurant
    private void filterDishLogsByRestaurant() {
        System.out.println("Please enter the restaurant of the dishes you would like to filter by:");
        String restaurant = input.next();

        viewList(listOfDishLog.filterByRestaurant(restaurant));
    }

    // EFFECTS: presents list of Dish Logs filtered by given price range
    private void filterDishLogsByPrice() {
        double lowerBound = 0;
        double upperBound = 0;

        while (true) {
            try {
                lowerBound = inputLowerBound();
                upperBound = inputUpperBound();
                if (lowerBound > upperBound) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Upper bound must be greater or equal to lower bound. Please try again.");
            }
        }

        viewList(listOfDishLog.filterByPrice(lowerBound, upperBound));
    }

    // EFFECTS: presents list of Dish Logs filtered by given enjoyment level
    private void filterDishLogsByEnjoymentLevel() {
        int enjoymentLevel = 0;

        while (true) {
            System.out.println("\nPlease enter the enjoyment level you would like to filter by"
                    + " (on a scale from 1 to 5):");
            try {
                enjoymentLevel = inputEnjoymentLevel();
                break;
            } catch (Exception e) {
                System.out.println("\nInvalid enjoyment level. Please try again.");
            }
        }

        viewList(listOfDishLog.filterByEnjoymentLevel(enjoymentLevel));
    }

    // EFFECTS: presents list of favourite Dish Logs
    private void filterDishLogsByFavourites() {
        viewList(listOfDishLog.filterByFavourite());
    }

    // EFFECTS: returns lower bound input from user
    private double inputLowerBound() {
        String lowerBoundInput;
        double lowerBound;
        while (true) {
            try {
                System.out.println("\nPlease enter the lower bound of the range of "
                        + "prices you would like to filter by:");
                lowerBoundInput = input.next();
                lowerBound = Double.parseDouble(lowerBoundInput);
                break;
            } catch (Exception e) {
                System.out.println("\nInvalid price. Please try again.");
            }
        }
        return lowerBound;
    }

    // EFFECTS: returns upper bound input from user
    private double inputUpperBound() {
        String upperBoundInput;
        double upperBound;
        while (true) {
            try {
                System.out.println("\nPlease enter the upper bound of the range of "
                        + "prices you would like to filter by:");
                upperBoundInput = input.next();
                upperBound = Double.parseDouble(upperBoundInput);
                break;
            } catch (Exception e) {
                System.out.println("\nInvalid price. Please try again.");
            }
        }
        return upperBound;
    }

    // EFFECTS: returns enjoyment level of dish entered by user
    private int inputEnjoymentLevel() throws Exception {
        String enjoymentLevelInput;
        enjoymentLevelInput = input.next();
        int enjoymentLevel = Integer.parseInt(enjoymentLevelInput);
        if (!validEnjoymentLevels.contains(enjoymentLevel)) {
            throw new Exception();
        }
        return enjoymentLevel;
    }

    // MODIFIES: this
    // EFFECTS: allows user to choose to edit or remove Dish Logs from listOfDishLog.getListOfDishLog()
    private void editDishLogs() {
        String request;
        while (true) {
            viewEditMenu();
            request = input.next();
            request = request.toLowerCase();

            if (request.equals("q")) {
                break;
            } else {
                selectEditMenu(request);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: changes information of requested Dish Log
    private void requestEditDishLog() {
        String requestInput;
        DishLog requestedDishLog;
        int request;
        ArrayList<Integer> validDishLogNumbers = new ArrayList<>();
        for (DishLog dishLog : listOfDishLog.getListOfDishLog()) {
            validDishLogNumbers.add(listOfDishLog.getListOfDishLog().indexOf(dishLog) + 1);
        }
        while (true) {
            viewList(listOfDishLog.getListOfDishLog());
            System.out.println("\nPlease enter the number of the Dish Log you want to edit:");
            try {
                requestInput = input.next();
                request = Integer.parseInt(requestInput);
                if (!validDishLogNumbers.contains(request)) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("\nInvalid Dish Log number. Please try again.");
            }
        }
        requestedDishLog = listOfDishLog.getDishLog(request - 1);
        editDishLog(requestedDishLog);
    }

    // MODIFIES: this
    // EFFECTS: changes requested information of requestedDishLog
    private void editDishLog(DishLog requestedDishLog) {
        String request;
        while (true) {
            System.out.println("\nSelected Dish Log: " + requestedDishLog.getName());
            viewEditDishLogMenu();
            request = input.next();
            request = request.toLowerCase();

            if (request.equals("q")) {
                break;
            } else {
                selectEditDishLogMenu(request, requestedDishLog);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: removes user chosen Dish Log from listOfDishLog.getListOfDishLog()
    private void removeDishLog() {
        String requestInput;
        int request;
        ArrayList<Integer> validDishLogNumbers = new ArrayList<>();
        for (DishLog dishLog : listOfDishLog.getListOfDishLog()) {
            validDishLogNumbers.add(listOfDishLog.getListOfDishLog().indexOf(dishLog) + 1);
        }

        while (true) {
            viewList(listOfDishLog.getListOfDishLog());
            System.out.println("\nPlease enter the number of the Dish Log you want to remove:");
            try {
                requestInput = input.next();
                request = Integer.parseInt(requestInput);
                if (!validDishLogNumbers.contains(request)) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("\nInvalid Dish Log number. Please try again.");
            }
        }
        listOfDishLog.removeDishLog(request - 1);
        System.out.println("\nDish log removed successfully!");
    }
}

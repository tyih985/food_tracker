package model;

import org.json.JSONObject;
import persistence.Writable;

// DishLog represents a log of a dish a user has had.
// In each DishLog, the user records the name of the dish, the restaurant the dish came from, the price of the dish,
// the level of enjoyment they experienced from the dish (on a scale from 1-5), and if the dish is a favourite.
public class DishLog implements Writable {
    private String name;
    private String restaurant;
    private double price;
    private int enjoymentLevel;
    private boolean favourite;

    // REQUIRES: name.length() > 0, restaurant.length() > 0, price >= 0, enjoymentLevel in [1, 5]
    // EFFECTS: Makes new DishLog with given name, restaurant, price, enjoymentLevel, and favourite status
    public DishLog(String name, String restaurant, double price, int enjoymentLevel, boolean favourite) {
        this.name = name;
        this.restaurant = restaurant;
        this.price = price;
        this.enjoymentLevel = enjoymentLevel;
        this.favourite = favourite;
    }

    // EFFECTS: converts dish log to json object and returns it
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("restaurant", restaurant);
        json.put("price", price);
        json.put("enjoymentLevel", enjoymentLevel);
        json.put("favourite", favourite);
        return json;
    }

    // EFFECTS: returns summary of this Dish Log
    public String printReport() {
        String fav = "unfavourite";
        if (favourite) {
            fav = "favourite";
        }
        return name + " (name), " + restaurant + " (restaurant), $" + price + ", enjoyment level "
                + enjoymentLevel + ", " + fav;
    }

    public String getName() {
        return this.name;
    }

    public String getRestaurant() {
        return this.restaurant;
    }

    public double getPrice() {
        return this.price;
    }

    public int getEnjoymentLevel() {
        return this.enjoymentLevel;
    }

    public boolean getFavourite() {
        return this.favourite;
    }

    public void setName(String name) {
        String prev = this.name;
        this.name = name;
        EventLog.getInstance().logEvent(new Event(prev + ": set name to " + name));
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
        EventLog.getInstance().logEvent(new Event(name + ": set restaurant to " + restaurant));
    }

    public void setPrice(double price) {
        this.price = price;
        EventLog.getInstance().logEvent(new Event(name + ": set price to " + price));
    }

    public void setEnjoymentLevel(int enjoymentLevel) {
        this.enjoymentLevel = enjoymentLevel;
        EventLog.getInstance().logEvent(new Event(name + ": set enjoyment level to " + enjoymentLevel));
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
        EventLog.getInstance().logEvent(new Event(name + ": set favourite status to " + favourite));
    }
}

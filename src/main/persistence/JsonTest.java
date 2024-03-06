package persistence;

import model.DishLog;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkDishLog(String name, String restaurant, double price, int enjoymentLevel, boolean favourite,
                                DishLog dl) {
        assertEquals(name, dl.getName());
        assertEquals(restaurant, dl.getRestaurant());
        assertEquals(price, dl.getPrice());
        assertEquals(enjoymentLevel, dl.getEnjoymentLevel());
        assertEquals(favourite, dl.getFavourite());
    }
}

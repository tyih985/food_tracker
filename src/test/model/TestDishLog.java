package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestDishLog {
    DishLog testDishLog1;
    DishLog testDishLog2;
    DishLog testDishLog3;

    @BeforeEach
    public void setup() {
        testDishLog1 = new DishLog("Congee", "Congee Masters",
                100.50, 3, false);
        testDishLog2 = new DishLog("1-Month-Old Kitten", "Cat Soo San",
                20.13, 5, true);
        testDishLog3 = new DishLog("BEYOND Beyond Vegan Burger", "Beyond the Third Dimension",
                0.00, 1, false);
    }

    @Test
    public void testConstructorAndGetters() {
        testDishLog1 = new DishLog("Congee", "Congee Masters",
                100.50, 3, false);
        assertEquals("Congee", testDishLog1.getName());
        assertEquals("Congee Masters", testDishLog1.getRestaurant());
        assertEquals(100.50, testDishLog1.getPrice());
        assertEquals(3, testDishLog1.getEnjoymentLevel());
        assertFalse(testDishLog1.getFavourite());

        assertEquals("1-Month-Old Kitten", testDishLog2.getName());
        assertEquals("Cat Soo San", testDishLog2.getRestaurant());
        assertEquals(20.13, testDishLog2.getPrice());
        assertEquals(5, testDishLog2.getEnjoymentLevel());
        assertTrue(testDishLog2.getFavourite());

        assertEquals("BEYOND Beyond Vegan Burger", testDishLog3.getName());
        assertEquals("Beyond the Third Dimension", testDishLog3.getRestaurant());
        assertEquals(0.00, testDishLog3.getPrice());
        assertEquals(1, testDishLog3.getEnjoymentLevel());
        assertFalse(testDishLog3.getFavourite());
    }

    @Test
    public void testSetters() {
        testDishLog1.setName("Honeycomb Malt Chocolate");
        testDishLog1.setRestaurant("Rain or Shine Ice Cream");
        testDishLog1.setPrice(99.99);
        testDishLog1.setEnjoymentLevel(5);
        testDishLog1.setFavourite(true);

        assertEquals("Honeycomb Malt Chocolate", testDishLog1.getName());
        assertEquals("Rain or Shine Ice Cream", testDishLog1.getRestaurant());
        assertEquals(99.99, testDishLog1.getPrice());
        assertEquals(5, testDishLog1.getEnjoymentLevel());
        assertTrue(testDishLog1.getFavourite());

        List<String> l = new ArrayList<>();
        EventLog el = EventLog.getInstance();
        for (Event event : el) {
            l.add(event.getDescription());
        }

        assertTrue(l.contains("Congee: set name to " + testDishLog1.getName()));
        assertTrue(l.contains(testDishLog1.getName() + ": set restaurant to " + testDishLog1.getRestaurant()));
        assertTrue(l.contains(testDishLog1.getName() + ": set price to " + testDishLog1.getPrice()));
        assertTrue(l.contains(testDishLog1.getName() + ": set enjoyment level to " + testDishLog1.getEnjoymentLevel()));
        assertTrue(l.contains(testDishLog1.getName() + ": set favourite status to " + testDishLog1.getFavourite()));
    }

    @Test
    public void testPrintReport() {
        assertEquals("Congee (name), Congee Masters (restaurant), $100.5, enjoyment level 3, " +
                "unfavourite", testDishLog1.printReport());
        assertEquals("1-Month-Old Kitten (name), Cat Soo San (restaurant), $20.13, enjoyment level 5, " +
                "favourite", testDishLog2.printReport());
    }
}

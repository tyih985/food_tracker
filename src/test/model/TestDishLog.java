package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestDishLog {
    DishLog dishLog1;
    DishLog dishLog2;
    DishLog dishLog3;

    @BeforeEach
    public void setup() {
        dishLog1 = new DishLog("Congee", "Congee Masters",
                100.50, 3, false);
        dishLog2 = new DishLog("1-Month-Old Kitten", "Cat Soo San",
                20.13, 5, true);
        dishLog3 = new DishLog("BEYOND Beyond Vegan Burger", "Beyond the Third Dimension",
                0.00, 1, false);
    }

    @Test
    public void testConstructorAndGetters() {
        dishLog1 = new DishLog("Congee", "Congee Masters",
                100.50, 3, false);
        assertEquals("Congee", dishLog1.getName());
        assertEquals("Congee Masters", dishLog1.getRestaurant());
        assertEquals(100.50, dishLog1.getPrice());
        assertEquals(3, dishLog1.getEnjoymentLevel());
        assertFalse(dishLog1.getFavourite());

        assertEquals("1-Month-Old Kitten", dishLog2.getName());
        assertEquals("Cat Soo San", dishLog2.getRestaurant());
        assertEquals(20.13, dishLog2.getPrice());
        assertEquals(5, dishLog2.getEnjoymentLevel());
        assertTrue(dishLog2.getFavourite());

        assertEquals("BEYOND Beyond Vegan Burger", dishLog3.getName());
        assertEquals("Beyond the Third Dimension", dishLog3.getRestaurant());
        assertEquals(0.00, dishLog3.getPrice());
        assertEquals(1, dishLog3.getEnjoymentLevel());
        assertFalse(dishLog3.getFavourite());
    }

    @Test
    public void testSetters() {
        dishLog1.setName("Honeycomb Malt Chocolate");
        dishLog1.setRestaurant("Rain or Shine Ice Cream");
        dishLog1.setPrice(99.99);
        dishLog1.setEnjoymentLevel(5);
        dishLog1.setFavourite(true);

        assertEquals("Honeycomb Malt Chocolate", dishLog1.getName());
        assertEquals("Rain or Shine Ice Cream", dishLog1.getRestaurant());
        assertEquals(99.99, dishLog1.getPrice());
        assertEquals(5, dishLog1.getEnjoymentLevel());
        assertTrue(dishLog1.getFavourite());
    }

    @Test
    public void testPrintReport() {
        assertEquals("Congee (name), Congee Masters (restaurant), $100.5, enjoyment level 3, " +
                "unfavourite", dishLog1.printReport());
        assertEquals("1-Month-Old Kitten (name), Cat Soo San (restaurant), $20.13, enjoyment level 5, " +
                "favourite", dishLog2.printReport());
    }
}

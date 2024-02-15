package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestListOfDishLog {
    ListOfDishLog testListOfDishLog;
    DishLog testDishLog1;
    DishLog testDishLog2;
    DishLog testDishLog3;
    DishLog testDishLog4;
    DishLog testDishLog5;
    DishLog testDishLog6;

    @BeforeEach
    public void setup() {
        testListOfDishLog = new ListOfDishLog();
        testDishLog1 = new DishLog("Fried Chicken DX", "Jones' BBQ and Foot Massage",
                100.50, 5, true);
        testDishLog2 = new DishLog("Ramen", "Ichiraku Ramen",
                20.13, 1, false);
        testDishLog3 = new DishLog("Xue Hua Piao Piao", "Icy Bar",
                0.01, 3, true);
        testDishLog4 = new DishLog("Fried Chicken DX 2", "Jones' BBQ and Leg Massage",
                90.50, 2, true);
        testDishLog5 = new DishLog("Ramen 2", "Rum in Ramen",
                20.14, 4, false);
        testDishLog6 = new DishLog("Ramen 3", "Icy Bar",
                0.00, 5, true);

        testListOfDishLog.addDishLog(testDishLog1);
        testListOfDishLog.addDishLog(testDishLog2);
        testListOfDishLog.addDishLog(testDishLog3);
        testListOfDishLog.addDishLog(testDishLog4);
        testListOfDishLog.addDishLog(testDishLog5);
        testListOfDishLog.addDishLog(testDishLog6);
    }


    @Test
    public void testConstructorAndIsEmpty() {
        testListOfDishLog = new ListOfDishLog();
        assertTrue(testListOfDishLog.isEmpty());
        testListOfDishLog.addDishLog(testDishLog1);
        assertFalse(testListOfDishLog.isEmpty());
    }

    @Test
    public void testAddDishLogAndGetDishLog() {
        assertFalse(testListOfDishLog.isEmpty());
        assertEquals(testDishLog1, testListOfDishLog.getDishLog(0));
        assertEquals(testDishLog2, testListOfDishLog.getDishLog(1));
        assertEquals(testDishLog3, testListOfDishLog.getDishLog(2));
        assertEquals(testDishLog4, testListOfDishLog.getDishLog(3));
        assertEquals(testDishLog5, testListOfDishLog.getDishLog(4));
        assertEquals(testDishLog6, testListOfDishLog.getDishLog(5));
    }

    @Test
    public void testRemoveDishLog() {
        assertFalse(testListOfDishLog.isEmpty());

        testListOfDishLog.removeDishLog(0);
        assertEquals(testDishLog2, testListOfDishLog.getDishLog(0));
        assertEquals(testDishLog3, testListOfDishLog.getDishLog(1));
        assertEquals(testDishLog4, testListOfDishLog.getDishLog(2));
        assertEquals(testDishLog5, testListOfDishLog.getDishLog(3));
        assertEquals(testDishLog6, testListOfDishLog.getDishLog(4));

        testListOfDishLog.removeDishLog(0);
        testListOfDishLog.removeDishLog(0);
        testListOfDishLog.removeDishLog(0);
        testListOfDishLog.removeDishLog(0);
        testListOfDishLog.removeDishLog(0);
        assertTrue(testListOfDishLog.isEmpty());
    }

    @Test
    public void testFilterByName() {
        assertEquals(2, testListOfDishLog.filterByName("fried chicken dx").size());
        assertEquals(2, testListOfDishLog.filterByName("FRIED CHICKEN DX").size());
        assertEquals(testDishLog1, testListOfDishLog.filterByName("fried chicken DX").get(0));
        assertEquals(testDishLog4, testListOfDishLog.filterByName("FRIED CHICKEN DX").get(1));

        assertEquals(3, testListOfDishLog.filterByName("RAMEN").size());
        assertEquals(testDishLog2, testListOfDishLog.filterByName("ramen").get(0));
        assertEquals(testDishLog5, testListOfDishLog.filterByName("raMEN").get(1));
        assertEquals(testDishLog6, testListOfDishLog.filterByName("RAmen").get(2));

        assertEquals(1, testListOfDishLog.filterByName("xUe hUa pIaO pIaO").size());
        assertEquals(testDishLog3, testListOfDishLog.filterByName("XUE HUA PIAO PIAO").get(0));

        assertTrue(testListOfDishLog.filterByName("Dubious Food").isEmpty());
    }

    @Test
    public void testFilterByRestaurant() {
        assertEquals(1, testListOfDishLog.filterByRestaurant("JONES' BBQ AND FOOT MASSAGE").size());
        assertEquals(1, testListOfDishLog.filterByRestaurant("Jones' BBQ and foot Massage").size());
        assertEquals(testDishLog1, testListOfDishLog.filterByRestaurant("JONES' BBQ AND FOOT MASSAGE").get(0));

        assertEquals(1, testListOfDishLog.filterByRestaurant("Jones' BBQ and LEG Massage").size());
        assertEquals(testDishLog4, testListOfDishLog.filterByRestaurant("Jones' BBQ and leg Massage").get(0));

        assertEquals(1, testListOfDishLog.filterByRestaurant("Ichiraku RAMEN").size());
        assertEquals(testDishLog2, testListOfDishLog.filterByRestaurant("Ichiraku Ramen").get(0));

        assertEquals(2, testListOfDishLog.filterByRestaurant("ICY BAR").size());
        assertEquals(testDishLog3, testListOfDishLog.filterByRestaurant("icy bar").get(0));
        assertEquals(testDishLog6, testListOfDishLog.filterByRestaurant("Icy Bar").get(1));

        assertTrue(testListOfDishLog.filterByRestaurant("Jabba's Hut").isEmpty());
    }

    @Test
    public void testFilterByPrice() {
        assertEquals(0, testListOfDishLog.filterByPrice(5000.00, 50000000.99).size());

        assertEquals(1, testListOfDishLog.filterByPrice(90.50, 90.50).size());
        assertEquals(testDishLog4, testListOfDishLog.filterByPrice(90.50, 90.50).get(0));

        assertEquals(2, testListOfDishLog.filterByPrice(0.01, 20.13).size());
        assertEquals(testDishLog2, testListOfDishLog.filterByPrice(0.01, 20.13).get(0));
        assertEquals(testDishLog3, testListOfDishLog.filterByPrice(0.01, 20.13).get(1));

        assertEquals(4, testListOfDishLog.filterByPrice(0.00, 20.14).size());
        assertEquals(testDishLog2, testListOfDishLog.filterByPrice(0.00, 20.14).get(0));
        assertEquals(testDishLog3, testListOfDishLog.filterByPrice(0.00, 20.14).get(1));
        assertEquals(testDishLog5, testListOfDishLog.filterByPrice(0.00, 20.14).get(2));
        assertEquals(testDishLog6, testListOfDishLog.filterByPrice(0.00, 20.14).get(3));

        assertEquals(6, testListOfDishLog.filterByPrice(0.00, 100.50).size());
        assertEquals(testDishLog1, testListOfDishLog.filterByPrice(0.00, 100.50).get(0));
        assertEquals(testDishLog2, testListOfDishLog.filterByPrice(0.00, 100.50).get(1));
        assertEquals(testDishLog3, testListOfDishLog.filterByPrice(0.00, 100.50).get(2));
        assertEquals(testDishLog4, testListOfDishLog.filterByPrice(0.00, 100.50).get(3));
        assertEquals(testDishLog5, testListOfDishLog.filterByPrice(0.00, 100.50).get(4));
        assertEquals(testDishLog6, testListOfDishLog.filterByPrice(0.00, 100.50).get(5));


        assertEquals(6, testListOfDishLog.filterByPrice(0.00, 50000000.99).size());
        assertEquals(testDishLog1, testListOfDishLog.filterByPrice(0.00, 50000000.99).get(0));
        assertEquals(testDishLog2, testListOfDishLog.filterByPrice(0.00, 50000000.99).get(1));
        assertEquals(testDishLog3, testListOfDishLog.filterByPrice(0.00, 50000000.99).get(2));
        assertEquals(testDishLog4, testListOfDishLog.filterByPrice(0.00, 50000000.99).get(3));
        assertEquals(testDishLog5, testListOfDishLog.filterByPrice(0.00, 50000000.99).get(4));
        assertEquals(testDishLog6, testListOfDishLog.filterByPrice(0.00, 50000000.99).get(5));
    }

    @Test
    public void testFilterByEnjoymentLevel() {
        assertEquals(1, testListOfDishLog.filterByEnjoymentLevel(1).size());
        assertEquals(testDishLog2, testListOfDishLog.filterByEnjoymentLevel(1).get(0));

        assertEquals(1, testListOfDishLog.filterByEnjoymentLevel(2).size());
        assertEquals(testDishLog4, testListOfDishLog.filterByEnjoymentLevel(2).get(0));

        assertEquals(1, testListOfDishLog.filterByEnjoymentLevel(3).size());
        assertEquals(testDishLog3, testListOfDishLog.filterByEnjoymentLevel(3).get(0));

        assertEquals(1, testListOfDishLog.filterByEnjoymentLevel(4).size());
        assertEquals(testDishLog5, testListOfDishLog.filterByEnjoymentLevel(4).get(0));

        assertEquals(2, testListOfDishLog.filterByEnjoymentLevel(5).size());
        assertEquals(testDishLog1, testListOfDishLog.filterByEnjoymentLevel(5).get(0));
        assertEquals(testDishLog6, testListOfDishLog.filterByEnjoymentLevel(5).get(1));
    }

    @Test
    public void testFilterByFavourite() {
        assertEquals(4, testListOfDishLog.filterByFavourite().size());
        assertEquals(testDishLog1, testListOfDishLog.filterByFavourite().get(0));
        assertEquals(testDishLog3, testListOfDishLog.filterByFavourite().get(1));
        assertEquals(testDishLog4, testListOfDishLog.filterByFavourite().get(2));
        assertEquals(testDishLog6, testListOfDishLog.filterByFavourite().get(3));
    }
}
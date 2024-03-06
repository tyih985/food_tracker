package persistence;

import model.DishLog;
import model.ListOfDishLog;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfDishLog ld = new ListOfDishLog();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ListOfDishLog ld = new ListOfDishLog();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyListOfDishLog.json");
            writer.open();
            writer.write(ld);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyListOfDishLog.json");
            ld = reader.read();
            assertEquals(0, ld.getListOfDishLog().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralListOfDishLog() {
        try {
            ListOfDishLog ld = new ListOfDishLog();
            ld.addDishLog(new DishLog("burger", "burger place",
                    500.50, 2, true));
            ld.addDishLog(new DishLog("dog", "dog house",
                    25.99, 5, false));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralListOfDishLog.json");
            writer.open();
            writer.write(ld);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralListOfDishLog.json");
            ld = reader.read();
            List<DishLog> thingies = ld.getListOfDishLog();
            assertEquals(2, thingies.size());
            checkDishLog("burger", "burger place",
                    500.50, 2, true, ld.getListOfDishLog().get(0));
            checkDishLog("dog", "dog house",
                    25.99, 5, false, ld.getListOfDishLog().get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
// based on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfDishLog ld = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyListOfDishLog() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyListOfDishLog.json");
        try {
            ListOfDishLog ld = reader.read();
            assertEquals(0, ld.getListOfDishLog().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralListOfDishLog() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralListOfDishLog.json");
        try {
            ListOfDishLog ld = reader.read();
            List<DishLog> dishLogs = ld.getListOfDishLog();
            assertEquals(2, ld.getListOfDishLog().size());
            checkDishLog("burger", "burger place",
                    500.50, 2, true, dishLogs.get(0));
            checkDishLog("dog", "dog house",
                    25.99, 5, false, ld.getListOfDishLog().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
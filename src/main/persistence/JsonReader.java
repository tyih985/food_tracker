// based on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.DishLog;
import model.ListOfDishLog;
import org.json.*;

// Represents a reader that reads ListOfDishLog from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ListOfDishLog from file and returns it
    // throws IOException if an error occurs reading data from file
    public ListOfDishLog read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfDishLog(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ListOfDishLog from JSON object and returns it
    private ListOfDishLog parseListOfDishLog(JSONObject jsonObject) {
        ListOfDishLog ld = new ListOfDishLog();
        addDishLogs(ld, jsonObject);
        return ld;
    }

    // MODIFIES: ld
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addDishLogs(ListOfDishLog ld, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("dish logs");
        for (Object json : jsonArray) {
            JSONObject nextDishLog = (JSONObject) json;
            saveDishLog(ld, nextDishLog);
        }
    }

    // MODIFIES: ld
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void saveDishLog(ListOfDishLog ld, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String restaurant = jsonObject.getString("restaurant");
        double price = jsonObject.getDouble("price");
        int enjoymentLevel = jsonObject.getInt("enjoymentLevel");
        boolean favourite = jsonObject.getBoolean("favourite");
        DishLog dl = new DishLog(name, restaurant, price, enjoymentLevel, favourite);
        ld.addDishLog(dl);
    }
}
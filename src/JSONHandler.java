


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


class JSONHandler {

    public JSONObject jsonFile(String forecastDataString) {
        try {
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(forecastDataString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

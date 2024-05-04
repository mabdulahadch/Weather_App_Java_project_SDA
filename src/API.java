import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class API {
    private String locationName;
    JSONObject weatherData = new JSONObject();
    JSONObject currWeatherData = new JSONObject();
    JSONObject locationData = new JSONObject();
    JSONObject pollutantData = new JSONObject();

    API(String locationName) {
        this.locationName = locationName;
    }

    public JSONObject getWeatherData() {
        if (locationData.toString().equals("{}")) {
            locationData = getLocationData(locationName);
        }
        double latitude = (double) locationData.get("latitude");
        double longitude = (double) locationData.get("longitude");

        String apiKey = "7d888618e8c1a5f4afaead050a345b88";

        String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?lat=" + latitude + "&lon=" + longitude
                + "&appid=" + apiKey;

        try {
            HttpURLConnection conn = fetchAPIResponse(apiUrl);

            if (conn.getResponseCode() != 200) {
                System.out.println("Error: Could not connect to API");
                return null;
            }

            StringBuilder resultJson = new StringBuilder();
            Scanner scanner = new Scanner(conn.getInputStream());
            while (scanner.hasNext()) {
                resultJson.append(scanner.nextLine());
            }

            scanner.close();

            conn.disconnect();
            JSONParser parser = new JSONParser();
            JSONObject resultJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

            return resultJsonObj;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public JSONObject getCurrWeatherData() {

        JSONObject locationData = getLocationData(locationName);
        double latitude = (double) locationData.get("latitude");
        double longitude = (double) locationData.get("longitude");
        String apiKey = "7d888618e8c1a5f4afaead050a345b88";

        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude
                + "&appid=" + apiKey;

        try {
            HttpURLConnection conn = fetchAPIResponse(apiUrl);

            if (conn.getResponseCode() != 200) {
                System.out.println("Error: Could not connect to API");
                return null;
            }

            StringBuilder resultJson = new StringBuilder();
            Scanner scanner = new Scanner(conn.getInputStream());
            while (scanner.hasNext()) {
                resultJson.append(scanner.nextLine());
            }

            scanner.close();

            conn.disconnect();
            JSONParser parser = new JSONParser();
            JSONObject resultJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

            return resultJsonObj;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public JSONObject getPollutantData() {

        if (locationData.toString().equals("{}")) {
            locationData = getLocationData(locationName);
        }
        double latitude = (double) locationData.get("latitude");
        double longitude = (double) locationData.get("longitude");

        String apiKey = "7d888618e8c1a5f4afaead050a345b88";

        String apiUrl = "http://api.openweathermap.org/data/2.5/air_pollution?lat=" + latitude + "&lon=" + longitude
                + "&appid=" + apiKey;

        try {
            HttpURLConnection conn = fetchAPIResponse(apiUrl);

            if (conn.getResponseCode() != 200) {
                System.out.println("Error: Could not connect to API");
                return null;
            }

            StringBuilder resultJson = new StringBuilder();
            Scanner scanner = new Scanner(conn.getInputStream());
            while (scanner.hasNext()) {
                resultJson.append(scanner.nextLine());
            }

            scanner.close();

            conn.disconnect();
            JSONParser parser = new JSONParser();
            JSONObject resultJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

            return resultJsonObj;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public JSONObject getFiveDayForecast() {
        if (weatherData.toString().equals("{}")) {
            weatherData = getWeatherData();
        }

        JSONObject forecastData = new JSONObject();
        JSONArray forecastList = new JSONArray();

        JSONArray list = (JSONArray) weatherData.get("list");

        for (int i = 1; i < list.size(); i += 8) {
            JSONObject fiveDayData = (JSONObject) list.get(i);

            String date = (String) fiveDayData.get("dt_txt").toString().substring(0, 10);

            JSONObject main = (JSONObject) fiveDayData.get("main");

            double tempMin = (double) main.get("temp_min");
            tempMin = (tempMin - 273);

            double tempMax = (double) main.get("temp_max");
            tempMax = (tempMax - 273);

            JSONArray weatherArray = (JSONArray) fiveDayData.get("weather");
            JSONObject weatherObject = (JSONObject) weatherArray.get(0);
            String weatherDescription = (String) weatherObject.get("description");

            JSONObject dayForecast = new JSONObject();
            dayForecast.put("date", date);
            dayForecast.put("temp_min", tempMin);
            dayForecast.put("temp_max", tempMax);
            dayForecast.put("weather_description", weatherDescription);

            forecastList.add(dayForecast);
        }

        forecastData.put("forecast", forecastList);

        return forecastData;
    }

    @SuppressWarnings("unchecked")
    public JSONObject getThreeHourlyForecast() {
        if (weatherData.toString().equals("{}")) {
            weatherData = getWeatherData();
        }
        
        JSONObject forecastData = new JSONObject();
        JSONArray forecastList = new JSONArray();
        JSONArray list = (JSONArray) weatherData.get("list");
    
        for (int i = 0; i < 8; i++) {
            JSONObject threeHourData = (JSONObject) list.get(i);
            String dateTimeString = (String) threeHourData.get("dt_txt");
            
            JSONObject main = (JSONObject) threeHourData.get("main");
            
            // Convert temperature from Kelvin to Celsius
            double temperature = 0.0;
            Object tempObject = main.get("temp");
            if (tempObject instanceof Number) {
                temperature = ((Number) tempObject).doubleValue() - 273.15;
            }
            
            // Get humidity
            double humidity = 0.0;
            Object humidityObject = main.get("humidity");
            if (humidityObject instanceof Number) {
                humidity = ((Number) humidityObject).doubleValue();
            }
            
            JSONObject hourlyForecast = new JSONObject();
            hourlyForecast.put("date_time", dateTimeString);
            hourlyForecast.put("temperature", temperature);
            hourlyForecast.put("humidity", humidity);
    
            forecastList.add(hourlyForecast);
        }
    
        forecastData.put("forecast", forecastList);
    
        return forecastData;
    }
    
    public long getCurrentHumidity() {
        if (currWeatherData.toString().equals("{}")) {
            currWeatherData = getCurrWeatherData();
        }
        JSONObject mainObject = (JSONObject) currWeatherData.get("main");
        return (long) mainObject.get("humidity");
    }

    public double getCurrentTemperature() {

        if (currWeatherData.toString().equals("{}")) {
            currWeatherData = getCurrWeatherData();
        }

        JSONObject mainObject = (JSONObject) currWeatherData.get("main");
        double temperature = (double) mainObject.get("temp");
        return (int) (temperature - 273);
    }

    public double getMinimumTemperature() {

        if (currWeatherData.toString().equals("{}")) {
            currWeatherData = getCurrWeatherData();
        }

        JSONObject mainObject = (JSONObject) currWeatherData.get("main");
        double temperature = (double) mainObject.get("temp_min");
        return (int) (temperature - 273);
    }

    public double getMaximumTemperature() {

        if (currWeatherData.toString().equals("{}")) {
            currWeatherData = getCurrWeatherData();
        }

        JSONObject mainObject = (JSONObject) currWeatherData.get("main");
        double temperature = (double) mainObject.get("temp_max");
        return (int) (temperature - 273);
    }

    public LocalDateTime getSunriseTime() {
        if (locationData.toString().equals("{}")) {
            locationData = getLocationData(locationName);
        }
        if (currWeatherData.toString().equals("{}")) {
            currWeatherData = getCurrWeatherData();
        }

        JSONObject sysObject = (JSONObject) currWeatherData.get("sys");
        long sunriseUnixTimestamp = (long) sysObject.get("sunrise");

        String timeZoneId = (String) locationData.get("timezone");

        Instant instant = Instant.ofEpochSecond(sunriseUnixTimestamp);
        LocalDateTime sunriseDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        ZoneId zoneId = ZoneId.of(timeZoneId);
        LocalDateTime localDateTime = sunriseDateTime.atZone(ZoneOffset.UTC).withZoneSameInstant(zoneId)
                .toLocalDateTime();
        return localDateTime;
    }

    public LocalDateTime getSunsetTime() {
        if (locationData.toString().equals("{}")) {
            locationData = getLocationData(locationName);
        }
        if (currWeatherData.toString().equals("{}")) {
            currWeatherData = getCurrWeatherData();
        }

        JSONObject sysObject = (JSONObject) currWeatherData.get("sys");
        long sunriseUnixTimestamp = (long) sysObject.get("sunset");

        // Get timezone information from the API response
        String timeZoneId = (String) locationData.get("timezone");

        // Convert Unix timestamp to LocalDateTime in the specified timezone
        Instant instant = Instant.ofEpochSecond(sunriseUnixTimestamp);
        LocalDateTime sunriseDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        ZoneId zoneId = ZoneId.of(timeZoneId);
        LocalDateTime localDateTime = sunriseDateTime.atZone(ZoneOffset.UTC).withZoneSameInstant(zoneId)
                .toLocalDateTime();
        return localDateTime;
    }

    public String getWeatherDescription() {

        JSONArray weatherArray = (JSONArray) currWeatherData.get("weather");
        JSONObject weatherObject = (JSONObject) weatherArray.get(0);
        return (String) weatherObject.get("description");
    }

    public double getFeelsLikeTemperature() {

        JSONObject mainObject =(JSONObject) currWeatherData.get("main");
        double temperature=(double) mainObject.get("feels_like");
        return (int)(temperature-273);
    }
    

    public JSONObject getLocationData(String locationName) {
        locationName = locationName.replaceAll(" ", "+");
        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name=" + locationName
                + "&count=10&language=en&format=json";
        try {
            HttpURLConnection conn = fetchAPIResponse(urlString);
            if (conn.getResponseCode() != 200) {
                System.err.println("Error : Could Not Connect To API ");
                return null;
            } else {
                StringBuilder resultJson = new StringBuilder();
                Scanner scanner = new Scanner(conn.getInputStream());

                while (scanner.hasNext()) {
                    resultJson.append(scanner.nextLine());
                }

                scanner.close();

                conn.disconnect();

                JSONParser parser = new JSONParser();
                JSONObject resultsJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

                JSONArray locationData = (JSONArray) resultsJsonObj.get("results");
                return (JSONObject) locationData.get(0);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public JSONObject getComponentGasses() {

        if (pollutantData.toString().equals("{}")) {
            pollutantData = getPollutantData();
        }

        JSONArray list = (JSONArray) pollutantData.get("list");
        JSONObject data = (JSONObject) list.get(0);
        JSONObject components = (JSONObject) data.get("components");

        return components;
    }

    public long getAQI() {

        if (pollutantData.toString().equals("{}")) {
            pollutantData = getPollutantData();
        }

        JSONArray listArray = (JSONArray) pollutantData.get("list");
        JSONObject firstItem = (JSONObject) listArray.get(0);
        JSONObject mainObject = (JSONObject) firstItem.get("main");
        long aqi = (long) mainObject.get("aqi");

        return aqi;
    }

    private static HttpURLConnection fetchAPIResponse(String urlString) {
        try {
            @SuppressWarnings("deprecation")
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            conn.connect();
            return conn;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // public static void main(String[] args) {
    //     try (Scanner scanner = new Scanner(System.in)) {
    //         System.out.println("Enter Your City Name: ");
    //         String locationName = scanner.nextLine();
    //         API myApp = new API(locationName);
    //         System.out.println(myApp.getCurrentHumidity());
    //     }catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

}
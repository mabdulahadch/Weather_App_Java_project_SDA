


//import org.json.JSONArray;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class weatherData {
    String[] threeHourlyTime = new String[8];
    int[] threeHourlyTemp = new int[8];
    // for FiveDay
    int[] FiveDayMaxTemp = new int[5];
    int[] FiveDayMinTemp = new int[5];
    String[] FiveDayDate = new String[5];
    String[] FiveDayDescription = new String[5];
    String Location = "";
    // for gases Components
    // No NEED ---- String[] gasesNames=new String[8];
    Double[] gasesConcentration = new Double[8];

    // for AQI
    int AirQualityIndex;

    // Now Variables for API call wali cheezein
    int currentTemperature, feelsLike;
    int currentMin, currentMax, humidity;
    String weatherDiscription;

    String sunSetTime = "6:17", sunRiseTime;

    public weatherData() {
        // this.city = "Karachi";
        // this.getThreeHourly();
    }

    public void setLocation(String loc) {

        this.Location = loc;
    }

    public void setCurrentTemp(String city, int temp) {
        this.currentTemperature = temp;
    }

    public int getCurrentTemp() {
        return this.currentTemperature;
    }

    // Humidity k liye
    public void setCurrentHumidity(String city, long temp) {
        this.humidity = (int) temp;
    }

    public int getCurrentHumidity() {
        return this.humidity;
    }

    // MAX temp k liye
    public void setCurrentMaxTemp(String city, int temp) {
        this.currentMax = temp;
    }

    public int getCurrentMaxTemp() {
        return this.currentMax;
    }

    // Min temp k liye
    public void setCurrentMinTemp(String city, int temp) {
        this.currentMin = temp;
    }

    public int getCurrentMinTemp() {
        return this.currentMin;
    }

    // feels Like k liye
    public void setFeelsLikeTemp(String city, int temp) {
        this.feelsLike = temp;
    }

    public int getFeelsLikeTemp() {
        return this.feelsLike;
    }

    // for weather discription -- update----
    public void setWeatherDiscription(String city, String temp) {
        weatherDiscription = temp;
    }

    public String getWeatherDiscription() {
        return this.weatherDiscription;
    }

    public void setSunriseTime(String city, LocalDateTime srTime) {
        // Define a formatter for hours and minutes in AM/PM format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        // Format the LocalDateTime object to a String with hours, minutes, and AM/PM
        this.sunRiseTime = srTime.format(formatter);
    }

    public String getSunriseTime() {
        return this.sunRiseTime;
    }

    public void setSunSetTime(String city, LocalDateTime ssTime) {
        // Define a formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        // Format the LocalDateTime object to a String
        this.sunSetTime = ssTime.format(formatter);
    }

    public String getSunSetTime() {
        return this.sunSetTime;
    }

    public void setThreeHourly(String city, JSONArray jsonArray) {
        this.Location = city;

        // Iterate through the JSONArray
        for (int i = 0; i < jsonArray.size(); i++) {
            // Get the current JSONObject
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

            // Extract the date_time and temperature values
            String dateTime = (String) jsonObject.get("date_time");
            double temperature = (Double) jsonObject.get("temperature");

            // Split the date_time string to extract the time part
            String time = dateTime.split(" ")[1];            

            // Extract the hour from the time string
            int hour = Integer.parseInt(time.split(":")[0]);

            // Convert 24-hour format to 12-hour format and determine AM/PM
            String amPm;
            if (hour == 0) {
                hour = 12; // Midnight case
                amPm = "AM";
            } else if (hour == 12) {
                amPm = "PM"; // Noon case
            } else if (hour > 12) {
                hour -= 12;
                amPm = "PM";
            } else {
                amPm = "AM";
            }

            // Construct the new time string
            String newTime = hour + " " + amPm;
            threeHourlyTime[i] = newTime;

            // Store the temperature as an integer
            threeHourlyTemp[i] = (int) temperature;
        }
    }

    public int[] getThreeHourlyTemp() {
        return this.threeHourlyTemp;
    }

    public String[] getThreeHourlyTimes() {
        return this.threeHourlyTime;
    }

    public String getLocaion() {
        return this.Location;
    }

    public void setFiveDays(String city, JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.isEmpty()) {
            // Handle the case where the JSON array is null or empty
            // You may log an error or throw an exception depending on your application logic
            return;
        }

        // Iterate through the JSONArray
        for (int i = 0; i < jsonArray.size(); i++) {
            // Get the current JSONObject
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

            // Check if the JSONObject is not null
            if (jsonObject != null) {
                // Extract the date, max temperature, min temperature, and description values
                String dateStr = (String) jsonObject.get("date");
                LocalDate date = LocalDate.parse(dateStr); // Parse date string to LocalDate
                double maxTemp = (Double) jsonObject.get("temp_max");
                double minTemp = (Double) jsonObject.get("temp_min");
                String desc = (String) jsonObject.get("weather_description");

                // Format the date to "dd MMM, EEE"
                String formattedDate = date.format(DateTimeFormatter.ofPattern("dd MMM, EEE"));

                // Store the extracted values
                FiveDayMaxTemp[i] = (int) maxTemp;
                FiveDayMinTemp[i] = (int) minTemp;
                FiveDayDescription[i] = desc;
                FiveDayDate[i] = formattedDate;
            } else {
                // Handle the case where the JSONObject is null
                // You may log an error or throw an exception depending on your application logic
            }
        }
    }

    public int[] getFiveDayMaxTemp() {
        return this.FiveDayMaxTemp;
    }

    public int[] getFiveDayMinTemp() {
        return this.FiveDayMinTemp;
    }

    public String[] getFiveDayDesc() {
        return this.FiveDayDescription;
    }

    public String[] getFiveDayDates() {
        return this.FiveDayDate;
    }

    public void setGasesConcentration(String city, JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.isEmpty()) {
            // Handle the case where the JSON array is null or empty
            // You may log an error or throw an exception depending on your application logic
            return;
        }
    
        // Get the first (and only) JSONObject from the JSONArray
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
    
        // Check if the JSONObject is not null
        if (jsonObject != null) {
            // Extract and store the gases concentrations from the JSONObject
            gasesConcentration[0] = jsonObject.containsKey("no2") ? ((Number) jsonObject.get("no2")).doubleValue() : null;
            gasesConcentration[1] = jsonObject.containsKey("no") ? ((Number) jsonObject.get("no")).doubleValue() : null;
            gasesConcentration[2] = jsonObject.containsKey("o3") ? ((Number) jsonObject.get("o3")).doubleValue() : null;
            gasesConcentration[3] = jsonObject.containsKey("so2") ? ((Number) jsonObject.get("so2")).doubleValue() : null;
            gasesConcentration[4] = jsonObject.containsKey("pm2_5") ? ((Number) jsonObject.get("pm2_5")).doubleValue() : null;
            gasesConcentration[5] = jsonObject.containsKey("pm10") ? ((Number) jsonObject.get("pm10")).doubleValue() : null;
            gasesConcentration[6] = jsonObject.containsKey("nh3") ? ((Number) jsonObject.get("nh3")).doubleValue() : null;
            gasesConcentration[7] = jsonObject.containsKey("co") ? ((Number) jsonObject.get("co")).doubleValue() : null;
        } else {
            // Handle the case where the JSONObject is null
            // You may log an error or throw an exception depending on your application logic
        }
    }
    
    

    public Double[] getGasesData() {
        return this.gasesConcentration;
    }

    public void setAQI(String city, int AQI) {
        this.AirQualityIndex = AQI;
    }

    public int getAQI() {
        return this.AirQualityIndex;
    }

    // public static void main(String[] args) {
    // weatherData data = new weatherData("Lahore");
    // // data.getThreeHourly(""); // Use a real city name or keep it as is for
    // testing

    // // Print the results
    // System.out.println("Three Hourly Time:");
    // for (String time : data.threeHourlyTime) {
    // System.out.println(time);
    // }
    // System.out.println("\nThree Hourly Temperature:");
    // for (int temp : data.threeHourlyTemp) {
    // System.out.println(temp);
    //     }
    //  }
}
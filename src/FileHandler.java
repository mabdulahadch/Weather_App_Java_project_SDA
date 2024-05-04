import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FileHandler implements AbstractDB {
    
    @Override
    public void storeFiveDayData(String city, JSONObject fiveDayData) {
        createDateFile();
        if (isCityExists("FiveDayWeatherData.txt", city)) {
            System.out.println("Data for " + city + " already Exists!");
            return;
        } else {
            String data = fiveDayData.toString();
            String finalData = city + data + "!*";
            writeToFile("FiveDayWeatherData.txt", finalData);
        }
    }

    @Override
    public JSONArray getFiveDaysData(String city) {
        JSONArray n = new JSONArray();
        if (!compareDatesFromFile("date.txt")) {
            createDateFile();
            clearFile("FiveDayWeatherData.txt");
            clearFile("threeHourlyWeatherData.txt");
            clearFile("GasesCompWeatherData.txt");
            clearFile("aqi_data.txt");
            return null;
        }
        if (!isCityExists("FiveDayWeatherData.txt", city)) {
            System.out.println("Data of " + city + " doest not exist.");
            return n;
        }
        String data = readFileAsString("FiveDayWeatherData.txt");

        StringBuilder stringBuilder = new StringBuilder();
        int cityLen = city.length();
        int index = data.indexOf(city) + cityLen;
        System.out.println();

        if (index != -1) {
            while (index < data.length() && data.charAt(index) != '!' && data.charAt(index) != '*') {
                stringBuilder.append(data.charAt(index));
                index++;
            }

            String cityData = stringBuilder.toString();
            JSONHandler j = new JSONHandler();
            JSONObject jsonObject = j.jsonFile(cityData);

            n = (JSONArray) jsonObject.get("forecast");
            return n;
        } else {
            System.out.println("City not found in data.");
            return n;
        }
    }

    @Override
    public void storeThreeHourly(String city, JSONObject threehrly) {

        if (isCityExists("threeHourlyWeatherData.txt", city)) {
            System.out.println("Data for " + city + " already Exists!");
            return;
        } else {
            String data = threehrly.toString();
            String finalData = city + data + "!*";
            writeToFile("threeHourlyWeatherData.txt", finalData);
        }
    }

    @Override
    public JSONArray getThreeHourlyData(String city) {
        JSONArray n = new JSONArray();
        if (!isCityExists("threeHourlyWeatherData.txt", city)) {
            System.out.println("Data of " + city + " doest not exist.");
            return n;
        }
        String data = readFileAsString("threeHourlyWeatherData.txt");

        StringBuilder stringBuilder = new StringBuilder();
        int cityLen = city.length();
        int index = data.indexOf(city) + cityLen;
        System.out.println(index);
        if (index != -1) {
            while (index < data.length() && data.charAt(index) != '!' && data.charAt(index) != '*') {
                stringBuilder.append(data.charAt(index));
                index++;
            }

            String cityData = stringBuilder.toString();
            JSONHandler j = new JSONHandler();
            JSONObject jsonObject = j.jsonFile(cityData);

            n = (JSONArray) jsonObject.get("forecast");
            return n;
        } else {
            System.out.println("City not found in data.");
            return n;
        }
    }

    @Override
    public void storeGasesComponent(String city, JSONObject gasesComp) {
        if (isCityExists("GasesCompWeatherData.txt", city)) {
            System.out.println("Data for " + city + " already Exists!");
            return;
        }
        else
        {
        String data = gasesComp.toJSONString(); 
        System.out.println(data);
        String finalData = city + data + "!*"; 
        writeToFile("GasesCompWeatherData.txt", finalData); 
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONArray getGasesComponent(String city) {
        JSONArray n = new JSONArray();
        if (!isCityExists("GasesCompWeatherData.txt", city)) {
            System.out.println("Data of " + city + " doest not exist.");
            return n;
        }
        String data = readFileAsString("GasesCompWeatherData.txt");

        StringBuilder stringBuilder = new StringBuilder();
        int cityLen = city.length();
        int index = data.indexOf(city) + cityLen;
        System.out.println(index);
        if (index != -1) {
            while (index < data.length() && data.charAt(index) != '!' && data.charAt(index) != '*') {
                stringBuilder.append(data.charAt(index));
                index++;
            }

            String cityData = stringBuilder.toString();

            JSONObject jsonObject = new JSONObject();
            try {
                JSONParser parser = new JSONParser();
                jsonObject = (JSONObject) parser.parse(cityData.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            n = new JSONArray();
            n.add(jsonObject);
            return n;

        } else {
            System.out.println("City not found in data.");
            return n;
        }
    }

    public static String readFileAsString(String fileName) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    private static void writeToFile(String fileName, String data) {
        try (FileWriter fileWriter = new FileWriter(fileName, true)) { // The true flag enables append mode
            fileWriter.write(data + System.lineSeparator()); // Append data to a new line
            System.out.println("Data stored successfully in " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isCityExists(String fileName, String city) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(city)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static final String FILE_NAME = "aqi_data.txt";

    @Override
    public void storeAQIComponent(String city, long AQI) {
        if (isCityStored(city)) {
            System.out.println("City Data Already Exists");
            return;
        }
        try (FileWriter fileWriter = new FileWriter(FILE_NAME, true)) { // Append mode
            fileWriter.write(city + ":" + AQI + "\n");
            System.out.println("AQI component stored successfully for " + city);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double getAQIForCity(String city) {
        Map<String, Long> cityAQIMap = readAQIDataFromFile();
        return cityAQIMap.getOrDefault(city, -1L);
    }

    private static Map<String, Long> readAQIDataFromFile() {
        Map<String, Long> cityAQIMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String city = parts[0];
                    long AQI = Long.parseLong(parts[1]);
                    cityAQIMap.put(city, AQI);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return cityAQIMap;
    }

    public static boolean isCityStored(String city) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String storedCity = parts[0];
                    if (storedCity.equals(city)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createDateFile() {
        // Get the current date and time
        Date currentDate = new Date();

        // Format the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);

        // File name
        String fileName = "date.txt";

        try {
            // Create FileWriter
            FileWriter fileWriter = new FileWriter(fileName);

            // Create BufferedWriter
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write the formatted date to the file
            bufferedWriter.write(formattedDate);

            // Close the resources
            bufferedWriter.close();
            fileWriter.close();

            System.out.println("Date has been written to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public boolean compareDatesFromFile(String fileName) {
        // Read date from the file
        String dateFromFile = readDateFromFile(fileName);

        // Get current date
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateStr = dateFormat.format(currentDate);

        System.out.println(currentDateStr);
        System.out.println(dateFromFile);

        // Compare dates ignoring time
        return currentDateStr.equals(dateFromFile);
    }

    public String readDateFromFile(String fileName) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    public void clearFile(String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName, false)) { // Pass false to truncate the file
            // Truncate the file by opening it in write mode
            System.out.println("File " + fileName + " has been cleared.");
        } catch (IOException e) {
            System.out.println("An error occurred while clearing the file: " + e.getMessage());
        }
    }

}
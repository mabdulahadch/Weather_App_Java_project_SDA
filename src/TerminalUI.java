

import java.util.Scanner;
import java.util.function.Consumer;

public class TerminalUI implements AbstractUI {
    private Scanner scanner;

    public TerminalUI() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void userInputFromTextBox(Consumer<String> userInputHandler) {
        System.out.println("Please enter your location and press Enter:");
        String location = scanner.nextLine(); // Wait for user input
        userInputHandler.accept(location); // Invoke the handler with the entered location
    }

    @Override
    public void start() throws Exception {
        displayWeatherAppHeader(); // Display the "Weather Application" block
    }

    @Override
    public void displayAirQualityIndex(int index) {
        System.out.println("Air Quality Index: " + index);
    }

    @Override
    public void displayHumidity(int humidity) {
        System.out.println("Humidity: " + humidity + "%");
    }

    @Override
    public void displayFeelsLike(int temp) {
        System.out.println("Feels Like: " + temp + "°C");
    }

    @Override
    public void displayCurrentDescription(String description) {
        System.out.println("Current Description: " + description);
    }

    @Override
    public void displaySunRiseTime(String time) {
        System.out.println("Sunrise Time: " + time);
    }

    @Override
    public void displaySunSetTime(String time) {
        System.out.println("Sunset Time: " + time);
    }

    @Override
    public void displayH3Temp(int[] temps) {
        System.out.print("H3 Temp : ");
        for (int temp : temps) {
            System.out.print(temp + "°C ");
        }
        System.out.println();
    }

    @Override
    public void displayH3Time(String[] times) {
        System.out.print("H3 Times: ");
        for (String time : times) {
            System.out.print(time + " ");
        }
        System.out.println();
    }

    @Override
    public void displayDMaxTemp(int[] temps) {
        System.out.print("Day Max Temperatures: ");
        for (int temp : temps) {
            System.out.print(temp + "°C ");
        }
        System.out.println();
    }

    @Override
    public void displayDMinTemp(int[] temps) {
        System.out.print("Day Min Temperatures: ");
        for (int temp : temps) {
            System.out.print(temp + "°C ");
        }
        System.out.println();
    }

    @Override
    public void displayDDescription(String[] descriptions) {
        System.out.print("Day Descriptions: ");
        for (String description : descriptions) {
            System.out.print(description + " ");
        }
        System.out.println();
    }

    @Override
    public void displayDDates(String[] dates) {
        System.out.print("Day Dates: ");
        for (String date : dates) {
            System.out.print(date + " ");
        }
        System.out.println();
    }

    @Override
    public void displayGases(Double[] gases) {
        String[] gasNames = {"NO", "NO2", "O3", "SO2", "PM2.5", "PM10", "NH3", "CO"};
        System.out.println("Gases:");
        for (int i = 0; i < gases.length && i < gasNames.length; i++) {
            System.out.println(gasNames[i] + ": " + gases[i]);
        }
    }

    @Override
    public void displayCurrentTemp(int temp) {
        System.out.println("Current Temperature: " + temp + "°C");
    }

    @Override
    public void userInputFromMap(Consumer<String> userInputHandler) {
        System.out.println("Please enter your location and press Enter:");
        String location = scanner.nextLine(); // Wait for user input
        userInputHandler.accept(location);
    }

    // Method to display data within a UI
    public void displayInUI(String label, String data) {
        int length = label.length() + data.length() + 6;
        StringBuilder UI = new StringBuilder();
        UI.append("-").append("-".repeat(Math.max(0, length))).append("-\n");
        UI.append("| ").append(label).append(": ").append(data).append(" |\n");
        UI.append("-").append("-".repeat(Math.max(0, length))).append("-");
        System.out.println(UI.toString());
    }

    // Method to display the "Weather Application" in a block with dashed lines
    public void displayWeatherAppHeader() {
        int length = "Weather Application".length() + 6;
        StringBuilder header = new StringBuilder();
        header.append("-").append("-".repeat(Math.max(0, length))).append("-\n");
        header.append("| ").append("Weather Application").append(" |\n");
        header.append("-").append("-".repeat(Math.max(0, length))).append("-");
        System.out.println(header.toString());
    }

    @Override
    public void displayMinTemp(int temp) {
        System.out.println("Min Temperature: " + temp + "°C");
    }

    @Override
    public void displayMaxTemp(int temp) {
        System.out.println("Max Temperature: " + temp + "°C");
    }

}
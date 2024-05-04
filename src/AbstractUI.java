



import java.util.function.Consumer;

public interface AbstractUI {
    
    void start() throws Exception;

    void userInputFromTextBox(Consumer<String> userInputHandler);
    void userInputFromMap(Consumer<String> userInputHandler);
    
    void displayCurrentTemp(int temp);
    void displayMinTemp(int temp);
    void displayMaxTemp(int temp);

    void displayHumidity(int temp);
    void displayFeelsLike(int temp);
    void displayCurrentDescription(String temp);
    void displaySunRiseTime(String temp);
    void displaySunSetTime(String temp);

    //timestamp 
    void displayH3Temp(int[] temp);
    void displayH3Time(String[] times);

    //5 day forecast 
    void displayDMaxTemp(int[] temp);
    void displayDMinTemp(int[] temps);
    void displayDDescription(String[] descriptions);
    void displayDDates(String[] dates);

    // Gases
    void displayGases(Double[] temp);
    void displayAirQualityIndex(int temp);
}
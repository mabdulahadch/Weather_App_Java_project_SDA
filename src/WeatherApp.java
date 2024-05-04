import javafx.application.Platform;
import org.json.simple.JSONArray;


public class WeatherApp {

    private AbstractUI ui;
    private weatherData data;
    private AbstractDB db;
    private API api;
    private Notification noti;

    public WeatherApp() {
        ui = new JavaUI();
        db = new FileHandler();
        data = new weatherData();
        noti=new Notification();
    }
    
    public void callAPIFirst(String location) {
            db.storeFiveDayData(location, api.getFiveDayForecast());
            db.storeThreeHourly(location, api.getThreeHourlyForecast());
            db.storeGasesComponent(location, api.getComponentGasses());
            db.storeAQIComponent(location, api.getAQI());
            api = null;
    }

    public void setAttributesThroughDataBase(String location) {
        data.setFiveDays(location, db.getFiveDaysData(location));
        data.setThreeHourly(location, db.getThreeHourlyData(location));
        data.setGasesConcentration(location, db.getGasesComponent(location));
        data.setAQI(location, (int) db.getAQIForCity(location));
    }

    public void setAPICallingAttributes(String location) {
        api = new API(location);
            data.setCurrentHumidity(location, api.getCurrentHumidity());
            data.setCurrentMaxTemp(location, (int) api.getMaximumTemperature());
            data.setCurrentMinTemp(location, (int) api.getMinimumTemperature());
            data.setCurrentTemp(location, (int) api.getCurrentTemperature());
            data.setSunSetTime(location, api.getSunsetTime());
            data.setSunriseTime(location, api.getSunriseTime());
            data.setFeelsLikeTemp(location, (int) api.getFeelsLikeTemperature());
            data.setWeatherDiscription(location, api.getWeatherDescription());
    }

    public void retrieve(String location) {
        JSONArray d = db.getFiveDaysData(location);
        setAPICallingAttributes(location);
        if (d.isEmpty()) {
            callAPIFirst(location);
        }
        setAttributesThroughDataBase(location);
    }

    public void display() {
        Platform.startup(() -> {
            try {
                ui.start();
                retrieve("Lahore");
                displayUIComponents();
                ui.userInputFromTextBox(userInput -> {
                    retrieve(userInput);
                    displayUIComponents();
                    noti.displayTrayNotification(data.getAQI(),data.getWeatherDiscription());
                });
                ui.userInputFromMap(line -> {
                    retrieve(line);
                    displayUIComponents();
                    noti.displayTrayNotification(data.getAQI(),data.getWeatherDiscription());
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void displayUIComponents() {
        ui.displayCurrentTemp(data.getCurrentTemp());
        ui.displaySunSetTime(data.getSunSetTime());
        ui.displaySunRiseTime(data.getSunriseTime());
        ui.displayHumidity(data.getCurrentHumidity());
        ui.displayFeelsLike(data.getFeelsLikeTemp());
        ui.displayCurrentDescription(data.getWeatherDiscription());
        ui.displayMinTemp(data.getCurrentMinTemp());
        ui.displayMaxTemp(data.getCurrentMaxTemp());
        ui.displayGases(data.getGasesData());
        ui.displayDDates(data.getFiveDayDates());
        ui.displayDDescription(data.getFiveDayDesc());
        ui.displayDMaxTemp(data.getFiveDayMaxTemp());
        ui.displayDMinTemp(data.getFiveDayMinTemp());
        ui.displayH3Temp(data.getThreeHourlyTemp());
        ui.displayH3Time(data.getThreeHourlyTimes());
    }

    public static void main(String[] args) {
        WeatherApp app = new WeatherApp();
        app.display();
    }
}



import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Notification {

    public void displayTrayNotification(int airQualityIndex, String weatherDescription) {
        displayAirQualityNotification(airQualityIndex);
        displayWeatherNotification(weatherDescription);
    }

    public void displayWeatherNotification(String weatherDescription) {
        if (isStormyWeather(weatherDescription)) {
            String title = "Weather Notification";
            String message = "Stormy weather conditions: " + weatherDescription;

            displayNotification(title, message);
        }
    }

    public void displayAirQualityNotification(int airQualityIndex) {
        if (airQualityIndex > 3) {
            String title = "Air Quality Notification";
            String message = getMessageForAQI(airQualityIndex);

            displayNotification(title, message);
        }
    }

    private void displayNotification(String title, String message) {
        // Load icon for the system tray
        BufferedImage iconImage = null;
        try {
            InputStream iconStream = Notification.class.getResourceAsStream("/img/weather-app-icon.png");
            if (iconStream != null) {
                iconImage = ImageIO.read(iconStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create tray icon
        if (SystemTray.isSupported() && iconImage != null) {
            TrayIcon trayIcon = new TrayIcon(iconImage, "Weather App");
            trayIcon.setImageAutoSize(true);

            // Add tray icon to the system tray
            try {
                SystemTray.getSystemTray().add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }

            // Display notification
            trayIcon.displayMessage(title, message, TrayIcon.MessageType.WARNING);
        } else {
            System.out.println("System tray not supported or icon not available.");
        }
    }

    private String getMessageForAQI(int airQualityIndex) {
        switch (airQualityIndex) {
            case 1:
                return "Good air quality.";
            case 2:
                return "Fair air quality.";
            case 3:
                return "Moderate air quality.";
            case 4:
                return "Poor air quality.";
            case 5:
                return "Very Poor air quality.";
            default:
                return "Unknown air quality.";
        }
    }

    private boolean isStormyWeather(String weatherDescription) {
        return weatherDescription != null &&
                (weatherDescription.contains("thunderstorm") || weatherDescription.contains("rain") || weatherDescription.contains("snow"));
    }

}

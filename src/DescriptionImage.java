
import javafx.scene.image.Image;
import java.io.InputStream;

public class DescriptionImage {

    public static Image getImageForDescription(String description) {
        InputStream inputStream = null;

        if (description.equals("shower rain")) {
            description = "shower";
        } else if (description.equals("broken clouds")) {
            description = "broken";
        }

        if (description.toLowerCase().contains("sky")) {
            inputStream = DescriptionImage.class.getResourceAsStream("/img/clear sky.png");
        } else if (description.toLowerCase().contains("clouds")) {
            inputStream = DescriptionImage.class.getResourceAsStream("/img/scattered cloud.png");
        } else if (description.toLowerCase().contains("broken")) {
            inputStream = DescriptionImage.class.getResourceAsStream("/img/broken clouds.png");
        } else if (description.toLowerCase().contains("shower")) {
            inputStream = DescriptionImage.class.getResourceAsStream("/img/shower rain.png");
        } else if (description.toLowerCase().contains("rain")) {
            inputStream = DescriptionImage.class.getResourceAsStream("/img/rain.png");
        } else if (description.toLowerCase().contains("thunderstorm")) {
            inputStream = DescriptionImage.class.getResourceAsStream("/img/thunderstorm.png");
        } else if (description.toLowerCase().contains("mist")) {
            inputStream = DescriptionImage.class.getResourceAsStream("/img/mist.png");
        } else if (description.toLowerCase().contains("snow")) {
            inputStream = DescriptionImage.class.getResourceAsStream("/img/snow.png");
        } else if (description.toLowerCase().contains("windy")) {
            inputStream = DescriptionImage.class.getResourceAsStream("/img/windy.png");
        } else {
            inputStream = DescriptionImage.class.getResourceAsStream("/img/scattered cloud.png");
        }

        if (inputStream != null) {
            return new Image(inputStream);
        } else {
            // Provide fallback image or handle the error appropriately
            System.err.println("Failed to load image for description: " + description);
            // Return a placeholder image or null depending on your requirements
            return null;
        }
    }
}

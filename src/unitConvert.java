

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class unitConvert {

    private boolean flag;

    public unitConvert() {
        this.flag = false; // Default unit is Celsius
    }

    public void changeUnit(Label ctxt, Label ftxt,Rectangle unitrect, Rectangle unitrect1,Label temperatureLabel, Label feelLabel, Label c_mintemp, Label c_maxtemp,
                           Label[] f_maxLabels, Label[] f_minLabels, Label[] h3_tempLabels) {
        flag = !flag;
        unitrect.setVisible(!unitrect.isVisible());
        unitrect1.setVisible(!unitrect1.isVisible());

        if (!flag) {
             ctxt.setTextFill(Color.WHITE);
            ftxt.setTextFill(Color.BLACK);
            // Change to Celsius
            temperatureLabel.setText(fahrenheitToCelsius(getIntegerFromString(temperatureLabel)) + "°");

            for (Label label : f_maxLabels) {
                label.setText((fahrenheitToCelsius(getIntegerFromString(label))) + "°");
            }
            for (Label label : f_minLabels) {
                label.setText((fahrenheitToCelsius(getIntegerFromString(label))) + "°");
            }
            for (Label label : h3_tempLabels) {
                label.setText((fahrenheitToCelsius(getIntegerFromString(label))) + "°");
            }
            String temp1 = "Feels Like " + fahrenheitToCelsius(getIntegerFromString(feelLabel));
            feelLabel.setText(temp1 + "°");
            c_mintemp.setText((fahrenheitToCelsius(getIntegerFromString(c_mintemp))) + "°");
            c_maxtemp.setText((fahrenheitToCelsius(getIntegerFromString(c_maxtemp))) + "°");

        } else {
            ctxt.setTextFill(Color.BLACK);
            ftxt.setTextFill(Color.WHITE);
            // Change to Fahrenheit
            temperatureLabel.setText(celsiusToFahrenheit(getIntegerFromString(temperatureLabel)) + "°");

            for (Label label : f_maxLabels) {
                label.setText((celsiusToFahrenheit(getIntegerFromString(label))) + "°");
            }
            for (Label label : f_minLabels) {
                label.setText((celsiusToFahrenheit(getIntegerFromString(label))) + "°");
            }
            for (Label label : h3_tempLabels) {
                label.setText((celsiusToFahrenheit(getIntegerFromString(label))) + "°");
            }
            String temp = "Feels Like " + celsiusToFahrenheit(getIntegerFromString(feelLabel));
            feelLabel.setText(temp + "°");
            c_mintemp.setText((celsiusToFahrenheit(getIntegerFromString(c_mintemp))) + "°");
            c_maxtemp.setText((celsiusToFahrenheit(getIntegerFromString(c_maxtemp))) + "°");
        }
    }
    int getIntegerFromString(Label temp) {
        String text = temp.getText().replaceAll("[^\\d-]", ""); // Keep negative sign and digits
        return Integer.parseInt(text);
    }

    int celsiusToFahrenheit(int celsius) {
        return (int) Math.round((celsius * 9.0 / 5.0) + 32);
    }

    int fahrenheitToCelsius(int fahrenheit) {
        return (int) Math.round((fahrenheit - 32) * 5.0 / 9.0);
    }
}

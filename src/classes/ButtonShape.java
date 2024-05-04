package classes;

import javafx.scene.control.Button;

public class ButtonShape extends Shapes{
    private Button button;

    public ButtonShape(Button button, double designWidth, double designHeight, double screenWidth, double screenHeight) {
        super(designWidth, designHeight, screenWidth, screenHeight);
        this.button = button;
    }

    @Override
    public void dynamic() {
        button.setLayoutX(scaleWidth(button.getLayoutX()));
        button.setLayoutY(scaleHeight(button.getLayoutY()));
        button.setPrefWidth(scaleWidth(button.getPrefWidth()));
        button.setPrefHeight(scaleHeight(button.getPrefHeight()));
    }
}

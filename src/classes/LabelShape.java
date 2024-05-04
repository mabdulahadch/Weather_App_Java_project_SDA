package classes;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class LabelShape extends Shapes {
    private Label label;

    public LabelShape(Label label, double designWidth, double designHeight, double screenWidth, double screenHeight) {
        super(designWidth, designHeight, screenWidth, screenHeight);
        this.label = label;
    }

    @Override
    public void dynamic() {
        Font oldFont = label.getFont();
        double tempFontSize = scaleHeight(label.getFont().getSize());
        label.setFont(Font.font(oldFont.getFamily(), tempFontSize));
        label.setLayoutX(scaleWidth(label.getLayoutX()));
        label.setLayoutY(scaleHeight(label.getLayoutY()));
        label.setPrefWidth(scaleWidth(label.getPrefWidth()));
        label.setPrefHeight(scaleHeight(label.getPrefHeight()));
    }
}

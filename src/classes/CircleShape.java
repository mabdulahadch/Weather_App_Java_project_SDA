package classes;

import javafx.scene.shape.Circle;

public class CircleShape extends Shapes {
    private Circle circle;

    public CircleShape(Circle circle, double designWidth, double designHeight, double screenWidth, double screenHeight) {
        super(designWidth, designHeight, screenWidth, screenHeight);
        this.circle = circle;
    }

    @Override
    public void dynamic() {
        circle.setRadius(scaleWidth(circle.getRadius()));
        circle.setLayoutX(scaleWidth(circle.getLayoutX()));
        circle.setLayoutY(scaleHeight(circle.getLayoutY()));
    }
}

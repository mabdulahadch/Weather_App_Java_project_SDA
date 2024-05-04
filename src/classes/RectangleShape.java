package classes;

import javafx.scene.shape.Rectangle;

public class RectangleShape extends Shapes {
    private Rectangle rectangle;

    public RectangleShape(Rectangle rectangle, double designWidth, double designHeight, double screenWidth, double screenHeight) {
        super(designWidth, designHeight, screenWidth, screenHeight);
        this.rectangle = rectangle;
    }

    @Override
    public void dynamic() {
        rectangle.setWidth(scaleWidth(rectangle.getWidth()));
        rectangle.setHeight(scaleHeight(rectangle.getHeight()));
        rectangle.setLayoutX(scaleWidth(rectangle.getLayoutX()));
        rectangle.setLayoutY(scaleHeight(rectangle.getLayoutY()));
    }
}

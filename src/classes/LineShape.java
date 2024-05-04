package classes;

import javafx.scene.shape.Line;

public class LineShape extends Shapes {
    private Line line;

    public LineShape(Line line, double designWidth, double designHeight, double screenWidth, double screenHeight) {
        super(designWidth, designHeight, screenWidth, screenHeight);
        this.line = line;
    }

    @Override
    public void dynamic() {
        line.setLayoutX(scaleWidth(line.getLayoutX()));
        line.setLayoutY(scaleHeight(line.getLayoutY()));
        line.setStartX(scaleWidth(line.getStartX()));
        line.setEndX(scaleWidth(line.getEndX()));
    }
}

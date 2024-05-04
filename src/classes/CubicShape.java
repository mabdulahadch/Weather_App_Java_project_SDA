package classes;

import javafx.scene.shape.CubicCurve;

public class CubicShape extends Shapes {
    private CubicCurve cubicCurve;

    public CubicShape(CubicCurve cubicCurve, double designWidth, double designHeight, double screenWidth, double screenHeight) {
        super(designWidth, designHeight, screenWidth, screenHeight);
        this.cubicCurve = cubicCurve;
    }

    @Override
    public void dynamic() {
        cubicCurve.setLayoutX(scaleWidth(cubicCurve.getLayoutX()));
        cubicCurve.setLayoutY(scaleHeight(cubicCurve.getLayoutY()));
        cubicCurve.setStartX(scaleWidth(cubicCurve.getStartX()));
        cubicCurve.setEndX(scaleWidth(cubicCurve.getEndX()));
        cubicCurve.setStartY(scaleHeight(cubicCurve.getStartY()));
        cubicCurve.setEndY(scaleHeight(cubicCurve.getEndY()));
        cubicCurve.setControlX1(scaleWidth(cubicCurve.getControlX1()));
        cubicCurve.setControlX2(scaleWidth(cubicCurve.getControlX2()));
        cubicCurve.setControlY1(scaleHeight(cubicCurve.getControlY1()));
        cubicCurve.setControlY2(scaleHeight(cubicCurve.getControlY2()));
    }
}


package classes;

import javafx.scene.layout.Region;

public class RegionShape extends Shapes {
    private Region region;

    public RegionShape(Region region, double designWidth, double designHeight, double screenWidth, double screenHeight) {
        super(designWidth, designHeight, screenWidth, screenHeight);
        this.region = region;
    }

    @Override
    public void dynamic() {
        region.setPrefWidth(scaleWidth(region.getPrefWidth()));
        region.setPrefHeight(scaleHeight(region.getPrefHeight()));
        region.setLayoutX(scaleWidth(region.getLayoutX()));
        region.setLayoutY(scaleHeight(region.getLayoutY()));
    }
}

package classes;

import javafx.scene.image.ImageView;

public class ImageViewShape extends Shapes {
    private ImageView imageView;

    public ImageViewShape(ImageView imageView, double designWidth, double designHeight, double screenWidth, double screenHeight) {
        super(designWidth, designHeight, screenWidth, screenHeight);
        this.imageView = imageView;
    }

    @Override
    public void dynamic() {
        imageView.setFitWidth(scaleWidth(imageView.getFitWidth()));
        imageView.setFitHeight(scaleHeight(imageView.getFitHeight()));
        imageView.setLayoutX(scaleWidth(imageView.getLayoutX()));
        imageView.setLayoutY(scaleHeight(imageView.getLayoutY()));
    }
}

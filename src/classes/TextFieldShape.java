package classes;

import javafx.scene.control.TextField;

public class TextFieldShape extends Shapes {
    private TextField tf;

    public TextFieldShape(TextField tf,double designWidth, double designHeight, double screenWidth, double screenHeight) {
        super(designWidth, designHeight, screenWidth, screenHeight);
        this.tf=tf;
    }

    @Override
    public void dynamic() {
        tf.setLayoutX(scaleWidth(tf.getLayoutX()));
        tf.setLayoutY(scaleHeight(tf.getLayoutY()));
    }
    
}

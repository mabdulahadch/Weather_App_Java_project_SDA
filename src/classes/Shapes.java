package classes;

public abstract class Shapes {
    protected double designWidth;
    protected double designHeight;
    protected double screenWidth;
    protected double screenHeight;
    
    public Shapes(double designWidth, double designHeight, double screenWidth, double screenHeight) {
        this.designWidth = designWidth;
        this.designHeight = designHeight;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public abstract void dynamic();

    public double scaleWidth(double value) {
        return screenWidth * value / designWidth;
    }

    public double scaleHeight(double value) {
        return screenHeight * value / designHeight;
    }
}

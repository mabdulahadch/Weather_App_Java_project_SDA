package classes;

import javafx.scene.web.WebView;

public class WebViewShape extends Shapes {
    private WebView webView;

    public WebViewShape(WebView webView, double designWidth, double designHeight, double screenWidth, double screenHeight) {
        super(designWidth, designHeight, screenWidth, screenHeight);
        this.webView = webView;
    }

    @Override
    public void dynamic() {
        webView.setPrefWidth(scaleWidth(webView.getPrefWidth()));
        webView.setPrefHeight(scaleHeight(webView.getPrefHeight()));
        webView.setLayoutX(scaleWidth(webView.getLayoutX()));
        webView.setLayoutY(scaleHeight(webView.getLayoutY()));
    }
}

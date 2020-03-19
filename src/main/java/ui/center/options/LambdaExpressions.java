package ui.center.options;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import ui.MainUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class LambdaExpressions implements CenterUIInterface {
    public static final String VIEW_FILE_NAME = "LambdaExpressions.fxml";
    public static final String VIEW_FILE_PACKAGE = "ui" + File.separator + "center" + File.separator + "options";

    private Parent parent;
    private LambdaExpressionsController controller;

    public LambdaExpressions() {
        try {
            initController();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void initController() throws IOException {
        URL url = MainUI.getViewFileURL(VIEW_FILE_PACKAGE, VIEW_FILE_NAME);
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        this.parent = fxmlLoader.load();
        this.controller = fxmlLoader.getController();
    }

    @Override
    public CenterUIControllerInterface getController() {
        return this.controller;
    }
}
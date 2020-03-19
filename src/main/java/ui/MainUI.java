package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.example.ExampleMain;
import ui.pop.up.ConfirmPopUpUI;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class MainUI extends Application {
    public static final int SCENE_WIDTH = 800;
    public static final int SCENE_HEIGHT = 600;
    public static final String STAGE_TITLE = "Java Laboratories";
    public static final String VIEW_FILE_NAME = "MainUI.fxml";
    public static final String VIEW_FILE_PACKAGE = "ui";
    public static final String PATH_TO_RESOURCE_FOLDER = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources";

    private Stage stage = null;
    private Scene scene = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = getViewFileURL(VIEW_FILE_PACKAGE, VIEW_FILE_NAME);
        Parent parent = FXMLLoader.load(url);

        this.scene = new Scene(parent, SCENE_WIDTH, SCENE_HEIGHT);

        this.stage = stage;
        this.stage.setTitle(STAGE_TITLE);
        this.stage.setScene(scene);
        this.stage.setOnCloseRequest(actionEvent -> {
            actionEvent.consume();
            handleCloseMainStage();
        });
        this.stage.show();
    }

    /**
     * View file (.fxml) needs to be in "src/main/resources/..." location under folder corresponding to Model class
     * (.java) package location from "src/main/java/..." to be able to get URL using:
     * URL url = ClassName.class.getResource(viewFileName);
     *
     * The second option is to get URL using absolute path via:
     * URL url = new File(viewFileFullPath).toURI().toURL();
     **/
    public static URL getViewFileURL(String packageName, String fileName) {
        URL url = ExampleMain.class.getResource(fileName);
        if(url == null) url = getViewFileURLUsingFile(packageName, fileName);
        return url;
    }

    private static URL getViewFileURLUsingFile(String packageName, String fileName) {
        final String viewFileFullPath = PATH_TO_RESOURCE_FOLDER + File.separator + packageName + File.separator + fileName;
        URL url = null;
        try {
            url = new File(viewFileFullPath).toURI().toURL();
        } catch(MalformedURLException e) {
            final String message = "Could not retrieve resource: \"" + fileName + "\" or \"" + viewFileFullPath + "\".";
            throw new NullPointerException(message + "\n\r" + e.getMessage());
        }
        return url;
    }

    private void handleCloseMainStage() {
        ConfirmPopUpUI confirmPopUpUI = new ConfirmPopUpUI("Close program confirmation window", "Do you really want to close the program?");
        Boolean answer = confirmPopUpUI.getAnswer();
        if(answer != null && answer) stage.close();
    }
}

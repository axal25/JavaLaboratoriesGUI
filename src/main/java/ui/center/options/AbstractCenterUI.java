package ui.center.options;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

public abstract class AbstractCenterUI {
    public static final String DEFAULT_EXERCISES_MENU_ITEM_TEXT = "The only exercise";

    AbstractCenterUIController controller = null;

    private AbstractCenterUI() {}

    /** String exercise is
     * final static EXERCISES[index]
     * field of given class extending this one
    **/
    public AbstractCenterUI(String exercise) {
        try {
            loadController(exercise);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadController(String exercise) throws IOException {
        int viewFileIndex = getViewFileIndex(exercise);
        URL url = loadURL(viewFileIndex);
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent parent = fxmlLoader.load();
        this.controller = fxmlLoader.getController();
    }

    public abstract int getViewFileIndex(String exercise);
    public abstract URL loadURL(int viewFileIndex);

    public AbstractCenterUIController getController() {
        return this.controller;
    }
}

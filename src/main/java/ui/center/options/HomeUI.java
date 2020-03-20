package ui.center.options;

import javafx.scene.Parent;
import ui.MainUI;

import java.io.File;
import java.net.URL;
import java.util.Arrays;

public class HomeUI extends AbstractCenterUI {
    public static final String[] VIEW_FILE_NAMES = {"HomeUI.fxml"};
    public static final String VIEW_FILE_PACKAGE = "ui" + File.separator + "center" + File.separator + "options";
    public static final String[] EXERCISES = {"The only welcome page available"};

    private Parent parent;
    private HomeUIController controller;

    public HomeUI(String exercise) {
        super(exercise);
    }

    public int getViewFileIndex(String exercise) {
        return Arrays.asList(EXERCISES).indexOf(exercise);
    }

    public URL loadURL(int viewFileIndex) {
        return MainUI.getViewFileURL(VIEW_FILE_PACKAGE, VIEW_FILE_NAMES[viewFileIndex]);
    }
}

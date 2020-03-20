package ui.center.options;

import javafx.scene.Parent;
import ui.MainUI;
import ui.MainUIController;

import java.io.File;
import java.net.URL;
import java.util.Arrays;

public class Laboratory2UI extends AbstractCenterUI {
    public static final String[] VIEW_FILE_NAMES = {"Laboratory2UI.fxml"};
    public static final String VIEW_FILE_PACKAGE = "ui" + File.separator + "center" + File.separator + "options";
    public static final String[] EXERCISES = {AbstractCenterUI.DEFAULT_EXERCISES_MENU_ITEM_TEXT};

    private Parent parent;
    private Laboratory2UIController controller;

    public Laboratory2UI(String exercise) {
        super(exercise);
    }

    public int getViewFileIndex(String exercise) {
        return Arrays.asList(EXERCISES).indexOf(exercise);
    }

    public URL loadURL(int viewFileIndex) {
        return MainUI.getViewFileURL(VIEW_FILE_PACKAGE, VIEW_FILE_NAMES[viewFileIndex]);
    }
}

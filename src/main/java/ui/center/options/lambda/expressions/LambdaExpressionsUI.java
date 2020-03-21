package ui.center.options.lambda.expressions;

import javafx.scene.Parent;
import ui.MainUI;
import ui.center.options.AbstractCenterUI;
import ui.center.options.NoMatchingIndexFoundException;

import java.io.File;
import java.net.URL;

public class LambdaExpressionsUI extends AbstractCenterUI {
    public static final String[] VIEW_FILE_NAMES = {"LambdaExpressionsUI.fxml"};
    public static final String VIEW_FILE_PACKAGE = "ui" + File.separator + "center" + File.separator + "options" + File.separator + "lambda" + File.separator + "expressions";
    public static final String[] EXERCISES = {AbstractCenterUI.DEFAULT_EXERCISES_MENU_ITEM_TEXT};

    private Parent parent;
    private LambdaExpressionsUIController controller;

    public LambdaExpressionsUI(String exercise) {
        super(exercise);
    }

    public int getViewFileIndex(String exercise) throws NoMatchingIndexFoundException {
        return getSafelyViewFileIndex(EXERCISES, exercise);
    }

    public URL loadURL(int viewFileIndex) {
        return MainUI.getViewFileURL(VIEW_FILE_PACKAGE, VIEW_FILE_NAMES[viewFileIndex]);
    }
}

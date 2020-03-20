package ui.center.options;

import javafx.scene.Parent;
import ui.MainUI;

import java.io.File;
import java.net.URL;
import java.util.Arrays;

public class MayerynRecruitmentUI extends AbstractCenterUI {
    public static final String[] VIEW_FILE_NAMES = {
            "MayerynRecruitmentUI_Exercise1.fxml",
            "MayerynRecruitmentUI_Exercise2.fxml",
            "MayerynRecruitmentUI_Exercise3.fxml",
    };
    public static final String VIEW_FILE_PACKAGE = "ui" + File.separator + "center" + File.separator + "options";
    public static final String[] EXERCISES = {
            "Exercise #1",
            "Exercise #2",
            "Exercise #3",
    };

    private Parent parent;
    private MayerynRecruitmentUIController controller;

    public MayerynRecruitmentUI(String exercise) {
        super(exercise);
    }

    public int getViewFileIndex(String exercise) {
        return Arrays.asList(EXERCISES).indexOf(exercise);
    }

    public URL loadURL(int viewFileIndex) {
        return MainUI.getViewFileURL(VIEW_FILE_PACKAGE, VIEW_FILE_NAMES[viewFileIndex]);
    }
}

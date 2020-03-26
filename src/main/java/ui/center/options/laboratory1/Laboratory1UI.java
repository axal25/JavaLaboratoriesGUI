package ui.center.options.laboratory1;

import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import ui.MainUI;
import ui.center.options.AbstractCenterUI;
import ui.center.options.NoMatchingIndexFoundException;

import java.io.File;
import java.net.URL;

public class Laboratory1UI extends AbstractCenterUI {
    public static final String[] VIEW_FILE_NAMES = {
            "Laboratory1UI_Introduction_Loading.fxml",
            "Laboratory1UI_Exercise1_Loading.fxml",
            "Laboratory1UI_Exercise2_Loading.fxml",
            "Laboratory1UI_Exercise3_Loading.fxml",
    };
    public static final String[] COMPLEX_VIEW_FILE_NAMES = {
            "Laboratory1UI_Introduction_Complex.fxml",
            "Laboratory1UI_Exercise1_Complex.fxml",
            "Laboratory1UI_Exercise2_Complex.fxml",
            "Laboratory1UI_Exercise3_Complex.fxml",
    };
    public static final String VIEW_FILE_PACKAGE = "ui" + File.separator + "center" + File.separator + "options" + File.separator + "laboratory1";
    public static final String[] EXERCISES = {
            "Introduction",
            "Exercise #1",
            "Exercise #2",
            "Exercise #3",
    };
    public static final Long FAKE_LOADING_DELAY = 500L;

    private Parent parent;
    private Laboratory1UIController controller;

    public Laboratory1UI(String exercise) {
        super(exercise);
        loadComplexScene(exercise);
    }

    public int getViewFileIndex(String exercise) throws NoMatchingIndexFoundException {
        return getSafelyViewFileIndex(EXERCISES, exercise);
    }

    public URL loadURL(int viewFileIndex) {
        return MainUI.getViewFileURL(VIEW_FILE_PACKAGE, VIEW_FILE_NAMES[viewFileIndex]);
    }

    private void loadComplexScene(String exercise) {
        try {
            startTask(getSafelyViewFileIndex(EXERCISES, exercise));
        } catch (NoMatchingIndexFoundException e) {
            e.printStackTrace();
        }
    }

    private void startTask(int complexFileIndex) {
        Task<Parent> loadTask = new Task<Parent>() {
            public int complexFileIndexInnerCopy = complexFileIndex;

            @Override
            protected Parent call() throws Exception {
                Thread.sleep(FAKE_LOADING_DELAY);
                URL url = MainUI.getViewFileURL(VIEW_FILE_PACKAGE, COMPLEX_VIEW_FILE_NAMES[complexFileIndexInnerCopy]);
                FXMLLoader fxmlLoader = new FXMLLoader(url);
                Parent parent = fxmlLoader.load();
                return parent;
            }
        };

        loadTask.setOnSucceeded(workerStateEvent -> {
            super.getController().centerNodeProperty.set(loadTask.getValue());
        });

        loadTask.setOnFailed(workerStateEvent -> {
            loadTask.getException().printStackTrace();
        });

        Thread thread = new Thread(loadTask);
        thread.start();
    }
}

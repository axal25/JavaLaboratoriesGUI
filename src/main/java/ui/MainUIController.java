package ui;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import ui.center.factory.CenterFactory;
import ui.center.factory.CenterOption;
import ui.center.factory.CenterOptionFactory;
import ui.center.options.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MainUIController implements Initializable {
    public static final String EXERCISES_MENU_TEXT = "Exercises";
    public static final int EXERCISES_MENU_INDEX = 1;

    public BorderPane mainBorderPane;
    public MenuBar menuBar;

    private AbstractCenterUI abstractCenterUI;
    private Node centerNode;
    private CenterOption centerOption;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.centerOption = CenterOption.HOME;
        loadNewView(HomeUI.EXERCISES[0]);
    }

    public void loadNewView(String exercise) {
        loadMenu();
        loadCenter(exercise);
    }

    private void loadCenter(String exercise) {
        this.abstractCenterUI = CenterFactory.getCenter(this.centerOption, exercise);
        Node newCenterNode = abstractCenterUI.getController().getCenterNode();
        this.mainBorderPane.getChildren().removeAll(this.centerNode);
        this.mainBorderPane.setCenter(newCenterNode);
        this.centerNode = newCenterNode;
    }

    private void loadMenu() {
        Menu menu = this.menuBar.getMenus().get(EXERCISES_MENU_INDEX);
        if(menu.getText().compareTo(EXERCISES_MENU_TEXT) == 0) {
            populateMenuWithMenuItemsAndTheirEventHandlers(menu, getExercises());
        } else throw new UnsupportedOperationException("Could not get \"" + EXERCISES_MENU_TEXT + "\" Menu from MenuBar at index: " + EXERCISES_MENU_INDEX + ".");
    }

    private String[] getExercises() {
        String[] exercises = null;
        switch (this.centerOption) {
            case HOME:
                exercises = HomeUI.EXERCISES;
                break;
            case LABORATORY_1:
                exercises = Laboratory1UI.EXERCISES;
                break;
            case LABORATORY_2:
                exercises = Laboratory2UI.EXERCISES;
                break;
            case LAMBDA_EXPRESSIONS:
                exercises = LambdaExpressionsUI.EXERCISES;
                break;
            case MAYERYN_RECRUITMENT:
                exercises = MayerynRecruitmentUI.EXERCISES;
                break;
            default:
                throw new UnsupportedOperationException("Unsupported option: " + centerOption);
        }
        return exercises;
    }

    private void populateMenuWithMenuItemsAndTheirEventHandlers(Menu exerciseMenu, String[] exercises) {
        exerciseMenu.getItems().removeAll(exerciseMenu.getItems());
        for(String exercise : exercises) {
            MenuItem newMenuItem = new MenuItem(exercise);
            newMenuItem.setOnAction(actionEvent -> handleExercisesMenuItemClick(actionEvent));
            exerciseMenu.getItems().add(newMenuItem);
        }
    }

    public void handleLaboratoriesMenuItemClick(ActionEvent actionEvent) {
        String menuItemText = ((MenuItem) actionEvent.getSource()).getText();
        this.centerOption = CenterOptionFactory.getCenterOption(menuItemText);
        loadNewView(getExercises()[0]);
    }

    public void handleExercisesMenuItemClick(ActionEvent actionEvent) {
        loadNewView(((MenuItem) actionEvent.getSource()).getText());
    }

    private String[] getViewFileNames() {
        String[] viewFileNames = null;
        switch (this.centerOption) {
            case HOME:
                viewFileNames = HomeUI.VIEW_FILE_NAMES;
                break;
            case LABORATORY_1:
                viewFileNames = Laboratory1UI.VIEW_FILE_NAMES;
                break;
            case LABORATORY_2:
                viewFileNames = Laboratory2UI.VIEW_FILE_NAMES;
                break;
            case LAMBDA_EXPRESSIONS:
                viewFileNames = LambdaExpressionsUI.VIEW_FILE_NAMES;
                break;
            case MAYERYN_RECRUITMENT:
                viewFileNames = MayerynRecruitmentUI.VIEW_FILE_NAMES;
                break;
            default:
                throw new UnsupportedOperationException("Unsupported option: " + centerOption);
        }
        return viewFileNames;
    }
}

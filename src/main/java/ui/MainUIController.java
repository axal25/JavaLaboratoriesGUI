package ui;

import javafx.collections.ObservableList;
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
import ui.center.options.home.HomeUI;
import ui.center.options.laboratory1.Laboratory1UI;
import ui.center.options.laboratory2.Laboratory2UI;
import ui.center.options.lambda.expressions.LambdaExpressionsUI;
import ui.center.options.mayeryn.recruitment.MayerynRecruitmentUI;

import java.net.URL;
import java.util.ResourceBundle;

public class MainUIController implements Initializable {
    public static final String LABORATORIES_MENU_TEXT = "Laboratories";
    public static final int LABORATORIES_MENU_INDEX = 0;
    public static final String DEFAULT_MENU_ITEM_CSS_STYLE = "menu-item";
    public static final String SELECTED_MENU_ITEM_CSS_STYLE = "menu-item-selected";
    public static final String EXERCISES_MENU_TEXT = "Exercises";
    public static final int EXERCISES_MENU_INDEX = 1;

    public BorderPane mainBorderPane;
    public MenuBar menuBar;

    private AbstractCenterUI abstractCenterUI;
    private Node centerNode;
    private CenterOption centerOption;
    private String exercise;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadNewView(CenterOption.HOME, HomeUI.EXERCISES[0]);
    }

    public void loadNewView(CenterOption centerOption, String exercise) {
        this.centerOption = centerOption;
        this.exercise = exercise;
        loadMenus();
        loadCenter();
    }

    private void loadCenter() {
        this.abstractCenterUI = CenterFactory.getCenter(this.centerOption, this.exercise);
        Node newCenterNode = abstractCenterUI.getController().getCenterNode();
        this.mainBorderPane.getChildren().removeAll(this.centerNode);
        this.mainBorderPane.setCenter(newCenterNode);
        this.centerNode = newCenterNode;
    }

    private void loadMenus() {
        loadLaboratoriesMenu();
        loadExercisesMenu();
    }

    private void loadLaboratoriesMenu() {
        Menu menu = this.menuBar.getMenus().get(LABORATORIES_MENU_INDEX);
        if(menu.getText().compareTo(LABORATORIES_MENU_TEXT) == 0) {
            MenuItem[] menuItems = copyMenuItemsToArray(menu.getItems());
            for (int i=0; i < menuItems.length; i++) {
                menuItems[i].getStyleClass().removeAll(DEFAULT_MENU_ITEM_CSS_STYLE, SELECTED_MENU_ITEM_CSS_STYLE);
                if(CenterOptionFactory.getCenterOption(menuItems[i].getText()) == this.centerOption) menuItems[i].getStyleClass().add(SELECTED_MENU_ITEM_CSS_STYLE);
                else menuItems[i].getStyleClass().add(DEFAULT_MENU_ITEM_CSS_STYLE);
                menu.getItems().remove(i);
                menu.getItems().add(i, menuItems[i]);
            }
        } else throw new UnsupportedOperationException("Could not get \"" + LABORATORIES_MENU_TEXT + "\" Menu from MenuBar at index: " + LABORATORIES_MENU_INDEX + ".");
    }

    private MenuItem[] copyMenuItemsToArray(ObservableList<MenuItem> menuItemsList) {
        Object[] objects = menuItemsList.toArray();
        MenuItem[] menuItemsArray = new MenuItem[objects.length];
        for (int i = 0; i < objects.length && i < menuItemsArray.length; i++) {
            menuItemsArray[i] = (MenuItem) objects[i];
        }
        return menuItemsArray;
    }

    private void loadExercisesMenu() {
        Menu menu = this.menuBar.getMenus().get(EXERCISES_MENU_INDEX);
        if(menu.getText().compareTo(EXERCISES_MENU_TEXT) == 0) {
            populateExercisesMenuWithMenuItemsAndTheirEventHandlers(menu, getExercises(this.centerOption));
        } else throw new UnsupportedOperationException("Could not get \"" + EXERCISES_MENU_TEXT + "\" Menu from MenuBar at index: " + EXERCISES_MENU_INDEX + ".");
    }

    private String[] getExercises(CenterOption centerOption) {
        String[] exercises = null;
        switch (centerOption) {
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

    private void populateExercisesMenuWithMenuItemsAndTheirEventHandlers(Menu exerciseMenu, String[] exercises) {
        exerciseMenu.getItems().removeAll(exerciseMenu.getItems());
        for(String exercise : exercises) {
            MenuItem newMenuItem = new MenuItem(exercise);
            newMenuItem.setOnAction(actionEvent -> handleExercisesMenuItemClick(actionEvent));
            if(this.exercise.compareTo(exercise) == 0) {
                newMenuItem.getStyleClass().remove(DEFAULT_MENU_ITEM_CSS_STYLE);
                newMenuItem.getStyleClass().add(SELECTED_MENU_ITEM_CSS_STYLE);
            }
            exerciseMenu.getItems().add(newMenuItem);
        }
    }

    public void handleLaboratoriesMenuItemClick(ActionEvent actionEvent) {
        String menuItemText = ((MenuItem) actionEvent.getSource()).getText();
        CenterOption centerOption = CenterOptionFactory.getCenterOption(menuItemText);
        String exercise = getExercises(centerOption)[0];
        loadNewView(centerOption, exercise);
    }

    public void handleExercisesMenuItemClick(ActionEvent actionEvent) {
        String exercise = ((MenuItem) actionEvent.getSource()).getText();
        loadNewView(this.centerOption, exercise);
    }
}

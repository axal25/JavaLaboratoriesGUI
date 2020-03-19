package ui;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import ui.center.factory.CenterFactory;
import ui.center.factory.CenterOption;
import ui.center.factory.CenterOptionFactory;
import ui.center.options.CenterUIInterface;

import java.net.URL;
import java.util.ResourceBundle;

public class MainUIController implements Initializable {
    public BorderPane mainBorderPane;
    public MenuBar menuBar;

    private CenterUIInterface centerUIInterface;
    private Node centerNode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCenter(CenterOption.HOME);
    }

    public void handleLaboratoriesMenuItemClick(ActionEvent actionEvent) {
        String menuItemText = ((MenuItem) actionEvent.getSource()).getText();
        CenterOption centerOption = CenterOptionFactory.getCenterOption(menuItemText);
        loadCenter(centerOption);
    }

    private void loadCenter(CenterOption centerOption) {
        this.centerUIInterface = CenterFactory.getCenter(centerOption);
        Node newCenterNode = centerUIInterface.getController().getCenterNode();
        this.mainBorderPane.getChildren().removeAll(this.centerNode);
        this.mainBorderPane.setCenter(newCenterNode);
        this.centerNode = newCenterNode;
    }
}

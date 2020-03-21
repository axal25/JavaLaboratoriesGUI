package ui.center.options.home;

import javafx.scene.layout.VBox;
import ui.center.options.AbstractCenterUIController;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeUIController extends AbstractCenterUIController {
    public VBox centerNode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.centerNode = this.centerNode;
    }
}

package ui.center.options;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LambdaExpressionsUIController extends AbstractCenterUIController {
    public VBox centerNode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.centerNode = this.centerNode;
    }
}

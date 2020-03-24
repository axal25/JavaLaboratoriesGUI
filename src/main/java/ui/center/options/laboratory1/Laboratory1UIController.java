package ui.center.options.laboratory1;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import ui.center.options.AbstractCenterUIController;

import java.net.URL;
import java.util.ResourceBundle;

public class Laboratory1UIController extends AbstractCenterUIController {

    public Node centerNode; // just for injection from Laboratory1UI_Introduction.fxml
    public ObjectProperty<Node> centerNodeProperty = new SimpleObjectProperty<>();

    public Laboratory1UIController() {
        super.centerNodeProperty = this.centerNodeProperty;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.centerNodeProperty.set(this.centerNode);
    }
}

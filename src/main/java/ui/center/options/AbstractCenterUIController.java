package ui.center.options;

import javafx.beans.property.ObjectProperty;
import javafx.fxml.Initializable;
import javafx.scene.Node;

public abstract class AbstractCenterUIController implements Initializable {
    public ObjectProperty<Node> centerNodeProperty;
}

package ui.center.options;

import javafx.fxml.Initializable;
import javafx.scene.Node;

public abstract class AbstractCenterUIController implements Initializable {
    Node centerNode;

    public Node getCenterNode() { return this.centerNode; }
}

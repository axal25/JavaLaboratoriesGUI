package ui.center.options;

import javafx.fxml.Initializable;
import javafx.scene.Node;

public abstract class AbstractCenterUIController implements Initializable {
    public Node centerNode;

    public Node getCenterNode() { return this.centerNode; }
}

package ui.center.options.mayeryn.recruitment;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import ui.center.options.AbstractCenterUIController;

import java.net.URL;
import java.util.ResourceBundle;

public class MayerynRecruitmentUIController extends AbstractCenterUIController {
    public VBox centerNode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.centerNode = this.centerNode;
    }
}

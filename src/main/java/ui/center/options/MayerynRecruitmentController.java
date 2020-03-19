package ui.center.options;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MayerynRecruitmentController implements CenterUIControllerInterface {
    public VBox centerVBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @Override
    public Node getCenterNode() {
        return this.centerVBox;
    }
}

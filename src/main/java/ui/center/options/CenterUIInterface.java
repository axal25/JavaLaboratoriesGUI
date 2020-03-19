package ui.center.options;

import java.io.IOException;

public interface CenterUIInterface {
    public void initController() throws IOException;
    public CenterUIControllerInterface getController();
}

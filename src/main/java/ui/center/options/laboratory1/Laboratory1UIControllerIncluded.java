package ui.center.options.laboratory1;

import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import laboratories.lab1.Introduction;

import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

public class Laboratory1UIControllerIncluded implements Initializable {
    public ScrollPane scrollPane;
    public TextArea sourceTextArea1, outputTextArea1, sourceTextArea2, outputTextArea2, sourceTextArea3, outputTextArea3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sourceTextArea1.setText(Introduction.displayText[0]);

        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        Introduction introduction = new Introduction(printStream);
        introduction.firstDemonstration();
        outputTextArea1.setText(out.toString());

        out = new java.io.ByteArrayOutputStream();
        printStream = new PrintStream(out);
        sourceTextArea2.setText(Introduction.displayText[1]);
        introduction = new Introduction(printStream);
        introduction.secondDemonstration();
        outputTextArea2.setText(out.toString());

        out = new java.io.ByteArrayOutputStream();
        printStream = new PrintStream(out);
        sourceTextArea3.setText(Introduction.displayText[2]);
        introduction = new Introduction(printStream);
        introduction.thirdDemonstration();
        outputTextArea3.setText(out.toString());
    }
}

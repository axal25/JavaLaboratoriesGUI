package ui.center.options.laboratory1;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import laboratories.lab1.Exercise1;
import lombok.Getter;
import lombok.Setter;
import ui.pop.up.ErrorPopUpUI;
import utils.lab1.FileOps;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
public class Lab1UIFileShortController implements Initializable {
    public static final Long DEFAULT_SIZE_TO_SHORTEN_THE_FILE_TO = 1024 * 1024L;

    @FXML private JFXTextField sizeToShortenTheFileToTextField;
    @FXML private JFXTextField currentFileSizeTextField;
    @FXML private JFXTextArea outputTextArea;
    @FXML private JFXTextArea sourceTextArea;
    @FXML private JFXButton shortenButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sizeToShortenTheFileToTextField.textProperty().addListener((obsVal, oldText, newText) -> handleSizeToShortenTheFileToTextFieldChanged());
        sizeToShortenTheFileToTextField.setText(String.valueOf(DEFAULT_SIZE_TO_SHORTEN_THE_FILE_TO));
        sourceTextArea.setText(Exercise1.DISPLAY_TEXT[0]);
    }

    public void handleShortenButtonClick() {
        Long sizeToShortenTheFileTo = getSafelySizeToShortenTheFileToTextField();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            PrintStream out = new PrintStream(byteArrayOutputStream);
            Exercise1 exercise1 = new Exercise1(out);
            exercise1.shortenFileCrimes(sizeToShortenTheFileTo);
            currentFileSizeTextField.setText(String.valueOf(FileOps.getFileSizeBytes(Exercise1.EXISTING_FULL_FILE_PATH)));
            outputTextArea.setText(byteArrayOutputStream.toString());
        } catch (Exception e) {
            System.out.println(byteArrayOutputStream.toString());
            e.printStackTrace();
            currentFileSizeTextField.setText("! ERROR !");
            ErrorPopUpUI errorPopUpUI = new ErrorPopUpUI("Error during shortening of file", e.getMessage());
        }
    }

    public void handleSizeToShortenTheFileToTextFieldChanged() {
        try {
            getAndValidateSizeToShortenTheFileToTextField();
            shortenButton.setDisable(false);
        } catch (Exception e) {
            shortenButton.setDisable(true);
        }
    }

    private Long getSafelySizeToShortenTheFileToTextField() {
        try {
            return getAndValidateSizeToShortenTheFileToTextField();
        } catch (Exception e) {
            ErrorPopUpUI errorPopUpUI = new ErrorPopUpUI("Bad size to shorten the file to", "The size you have entered to shorten the file to is invalid.");
            return Long.MAX_VALUE;
        }
    }

    private Long getAndValidateSizeToShortenTheFileToTextField() throws Exception {
        Double sizeToShortenTheFileToDouble = Double.parseDouble((sizeToShortenTheFileToTextField.getText()));
        Long sizeToShortenTheFileToLong = sizeToShortenTheFileToDouble.longValue();
        if(sizeToShortenTheFileToLong < 0) throw new Exception();
        return sizeToShortenTheFileToLong;
    }
}

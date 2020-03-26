package ui.center.options.laboratory1;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.Initializable;
import laboratories.lab1.Exercise1;
import ui.pop.up.ErrorPopUpUI;
import utils.lab1.FileOps;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

public class Laboratory1UIExercise1 implements Initializable {
    public static final Long DEFAULT_SIZE_TO_SHORTEN_THE_FILE_TO = 1024 * 1024L;

    public JFXTextField sizeToShortenTheFileToTextField;
    public JFXTextField currentFileSizeTextField;
    public JFXTextArea outputTextArea;
    public JFXTextArea sourceTextArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sizeToShortenTheFileToTextField.setText(String.valueOf(DEFAULT_SIZE_TO_SHORTEN_THE_FILE_TO));
        sourceTextArea.setText(Exercise1.DISPLAY_TEXT[0]);
    }

    public void handleShortenButtonClick() {
        Long sizeToShortenTheFileTo = getAndValidateDoubleFromSizeToShortenTheFileToTextField();
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

    private Long getAndValidateDoubleFromSizeToShortenTheFileToTextField() {
        try {
            Double sizeToShortenTheFileToDouble = Double.parseDouble((sizeToShortenTheFileToTextField.getText()));
            Long sizeToShortenTheFileToLong = sizeToShortenTheFileToDouble.longValue();
            if(sizeToShortenTheFileToLong <= 0) throw new Exception();
            return sizeToShortenTheFileToLong;
        } catch (Exception e) {
            ErrorPopUpUI errorPopUpUI = new ErrorPopUpUI("Bad size to shorten the file to", "The size you have entered to shorten the file to is invalid.");
            return Long.MIN_VALUE;
        }
    }
}

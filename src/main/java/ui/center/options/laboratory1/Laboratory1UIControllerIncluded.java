package ui.center.options.laboratory1;

import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import laboratories.lab1.Introduction;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

public class Laboratory1UIControllerIncluded implements Initializable {
    public ScrollPane scrollPane;
    public TextArea sourceTextArea1, outputTextArea1, sourceTextArea2, outputTextArea2, sourceTextArea3, outputTextArea3;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.sourceTextArea1.setText("1");
        initializeFields();
    }

    private void initializeFields() {
        Task<ByteArrayOutputStream[]> initializeOutputFieldsTask = new Task<ByteArrayOutputStream[]>() {
            @Override
            protected ByteArrayOutputStream[] call() throws Exception {
                final int length = 3;
                ByteArrayOutputStream[] outs = new ByteArrayOutputStream[length];
                PrintStream[] printStreams = new PrintStream[length];
                Introduction[] introductions = new Introduction[length];
                for (int i = 0; i < length; i++) {
                    outs[i] = new ByteArrayOutputStream();
                    printStreams[i] = new PrintStream(outs[i]);
                    introductions[i] = new Introduction(printStreams[i]);
                    if(i == 0) introductions[0].firstDemonstration();
                    else if(i == 1) introductions[1].secondDemonstration();
                    else if(i == 2) introductions[2].thirdDemonstration();
                    else throw new UnsupportedOperationException("Unsupported demonstration in Introduction - introductions[" + i + "] - where i: " + i + ".");
                }
                return outs;
            }
        };

        initializeOutputFieldsTask.setOnSucceeded(workerStateEvent -> {
            this.outputTextArea1.setText(initializeOutputFieldsTask.getValue()[0].toString());
            this.outputTextArea2.setText(initializeOutputFieldsTask.getValue()[1].toString());
            this.outputTextArea3.setText(initializeOutputFieldsTask.getValue()[2].toString());
        });

        initializeOutputFieldsTask.setOnFailed(workerStateEvent -> {
            initializeOutputFieldsTask.getException().printStackTrace();
            this.outputTextArea1.setText(initializeOutputFieldsTask.getException().getStackTrace().toString());
            this.outputTextArea2.setText(initializeOutputFieldsTask.getException().getStackTrace().toString());
            this.outputTextArea3.setText(initializeOutputFieldsTask.getException().getStackTrace().toString());
        });

        Thread initializeOutputFieldsTaskThread = new Thread(initializeOutputFieldsTask);
        initializeOutputFieldsTaskThread.start();

        initializeSourceField(this.sourceTextArea1, 0);
        initializeSourceField(this.sourceTextArea2, 1);
        initializeSourceField(this.sourceTextArea3, 2);
    }

    private void initializeSourceField(TextArea textArea, int index) {
        Task<String> initializeSourceFieldTask = new Task<String>() {
            @Override
            protected String call() throws Exception {
                return Introduction.DISPLAY_TEXT[index];
            }
        };

        initializeSourceFieldTask.setOnSucceeded(workerStateEvent -> {
            textArea.setText(initializeSourceFieldTask.getValue());
        });

        initializeSourceFieldTask.setOnFailed(workerStateEvent -> {
            initializeSourceFieldTask.getException().printStackTrace();
            textArea.setText(initializeSourceFieldTask.getException().getStackTrace().toString());
        });

        Thread initializeSourceFieldTaskThread = new Thread(initializeSourceFieldTask);
        initializeSourceFieldTaskThread.start();
    }
}

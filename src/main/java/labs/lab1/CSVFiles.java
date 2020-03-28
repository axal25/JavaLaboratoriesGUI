package labs.lab1;

import labs.lab1.exceptions.CopyFileException;
import utils.lab1.exceptions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CSVFiles {
    // file source: https://data.cityofchicago.org/Public-Safety/Crimes-2001-to-present/ijzp-q8t2
    public final static Long SIZE_LIMIT_IN_BYTES = 5L * 1025L * 1024L;
    public final static String EXISTING_FILE_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "csv";
    public final static String EXISTING_FILE_NAME = "Crimes_-_2001_to_present.csv";
    public final static String EXISTING_FULL_FILE_PATH = EXISTING_FILE_PATH + File.separator + EXISTING_FILE_NAME;
    public final static String NOT_EXISTING_FILE_PATH = "NOT/existing/path/to";
    public final static String NOT_EXISTING_FILE_NAME = "notExistingFile.txt";

    public static void main() throws FileReaderCloseException, RenameFileException, BufferedWriterOpenException, GetFileSizeException, IOException, BufferedReaderOpenException, CopyFileException, BufferedReaderCloseException, FileWriterCloseException, FileOpenException, BufferedWriterCloseException, FileInHandlersException, FileWriterOpenException, FileReaderOpenException {
        final String functionName = "main()";
        final String location = CSVFiles.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin( location );

        makeSureUntouchableSourceFileIsRightSize();
        Exercise1.main();
        Exercise2.main();
        Exercise3.main();

        int[] array = new int[3];
        array[0] = 0;
        array[1] = 1;
        array[2] = 2;
        for (int cell : array) {
            System.out.println("cell: " + cell);
        }

        new ArrayList<>(Arrays.asList(array)).forEach((node) -> {
            System.out.println("node: " + node);
        });

        new ArrayList<>(Arrays.asList(array)).forEach(System.out::println);

        Collection<Integer> input = Arrays.asList(10, 20, 30, 40, 50);

        Collector<Object, ?, List<Object>> hex2 = Collectors.toList();
        Collection<String> hex = input.stream()
                .map(Integer::toHexString)
                .collect(Collectors.toList());

        utils.PrintSystem.outEnd( location );
    }

    public static void makeSureUntouchableSourceFileIsRightSize() throws GetFileSizeException, FileReaderOpenException, BufferedWriterOpenException, IOException, BufferedReaderOpenException, RenameFileException, FileOpenException, FileWriterOpenException, CopyFileException {
        CrimesFileOp.cutFileToSize(EXISTING_FULL_FILE_PATH , EXISTING_FULL_FILE_PATH + "_tmp", SIZE_LIMIT_IN_BYTES, false, true, true);
    }
}
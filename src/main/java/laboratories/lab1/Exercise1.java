package laboratories.lab1;

import utils.ExceptionMessageGenerator;
import utils.lab1.FilesOp;

import java.io.BufferedReader;
import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;

// file source: https://data.cityofchicago.org/Public-Safety/Crimes-2001-to-present/ijzp-q8t2

public class Exercise1 {
    public static final boolean doPrintMatchInfo = false;

    public static void main()
    {
        System.out.println("Excercise1.main() \\/\\/\\/");

        System.out.println("Workspace project path (absolute path): " + System.getProperty("user.dir"));
        System.out.println("Should equal to something like: \t\t" +
                "\"/home/jackdaeel/IdeaProjects/JavaProjects/JavaLaboratories\"");

        /** Relative path **/
        // ../../../resources/csv/  Crimes_-_2001_to_present.csv
        //                          Current_Employee_Names__Salaries__and_Position_Titles.csv

        shortenFileCrimes( 1024 );

        File f_crimes = null;

        f_crimes = FilesOp.openExistingFile( Lab1_main.existingFullFilePath );
        f_crimes = FilesOp.openExistingFile( Lab1_main.existingFilePath, Lab1_main.existingFileName );

        int number_of_GTA = count_GTA_in_crimes(f_crimes);
        System.out.println("Count of \"Grand Theft Auto\" crimes = " + number_of_GTA );
        System.out.println("Excercise1.main() /\\/\\/\\");
    }

    public static void shortenFileCrimes(double sizeLimitInKiloB) {
        double fileSizeBytes = Double.MIN_VALUE;
        System.out.println("Excercise1.shortenFileCrimes() \\/\\/\\/");

        String fileName = "Crimes_-_2001_to_present.csv";
        String filePathWithoutName = System.getProperty("user.dir")+"/resources/csv/";
        String filePath = filePathWithoutName + fileName;
        fileSizeBytes = FilesOp.getFileSizeBytes( filePath );
        System.out.println( "file size \"Crimes_-_2001_to_present.csv\"(B)  = " + fileSizeBytes );
        double fileSizeKiloB = FilesOp.sizeBytes2KiloB( fileSizeBytes );
        System.out.println( "file size \"Crimes_-_2001_to_present.csv\"(KB) = " + fileSizeKiloB );
        double fileSizeMegaB = FilesOp.sizeBytes2MegaB( fileSizeBytes );
        System.out.println( "file size \"Crimes_-_2001_to_present.csv\"(MB) = " + fileSizeMegaB );
        double fileSizeGigaB = FilesOp.sizeBytes2GigaB( fileSizeBytes );
        System.out.println( "file size \"Crimes_-_2001_to_present.csv\"(GB) = " + fileSizeGigaB );

        CrimesFileOp.cutFileToSize( filePath, filePathWithoutName + "/" + "smaller_" + fileName, 1024, false, true, true );
        System.out.println("Changed file \"" + filePath + "\" so it's size would be equal to 1024 KiloBytes" + "\n\r" +
                "\t" + "real size = " + FilesOp.sizeBytes2KiloB(FilesOp.getFileSizeBytes( filePath )) + " KB");

        System.out.println("Excercise1.shortenFileCrimes() /\\/\\/\\");
    }

    public static int count_GTA_in_crimes(File f_crimes)
    {
        int number_of_GTA = 0;
        int crime_type_column_numb = 0;

        java.io.FileReader fr_crimes = null;
        fr_crimes = FilesOp.openFileReader(f_crimes);

        BufferedReader br_crimes = null;
        br_crimes = FilesOp.openBufferedReader( fr_crimes );

        crime_type_column_numb = find_matching_column_number(br_crimes, "Primary Type");
        if( crime_type_column_numb != -1 )
        {
            System.out.println("Found column number of \"Primary Type\" = "+crime_type_column_numb+".");
            number_of_GTA = count_matching_patterns_in_column_X(br_crimes, "MOTOR VEHICLE THEFT", crime_type_column_numb);
            System.out.println("Number of \"MOTOR VIHICLE THEFT\" = "+number_of_GTA+".");
        }
        else
        {
            System.out.println("Cound not find column number of \"Primary Type\"");
        }

        FilesOp.closeBufferedReader( br_crimes );
        FilesOp.closeFileReader( fr_crimes );

        return number_of_GTA;
    }

    public static int find_matching_column_number(BufferedReader br_crimes, String pattern)
    {
        String functionName = "find_matching_column_number(BufferedReader br_crimes, String pattern)";
        int columnNumberToReturn = -1;
        int current_column = 1;
        String new_column = null;
        try
        {
            Scanner line = new Scanner(br_crimes.readLine());
            line.useDelimiter(",");
            while(line.hasNext())
            {
                new_column = line.next();
                if( new_column.compareTo(pattern) == 0 )
                {
                    columnNumberToReturn = current_column;
                }
                current_column++;
            }
        }
        catch(Exception e)
        {
            String exceptionMessage = ExceptionMessageGenerator.getMessage(Exercise1.class.getName(), functionName, e);
            System.out.println(exceptionMessage);
            return -1;
        }

        return columnNumberToReturn;
    }

    public static int count_matching_patterns_in_column_X(BufferedReader br_crimes, String pattern, int column_number)
    {
        String functionName = "count_matching_patterns_in_column_X(BufferedReader br_crimes, String pattern, int column_number)";
        int crimeCounter = 0;
        Scanner current_line = null; //Obecnie przetwarzany wiersz
        String buffer =  null; //Buffer, potrzebny, by current_line = new Scanner( null ) nie wywalal Exception
        int column_counter = 0; //Licznik kolumny w wierszu (porownuje ten licznyk z numerem columny podanym w argumencie metody)
        String current_column = null; //Przechowuje zawartosc obecnie przetwarzanej kolumny (w danym wierszu)
        String stringRegExpPattern = new StringBuilder().append("(.)*").append(pattern).append("(.)*").toString();
        Pattern regExpPattern = Pattern.compile( stringRegExpPattern );
        try
        {
            buffer = br_crimes.readLine();
            while( buffer !=  null )
            {
                current_line = new Scanner(buffer);
                current_line.useDelimiter( Pattern.compile(
                        "(?x)" +                        // ignore white spaces in regex
                        "," +                           // Comma char == ','
                                "(?=" +                 // Followed by - Positive lookahead - Next regex matches
                                "   (?:" +              // Non-capture group
                                "       [^\"]*" +     // 0 or more, NON-quote chars
                                "       \"" +         // 1 quote char
                                "       [^\"]*" +     // 0 or more, NON-quote chars
                                "       \"" +         // 1 quote char
                                "   )*" +               // 0 or more, repetitions of, chain of NON-quote and (even amount of) quote chars
                                "   [^\"]*" +         // 0 or more, NON-quote chars
                                "   $" +                // Followed by END - necessary - every comma would satisfy the condition
                                ")"
                ) );
                // https://regex101.com/r/lS5tT3/243
                // source: https://stackoverflow.com/questions/18893390/splitting-on-comma-outside-quotes
                column_counter = 0;
                while( current_line.hasNext() )
                {
                    current_column = current_line.next();
                    column_counter++;
                    if(column_counter == column_number)
                    {
                        if( doPrintMatchInfo ) System.err.print("current_column = \"" + current_column + "\" vs. pattern = \"" + pattern + "\"" );
                        if( regExpPattern.matcher( current_column ).matches() )
                        {
                            if( doPrintMatchInfo ) System.err.println(" +++ MATCHED");
                            crimeCounter++;
                        }
                        else {
                            if( doPrintMatchInfo ) System.err.println(" --- NOT matched");
                        }
                    }

                }
                current_line.close();
                buffer = br_crimes.readLine();
            }
        }
        catch(Exception e)
        {
            String exceptionMessage = ExceptionMessageGenerator.getMessage(Exercise1.class.getName(), functionName, e);
            System.out.println(exceptionMessage);
        }

        return crimeCounter;
    }

}

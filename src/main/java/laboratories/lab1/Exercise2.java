package laboratories.lab1;

import utils.ExceptionMessageGenerator;
import utils.lab1.FilesOp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

// file source: https://data.cityofchicago.org/Public-Safety/Crimes-2001-to-present/ijzp-q8t2

public class Exercise2 {
    // should be 2019 if file wasn't updated
    public static final int currentYear = Calendar.getInstance().get(Calendar.YEAR);

    public static void main()
    {
        System.out.println("Excercise2.main() \\/\\/\\/");

        File f_crimes = null;
        f_crimes = FilesOp.openExistingFile( Lab1_main.existingFullFilePath );
        int number_of_all_entries = count_all_entries( f_crimes );
        int number_of_crimes_in_year_X = count_crimes_in_year_X( Exercise2.currentYear, f_crimes );

        System.out.println("Excercise2.main() /\\/\\/\\");
    }

    public static int count_all_entries( File f_crimes ) {
        FileReader fileReader = FilesOp.openFileReader(f_crimes);
        BufferedReader bufferedReader = FilesOp.openBufferedReader( fileReader );
        int numberOfAllEntries = Exercise1.count_matching_patterns_in_column_X(bufferedReader, "", 1);
        System.out.println( "Number of all entries in file (including column titles as entry too) = " + numberOfAllEntries );
        return numberOfAllEntries;
    }

    public static int count_crimes_in_year_X(int year, File f_crimes)
    {
        String functionName = "count_crimes_in_year_2015(File f_crimes)";
        int number = 0;
        int year_column_numb = 0;
        int number_of_crimes_in_X = 0;

        FileReader fr_crimes = null;
        fr_crimes = FilesOp.openFileReader(f_crimes);

        BufferedReader br_crimes = null;
        br_crimes = new BufferedReader(fr_crimes);

        year_column_numb = Exercise1.find_matching_column_number(br_crimes, "Year");
        if( year_column_numb != 0 )
        {
            System.out.println("Found number of column \"Year\" = "+year_column_numb+".");
            number_of_crimes_in_X = Exercise1.count_matching_patterns_in_column_X(br_crimes, String.valueOf(year), year_column_numb);

            // year is calculated based on current year (real world year at excecution time)
            // should be 2019 if file wasn't updated
            if( number_of_crimes_in_X == 0 ) {
                year = 2019;
                number_of_crimes_in_X = Exercise1.count_matching_patterns_in_column_X(br_crimes, String.valueOf(year), year_column_numb);
            }
            System.out.println("Number of crimes in year " + year + " = " + number_of_crimes_in_X + ".");
        }
        else
        {
            System.out.println("Could not find number of column \"Year\".");
        }

        // closing br_crimes, fr_crimes
        try
        {
            br_crimes.close();
        }
        catch (IOException e)
        {
            String exceptionMessage = ExceptionMessageGenerator.getMessage(Exercise2.class.getName(), functionName, e);
            System.out.println(exceptionMessage);
        }
        FilesOp.closeFileReader(fr_crimes);

        return number;
    }
}

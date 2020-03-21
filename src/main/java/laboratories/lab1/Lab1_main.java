package laboratories.lab1;

public class Lab1_main
{
    public final static String existingFilePath = System.getProperty("user.dir")+"/src/main/resources/csv";
    public final static String existingFileName = "Crimes_-_2001_to_present.csv";
    public final static String existingFullFilePath = existingFilePath + "/" + existingFileName;
    public final static String notExistingFilePath = "NOT/existing/path/to";
    public final static String notExistingFileName = "notExistingFile.txt";
    public final static String notExistingFullFilePath = notExistingFilePath + "/" + notExistingFileName;

    public static void homeExercises()
    {
        final String functionName = "homeExercises()";
        final String location = Lab1_main.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin( location );

        Exercise1.main();
        Exercise2.main();
        Exercise3.main();

        utils.PrintSystem.outEnd( location );
    }

}
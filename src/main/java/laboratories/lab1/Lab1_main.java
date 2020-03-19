package laboratories.lab1;

public class Lab1_main
{
    public final static String existingFilePath = System.getProperty("user.dir")+"/src/main/resources/csv";
    public final static String existingFileName = "Crimes_-_2001_to_present.csv";
    public final static String existingFullFilePath = existingFilePath + "/" + existingFileName;
    public final static String notExistingFilePath = "NOT/existing/path/to";
    public final static String notExistingFileName = "notExistingFile.txt";
    public final static String notExistingFullFilePath = notExistingFilePath + "/" + notExistingFileName;

    public static void main()
    {
        final String functionName = "main()";
        final String location = Lab1_main.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin( location );

        String empty_String;
        empty_String = "";

        String[] empty_String_tab;
        empty_String_tab = new String[0];
        //empty_String_tab[0] = empty_String; //empty_String_tab ma zarezerwowana pamiec na 0 Stringow

        empty_String_tab = new String[1];
        empty_String_tab[0] = empty_String; //pierwsza komorka ma indeks 0
        main(empty_String_tab);

        utils.PrintSystem.outEnd( location );
    }

    public static void main(String[] args)
    {
        final String functionName = "main(String[] args)";
        final String location = Lab1_main.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin( location );

        baseExercises();
        System.out.println();
        homeExercises();

        utils.PrintSystem.outEnd( location );
    }

    public static void baseExercises()
    {
        final String functionName = "baseExercises()";
        final String location = Lab1_main.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin( location );

        int i1;
        i1 = 1;
        i1 = new Integer(1);
        int i2;
        i2 = 2;
        i2 = new Integer(2);
        i2 = i1;
        //i2 = i1.clone(); //Nie mozna klonowac prymitywnych typow

        Object o1;
        o1 = new Object();
        Object o2;
        o2 = null;
        o2 = o1;
        //o2 = o2.clone(); //Metoda clone nie jest widoczna z typu Object

        Abc abc1;

        abc1 = null;
        abc1 = new Abc();
        Abc abc2;
        abc2 = abc1;
        Abc abc3;
        abc3 = abc1.clone();


        Matrix.main();

        int i = 1;
        Object o = new Object();
        Matrix _m = new Matrix();
        _m.doSomething(i, o);

        utils.PrintSystem.outEnd( location );
    }

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
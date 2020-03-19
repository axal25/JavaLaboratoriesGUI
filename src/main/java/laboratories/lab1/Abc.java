package laboratories.lab1;

public class Abc
{

    /**
     * @param args
     */
    public Abc()
    {

    }

    public Abc clone()
    {
        System.out.println("abc.clone()");
        return new Abc();
    }

}
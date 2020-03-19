package laboratories.lab1;

/** Pierwsza linia komentarza to krótki opis.
 *
 * <p>Dalsza część jest już rozszerzonym opisem. Gdzie powinien
 * się znaleźć szczegółowy opis klasy. Komentarz Javadoc może
 * zawierać znaczniki <em>html</em></p>
 *
 *
 * <p>Poniżej znajduje się specjalny znacznik javadoc informujący o tym
 * kto jest autorem poniższego kodu</p>
 * @author Autor
 * @version wersja
 */

public class Matrix
{

    /**
     *
     */
    public Matrix()
    {

    }


    /** To jest opis metody.
     * Metoda ta przyjmuje dwa parametry
     * i coś zwraca.
     * @param int_arg podstawowy argument
     * @param obj drugi argument
     * @return to będzie zwórcone przez funkcję
     */
    public int doSomething(int int_arg,Object obj)
    {
        System.out.println("Matrix.doSomething(int_arg, obj)");
        return 0;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println("Matrix.main(args)");
    }

    /** Metoda main bezargumentowa (domyslna)
     *
     */
    public static void main()
    {
        System.out.println("Matrix.main()");
        String[] args = new String[0];
        main(args);
    }

}
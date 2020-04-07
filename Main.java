import java.io.*;
import java.util.*;
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main {
    private static String add="Data\\";
    private char[] commonLetters={'E','A','R','I'};
    public static void main(String[] args)throws IOException
    {
        System.out.println("--------  Welcome  --------\n");
        vigenereBreaker vb=new vigenereBreaker();
        while(true)
        {
            System.out.println("Available Files : ");
            File f = new File(add);
            for (File file:f.listFiles())
                System.out.println(" -- " + file);
            System.out.println("Enter file name to decrypt:");
            Scanner in = new Scanner(System.in);
            String fname=in.next();
            vb.breakVigenere(fname);
            System.out.println("\n---To exit type exit else continue");
            String cho=in.next().toLowerCase();
            if(cho.equals("exit"))
                break;
        }
    }
}

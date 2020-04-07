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
            System.out.println("Enter Choice : \n 1. Encrypt \n 2. Decrypt \n 3. Exit");
            Scanner in = new Scanner(System.in);
            int cho = in.nextInt();
            switch(cho)
            {
                case 1:
                    System.out.println("Available Files : ");
                    File fe = new File(add);
                    for (File file:fe.listFiles())
                        System.out.println(" -- " + file);
                    System.out.println("Enter file name :");
                    String fnamee=in.next();
                    System.out.println("Enter Key : ex - Flute");
                    String key=in.next();
                    System.out.println("Enter most common letter : ex - For English - 'e' ");
                    char c=in.next().charAt(0);
                    vigenereCipher vc=new vigenereCipher(key,c);
                    caesarCipher cc=new caesarCipher();
                    cc.textToFile(vc.encrypt(cc.textFromFile(fnamee)),"result.txt");
                    break;
                case 2:
                    System.out.println("Available Files : ");
                    File fd = new File(add);
                    for (File file:fd.listFiles())
                        System.out.println(" -- " + file);
                    System.out.println("Enter file name :");
                    String fnamed=in.next();
                    vb.breakVigenere(fnamed);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Wrong Choice !!!!");
                    continue;    
            }
        }
    }
}

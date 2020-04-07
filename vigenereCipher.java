
/**
 * This class implements vigenereCipher here.
 * This class also make use of caesarCipher class.
 * @author ( imasy36 ) 
 * @version ( 04/03/2020 )
 */
public class vigenereCipher 
{
    private int[] key;
    public caesarCipher cC;
    private String alphabets="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private char commonLetter;
    public vigenereCipher(String k,char c)
    {
        cC=new caesarCipher();
        k=k.toUpperCase();
        commonLetter = c;
        key=new int[k.length()];
        for(int i=0;i<k.length();i++)
        {
            key[i]=alphabets.indexOf(k.charAt(i));
        }
    }
    public String encrypt(String text)
    {
        String res="";
        int  i=0;
        for(char c : text.toCharArray())
        {
            int index = i % key.length;
            String shifted_alpha=alphabets.substring(key[index])+alphabets.substring(0,key[index]);
            res=res+cC.cipher(shifted_alpha,c);
            i++;
        }
        return res;
    } 
    public String decrypt(String text)
    {
        String res="";
        int  i=0;
        for(char c : text.toCharArray())
        {
            int index = i % key.length;
            String shifted_alpha=alphabets.substring(26-key[index])+alphabets.substring(0,26-key[index]);
            res=res+cC.cipher(shifted_alpha,c);
            i++;
        }
        return res;
    }
    public String toString()
    {
        String s="";
        for(int i=0;i<key.length;i++)
            s=s+alphabets.charAt(key[i]);
        return s;
    }
}

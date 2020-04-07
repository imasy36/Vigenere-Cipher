import java.io.*;
import java.util.*;
/**
 * Caesar Cipher is a technique used for encryption .
 * 
 * @author ( imasy36 ) 
 * @version ( 31 - 03 - 2020 )
 */
public class caesarCipher {
    private String alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String add="Data/";
    public String textFromFile(String fname)throws IOException
    {
        BufferedReader br=new BufferedReader(new FileReader(add+fname));
        String s="";
        String line="";
        while((line=br.readLine())!=null)
            s=s+line+"\n"; 
        return s;   
    }
    
    public void textToFile(String data,String fname)throws IOException
    {
        BufferedWriter bw=new BufferedWriter(new FileWriter(add+fname));
        bw.write(data,0,data.length());
        System.out.println("Result stored in " + (add+fname) );  
        bw.close();
    }
    
    public char cipher(String ci_alpha,char ch)
    {
        if(Character.isLowerCase(ch))
        {
            alpha=alpha.toLowerCase();
            ci_alpha=ci_alpha.toLowerCase();
        }
        else
        {
            alpha=alpha.toUpperCase();
            ci_alpha=ci_alpha.toUpperCase();
        }
        int index=alpha.indexOf(ch);
        if(index!=-1)
            return ci_alpha.charAt(index);
        else
            return ch;            
    }
    
    public String encrypt(String data,int key)
    {
        String cipher=new String();
        String ci_alpha=alpha.substring(key)+alpha.substring(0,key);
        for(int i=0;i<data.length();i++)
        {
            cipher+=cipher(ci_alpha,data.charAt(i));
        }
        data=data.toUpperCase();
        return cipher;
    }
    
    private int [] countLetters(String data)
    {
        int[] count=new int[26];
        data=data.toUpperCase();
        alpha=alpha.toUpperCase();
        for(int i=0;i<data.length();i++)
        {
            int index=alpha.indexOf(data.charAt(i));
            if(index!=-1)
                count[index]++;
        }
        return count;
    }
    
    public int maxIndex(int[] x)
    {
        int index=0;
        for(int i =1;i<x.length;i++)
        {
            if (x[i]>x[index])
                index=i;
        }
        return index;
    }
    
    public int getKey(String data,char commonLetter)
    {
        int [] c= countLetters(data);    
        int index=maxIndex(c);
        int pos=alpha.indexOf(commonLetter);
        int key=index-pos;
        if (index<pos)
            key=26-(pos-index);
        return key;
    }
    
    public String decrypt(String data,char commonLetter) 
    {
        int key=getKey(data,commonLetter);    
        return encrypt(data,26-key);
    }
}

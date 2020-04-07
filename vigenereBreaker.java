import java.io.*;
import java.util.*;
/**
 * This class breaks the vigenere cipher .
 * 
 * @author ( imasy36 ) 
 * @version ( 04 - 04 - 2020 )
 */
public class vigenereBreaker {
    private String add="Data/";
    private String alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private caesarCipher cc=new caesarCipher(); 
    // Used to Slice String into required amount of parts
    public String sliceString(String text,int whichSlice,int totalSlices)
    {
        String res="";
        for(int i=whichSlice;i<text.length();i+=totalSlices)
        {
            res=res+text.charAt(i);
        }
        return res;
    }
    // Finds Keys for a given keyLength
    public int[] tryKeyLength(String text,int keyLength,char commonLetter)
    {
        int[] key=new int[keyLength];
        String[] slices=new String[keyLength];
        for(int i=0;i<keyLength;i++)
        {
            slices[i]=sliceString(text,i,keyLength);
            key[i]=cc.getKey(slices[i],commonLetter);
        }
        return key;
    }
    // Main method to break cipher
    public void breakVigenere(String fname)throws IOException
    {
        String data=cc.textFromFile(fname);
        System.out.println(" -- Reading file - done ");
        HashMap<String,HashSet<String>> s = new HashMap<String,HashSet<String>>();
        s=getDictionaries();
        System.out.println(" -- Reading all dictionaries - done ");
        String res=breakForAllLangs(data,s);
        cc.textToFile(res,"result.txt");
    }
    public String intToString(int[] key)
    {
        String re="";
        for(int i=0;i<key.length;i++)
        {
            re+=alpha.charAt(key[i]);
        }
        return re;
    }
    public HashMap<String,HashSet<String>> getDictionaries()throws IOException
    {
        String fname=add+"dictionaries/";
        File f = new File(fname);
        HashMap<String,HashSet<String>> dict=new HashMap<String,HashSet<String>>();
        for ( File file : f.listFiles())
        {
            BufferedReader bf=new BufferedReader(new FileReader(file));
            String line="";
            fname=file.getName();
            dict.put(fname,new HashSet<String>());
            while((line=bf.readLine())!=null)
            {
                HashSet<String> temp= dict.get(fname);
                temp.add(line.toLowerCase());
                dict.replace(fname,temp);
            }
        }
        return dict;
    }
    public int countWords(HashSet<String> mySet,String text)
    {
        int count=0;
        for(String word : text.split("\\W"))
        {
            word=word.toLowerCase();
            if(mySet.contains(word))
                count++;
        }
        return count;
    }
    public char mostCommonCharIn(HashSet<String> mySet)
    {
        int[] cnt=new int[26];
        int ch;
        char res;
        for(String word:mySet)
        {
            word=word.toUpperCase();
            for(int j =0 ; j<word.length() ; j++ )
            {
                ch=alpha.indexOf(word.charAt(j));
                if(ch!=-1)
                    cnt[ch]++;
            }
        }
        int m=cc.maxIndex(cnt);
        res=alpha.charAt(m);
        return res;
    }
    public String breakForAllLangs(String text,HashMap<String,HashSet<String>> dict)
    {
        int[] keyL=new int[dict.size()];
        int[] cnt=new int[dict.size()];
        String[] ln=new String[dict.size()];
        char[] cL=new char[dict.size()];
        int i=0;
        for(String lang:dict.keySet())
        {
            HashSet<String> temp= dict.get(lang);
            int[] counts=new int[100];
            cL[i]= mostCommonCharIn(temp);
            for (int keyLength=1;keyLength<8;keyLength++)
            {
                String key = intToString(tryKeyLength(text,keyLength,cL[i]));
                vigenereCipher vc=new vigenereCipher(key,cL[i]);
                String tmp=vc.decrypt(text);
                counts[keyLength-1]=countWords(temp,tmp);
            }
            keyL[i]=cc.maxIndex(counts)+1;
            cnt[i]=counts[keyL[i]-1];
            ln[i]=lang;
            i++;
        }
        int m=cc.maxIndex(cnt);
        String final_key=intToString(tryKeyLength(text,keyL[m],cL[m]));
        vigenereCipher vc=new vigenereCipher(final_key,cL[m]);
        System.out.println("Language found : " + ln[m]);
        System.out.println("Final Key : " + final_key);
        return vc.decrypt(text);
    }
}

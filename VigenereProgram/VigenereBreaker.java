import java.util.*;
import edu.duke.*;
import java.io.*;
public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        String[] slices = new String[klength];
        CaesarCracker ck =new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++){
            String n = sliceString(encrypted, i, klength);
            
            key[i] = ck.getKey(n);
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dic = new HashSet<String>();
        for (String w : fr.lines()){
            dic.add(w.toLowerCase());
        }
        return dic;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        for (String w : message.split("\\W+")){
            if (dictionary.contains(w.toLowerCase())){
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int max = 0;
        int len = 0;
        String decrypted = null;
        Character c = mostCommonCharIn(dictionary);
        for (int i=1; i <=100; i++){
            int[] keys = tryKeyLength(encrypted, i, c);
            VigenereCipher vc = new VigenereCipher(keys);
            String answer = vc.decrypt(encrypted);
            int count = countWords(answer,dictionary);
            if (count > max){
                max = count;
                len = i;
                decrypted = answer;
            }
        }
        System.out.println("len:"+len+ " num:" + max);
        return decrypted;
    }
    
    public Character mostCommonCharIn(HashSet<String> dictionary){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(String w : dictionary){
            for(int k=0; k < w.length(); k++){
                int dex = alph.indexOf(Character.toLowerCase(w.charAt(k)));
                if (dex != -1){
                    counts[dex] += 1;
                }
            }
        }
        int maxDex = 0;
        for(int k=0; k < counts.length; k++){
            if (counts[k] > counts[maxDex]){
                maxDex = k;
            }
        }
        return alph.charAt(maxDex);
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){
        int max = 0;
        String answer = null;
        String lang = null;
        for(String name:languages.keySet()){
            HashSet<String> dic = languages.get(name);
            String message=breakForLanguage(encrypted,dic); //decrypted try
            int count=countWords(message,dic);//eligible words - find max
            if (count > max){
                max = count;
                answer = message;
                lang = name;
            }
        }
        System.out.println(lang + ": " + max);
        System.out.println(answer);
        return answer;
    }
    
    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        HashMap<String,HashSet<String>> langs = new HashMap<String,HashSet<String>>();
        for (File f : dr.selectedFiles()) {
            FileResource fr_e = new FileResource(f);
            HashSet<String> dic = readDictionary(fr_e);
            langs.put(f.getName(), dic);
        }
        
        breakForAllLangs(encrypted, langs);
        
        //int[] keys = tryKeyLength(encrypted, 4, 'e');
        //VigenereCipher vc = new VigenereCipher(keys);
        //System.out.println(breakForLanguage(encrypted,dic));
    }
    
}


/**
 * Write a description of test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.Arrays;
public class test {
    public void ccdecrypt(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        CaesarCracker ck =new CaesarCracker('a');
        
        
        System.out.println( ck.decrypt(encrypted));
        
    }
    public void vcencrypt(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int[] key = {17,14,12,4};
        VigenereCipher vc = new VigenereCipher(key);

        System.out.println( vc.encrypt(encrypted));
        
    }
    
    public void trykeylength(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        VigenereBreaker vb = new VigenereBreaker();
        int[] keys = vb.tryKeyLength(encrypted,4,'e');
        System.out.println(Arrays.toString(keys));
        
    }
}

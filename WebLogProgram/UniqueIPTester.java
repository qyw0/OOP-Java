
/**
 * Write a description of UniqueIPTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UniqueIPTester {
    public void testunique(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniIPs = la.countUniqueIPs();
        System.out.println(uniIPs);
    }
}


/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testhigherthanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> IPs = la.uniqueIPVisitsOnDay("Sep 24");
        System.out.println(IPs.size());
        IPs.forEach(System.out::println);
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println(la.countUniqueIPsInRange(200,299));
    }
    
    public void testCountVisitsperIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log"); 
        HashMap<String, Integer> counts = la.countVisitsperIP();
        System.out.println(counts);
    }
    
    public void testMaxVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log"); 
        HashMap<String, Integer> counts = la.countVisitsperIP();
        int max = la.mostNumberVisitsByIP(counts);
        System.out.println(max);
    }
    
    public void testIPsMostVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log"); 
        HashMap<String, Integer> counts = la.countVisitsperIP();
        ArrayList l = la.iPsMostVisits(counts);
        l.forEach(System.out::println);
    }
    
    public void testIPsForDays(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log"); 
        HashMap<String, ArrayList<String>> days = la.iPsForDays();
        System.out.println(days);
    }
    
    public void testDayWithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log"); 
        HashMap<String, ArrayList<String>> days = la.iPsForDays();
        String max = la.dayWithMostIPVisits(days);
        System.out.println(max);
    }
    
    public void testIPsWithMostVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log"); 
        HashMap<String, ArrayList<String>> days = la.iPsForDays();
        ArrayList<String> iPs = la.iPsWithMostVisitsOnDay(days, "Sep 30");
        iPs.forEach(System.out::println);
    }
    
}


/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String r : fr.lines()){
            LogEntry entry = WebLogParser.parseEntry(r);
            records.add(entry);
        }
     }
     
     public int countUniqueIPs(){
         //HashMap<String, Integer> counts = countVisitsperIP();
         //return counts.size();
         ArrayList<String> IPs = new ArrayList<String>();
         for (LogEntry le : records){
             String addr = le.getIpAddress();
             if (! IPs.contains(addr)){
                 IPs.add(addr);
                }
            }
         return IPs.size();
        }
     
     public void printAllHigherThanNum(int num){
         for (LogEntry le : records){
             int code = le.getStatusCode();
             if (code > num){
                System.out.println(le);
                }
            }
        }
        
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> IPs = new ArrayList<String>();
         for (LogEntry le : records){
             String d = le.getAccessTime().toString();
             String addr = le.getIpAddress();
             if (d.contains(someday) && ! IPs.contains(addr)){
                 IPs.add(addr);
                }
            }
         return IPs;
        }

     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> IPs = new ArrayList<String>();
         for (LogEntry le : records){
             int code = le.getStatusCode();
             String addr = le.getIpAddress();
             if (code >= low && code <= high && !IPs.contains(addr)){
                IPs.add(addr);
                }
            }
         return IPs.size();
        }
        
     public HashMap<String, Integer> countVisitsperIP(){
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for (LogEntry le : records){
             String addr = le.getIpAddress();
             if (!counts.containsKey(addr)){
                 counts.put(addr, 1);
                }else{
                    counts.put(addr, counts.get(addr) + 1);
                }
            }
         return counts;
        }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> map){
         int max = 0;
         for (int i : map.values()){
             if (i > max){
                 max = i;
                }
            }
         return max;
        }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map){
         ArrayList<String> IPs = new ArrayList<String>();
         for (String k : map.keySet()){
             if (map.get(k) == mostNumberVisitsByIP(map)){
                 IPs.add(k);
                }
            }
         return IPs;
        }
     
     public HashMap<String, ArrayList<String>> iPsForDays(){
         HashMap<String, ArrayList<String>> days = new HashMap<String, ArrayList<String>>();
         for (LogEntry le : records){
             String d = le.getAccessTime().toString().substring(4,10);
             String addr = le.getIpAddress();
             if (!days.containsKey(d)){
                 ArrayList<String> n = new ArrayList<String>();
                 n.add(addr);
                 days.put(d,n);
                }else{
                    days.get(d).add(addr);
                }
            }
         return days;
        }
        
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> days){
         String day = null;
         int max = 0;
         for (String i : days.keySet()){
             if (days.get(i).size() > max){
                 max = days.get(i).size();
                 day = i;
                }
            }
         return day;
        }
        
     public HashMap<String, Integer> countVisitsperIPFromList(ArrayList<String> ipList){
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for (String addr : ipList){
             
             if (!counts.containsKey(addr)){
                 counts.put(addr, 1);
                }else{
                    counts.put(addr, counts.get(addr) + 1);
                }
            }
         return counts;
        }
      public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> counts,String date){
        ArrayList<String> ipList= new ArrayList<String>();

        for(String day:counts.keySet()){
            if(day.equals(date)){
                ipList=counts.get(day);
            }
        }
        HashMap<String,Integer> count=countVisitsperIPFromList(ipList);
        Integer most = mostNumberVisitsByIP(count);
        ArrayList<String> mostVisit=iPsMostVisits(count);
        return mostVisit;
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}

//Priti Rangnekar
//ACSL Intermediate Contest 4: Duplicates
//April 11, 2018
//BISV, 10th Grade

import java.util.Scanner;
import java.util.ArrayList;

public class acslduplicates{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String curr = "";
    ArrayList<String> letters = new ArrayList<String>();
    ArrayList<Integer> counters = new ArrayList<Integer>();
    String[] reporters = new String[26];
      
    while (sc.hasNextLine()){
      curr = sc.nextLine().toUpperCase();
      
      if (curr.substring(0,5).equals("RESET")){ // LINES STARTING WITH RESET
        letters.clear(); //clear letters
        counters.clear(); //clear counters
        for (int i = 0; i<reporters.length; i++){ //set everything in reporters to ""
            reporters[i] = "";
        }
        String sub = "";
        for (int i = 6; i<curr.length(); i++){
            sub = curr.substring(i, i+1);
            if (!(sub.equals(" "))){
                if (i == 6){ //first letter being added
                    letters.add(sub);
                    counters.add(1);
                    for (int p = 0; p<letters.size(); p++){
                           reporters[p] = reporters[p] + letters.get(p);
                    }
                }
                else if (i>6){ //not first letter being added
                    if (letters.indexOf(sub) == -1){ //if not already in letters list
                         int size = letters.size();
                         for (int j = 0; j<size; j++){ //go through current array while comparing to each letter
                             int comp = sub.compareTo(letters.get(j));
                             if (comp < 0){
                                letters.add(j, sub);
                                counters.add(j, 1);
                                for (int p = 0; p<letters.size(); p++){
                                   if (reporters[p].length() > 0 && (!(reporters[p].substring(reporters[p].length()-1).equals(letters.get(p)))) || reporters[p].length() == 0){
                                       reporters[p] = reporters[p] + letters.get(p);
                                   }
                                }
                                break;
                             }
                             else if (comp > 0 && j == size-1){
                                letters.add(j+1, sub);
                                counters.add(j+1, 1);
                                for (int p = 0; p<letters.size(); p++){
                                   if (reporters[p].length() > 0 && (!(reporters[p].substring(reporters[p].length()-1).equals(letters.get(p)))) || reporters[p].length() == 0){
                                       reporters[p] = reporters[p] + letters.get(p);
                                   }
                                }
                                break;
                             }
                         }
                    }
                    else{ //already in letters list
                        int ind = letters.indexOf(curr.substring(i, i+1));
                        counters.set(ind, counters.get(ind) + 1);
                    }
                }
            }
        }
      }
     
     if (curr.substring(0,3).equals("ADD")){ //LINES STARTING WITH ADD
         String sub = "";
         for (int i = 4; i<curr.length(); i++){
            sub = curr.substring(i, i+1);
             if (!(sub.equals(" "))){
                 if (letters.indexOf(sub) == -1){ //if not already in letters list
                    int size = letters.size();
                    for (int j = 0; j<size; j++){ //go through current array while comparing to each letter
                        int comp = sub.compareTo(letters.get(j));
                        if (comp < 0){
                            letters.add(j, sub);
                            counters.add(j, 1);
                            for (int p = 0; p<letters.size(); p++){
                               if (reporters[p].length() > 0 && (!(reporters[p].substring(reporters[p].length()-1).equals(letters.get(p)))) || reporters[p].length() == 0){
                                       reporters[p] = reporters[p] + letters.get(p);
                               }
                            }
                            break;
                        }
                        else if (comp > 0 && j == size-1){
                            letters.add(j+1, sub);
                            counters.add(j+1, 1);
                            for (int p = 0; p<letters.size(); p++){
                               if (reporters[p].length() > 0 && (!(reporters[p].substring(reporters[p].length()-1).equals(letters.get(p)))) || reporters[p].length() == 0){
                                       reporters[p] = reporters[p] + letters.get(p);
                               }
                            }
                            break;
                        }
                    }
                }
                else{ //already in letters list
                    int ind = letters.indexOf(curr.substring(i, i+1));
                    counters.set(ind, counters.get(ind) + 1);
                }
             }
         }
     }
    if (curr.substring(0,6).equals("DELETE")){ //LINES STARTING WITH DELETE
        String sub = "";
        for (int i = 7; i<curr.length(); i++){
            sub = curr.substring(i, i+1);
            if (!(sub.equals(" "))){
                int ind = letters.indexOf(curr.substring(i, i+1));
                if (ind >= 0){
                    counters.set(ind, counters.get(ind) - 1);
                    if (counters.get(ind) == 0){
                        counters.remove(ind);
                        letters.remove(ind);
                        for (int p = 0; p<letters.size(); p++){
                               if (reporters[p].length() > 0 && (!(reporters[p].substring(reporters[p].length()-1).equals(letters.get(p)))) || reporters[p].length() == 0){
                                       reporters[p] = reporters[p] + letters.get(p);
                               }
                        }
                    }
                }
            }
         }
    }
    
   if (curr.substring(0,6).equals("REPORT")){ //LINES STARTING WITH REPORT
       String where = curr.substring(7);
       int whereint = Integer.parseInt(where);
       System.out.println(reporters[whereint-1]);
   }
        
        
      
        
     
    }
   
 

//these two close main and class      
}      
}

/*Input 1:
RESET abracadabracabob
REPORT 3
REPORT 5
ADD BATH
DELETE boa
REPORT 5
DELETE drr
REPORT 5
RESET American Computer Science League
ADD Computer
DELETE Computer
DELETE COMPUTER
REPORT 10
/*Output 1:
RC 
RO 
ROH 
ROHRT 
UTSRPRS 

/*Input 2:
RESET simple simon
REPORT 4
ADD simply said something slowly
REPORT 4
DELETE so say something
REPORT 4
RESET peter piper picked a peck of pickled
REPORT 7
DELETE pickled
DELETE sunflowers
ADD pickled
ADD sunflowers
REPORT 5
/*Output 2:
SPM 
SPMLIHG 
SPMLIHGHIL 
TRPOK 
TRPKIFIF 

*/


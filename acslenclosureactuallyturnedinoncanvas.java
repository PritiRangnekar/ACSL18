import java.io.*;
import java.util.*;

//Priti Rangnekar 2/20/2018
//BASIS Independent Silicon Valley
//ACSL Intermediate Contest 2: ACSL Enclosure


public class acslenclosureactuallyturnedinoncanvas{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    while (sc.hasNextLine()){
        String line = sc.nextLine();
        boolean b1 = false;
        boolean b2 = false;
        boolean p1 = false;
        boolean p2 = false;
        if (line.indexOf("[") >= 0 && line.indexOf("]")  < 0){
          b2 = true;
          btwo(line);
        }
        else if (line.indexOf("]") >= 0 && line.indexOf("[")  < 0){
          b1 = true;
          bone(line);
        }
        else if (line.indexOf("(") >= 0 && line.indexOf(")")  < 0){
          p2 = true;
          ptwo(line);
        }
        else if (line.indexOf(")") >= 0 && line.indexOf("(")  < 0){
          p1 = true;
          pone(line);
        }
        //System.out.println(b1 + " " + b2 + " " + p1 + " " + p2);
    }
  }
    
    
    ///////////////////////////////////////////////////
  public static void bone(String line){ //first bracket missing, second bracket present, both parentheses present
    String str = "";
    if(line.indexOf('(') > 0){
        for (int i = 0; i<=line.indexOf('('); i++){
            if (i == 0 || (Character.isDigit(line.charAt(i)) && isSign(line.charAt(i+1))) && i>=2 || line.charAt(i) == ('(')){
                str += ((i+1) + ",");
            }
        }
        System.out.println(str.substring(0, str.length()-1));
    }
    else if (line.indexOf('(') == 0) {
        str += "1";
        System.out.println(str);
    }
  }
    
    
   ///////////////////////////////////////////////////////////////////////////// 
  public static void btwo(String line){ //second bracket missing, first bracket present, both parentheses present
      String str = "";
      if(line.indexOf(')') > 0){
          for (int i = line.indexOf(')'); i<line.length(); i++){
              if ((line.charAt(i) == ')') || (Character.isDigit(line.charAt(i)) && i<line.length()-1 && !(Character.isDigit(line.charAt(i+1)))) || i == line.length()-1){
                  str += ((i+2) + ",");
              } 
          }
          System.out.println(str.substring(0, str.length()-1)); 
      }
      else if(line.indexOf(')') == line.length()-1){
          str += line.length();
          System.out.println(str);
      } 
  }
    
  
/////////////////////////////////////////////////////////////////// 
  public static void pone(String line){ //first parenthesis missing
      String str = "";
      if (line.indexOf('[') >= 0) { //checks if brackets present
          for (int i = line.indexOf('['); i<line.indexOf(')'); i++){ //from first bracket to second parenthesis
             /*if (Character.isDigit(line.charAt(i)) && isSign(line.charAt(i+1))){
                 str += ((i+1) + ",");
             }*/
              
              if (i == line.indexOf('[')+1 || (Character.isDigit(line.charAt(i)) && isSign(line.charAt(i+1)) && containsSign(line.substring(i, line.indexOf(')'))))){
                 str += ((i+1) + ",");
             }
          }  
      } 
      else { //no brackets present
          for (int i = 0; i<line.indexOf(')'); i++){
              if (i == 0 || ((Character.isDigit(line.charAt(i))) && isSign(line.charAt(i-1)) && containsSign(line.substring(i, line.indexOf(')'))))){
                 str += ((i+1) + ",");
             }
          }
      }
      System.out.println(str.substring(0, str.length()-1)); 
  }
    
  /////////////////////////////////////////////   
  public static void ptwo(String line){ //second parenthesis missing
      String str = "";
      if (line.indexOf(']') >= 0) { //checks if brackets present
          for (int i = line.indexOf('('); i<line.indexOf(']'); i++){ 
              if (Character.isDigit(line.charAt(i)) && (isSign(line.charAt(i+1)) || line.charAt(i+1) == (']')) && containsSign(line.substring(line.indexOf('('), i))){
                 str += ((i+2) + ","); 
              }
          }
      }
      else{ //no brackets present
          for (int i = line.indexOf('('); i<line.length(); i++){
              if (Character.isDigit(line.charAt(i)) && (i == line.length()-1 || (isSign(line.charAt(i+1)))) && containsSign(line.substring(line.indexOf('('), i))){
              str += ((i+2) + ",");
          }
          }
      }
      System.out.println(str.substring(0, str.length()-1)); 
      
      
  }
    
  
    
    
    
    
  //helper boolean methods  
    public static boolean isSign(char ch){
      if (ch == '*' || ch == '+'|| ch == '-' || ch == '/'){
          return true;
      }
      return false;
  }
    
  public static boolean containsSign(String sub){
      for (int i = 0; i<sub.length(); i++){
          if (isSign(sub.charAt(i))){
              return true;
          }
      }
      return false;
  }
}

/*Sample Cases
[2+3*8-3)]+6
[(2-5)+6
[(5+5-2]*5
13-[(6+18)/3*22
[4/(12-8/4*25]

Output:
2,4,6
7,9
6,8
11,13,16
9,11,14

Test Cases
12+[10/(2-3]*8
45/5/(3+2)*3]*5
1+[2-(3*4/5]*6
32/4)*2
99/3/3*2/5-6)

Output:
12
1,4,6
10,12
1
1,4,6,8,10

Cases from Junior:
(2+3*6+1
2-5*(6+1
5+5-2)*5
3*5+(8/4*2
2+8/4*5)

Output:
5,7,9
9
1,3
9,11
1,3,5

Test cases from Junior:
6+2/3*4)
(2-2+2+3*4/2
8/(2+3-6+2
7-5+8*6/2)+1
9+6)/2-4+5

Output:
1,3,5
5,7,9,11,13
7,9,11
1,3,5,7
1

*/
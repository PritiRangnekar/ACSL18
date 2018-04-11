//Priti Rangnekar 3/14/2018
//BASIS Independent Silicon Valley
//ACSL Intermediate Contest 3: ACSL Walk

import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.math.BigInteger;


public class acslwalk{
  public static void main(String[] args){
    //declare Scanner
    Scanner sc = new Scanner(System.in);
    //read 1st line of code
    String hexline = sc.nextLine(); //reads in entire first line
    String[] hexarr = new String[8]; //declare array which will contain each hex string
    int w = 0; //placeholder for dealing with commas
    for (int i = 0; i<7; i++){
      hexarr[i] = hexline.substring(w, hexline.substring(w).indexOf(',') + w);
      w = hexline.substring(w).indexOf(',') + 2 + w;
    }
    //add in last element (since there's no comma at the end, it can't be added in the for loop)
    hexarr[7] = hexline.substring(w);
    //CHECKPOINT: ARRAY CONSISTS OF ALL HEX NUMBERS
    
    String[] binarr = new String[8];
    for (int i = 0; i<8; i++){
       String initbin = htb(hexarr[i]);
       if (initbin.length() == 8){
         binarr[i] = initbin;
       }
       else{
         binarr[i] = initbin;
         for (int j = 0; j<8-initbin.length(); j++){
           binarr[i] = "0" + binarr[i];
         }
       }
    }
   //CHECKPOINT: binarr[] now contains string arrays of each row
    
   String[][] board = new String[8][8]; //the ACTUAL PLAYING BOARD
   for (int r = 0; r<8; r++){
     for (int c = 0; c<8; c++){
       board[r][c] = binarr[r].substring(c, c+1);
     }
   }
   //CHECKPOINT: board has been initialized with all values
   
   
   
   
  

   for(int runs = 0; runs<5; runs++){
     int[][] visits = new int[8][8]; //the board, but each location has a value telling what its angle is
    //CHECKPOINT: visits has been initialized all to 90 for the spots with 1 on board
     for (int a = 0; a<8; a++){ //initializes locations in visits[][] that have a value of 1 on the corresponding board to 90
       for (int b = 0; b<8; b++){
         if (board[a][b].equals("1")){
           visits[a][b] = 90;
         }
       }
     }
     
      int deltad; //the "change in" direction - basically to determine the new direction to travel in
      
     //NOW THE ACTUAL INPUT CASES WILL BE READ///////////
     String testcase = sc.nextLine();
     //System.out.println(testcase);
     int row = Integer.parseInt(testcase.substring(0, 1))- 1; //initializes starting row position, and subtracts 1 since arrays start from 0
     int col = Integer.parseInt(testcase.substring(3, 4)) - 1; //initializes starting col position, and subtracts 1 since arrays start from 0
     String dir = testcase.substring(6,7); //initializes starting direction - either A, B, R, or L
     int movestot = Integer.parseInt(testcase.substring(9));
     //CHECKPOINT: the initial row, the initial col (actual for each, with 0 as starting point), the startdir, and the movestot have all been initialized into variables
     
     for (int movecount = 0; movecount<movestot; movecount++){
       if (board[row][col].equals("0")){
         //these 4 check if it's at edge of board, with 0, and about to fall off
         if (dir.equals("B") && row == 0){
           row = 7;
         }
         else if (dir.equals("A") && row == 7){
           row = 0;
         }
         else if (dir.equals("L") && col == 7){
           col = 0;
         }
         else if (dir.equals("R") && col == 0){
           col = 7;
         }
         //if not at edge at board and about to fall off
         else{
           if (dir.equals("L")){
             col++;
           }
           else if (dir.equals("R")){
             col--;
           }
           else if (dir.equals("A")){
             row++;
           }
           else if (dir.equals("B")){
             row--;
           } 
         }
       }
       else if (board[row][col].equals("1")) { 
         deltad = visits[row][col]%360; //sets the angle value at that position to delta d, accounting for 360=720=1080 etc by using mod operater
         visits[row][col]+=90; //adds 90 degrees to that spot in visits array, since it got visited one additional time
         //for the following if-statements, it moves it in the appropriate direction and adjusts the dir variable to account for new direction
           if (dir.equals("B")){ //entering from below
             if (deltad == 0){
               dir = "A";
               if (row == 7){
                 row = 0;
               }
               else{
                 row++;
               }
             }
             else if (deltad == 90){
               dir = "R";
               if (col == 0){
                 col = 7;
               }
               else{
                 col--;
               }
             }
             else if (deltad == 180){
               dir = "B";
               if (row == 0){
                 row = 7;
               }
               else{
                 row--;
               }
             }
             else if (deltad == 270){
               dir = "L";
               if (col == 7){
                 col = 0;
               }
               else{
                 col++;
               }
             } 
           }
           else if (dir.equals("A")){ //entering from above
             if (deltad == 0){
               dir = "B";
               if (row == 0){
                 row = 7;
               }
               else{
                 row--;
               }
             }
             else if (deltad == 90){
               dir = "L";
               if (col == 7){
                 col = 0;
               }
               else{
                 col++;
               }
             } 
             else if (deltad == 180){
               dir = "A";
               if (row == 7){
                 row = 0;
               }
               else{
                 row++;
               }
             }
             else if (deltad == 270){
              dir = "R";
               if (col == 0){
                 col = 7;
               }
               else{
                 col--;
               }
             } 
           }
           else if (dir.equals("L")){ //entering from left
             if (deltad == 0){
               dir = "R";
               if (col == 0){
                 col = 7;
               }
               else{
                 col--;
               }
             }
             else if (deltad == 90){
               dir = "B";
               if (row == 0){
                 row = 7;
               }
               else{
                 row--;
               }
             }
             else if (deltad == 180){
               dir = "L";
               if (col == 7){
                 col = 0;
               }
               else{
                 col++;
               }
             }
             else if (deltad == 270){
               dir = "A";
               if (row == 7){
                 row = 0;
               }
               else{
                 row++;
               }
             } 
           }
           else if (dir.equals("R")){ //entering from right
             if (deltad == 0){
               dir = "L";
               if (col == 7){
                 col = 0;
               }
               else{
                 col++;
               }
             }
             else if (deltad == 90){
               dir = "A";
               if (row == 7){
                 row = 0;
               }
               else{
                 row++;
               }
             }
             else if (deltad == 180){
               dir = "R";
               if (col == 0){
                 col = 7;
               }
               else{
                 col--;
               }
             }
             else if (deltad == 270){
               dir = "B";
               if (row == 0){
                 row = 7;
               }
               else{
                 row--;
               }
             } 
           }
         
       
       
     }
       //System.out.println((row+1) + ", " + (col + 1));
   }
     System.out.println((row+1) + ", " + (col + 1));
   
   }
  }

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  public static String htb(String hex) {
    return new BigInteger(hex, 16).toString(2);
  }
}

/*Test Cases
B2, F, F3, A1, 4E, 3, 78, BC
1, 4, L, 5 
6, 5, B, 9 
4, 3, A, 7 
8, 8, R, 12 
1, 1, A, 20 

Output:
1, 5
5, 3
3, 7
3, 5
8, 6

/*Sample Cases 
C, C7, 85, D6, 46, D7, E6, 87
2, 3, L, 2
2, 7, B, 8
4, 5, R, 3
6, 7, A, 5
8, 8, L, 7

Output:
2, 5
2, 5
6, 4
3, 7
6, 1
*/





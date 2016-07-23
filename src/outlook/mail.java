/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package outlook;

/**
 *
 * @author zahurul
 *
 */

import java.util.*;
import java.io.*;
import dAO.*;
import dTO.adjDTO;


public class mail {

public static void processpstFile(){

    String input_file = "D:\\mailplay.txt";
    String str = null;
    String test = null;
    ArrayList ar = new ArrayList();
try{
    BufferedReader inFile = new BufferedReader(new FileReader(input_file));
    while ((str = inFile.readLine()) != null) {
        if (str.length() != 0) {

            adjDTO adto=new adjDTO();

            StringTokenizer stk = new StringTokenizer(str,"- *");
            for (int count = 0; stk.hasMoreTokens(); ++count) {
                test = stk.nextToken();

                switch (count) {
                case 0:
                    adto.sourceid=Long.parseLong(test);
                    break;
                case 1:
                    adto.targetid=Long.parseLong(test);
                    break;
                }//switch
            }//for
            ar.add(adto);
        }//if str
    } //while str
    inFile.close();
}// try

catch(Exception ex){
    System.out.println("Error from input...please take action "+ex);

}


}///func

public static void main(String[] args) {

    mail m=new mail();
    m.processpstFile();

    }///main


}///class

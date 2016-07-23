/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package appL;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 *
 * @author zahurul
 */
public class patternMatcher {

public static void main(String[] args) {

    String str="";

try{

File f= new File("input\\out_patmatch");
f.delete();

BufferedReader inFile = new BufferedReader(new FileReader("input\\input.txt"));

PrintWriter pmatf = new PrintWriter(new BufferedWriter(
                                        new FileWriter("input\\out_patmatch",true)));

while ((str = inFile.readLine()) != null) {

    Pattern patt1 = Pattern.compile("SEG-");
    Pattern patt2 = Pattern.compile("BVCI OPERATIONAL STATE.................................");
    Pattern patt3 = Pattern.compile("GPRS ENABLED.....................................(GENA)... Y");

            Matcher m1 = patt1.matcher(str);
            Matcher m2 = patt2.matcher(str);
            Matcher m3 = patt3.matcher(str);


if(m1.find()){

    String str1 = str.substring(3);
    pmatf.print(str1+":");
}
if(m2.find()){

    String str2 = str.substring(54);
    pmatf.println(str2);
}
    } //while str


pmatf.flush();pmatf.close();
inFile.close();

}///try

catch (Exception ex){
              System.out.println("Error from MD_NEW_DB "+ex);
}//catch


    }//main


}

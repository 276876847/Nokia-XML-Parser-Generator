/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package appL;



import java.util.*;
import java.io.*;
import dAO.*;
import dTO.adjDTO;

/**
 *
 * @author zahurul
 */
public class del_n {

public static ArrayList del_n_INPUT()
{
    String str = null;
    String test = null;
    ArrayList ar = new ArrayList();
    String input_file = "input\\input.txt";

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
    return null;
}
return ar;
}// ends funct


public static void delAdjcAll(ArrayList ar){
try{
    long not_exist=0;
        long found=0;
            long invalid_src=0;
                long total=0;
                    int ntype=-1;
File f=new File("input\\out_delFlexi.xml");
f.delete();

PrintWriter delf = new PrintWriter(new BufferedWriter(new FileWriter(
        "input\\out_delFlexi.xml",true)));
String sstart="<?xml version=\"1.0\"?>\n";
sstart+="<!DOCTYPE raml SYSTEM 'raml20.dtd'>\n";
sstart+="<raml version=\"2.0\" xmlns=\"raml20.xsd\">\n";
sstart+="  <cmData type=\"plan\">\n";
sstart+="    <header>\n";
sstart+="      <log dateTime=\"\" action=\"created\" user=\"blOMC\" " +
        "appInfo=\"blNokiaTool\"/>\n";
sstart+="    </header>";

String send="	</cmData>\n";
send+="</raml>\n";

delf.println(sstart);


            Iterator itr=ar.iterator();
            while(itr.hasNext()){
            adjDTO adjinp=(adjDTO) itr.next();
            adjDTO adto=adjDAO.getADJbySrcTarget(adjinp);

            if(btsDAO.GetMasterbtsDATA(adjinp.sourceid).lac==-1)
            {ntype=0;/*invalid source*/}
            else if(adto.adjIndex==-1 && adto.dname.equalsIgnoreCase("")){
            ntype=1;/*not_exist*/}
            else {/*relation exists*/ntype=2;}


            switch(ntype){
                case 0:{
String s0="##Source: "+adjinp.sourceid+" is not a valid Nokia cell.. please check ";
System.out.println(s0);
delf.println("<!--"+s0+"-->");
                invalid_src++;
                }
                break;

                case 1:{
String s1="##Source: "+adjinp.sourceid+" targetid: "+adjinp.targetid
        +" relation not exists.";
System.out.println(s1);
delf.println("<!--"+s1+"-->");
                not_exist++;
                }
                break;
                case 2:{
///System.out.println("found relation...");
String sprint="<!--deleting relation- "+adto.dname+"/ADCE-"+adto.adjIndex+"-->\n";

String dd="\""+adto.dname+"/ADCE-"+adto.tgtlac+"  "
        +String.valueOf(adjinp.targetid).substring(3,String.valueOf(adjinp.targetid)
        .length())+"\"";

String sdel=sprint+"\t<managedObject class=\"ADCE\" distName="+dd
        +" operation=\"delete\">\n";
sdel+="\t\t<p name=\"adjcIndex\">"+adto.adjIndex+"</p>\n";
sdel+="\t</managedObject>";
System.out.println(sprint);
delf.println(sdel);
adjDAO.delADJEntry(adjinp);
found++;
                }
                break;
                default:
                System.out.println("Error from delete program, check...");
            }//switch


            }///while itr

            delf.println(send);
            delf.flush();
            delf.close();

total=found+not_exist+invalid_src;
System.out.println(".............Summary............");
System.out.println("....Relation found="+found);
System.out.println("Relation not found="+not_exist);
System.out.println("..Invalid src_cell="+invalid_src);
System.out.println(".............Total="+total);
        }//try

catch (Exception ex){
     System.out.println("from del neibor.."+ex);
}

}///func



public static void main(String[] args) {
        del_n del_nobj = new del_n();
       
        ArrayList ar= new ArrayList();
        ar=del_nobj.del_n_INPUT();
        del_nobj.delAdjcAll(ar);
        
    }//main

}///class
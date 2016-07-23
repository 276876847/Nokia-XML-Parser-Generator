/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package appL;


import java.util.*;
import java.io.*;
import dAO.*;
import dTO.*;
import javax.swing.JOptionPane;

/**
 *
 * @author zahurul
 */
public class test {

private static  String[] finalName=new String[34];
private static  int length=0;

public static ArrayList test_INPUT(){
    
    String str = null;
    String test = null;
    ArrayList ar = new ArrayList();
    String input_file = "input\\input.txt";
    
try{
    BufferedReader inFile = new BufferedReader(new FileReader(input_file));
    int linecount=0;
    while ((str = inFile.readLine()) != null) {
        if (str.length() != 0) {
            paramDTO pdto=new paramDTO();
            StringTokenizer stk = new StringTokenizer(str," *");
            for (int count = 0; stk.hasMoreTokens(); ++count) {
                test = stk.nextToken();

                switch (count) {
                case 0:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 1:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 2:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 3:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 4:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 5:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 6:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 7:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 8:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 9:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 10:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 11:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 12:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 13:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 14:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 15:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 16:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 17:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 18:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 19:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 20:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 21:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 22:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 23:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 24:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 25:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 26:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 27:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 28:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 29:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 30:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 31:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 32:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                case 33:{
                    if(linecount!=0){pdto.attrValue[count]=test;}
                    if(linecount==0) pdto.attrName[count]=test;
                }
                    break;
                }//switch
            }//for
            ar.add(pdto);
        }//if str
        linecount++;
    } //while str
    inFile.close();
}// try

catch(Exception ex){
    System.out.println("Error from input...please take action "+ex);
    return null;
}
return ar;
}// ends funct

public static void testAll(ArrayList ar,String objStr){
try{

File f=new File("input\\out_"+objStr+".xml");
File f1=new File("input\\mml_"+objStr+".xml");
f.delete();f1.delete();

PrintWriter testf = new PrintWriter(new BufferedWriter(new FileWriter(
        "input\\out_"+objStr+".xml",true)));

PrintWriter mmlf = new PrintWriter(new BufferedWriter(new FileWriter(
        "input\\mml_"+objStr,true)));

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

testf.println(sstart);


            Iterator itr=ar.iterator();
            while(itr.hasNext()){
            paramDTO  pdto=(paramDTO)itr.next();
if(!(pdto.attrValue[0].equalsIgnoreCase("NULL")||pdto.attrValue[0].
                    equalsIgnoreCase("garbage"))){

    String stestStart="";
    String stest="";
    String stestEnd="";

if(objStr.equalsIgnoreCase("BCF")){


stestStart="\t<managedObject class=\"BCF\""+" distName="+
        "\"PLMN-PLMN/BSC-"+pdto.attrValue[0]+"/BCF-"+pdto.attrValue[1]
        +"\" operation=\"update\">\n";
for(int i=0;i<length;i++){
stest+="\t\t<p name=\""+finalName[i]+"\""+">"+pdto.attrValue[i+4]+"</p>\n";
            }///for
stestEnd="\t</managedObject>\n";
}

if(objStr.equalsIgnoreCase("BTS") || objStr.equalsIgnoreCase("SEG")){

stestStart="\t<managedObject class=\"BTS\""+" distName="+
        "\"PLMN-PLMN/BSC-"+pdto.attrValue[0]+"/BCF-"+pdto.attrValue[1]
        +"/BTS-"+pdto.attrValue[2]+"\" operation=\"update\">\n";
for(int i=0;i<length;i++){
stest+="\t\t<p name=\""+finalName[i]+"\""+">"+pdto.attrValue[i+4]+"</p>\n";
            }///for
stestEnd="\t</managedObject>\n";
}

if(objStr.equalsIgnoreCase("TRX") || objStr.equalsIgnoreCase("CHAN") ||
            objStr.equalsIgnoreCase("CHN") ){

stestStart="\t<managedObject class=\"TRX\""+" distName="+
        "\"PLMN-PLMN/BSC-"+pdto.attrValue[0]+"/BCF-"+pdto.attrValue[1]
        +"/BTS-"+pdto.attrValue[2]+"/TRX-"+pdto.attrValue[3]+"\" operation=\"update\">\n";
for(int i=0;i<length;i++){
stest+="\t\t<p name=\""+finalName[i]+"\""+">"+pdto.attrValue[i+4]+"</p>\n";
            }///for
stestEnd="\t</managedObject>\n";

}


if(objStr.equalsIgnoreCase("HOC") ){

stestStart="\t<managedObject class=\"HOC\""+" distName="+
        "\"PLMN-PLMN/BSC-"+pdto.attrValue[0]+"/BCF-"+pdto.attrValue[1]
        +"/BTS-"+pdto.attrValue[2]+"/HOC-1"+"\" operation=\"update\">\n";
for(int i=0;i<length;i++){
stest+="\t\t<p name=\""+finalName[i]+"\""+">"+pdto.attrValue[i+4]+"</p>\n";
            }///for
stestEnd="\t</managedObject>\n";
}

if(objStr.equalsIgnoreCase("POC") ){

stestStart="\t<managedObject class=\"POC\""+" distName="+
        "\"PLMN-PLMN/BSC-"+pdto.attrValue[0]+"/BCF-"+pdto.attrValue[1]
        +"/BTS-"+pdto.attrValue[2]+"/POC-1"+"\" operation=\"update\">\n";
for(int i=0;i<length;i++){
stest+="\t\t<p name=\""+finalName[i]+"\""+">"+pdto.attrValue[i+4]+"</p>\n";
            }///for
stestEnd="\t</managedObject>\n";
}

if(objStr.equalsIgnoreCase("DAP") ){

stestStart="\t<managedObject class=\"DAP\""+" distName="+
        "\"PLMN-PLMN/BSC-"+pdto.attrValue[0]+"/DAP-"+pdto.attrValue[1]
        +"\" operation=\"update\">\n";
for(int i=0;i<length;i++){
stest+="\t\t<p name=\""+finalName[i]+"\""+">"+pdto.attrValue[i+4]+"</p>\n";
            }///for
stestEnd="\t</managedObject>\n";
//System.out.println(stest);
}


if(objStr.equalsIgnoreCase("ADCE") || objStr.equalsIgnoreCase("ADJC") ||
            objStr.equalsIgnoreCase("ADJ")){

adjDTO adto=new adjDTO();adjDTO adto2=new adjDTO();
adto2.sourceid=Long.parseLong(pdto.attrValue[0]);
adto2.targetid=Long.parseLong(pdto.attrValue[1]);
adto=adjDAO.getADJbySrcTarget(adto2);

if(adto.adjIndex!=-1){
stestStart="\t<managedObject class=\"ADCE\""+" distName=\""+adto.dname+"/ADCE-"
        +adto.tgtlac+"  "+String.valueOf(adto2.targetid).substring(3,String.
        valueOf(adto2.targetid).length())+"\" operation=\"update\">\n";
stest+="\t\t<p name=\""+"adjcIndex"+"\""+">"+adto.adjIndex+"</p>\n";
stest+="\t\t<p name=\""+"targetCellDN"+"\""+">"+adto.tgtDN+"</p>\n";
for(int i=0;i<length;i++){
stest+="\t\t<p name=\""+finalName[i]+"\""+">"+pdto.attrValue[i+2]+"</p>\n";
            }///for
stestEnd="\t</managedObject>\n";
}///

else {
    stest="<!!--No relation found between src="+adto2.sourceid +" and tgt="+adto2.targetid+" ignoring..-->\n";
    System.out.println(stest);
}
}


testf.print(stestStart);
testf.print(stest);
testf.print(stestEnd);
}///if
}///while itr

            testf.println(send);
            testf.flush();mmlf.flush();
            testf.close();mmlf.close();

        }//try

catch (Exception ex){
     System.out.println("from test_ALL.."+ex);
}

}///func


public static void main(String[] args) {
        test tobj = new test();
        String objStr = JOptionPane.showInputDialog(null,"Please enter Object Type:");
        ArrayList ar= new ArrayList();
        ar=tobj.test_INPUT();
        

        Iterator itr=ar.iterator();

        while(itr.hasNext()){
            paramDTO  pdto2=(paramDTO)itr.next();
            for(int i=0;i<=29;i++){
            finalName[i]=pdto2.attrName[i];
            //System.out.println(finalName[i]);
            }///for
            break;
            }///while

        for(int i=0;i<=29;i++){
            if(!(finalName[i].equals("garbage") || finalName[i].equals("NULL"))){
            length++;
            System.out.println(finalName[i]);
            }
       }

        tobj.testAll(ar,objStr);


}//main

}///class

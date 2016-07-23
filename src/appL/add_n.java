/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package appL;

import java.util.*;
import java.io.*;
import dAO.*;
import dTO.adjDTO;
import dTO.btsDTO;

/**
 *
 * @author zahurul
 */
public class add_n {

public static ArrayList add_n_INPUT()
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
                case 2:
                    adto.tgtlac=Long.parseLong(test);
                    break;
                case 3:
                    adto.tgtbcch=Long.parseLong(test);
                    break;
                case 4:
                    adto.tgtncc=Long.parseLong(test);
                    break;
                case 5:
                    adto.tgtbcc=Long.parseLong(test);
                    break;
                case 6:
                {long k=Long.parseLong(test);
                 if(k==900) {adto.fband=0;}
                 else if (k==1800) {adto.fband=1;}
                 else {System.out.println("New type found ...please chck input or add code");
                 System.out.println("Exiting program..");
                 System.exit(0);
                 }
                }

                    break;
                case 7:
                    adto.pmrg=Long.parseLong(test);
                    break;
                case 8:
                    adto.pri=Long.parseLong(test);
                    break;
                case 9:
                    adto.prc=Long.parseLong(test);
                    break;
                case 10:{
                    if(test.equalsIgnoreCase("YES")){adto.sync=1;}
                    else if(test.equalsIgnoreCase("NO")){adto.sync=0;}
                    else {System.out.println("Invalid sync input, please check...");
                    System.exit(0);}
                }///case 10
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


public int check_INPUT(ArrayList ar){
        // first check if any source cell missing

        int val=1;
        ArrayList al=new ArrayList(); ///decisive
        Iterator itrmain = ar.iterator();
        while (itrmain.hasNext()) {
            adjDTO adto = (adjDTO) itrmain.next();
            btsDTO bdto=new btsDTO();
            bdto = btsDAO.GetMasterbtsDATA(adto.sourceid);

            adjDTO ap= new adjDTO();

if (bdto.lac == -1 && bdto.fband == -1 ){
    System.out.println("No entry found for source cell: " + adto.sourceid);
              ap.sourceid=adto.sourceid;
              al.add(ap);
            }
        }//while

       Iterator kp=al.iterator();

  while(kp.hasNext()){
      val = -1;
      adjDTO a=(adjDTO) kp.next();
System.out.println("Please Rectify input "+a.sourceid);
  }

return val;
}///funct



public static void add_n_ALL(ArrayList ar){
try{
    long found=0;
        long ex_32=0;
            long exist=0;
                long total=0;

    File f= new File("input\\out_addFlexi.xml");
    f.delete();

PrintWriter addf = new PrintWriter(new BufferedWriter(new FileWriter("input\\out_addFlexi.xml",true)));
String sstart="<?xml version=\"1.0\"?>\n";
sstart+="<!DOCTYPE raml SYSTEM 'raml20.dtd'>\n";
sstart+="<raml version=\"2.0\" xmlns=\"raml20.xsd\">\n";
sstart+="  <cmData type=\"plan\">\n";
sstart+="    <header>\n";
sstart+="      <log dateTime=\"\" action=\"created\" user=\"blOMC\" appInfo=\"blNokiaTool\"/>\n";
sstart+="    </header>";

String send="	</cmData>\n";
send+="</raml>\n";

addf.println(sstart);

Iterator itr=ar.iterator();
        while (itr.hasNext()) {
        adjDTO adjinp= (adjDTO) itr.next();

//Determine if 32 relation exceeds
        long k=-1;  // k= free adjc_id

        adjDTO adto=new adjDTO();
        adjDTO adto2=new adjDTO();
        int ntype=-1;
        adto2.sourceid=adjinp.sourceid;

        for(k=0;k<=30;k++){
        adto2.adjIndex=k;
        adto=adjDAO.getADJbyadjID(adto2);

        if(adto.targetid==-1){break;}///free id
           //remember k free adjc_id
        }///for

if(adjDAO.getADJbySrcTarget(adjinp).adjIndex!=-1){ntype=0;}///relation exist
else {ntype=1;}///new relation found
        switch (ntype) {
                case 0:{
String s0="\t##Source: "+adjinp.sourceid+" target: "+adjinp.targetid+" already exists as "+
          adto.dname+"/ADCE-"+adjDAO.getADJbySrcTarget(adjinp).adjIndex;
System.out.println(s0);
addf.println("<!--"+s0+"-->");
                exist++;
        }///case ii0
                break;
                case 1:{
///System.out.println("case 1");

            if(k>30){
String s1="\t####Source: "+adjinp.sourceid+" target: "+adjinp.targetid+" can not be added "+
        " due to 32 relation exceeds..";
System.out.println(s1);
addf.println("<!--"+s1+"-->");
                ex_32++;
            }///k> 30

            else if(k<=30){
//System.out.println("Found new relation... ");

adjinp.dname=btsDAO.GetMasterbtsDATA(adjinp.sourceid).dname;

String dd="\""+adjinp.dname+"/ADCE-"+adjinp.tgtlac+"  "
        +String.valueOf(adjinp.targetid).substring(3,String.valueOf(adjinp.targetid)
        .length())+"\"";

String sprint="\t<!--adding relation between "+adjinp.sourceid+" and "+adjinp.targetid
        +" as ADJID-"+k+"-->\n";
String sadd=sprint+"\t<managedObject class=\"ADCE\" version=\"S14\" distName="+dd+" " +
        "operation=\"create\">\n";
sadd+="\t  <p name=\"frequencyBandInUse\">"+adjinp.fband+"</p>\n";
sadd+="\t  <p name=\"gprsMsTxPwrMaxCCH1x00\">0</p>\n";
sadd+="\t  <p name=\"msTxPwrMaxGSM1x00\">0</p>\n";
sadd+="\t  <p name=\"adjCellBsicBcc\">"+adjinp.tgtbcc+"</p>\n";
sadd+="\t  <p name=\"adjCellBsicNcc\">"+adjinp.tgtncc+"</p>\n";
sadd+="\t  <p name=\"adjCellLayer\">0</p>\n";
sadd+="\t  <p name=\"adjacentCellIdCI\">"+String.valueOf(adjinp.targetid)
        .substring(3,String.valueOf(adjinp.targetid).length())+"</p>\n";
sadd+="\t  <p name=\"adjacentCellIdLac\">"+adjinp.tgtlac+"</p>\n";
sadd+="\t  <p name=\"adjacentCellIdMCC\">470</p>\n";
sadd+="\t  <p name=\"adjacentCellIdMNC\">03</p>\n";
sadd+="\t  <p name=\"adjcIndex\">"+k+"</p>\n";
sadd+="\t  <p name=\"amrDadlbTargetCell\">0</p>\n";
sadd+="\t  <p name=\"bcchFrequency\">"+adjinp.tgtbcch+"</p>\n";
sadd+="\t  <p name=\"chainedAdjacentCell\">0</p>\n";
sadd+="\t  <p name=\"drThreshold\">34</p>\n";
sadd+="\t  <p name=\"dtmEnabled\">0</p>\n";
sadd+="\t  <p name=\"dtmPowerBudgetMargin\">30</p>\n";
sadd+="\t  <p name=\"enableDerivedHandoverPower\">3</p>\n";
sadd+="\t  <p name=\"enableHoMarginLevQual\">1</p>\n";
sadd+="\t  <p name=\"fastMovingThreshold\">0</p>\n";
sadd+="\t  <p name=\"gprsEnabled\">1</p>\n";
sadd+="\t  <p name=\"gprsMsTxpwrMaxCCH\">5</p>\n";
sadd+="\t  <p name=\"gprsPenaltyTime\">0</p>\n";
sadd+="\t  <p name=\"gprsRxlevAccessMin\">20</p>\n";
sadd+="\t  <p name=\"gprsTemporaryOffset\">0</p>\n";
sadd+="\t  <p name=\"hcsPriorityClass\">"+adjinp.prc+"</p>\n";
sadd+="\t  <p name=\"hcsThreshold\">255</p>\n";
sadd+="\t  <p name=\"hoLevelUmbrella\">63</p>\n";
sadd+="\t  <p name=\"hoLoadFactor\">0</p>\n";
sadd+="\t  <p name=\"hoMarginLev\">6</p>\n";
sadd+="\t  <p name=\"hoMarginPbgt\">"+adjinp.pmrg+"</p>\n";
sadd+="\t  <p name=\"hoMarginQual\">0</p>\n";
sadd+="\t  <p name=\"hoPriorityLevel\">"+adjinp.pri+"</p>\n";
sadd+="\t  <p name=\"hoTargetArea\">0</p>\n";
sadd+="\t  <p name=\"msPwrOptLevel\">255</p>\n";
sadd+="\t  <p name=\"msTxPwrMaxGSM\">5</p>\n";
sadd+="\t  <p name=\"nccrEgprsPbgtMargin\">69</p>\n";
sadd+="\t  <p name=\"nccrEgprsQualityMargin\">5</p>\n";
sadd+="\t  <p name=\"nccrGprsPbgtMargin\">69</p>\n";
sadd+="\t  <p name=\"nccrGprsQualityMargin\">5</p>\n";
sadd+="\t  <p name=\"neighbourCellRanking\">0</p>\n";
sadd+="\t  <p name=\"reportingPriority\">0</p>\n";
sadd+="\t  <p name=\"rxLevMinCell\">20</p>\n";
sadd+="\t  <p name=\"synchronized\">"+adjinp.sync+"</p>\n";
sadd+="\t  <p name=\"trhoTargetLevel\">0</p>\n";
sadd+="\t</managedObject>";

adjinp.adjIndex=k;
System.out.println(sprint);
addf.println(sadd);
adjDAO.addADJEntry(adjinp);
found++;
            }///k<=30

            }///case 1
            break;
        }///switch

        }/// ends while itr

addf.println(send);
addf.flush();
addf.close();

total=found+ex_32+exist;
System.out.println(".............Summary.............");
System.out.println(".....Relation found="+found);
System.out.println("32 Relation exceeds="+ex_32);
System.out.println(".....Already exists="+exist);
System.out.println("..............Total="+total);

}//try

catch(Exception ex){
System.out.println("ERROR from add_n_ALL "+ex);
}


}///func

public static void main(String[] args) {
        add_n adn = new add_n();
     
        ArrayList ar=new ArrayList();
        ar=adn.add_n_INPUT();
        if(adn.check_INPUT(ar)==-1)
         {
            return;
         }
        else
        {
           adn.add_n_ALL(ar);
        }

    }///main

}///class
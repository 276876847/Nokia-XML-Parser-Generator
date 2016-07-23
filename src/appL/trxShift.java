/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package appL;

import java.util.*;
import java.io.*;
import dAO.*;
import dTO.trxDTO;
import dTO.lpdDTO;

/**
 *
 * @author zahurul
 */
public class trxShift {


public static ArrayList trx_Shift_INPUT()
{
    String str = null;
    String test = null;
    ArrayList ar = new ArrayList();
    String input_file = "input\\input.txt";

try{
    BufferedReader inFile = new BufferedReader(new FileReader(input_file));
    while ((str = inFile.readLine()) != null) {
        if (str.length() != 0) {

            trxDTO tdto=new trxDTO();

            StringTokenizer stk = new StringTokenizer(str," *");
            for (int count = 0; stk.hasMoreTokens(); ++count) {
                test = stk.nextToken();

                switch (count) {
                case 0:
                    tdto.bsc=Long.parseLong(test);
                    break;
                case 1:
                    tdto.bcf=Long.parseLong(test);
                    break;
                case 2:
                    tdto.bts=Long.parseLong(test);
                    break;
                case 3:
                    tdto.trxid=Long.parseLong(test);
                    break;
                case 4:
                    tdto.tsc=Long.parseLong(test);///holding new pcm
                    break;
                case 5:
                    tdto.sid=Long.parseLong(test);///holding new pcm start TSL
                    break;
                case 6:
                    tdto.lapdName=test.toString();///holding new pcm start TSL
                    break;
                case 7:
                    tdto.gena=Long.parseLong(test);///holding new LAPD TSL
                    break;
                case 8:
                    tdto.hrsup=Long.parseLong(test);///holding new LAPD SUB-TSL
                    break;
                }//switch
            }//for
            ar.add(tdto);
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


public static void del_trxShift(ArrayList ar){
try{

File f=new File("input\\del_trxShift.xml");
f.delete();

PrintWriter trxshf = new PrintWriter(new BufferedWriter(new FileWriter(
        "input\\del_trxShift.xml",true)));
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

trxshf.println(sstart);


            Iterator itr=ar.iterator();
            while(itr.hasNext()){

            trxDTO trxinp=(trxDTO) itr.next();
              String s0="";
              long lpdold=trxDAO.GettrxDATA(trxinp).lapdNumber;
            s0="\t<managedObject class=\"LAPD\" version=\"S15\" " + "distName=\"PLMN-PLMN/BSC-"+trxinp.bsc+"/LAPD-"
                    +lpdold+"\" operation=\"delete\"> </managedObject>\n";
            s0+="\t<managedObject class=\"TRX\" version=\"S15\" " + "distName=\"PLMN-PLMN/BSC-"+trxinp.bsc+"/BCF-"
                    +trxinp.bcf+"/BTS-"+trxinp.bts+"/TRX-"+trxinp.trxid+"\" operation=\"delete\"> </managedObject>";
            
            //lpdDAO.updateLPD(trxinp.bsc, lpdold, -1);

            trxshf.println(s0);
            }///while itr

            trxshf.println(send);
            trxshf.flush();
            trxshf.close();
        }//try

catch (Exception ex){
     System.out.println("from del neibor.."+ex);
}

}///func


public static void create_trxShift(ArrayList ar){
try{

File f=new File("input\\add_trxShift.xml");
f.delete();

PrintWriter trxshf = new PrintWriter(new BufferedWriter(new FileWriter(
        "input\\add_trxShift.xml",true)));
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

trxshf.println(sstart);


            Iterator itr=ar.iterator();
            while(itr.hasNext()){

            trxDTO trxinp=(trxDTO) itr.next();
            trxDTO trxdto=trxDAO.GettrxDATA(trxinp);
            trxdto.bsc=trxinp.bsc;trxdto.bcf=trxinp.bcf;trxdto.bts=trxinp.bts;trxdto.trxid=trxinp.trxid;
            lpdDTO ldto=lpdDAO.GetlpdDATA(trxdto);
            ldto.name=trxdto.lapdName.toString();
            lpdDTO lnew=new lpdDTO();
            lnew.bsc=trxinp.bsc; lnew.abispcm=trxinp.tsc;lnew.abistsl=trxinp.gena;lnew.abisssl=trxinp.hrsup;
            long lret=lpdDAO.GetlpdBCSUID(lnew);

            String sendmno="\t</managedObject>";


            long newlapdno=lpdDAO.getMaxLPD(lnew.bsc);
            lnew.lpd=newlapdno+1;
            lpdDAO.createLPD(lnew);
            String slapd="\t<managedObject class=\"LAPD\" version=\"S15.3 \"" + "distName=\"PLMN-PLMN/BSC-"+trxinp.bsc+"/LAPD-"
                    +(newlapdno+1)+"\" operation=\"create\">\n";
                    slapd+="\t<p name=\"bitRate\">"+ldto.brate+"</p>\n";
                    slapd+="\t<p name=\"abisSigChannelTimeSlotPcm\">"+trxinp.tsc+"</p>\n";/////looook
                    slapd+="\t<p name=\"abisSigChannelTimeSlotTsl\">"+trxinp.gena+"</p>\n";//////looook
                    if(trxinp.hrsup!=-1){slapd+="\t<p name=\"abisSigChannelSubSlot\">"+trxinp.hrsup+"</p>\n";}//////looook
                    if(lret!=-1){slapd+="\t<p name=\"logicalBCSUAddress\">"+lret+"</p>\n";}//////looook

                    slapd+="\t<p name=\"adminState\">"+ldto.admstate+"</p>\n";
                    slapd+="\t<p name=\"parentBSCId\">"+trxdto.bsc+"</p>\n";///parentBSCID
                    slapd+="\t<p name=\"dChannelType\">"+ldto.dchantype+"</p>\n";
                    slapd+="\t<p name=\"name\">"+ldto.name+"</p>\n";
                    slapd+="\t<p name=\"parameterSetNumber\">"+ldto.paramset+"</p>\n";
                    slapd+="\t<p name=\"sapi\">"+ldto.sapi+"</p>\n";
                    slapd+="\t<p name=\"tei\">"+ldto.tei+"</p>\n";
                    slapd+=sendmno.toString()+"\n";

            String strx=slapd+"\t<managedObject class=\"TRX\" version=\"S15.3\" " + "distName=\"PLMN-PLMN/BSC-"+trxinp.bsc+"/BCF-"
                    +trxinp.bcf+"/BTS-"+trxinp.bts+"/TRX-"+trxinp.trxid+"\" operation=\"create\"> \n";
                    strx+="\t<p name=\"name\">"+trxdto.name+"</p>\n";
                    strx+="\t<p name=\"adminState\">"+trxdto.admstate+"</p>\n";
                    strx+="\t<p name=\"channel0AdminState\">"+chnDAO.GetchnDATA(trxinp,0).chnadmstate+"</p>\n";
                    strx+="\t<p name=\"channel0Pcm\">"+trxinp.tsc+"</p>\n";
                    strx+="\t<p name=\"channel0Subslot\">"+chnDAO.GetchnDATA(trxinp,0).subslot+"</p>\n";
                    strx+="\t<p name=\"channel0Tsl\">"+trxinp.sid+"</p>\n";
                    strx+="\t<p name=\"channel0Type\">"+chnDAO.GetchnDATA(trxinp,0).chntype+"</p>\n";
                    strx+="\t<p name=\"channel1AdminState\">"+chnDAO.GetchnDATA(trxinp,1).chnadmstate+"</p>\n";
                    strx+="\t<p name=\"channel1Pcm\">"+trxinp.tsc+"</p>\n";
                    strx+="\t<p name=\"channel1Subslot\">"+chnDAO.GetchnDATA(trxinp,1).subslot+"</p>\n";
                    strx+="\t<p name=\"channel1Tsl\">"+trxinp.sid+"</p>\n";
                    strx+="\t<p name=\"channel1Type\">"+chnDAO.GetchnDATA(trxinp,1).chntype+"</p>\n";
                    strx+="\t<p name=\"channel2AdminState\">"+chnDAO.GetchnDATA(trxinp,2).chnadmstate+"</p>\n";
                    strx+="\t<p name=\"channel2Pcm\">"+trxinp.tsc+"</p>\n";
                    strx+="\t<p name=\"channel2Subslot\">"+chnDAO.GetchnDATA(trxinp,2).subslot+"</p>\n";
                    strx+="\t<p name=\"channel2Tsl\">"+trxinp.sid+"</p>\n";
                    strx+="\t<p name=\"channel2Type\">"+chnDAO.GetchnDATA(trxinp,2).chntype+"</p>\n";
                    strx+="\t<p name=\"channel3AdminState\">"+chnDAO.GetchnDATA(trxinp,3).chnadmstate+"</p>\n";
                    strx+="\t<p name=\"channel3Pcm\">"+trxinp.tsc+"</p>\n";
                    strx+="\t<p name=\"channel3Subslot\">"+chnDAO.GetchnDATA(trxinp,3).subslot+"</p>\n";
                    strx+="\t<p name=\"channel3Tsl\">"+trxinp.sid+"</p>\n";
                    strx+="\t<p name=\"channel3Type\">"+chnDAO.GetchnDATA(trxinp,3).chntype+"</p>\n";
                    strx+="\t<p name=\"channel4AdminState\">"+chnDAO.GetchnDATA(trxinp,4).chnadmstate+"</p>\n";
                    strx+="\t<p name=\"channel4Pcm\">"+trxinp.tsc+"</p>\n";
                    strx+="\t<p name=\"channel4Subslot\">"+chnDAO.GetchnDATA(trxinp,4).subslot+"</p>\n";
                    strx+="\t<p name=\"channel4Tsl\">"+(trxinp.sid+1)+"</p>\n";
                    strx+="\t<p name=\"channel4Type\">"+chnDAO.GetchnDATA(trxinp,4).chntype+"</p>\n";
                    strx+="\t<p name=\"channel5AdminState\">"+chnDAO.GetchnDATA(trxinp,5).chnadmstate+"</p>\n";
                    strx+="\t<p name=\"channel5Pcm\">"+trxinp.tsc+"</p>\n";
                    strx+="\t<p name=\"channel5Subslot\">"+chnDAO.GetchnDATA(trxinp,5).subslot+"</p>\n";
                    strx+="\t<p name=\"channel5Tsl\">"+(trxinp.sid+1)+"</p>\n";
                    strx+="\t<p name=\"channel5Type\">"+chnDAO.GetchnDATA(trxinp,5).chntype+"</p>\n";
                    strx+="\t<p name=\"channel6AdminState\">"+chnDAO.GetchnDATA(trxinp,6).chnadmstate+"</p>\n";
                    strx+="\t<p name=\"channel6Pcm\">"+trxinp.tsc+"</p>\n";
                    strx+="\t<p name=\"channel6Subslot\">"+chnDAO.GetchnDATA(trxinp,6).subslot+"</p>\n";
                    strx+="\t<p name=\"channel6Tsl\">"+(trxinp.sid+1)+"</p>\n";
                    strx+="\t<p name=\"channel6Type\">"+chnDAO.GetchnDATA(trxinp,6).chntype+"</p>\n";
                    strx+="\t<p name=\"channel7AdminState\">"+chnDAO.GetchnDATA(trxinp,7).chnadmstate+"</p>\n";
                    strx+="\t<p name=\"channel7Pcm\">"+trxinp.tsc+"</p>\n";
                    strx+="\t<p name=\"channel7Subslot\">"+chnDAO.GetchnDATA(trxinp,7).subslot+"</p>\n";
                    strx+="\t<p name=\"channel7Tsl\">"+(trxinp.sid+1)+"</p>\n";
                    strx+="\t<p name=\"channel7Type\">"+chnDAO.GetchnDATA(trxinp,7).chntype+"</p>\n";
                    strx+="\t<p name=\"daPool_ID\">"+trxdto.dapid+"</p>\n";
                    strx+="\t<p name=\"gprsEnabledTrx\">"+trxdto.gena+"</p>\n";
                    strx+="\t<p name=\"halfRateSupport\">"+trxdto.hrsup+"</p>\n";
                    strx+="\t<p name=\"initialFrequency\">"+trxdto.ifreq+"</p>\n";
                    strx+="\t<p name=\"lapdLinkName\">"+trxdto.lapdName+"</p>\n";
                    strx+="\t<p name=\"rfHoppingAllowed\">"+trxdto.rfhoppal+"</p>\n";
                    strx+="\t<p name=\"subslotsForSignalling\">"+trxdto.sslSignalling+"</p>\n";
                    strx+="\t<p name=\"tsc\">"+trxdto.tsc+"</p>\n";
                    strx+=sendmno;

                    trxshf.println(strx);
            }///while itr

            trxshf.println(send);
            trxshf.flush();
            trxshf.close();

        }//try

catch (Exception ex){
     System.out.println("from del neibor.."+ex);
}

}///func


public static void main(String[] args) {
        trxShift trx_Shiftobj = new trxShift();

        ArrayList ar= new ArrayList();
        ar=trx_Shiftobj.trx_Shift_INPUT();
        trx_Shiftobj.del_trxShift(ar);
        trx_Shiftobj.create_trxShift(ar);


    }//main

}////ends class

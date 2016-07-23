/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package appL;

import dTO.*;
import dAO.*;
import java.util.*;
import java.io.*;
/**
 *
 * @author zahurul
 */
public class freq_Change {

public static TwoArrayList FREQ_CHANGE_INPUT(){
    String str = null;
    String test = null;
    ArrayList arold = new ArrayList();
    ArrayList arnew = new ArrayList();
    TwoArrayList ta=null;

    String freq_input_file = "C:\\ranmaster\\NokiaProject\\input\\input.txt";
    //C:\\ranmaster\\NokiaProject\\input

try{
    BufferedReader inFile = new BufferedReader(new FileReader(freq_input_file));

    while ((str = inFile.readLine()) != null) {
        if (str.length() != 0) {

            long nccold=-1;
            long nccnew=-1;
            long cellid=-1;
            StringTokenizer stk = new StringTokenizer(str," ");
            for (int count = 0; stk.hasMoreTokens(); ++count) {
            trxDTO oldtrx = new trxDTO();
            trxDTO newtrx = new trxDTO();
                test = stk.nextToken();
                switch (count) {
                    case 0:{
                    cellid=Long.parseLong(test);
                    oldtrx.cellid=newtrx.cellid=cellid;
                    if(btsDAO.GetMasterbtsDATA(cellid).lac==-1){
                    System.out.println(oldtrx.cellid+" is not a valid Nokia Cell" +
                            " pls check/rectify input.");
                    System.exit(0);
                    }///if
                    }//case-0
                    break;

                    case 1:{
                    nccold=Long.parseLong(test);
                    }///holding
                    break;

                    case 2:{
                    long bccold=Long.parseLong(test);
                    boolean bsicpresent=false;
                    if(bccold!=-1 && nccold!=-1){
                    bsicpresent=true;
                    oldtrx.tsc=bccold;///hold bcc
                    oldtrx.lapdNumber=nccold;///hold ncc
                    oldtrx.cellid=cellid;
                    oldtrx.dname=btsDAO.GetMasterbtsDATA(cellid).dname;
                    arold.add(oldtrx); ////look this is trx why??
                    }
                    else if((btsDAO.GetMasterbtsDATA(cellid).ncc!=nccold ||
                            btsDAO.GetMasterbtsDATA(cellid).bcc!=bccold) && bsicpresent){
                    System.out.println("BSIC mismatch.. exiting.. cellid-"+oldtrx.cellid);
                    System.exit(0);}
                    else if((nccold*bccold<-1) && bsicpresent){
                    System.out.println("BSIC mismatch.. exiting.. cellid-"+oldtrx.cellid);
                    System.exit(0);}
                    ///else{System.out.println("unhandled case 2..");}
                    }///case 2
                    break;

                    case 3:{
                    long bcch=Long.parseLong(test);
                    trxDTO tdto=trxDAO.GettrxDATAbyFreq(cellid, bcch);

                    if(bcch!=-1){
                    if(tdto.trxid!=-1){
                    oldtrx.trxid=tdto.trxid;
                    oldtrx.dname=tdto.dname;
                    oldtrx.ifreq=bcch;
                    oldtrx.cellid=cellid;
                    oldtrx.tsc=-1;
                    oldtrx.lapdNumber=-1;
                    arold.add(oldtrx);

                    }
                    else{
                    System.out.println(cellid+" has no bcchfreq="+bcch+" exiting..");
                    System.exit(0);}
                    }
                    }///case 3
                    break;

                    case 4:{
                    long tch1=Long.parseLong(test);
                    trxDTO tdto=trxDAO.GettrxDATAbyFreq(cellid, tch1);
                    if(tch1!=-1){
                    if( tdto.trxid!=-1){
                    oldtrx.trxid=tdto.trxid;
                    oldtrx.dname=tdto.dname;
                    oldtrx.ifreq=tch1;
                    oldtrx.cellid=cellid;
                    oldtrx.tsc=-1;
                    oldtrx.lapdNumber=-1;
                    arold.add(oldtrx);
                    }
                    else{
                    System.out.println(cellid+" has no freq="+tch1+" exiting..");
                    System.exit(0);}
                    }
                    }///case 4
                    break;

                    case 5:{
                    long tch2=Long.parseLong(test);
                    trxDTO tdto=trxDAO.GettrxDATAbyFreq(cellid, tch2);
                    if(tch2!=-1){
                    if(tdto.trxid!=-1){
                    oldtrx.trxid=tdto.trxid;
                    oldtrx.dname=tdto.dname;
                    oldtrx.ifreq=tch2;
                    oldtrx.cellid=cellid;
                    oldtrx.tsc=-1;
                    oldtrx.lapdNumber=-1;
                    arold.add(oldtrx);
                    }
                    else{
                    System.out.println(cellid+" has no freq="+tch2+" exiting..");
                    System.exit(0);}
                    }
                    }///case 5
                    break;

                    case 6:{
                    long tch3=Long.parseLong(test);
                    trxDTO tdto=trxDAO.GettrxDATAbyFreq(cellid, tch3);
                    if(tch3!=-1){
                    if(tdto.trxid!=-1){
                    oldtrx.trxid=tdto.trxid;
                    oldtrx.dname=tdto.dname;
                    oldtrx.ifreq=tch3;
                    oldtrx.cellid=cellid;
                    oldtrx.tsc=-1;
                    oldtrx.lapdNumber=-1;
                    arold.add(oldtrx);
                    }
                    else{
                    System.out.println(cellid+" has no freq="+tch3+" exiting..");
                    System.exit(0);}
                    }
                    }///case 6
                    break;

                    case 7:{
                    long tch4=Long.parseLong(test);
                    trxDTO tdto=trxDAO.GettrxDATAbyFreq(cellid, tch4);
                    if(tch4!=-1){
                    if(tdto.trxid!=-1){
                    oldtrx.trxid=tdto.trxid;
                    oldtrx.dname=tdto.dname;
                    oldtrx.ifreq=tch4;
                    oldtrx.cellid=cellid;
                    oldtrx.tsc=-1;
                    oldtrx.lapdNumber=-1;
                    arold.add(oldtrx);
                    }
                    else{
                    System.out.println(cellid+" has no freq="+tch4+" exiting..");
                    System.exit(0);}
                    }
                    }///case 7
                    break;

                    case 8:{
                    long tch5=Long.parseLong(test);
                    trxDTO tdto=trxDAO.GettrxDATAbyFreq(cellid, tch5);
                    if(tch5!=-1){
                    if(tdto.trxid!=-1){
                    oldtrx.trxid=tdto.trxid;
                    oldtrx.dname=tdto.dname;
                    oldtrx.ifreq=tch5;
                    oldtrx.cellid=cellid;
                    oldtrx.tsc=-1;
                    oldtrx.lapdNumber=-1;
                    arold.add(oldtrx);
                    }
                    else{
                    System.out.println(cellid+" has no freq="+tch5+" exiting..");
                    System.exit(0);}
                    }
                    }///case 8
                    break;

                    case 9:{
                    long tch6=Long.parseLong(test);
                    trxDTO tdto=trxDAO.GettrxDATAbyFreq(cellid, tch6);
                    if(tch6!=-1){
                    if(tdto.trxid!=-1){
                    oldtrx.trxid=tdto.trxid;
                    oldtrx.dname=tdto.dname;
                    oldtrx.ifreq=tch6;
                    oldtrx.cellid=cellid;
                    oldtrx.tsc=-1;
                    oldtrx.lapdNumber=-1;
                    arold.add(oldtrx);
                    }
                    else{
                    System.out.println(cellid+" has no freq="+tch6+" exiting..");
                    System.exit(0);}
                    }
                    }///case 9
                    break;

                    case 10:{
                    long tch7=Long.parseLong(test);
                    trxDTO tdto=trxDAO.GettrxDATAbyFreq(cellid, tch7);
                    if(tch7!=-1){
                    if(tdto.trxid!=-1){
                    oldtrx.trxid=tdto.trxid;
                    oldtrx.dname=tdto.dname;
                    oldtrx.ifreq=tch7;
                    oldtrx.cellid=cellid;
                    oldtrx.tsc=-1;
                    oldtrx.lapdNumber=-1;
                    arold.add(oldtrx);
                    }
                    else{
                    System.out.println(cellid+" has no freq="+tch7+" exiting..");
                    System.exit(0);}
                    }
                    }///case 10
                    break;

                    ////new add for global change
                    case 11:{
                    long tch8=Long.parseLong(test);
                    trxDTO tdto=trxDAO.GettrxDATAbyFreq(cellid, tch8);
                    if(tch8!=-1){
                    if(tdto.trxid!=-1){
                    oldtrx.trxid=tdto.trxid;
                    oldtrx.dname=tdto.dname;
                    oldtrx.ifreq=tch8;
                    oldtrx.cellid=cellid;
                    oldtrx.tsc=-1;
                    oldtrx.lapdNumber=-1;
                    arold.add(oldtrx);
                    }
                    else{
                    System.out.println(cellid+" has no freq="+tch8+" exiting..");
                    System.exit(0);}
                    }
                    }///case 11
                    break;

                    ////new add for global change
                    case 12:{
                    long tch9=Long.parseLong(test);
                    trxDTO tdto=trxDAO.GettrxDATAbyFreq(cellid, tch9);
                    if(tch9!=-1){
                    if(tdto.trxid!=-1){
                    oldtrx.trxid=tdto.trxid;
                    oldtrx.dname=tdto.dname;
                    oldtrx.ifreq=tch9;
                    oldtrx.cellid=cellid;
                    oldtrx.tsc=-1;
                    oldtrx.lapdNumber=-1;
                    arold.add(oldtrx);
                    }
                    else{
                    System.out.println(cellid+" has no freq="+tch9+" exiting..");
                    System.exit(0);}
                    }
                    }///case 12
                    break;

                    ////new add for global change
                    case 13:{
                    long tch10=Long.parseLong(test);
                    trxDTO tdto=trxDAO.GettrxDATAbyFreq(cellid, tch10);
                    if(tch10!=-1){
                    if(tdto.trxid!=-1){
                    oldtrx.trxid=tdto.trxid;
                    oldtrx.dname=tdto.dname;
                    oldtrx.ifreq=tch10;
                    oldtrx.cellid=cellid;
                    oldtrx.tsc=-1;
                    oldtrx.lapdNumber=-1;
                    arold.add(oldtrx);
                    }
                    else{
                    System.out.println(cellid+" has no freq="+tch10+" exiting..");
                    System.exit(0);}
                    }
                    }///case 13
                    break;

                    case 14:{
                    nccnew=Long.parseLong(test);
                    }///holding ncc new
                    break;

                    case 15:{
                    long bccnew=Long.parseLong(test);
                    if(bccnew!=-1 && nccnew!=-1){
                    newtrx.tsc=bccnew;///hold new bcc
                    newtrx.lapdNumber=nccnew;///hold new ncc
                    arnew.add(newtrx);}
                    else if(nccnew*bccnew<-1){
                    System.out.println("BSIC mismatch.. exiting cellid-"+newtrx.cellid);
                    System.exit(0);}
                    }///case 15
                    break;

                    case 16:{
                    long bcch=Long.parseLong(test);
                    if(bcch!=-1){
                    newtrx.ifreq=bcch;
                    arnew.add(newtrx);}
                    }///case 16
                    break;

                    case 17:{
                    long tch1=Long.parseLong(test);
                    if(tch1!=-1){
                    newtrx.ifreq=tch1;
                    arnew.add(newtrx);}
                    }///case 17
                    break;

                    case 18:{
                    long tch2=Long.parseLong(test);
                    if(tch2!=-1){
                    newtrx.ifreq=tch2;
                    arnew.add(newtrx);}
                    }///case 18
                    break;

                    case 19:{
                    long tch3=Long.parseLong(test);
                    if(tch3!=-1){
                    newtrx.ifreq=tch3;
                    arnew.add(newtrx);}
                    }///case 19
                    break;

                    case 20:{
                    long tch4=Long.parseLong(test);
                    if(tch4!=-1){
                    newtrx.ifreq=tch4;
                    arnew.add(newtrx);}
                    }///case 20
                    break;

                    case 21:{
                    long tch5=Long.parseLong(test);
                    if(tch5!=-1){
                    newtrx.ifreq=tch5;
                    arnew.add(newtrx);}
                    }///case 21
                    break;

                    case 22:{
                    long tch6=Long.parseLong(test);
                    if(tch6!=-1){
                    newtrx.ifreq=tch6;
                    arnew.add(newtrx);}
                    }///case 22
                    break;

                    case 23:{
                    long tch7=Long.parseLong(test);
                    if(tch7!=-1){
                    newtrx.ifreq=tch7;
                    arnew.add(newtrx);}
                    }///case 23
                    break;

                    case 24:{
                    long tch8=Long.parseLong(test);
                    if(tch8!=-1){
                    newtrx.ifreq=tch8;
                    arnew.add(newtrx);}
                    }///case 24
                    break;

                    case 25:{
                    long tch9=Long.parseLong(test);
                    if(tch9!=-1){
                    newtrx.ifreq=tch9;
                    arnew.add(newtrx);}
                    }///case 25
                    break;

                    case 26:{
                    long tch10=Long.parseLong(test);
                    if(tch10!=-1){
                    newtrx.ifreq=tch10;
                    arnew.add(newtrx);}
                    }///case 26
                    break;
                }//switch
            }//for
        }//if str
    } //while str
    inFile.close();
        ta.a1=arold;
        ta.a2=arnew;

}// try

catch(Exception ex){
    System.out.println(ex);
    return null;
}

return ta; // input reading function ends here
}// ends funct



public static void FREQ_CHANGE_ALL(TwoArrayList ta){

ArrayList arold=ta.a1;
ArrayList arnew=ta.a2;

Iterator itrold=arold.iterator();
Iterator itrnew=arnew.iterator();

try{
    File f=new File("C:\\ranmaster\\NokiaProject\\input\\freq_change_Flexi.xml");
    File f2=new File("C:\\ranmaster\\NokiaProject\\input\\bsic_change_Flexi.xml");
    f.delete();f2.delete();

PrintWriter freqf = new PrintWriter(new BufferedWriter(
    new FileWriter("C:\\ranmaster\\NokiaProject\\input\\freq_change_Flexi.xml", true)));

PrintWriter bsicf = new PrintWriter(new BufferedWriter(
    new FileWriter("C:\\ranmaster\\NokiaProject\\input\\bsic_change_Flexi.xml", true)));

String sstart="<?xml version=\"1.0\"?>\n";
sstart+="<!DOCTYPE raml SYSTEM 'raml20.dtd'>\n";
sstart+="<raml version=\"2.0\" xmlns=\"raml20.xsd\">\n";
sstart+="  <cmData type=\"plan\">\n";
sstart+="    <header>\n";
sstart+="      <log dateTime=\"\" action=\"created\" user=\"blOMC\" appInfo=\"blNokiaTool\"/>\n";
sstart+="    </header>";

String send="	</cmData>\n";
send+="</raml>";

freqf.println(sstart);
bsicf.println(sstart);

while(itrold.hasNext() && itrnew.hasNext()){

    trxDTO told=(trxDTO) itrold.next();
    trxDTO tnew=(trxDTO) itrnew.next();
    tnew.cellid=told.cellid;

    told.bsc=btsDAO.GetMasterbtsDATA(told.cellid).bsc;//System.out.println(told.bsc);
    told.bcf=btsDAO.GetMasterbtsDATA(told.cellid).bcf;//System.out.println(told.bcf);
    told.bts=btsDAO.GetMasterbtsDATA(told.cellid).bts;//System.out.println(told.bts);

    String sfreq,sbsic,sprint,sfreq2,sfreq3="";

System.out.println(told.cellid+" "+told.lapdNumber+" "+told.tsc+" "+told.ifreq);//here
if(told.lapdNumber!=-1 && told.tsc!=-1 && told.ifreq==-1){ ///bsic only

sprint="<!--changing bsic of - "+told.cellid+"-"+told.dname+"-->\n";
sbsic=sprint+"\t<managedObject class=\"BTS\" distName=\""+told.dname
        +"\" operation=\"update\">\n";
sbsic+="\t<p name=\"bsIdentityCodeNCC\">"+tnew.lapdNumber+"</p>\n";
sbsic+="\t<p name=\"bsIdentityCodeBCC\">"+tnew.tsc+"</p>\n";
sbsic+="\t</managedObject>" ;
btsDTO bdto2=new btsDTO();
bdto2.cellid=tnew.cellid;
bdto2.ncc=tnew.lapdNumber;
bdto2.bcc=tnew.tsc;

//System.out.println(tnew.tsc);///////////////////////////

btsDAO.changeBTSncC(bdto2);
btsDAO.changeBTSbcC(bdto2);
bsicf.println(sbsic);///cell part

///start TRX part


for(long trx=1;trx<=32;trx++){
    tnew.trxid=told.trxid=trx;

    if(trxDAO.GettrxDATA(told).lapdNumber!=-1){
    //System.out.println(trxDAO.GettrxDATA(told).lapdNumber);
    ///trx code
sfreq2="\t<managedObject class=\"TRX\" distName=\""+told.dname
        +"/TRX-"+trx+"\" operation=\"update\">\n";
sfreq2+="\t<p name=\"tsc\">"+tnew.tsc+"</p>\n";
sfreq2+="\t</managedObject>";

trxDAO.changeTRXtsC(tnew);
bsicf.println(sfreq2);
    }///if first cell finished
}///for

    btsDTO bin=new btsDTO();

    bin.bsc=told.bsc;
    bin.bcf=told.bcf;
    bin.bts=told.bts;
    bin.cellid=told.cellid;

    told.bts=btsDAO.GetNextbtsDATA(bin).bts;

    for(long trx=1;trx<=32;trx++){  ///mbc bts trx
    told.trxid=trx;

    if(trxDAO.GettrxDATA(told).lapdNumber!=-1){
    //System.out.println(trxDAO.GettrxDATA(told).lapdNumber);
    ///trx code
sfreq3="\t<managedObject class=\"TRX\" distName=\"PLMN-PLMN/BSC-"+bin.bsc+
        "/BCF-"+bin.bcf+"/BTS-"+told.bts+"/TRX-"+trx+"\" operation=\"update\">\n";
sfreq3+="\t<p name=\"tsc\">"+tnew.tsc+"</p>\n";
sfreq3+="\t</managedObject>" ;
bsicf.println(sfreq3);
    }///if first cell finished
}///for


}////BSIC only code ends

else if(told.lapdNumber==-1 && told.tsc==-1 && told.ifreq!=-1){ //freq only

tnew.trxid=told.trxid;
//need to find correct BTS number in case of the MBC cells
sprint="<!--changing frequency of TRX - "+told.dname+"/TRX-"+told.trxid+"-->\n";
sfreq=sprint+"\t<managedObject class=\"TRX\" distName=\""+told.dname+"/TRX-"+told.trxid
        +"\" operation=\"update\">\n";;
sfreq+="\t<p name=\"initialFrequency\">"+tnew.ifreq+"</p>\n";
sfreq+="\t</managedObject>" ;

trxDAO.changeTRXfreQ(tnew);
freqf.println(sfreq);
}

}////while

    freqf.println(send);bsicf.println(send);
    freqf.flush();bsicf.flush();
    freqf.close();bsicf.close();
}///try
catch(Exception ex){
    System.out.println(ex);}

}///func

public static void delFiles(){
    File f=new File("C:\\ranmaster\\NokiaProject\\input\\freq_change_Flexi.xml");
    File f2=new File("C:\\ranmaster\\NokiaProject\\input\\bsic_change_Flexi.xml");
    if(f.length()==246){f.delete();}
    if(f2.length()==246){f2.delete();}
}

public static void main(String[] args) {
        freq_Change fchange = new freq_Change();
        TwoArrayList ta=new TwoArrayList();
        ta=fchange.FREQ_CHANGE_INPUT();
        fchange.FREQ_CHANGE_ALL(ta);
        delFiles();
}///main

}////class

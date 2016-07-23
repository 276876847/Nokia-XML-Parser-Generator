
package nokiaParse;

/**
 *
 * @author zahurul
 */

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;
import java.util.*;
import dTO.bcfDTO;
import dTO.btsDTO;
import dTO.adjDTO;
import dTO.chnDTO;
import dTO.trxDTO;
import dTO.dapDTO;
import dTO.lpdDTO;
import dAO.*;
import chkTAG.*;

public class parseNokiaCM extends DefaultHandler {

public static String CurrDate()
{
    Date d=new Date();

    String p=d.toString();

    String month=p.substring(4,7);
    String day=  p.substring(8,10);
    String year=  p.substring(26,28);
    //System.out.println("p is...." + p);

    if(month.equals("Jan")){
    month="01";
    }
    else if(month.equals("Feb")){
        month="02";
    }
    else if(month.equals("Mar")){
            month="03";
    }
    else if(month.equals("Apr")){
    month="04";
    }
    else if(month.equals("May")){
    month="05";
    }
    else if(month.equals("Jun")){
    month="06";
    }
    else if(month.equals("Jul")){
    month="07";
    }
    else if(month.equals("Aug")){
    month="08";
    }
    else if(month.equals("Sep")){
        month="09";
    }
    else if(month.equals("Oct")){
    month="10";
    }
    else if(month.equals("Nov")){
    month="11";
    }
    else if(month.equals("Dec")){
    month="12";
    }

String date=day+month+year;
   //System.out.println("date is "+date);
   System.out.println("time is "+p);
return date;
}


   private CharArrayWriter contents = new CharArrayWriter();

   bcfDTO currentBCF = new bcfDTO();
   btsDTO currentBTS = new btsDTO();
   adjDTO currentADJ = new adjDTO();
   trxDTO currentTRX = new trxDTO();
   chnDTO currentCHN = new chnDTO(); //RG10
   dapDTO currentDAP = new dapDTO();
   lpdDTO currentLPD = new lpdDTO();

   //introduced for RG20 BSC
   chnDTO currentCHN0 = new chnDTO();
   chnDTO currentCHN1 = new chnDTO();
   chnDTO currentCHN2 = new chnDTO();
   chnDTO currentCHN3 = new chnDTO();
   chnDTO currentCHN4 = new chnDTO();
   chnDTO currentCHN5 = new chnDTO();
   chnDTO currentCHN6 = new chnDTO();
   chnDTO currentCHN7 = new chnDTO();

   Vector bcfVector=new Vector();
   Vector btsVector=new Vector();
   Vector adjVector=new Vector();
   Vector trxVector=new Vector();
   Vector chnVector=new Vector();
   Vector dapVector=new Vector();
   Vector lpdVector=new Vector();

   boolean newbcf=false;
   boolean newbts=false;
   boolean newadj=false;
   boolean newtrx=false;
   boolean newchn=false;
   boolean newdap=false;
   boolean newlpd=false;

   chkbcfTAG ckbcf=new chkbcfTAG();
   chkbtsTAG ckbts=new chkbtsTAG();
   chkadjTAG ckadj=new chkadjTAG();
   chktrxTAG cktrx=new chktrxTAG();
   chkchnTAG ckchn=new chkchnTAG();
   chkdapTAG ckdap=new chkdapTAG();
   chklpdTAG cklpd=new chklpdTAG();

   StringBuffer sbuf=new StringBuffer();
   StringBuilder sbuild=new StringBuilder();

   String currentElement = null;
   HashMap elementValues = new HashMap();

   long test=-1;
   String pooh="";
   public void startElement( String namespaceURI,String localName,String qName,
               Attributes attr)  throws SAXException {

try{
    currentElement = qName;
    elementValues.put(currentElement, new StringBuffer());

//BCF start
   if( localName.equals("managedObject") && attr.getValue("class").equals("BCF")){
       currentBCF=new bcfDTO(); //initialize
       newbcf=true;
       currentBCF.ver=attr.getValue(1);
       currentBCF.dname=attr.getValue(2);
       currentBCF.sid=Long.parseLong(attr.getValue(3));
       ///add it here
      }

if(newbcf){
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("latitude")){  ckbcf.islatitude=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("longitude")){  ckbcf.islongitude=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("name")){ ckbcf.isname=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("adminState")){  ckbcf.isadminState=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("autoConfig")){  ckbcf.isautoConfig=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("batteryBackupProcedure")){ckbcf.isbatteryBackupProcedure=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("bcchTrxBatbuTimer")){ ckbcf.isbcchTrxBatbuTimer=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("bcfType")){  ckbcf.isbcfType=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("btsSiteSubtype")){ ckbcf.isbtsSiteSubtype=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("lapdLinkName")){  ckbcf.islapdLinkName=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("lapdLinkNumber")){  ckbcf.islapdLinkNumber=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("normTrxBatbuTimer")){ ckbcf.isnormTrxBatbuTimer=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("pcmPortId0")){  ckbcf.ispcmPortId0=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("pcmPortId1")){   ckbcf.ispcmPortId1=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("pcmPortId2")){  ckbcf.ispcmPortId2=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("pcmPortId3")){   ckbcf.ispcmPortId3=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("pcmPortId4")){   ckbcf.ispcmPortId4=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("pcmPortId5")){ ckbcf.ispcmPortId5=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("pcmPortId6")){  ckbcf.ispcmPortId6=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("pcmPortId7")){ ckbcf.ispcmPortId7=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("autoUnlAllowed")){  ckbcf.isautoUnlAllowed=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("clockSource")){ ckbcf.isclockSource=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
   equals("flexiEdgeTrsAbisGroomingUsage")){ckbcf.isflexiEdgeTrsAbisGroomingUsage=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("masterClockBcf")){ckbcf.ismasterClockBcf=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("rxDifferenceLimit")){ckbcf.isrxDifferenceLimit=true;}
}//newbcf

//BCF ends

//BTS start
if(localName.equals("managedObject") && attr.getValue("class").equals("BTS")){

       currentBTS=new btsDTO();  //initialize
       newbts=true;
       currentBTS.ver=attr.getValue(1);
       currentBTS.dname=attr.getValue(2);
       currentBTS.sid=Long.parseLong(attr.getValue(3));
    }
if(newbts ){
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("frequencyBandInUse")){  ckbts.isfrequencyBandInUse=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("name")){  ckbts.isname=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("adminState")){  ckbts.isadminState=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("bsIdentityCodeBCC")){  ckbts.isbsIdentityCodeBCC=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("bsIdentityCodeNCC")){  ckbts.isbsIdentityCodeNCC=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("btsIsHopping")){  ckbts.isbtsIsHopping=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("carrierUnitConfiguration")){  ckbts.iscarrierUnitConfiguration=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("cellBarQualify")){  ckbts.iscellBarQualify=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("cellBarred")){  ckbts.iscellBarred=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("cellId")){  ckbts.iscellId=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("cellNumberInBtsHw")){  ckbts.iscellNumberInBtsHw=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("cellReselectHysteresis")){  ckbts.iscellReselectHysteresis=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("cellReselectOffset")){  ckbts.iscellReselectOffset=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("cellReselectParamInd")){  ckbts.iscellReselectParamInd=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("cs3Cs4Enabled")){  ckbts.iscs3Cs4Enabled=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("dedicatedGPRScapacity")){  ckbts.isdedicatedGPRScapacity=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("diversityUsed")){  ckbts.isdiversityUsed=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("egprsEnabled")){  ckbts.isegprsEnabled=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("gprsEnabled")){  ckbts.isgprsEnabled=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("hoppingMode")){  ckbts.ishoppingMode=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("hoppingSequenceNumber1")){  ckbts.ishoppingSequenceNumber1=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("hoppingSequenceNumber2")){  ckbts.ishoppingSequenceNumber2=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("hoppingSequenceNumber3")){  ckbts.ishoppingSequenceNumber3=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("locationAreaIdLAC")){  ckbts.islocationAreaIdLAC=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("locationAreaIdMCC")){  ckbts.islocationAreaIdMCC=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("locationAreaIdMNC")){  ckbts.islocationAreaIdMNC=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("maioOffset")){  ckbts.ismaioOffset=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("maioStep")){  ckbts.ismaioStep=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("msTxPwrMaxCCH")){  ckbts.ismsTxPwrMaxCCH=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("msTxPwrMaxGSM")){  ckbts.ismsTxPwrMaxGSM=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("multiBandCell")){  ckbts.ismultiBandCell=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("nsei")){  ckbts.isnsei=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("nwName")){  ckbts.isnwName=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("rac")){  ckbts.israc=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("radioLinkTimeout")){  ckbts.isradioLinkTimeout=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("sectorId")){  ckbts.issectorId=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("segmentId")){  ckbts.issegmentId=true;}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("segmentName")){  ckbts.issegmentName=true; }
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("masterBcf")){ckbts.ismasterBcf=true; }
}//newbts
//BTS ends


//ADJ start
   if( localName.equals("managedObject") && attr.getValue("class").equals("ADCE")){
       currentADJ=new adjDTO();  //initialize
       newadj=true;
       currentADJ.ver=attr.getValue(1);
       currentADJ.dname=attr.getValue(2);
       currentADJ.sid=Long.parseLong(attr.getValue(3));
      }

if(newadj){
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("frequencyBandInUse")){ckadj.isfrequencyBandInUse=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("targetCellDN")){ckadj.istargetCellDN=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("adjCellBsicBcc")){ckadj.isadjCellBsicBcc=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("adjCellBsicNcc")){ckadj.isadjCellBsicNcc=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("adjacentCellIdCI")){ckadj.isadjacentCellIdCI=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("adjacentCellIdLac")){ckadj.isadjacentCellIdLac=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("adjacentCellIdMCC")){ckadj.isadjacentCellIdMCC=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("adjacentCellIdMNC")){ckadj.isadjacentCellIdMNC=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("adjcIndex")){ckadj.isadjcIndex=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("bcchFrequency")){ckadj.isbcchFrequency=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("gprsEnabled")){ckadj.isgprsEnabled=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("synchronized")){ckadj.issynchronized=true;}
}//newadj

//ADJ ends

//TRX start
   if(localName.equals("managedObject") && attr.getValue("class").equals("TRX")){

       currentTRX=new trxDTO(); //initialize
       newtrx=true;
       currentTRX.ver=attr.getValue(1);
       currentTRX.dname=attr.getValue(2);
       currentTRX.sid=Long.parseLong(attr.getValue(3));
   }

if(newtrx){
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("adminState")){cktrx.isadminState=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("autoConfig")){cktrx.isautoConfig=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("daPool_ID")){cktrx.isdaPool_ID=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("gprsEnabledTrx")){cktrx.isgprsEnabledTrx=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("halfRateSupport")){cktrx.ishalfRateSupport=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("initialFrequency")){cktrx.isinitialFrequency=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("lapdLinkName")){cktrx.islapdLinkName=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("lapdLinkNumber")){cktrx.islapdLinkNumber=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("rfHoppingAllowed")){cktrx.isrfHoppingAllowed=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("subslotsForSignalling")){cktrx.issubslotsForSignalling=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("trxFrequencyType")){cktrx.istrxFrequencyType=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("name")){cktrx.isname=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("tsc")){cktrx.istsc=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("eTrxInd")){cktrx.iseTrxInd=true;}
//CHN0 start
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel0AdminState")){ckchn.ischannel0AdminState=true;
       ckchn.chn0=true;
       currentCHN0=new chnDTO(); //initialize
       currentCHN0.ver=currentTRX.ver;
       currentCHN0.dname=currentTRX.dname;
       currentCHN0.sid=currentTRX.sid;
    }

if( ckchn.chn0 ){
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel0Maio")){ckchn.ischannel0Maio=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel0Pcm")){ckchn.ischannel0Pcm=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel0Subslot")){ckchn.ischannel0Subslot=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel0Tsl")){ckchn.ischannel0Tsl=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel0Type")){ckchn.ischannel0Type=true;}
}//chn0
///
//CHN0 ends

//CHN1 start
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel1AdminState")){ckchn.ischannel1AdminState=true;
       ckchn.chn1=true;
       currentCHN1=new chnDTO(); //initialize
       currentCHN1.ver=currentTRX.ver;
       currentCHN1.dname=currentTRX.dname;
       currentCHN1.sid=currentTRX.sid;
    }
if( ckchn.chn1 ){
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel1Maio")){ckchn.ischannel1Maio=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel1Pcm")){ckchn.ischannel1Pcm=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel1Subslot")){ckchn.ischannel1Subslot=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel1Tsl")){ckchn.ischannel1Tsl=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel1Type")){ckchn.ischannel1Type=true;}
}//chn1
//CHN1 ends

//CHN2 start
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel2AdminState")){ckchn.ischannel2AdminState=true;
       ckchn.chn2=true;
       currentCHN2=new chnDTO(); //initialize
       currentCHN2.ver=currentTRX.ver;
       currentCHN2.dname=currentTRX.dname;
       currentCHN2.sid=currentTRX.sid;
    }
if( ckchn.chn2 ){
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel2Maio")){ckchn.ischannel2Maio=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel2Pcm")){ckchn.ischannel2Pcm=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel2Subslot")){ckchn.ischannel2Subslot=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel2Tsl")){ckchn.ischannel2Tsl=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel2Type")){ckchn.ischannel2Type=true;}
}//chn2
//CHN2 ends

//CHN3 start
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel3AdminState")){ckchn.ischannel3AdminState=true;
       ckchn.chn3=true;
       currentCHN3=new chnDTO(); //initialize
       currentCHN3.ver=currentTRX.ver;
       currentCHN3.dname=currentTRX.dname;
       currentCHN3.sid=currentTRX.sid;
    }
if( ckchn.chn3 ){
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel3Maio")){ckchn.ischannel3Maio=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel3Pcm")){ckchn.ischannel3Pcm=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel3Subslot")){ckchn.ischannel3Subslot=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel3Tsl")){ckchn.ischannel3Tsl=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel3Type")){ckchn.ischannel3Type=true;}
}//chn3
//CHN3 ends

//CHN4 start
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel4AdminState")){ckchn.ischannel4AdminState=true;
       ckchn.chn4=true;
       currentCHN4=new chnDTO(); //initialize
       currentCHN4.ver=currentTRX.ver;
       currentCHN4.dname=currentTRX.dname;
       currentCHN4.sid=currentTRX.sid;
    }
if( ckchn.chn4 ){
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel4Maio")){ckchn.ischannel4Maio=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel4Pcm")){ckchn.ischannel4Pcm=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel4Subslot")){ckchn.ischannel4Subslot=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel4Tsl")){ckchn.ischannel4Tsl=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel4Type")){ckchn.ischannel4Type=true;}
}//chn4
//CHN4 ends

//CHN5 start
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel5AdminState")){ckchn.ischannel5AdminState=true;
       ckchn.chn5=true;
       currentCHN5=new chnDTO(); //initialize
       currentCHN5.ver=currentTRX.ver;
       currentCHN5.dname=currentTRX.dname;
       currentCHN5.sid=currentTRX.sid;
    }
if( ckchn.chn5 ){
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel5Maio")){ckchn.ischannel5Maio=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel5Pcm")){ckchn.ischannel5Pcm=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel5Subslot")){ckchn.ischannel5Subslot=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel5Tsl")){ckchn.ischannel5Tsl=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel5Type")){ckchn.ischannel5Type=true;}
}//chn5
//CHN5 ends

//CHN6 start
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel6AdminState")){ckchn.ischannel6AdminState=true;
       ckchn.chn6=true;
       currentCHN6=new chnDTO(); //initialize
       currentCHN6.ver=currentTRX.ver;
       currentCHN6.dname=currentTRX.dname;
       currentCHN6.sid=currentTRX.sid;
    }
if( ckchn.chn6 ){
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel6Maio")){ckchn.ischannel6Maio=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel6Pcm")){ckchn.ischannel6Pcm=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel6Subslot")){ckchn.ischannel6Subslot=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel6Tsl")){ckchn.ischannel6Tsl=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel6Type")){ckchn.ischannel6Type=true;}
}//chn6
//CHN6 ends

//CHN7 start
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel7AdminState")){ckchn.ischannel7AdminState=true;
       ckchn.chn7=true;
       currentCHN7=new chnDTO(); //initialize
       currentCHN7.ver=currentTRX.ver;
       currentCHN7.dname=currentTRX.dname;
       currentCHN7.sid=currentTRX.sid;
    }
if( ckchn.chn7 ){
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel7Maio")){ckchn.ischannel7Maio=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel7Pcm")){ckchn.ischannel7Pcm=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel7Subslot")){ckchn.ischannel7Subslot=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel7Tsl")){ckchn.ischannel7Tsl=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("channel7Type")){ckchn.ischannel7Type=true;}
}//chn7
//CHN7 ends
}//newtrx
//TRX ends

//DAP start
   if( localName.equals("managedObject") && attr.getValue("class").equals("DAP")){

       currentDAP=new dapDTO(); //initialize
       newdap=true;
       currentDAP.ver=attr.getValue(1);
       currentDAP.dname=attr.getValue(2);
       currentDAP.sid=Long.parseLong(attr.getValue(3));
      }
if(newdap){
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("bcsuID")){ckdap.isbcsuID=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("firstTSL")){ckdap.isfirstTSL=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("lastTSL")){ckdap.islastTSL=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("logicalBCSUAddress")){ckdap.islogicalBCSUAddress=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("pcmCircuit_ID")){ckdap.ispcmCircuit_ID=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("pcuID")){ckdap.ispcuID=true;}

}//newdap

//DAP ends

//LPD start
   if( localName.equals("managedObject") && attr.getValue("class").equals("LAPD")){
       currentLPD=new lpdDTO(); //initialize
       newlpd=true;
       currentLPD.ver=attr.getValue(1);
       currentLPD.dname=attr.getValue(2);
       currentLPD.sid=Long.parseLong(attr.getValue(3));
      }
if(newlpd){
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("bitRate")){cklpd.isbitRate=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("abisSigChannelTimeSlotPcm")){cklpd.isabisSigChannelTimeSlotPcm=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("abisSigChannelTimeSlotTsl")){cklpd.isabisSigChannelTimeSlotTsl=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("abisSigChannelSubSlot")){cklpd.isabisSigChannelSubSlot=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("adminState")){cklpd.isadminState=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("dChannelType")){cklpd.isdChannelType=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("logicalBCSUAddress")){cklpd.islogicalBCSUAddress=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("name")){cklpd.isname=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("parameterSetNumber")){cklpd.isparameterSetNumber=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("sapi")){cklpd.issapi=true;}
if(localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
equals("tei")){cklpd.istei=true;}
}//newlpd

//LPD ends

    }//try
    catch(Exception ex){System.out.println("Error:  "+ ex );}

    }//func

public void endElement( String namespaceURI,String localName,
          String qName) throws SAXException {

    String value = ((StringBuffer)(elementValues.get(qName))).toString();

if(!value.equals("")){
//BCF start
if(newbcf){
if(ckbcf.islatitude) {currentBCF.lat=Long.parseLong(value);
ckbcf.islatitude=false;}
if(ckbcf.islongitude) {currentBCF.longg=Long.parseLong(value);
ckbcf.islongitude=false;}
if(ckbcf.isname) {currentBCF.name=value;ckbcf.isname=false;}
if(ckbcf.isadminState) {currentBCF.admstate=Long.parseLong(value);
ckbcf.isadminState=false;}
if(ckbcf.isautoConfig) {currentBCF.autocfg=Long.parseLong(value);
ckbcf.isautoConfig=false;}
if(ckbcf.isbatteryBackupProcedure) {currentBCF.bbupr=
Long.parseLong(value);ckbcf.isbatteryBackupProcedure=false;}
if(ckbcf.isbcchTrxBatbuTimer) {currentBCF.bcchbbuTimer=
Long.parseLong(value);ckbcf.isbcchTrxBatbuTimer=false;}
if(ckbcf.isbcfType) {currentBCF.bcfType=Long.parseLong(value);
ckbcf.isbcfType=false;}
if(ckbcf.isbtsSiteSubtype) {currentBCF.siteSubtype=
Long.parseLong(value); ckbcf.isbtsSiteSubtype=false;}
if(ckbcf.islapdLinkName) {currentBCF.lapdName=value;
ckbcf.islapdLinkName=false;}
if(ckbcf.islapdLinkNumber) {currentBCF.lapdNumber=Long.parseLong(
value);ckbcf.islapdLinkNumber=false;}
if(ckbcf.isnormTrxBatbuTimer) {currentBCF.ntrxbbuTimer=Long.parseLong(
value);ckbcf.isnormTrxBatbuTimer=false;}
if(ckbcf.ispcmPortId0) {currentBCF.port0=Long.parseLong(value);
ckbcf.ispcmPortId0=false;}
if(ckbcf.ispcmPortId1) {currentBCF.port1=Long.parseLong(value);
ckbcf.ispcmPortId1=false;}
if(ckbcf.ispcmPortId2) {currentBCF.port2=Long.parseLong(value);
ckbcf.ispcmPortId2=false;}
if(ckbcf.ispcmPortId3) {currentBCF.port3=Long.parseLong(value);
ckbcf.ispcmPortId3=false;}
if(ckbcf.ispcmPortId4) {currentBCF.port4=Long.parseLong(value);
ckbcf.ispcmPortId4=false;}
if(ckbcf.ispcmPortId5) {currentBCF.port5=Long.parseLong(value);
ckbcf.ispcmPortId5=false;}
if(ckbcf.ispcmPortId6) {currentBCF.port6=Long.parseLong(value);
ckbcf.ispcmPortId6=false;}
if(ckbcf.ispcmPortId7) {currentBCF.port7=Long.parseLong(value);
ckbcf.ispcmPortId7=false;}
if(ckbcf.isautoUnlAllowed) {currentBCF.autounl=Long.parseLong(value);
ckbcf.isautoUnlAllowed=false;}
if(ckbcf.isclockSource) {currentBCF.clksrc=Long.parseLong(value);
ckbcf.isclockSource=false;}
if(ckbcf.isflexiEdgeTrsAbisGroomingUsage) {currentBCF.ftrsAbisgrm=
Long.parseLong(value);ckbcf.isflexiEdgeTrsAbisGroomingUsage=false;}
if(ckbcf.ismasterClockBcf) {currentBCF.mclkBCF=Long.parseLong(value);
ckbcf.ismasterClockBcf=false;}
if(ckbcf.isrxDifferenceLimit) {currentBCF.rxdLimit=Long.parseLong(
value);ckbcf.isrxDifferenceLimit=false; bcfVector.addElement(currentBCF);newbcf=false;}
}//BCF end


///BTS starts
if(newbts){
if(ckbts.isfrequencyBandInUse) {currentBTS.fband=Long.parseLong(value);
ckbts.isfrequencyBandInUse=false;}
if(ckbts.isname) {currentBTS.name=value; ckbts.isname=false;}
if(ckbts.isadminState) {currentBTS.admstate=Long.parseLong(value);
ckbts.isadminState=false;}
if(ckbts.isbsIdentityCodeBCC) {currentBTS.bcc=Long.parseLong(value);
ckbts.isbsIdentityCodeBCC=false;}
if(ckbts.isbsIdentityCodeNCC) {currentBTS.ncc=Long.parseLong(value);
ckbts.isbsIdentityCodeNCC=false;}
if(ckbts.isbtsIsHopping) {currentBTS.hopp=Long.parseLong(value);
ckbts.isbtsIsHopping=false;}
if(ckbts.iscarrierUnitConfiguration) {currentBTS.cuconf=Long.parseLong(value);
ckbts.iscarrierUnitConfiguration=false;}
if(ckbts.iscellBarQualify) {currentBTS.cbq=Long.parseLong(value);
ckbts.iscellBarQualify=false;}
if(ckbts.iscellBarred) {currentBTS.cbarr=Long.parseLong(value);
ckbts.iscellBarred=false;}
if(ckbts.iscellId) {currentBTS.cellid=Long.parseLong(value);
ckbts.iscellId=false;}
if(ckbts.iscellNumberInBtsHw) {currentBTS.cnHW=Long.parseLong(value);
ckbts.iscellNumberInBtsHw=false;}
if(ckbts.iscellReselectHysteresis) {currentBTS.cellresh=Long.parseLong(value);
ckbts.iscellReselectHysteresis=false;}
if(ckbts.iscellReselectOffset) {currentBTS.croff=Long.parseLong(value);
ckbts.iscellReselectOffset=false;}
if(ckbts.iscellReselectParamInd) {currentBTS.cpari=Long.parseLong(value);
ckbts.iscellReselectParamInd=false;}
if(ckbts.iscs3Cs4Enabled) {currentBTS.cs34sup=Long.parseLong(value);
ckbts.iscs3Cs4Enabled=false;}
if(ckbts.isdedicatedGPRScapacity) {currentBTS.dedgprs=Long.parseLong(value);
ckbts.isdedicatedGPRScapacity=false;}
if(ckbts.isdiversityUsed) {currentBTS.divuse=Long.parseLong(value);
ckbts.isdiversityUsed=false;}
if(ckbts.isegprsEnabled) {currentBTS.egena=Long.parseLong(value);
ckbts.isegprsEnabled=false;}
if(ckbts.isgprsEnabled) {currentBTS.gena=Long.parseLong(value);
ckbts.isgprsEnabled=false;}
if(ckbts.ishoppingMode) {currentBTS.hopmode=Long.parseLong(value);
ckbts.ishoppingMode=false;}
if(ckbts.ishoppingSequenceNumber1) {currentBTS.hsn1=Long.parseLong(value);
ckbts.ishoppingSequenceNumber1=false;}
if(ckbts.ishoppingSequenceNumber2) {currentBTS.hsn2=Long.parseLong(value);
ckbts.ishoppingSequenceNumber2=false;}
if(ckbts.ishoppingSequenceNumber3) {currentBTS.hsn3=Long.parseLong(value);
ckbts.ishoppingSequenceNumber3=false;}
if(ckbts.islocationAreaIdLAC) {currentBTS.lac=Long.parseLong(value);
ckbts.islocationAreaIdLAC=false;}
if(ckbts.islocationAreaIdMCC) {currentBTS.mcc=Long.parseLong(value);
ckbts.islocationAreaIdMCC=false;}
if(ckbts.islocationAreaIdMNC) {currentBTS.mnc=Long.parseLong(value);
ckbts.islocationAreaIdMNC=false;}
if(ckbts.ismaioOffset) {currentBTS.moff=Long.parseLong(value);
ckbts.ismaioOffset=false;}
if(ckbts.ismaioStep) {currentBTS.mstep=Long.parseLong(value);
ckbts.ismaioStep=false;}
if(ckbts.ismsTxPwrMaxCCH) {currentBTS.mspwrmcch=Long.parseLong(value);
ckbts.ismsTxPwrMaxCCH=false;}
if(ckbts.ismsTxPwrMaxGSM) {currentBTS.mspwrmgsm=Long.parseLong(value);
ckbts.ismsTxPwrMaxGSM=false;}
if(ckbts.ismultiBandCell) {currentBTS.mbc=Long.parseLong(value);
ckbts.ismultiBandCell=false;}
if(ckbts.isnsei) {currentBTS.nsei=Long.parseLong(value);
ckbts.isnsei=false;}
if(ckbts.isnwName) {currentBTS.nwname=value; ckbts.isnwName=false;}
if(ckbts.israc) {currentBTS.rac=Long.parseLong(value);
ckbts.israc=false;}
if(ckbts.isradioLinkTimeout) {currentBTS.rlt=Long.parseLong(value);
ckbts.isradioLinkTimeout=false;}
if(ckbts.issectorId) {currentBTS.sectorid=Long.parseLong(value);
ckbts.issectorId=false;}
if(ckbts.issegmentId) {currentBTS.segid=Long.parseLong(value);
ckbts.issegmentId=false;}
if(ckbts.ismasterBcf) {currentBTS.mbcf=Long.parseLong(value);ckbts.ismasterBcf=false;}
if(ckbts.issegmentName) {currentBTS.segname=value;ckbts.issegmentName=false;
btsVector.addElement(currentBTS);newbts=false;
}
}//BTS ends

//ADJ start
if(newadj){
if(ckadj.isfrequencyBandInUse) {currentADJ.fband=Long.parseLong(value);
ckadj.isfrequencyBandInUse=false;}
if(ckadj.istargetCellDN) {currentADJ.tgtDN=value;
 ckadj.istargetCellDN=false;}
if(ckadj.isadjCellBsicBcc) {currentADJ.tgtbcc=Long.parseLong(value);
 ckadj.isadjCellBsicBcc=false;}
if(ckadj.isadjCellBsicNcc) {currentADJ.tgtncc=Long.parseLong(value);
 ckadj.isadjCellBsicNcc=false;}
if(ckadj.isadjacentCellIdCI) {currentADJ.targetid=Long.parseLong(value);
 ckadj.isadjacentCellIdCI=false;}
if(ckadj.isadjacentCellIdLac) {currentADJ.tgtlac=Long.parseLong(value);
 ckadj.isadjacentCellIdLac=false;}
if(ckadj.isadjacentCellIdMCC) {currentADJ.tgtmcc=Long.parseLong(value);
ckadj.isadjacentCellIdMCC=false;}
if(ckadj.isadjacentCellIdMNC) {currentADJ.tgtmnc=Long.parseLong(value);
 ckadj.isadjacentCellIdMNC=false;}
if(ckadj.isadjcIndex) {currentADJ.adjIndex=Long.parseLong(value);
 ckadj.isadjcIndex=false;}
if(ckadj.isbcchFrequency) {currentADJ.tgtbcch=Long.parseLong(value);
 ckadj.isbcchFrequency=false;}
if(ckadj.isgprsEnabled) {currentADJ.tgtgena=Long.parseLong(value);
 ckadj.isgprsEnabled=false;}
if(ckadj.issynchronized) {currentADJ.sync=Long.parseLong(value);
 ckadj.issynchronized=false; adjVector.addElement(currentADJ); newadj=false;}
}//ADJ end


//TRX start
if(newtrx){
if(cktrx.isadminState) {currentTRX.admstate=Long.parseLong(value);
cktrx.isadminState=false;}
if(cktrx.isautoConfig) {currentTRX.autocfg=Long.parseLong(value);
cktrx.isautoConfig=false;}
if(cktrx.isdaPool_ID) {currentTRX.dapid=Long.parseLong(value);
cktrx.isdaPool_ID=false;}
if(cktrx.isgprsEnabledTrx) {currentTRX.gena=Long.parseLong(value);
cktrx.isgprsEnabledTrx=false;}
if(cktrx.ishalfRateSupport) {currentTRX.hrsup=Long.parseLong(value);
 cktrx.ishalfRateSupport=false;}
if(cktrx.isinitialFrequency) {currentTRX.ifreq=Long.parseLong(value);
 cktrx.isinitialFrequency=false;}
if(cktrx.islapdLinkName) {currentTRX.lapdName=value;
cktrx.islapdLinkName=false;}
if(cktrx.islapdLinkNumber) {currentTRX.lapdNumber=Long.parseLong(value);
 cktrx.islapdLinkNumber=false;}
if(cktrx.isrfHoppingAllowed) {currentTRX.rfhoppal=Long.parseLong(value);
 cktrx.isrfHoppingAllowed=false;}
if(cktrx.issubslotsForSignalling) {currentTRX.sslSignalling=Long.parseLong(value);
cktrx.issubslotsForSignalling=false;}
if(cktrx.istrxFrequencyType) {currentTRX.freqType=Long.parseLong(value);
cktrx.istrxFrequencyType=false;}
if(cktrx.isname){currentTRX.name=value;cktrx.isname=false;}
if(cktrx.iseTrxInd){currentTRX.eTrxInd=Long.parseLong(value);cktrx.iseTrxInd=false;}
if(cktrx.istsc){currentTRX.tsc=Long.parseLong(value);cktrx.istsc=false;
trxVector.addElement(currentTRX); newtrx=false;
}
//TRX end

//CHN0 start
if(ckchn.ischannel0AdminState) {currentCHN0.chnadmstate=Long.parseLong(value);
currentCHN0.chanid=0;ckchn.ischannel0AdminState=false;}
if(ckchn.ischannel0Maio) {currentCHN0.maio=Long.parseLong(value);
 ckchn.ischannel0Maio=false;}
if(ckchn.ischannel0Pcm) {currentCHN0.pcm=Long.parseLong(value);
 ckchn.ischannel0Pcm=false;}
if(ckchn.ischannel0Subslot) {currentCHN0.subslot=Long.parseLong(value);
 ckchn.ischannel0Subslot=false;}
if(ckchn.ischannel0Tsl) {currentCHN0.tsl=Long.parseLong(value);
 ckchn.ischannel0Tsl=false;}
if(ckchn.ischannel0Type) {currentCHN0.chntype=Long.parseLong(value);
 ckchn.ischannel0Type=false;chnVector.addElement(currentCHN0);ckchn.chn0=false;}
//CHN0 end

//CHN1 start
if(ckchn.ischannel1AdminState) {currentCHN1.chnadmstate=Long.parseLong(value);
currentCHN1.chanid=1;ckchn.ischannel1AdminState=false;}
if(ckchn.ischannel1Maio) {currentCHN1.maio=Long.parseLong(value);
 ckchn.ischannel1Maio=false;}
if(ckchn.ischannel1Pcm) {currentCHN1.pcm=Long.parseLong(value);
 ckchn.ischannel1Pcm=false;}
if(ckchn.ischannel1Subslot) {currentCHN1.subslot=Long.parseLong(value);
 ckchn.ischannel1Subslot=false;}
if(ckchn.ischannel1Tsl) {currentCHN1.tsl=Long.parseLong(value);
 ckchn.ischannel1Tsl=false;}
if(ckchn.ischannel1Type) {currentCHN1.chntype=Long.parseLong(value);
 ckchn.ischannel1Type=false;chnVector.addElement(currentCHN1);ckchn.chn1=false;}
//CHN1 end

//CHN2 start
if(ckchn.ischannel2AdminState) {currentCHN2.chnadmstate=Long.parseLong(value);
currentCHN2.chanid=2;ckchn.ischannel2AdminState=false;}
if(ckchn.ischannel2Maio) {currentCHN2.maio=Long.parseLong(value);
 ckchn.ischannel2Maio=false;}
if(ckchn.ischannel2Pcm) {currentCHN2.pcm=Long.parseLong(value);
 ckchn.ischannel2Pcm=false;}
if(ckchn.ischannel2Subslot) {currentCHN2.subslot=Long.parseLong(value);
 ckchn.ischannel2Subslot=false;}
if(ckchn.ischannel2Tsl) {currentCHN2.tsl=Long.parseLong(value);
 ckchn.ischannel2Tsl=false;}
if(ckchn.ischannel2Type) {currentCHN2.chntype=Long.parseLong(value);
 ckchn.ischannel2Type=false;chnVector.addElement(currentCHN2);ckchn.chn2=false;}
//CHN2 end

//CHN3 start
if(ckchn.ischannel3AdminState) {currentCHN3.chnadmstate=Long.parseLong(value);
currentCHN3.chanid=3;ckchn.ischannel3AdminState=false;}
if(ckchn.ischannel3Maio) {currentCHN3.maio=Long.parseLong(value);
 ckchn.ischannel3Maio=false;}
if(ckchn.ischannel3Pcm) {currentCHN3.pcm=Long.parseLong(value);
 ckchn.ischannel3Pcm=false;}
if(ckchn.ischannel3Subslot) {currentCHN3.subslot=Long.parseLong(value);
 ckchn.ischannel3Subslot=false;}
if(ckchn.ischannel3Tsl) {currentCHN3.tsl=Long.parseLong(value);
 ckchn.ischannel3Tsl=false;}
if(ckchn.ischannel3Type) {currentCHN3.chntype=Long.parseLong(value);
 ckchn.ischannel3Type=false;chnVector.addElement(currentCHN3);ckchn.chn3=false;}
//CHN3 end

//CHN4 start
if(ckchn.ischannel4AdminState) {currentCHN4.chnadmstate=Long.parseLong(value);
 currentCHN4.chanid=4; ckchn.ischannel4AdminState=false;}
if(ckchn.ischannel4Maio) {currentCHN4.maio=Long.parseLong(value);
 ckchn.ischannel4Maio=false;}
if(ckchn.ischannel4Pcm) {currentCHN4.pcm=Long.parseLong(value);
 ckchn.ischannel4Pcm=false;}
if(ckchn.ischannel4Subslot) {currentCHN4.subslot=Long.parseLong(value);
 ckchn.ischannel4Subslot=false;}
if(ckchn.ischannel4Tsl) {currentCHN4.tsl=Long.parseLong(value);
 ckchn.ischannel4Tsl=false;}
if(ckchn.ischannel4Type) {currentCHN4.chntype=Long.parseLong(value);
 ckchn.ischannel4Type=false;chnVector.addElement(currentCHN4);ckchn.chn4=false;}
//CHN4 end

//CHN5 start
if(ckchn.ischannel5AdminState) {currentCHN5.chnadmstate=Long.parseLong(value);
currentCHN5.chanid=5;ckchn.ischannel5AdminState=false;}
if(ckchn.ischannel5Maio) {currentCHN5.maio=Long.parseLong(value);
 ckchn.ischannel5Maio=false;}
if(ckchn.ischannel5Pcm) {currentCHN5.pcm=Long.parseLong(value);
 ckchn.ischannel5Pcm=false;}
if(ckchn.ischannel5Subslot) {currentCHN5.subslot=Long.parseLong(value);
 ckchn.ischannel5Subslot=false;}
if(ckchn.ischannel5Tsl) {currentCHN5.tsl=Long.parseLong(value);
 ckchn.ischannel5Tsl=false;}
if(ckchn.ischannel5Type) {currentCHN5.chntype=Long.parseLong(value);
 ckchn.ischannel5Type=false;chnVector.addElement(currentCHN5);ckchn.chn5=false;}
//CHN5 end

//CHN6 start
if(ckchn.ischannel6AdminState) {currentCHN6.chnadmstate=Long.parseLong(value);
currentCHN6.chanid=6;ckchn.ischannel6AdminState=false;}
if(ckchn.ischannel6Maio) {currentCHN6.maio=Long.parseLong(value);
 ckchn.ischannel6Maio=false;}
if(ckchn.ischannel6Pcm) {currentCHN6.pcm=Long.parseLong(value);
 ckchn.ischannel6Pcm=false;}
if(ckchn.ischannel6Subslot) {currentCHN6.subslot=Long.parseLong(value);
 ckchn.ischannel6Subslot=false;}
if(ckchn.ischannel6Tsl) {currentCHN6.tsl=Long.parseLong(value);
 ckchn.ischannel6Tsl=false;}
if(ckchn.ischannel6Type) {currentCHN6.chntype=Long.parseLong(value);
 ckchn.ischannel6Type=false;chnVector.addElement(currentCHN6);ckchn.chn6=false;}
//CHN6 end

//CHN7 start
if(ckchn.ischannel7AdminState) {currentCHN7.chnadmstate=Long.parseLong(value);
currentCHN7.chanid=7;ckchn.ischannel7AdminState=false;}
if(ckchn.ischannel7Maio) {currentCHN7.maio=Long.parseLong(value);
 ckchn.ischannel7Maio=false;}
if(ckchn.ischannel7Pcm) {currentCHN7.pcm=Long.parseLong(value);
 ckchn.ischannel7Pcm=false;}
if(ckchn.ischannel7Subslot) {currentCHN7.subslot=Long.parseLong(value);
 ckchn.ischannel7Subslot=false;}
if(ckchn.ischannel7Tsl) {currentCHN7.tsl=Long.parseLong(value);
 ckchn.ischannel7Tsl=false;}
if(ckchn.ischannel7Type) {currentCHN7.chntype=Long.parseLong(value);
 ckchn.ischannel7Type=false;chnVector.addElement(currentCHN7);ckchn.chn7=false;
}
//CHN7 end
}//TRX end

//DAP start
if(newdap){
if(ckdap.isbcsuID) {currentDAP.bcsu=Long.parseLong(value);
  ckdap.isbcsuID=false;}
if(ckdap.isfirstTSL) {currentDAP.ftsl=Long.parseLong(value);
 ckdap.isfirstTSL=false;}
if(ckdap.islastTSL) {currentDAP.ltsl=Long.parseLong(value);
 ckdap.islastTSL=false;}
if(ckdap.islogicalBCSUAddress) {currentDAP.lbcsuadd=Long.parseLong(value);
ckdap.islogicalBCSUAddress=false;}
if(ckdap.ispcmCircuit_ID) {currentDAP.pcmckt=Long.parseLong(value);
 ckdap.ispcmCircuit_ID=false;}
if(ckdap.ispcuID) {currentDAP.pcu=Long.parseLong(value);
 ckdap.ispcuID=false;dapVector.addElement(currentDAP);newdap=false;}
}//DAP end

//LPD start
if(newlpd){
if(cklpd.isbitRate) {currentLPD.brate=Long.parseLong(value);
cklpd.isbitRate=false;}
if(cklpd.isabisSigChannelTimeSlotPcm) {currentLPD.abispcm=Long.parseLong(value);
cklpd.isabisSigChannelTimeSlotPcm=false;}
if(cklpd.isabisSigChannelTimeSlotTsl) {currentLPD.abistsl=Long.parseLong(value);
cklpd.isabisSigChannelTimeSlotTsl=false;}
if(cklpd.isabisSigChannelSubSlot) {currentLPD.abisssl=Long.parseLong(value);
cklpd.isabisSigChannelSubSlot=false;}
if(cklpd.isadminState) {currentLPD.admstate=Long.parseLong(value);
cklpd.isadminState=false;}
if(cklpd.isdChannelType) {currentLPD.dchantype=Long.parseLong(value);
cklpd.isdChannelType=false;}
if(cklpd.islogicalBCSUAddress) {currentLPD.lbcsuadd=Long.parseLong(value);
cklpd.islogicalBCSUAddress=false;}
if(cklpd.isname) {currentLPD.name=value;cklpd.isname=false;}
if(cklpd.isparameterSetNumber) {currentLPD.paramset=Long.parseLong(value);
cklpd.isparameterSetNumber=false;}
if(cklpd.issapi) {currentLPD.sapi=Long.parseLong(value);
cklpd.issapi=false;}
if(cklpd.istei) {currentLPD.tei=Long.parseLong(value);
cklpd.istei=false;lpdVector.addElement(currentLPD);newlpd=false;}
}//LPD end

elementValues.remove(qName);////important

}///value
}//func

public void characters( char[] ch, int start, int length )
             throws SAXException {

String tempString = new String(ch,start,length);

if(!(tempString.trim().equals(""))){
    ((StringBuffer)(elementValues.get(currentElement))).append(tempString);
}///if big
}//func

public Vector getbcfVector() {
    return bcfVector;
}

public Vector getbtsVector() {
    return btsVector;
}

public Vector getadjVector() {
    return adjVector;
}

public Vector gettrxVector() {
    return trxVector;
}

public Vector getchnVector() {
    return chnVector;
}

public Vector getdapVector() {
    return dapVector;
}

public Vector getlpdVector() {
    return lpdVector;
}


public static void writebcfFile(Vector v){
try{
File f1 = new File("C:\\Program Files\\MySQL\\MySQL Server 5.0\\data\\nokia\\in_bcf");
f1.delete();


PrintWriter bcff = new PrintWriter(new BufferedWriter(new FileWriter(
        "C:\\Program Files\\MySQL\\MySQL Server 5.0\\data\\nokia\\in_bcf", true)));


     bcfDTO i;
     String bcfString="";
     long j=1;
     Enumeration e = v.elements();
     while( e.hasMoreElements()){
     i = (bcfDTO) e.nextElement();
     String a=i.dname;
     long bsc=Long.parseLong(a.substring(a.indexOf("/BSC-")+5, a.indexOf("/BCF-")));
     long bcf=Long.parseLong(a.substring(a.indexOf("/BCF-")+5, a.length()));
     //System.out.println(bsc+"--"+bcf);
     bcfString=j+","+i.ver+","+i.dname+","+bsc+","+bcf+","+i.sid+","+i.lat+","
             +i.longg+","+i.name+","+i.admstate+","+i.autocfg+","
             +i.bbupr+","+i.bcchbbuTimer +","+i.bcfType+","+i.siteSubtype
             +","+i.lapdName+","+i.lapdNumber+","+i.ntrxbbuTimer+","+i.port0
             +","+i.port1+","+i.port2+","+i.port3+","+i.port4+","+i.port5+","
             +i.port6+","+i.port7+"," +i.rxdLimit+","+i.autounl+","+i.clksrc +","
             +i.ftrsAbisgrm+","+i.mclkBCF;


     //System.out.println(bcfString);
     bcff.println(bcfString);
     j++;
    }

bcff.flush();bcff.close();
}///try
catch(Exception ex){
System.out.println("Error writing BCF file " +  ex);
}
}///func



public static void writebtsFile(Vector v){
try{
File f1 = new File("C:\\Program Files\\MySQL\\MySQL Server 5.0\\data\\nokia\\in_bts");
f1.delete();


PrintWriter btsf = new PrintWriter(new BufferedWriter(new FileWriter(
        "C:\\Program Files\\MySQL\\MySQL Server 5.0\\data\\nokia\\in_bts", true)));


     btsDTO i;
     String btsString="";
     long j=1;
     Enumeration e = v.elements();
     while( e.hasMoreElements()){
     i = (btsDTO) e.nextElement();
///Please note that cellid means lac+ci, written here


     String a=i.dname;
     long bsc=Long.parseLong(a.substring(a.indexOf("/BSC-")+5, a.indexOf("/BCF-")));
     long bcf=Long.parseLong(a.substring(a.indexOf("/BCF-")+5, a.indexOf("/BTS-")));
     long bts=Long.parseLong(a.substring(a.indexOf("/BTS-")+5, a.length()));
     //System.out.println(bsc+"--"+bcf+"--"+bts);
     btsString=j+","+i.ver+ "," +i.dname+ "," +bsc+ "," +bcf+ "," +bts+ "," +i.sid
             + "," +i.fband+ "," +i.name+ "," +i.admstate+ "," +i.bcc+ "," +i.ncc+ ","
             +i.hopp+ ","+i.cuconf+ "," +i.cbq+ "," +i.cbarr+ "," +String.valueOf(i.lac)
             +String.valueOf(i.cellid)+ "," +i.cnHW+ ","+i.cellresh+ "," +i.croff+ ","
             +i.cpari+ "," +i.cs34sup+ ","+i.dedgprs+ "," +i.divuse+ "," +i.egena+ ","
             +i.gena+ "," +i.hopmode+ ","+i.hsn1+ "," +i.hsn2+ "," +i.hsn3+ "," +i.lac
             + "," +i.mcc+ ","+i.mnc+ "," +i.moff+ "," +i.mstep+ "," +i.mbcf+ ","
             +i.mspwrmcch+ ","+i.mspwrmgsm+ "," +i.mbc+ "," +i.nsei+ "," +i.nwname
             + "," +i.rac+ ","+i.rlt+ "," +i.sectorid+ "," +i.segid+ "," +i.segname;


     btsf.println(btsString);
     j++;
    }

btsf.flush();btsf.close();
}///try
catch(Exception ex){
System.out.println("Error writing BTS file " +  ex);
}
}///func


public static void writeadjFile(Vector v){
try{
File f1 = new File("C:\\Program Files\\MySQL\\MySQL Server 5.0\\data\\nokia\\in_adj");
f1.delete();


PrintWriter adjf = new PrintWriter(new BufferedWriter(new FileWriter(
        "C:\\Program Files\\MySQL\\MySQL Server 5.0\\data\\nokia\\in_adj", true)));


     adjDTO i;
     String adjString="";
     long j=1;
     Enumeration e = v.elements();
     while( e.hasMoreElements()){
     i = (adjDTO) e.nextElement();
     ///Please note that targetid=tgtlac+targetid, defined here

     String a=i.dname;
     long bsc=Long.parseLong(a.substring(a.indexOf("/BSC-")+5, a.indexOf("/BCF-")));
     long bcf=Long.parseLong(a.substring(a.indexOf("/BCF-")+5, a.indexOf("/BTS-")));
     long bts=Long.parseLong(a.substring(a.indexOf("/BTS-")+5, a.indexOf("/ADCE-")));
     //System.out.println(bsc+"--"+bcf+"--"+bts);
     String mod_dname=i.dname.substring(0, i.dname.indexOf("/ADCE"));
     adjString=j+","+i.ver +","+"\""+mod_dname+"\""+","+bsc+","+bcf+","+bts+","+i.sid
             +","+i.fband +","+"\""+i.tgtDN+"\"" +","+i.tgtbcc +","+i.tgtncc +","
             +String.valueOf(i.tgtlac)+String.valueOf(i.targetid)+","+i.tgtlac +","
             +i.tgtmcc+","+i.tgtmnc+","+i.adjIndex +","+i.tgtbcch +","+i.tgtgena
             + "," + i.sync;


     adjf.println(adjString);
     j++;
    }

adjf.flush();adjf.close();
}///try
catch(Exception ex){
System.out.println("Error writing ADJ file " +  ex);
}
}///func

public static void writetrxFile(Vector v){
try{
File f1 = new File("C:\\Program Files\\MySQL\\MySQL Server 5.0\\data\\nokia\\in_trx");
f1.delete();


PrintWriter trxf = new PrintWriter(new BufferedWriter(new FileWriter(
        "C:\\Program Files\\MySQL\\MySQL Server 5.0\\data\\nokia\\in_trx", true)));

     trxDTO i;
     String trxString="";
     long j=1;
     Enumeration e = v.elements();
     while( e.hasMoreElements()){
     i = (trxDTO) e.nextElement();
    String mod_dname=i.dname.substring(0, i.dname.indexOf("/TRX-"));

     String a=i.dname;
     long bsc=Long.parseLong(a.substring(a.indexOf("/BSC-")+5, a.indexOf("/BCF-")));
     long bcf=Long.parseLong(a.substring(a.indexOf("/BCF-")+5, a.indexOf("/BTS-")));
     long bts=Long.parseLong(a.substring(a.indexOf("/BTS-")+5, a.indexOf("/TRX-")));
     i.trxid=Long.parseLong(a.substring(a.indexOf("/TRX-")+5, a.length()));
     //System.out.println(bsc+"--"+bcf+"--"+bts+"--"+i.trxid);

     trxString=j+","+i.ver + "," +mod_dname +","+i.dname+","+bsc+","+bcf+","+bts+","+i.trxid
             + "," +i.sid + "," +i.admstate + ","+i.autocfg + "," +i.dapid + ","
             +i.gena + "," +i.hrsup + ","+i.ifreq + "," +i.lapdName + "," +i.lapdNumber
             + "," +i.rfhoppal+ "," +i.sslSignalling + "," +i.freqType + ","
             +i.tsc + "," +i.name;

             chkTRXFreq(bsc,bcf,bts,i.trxid,i.ifreq);
     trxf.println(trxString);
     j++;
    }

trxf.flush();trxf.close();
}///try
catch(Exception ex){
System.out.println("Error writing TRX file " +  ex);
}
}///func

public static void writechnFile(Vector v){
try{
File f1 = new File("C:\\Program Files\\MySQL\\MySQL Server 5.0\\data\\nokia\\in_chn");
f1.delete();


PrintWriter chnf = new PrintWriter(new BufferedWriter(new FileWriter(
        "C:\\Program Files\\MySQL\\MySQL Server 5.0\\data\\nokia\\in_chn", true)));

     chnDTO i;
     String chnString="";
     long j=1;
     Enumeration e = v.elements();
     while( e.hasMoreElements()){
     i = (chnDTO) e.nextElement();

     String a=i.dname;
     long bsc=Long.parseLong(a.substring(a.indexOf("/BSC-")+5, a.indexOf("/BCF-")));
     long bcf=Long.parseLong(a.substring(a.indexOf("/BCF-")+5, a.indexOf("/BTS-")));
     long bts=Long.parseLong(a.substring(a.indexOf("/BTS-")+5, a.indexOf("/TRX-")));
     long trx=Long.parseLong(a.substring(a.indexOf("/TRX-")+5, a.length()));
     //System.out.println(bsc+"--"+bcf+"--"+bts+"--"+trx);

     chnString=j+","+i.ver+","+i.dname+","+bsc+","+bcf+","+bts+","+trx+","+i.chanid
             +","+i.sid + "," +i.chnadmstate + "," +i.maio + "," +i.pcm + ","
             +i.subslot + "," +i.tsl+ "," +i.chntype ;

     //System.out.println(chnString);
     chnf.println(chnString);
     j++;
    }

chnf.flush();chnf.close();
}///try
catch(Exception ex){
System.out.println("Error writing CHN file " +  ex);
}
}///func

public static void writedapFile(Vector v){
try{
File f1 = new File("C:\\Program Files\\MySQL\\MySQL Server 5.0\\data\\nokia\\in_dap");
f1.delete();


PrintWriter dapf = new PrintWriter(new BufferedWriter(new FileWriter(
        "C:\\Program Files\\MySQL\\MySQL Server 5.0\\data\\nokia\\in_dap", true)));

     dapDTO i;
     String dapString="";
     long j=1;
     Enumeration e = v.elements();
     while( e.hasMoreElements()){
     i = (dapDTO) e.nextElement();
     String a=i.dname;
     long bsc=Long.parseLong(a.substring(a.indexOf("/BSC-")+5, a.indexOf("/DAP-")));
     long dap=Long.parseLong(a.substring(a.indexOf("/DAP-")+5, a.length()));
     //System.out.println(bsc+"--"+dap);

     dapString=j+","+i.ver+","+i.dname+","+bsc+","+dap+","+i.sid+","+i.bcsu+","
             +i.ftsl+","+i.ltsl+","+i.lbcsuadd+","+i.pcmckt+","+i.pcu;

     //System.out.println(dapString);
     dapf.println(dapString);
     j++;
    }

dapf.flush();dapf.close();
}///try
catch(Exception ex){
System.out.println("Error writing DAP file " +  ex);
}
}///func

public static void writelpdFile(Vector v){
try{
File f1 = new File("C:\\Program Files\\MySQL\\MySQL Server 5.0\\data\\nokia\\in_lpd");
f1.delete();


PrintWriter lpdf = new PrintWriter(new BufferedWriter(new FileWriter(
        "C:\\Program Files\\MySQL\\MySQL Server 5.0\\data\\nokia\\in_lpd", true)));

     lpdDTO i;
     String lpdString="";
     long j=1;
     Enumeration e = v.elements();
     while( e.hasMoreElements()){
     i = (lpdDTO) e.nextElement();
     String a=i.dname;
     long bsc=Long.parseLong(a.substring(a.indexOf("/BSC-")+5, a.indexOf("/LAPD-")));
     long lpd=Long.parseLong(a.substring(a.indexOf("/LAPD-")+6, a.length()));
     //System.out.println(bsc+"--"+lpd);

     lpdString=j+","+i.ver+","+i.dname+","+bsc+","+lpd+","+i.sid+","+i.brate+","
             +i.abispcm+","+i.abistsl+","+i.abisssl+","+i.admstate+","+i.dchantype+","+i.lbcsuadd
             +","+i.name+","+i.paramset+","+i.sapi+","+i.tei;

     //System.out.println(lpdString);
     lpdf.println(lpdString);
     j++;
    }

lpdf.flush();lpdf.close();
}///try
catch(Exception ex){
System.out.println("Error writing LPD file " +  ex);
}
}///func

public static long chkTRXFreq(long bsc,long bcf,long bts,long trx, long freq){
long count=0;

if((freq<27 | (freq >50) && freq<722) | freq>772)
{////(freq<27 || freq >50) ||
System.out.print("!!!WARNING!!! out of BAND frequency found->");
System.out.println("BSC="+bsc+",BCF="+bcf+",BTS="+bts+",TRX="+trx+",freq="+freq);
count++;
}

return count;
}///funct

public static void processTableBCF(){
        bcfDAO.dropTableBCF();
        bcfDAO.createTableBCF();
        bcfDAO.loadTableBCF();
}//processTableBCF ends

public static void processTableBTS(){
        btsDAO.dropTableBTS();
        btsDAO.createTableBTS();
        btsDAO.loadTableBTS();
}//processTableBTS ends

public static void processTableADJ(){
        adjDAO adjdao=new adjDAO();
        adjdao.dropTableADJ();
        adjdao.createTableADJ();
        adjdao.loadTableADJ();
        adjdao.updateTableADJSRCInfo();

}//processTableadj ends

public static void processTableCHN(){
        chnDAO chndao=new chnDAO();
        chndao.dropTableCHN();
        chndao.createTableCHN();
        chndao.loadTableCHN();
        trxDAO.updateTRXisBCCH();
}//processTablechn ends

public static void processTableDAP(){
        dapDAO dapdao=new dapDAO();
        dapdao.dropTableDAP();
        dapdao.createTableDAP();
        dapdao.loadTableDAP();

}//processTabledap ends

public static void processTableLPD(){
        lpdDAO lpddao=new lpdDAO();
        lpddao.dropTableLPD();
        lpddao.createTableLPD();
        lpddao.loadTableLPD();

}//processTablelpd ends

public static void processTableTRX(){
        trxDAO trxdao=new trxDAO();
        trxdao.dropTableTRX();
        trxdao.createTableTRX();
        trxdao.loadTableTRX();
        trxdao.updateTableTRXaddcellid();


}//processTabletrx ends

public static void main( String[] argv ){

  remLine2.remove2ndLineFromALLXMLFile();
  System.out.println( "parseNokiaCM:" );
   try {
      parseNokiaCM pNCM = new parseNokiaCM();
      // Create SAX 2 parser...
      XMLReader xr = XMLReaderFactory.createXMLReader();

      // Set the ContentHandler...

      xr.setContentHandler( pNCM );

      // Parse the file...

      File dbfolder=new File("C://ranmaster//DB//2G//XML_DATABASE//"+ CurrDate());
      //C:\ranmaster\DB\2G\XML_DATABASE
      //File dbfolder=new File("D://Flexi_"+ CurrDate());
      File[] f=dbfolder.listFiles();

      for(int i=0;i< f.length;i++){
          try{
      xr.parse( new InputSource(new FileReader( f[i] )) );
          }
          catch(Exception ex0){System.out.println(ex0);
          ex0.printStackTrace();}
      }


     Vector bcfList=pNCM.getbcfVector();
	 Vector btsList=pNCM.getbtsVector();
     Vector adjList=pNCM.getadjVector();
	 Vector trxList=pNCM.gettrxVector();
     Vector chnList=pNCM.getchnVector();
     Vector dapList=pNCM.getdapVector();
     Vector lpdList=pNCM.getlpdVector();


     pNCM.writebcfFile(bcfList);
	 pNCM.writebtsFile(btsList);
     pNCM.writeadjFile(adjList);
	 pNCM.writetrxFile(trxList);
     pNCM.writechnFile(chnList);
     pNCM.writedapFile(dapList);
     pNCM.writelpdFile(lpdList);


     pNCM.processTableBCF();
     pNCM.processTableBTS();
     pNCM.processTableADJ();
     pNCM.processTableTRX();
     pNCM.processTableCHN();
     pNCM.processTableDAP();
     pNCM.processTableLPD();



   }catch ( Exception e ) {
       System.out.println("Error from main: "+e);
       System.exit(0);
        e.printStackTrace();
     }//catch
  }///main
}///class

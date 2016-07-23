/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package appL;


import java.util.*;
import java.io.*;
import dTO.btsDTO;



/**
 *
 * @author zahurul
 */
public class nokiaMeas {


public static ArrayList nokiaMeas_INPUT()
{
    String str = null;
    String test = null;
    ArrayList ar = new ArrayList();
    String input_file = "input\\input.txt";

try{
    BufferedReader inFile = new BufferedReader(new FileReader(input_file));
    while ((str = inFile.readLine()) != null) {
        if (str.length() != 0) {

            btsDTO bdto=new btsDTO();
            StringTokenizer stk = new StringTokenizer(str," *");
            for (int count = 0; stk.hasMoreTokens(); ++count) {
                test = stk.nextToken();

                switch (count) {
                case 0:
                {bdto.name=test;}
                break;
                }//switch
            }//for
            ar.add(bdto);
        }//if str
    } //while str
    inFile.close();
}// try

catch(Exception ex){
    System.out.println("Error from input of nokia Meas...please take action "+ex);
    return null;
}
return ar;
}// ends funct



public static void writeCHFfile(ArrayList ar){
try{

File f=new File("input\\CHFfile.sql");
f.delete();

PrintWriter chff = new PrintWriter(new BufferedWriter(new FileWriter(
        "input\\CHFfile.sql",true)));
String sstart="SET heading off\n";
sstart+="SET linesize 32767\n";
sstart+="SET colsep ,\n";
sstart+="SET pagesize 0\n";
sstart+="SET newpage none\n";
sstart+="SET feedback off\n";
sstart="SET termout off\n";
sstart+="SET recsep off\n";
sstart+="SET sqlprompt \"\"\n";
sstart+="SET TRIMSPOOL ON\n";
sstart+="SET echo off\n";
sstart+="spool \"chf29.csv\"\n";

String send="spool off\n";
send+="exit";

chff.println(sstart);


            Iterator itr=ar.iterator();
            while(itr.hasNext()){

            btsDTO btsinp=(btsDTO) itr.next();

            String s0="Select \"SWITCH”, \"ELEMENT”, \"DAY”, \"HOUR”, \"AVE_DL_SIGNAL_STRENGTH”, \"NCC”, \"BCC”, \"BCCH”, \"DB_VALUE_HIGH”, \"DB_VALUE_LOW”, \" NUM_OF_SAMPLES_IN_CLASS_1”, \"NUM_OF_SAMPLES_IN_CLASS_2”, \"NUM_OF_SAMPLES_IN_CLASS_3”, \"STANDARD_DEVIATION” UNION;\n";
            s0+="select bsc.name as SWITCH, bts.segment_name as ELEMENT, to_char(x.period_start_time, 'DD/MM/YY') as DAY, to_char(x.period_start_time, 'HH24') as HOUR, x.ave_dl_signal_strength as AVE_DL_SIGNAL_STRENGTH, x.ncc as NCC, x.bcc as BCC, x.bcch as BCCH, x.db_value_high as DB_VALUE_HIGH, x.db_value_low as DB_VALUE_LOW, sum(num_of_samples_in_class_1) as NUM_OF_SAMPLES_IN_CLASS_1, sum(num_of_samples_in_class_2) as NUM_OF_SAMPLES_IN_CLASS_2, sum(num_of_samples_in_class_3) as NUM_OF_SAMPLES_IN_CLASS_3, x.standard_deviation as STANDARD_DEVIATION from p_nbsc_channel_finder x, objects bsc, c_bts bts where x.period_start_time between to_date('26-07-2012 16:00','DD-MM-YYYY HH24:MI') AND to_date('26-07-2012 22:00','DD-MM-YYYY HH24:MI') and x.bts_int_id = bts.int_id and bts.bsc_int_id = bsc.int_id and bsc.object_class = '3' and bsc.name in ('BSDH11_F','BSDH12_F','BSDH16_F,'BSDH18_F') and bts.conf_name = '<ACTUAL>' group by bsc.name, bts.segment_name, x.period_start_time, x.ave_dl_signal_strength, x.bcch, x.bcc, x.ncc, x.db_value_low, x.db_value_high, x.standard_deviation order by bsc.name, x.period_start_time, bts.segment_name;\n";

            chff.println(s0);
            }///while itr

            chff.println(send);
            chff.flush();
            chff.close();
        }//try

catch (Exception ex){
     System.out.println("from del neibor.."+ex);
}

}///func




public static void main(String[] args) {
        nokiaMeas nokms = new nokiaMeas();

        ArrayList ar= new ArrayList();
        ar=nokms.nokiaMeas_INPUT();
        nokms.writeCHFfile(ar);

}//main

}///class

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dAO;

import org.apache.log4j.Logger;
import java.sql.*;
import databasemanager.*;
import dTO.*;

/**
 *
 * @author zahurul
 */
public class trxDAO {
static Logger logger = Logger.getLogger(trxDAO.class.getName());

public trxDAO(){

}

public static void createTableTRX()
{
    Connection cn = null;
    PreparedStatement ps = null;
    String sql = null;

try
{
         cn = DatabaseManager.getInstance().getConnection();

sql = "CREATE TABLE nokia.trx(id MEDIUMINT primary key auto_increment,ver varchar(18)," +
        "dname varchar(100),dnamefull varchar(100),bsc numeric(18),bcf numeric(18),bts numeric(18)," +
        "trxid numeric(18)," +"sid numeric(18),adm numeric(18),autocfg numeric(18)," +
        "dapid numeric(18),gena numeric(18),hrsup numeric(18),ifreq numeric(18)," +
        "lapdname varchar(18),lapdnumber numeric(18),rfhoppal numeric(18)," +
        "sslsignalling numeric(18),freqtype numeric(18),tsc numeric(18), name varchar(100)) ENGINE=MEMORY;";

               ps = cn.prepareStatement(sql);
               ps.executeUpdate();
          }

          catch (Exception e)
          {
           logger.fatal("DAO" + e.toString());
           System.out.println("Error from create table "+e.toString());
          }
          finally
          {
              try{
                ps.close();
                            }
               catch (Throwable th)
               {}
                  try
                 {
                   if (cn != null)
                              {
                                DatabaseManager.getInstance().freeConnection(cn);
                              }
                  }
           catch (Exception e)
           {
             logger.fatal("DAO finally :" + e.toString());
           }
          }

}// funct


public static void loadTableTRX()
{
          Connection cn = null;
          PreparedStatement ps = null;
          String sql = null;

  try
  {
     cn = DatabaseManager.getInstance().getConnection();

sql = "LOAD DATA INFILE 'C:/Program Files/MySQL/MySQL Server 5.0/data/nokia/in_trx' INTO TABLE nokia.trx FIELDS TERMINATED BY ',' ENCLOSED BY '\"'  LINES TERMINATED BY '\r\n';" ;

     ps = cn.prepareStatement(sql);
     ps.executeUpdate();

    }

    catch (Exception e)
    {
       logger.fatal("DAO" + e.toString());
       System.out.println("Error "+e.toString());
    }
        finally
          {
             try{
                  ps.close();
                 }
              catch (Throwable th)
                   {}
                  try
                     {
                       if (cn != null)
                       {
                        DatabaseManager.getInstance().freeConnection(cn);
                       }
                     }
                  catch (Exception e)
                     {
                        logger.fatal("DAO finally :" + e.toString());
                     }
            }

}// funct

public static void dropTableTRX()
{
        Connection cn = null;
        PreparedStatement ps = null;
        String sql = null;
try
{
    cn = DatabaseManager.getInstance().getConnection();

sql = "drop table nokia.trx;" ;

     ps = cn.prepareStatement(sql); 
     ps.executeUpdate();

}
catch (Exception e)
{

           logger.fatal("DAO" + e.toString());
           System.out.println("Error "+e.toString());
}
         finally
         {
           try{
                ps.close();
              }
              catch (Throwable th)
              {}
              try
               {
               if (cn != null)
                {
                 DatabaseManager.getInstance().freeConnection(cn);
                }
               }
              catch (Exception e)
               {
               logger.fatal("DAO finally :" + e.toString());
               }
}

}// funct

public static void updateTableTRXaddcellid()
{
        Connection cn1 = null, cn2 = null, cn3 = null, cn4 = null, cn33 = null, cn44 = null;
        PreparedStatement ps1 = null,ps2 = null,ps3 = null,ps4 = null,ps33 = null,ps44 = null;
        String sql1 = null,sql2 = null,sql3 = null,sql4 = null,sql33 = null,sql44 = null;
try
{
    cn1 = DatabaseManager.getInstance().getConnection();
	cn2 = DatabaseManager.getInstance().getConnection();
    cn3 = DatabaseManager.getInstance().getConnection();
    cn4 = DatabaseManager.getInstance().getConnection();
    cn33 = DatabaseManager.getInstance().getConnection();
    cn44 = DatabaseManager.getInstance().getConnection();

	sql1 = "alter table nokia.trx add column cellid numeric(18);";
    sql3 = "CREATE INDEX intrx USING BTREE ON trx (dname);";
    sql4 = "CREATE INDEX inbts USING BTREE ON bts (dname);";
    sql33 = "drop INDEX intrx ON trx;";
    sql44 = "drop INDEX inbts ON bts;";
	sql2 = "update nokia.trx,nokia.bts set trx.cellid=bts.cellid where trx.dname=bts.dname;";

    ps3 = cn3.prepareStatement(sql3);ps3.executeUpdate();
    ps4 = cn4.prepareStatement(sql4);ps4.executeUpdate();
    ps1 = cn1.prepareStatement(sql1);ps1.executeUpdate();
	ps2 = cn2.prepareStatement(sql2);ps2.executeUpdate();
    ps33 = cn33.prepareStatement(sql33);ps33.executeUpdate();
    ps44 = cn44.prepareStatement(sql44);ps44.executeUpdate();

	}
catch (Exception e)
{

           logger.fatal("DAO" + e.toString());
           System.out.println("Error "+e.toString());
}
         finally
         {
           try{
                ps1.close();ps2.close();ps3.close();ps4.close();ps33.close();ps44.close();
              }
              catch (Throwable th)
              {}
              try
               {
               if (cn1 != null || cn2 != null || cn3 != null || cn4 != null || cn33 != null || cn44 != null)
                {
                 DatabaseManager.getInstance().freeConnection(cn1);
                 DatabaseManager.getInstance().freeConnection(cn2);
                 DatabaseManager.getInstance().freeConnection(cn3);
                 DatabaseManager.getInstance().freeConnection(cn4);
                 DatabaseManager.getInstance().freeConnection(cn33);
                 DatabaseManager.getInstance().freeConnection(cn44);
                }
               }
              catch (Exception e)
               {
               logger.fatal("DAO finally :" + e.toString());
               }
}

}// funct

public static trxDTO GettrxDATAbyFreq(long cellid, long ifreq)
  {
  Connection cn = null;
  PreparedStatement ps = null;
  String sql = null;
  trxDTO pdto = new trxDTO();
  pdto.cellid=cellid;
  pdto.ifreq=ifreq;

try{
     cn = DatabaseManager.getInstance().getConnection();

     sql = "select ver,dname,adm,autocfg,dapid,gena,hrsup,lapdname,lapdnumber," +
           "rfhoppal,sslsignalling,freqtype,tsc,trxid,name from nokia.trx where cellid = ? and ifreq= ?";
     ps = cn.prepareStatement(sql);
     ps.setLong(1, pdto.cellid);
	 ps.setLong(2, pdto.ifreq);
     ResultSet resultSet = ps.executeQuery();

     if (resultSet.next())
     {
     pdto.name=resultSet.getString("name");
     pdto.dname=resultSet.getString("dname");
     pdto.ver=resultSet.getString("ver");
     pdto.lapdName=resultSet.getString("lapdname");
     pdto.admstate=resultSet.getLong("adm");
     pdto.autocfg=resultSet.getLong("autocfg");
     pdto.dapid=resultSet.getLong("dapid");
     pdto.gena=resultSet.getLong("gena");
     pdto.hrsup=resultSet.getLong("hrsup");
     pdto.lapdNumber=resultSet.getLong("lapdnumber");
     pdto.rfhoppal=resultSet.getLong("rfhoppal");
     pdto.sslSignalling=resultSet.getLong("sslsignalling");
     pdto.freqType=resultSet.getLong("freqtype");
     pdto.tsc=resultSet.getLong("tsc");
     pdto.trxid=resultSet.getLong("trxid");
     }

     else {
     pdto.dname="";
     pdto.lapdName="";
     pdto.trxid=-1;
     pdto.admstate=-1;
     }
     resultSet.close();
  }
  catch (Exception e)
  {
    logger.fatal("DAO" + e.toString());
    System.out.println("Error from gettrxdatabyferq "+e.toString());
  }
  finally
  {
    try
    {
        ps.close();
    }
    catch (Throwable th)
    {
    }
    try
    {
      if (cn != null)
      {
        DatabaseManager.getInstance().freeConnection(cn);

      }
    }
    catch (Exception e)
    {
      logger.fatal("DAO finally :" + e.toString());

    }
  }
  return pdto;
} //functs



public static trxDTO GettrxDATA(trxDTO tin)
  {
  Connection cn = null;
  PreparedStatement ps = null;
  String sql = null;
  trxDTO tdto = new trxDTO();

try{
     cn = DatabaseManager.getInstance().getConnection();

     sql = "select ver,dname,name,adm,autocfg,dapid,gena,hrsup,lapdname,lapdnumber," +
           "rfhoppal,sslsignalling,freqtype,tsc,cellid,ifreq from nokia.trx where bsc = ?" +
           " and bcf= ? and bts= ? and trxid = ?";
     ps = cn.prepareStatement(sql);
     ps.setLong(1, tin.bsc);
	 ps.setLong(2, tin.bcf);
     ps.setLong(3, tin.bts);
	 ps.setLong(4, tin.trxid);
     ResultSet resultSet = ps.executeQuery();

     if (resultSet.next())
     {
     tdto.name=resultSet.getString("name");
     tdto.dname=resultSet.getString("dname");
     tdto.ver=resultSet.getString("ver");
     tdto.lapdName=resultSet.getString("lapdname");
     tdto.admstate=resultSet.getLong("adm");
     tdto.autocfg=resultSet.getLong("autocfg");
     tdto.dapid=resultSet.getLong("dapid");
     tdto.gena=resultSet.getLong("gena");
     tdto.hrsup=resultSet.getLong("hrsup");
     tdto.lapdNumber=resultSet.getLong("lapdnumber");
     tdto.rfhoppal=resultSet.getLong("rfhoppal");
     tdto.sslSignalling=resultSet.getLong("sslsignalling");
     tdto.freqType=resultSet.getLong("freqtype");
     tdto.tsc=resultSet.getLong("tsc");
     tdto.cellid=resultSet.getLong("cellid");
     tdto.ifreq=resultSet.getLong("ifreq");
     }

     else {
     tdto.dname="";
     tdto.lapdName="";
     tdto.admstate=-1;
	 tdto.lapdNumber=-1;
	 tdto.tsc=-1;
     tdto.cellid=-1;
     tdto.ifreq=-1;
     }
     resultSet.close();
  }
  catch (Exception e)
  {
    logger.fatal("DAO" + e.toString());
    System.out.println("Error from gettrxdataBUGG "+e.toString());
  }
  finally
  {
    try
    {
        ps.close();
    }
    catch (Throwable th)
    {
    }
    try
    {
      if (cn != null)
      {
        DatabaseManager.getInstance().freeConnection(cn);

      }
    }
    catch (Exception e)
    {
      logger.fatal("DAO finally :" + e.toString());

    }
  }
  return tdto;
} //functs end

public static void changeTRXtsC(trxDTO tdto)
{
          Connection cn = null;
          PreparedStatement ps = null;
          String sql = null;

          try
          {
          cn = DatabaseManager.getInstance().getConnection();

sql = "update nokia.trx set tsc="+tdto.tsc+" where cellid="+tdto.cellid;

          ps = cn.prepareStatement(sql);
          ps.executeUpdate();
          }

          catch (Exception e)
          {

            logger.fatal("DAO" + e.toString());
            System.out.println("Error "+e.toString());
          }
          finally
          {
            try{
               ps.close();
                }
            catch (Throwable th)
                {}
                try
                {
                  if (cn != null)
                  {
                   DatabaseManager.getInstance().freeConnection(cn);
                  }
                 }
             catch (Exception e)
             {
             logger.fatal("DAO finally :" + e.toString());
             }
          }

}// funct


public static void changeTRXfreQ(trxDTO tdto)
{
       Connection cn = null;
       PreparedStatement ps = null;
       String sql = null;

       try
       {
       cn = DatabaseManager.getInstance().getConnection();

sql = "update nokia.trx set ifreq="+tdto.ifreq+" where cellid="+tdto.cellid+" and trxid="+tdto.trxid;

       ps = cn.prepareStatement(sql);
       ps.executeUpdate();
       }

       catch (Exception e)
       {

        logger.fatal("DAO" + e.toString());
        System.out.println("Error "+e.toString());
        }
     finally
      {
         try{
             ps.close();
            }
         catch (Throwable th)
           {}
              try
                {
                 if (cn != null)
                  {
                  DatabaseManager.getInstance().freeConnection(cn);
                 }
               }
               catch (Exception e)
               {
               logger.fatal("DAO finally :" + e.toString());
               }
            }

}// funct

public static void updateTRXisBCCH()
{
       Connection cn1 = null;
       Connection cn2 = null;
       Connection cn3 = null;
       Connection cn4 = null;
       Connection cn5 = null;
       Connection cn44 = null;
       Connection cn55 = null;
       PreparedStatement ps1 = null;
       PreparedStatement ps2 = null;
       PreparedStatement ps3 = null;
       PreparedStatement ps4 = null;
       PreparedStatement ps5 = null;
       PreparedStatement ps44 = null;
       PreparedStatement ps55 = null;
       String sql1 = null;
       String sql2 = null;
       String sql3 = null;
       String sql4 = null;
       String sql5 = null;
       String sql44 = null;
       String sql55 = null;
       try
       {
       cn1 = DatabaseManager.getInstance().getConnection();
	   cn2 = DatabaseManager.getInstance().getConnection();
	  // cn3 = DatabaseManager.getInstance().getConnection();
       cn4 = DatabaseManager.getInstance().getConnection();
       cn5 = DatabaseManager.getInstance().getConnection();
       cn44 = DatabaseManager.getInstance().getConnection();
       cn55 = DatabaseManager.getInstance().getConnection();

	   sql1 = "alter table nokia.trx add column isBCCH numeric(18) default 0;";
	   sql2 = "update nokia.trx,nokia.chn set trx.isBCCH=1 where (trx.dnamefull=chn.dname and chn.chntype=4);";
	   //sql3 = "update nokia.trx,nokia.chn set trx.isBCCH=0 ;";
       sql4 = "CREATE INDEX intrx USING BTREE ON trx (dnamefull);";
       sql5 = "CREATE INDEX inchn USING BTREE ON chn (dname,chntype);";
       sql44 = "drop INDEX intrx ON trx;";
       sql55 = "drop INDEX inchn ON chn;";

       ps4 = cn4.prepareStatement(sql4);ps4.executeUpdate();
       ps5 = cn5.prepareStatement(sql5);ps5.executeUpdate();
       ps1 = cn1.prepareStatement(sql1);ps1.executeUpdate();
       //ps3 = cn3.prepareStatement(sql3);ps3.executeUpdate();
       ps2 = cn2.prepareStatement(sql2);ps2.executeUpdate();
       ps44 = cn44.prepareStatement(sql44);ps44.executeUpdate();
       ps55 = cn55.prepareStatement(sql55);ps55.executeUpdate();
       }

       catch (Exception e)
       {

        logger.fatal("DAO" + e.toString());
        System.out.println("Error "+e.toString());
        }
     finally
      {
         try{
             ps1.close();ps2.close();ps3.close();ps4.close();ps5.close();ps44.close();ps55.close();
            }
         catch (Throwable th)
           {}
              try
                {
                 if (cn1 != null || cn2 != null || cn3 != null || cn4 != null || cn5 != null || cn44 != null || cn55 != null)
                  {
                  DatabaseManager.getInstance().freeConnection(cn1);
                  DatabaseManager.getInstance().freeConnection(cn2);
                  DatabaseManager.getInstance().freeConnection(cn3);
                  DatabaseManager.getInstance().freeConnection(cn4);
                  DatabaseManager.getInstance().freeConnection(cn5);
                  DatabaseManager.getInstance().freeConnection(cn44);
                  DatabaseManager.getInstance().freeConnection(cn55);
                 }
               }
               catch (Exception e)
               {
               logger.fatal("DAO finally :" + e.toString());
               }
            }

}// funct


}///class

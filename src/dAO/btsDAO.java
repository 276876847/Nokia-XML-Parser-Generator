/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dAO;

import org.apache.log4j.Logger;
import java.sql.*;
import databasemanager.*;
import dTO.btsDTO;

/**
 *
 * @author zahurul
 */
public class btsDAO {
static Logger logger = Logger.getLogger(btsDAO.class.getName());

public static void createTableBTS()
{
        Connection cn = null;
        PreparedStatement ps = null;
        String sql = null;

try
{
         cn = DatabaseManager.getInstance().getConnection();

sql = "CREATE TABLE nokia.bts(id MEDIUMINT primary key auto_increment,ver varchar(18)," +
        "dname varchar(100),bsc numeric(18),bcf numeric(18),bts numeric(18)," +
        "sid numeric(18),fband numeric(18),name varchar(50)," +
        "adm numeric(18),bcc numeric(18),ncc numeric(18),hopp numeric(18)," +
        "cuconf numeric(18),cbq numeric(18),cbarr numeric(18),cellid numeric(18)," +
        "cnhw numeric(18),cellresh numeric(18),croff numeric(18),cpari numeric(18)," +
        "cs34sup numeric(18),dedgprs numeric(18),divuse numeric(18),egena numeric(18)," +
        "gena numeric(18),hopmode numeric(18),hsn1 numeric(18),hsn2 numeric(18)," +
        "hsn3 numeric(18),lac numeric(18),mcc numeric(18),mnc numeric(18)," +
        "moff numeric(18),mstep numeric(18),mbcf numeric(18),mspwrmcch numeric(18)," +
        "mspwrmgsm numeric(18),mbc numeric(18),nsei numeric(18),nwname varchar(18)," +
        "rac numeric(18),rlt numeric(18),sectorid numeric(18),segid numeric(18)," +
        "segname varchar(18)) ENGINE=MEMORY;";


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


public static void loadTableBTS()
{
        Connection cn = null;
        PreparedStatement ps = null;
        String sql = null;

  try
  {
     cn = DatabaseManager.getInstance().getConnection();
     
sql = "LOAD DATA INFILE 'C:/Program Files/MySQL/MySQL Server 5.0/data/nokia/in_bts' INTO TABLE nokia.bts FIELDS TERMINATED BY ',' ENCLOSED BY '\"'  LINES TERMINATED BY '\r\n';" ;

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

public static void dropTableBTS()
{
        Connection cn = null;
        PreparedStatement ps = null;
        String sql = null;
try
{
    cn = DatabaseManager.getInstance().getConnection();

sql = "drop table nokia.bts;" ;


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

public static btsDTO GetbtsDATA(long cellid)
  {
  Connection cn = null;
  PreparedStatement ps = null;
  String sql = null;
  btsDTO pdto = new btsDTO();
  pdto.cellid=cellid;

try{
     cn = DatabaseManager.getInstance().getConnection();

     sql = "select dname,fband,name,adm,bcc,ncc,hopp,cbarr,cnhw,hopmode,lac,mcc," +
       "mnc,mbc,nsei,nwname,rac,sectorid,segid,segname from nokia.bts where cellid = ? ";
     ps = cn.prepareStatement(sql);
     ps.setLong(1, pdto.cellid);

     ResultSet resultSet = ps.executeQuery();

     if (resultSet.next())
     {
     pdto.dname=resultSet.getString("dname");
     pdto.name=resultSet.getString("name");
     pdto.segname=resultSet.getString("segname");
     pdto.fband=resultSet.getLong("fband");
     pdto.admstate=resultSet.getLong("adm");
     pdto.bcc=resultSet.getLong("bcc");
     pdto.ncc=resultSet.getLong("ncc");
     pdto.hopp=resultSet.getLong("hopp");
     pdto.cbarr=resultSet.getLong("cbarr");
     pdto.cnHW=resultSet.getLong("cnhw");
     pdto.hopmode=resultSet.getLong("hopmode");
     pdto.lac=resultSet.getLong("lac");
     pdto.mcc=resultSet.getLong("mcc");
     pdto.mnc=resultSet.getLong("mnc");
     pdto.mbc=resultSet.getLong("mbc");
     pdto.nsei=resultSet.getLong("nsei");
     pdto.rac=resultSet.getLong("rac");
     pdto.sectorid=resultSet.getLong("sectorid");
     pdto.segid=resultSet.getLong("segid");
     }

     else {
     pdto.dname="";
     pdto.name="";
     pdto.segname="";
     pdto.fband=-1;
     pdto.admstate=-1;
     pdto.lac=-1;
     }
     resultSet.close();
  }
  catch (Exception e)
  {
    logger.fatal("DAO" + e.toString());
    System.out.println("Error "+e.toString());
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


public static btsDTO GetNextbtsDATA(btsDTO bin)
  {
  Connection cn = null;
  PreparedStatement ps = null;
  String sql = null;
  btsDTO pdto = new btsDTO();

try{
     cn = DatabaseManager.getInstance().getConnection();

     sql = "select dname,bts,fband,name,adm,bcc,ncc,hopp,cbarr,cnhw,hopmode,lac,mcc," +
       "mnc,mbc,nsei,nwname,rac,sectorid,segid,segname from nokia.bts where cellid= ? " +
       " and bsc= ? and bcf= ? and bts!=?";
     ps = cn.prepareStatement(sql);
     ps.setLong(1, bin.cellid);
     ps.setLong(2, bin.bsc);
     ps.setLong(3, bin.bcf);
     ps.setLong(4, bin.bts);

     ResultSet resultSet = ps.executeQuery();

     if (resultSet.next())
     {
     pdto.dname=resultSet.getString("dname");
     pdto.name=resultSet.getString("name");
     pdto.segname=resultSet.getString("segname");
     pdto.fband=resultSet.getLong("fband");
     pdto.bts=resultSet.getLong("bts");
     pdto.admstate=resultSet.getLong("adm");
     pdto.bcc=resultSet.getLong("bcc");
     pdto.ncc=resultSet.getLong("ncc");
     pdto.hopp=resultSet.getLong("hopp");
     pdto.cbarr=resultSet.getLong("cbarr");
     pdto.cnHW=resultSet.getLong("cnhw");
     pdto.hopmode=resultSet.getLong("hopmode");
     pdto.lac=resultSet.getLong("lac");
     pdto.mcc=resultSet.getLong("mcc");
     pdto.mnc=resultSet.getLong("mnc");
     pdto.mbc=resultSet.getLong("mbc");
     pdto.nsei=resultSet.getLong("nsei");
     pdto.rac=resultSet.getLong("rac");
     pdto.sectorid=resultSet.getLong("sectorid");
     pdto.segid=resultSet.getLong("segid");
     }

     else {
     pdto.dname="";
     pdto.name="";
     pdto.segname="";
     pdto.fband=-1;
     pdto.bts=-1;
     pdto.admstate=-1;
     pdto.lac=-1;
     }
     resultSet.close();
  }
  catch (Exception e)
  {
    logger.fatal("DAO" + e.toString());
    System.out.println("Error "+e.toString());
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

public static btsDTO GetMasterbtsDATA(long cellid)
  {
  Connection cn = null;
  PreparedStatement ps = null;
  String sql = null;
  btsDTO pdto = new btsDTO();
  pdto.cellid=cellid;

try{
     cn = DatabaseManager.getInstance().getConnection();

     sql = "select dname,bsc,bcf,bts,fband,name,adm,bcc,ncc,hopp,cbarr,cnhw,hopmode,lac,mcc," +
       "mnc,mbc,nsei,nwname,rac,sectorid,segid,segname from nokia.bts where cellid = ?" +
       " and mbcf=1";
     ps = cn.prepareStatement(sql);
     ps.setLong(1, pdto.cellid);

     ResultSet resultSet = ps.executeQuery();

     if (resultSet.next())
     {
     pdto.dname=resultSet.getString("dname");
     pdto.bsc=resultSet.getLong("bsc");
     pdto.bcf=resultSet.getLong("bcf");
     pdto.bts=resultSet.getLong("bts");
     pdto.name=resultSet.getString("name");
     pdto.segname=resultSet.getString("segname");
     pdto.fband=resultSet.getLong("fband");
     pdto.admstate=resultSet.getLong("adm");
     pdto.bcc=resultSet.getLong("bcc");
     pdto.ncc=resultSet.getLong("ncc");
     pdto.hopp=resultSet.getLong("hopp");
     pdto.cbarr=resultSet.getLong("cbarr");
     pdto.cnHW=resultSet.getLong("cnhw");
     pdto.hopmode=resultSet.getLong("hopmode");
     pdto.lac=resultSet.getLong("lac");
     pdto.mcc=resultSet.getLong("mcc");
     pdto.mnc=resultSet.getLong("mnc");
     pdto.mbc=resultSet.getLong("mbc");
     pdto.nsei=resultSet.getLong("nsei");
     pdto.rac=resultSet.getLong("rac");
     pdto.sectorid=resultSet.getLong("sectorid");
     pdto.segid=resultSet.getLong("segid");
     }

     else {
     pdto.dname="";
     pdto.name="";
     pdto.bsc=-1;
     pdto.bcf=-1;
     pdto.bts=-1;
     pdto.segname="";
     pdto.fband=-1;
     pdto.admstate=-1;
     pdto.lac=-1;
     }
     resultSet.close();
  }
  catch (Exception e)
  {
    logger.fatal("DAO" + e.toString());
    System.out.println("Error "+e.toString());
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

public static void changeBTSncC(btsDTO tdto)
{
       Connection cn = null;
       PreparedStatement ps = null;
       String sql = null;

       try
       {
       cn = DatabaseManager.getInstance().getConnection();

sql = "update nokia.bts set ncc="+tdto.ncc+" where cellid="+tdto.cellid+";";

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


public static void changeBTSbcC(btsDTO tdto)
{
       Connection cn = null;
       PreparedStatement ps = null;
       String sql = null;

       try
       {
       cn = DatabaseManager.getInstance().getConnection();

sql = "update nokia.bts set bcc="+tdto.bcc+" where cellid="+tdto.cellid+";";

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

}///class

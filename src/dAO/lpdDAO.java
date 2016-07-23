/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dAO;

import org.apache.log4j.Logger;
import java.sql.*;
import databasemanager.*;
import dTO.lpdDTO;
import dTO.trxDTO;

/**
 *
 * @author zahurul
 */
public class lpdDAO {
static Logger logger = Logger.getLogger(lpdDAO.class.getName());

public lpdDAO(){

}

public static void createTableLPD()
{
    Connection cn = null;
    PreparedStatement ps = null;
    String sql = null;

try
{
         cn = DatabaseManager.getInstance().getConnection();

sql = "CREATE TABLE nokia.lpd(id MEDIUMINT primary key auto_increment,ver varchar(18)," +
        "dname varchar(100),bsc numeric(18),lpd numeric(18),sid numeric(18)," +
        "brate numeric(18),abispcm numeric(18),abistsl numeric(18),abisssl numeric(18)," +
        "adm numeric(18),dchantype numeric(18)," +
        "lbcsuadd numeric(18),name varchar(18),paramset numeric(18),sapi numeric(18)," +
        "tei numeric(18)) ENGINE=MEMORY;";

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


public static void loadTableLPD()
{
          Connection cn = null;
          PreparedStatement ps = null;
          String sql = null;

  try
  {
     cn = DatabaseManager.getInstance().getConnection();

sql = "LOAD DATA INFILE 'C:/Program Files/MySQL/MySQL Server 5.0/data/nokia/in_lpd' INTO TABLE nokia.lpd FIELDS TERMINATED BY ',' ENCLOSED BY '\"'  LINES TERMINATED BY '\r\n';" ;

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

public static void dropTableLPD()
{
        Connection cn = null;
        PreparedStatement ps = null;
        String sql = null;
try
{
    cn = DatabaseManager.getInstance().getConnection();

sql = "drop table nokia.lpd;" ;

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


public static lpdDTO GetlpdDATA(trxDTO tin)
  {
  Connection cn = null;
  PreparedStatement ps = null;
  String sql = null;
  lpdDTO ldto = new lpdDTO();

try{
     cn = DatabaseManager.getInstance().getConnection();

     sql = "select ver,dname,lpd,sid,brate,abispcm,abistsl,abisssl,adm,dchantype,lbcsuadd,paramset,sapi,tei " +
             "from nokia.lpd where bsc = ? and name= ?";

     ps = cn.prepareStatement(sql);
     ps.setLong(1, tin.bsc);
	 ps.setString(2, tin.lapdName);

     ResultSet resultSet = ps.executeQuery();

     if (resultSet.next())
     {
     ldto.ver=resultSet.getString("ver");
     ldto.dname=resultSet.getString("dname");
     ldto.lpd=resultSet.getLong("lpd");
     ldto.sid=resultSet.getLong("sid");
     ldto.brate=resultSet.getLong("brate");
     ldto.abispcm=resultSet.getLong("abispcm");
     ldto.abistsl=resultSet.getLong("abistsl");
     ldto.abisssl=resultSet.getLong("abisssl");
     ldto.admstate=resultSet.getLong("adm");
     ldto.dchantype=resultSet.getLong("dchantype");
     ldto.lbcsuadd=resultSet.getLong("lbcsuadd");
     ldto.paramset=resultSet.getLong("paramset");
     ldto.sapi=resultSet.getLong("sapi");
     ldto.tei=resultSet.getLong("tei");

     }

     else {
     ldto.dname="";
     ldto.name="";
     ldto.admstate=-1;
	 ldto.lpd=-1;
     }
     resultSet.close();
  }
  catch (Exception e)
  {
    logger.fatal("DAO" + e.toString());
    System.out.println("Error from getlpddata "+e.toString());
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
  return ldto;
} //functs end


public static long GetlpdBCSUID(lpdDTO lin)
  {
  Connection cn = null;
  PreparedStatement ps = null;
  String sql = null;
  long lbcsu=-1;

try{
     cn = DatabaseManager.getInstance().getConnection();

     sql = "select distinct lbcsuadd from nokia.lpd where bsc = ? and abispcm= ?"
             +" and abistsl= ? and abisssl=?";

     ps = cn.prepareStatement(sql);
     ps.setLong(1, lin.bsc);
	 ps.setLong(2, lin.abispcm);
     ps.setLong(3, lin.abistsl);
     ps.setLong(4, lin.abisssl);

     ResultSet resultSet = ps.executeQuery();

     if (resultSet.next())
     {
     lbcsu=resultSet.getLong("lbcsuadd");
     }

     else {
     lbcsu=-1;
     }
     resultSet.close();
  }
  catch (Exception e)
  {
    logger.fatal("DAO" + e.toString());
    System.out.println("Error from GetlpdBCSUID "+e.toString());
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
  return lbcsu;
} //functs end

public static void updateLPD(long bsc,long lpd, long value)
  {
  Connection cn = null;
  PreparedStatement ps = null;
  String sql = null;

try{
     cn = DatabaseManager.getInstance().getConnection();

     sql = "set lpd=-1 where bsc=? and lpd=? ";

     ps = cn.prepareStatement(sql);
     ps.setLong(1, bsc);
     ps.setLong(2, lpd);
     ps.setLong(3, value);

     ps.executeUpdate();
  }
  catch (Exception e)
  {
    logger.fatal("DAO" + e.toString());
    System.out.println("Error from GetlpdBCSUID "+e.toString());
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

} //functs end
public static void createLPD(lpdDTO lin)
  {
  Connection cn = null;
  PreparedStatement ps = null;
  String sql = null;

try{
     cn = DatabaseManager.getInstance().getConnection();

     sql = "insert into lpd(bsc,lpd) values (?,?) ";

     ps = cn.prepareStatement(sql);
     ps.setLong(1, lin.bsc);
     ps.setLong(2, lin.lpd);

     ps.executeUpdate();
  }
  catch (Exception e)
  {
    logger.fatal("DAO" + e.toString());
    System.out.println("Error from GetlpdBCSUID "+e.toString());
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

} //functs end


public static long getMaxLPD(long bsc)
 {
  Connection cn = null;
  PreparedStatement ps = null;
  String sql = null;
  long maxLpd=-1;

try{
     cn = DatabaseManager.getInstance().getConnection();

     sql = " select max(lpd) as lpd from lpd where bsc=? ";

     ps = cn.prepareStatement(sql);
     ps.setLong(1, bsc);

     ResultSet resultSet = ps.executeQuery();

     if (resultSet.next())
     {
     maxLpd=resultSet.getLong("lpd");
     }
     else {
     maxLpd=-1;
     }
     resultSet.close();
  }
  catch (Exception e)
  {
    logger.fatal("DAO" + e.toString());
    System.out.println("Error from GetlpdBCSUID "+e.toString());
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

  return maxLpd;
} //functs end


}///class

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dAO;

import org.apache.log4j.Logger;
import java.sql.*;
import databasemanager.*;
import dTO.chnDTO;
import dTO.trxDTO;

/**
 *
 * @author zahurul
 */
public class chnDAO {
static Logger logger = Logger.getLogger(chnDAO.class.getName());

public chnDAO(){

}

public static void createTableCHN()
{
    Connection cn = null;
    PreparedStatement ps = null;
    String sql = null;

try
{
         cn = DatabaseManager.getInstance().getConnection();

sql = "CREATE TABLE nokia.chn(id MEDIUMINT primary key auto_increment,ver varchar(18)," +
        "dname varchar(100),bsc numeric(18),bcf numeric(18),bts numeric(18),trxid numeric(18)"+
        ",chanid numeric(18),sid numeric(18),chnadmstate numeric(18)," +
        "maio numeric(18),pcm numeric(18),subslot numeric(18),tsl numeric(18)," +
        "chntype numeric(18)) ENGINE=MEMORY;";

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


public static void loadTableCHN()
{
          Connection cn = null;
          PreparedStatement ps = null;
          String sql = null;

  try
  {
     cn = DatabaseManager.getInstance().getConnection();

sql = "LOAD DATA INFILE 'C:/Program Files/MySQL/MySQL Server 5.0/data/nokia/in_chn' INTO TABLE nokia.chn FIELDS TERMINATED BY ',' ENCLOSED BY '\"'  LINES TERMINATED BY '\r\n';" ;

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

public static void dropTableCHN()
{
        Connection cn = null;
        PreparedStatement ps = null;
        String sql = null;
try
{
    cn = DatabaseManager.getInstance().getConnection();

sql = "drop table nokia.chn;" ;

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

public static chnDTO GetchnDATA(trxDTO tin, long chanid)
  {
  Connection cn = null;
  PreparedStatement ps = null;
  String sql = null;
  chnDTO cdto = new chnDTO();

try{
     cn = DatabaseManager.getInstance().getConnection();

     sql = "select ver,dname,sid,chnadmstate,maio,pcm,subslot,tsl,chntype from nokia.chn where bsc = ?" +
           " and bcf= ? and bts= ? and trxid = ? and chanid= ?";
     ps = cn.prepareStatement(sql);
     ps.setLong(1, tin.bsc);
	 ps.setLong(2, tin.bcf);
     ps.setLong(3, tin.bts);
	 ps.setLong(4, tin.trxid);
	 ps.setLong(5, chanid);
     ResultSet resultSet = ps.executeQuery();

     if (resultSet.next())
     {

     cdto.dname=resultSet.getString("dname");
     cdto.ver=resultSet.getString("ver");
     cdto.chnadmstate=resultSet.getLong("chnadmstate");
     cdto.sid=resultSet.getLong("sid");
     cdto.maio=resultSet.getLong("maio");
     cdto.pcm=resultSet.getLong("pcm");
     cdto.subslot=resultSet.getLong("subslot");
     cdto.tsl=resultSet.getLong("tsl");
     cdto.chntype=resultSet.getLong("chntype");
     }

     else {
     cdto.dname="";
     cdto.chnadmstate=-1;
	 cdto.pcm=-1;
     }
     resultSet.close();
  }
  catch (Exception e)
  {
    logger.fatal("DAO" + e.toString());
    System.out.println("Error from getchndata "+e.toString());
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
  return cdto;
} //functs end

}///class

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dAO;

import org.apache.log4j.Logger;
import java.sql.*;
import databasemanager.*;
import dTO.adjDTO;

/**
 *
 * @author zahurul
 */
public class adjDAO {
static Logger logger = Logger.getLogger(adjDAO.class.getName());

public adjDAO(){

}

public static void createTableADJ()
{
    Connection cn = null;
    PreparedStatement ps = null;
    String sql = null;

try
{
         cn = DatabaseManager.getInstance().getConnection();

sql = "CREATE TABLE nokia.adj(id MEDIUMINT primary key auto_increment,ver varchar(18)," +
        "dname varchar(100),bsc numeric(18),bcf numeric(18),bts numeric(18),sid numeric(18),fband numeric(18),tgtDN varchar(150)," +
        "tgtbcc numeric(18),tgtncc numeric(18),targetid numeric(18),tgtlac numeric(18)," +
        "tgtmcc numeric(18),tgtmnc numeric(18),adjid numeric(18),tgtbcch numeric(18)," +
        "tgtgena numeric(18), sync numeric(18)) ENGINE=MEMORY;";

               ps = cn.prepareStatement(sql);
               ps.executeUpdate();
          }

          catch (Exception e)
          {
           logger.fatal("DAO" + e.toString());
           System.out.println("Error from createTableADJ "+e.toString());
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


public static void loadTableADJ()
{
          Connection cn = null;
          PreparedStatement ps = null;
          String sql = null;

  try
  {
     cn = DatabaseManager.getInstance().getConnection();

sql = "LOAD DATA INFILE 'C:/Program Files/MySQL/MySQL Server 5.0/data/nokia/in_adj' INTO TABLE nokia.adj FIELDS TERMINATED BY ',' ENCLOSED BY '\"'  LINES TERMINATED BY '\r\n';" ;

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

public static void dropTableADJ()
{
        Connection cn = null;
        PreparedStatement ps = null;
        String sql = null;
try
{
    cn = DatabaseManager.getInstance().getConnection();

sql = "drop table nokia.adj;" ;

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

public static void updateTableADJSRCInfo()
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
	
	sql1 = "alter table nokia.adj add column sourceid numeric(18);";
	sql2 = "update nokia.adj,nokia.bts set sourceid=cellid where adj.dname=bts.dname;";
    sql3 = "CREATE INDEX inadj USING BTREE ON adj (dname);";
    sql4 = "CREATE INDEX inbts USING BTREE ON bts (dname);";
    sql33 = "drop INDEX inadj ON adj ;";
    sql44 = "drop INDEX inbts ON bts;";
	
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


public static adjDTO getADJbyadjID(adjDTO adto2)
{
        Connection cn = null;
        PreparedStatement ps = null;
        String sql = null;
        adjDTO adto=new adjDTO();
try
{
    cn = DatabaseManager.getInstance().getConnection();

sql = "select id,ver,dname,sid,fband,tgtDN,tgtbcc,tgtncc,targetid," +
       "tgtlac,tgtmcc,tgtmnc,tgtbcch,tgtgena,sync from adj where sourceid=? " +
       " and adjid=?;" ;

     ps = cn.prepareStatement(sql);

     ps.setLong(1, adto2.sourceid);
     ps.setLong(2, adto2.adjIndex);

     ResultSet resultSet = ps.executeQuery();
     
     if (resultSet.next()){
     adto.targetid=resultSet.getLong("targetid");
     adto.dname=resultSet.getString("dname");
     }
     
     else{adto.targetid=-1;}
      resultSet.close();

}
catch (Exception e)
{

           logger.fatal("DAO" + e.toString());
           System.out.println("From getADJ Error "+e.toString());
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

         return adto;
}// funct
public static DAOResult addADJEntry(adjDTO p_dto){

    DAOResult daoResult = new DAOResult();
    Connection cn = null;
    PreparedStatement ps = null;
    String sql = null;

    //System.out.println(p_dto.dname);
    try
    {
      cn = DatabaseManager.getInstance().getConnection();
      sql = " insert into nokia.adj(id,sourceid,targetid,adjid,dname,tgtlac) values (?,?,?,?,?,?) ";
      ps = cn.prepareStatement(sql);
      ps.setLong(1, DatabaseManager.getInstance().getNextSequenceId("nokia.adj"));
      ps.setLong(2, p_dto.sourceid);
      ps.setLong(3, p_dto.targetid);
      ps.setLong(4, p_dto.adjIndex);
      ps.setString(5, p_dto.dname);
      ps.setLong(6, p_dto.tgtlac);

      ps.executeUpdate();
      daoResult.setResult("", true, DAOResult.DONE);
    }
    catch (Exception e)
    {
      daoResult.setResult(e.toString(), false, DAOResult.DB_EXCEPTION);
      logger.fatal("DAO" + e.toString());
      System.out.println(e.toString());
    }
    finally
    {
      try
      {
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
    return daoResult;
}///func



public static adjDTO getADJbySrcTarget(adjDTO adto2)
{
        Connection cn = null;
        PreparedStatement ps = null;
        String sql = null;
        adjDTO adto=new adjDTO();
try
{
    cn = DatabaseManager.getInstance().getConnection();

sql = "select id,ver,dname,sid,fband,tgtDN,tgtbcc,tgtncc,tgtlac,adjid," +
       "tgtmcc,tgtmnc,tgtbcch,tgtgena,sync from adj where sourceid=? " +
       " and targetid=?;" ;

     ps = cn.prepareStatement(sql);

     ps.setLong(1, adto2.sourceid);
     ps.setLong(2, adto2.targetid);

     ResultSet resultSet = ps.executeQuery();

     if (resultSet.next()){
     adto.adjIndex=resultSet.getLong("adjid");
     adto.tgtlac=resultSet.getLong("tgtlac");
     adto.dname=resultSet.getString("dname");
     adto.tgtDN=resultSet.getString("tgtDN");
     }

     else{adto.adjIndex=-1;}
      resultSet.close();

}
catch (Exception e)
{

           logger.fatal("DAO" + e.toString());
           System.out.println("From getADJ Error "+e.toString());
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

         return adto;
}// funct


public static DAOResult delADJEntry(adjDTO p_dto){

    DAOResult daoResult = new DAOResult();
    Connection cn = null;
    PreparedStatement ps = null;
    String sql = null;

    //System.out.println(p_dto.dname);
    try
    {
      cn = DatabaseManager.getInstance().getConnection();
      sql = " delete from nokia.adj where sourceid=? and targetid=? ";
      ps = cn.prepareStatement(sql);
      ps.setLong(1, p_dto.sourceid);
      ps.setLong(2, p_dto.targetid);

      ps.executeUpdate();
      daoResult.setResult("", true, DAOResult.DONE);
    }
    catch (Exception e)
    {
      daoResult.setResult(e.toString(), false, DAOResult.DB_EXCEPTION);
      logger.fatal("DAO" + e.toString());
      System.out.println(e.toString());
    }
    finally
    {
      try
      {
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
    return daoResult;
}///func

}///class

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dAO;

import org.apache.log4j.*;
import java.sql.*;
import databasemanager.DatabaseManager;

/**
 *
 * @author zahurul
 */
public class bcfDAO {
static Logger logger = Logger.getLogger(bcfDAO.class.getName());

public bcfDAO(){

}

public static void createTableBCF()
{
    Connection cn = null;
    PreparedStatement ps = null;
    String sql = null;

try
{
         cn = DatabaseManager.getInstance().getConnection();

sql = "CREATE TABLE nokia.bcf(id MEDIUMINT primary key auto_increment,ver varchar(18)," +
        "dname varchar(100),bsc numeric(18),bcf numeric(18),sid numeric(18),lat numeric(18),longg numeric(18)," +
        "name varchar(18),adm numeric(18),autocfg numeric(18),bbupr numeric(18)," +
        "bcchbbut numeric(18),bcftype numeric(18),sitesubtype numeric(18)," +
        "lapdname varchar(18),lapdnumber numeric(18),ntrxbbuTimer numeric(18)," +
        "port0 numeric(18),port1 numeric(18),port2 numeric(18),port3 numeric(18)," +
        "port4 numeric(18),port5 numeric(18),port6 numeric(18),port7 numeric(18)," +
        "rxdLimit numeric(18),autounl numeric(18),clksrc numeric(18)," +
        "ftrsAbisgrm numeric(18),mclkBCF numeric(18)) ENGINE=MEMORY;";

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


public static void loadTableBCF()
{
          Connection cn = null;
          PreparedStatement ps = null;
          String sql = null;

  try
  {
     cn = DatabaseManager.getInstance().getConnection();

sql = "LOAD DATA INFILE 'C:/Program Files/MySQL/MySQL Server 5.0/data/nokia/in_bcf' INTO TABLE nokia.bcf FIELDS TERMINATED BY ',' ENCLOSED BY '\"'  LINES TERMINATED BY '\r\n';" ;

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

public static void dropTableBCF()
{
        Connection cn = null;
        PreparedStatement ps = null;
        String sql = null;
try
{
    cn = DatabaseManager.getInstance().getConnection();

sql = "drop table nokia.bcf;" ;

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

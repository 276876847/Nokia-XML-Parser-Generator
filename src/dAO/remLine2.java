/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import nokiaParse.parseNokiaCM;



/**
 *
 * @author zahurul
 */
public class remLine2 {

  public static void removeLineFromFile(File inFile, String lineToRemove) {

    try {

//      File inFile = new File(file);

      if (!inFile.isFile()) {
        System.out.println("Filename incorrect.. pls check");
        return;
      }

      //Construct the new file that will later be renamed to the original filename.
      File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

      BufferedReader br = new BufferedReader(new FileReader(inFile));
      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

      String line = null;

      //Read from the original file and write to the new
      //unless content matches data to be removed.
      while ((line = br.readLine()) != null) {

        if (!line.trim().equals(lineToRemove)) {

          pw.println(line);
          pw.flush();
        }
      }
      pw.close();
      br.close();

      //Delete the original file
      if (!inFile.delete()) {
        System.out.println("Could not delete original file");
        return;
      }

      //Rename the new file to the filename the original file had.
      if (!tempFile.renameTo(inFile))
        System.out.println("Could not rename temp file to original");

    }
    catch (FileNotFoundException ex) {
      ex.printStackTrace();
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
  }///func


  public static void remove2ndLineFromALLXMLFile() {

      File dbfolder=new File("C://ranmaster//DB//2G//XML_DATABASE//"+ parseNokiaCM.CurrDate());
      File[] f=dbfolder.listFiles();

      for(int i=0;i< f.length;i++){
          try{
          removeLineFromFile(f[i],"<!DOCTYPE raml SYSTEM 'raml20.dtd'>");
          }
          catch(Exception ex0){System.out.println(ex0);
          ex0.printStackTrace();}
      }


  }///func


}//class

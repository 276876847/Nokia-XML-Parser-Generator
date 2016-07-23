/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dTO;

/**
 *
 * @author zahurul
 */
public class paramDTO {

final int maxparamNo=20;

public paramDTO(){

    for(int i = 0; i <= maxparamNo; i++){
        this.attrName[i] = "garbage";
        this.attrValue[i] = "garbage";
        //System.out.println(this.attrName[i]);
    }///for
}///cons

public String[] attrName=new String[100];
public String[] attrValue=new String[100];

}//class

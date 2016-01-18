/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javautf;

import java.io.File;

public class JavaUtf

{

public static void main (String args[])

throws Exception

{

File path = new File("C:\\Users\\nk91008743\\Desktop\\new" );

String element0;

String element;

byte[] bytes;

for (File f : path.listFiles()) 

{

    element0 = new String("привет"); 

    bytes = f.getName().getBytes("UTF-8");
    element = new String(bytes, "UTF-8"); 
    System.out.println(element);
}
   }
}
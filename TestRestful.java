/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package testrestful;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author nk91008743
 */
public class TestRestful {

    /**
     * @param args the command line arguments
     */
    private static final String logonURL = "http://kzatapp202:6405/biprws/logon/long/";
    private static final String infoStoreURL = "http://kzatapp202:6405/biprws/infostore";
    private static final String logonText = "<attrs xmlns=\"http://www.sap.com/rws/bip\">\n"+
                                                "<attr name=\"userName\" type=\"string\">Admin	</attr>\n"+
                                                "<attr name=\"password\" type=\"string\">password</attr>\n"+
                                                "<attr name=\"auth\" type=\"string\""+
                                                " possibilities=\"secEnterprise,secLDAP,secWinAD,secSAPR3\">secEnterprise</attr>\n"+
                                            "</attrs>\n";

    
    public static void main(String[] args) {
        getRequest(logonURL,logonText);
      }
    
    public static void getRequest(String url, String parameters){
        try {
            
            URL obj = new URL("http://services.groupkt.com/country/get/iso2code/KZ	");

            HttpURLConnection con  = (HttpURLConnection) obj.openConnection();

            
            String postString =                "<attr name=\"userName\" type=\"string\">Administrator</attr>"+
                                                "<attr name=\"password\" type=\"string\">Admin123</attr>"+
                                                "<attr name=\"auth\" type=\"string\" possibilities=\"secEnterprise,secLDAP,secWinAD,secSAPR3\">secEnterprise</attr>"+
                                            "</attrs>";
            System.out.println(postString);
            byte[] postData       = postString.getBytes( StandardCharsets.UTF_8 );
            
            
            //con.setRequestProperty("Accept", "application/xml");
            //con.setRequestProperty("Content-Type", "application/xml");
            con.setDoOutput(true);
            //con.setRequestMethod("GET");
            //con.setRequestProperty( "charset", "utf-8");
            //con.setRequestProperty( "Content-Length", Integer.toString( postData.length ));
            //con.setUseCaches(false);
            
            
            //DataOutputStream outStream = new DataOutputStream(con.getOutputStream());
            BufferedReader inStream = new BufferedReader(new InputStreamReader(con.getInputStream()));
            //outStream.write(postData);
            //outStream.flush();
            
            String buffer;
            while((buffer = inStream.readLine()) != null) {
                System.out.println(buffer);
            }
            
            inStream.close();
            //outStream.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}

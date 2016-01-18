/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package testrestful;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
    private static final String userFolders = "http://kzatapp202:6405/biprws/infostore/Relationships";
    private static final String logonText = "<attrs xmlns=\"http://www.sap.com/rws/bip\">\n"+
                                                "<attr name=\"userName\" type=\"string\">Username</attr>\n"+
                                                "<attr name=\"password\" type=\"string\">password</attr>\n"+
                                                "<attr name=\"auth\" type=\"string\""+
                                                " possibilities=\"secEnterprise,secLDAP,secWinAD,secSAPR3\">secEnterprise</attr>\n"+
                                            "</attrs>\n";

    
    public static void main(String[] args) throws Exception {
       // System.setProperty("java.net.useSystemProxies", "true");
        HashMap<String, String> hmap = new HashMap<String, String>();
        hmap.put("X-SAP-LogonToken", "KZATAPP202.okioc.com:6400@{3&2=15663,U3&2v=KZATAPP202.okioc.com:6400,UP&66=60,U3&68=secEnterprise:Administrator,UP&S9=12,U3&qe=100,U3&vz=0kry.6JiQKtSH9KfU7_013cWKLshhITt0rZ4pVgiytQ,UP}");
                                      
        
        //System.out.println(doPost(logonURL, logonText));
        System.out.println(doGet("http://kzatapp202:6405/biprws/infostore/6207/relationships", hmap));
    }
    
    public static String doGet(final String urlToRead, HashMap<String, String> headers ) throws Exception{
        HttpURLConnection connection = (HttpURLConnection) new URL(urlToRead).openConnection();
        
        final String charset = "UTF-8";
        
        connection.setRequestProperty("Accept-Charset", charset);
        
        connection.setRequestProperty("Accept", "application/xml");
        connection.setRequestProperty("Content-type", "application/xml");
        
        Iterator it = headers.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            connection.setRequestProperty(""+pair.getKey(), ""+pair.getValue());
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
        
        InputStream inputStream = connection.getErrorStream();
        if (inputStream == null)
            inputStream = connection.getInputStream();

        // Read everything from our stream
        BufferedReader responseReader = new BufferedReader(new InputStreamReader(inputStream, charset));

        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = responseReader.readLine()) != null) {
            response.append(inputLine).append("\n");
        }
        responseReader.close();
    
        return response.toString();
        
    }
            
    public static String doPost(final String urlToRead, final String content) throws IOException {
        final String charset = "UTF-8";
        HttpURLConnection connection = (HttpURLConnection) new URL(urlToRead).openConnection();
        
        // setDoOutput(true) implicitly set's the request type to POST
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept-Charset", charset);
        connection.setRequestProperty("Accept", "application/xml");
        connection.setRequestProperty("Content-type", "application/xml");

        // Write to the connection
        OutputStream output = connection.getOutputStream();
        output.write(content.getBytes(charset));
        output.close();

        // Check the error stream first, if this is null then there have been no issues with the request
        InputStream inputStream = connection.getErrorStream();
        if (inputStream == null)
            inputStream = connection.getInputStream();

        // Read everything from our stream
        BufferedReader responseReader = new BufferedReader(new InputStreamReader(inputStream, charset));

        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = responseReader.readLine()) != null) {
            response.append(inputLine);
        }
        responseReader.close();

        return response.toString();
    }
    
}

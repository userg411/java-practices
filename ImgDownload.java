/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package img;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory ;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author nk91008743
 */
public class Img {
    final static String UPLOAD_PATH = "C:/Users/nk91008743/Desktop/excels/";
    
    
    /**
     * @param args the command line arguments
     */
    
    
    
public static void main(String[] args) throws Exception{
    
    HSSFWorkbook result = new HSSFWorkbook();
    
    File dir = new File(UPLOAD_PATH);
    File[] directoryListing = dir.listFiles();
    
    Workbook wb = WorkbookFactory.create(new File(UPLOAD_PATH+"/T5UBF.XLSX"));
    
    Sheet sheet  = wb.getSheetAt(0);
    System.out.println(sheet.getProtect());
    Row row = sheet.getRow(0);
    Cell cell = row.getCell(0);
    System.out.println(cell.getStringCellValue());
    
    
    /*if (directoryListing != null) {
        for (File child : directoryListing) {
            System.out.println("Reading from: "+child.getAbsolutePath());
            Workbook wb = WorkbookFactory.create(child);
            Sheet sheet  = wb.getSheetAt(0);
            
        
        
        }
        
    }*/
    FileOutputStream out = new FileOutputStream(new File("C:/Users/nk91008743/Desktop/result.xls"));
    result.write(out);
    out.close();
}
        
    public static void generatePics() throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
        
        //String dbURL = "jdbc:oracle:thin:@oradevmw";
        //Class.forName("oracle.jdbc.driver.OracleDriver");
        
        Connection conn = null;
        Statement stmt = null; 
        try {
            //conn = DriverManager.getConnection(jdbc:oracle:thin:@oradevmw, "serik", "1");
            conn = DriverManager.getConnection("jdbc:sqlserver://kzatsrv0017;user=SAP;password=123;database=APACS36ATEW");
            System.out.println("Connection established");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tcardimages where not fimg is null");
             
            while(rs.next()){
                
                Blob ph = rs.getBlob("fimg");
                InputStream in = ph.getBinaryStream();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                OutputStream outputStream = new FileOutputStream ("C:/Users/nk91008743/Desktop/test/"+rs.getInt("fid")+".jpg");

                int length = (int) ph.length();
                int bufferSize = 1024;

                byte[] buffer = new byte[bufferSize];

                while ((length = in.read(buffer)) != -1) {
                    //System.out.println("writing " + length + " bytes");
                    out.write(buffer, 0, length);   
                }
                out.writeTo(outputStream); 
                in.close();
        
            }
            
            
            
            
            
            

            if (rs.next()) {
                System.out.println(rs.getString("name"));
            }
            
            
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally{
            if (stmt != null) try { stmt.close(); } catch (Exception e) {}
            if (conn != null) try { conn.close(); } catch (Exception e) {}  
        }
    
    }
    
    
    
}

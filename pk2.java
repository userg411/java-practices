import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;





import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.OracleDriver;


import java.io.*;
import java.util.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;



public class pk2
{
	private static String TNS_KEY; 
	private static String TNS_PATH;	
	private static String DB_USER_NAME;
	private static String DB_PASSWORD;
	private static String CONN_URL;
	private static String CORR_FILE_REV;
	private static String UPLOAD_PATH;
	private static String TB_IMPFILEEXCEL;
	private static String TB_LISTIMPFILEEXCEL;
	private static String STRING_OK;
	private static String STRING_KO;
	
	private static String ERR_FILE_EXCEL="File can not be opened: valid Excel file required";
	private static String ERR_EXCEL_ENTRY="Cell or required sheet does not exist: valid contractor sheet required";
	private static String ERR_FILE_REV="Wrong template or revision number";
	private static String ERR_DATE_ENTRY="Wrong date format";
	private static String ERR_EXCEL_FORMULA="Invalid Excel formula: remove external link or recreate the Excel file";
	private static String ERR_SQL="SQL Error: please contact BI Team";
	private static String ERR_UNDEFINED="Undefined Error: please contact BI Team";
	private static boolean isDifferentVersion = false;
	
	static String [] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    static String sqlText;
	static String insertImpFileExcelSql;
	
	
	public static  void readXLSFile( File file) 
	{
		
		String errorMessage="";
		String year="";
		String repPeriod="";
		String name="";
		String location="";
		String activity="";
		String number = "";
		String reportPeriod="";
		String loadDate="";
		boolean flag=false;
		
		try{
			org.apache.poi.ss.usermodel.Workbook  wb;
			org.apache.poi.ss.usermodel.Sheet sheet;
			Row row;
			InputStream ExcelFileToRead  = new FileInputStream(file);
			wb = WorkbookFactory.create(file);
			
			/*if(getFileExtension(file).equals("xls"))
			{
				
				wb  = new HSSFWorkbook(ExcelFileToRead);
				
			}	
			else
			{
				wb =  new XSSFWorkbook(ExcelFileToRead);
				
			}
			*/
			
			sheet = wb.getSheetAt(4);
				
			row = sheet.getRow(1); 
			//HSSFWorkbook wb  = new HSSFWorkbook(ExcelFileToRead);
			FormulaEvaluator evaluator  = wb.getCreationHelper().createFormulaEvaluator();
			Cell cell;
			Iterator cells = row.cellIterator();
			int columnCounter=1;
			int cellCounter = 0;
			
			SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
			SimpleDateFormat sdfLoad = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
			year = getCellStringValue(getCellValue("A2",evaluator, sheet));
			repPeriod = getCellStringValue(getCellValue("B2",evaluator, sheet));
			name = getCellStringValue(getCellValue("C2",evaluator, sheet)).replace("'","''");
			location = getCellStringValue(getCellValue("D2",evaluator, sheet)).replace("'","''");
			activity = getCellStringValue(getCellValue("E2",evaluator, sheet)).replace("'","''");
			reportPeriod = year+getPeriod(repPeriod);
			loadDate = sdfLoad.format(new Date());
			
			flag=true;
			
			
			if(!getCellStringValue(getCellValue("A8",evaluator, sheet)).equals(CORR_FILE_REV)){
				errorMessage=ERR_FILE_REV;
				
			}
			else
			{
			
				
				while (columnCounter<=102)
					{
						cell= row.getCell(cellCounter);
						CellValue cellValue = evaluator.evaluate(cell);
						if(columnCounter==10)
						{
							insertImpFileExcelSql+="'"+getCellStringValue(getCellValue("D4",evaluator, sheet)).replace("'","''")+"', ";
						}
						else if(columnCounter==11)
						{
							insertImpFileExcelSql+="'"+getCellStringValue(getCellValue("A4",evaluator, sheet)).replace("'","''")+"', ";
						}
						else if(columnCounter==12)
						{
							insertImpFileExcelSql+="'"+getCellStringValue(getCellValue("E4",evaluator, sheet)).replace("'","''")+"', ";
							cellCounter++;
						}
						else if(columnCounter==50)
						{
							insertImpFileExcelSql+="'"+getCellStringValue(getCellValue("C4",evaluator, sheet)).replace("'","''")+"', ";
						}
						else if(columnCounter==53||columnCounter==58||columnCounter==63||columnCounter==68||columnCounter==73||columnCounter==78||
							columnCounter==83||columnCounter==88||columnCounter==93||columnCounter==98)
						{
							Date date = (Date)sdf.parse(cell.getDateCellValue()+"");
							
							Calendar cal = Calendar.getInstance();
							cal.setTime(date);
							String formattedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +         cal.get(Calendar.YEAR);
							if(formattedDate.equals("31/12/1899"))
								insertImpFileExcelSql+="'',";
							else
								insertImpFileExcelSql+="to_date('"+formattedDate+"','dd/mm/YYYY'),";
							
							cellCounter++;
						}
						else if (cellValue.getCellType() == Cell.CELL_TYPE_STRING)
						{
							if(cellValue.getStringValue().equals(" "))
								insertImpFileExcelSql+="'',";
							else
								insertImpFileExcelSql+="'"+cellValue.getStringValue().replace("'","''")+"', ";
							cellCounter++;
							
						}
						else if(cellValue.getCellType() == Cell.CELL_TYPE_NUMERIC)
						{	
							//System.out.println(columnCounter+"="+ cellValue.getNumberValue()+" ");
							number = cellValue.getNumberValue()+"";
							if(number.endsWith(".0")) 
								number = number.substring(0,number.length()-2);
							else if(number.endsWith("E10")) 
								number = number.substring(0,number.length()-3).replace(".","");
							else if(number.length()==0)
								number = "0";
							insertImpFileExcelSql+="'"+number+"', ";
							cellCounter++;
						
						}
						else
						{
							
						}
						columnCounter++;
					}
				insertImpFileExcelSql+="'"+file.getAbsolutePath().replace("'","''")+"', '"+STRING_OK+"','" +reportPeriod+"',to_date('"+loadDate+"','DD/MM/YYYY HH24:MI:SS'))";
				
				executeSql(insertImpFileExcelSql);
				
			}
		}
		
		catch(IOException e){
			errorMessage=ERR_FILE_EXCEL;
			e.printStackTrace();
			}
		catch(NullPointerException e){
			errorMessage=ERR_EXCEL_ENTRY;
			e.printStackTrace();
			}
		catch(IllegalArgumentException e){
			errorMessage=ERR_EXCEL_ENTRY;
			e.printStackTrace();
			}
			
		catch(ParseException e){
			errorMessage=ERR_DATE_ENTRY;
			e.printStackTrace();
		}
		catch(SQLException e){
			errorMessage=ERR_SQL;
			e.printStackTrace();
		}
		catch(RuntimeException e){
			errorMessage=ERR_EXCEL_FORMULA;
			e.printStackTrace();
		}
		catch(Exception e){
			errorMessage=ERR_UNDEFINED;
			e.printStackTrace();
		}
		finally
		{
			insertImpFileExcelSql=sqlText;
		}
		
		if(errorMessage.length()>0)
		{
			String sql = "insert into "+ TB_LISTIMPFILEEXCEL+"( year,repperiod,name,location,activity,loadstatus,filename,errormessage,report_period,load_date) values('"
						 +year+"','"+repPeriod+"','"+name+"','"
						 +location+"','"+activity+"','" + STRING_KO +"','"+file.getAbsolutePath().replace("'","''")+"','"+errorMessage+"','" + reportPeriod+"',to_date('"+loadDate+"','DD/MM/YYYY HH24:MI:SS'))";
			try{executeSql(sql);}
			catch(Exception e)
			{
				String sqll = "insert into "+ TB_LISTIMPFILEEXCEL+"(errormessage,loadstatus,filename) values('Wrong template or revision number','"+STRING_KO+"','"+file.getAbsolutePath().replace("'","''")+"')";
				try{executeSql(sqll);}
				catch(Exception ee){ee.printStackTrace();System.exit(5);}
				
				
				
			}
		}
		else
		{
			String sql = "insert into "+ TB_LISTIMPFILEEXCEL+"( year,repperiod,name,location,activity,loadstatus,filename,errormessage,report_period,load_date) values('"
						 +year+"','"+repPeriod+"','"+name+"','"
						 +location+"','"+activity+"','"+STRING_OK+"','"+file.getAbsolutePath().replace("'","''")+"','"+errorMessage+"','" + reportPeriod+"',to_date('"+loadDate+"','DD/MM/YYYY HH24:MI:SS'))";
			try{executeSql(sql);}
			catch(Exception e){
				String sqll = "insert into "+ TB_LISTIMPFILEEXCEL+"(errormessage,loadstatus,filename) values('Wrong template or revision number','"+STRING_KO+"','"+file.getAbsolutePath().replace("'","''")+"')";
				try{executeSql(sqll);}
				catch(Exception ee){ee.printStackTrace();System.exit(5);}
				
				}; 
		}
		 
	}
	public static String getPeriod(String monthName){
		int position = Arrays.asList(months).indexOf(monthName)+1;
		if(position<10)
			return "0"+position;
		else
			return ""+position;
	}
	
	public static  CellValue getCellValue(String CellAddress,FormulaEvaluator evaluator, Sheet sheet)
	{
		CellReference cellReference  = new CellReference(CellAddress); 
		Row row  = sheet.getRow(cellReference.getRow());
		Cell cell  = row.getCell(cellReference.getCol());
		CellValue cellValue  = evaluator.evaluate(cell);	
		return cellValue;
	}
	
	
	public static  String getCellStringValue(CellValue cellValue)
	{
		String result="";
		

		switch (cellValue.getCellType()) {
			case Cell.CELL_TYPE_BOOLEAN:
				result  = cellValue.getBooleanValue()+"";
				break;
			case Cell.CELL_TYPE_NUMERIC:
				result  = cellValue.getNumberValue()+"";
				if(result.endsWith(".0"))
					result=result.substring(0,result.length()-2);
				break;
			case Cell.CELL_TYPE_STRING:
				result  = cellValue.getStringValue();
				break;
			case Cell.CELL_TYPE_BLANK:
				break;
			case Cell.CELL_TYPE_ERROR:
				break;
			case Cell.CELL_TYPE_FORMULA: 
				break;
		}
		
		
		
		return result;
		
	}
	public static void executeSql (String sql) throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        Connection conn = DriverManager.getConnection(CONN_URL,DB_USER_NAME,DB_PASSWORD);
		Statement stmt = conn.createStatement();
	    //System.out.println(sql);
		stmt.executeUpdate(sql);
        conn.close();
    }
	
	public static String getFileExtension(File file)
	{
		int i = file.getName().lastIndexOf('.');
			return file.getName().substring(i+1).toLowerCase();
		
	}
	public static void clearStlTables()
	{
		String sql1 = "truncate table "+ TB_IMPFILEEXCEL;
		String sql2 = "truncate table "+ TB_LISTIMPFILEEXCEL;
		try{executeSql(sql1);}catch(SQLException e){System.out.println("Cant access base tables");System.exit(4);}
		try{executeSql(sql2);}catch(SQLException e){System.out.println("Cant access base tables");System.exit(4);}
	}
	public static void initializeVariables(){
		
		Properties prop = new Properties();
		InputStream input = null;
 		try {
			
			input = new FileInputStream("config.properties");
			prop.load(input);
			
			TNS_KEY =prop.getProperty("tns_key");
			TNS_PATH	= prop.getProperty("tns_path");
			DB_USER_NAME = prop.getProperty("db_username");
			DB_PASSWORD = prop.getProperty("db_password");
			CONN_URL = prop.getProperty("conn_url");
			CORR_FILE_REV = prop.getProperty("file_rev");
			UPLOAD_PATH = prop.getProperty("upload_path").replace("/","\\");
			TB_IMPFILEEXCEL = prop.getProperty("impfileexcel");
			TB_LISTIMPFILEEXCEL = prop.getProperty("listimpfileexcel");
			STRING_OK = prop.getProperty("statusOKName");
			STRING_KO = prop.getProperty("statusKOName");
									      
			
			sqlText = "INSERT INTO "+ TB_IMPFILEEXCEL +" (year, repperiod, name, location, activity,avgempno,HoursWork, ExpWorkforceNextMont," 
									   +"km, noofincident, fire,RTA_Recordable, oilspills, volumespills, empfatal, Permantotaldisab, partialtotdisab, lostworkdaycas,"
									   +"noofltis, medicaltreatcases, restrictedworkdaycases,OCCUPAL3HEALTHCASES,FirstAidCases,TotalInjuries,"
									   +"NearMissIncidents, TBTorBriefings,SaferStopCards,HTraiDays,STraiDays,ETraiDays,HSETraiDays,IntHAudits,"
									   +"IntSHAudits,InatEHAudits, IntHSEHAudits,ExtHAudits, ExtSHAudits, ExtEHAudits, ExtHSEHAudits,"
									   +"Inspections, Drills, Exercises,Info1,compilername,Position,e_mail,Phone,Fax, AssetDamage, rta,"
									   +"IncidentID1, IncidentDesc1, IncidentDate1,IncidentClass1,IncidentRiskLev1,"
									   +"IncidentID2, IncidentDesc2, IncidentDate2,IncidentClass2,IncidentRiskLev2,"
									   +"IncidentID3, IncidentDesc3, IncidentDate3,IncidentClass3,IncidentRiskLev3,"
									   +"IncidentID4, IncidentDesc4, IncidentDate4,IncidentClass4,IncidentRiskLev4,"
									   +"IncidentID5, IncidentDesc5, IncidentDate5,IncidentClass5,IncidentRiskLev5,"
									   +"IncidentID6, IncidentDesc6, IncidentDate6,IncidentClass6,IncidentRiskLev6,"
									   +"IncidentID7, IncidentDesc7, IncidentDate7,IncidentClass7,IncidentRiskLev7,"
									   +"IncidentID8, IncidentDesc8, IncidentDate8,IncidentClass8,IncidentRiskLev8,"
									   +"IncidentID9, IncidentDesc9, IncidentDate9,IncidentClass9,IncidentRiskLev9,"
									   +"IncidentID10, IncidentDesc10, IncidentDate10,IncidentClass10,IncidentRiskLev10,PSITierI,PSITierII,filename, loadstatus,"
									   +"REPORT_PERIOD, LOAD_DATE) VALUES (";
			insertImpFileExcelSql = sqlText;
			System.setProperty(TNS_KEY,TNS_PATH);
			
		} catch (IOException ex) {
			System.out.println("Please check configurations file");
			System.exit(1);
			
		}
		catch(NullPointerException ex)
		{
			System.out.println("Null key value in configurations file");
			System.exit(2);
			
		}
	}
	
	
	public static void main(String[] args) 
	{
		
		initializeVariables();
		try{executeSql("select 1 from dual");} catch(SQLException e ){System.out.println("Check DB Connection");e.printStackTrace();System.exit(3);}
		
		clearStlTables();
		int fileCounter = 0;
		File dir = new File(UPLOAD_PATH);
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				System.out.println("Reading from: "+child.getAbsolutePath());
				if(getFileExtension(child).equals("xls")||getFileExtension(child).equals("xlsx")){
					readXLSFile(child);
					
				}
				else
				{
					String sql = "insert into "+ TB_LISTIMPFILEEXCEL+"(errormessage,loadstatus,filename) values('File can not be opened: valid Excel file required','"+STRING_KO+"','"+child.getAbsolutePath().replace("'","''")+"')";
					try{executeSql(sql);}
					catch(SQLException e){e.printStackTrace();}; 
				}
				fileCounter++;
			}
		} 
		else 
		{
			
		}	
		System.out.println("Done. Files processed "+ fileCounter);
 }
}
import java.io.*;
public class runtimeTest{
	public static void main(String args[]) throws Exception{
		Runtime rt = Runtime.getRuntime();
		System.out.println("Available processors to JVM: "+rt.availableProcessors());
		System.out.println("Free memory in JVM: "+rt.freeMemory());
		rt.addShutdownHook(new sdhook());
		//Process p =	rt.exec("cmd /c chrome nur.kz tengrinews.kz lenta.ru azh.kz",args, new File("C:/Program Files/Google/Chrome/Application"));
		Process p =	rt.exec("cmd /c chrome nur.kz tengrinews.kz lenta.ru azh.kz");
		BufferedReader in = new BufferedReader(
                                new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
			}
		//p.waitFor();
		p.destroy();	
	
	}
	private static class sdhook extends Thread{
		public void run(){
			System.out.println("From sdhook");
		}
		
	}
	
}
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class parseContacts{
	private static ArrayList<Contact> contacts;
	
	public static void main(String args[]) throws Exception{
		contacts = new ArrayList<Contact>();
		List <String> lines  = Files.readAllLines(Paths.get("in.vcf"),Charset.defaultCharset());
		for(int i=0; i<lines.size();){
			String name = "";
			String number = "";
			if(lines.get(i).equals("BEGIN:VCARD")){
				while(!lines.get(i).equals("END:VCARD")){
					String[] details = lines.get(i).split(":");
					if(details[0].equals("FN"))
						name = details[1];
					else if (details[0].equals("TEL;CELL;PREF")||details[0].equals("TEL;CELL"))
						number = details[1];	
					i++;
				}
			if(name.length()>0)	
				contacts.add(new Contact(name, number));
			
			}
			i++;
		}
		
		ArrayList <Contact> result = new ArrayList<Contact>();
		Iterator it = contacts.iterator();
		while(it.hasNext()){
			Contact c = (Contact)it.next();
			if(!result.contains(c))
				result.add(c);
		}
		Collections.sort(result);
		String out = "";
		for(Contact c:result){
			out+="BEGIN:VCARD" + "\n";
			out+="FN:" +c.name+ "\n";
			out+="TEL;CELL;PREF:" +c.number+ "\n";
			out+="END:VCARD" + "\n";
		}
		
		//System.out.println(out);
		
		Files.write(Paths.get("out.vcf"), out.getBytes());
		
	}
	static class Contact implements Comparable <Contact>{
		private String name;
		private String number;
		public Contact(String name, String number){
			this.name = name;
			this.number = number;
		}
		@Override
		public boolean equals(Object obj){
			return (obj!=null && obj instanceof Contact && this.number.equals(((Contact)obj).number) );
		}
		@Override
		public int compareTo(Contact con){
			return this.name.compareTo(con.name);
		}
		
	}
}

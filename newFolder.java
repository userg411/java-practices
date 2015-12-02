public class newFolder{
	public static void main(String args[]){
		String [] names = {"New Folder", "New Folder (1)","New Folder (2)","New Folder (3)","New Folder (4)"};
		String[] namess = {};
		System.out.println(New_Folder(namess));
	}
	public static String New_Folder(String[] files){
		if(files.length==0) return "New Folder";
		else if(files.length == 1) return "New Folder (1)";
		
		int max = 1;
		for(String s:files){
			
			if(s.equals("New Folder"))
				continue;
			
			int num =0;
			int start = s.indexOf('(');
			int end = s.indexOf(')');
			
			for(int i=start+1; i<end; i++){
				num += num*10 + s.charAt(i)-'0';
			}
			max = Math.max(max, num);
			
		}
		
		return "New Folder (" + (max+1)+")";
		
		
		
	}
	
}
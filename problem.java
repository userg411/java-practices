public class problem{
	public static void main(String[] args){
		/*String[][] in = {{"O","O","O"},
						 {"X","O","X"},
						 {"X","X","-"}}; 
		System.out.println(isTicTac(in));
		int a[] = {5, 2, 4, 1};
		System.out.println(missingNumber(a));
		int [] [] treasure = {{4,1}, {9,3}, {2,1}, {3,1}};
		System.out.println(max_treasure(4,treasure,5));
		System.out.println(differentSymbolsNaive("abcabdaaa"));
		String inArrayOne[] = {"A","E","I","AA"};
		String inArrayTwo[] = {"O","U","T","X"};
		String result[] = mergeArrays(inArrayOne,inArrayTwo);
		
		System.out.println(box(1));
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Fib(99));
		System.out.println(distribution("me pica aca y","c"));
		*/
		
		int a = 4;	/* 60 = 0011 1100 */  
		int b = 1;	/* 13 = 0000 1101 */
		int c = 0;

		c = a | b;       /* 12 = 0000 1100 */ 
		
		c = a & 1;     /* 215 = 1111 */
		//System.out.println("a & 1  = " + c );
		
		c = b & 1;     /* 215 = 1111 */
		//System.out.println("b & 1  = " + c );
		
		a>>=1;
		b>>=1;
		
		c = a & 1;     /* 215 = 1111 */
		//System.out.println("a & 1  = " + c );
		
		c = b | 1;     /* 215 = 1111 */
		//System.out.println("b | 1  = " + c );
		
		//System.out.println("Ham dist = "+hammingDistance(5,3));
		//System.out.println("Primeburg " + primeSum(8,17));
		//System.out.println("sum " + Sum(6));
		
		//withdraw(110);
		//System.out.println(isFibonacci(Integer.parseInt(args[0])));
		//System.out.println(Digital_sum(Integer.parseInt(args[0])));
		//System.out.println(HackIt(args[0]));
		//System.out.println(hexa(29));
		//System.out.println(LuckyNum(41,12312));
		//int  aa[]  = {1,2,3};
		//System.out.println(SumGroups(aa));
		//System.out.println(Anagram("silent","listen"));
		//System.out.println(Anagram("hey","yey"));
		//System.out.println(Chocolates(8932434 ,22));
		//System.out.println(nineDupe(9));
		//System.out.println(virusScan("sddszzvirusxxxx"));
		int [] A  = {6,4}, B = {1,2};
		System.out.println(19958%8);
		
	}
	
	
	static int BestMAtch(int N, int[] AL_AHLY_Goals, int[] Zamalek_Goals) {
		if(N==1) return 0;
		int d=AL_AHLY_Goals[0] - Zamalek_Goals[0],r = Zamalek_Goals[0], x=0;
		for(int i=1;i<N;i++)
		{
			
			if (AL_AHLY_Goals[i] - Zamalek_Goals[i]==d)
			{
				if(r<Zamalek_Goals[i])
					x=i;
			}
			else if (AL_AHLY_Goals[i] - Zamalek_Goals[i]<d)
			{
			  	d = AL_AHLY_Goals[i]-Zamalek_Goals[i];
				r=Zamalek_Goals[i];
				x=i;
				
	        }
			System.out.println("d = "+d+" r = "+r+" x = "+ x);
		
		}	
	    
    	 return x;
	}
	
	static boolean virusScan(String input) {
		return input.indexOf("sdd")>=0&&true;
	}
	
	static int nineDupe(int n)
	{
		int r=n;
		for(int i=0;i<8;i++){
			r*=10;
			r+=n;
		}
		return r;
  
	}
	
	static  int Chocolates(int B, int w) {
		int r = B;
		int ww=B; 	
	  while(ww>=w)
	  {
		  r+=ww/w;
		  ww=ww/w+ww%w;
		  System.out.println(r);
		
	  }
	  return r;
  
	}



	static boolean Anagram(String a, String b)
	{
		boolean r=true;
		for(int i=0;i<a.length();i++)
			if(b.indexOf(a.charAt(i))==-1)
				r=false;
		return r&(a.length()==b.length());
	}
	static int SumGroups(int[] arr) 
	{
		char [] a = new char[arr.length];
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i]%2==0)
			{
				a[i]='e';
			}
			else
				a[i]='o';
		}
		for(int i=0;i<a.length;i++)
			System.out.println(a[i]);
		
		return 0;
		
	}
		

	static int LuckyNum(int L,  int R){
		  for(int i=L;i<=R;i++){
			int a= i;
			boolean f = true;
			while(a!=0){
             	if(a%10!=4&&a%10!=7)
                {
			     f=false;
                }
              
				a/=10;
			}
			if(f)
                return i;
              
		}
		return -1;
		
	}
	static int water_area(int[] arr) 
	{
		return 1;

	}
	static String Caesar_Cipher(String message, int shift)
	{
		String r="";
		for(int i=0;i<message.length();i++)
		{
			r+="";
			
		}
		return r;
	}
	
	
	
	static String hexa(int h)
	{
		String a="",s = Integer.toHexString(h);
		for(int i=0;i<s.length();i++)
		{
			a+=Character.toUpperCase(s.charAt(i));
		}
		return a;
	}
	
	
	static String HackIt(String str) {
		String r=""; 
		char c,d = 'a';
		for(int i =0;  i<str.length();i++)
		{
			
			c='a';
			d=str.charAt(i);
			if(!Character.isLetter(d))
			{
				r+=d;
				continue;
			}
			if(Character.isUpperCase(d))
				c='A';
			r+=  (char)(25- (d-c)+c )     ;      
		}
		
		
		
		
		return r;
		
	}
	
	
	
	
		
	
	static int Digital_sum(int arg1) {
		if(arg1/10==0)
			return arg1;
		int sum=0;
		while(arg1!=0){ 
			sum+=arg1%10;
			arg1/=10;
		}
		//return sum;
		
		 return Digital_sum(sum);
	
	
	
	
	}
	
	
	static boolean isFibonacci(int N){
		
		if(N==1) return true;
		int a=1, b=1,c;
		for(c=2;c<=N;)
		{
			c=a+b;
			a=b;
			b=c;
			if(c==N)return true;
		}
		return false;
	
		
		
	}
	
	static int[] withdraw(int N) {
		int[] a = {0,0,0};
		if(N >= 100){
			a[0]=N/100;
		}
		if((N-a[0]*100)%20!=0&&(N-a[0]*100)%50!=0)
			a[0]=0;
		a[1]=N/50;
		if((N-a[1]*50)%20!=0)
			a[1]=0;
		a[2]=(N-a[1]*50)/20;
		for(int i=0;i<3;i++)
			System.out.println(a[i]);
		
		return a;
	}
	static int Sum(int N) {
		int r=0;
		int a=1;
		if(N<1)
		  a=-1;
		for(int i=1;i!=N;i+=a)
		{
		  r+=i;
		  System.out.println(i);
		}
		return r+N;
	}
	static int primeSum(int A, int B) {
	  int a=0;
		for(int i=A;i <= B; i++){
			int d=0;
			for(int j=i;j >= 1;j--){
				if(i%j==0)
					d++;
			}
			if(d==2){
				a+=i;
				System.out.println(i);
			}
		  
		}
		return a;
	}
	static int hammingDistance(int a,  int b){
		int r=0;
		int c = a^b;
		while(c!=0)
		{
			
			c&=c-1;
			r++;
		}
		return r;
	}
	
	static double distribution(String text, String character) {
	  double result=0;
	  text = text.replaceAll("\\s+","");
	  for(int i=0;i<text.length();i++){
		if(text.charAt(i)==character.charAt(0))
		  result++;
	  
		}
		return result/text.length();
	}
	static int Fib(int N){
		int a=0,b=1;
		for (;--N > 0; b = a + (a=b%=1e9+7));
		return a;
		
		
			
		
	}
	static String box(int n){
		String result="";
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(i==0||j==0||j==n-1||i==n-1)
					result+="*";
				else
					result+=" ";
			}		
				if(i!=n-1)result+="\n";
		}
			return result;
		
	}
	static String[] mergeArrays(String[] a, String[] b) {
		int i,d,e;
		d=a.length;
		e=b.length;
		String c[] = new String[d+e];
		for(i=0;i<d;i++)
			c[i]=a[i];
		for(i=0;i<e;i++)
			c[i+d]=b[i];
		return c;
	}
	
	static int differentSymbolsNaive(String s) {

  int result = 0;

  for (int i = 0; i < 26; i++) {
    boolean found = false;
    for (int j = 0; j < s.length(); j++) {
      if (s.charAt(j) == 'a' + i) {
        found = true;
        break;
      }
    }
    if (found) {
      result++;
    }
  }
  return result;
}
	static int max_treasure(int N, int[][] coins, int P) {
		int index = 0;
		for(int i=0;i<coins.length;i++){
			
			if((coins[i][0]-coins[i][1])>coins[index][0]-coins[index][1]){
				index=i;
				
			}
		}
		
		
		return index;
	}

	static int missingNumber(int[] A) {
		int s=A.length+1;
		int ss=s*(s+1)/2;
		int i=0;
		do
		{
		  ss-=A[i];
		}while(i++<s-2);
		return ss;
	}
	
	
	static boolean isTicTac(String[][] a){
		
		
		if((a[0][0]+a[1][1]+a[2][2]).equals("OOO")||(a[0][0]+a[1][1]+a[2][2]).equals("XXX"))
			return true;
		if((a[0][0]+a[1][0]+a[2][0]).equals("OOO")||(a[0][0]+a[1][0]+a[2][0]).equals("XXX"))
			return true;
		if((a[0][0]+a[0][1]+a[0][2]).equals("OOO")||(a[0][0]+a[0][1]+a[0][2]).equals("XXX"))
			return true;
		if((a[0][1]+a[1][1]+a[2][1]).equals("OOO")||(a[0][1]+a[1][1]+a[2][1]).equals("XXX"))
			return true;
		if((a[0][2]+a[1][2]+a[2][2]).equals("OOO")||(a[0][2]+a[1][2]+a[2][2]).equals("XXX"))
			return true;
		if((a[0][2]+a[1][1]+a[2][0]).equals("OOO")||(a[0][2]+a[1][1]+a[2][0]).equals("XXX"))
			return true;
		if((a[1][0]+a[1][1]+a[1][2]).equals("OOO")||(a[1][0]+a[1][1]+a[1][2]).equals("XXX"))
			return true;
		if((a[2][0]+a[2][1]+a[2][2]).equals("OOO")||(a[2][0]+a[2][1]+a[2][2]).equals("XXX"))
			return true;
		
		
		for(int i=0; i<3; i++){
			for(int j=0;j<3;j++){
				String s = a[i][j];
				System.out.print(s+" ");
			}
			System.out.println();
		}
		return false;
	}
	
}
	
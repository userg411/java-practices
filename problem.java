import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class problem{
	public static void main(String[] args){
		System.out.println(pr6());
		
		
	}
	static int pr8(){
		String [] s =  
					{"73167176531330624919225119674426574742355349194934",
					"96983520312774506326239578318016984801869478851843",
					"85861560789112949495459501737958331952853208805511",
					"12540698747158523863050715693290963295227443043557",
					"66896648950445244523161731856403098711121722383113",
					"62229893423380308135336276614282806444486645238749",
					"30358907296290491560440772390713810515859307960866",
					"70172427121883998797908792274921901699720888093776",
					"65727333001053367881220235421809751254540594752243",
					"52584907711670556013604839586446706324415722155397",
					"53697817977846174064955149290862569321978468622482",
					"83972241375657056057490261407972968652414535100474",
					"82166370484403199890008895243450658541227588666881",
					"16427171479924442928230863465674813919123162824586",
					"17866458359124566529476545682848912883142607690042",
					"24219022671055626321111109370544217506941658960408",
					"07198403850962455444362981230987879927244284909188",
					"84580156166097919133875499200524063689912560717606",
					"05886116467109405077541002256983155200055935729725",
					"71636269561882670428252483600823257530420752963450"};
					
		int max = 0;
		for(int i = 0; i<s.length();i++){
			 String line = s[i];
			 int lineMax = 0;
			 for(int j=0 ; j<line.length()-13;j++){
				 int prod = line.charAt(i)-'0';
				 for(int k = 1; k<13;k++){
					 prod*=line.charAt(j+k)-'0';
				 }
				 lineMax = Math.max(lineMax, prod);
				 
			 }
			max = Math.max(lineMax,max);
			
		}			
					
					
		return max;
	}
	static int pr6(){
		int s=0;
		for(int i=1;i<100;i++){
			for(int j=i+1;j<=100;j++){
				s+=i*j;
			}
		}
		return 2*s;
	}
	static int pr5(){
		int n = 2520;
		for(int i=n+1; ;i++){
			if(i%11==0&&i%13==0&&i%17==0&&i%19==0&&i%16==0&&i%2520==0)
				return i;
		}
		//return -1;
	}
	static int pr4(){
		int res = 0; 
		class Helper{
			boolean isPalindrome(int n){
				String s = String.valueOf(n);
				for(int i =0; i<s.length()/2;i++){
					if(s.charAt(i)!=s.charAt(s.length() -i -1))
						return false;
				}
				return true;
			}
		}
		Helper h = new Helper();
		for(int i=100;i<=999; i++){
			for(int j=i;j<=999; j++)
			{
				if(h.isPalindrome(i*j))
					res = Math.max(res,i*j);
			}
		}
		return res;
	}
	
	static long pr3(long n){
		class Helper{
			boolean isPrime(long n){
				for(long i=2; i*i<=n;i++){
					if(n%i==0)
						return false;
				}
				return true;
			}
		}
		Helper h = new Helper();
		long ans = 0;
		for(long i = 2; i*i<n;i++){
			if(n%i==0&&h.isPrime(i))
				ans = Math.max(ans,i);
			
			
		}
		return ans;
	}
	static int pr2(){
		int a = 0;
		int b = 1;
		int fib = 0;
		int sum  = 0;
		while(fib<4000000){
			fib = a+b;
			if(fib<4000000&&fib%2==0)
				sum+=fib;
			a=b;
			b = fib;
			
			
		}
		return sum;
		
		
		
	}
	static int pr1(){
		int r = 0;
		for(int i=1; i<1000;i++){
			if(i%3==0|i%5==0)
				r+=i;
		}
		return r;
	}
	static boolean correct_parentheses(String seq){
		
		Stack <Character>s = new Stack<Character>();
		
		for(int i=0;i<seq.length();i++){
			
			if(seq.charAt(i)=='('||seq.charAt(i)=='['){
				s.push(seq.charAt(i));
				System.out.println("Pushed: "+seq.charAt(i));
			}
			else if (seq.charAt(i)==')'){
				if(!s.empty()&&(char)s.peek()=='('){
					s.pop();
					//System.out.println("Popped: "+s.pop());
				}
				else return false;
			}
			else if (seq.charAt(i)==']'){
				if(!s.empty()&&(char)s.peek()=='['){
					//s.pop();
					System.out.println("Popped: "+s.pop());
				}
				else return false;
			}
			
		}
		return s.empty();
		
		
	
	}
	
	static int Majority_Element(int N, int[] array){
		HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();	
		for(int i=0;i<N;i++){
			if(cache.get(array[i])==null)
				cache.put(array[i], 1);
			else
				cache.put(array[i], cache.get(array[i])+1);
		}		
		
		Iterator<Integer> keySetIterator = cache.keySet().iterator(); 						
		while(keySetIterator.hasNext())
		{ 
			Integer key = keySetIterator.next();
 			if(cache.get(key)>N/2)
 				return key;
 		}

		return -1;
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
		int a=1, b=1,c=0;
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
	
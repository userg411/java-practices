import java.util.stream.*;
public class javaEightFeatures{
	public static void main(String[] args){
		IntStream first = IntStream.builder().add(10).build();
		int sum  = IntStream.range(1,5).map(i->i*i).sum();
		System.out.println(sum);
	}
	
}
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;


public class testing {

	public static void main (String[] args) {
//		System.out.println("hola");
		ArrayList<Integer> nums = new ArrayList<>();
		nums.add(0);
		nums.add(1);
		nums.add(2);
		nums.add(3);
		System.out.println(nums);
		
		Collections.reverse(nums);
		System.out.println(nums);
		Collections.reverse(nums);
		System.out.println(nums);
		
		}
}	


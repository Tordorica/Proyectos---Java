import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

import javafx.geometry.Point2D;


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
		
		Point2D point1 = new Point2D(1.0, 2.0);
		Point2D point2 = new Point2D(3.0, 2.0);
		
		System.out.println(point1.equals(point2));
		
		}
}	


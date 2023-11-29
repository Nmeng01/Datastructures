import java.util.Arrays;

// Worked with Owen Zhang and Marcus Huh

public class Main {
	public static String reverseString(String originalString) {
		String[] vowels = {"a", "e", "i", "o", "u"};
		if(originalString.length() == 1) {
			for(String vowel : vowels) {
				if(originalString.substring(0, 1).contains(vowel)) {
					return "";
				}
			}	
			return originalString;
		}
		else {
			for(String vowel : vowels) {
				if(originalString.substring(0, 1).contains(vowel)) {
					return reverseString(originalString.substring(1));
				}
			}	
			return reverseString(originalString.substring(1)) + originalString.substring(0,1);
		}
	}
	public static String intAsString(int i){
		if (i < 0) {
			throw new IllegalArgumentException("Not an integer!");
		}
		else if (i < 10) {
			return String.valueOf(i);
		} else {
			return intAsString(i / 10) + String.valueOf(i % 10);
		}
	}
	public static int findSmallest(int[] data, int firstElement) {
		int smallest = firstElement; //define the smallest number to be the first element on the array
		if (data.length <= 1) {
			if (smallest >= data[0]) {
				smallest = data[0];
			}
			return smallest;
		}
		int[] temp = new int[data.length - 1]; //create a new array with the same values except for the first element
		for (int i = 1; i <= temp.length; i++) {
			temp[i-1] = data[i];
		}
		if (smallest >= temp[0]) {
			smallest = temp[0];
		}
		return findSmallest(temp, smallest);
	}
	public static void main(String[] args) {
		System.out.println(reverseString("abkdoiswekjdies"));
		System.out.println(intAsString(12345));	
		int[] numArray = new int[20];
		for(int i = 0; i < 20; i++) {
			double temp = Math.random() * 100;
			numArray[i] = (int) temp;
		}
		System.out.println(Arrays.toString(numArray));
		System.out.println(findSmallest(numArray, numArray[0]));
	}
}

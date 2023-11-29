import java.util.Random;

// Worked with Owen Zhang

public class Main {
	public static void main(String[] args) {
		int upperLimit = 100;
		Random rand = new Random();
		LinkedStack<Integer> R = new LinkedStack<Integer>();
		LinkedStack<Integer> S = new LinkedStack<Integer>();
		LinkedStack<Integer> T = new LinkedStack<Integer>();
		for(int i = 0; i < 10; i++) {
			int randNum1 = rand.nextInt(upperLimit) + 1;
			int randNum2 = rand.nextInt(upperLimit) + 1;
			int randNum3 = rand.nextInt(upperLimit) + 1;
			R.push(randNum1);
			S.push(randNum2);
			T.push(randNum3);
		}
		System.out.println(R);
		System.out.println(S);
		System.out.println(T);
		int rsize = R.size();
		while(!(S.isEmpty())) {
			int temp = S.pop();
			R.push(temp);
		}
		while(!(T.isEmpty())) {
			int temp = T.pop();
			R.push(temp);
		}
		while(R.size() > rsize) {
			int temp = R.pop();
			S.push(temp);
		}
		System.out.println(R);
		System.out.println(S);
		System.out.println(T);
	}
}

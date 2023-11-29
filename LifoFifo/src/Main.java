import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		LinkedQueue<Integer[]> stockQueue = new LinkedQueue<Integer[]>();
		LinkedStack<Integer[]> stockStack = new LinkedStack<Integer[]>();
		Scanner scn = new Scanner(System.in);
		String stock = " ";
		while(!(stock.equals(""))) {
			System.out.println("Please input the stocks you've bought in the correct format (Simply press enter without typing anything when you are done): ");
			stock = scn.nextLine();
			if(stock.equals("")) {
				continue;
			}
			String[] stockSplit = stock.split(",");
			Integer[] stocks = new Integer[2];
			stocks[0] = Integer.parseInt(stockSplit[1]);
			stocks[1] = Integer.parseInt(stockSplit[2]);
			if(stockSplit[0].equals("B")) {	
				stockQueue.enqueue(stocks);
				stockStack.push(stocks);
			}
			else if(stockSplit[0].equals("S")) {
				int totalStockLeft = stocks[0];
				int totalSale = totalStockLeft * stocks[1];
				int capitalGains = 0;
				while(stockQueue.size() > 0 && totalStockLeft > 0) {
					int firstBought = stockQueue.first()[0];
					int totalFirstBought = firstBought * stockQueue.first()[1];
					if(totalStockLeft > firstBought) {
						totalStockLeft -= firstBought;
						capitalGains += ((firstBought * stocks[1]) - totalFirstBought);
						stockQueue.dequeue();
					}
					else {
						capitalGains += ((totalStockLeft * stocks[1]) - (totalStockLeft * stockQueue.first()[1]));
						totalStockLeft = 0;
						stockQueue.first()[0] = firstBought - totalStockLeft;
					}
				}
				System.out.println(capitalGains);
			}
		}
	}
}

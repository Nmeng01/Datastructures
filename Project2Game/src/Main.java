import java.util.Random;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		DoublyLinkedList<Integer> board = new DoublyLinkedList<Integer>();
		int[] elements = {0, 5, 10, 8, 10, 7, 5, 9, 10, 6, 7, 10, 6, 5, 8, 9, 5, 10, 5, 9, 6, 8, 7, 10, 6, 8, 0};
		for(int element : elements) {
			board.addLast(element);
		}
		String[] players = {"A", "B", "C", "D"};
		ArrayList<Integer> averageMovesWins = new ArrayList<>();
		ArrayList<Double> winPercentages = new ArrayList<>();
		Random rand = new Random();
		for(int i = 1; i < 5; i++) {
			double[] totalsPerPlayer = {0.0, 0.0, 0.0, 0.0};
			double[] totalMovesWinsPerPlayer = {0.0, 0.0, 0.0, 0.0};
			ArrayList<String> playersInGame = new ArrayList<>();
			for(int k = 0; k < i; k++) {
				playersInGame.add(players[k]);
			}
			for(int j = 0; j < 1000; j++) {
				board.setGame(i);
				double moves = 0.0;
				while(!(board.getGameWon())) {
					moves++;
					for(String player : playersInGame) {
						board.movePlayer(rand.nextInt(6) + 1, player);
						if(board.getGameWon()) {
							if(player.equals("A")) {
								totalsPerPlayer[0]++;
								totalMovesWinsPerPlayer[0] += moves;
							}
							else if(player.equals("B")) {
								totalsPerPlayer[1]++;
								totalMovesWinsPerPlayer[1] += moves;
							}
							else if(player.equals("C")) {
								totalsPerPlayer[2]++;
								totalMovesWinsPerPlayer[2] += moves;
							}
							else if(player.equals("D")) {
								totalsPerPlayer[3]++;
								totalMovesWinsPerPlayer[3] += moves;
							}
							break;
						}
					 }
				}
				if(j % 100 == 0) {
					board.printBoard();
				}
				board.clearBoard();
			}
			for(int k = 0; k < i; k++) {
				averageMovesWins.add((int) Math.round(totalMovesWinsPerPlayer[k]/totalsPerPlayer[k]));
				winPercentages.add(totalsPerPlayer[k]/10.0);
			}
		}
		int count = 0;
		System.out.println();
		System.out.println("Here is a summary of the simulation in the format: average # of moves in games won / % of wins");
		System.out.println("               A             B             C             D");
		System.out.println("______________________________________________________________________");
		for(int i = 1; i < 5; i++) {
			System.out.print(i + " Player | ");
			for(int j = 0; j < i; j++) {
				if(i == 1) {
					System.out.print(averageMovesWins.get(count) + " / " + winPercentages.get(count) + "% | ");
				}
				else {
				System.out.print(String.format("%1$14s", averageMovesWins.get(count) + " / " + winPercentages.get(count) + "% | "));
				}
				count++;
			}
			System.out.println();
		}
	}
}

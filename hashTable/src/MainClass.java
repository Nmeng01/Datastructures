
import java.io.File;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class MainClass {
	
	static int RomneyVotes = 0;
	static int ObamaVotes = 0;

	public static void main(String[] args) {
		// File Formats: 
		//  data/ElectoralVotes.csv :  State, Number of electoral votes
		//  data/ElectionData 2012.csv:  State, # of Obama votes, # of Romney votes
		File selectedfile = new File ("data/ElectoralVotes.csv");		
		// Confirm if the file exists -- if not, prompt error
		Scanner fileInput = null;
		try {
			fileInput = new Scanner(selectedfile);
		}
		catch (FileNotFoundException e1) {
			System.out.println("\n[ERROR]: Cannot find the electoral votes file.");
		}		
		// Scan each entry until no more exist  
		while (fileInput.hasNextLine()) {
			// Read the next line in the file
			String buffline = fileInput.nextLine();
			// Split the line into an array
			String[] data_line = buffline.split(",");
			// Store the information  in your HashMap
		}
		
		File file = new File("data/ElectionData 2012.csv");
		Scanner scn = null;
		try {
			scn = new Scanner(file);
		}
		catch (FileNotFoundException e2) {
			System.out.println(e2);
		}
		int[] obamaVotes = new int[50];
		int[] romneyVotes = new int[50];
		int count = 0;
		while(scn.hasNextLine()) {
			count++;
			String line = fileInput.nextLine();
			String[] lineArray = line.split(",");
			System.out.println(Arrays.toString(lineArray));
			obamaVotes[count] = Integer.parseInt(lineArray[1]);
			romneyVotes[count] = Integer.parseInt(lineArray[2]);
		}
		// System.out.println(Arrays.toString(obamaVotes));
		// System.out.println(Arrays.toString(romneyVotes));
		
		// Use above logic to read each state's actual vote total
		// For each state, find out who won
		// Look up in HashMap number of electoral votes for the state
		// Award electoral votes for that state to the winning candidate
		
		
		
		
		System.out.println("Romney Votes: " + RomneyVotes);
		System.out.println("Obama Votes: " + ObamaVotes);
		

	}

}

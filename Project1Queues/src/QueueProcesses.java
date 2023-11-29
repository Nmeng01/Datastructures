import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class QueueProcesses {
	public static void main(String[] args) {
		String fileName = "Project1.txt";
		String fullText = "";
		try {
		  Scanner scn = new Scanner(new File(fileName));
		  while (scn.hasNextLine()) {
		    String line = scn.nextLine();
		    fullText += line + "\n";
		  }
		  scn.close();
		}
		catch (FileNotFoundException e) {
		  System.out.println("File not found");
		}
		String[] splitText = fullText.split("\n");
		ArrayList<String> resources = new ArrayList<>();
		repopulate(resources);
		for(String processes : splitText) {
			LinkedQueue<String> processQueue = new LinkedQueue<String>();
			String[] processList = processes.split(";");
			int count = 0;
			for(String process : processList) {
				processQueue.enqueue(process);
			}
			System.out.println(processQueue);
			while(processQueue.size() > 0) {
				if(processQueue.first().contains("A")) {
					if(resources.contains("A")) {
						resources.remove("A");
					}
					else {
						count++;
						repopulate(resources);
						continue;
					}
				}
				if(processQueue.first().contains("B")) {
					if(resources.contains("B")) {
						resources.remove("B");
					}
					else {
						count++;
						repopulate(resources);
						continue;
					}
				}
				if(processQueue.first().contains("C")) {
					if(resources.contains("C")) {
						resources.remove("C");
					}
					else {
						count++;
						repopulate(resources);
						continue;
					}
				}
				processQueue.dequeue();
			}
			count++;
			repopulate(resources);
			System.out.println("Total number of cycles needed: " + count);
		}
		
	}
	
	public static ArrayList<String> repopulate(ArrayList<String> resources) {
		  if(!(resources.contains("A"))) {
		  	resources.add("A");
		  }
		  if(!(resources.contains("B"))) {
			resources.add("B");
		  }
		  if(!(resources.contains("C"))) {
			resources.add("C");
		  }
		  return resources;
	 }
}

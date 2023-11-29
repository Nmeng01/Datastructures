import java.util.ArrayList;
import java.util.Random;

public class QueueRandomProcesses {
	public static void main(String[] args) {
		LinkedQueue<String> processQueue = new LinkedQueue<String>();
		int count = 0;
		int processNumber = 1;
		ArrayList<String> resources = new ArrayList<>();
		resources = repopulate(resources);
		processQueue = addNewProcesses(processQueue, resources, 20, processNumber);
		processNumber += 20;
		while(processQueue.size() > 0 && count < 1000) {
			if(processQueue.first().contains("A")) {
				if(resources.contains("A")) {
					resources.remove("A");
				}
				else {
					count++;
					resources = repopulate(resources);
					processQueue = addNewProcesses(processQueue, resources, 2, processNumber);
					if(count % 100 == 0) {
						System.out.println("Length of processes at cycle " + count + ": " + processQueue.size());
					}
					processNumber += 2;
					continue;
				}
			}
			if(processQueue.first().contains("B")) {
				if(resources.contains("B")) {
					resources.remove("B");
				}
				else {
					count++;
					resources = repopulate(resources);
					processQueue = addNewProcesses(processQueue, resources, 2, processNumber);
					if(count % 100 == 0) {
						System.out.println("Length of processes at cycle " + count + ": " + processQueue.size());
					}
					processNumber += 2;
					continue;
				}
			}
			if(processQueue.first().contains("C")) {
				if(resources.contains("C")) {
					resources.remove("C");
				}
				else {
					count++;
					resources = repopulate(resources);
					processQueue = addNewProcesses(processQueue, resources, 2, processNumber);
					if(count % 100 == 0) {
						System.out.println("Length of processes at cycle " + count + ": " + processQueue.size());
					}
					processNumber += 2;
					continue;
				}
			}
			processQueue.dequeue();
		}
		if(processQueue.size() == 0) {
			count++;
			System.out.println("Total number of cycles needed: " + count);
		}
		else {
			System.out.println("Total number of processes left: " + processQueue.size());
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
	
	public static LinkedQueue<String> addNewProcesses(LinkedQueue<String> processQueue, ArrayList<String> resources, int numNewProcesses, int processNumber) {
		String processes = "";
		Random randObj = new Random();
		for(int i = 0; i < numNewProcesses; i++) {
			int upperLimit = 3;
			int randNumResources = randObj.nextInt(upperLimit) + 1;
			processes += "P" + processNumber + "(";
			for(int j = 0; j < randNumResources; j++) {
				int randResourceIndex = randObj.nextInt(upperLimit);
				String randResource = resources.get(randResourceIndex);
				resources.remove(randResourceIndex);
				if(j != randNumResources - 1) {
					processes += randResource + ",";
				}
				else {
					processes += randResource;
				}
				upperLimit--;
			}
			processes += ")";
			if(i != numNewProcesses - 1) {
				processes += "; ";
			}
			processNumber++;
			repopulate(resources);
		}
		String[] processList = processes.split(";");
		for(String process : processList) {
			processQueue.enqueue(process);
		}
		return processQueue;
	}
}

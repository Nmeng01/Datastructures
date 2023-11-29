
public class Main {

	public static void main(String[] args) {
		System.out.println("Example of a structure using the provided one in the ppt as well as some additional nodes: ");
		QuadruplyLinkedList<String> lewisStructure = new QuadruplyLinkedList<>();
		lewisStructure.Root("N");
		lewisStructure.Add("C", "East");
		lewisStructure.Add("A", "South");
		lewisStructure.Move("North");
		lewisStructure.Move("West");
		lewisStructure.Add("K", "South");
		lewisStructure.Add("M", "West");
//		lewisStructure.Add("O", "North");
//		lewisStructure.Add("T", "South");
//		lewisStructure.Move("South");
//		lewisStructure.Add("P", "East");
//		lewisStructure.Add("R", "West");
		lewisStructure.Print();
	}

}

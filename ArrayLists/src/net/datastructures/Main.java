package net.datastructures;;

public class Main {
	public static void main(String[] args) {
		MyListClass<String> newList = new MyListClass<String>();
		newList.add(0, "A");
		newList.add(1, "B");
		newList.add(2, "C");
		newList.add(3, "D");
		
		newList.switchTopBottom(newList);
		System.out.println(newList);
		newList.swap(3, 1, newList);
		System.out.println(newList);
	}
}

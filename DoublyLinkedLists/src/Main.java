
public class Main {
	public static void main(String[] args) {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.addFirst("D");
		list.addFirst("C");
		list.addFirst("B");
		list.addFirst("A");
		list.addLast("E");
		list.swap(1, 3);
		System.out.println(list);
	}
}

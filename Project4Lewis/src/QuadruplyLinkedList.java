
import net.datastructures.*;

public class QuadruplyLinkedList<E> {

	private static class Position<E> {
		  
		  private E position;
		  
		  public Position(E element) {
			  position = element;
		  }
		  
		  public E getElement() {return position;}
		  
		  public void setElement(E n) {position = n;}
	  }
	
  //---------------- nested Node class ----------------
  /**
   * Node of a doubly linked list, which stores a reference to its
   * element and to both the previous and next node in the list.
   */
  private static class Node<E> {

    /** The element stored at this node */
    private String element;               // reference to the element stored at this node

    private Node<E> north;

    private Node<E> south;
    
    private Node<E> east;
    
    private Node<E> west;
    
    private Position<Node<E>> pos;
    
    /**
     * Creates a node with the given element and next node.
     *
     * @param e  the element to be stored
     * @param p  reference to a node that should precede the new node
     * @param n  reference to a node that should follow the new node
     */
    public Node(String elmnt, Node<E> n, Node<E> s,  Node<E> e,  Node<E> w) {
      element = elmnt;
      north = n;
      south = s;
      east = e;
      west = w;
      pos = new Position<Node<E>>(this);
    }

    // public accessor methods
    /**
     * Returns the element stored at the node.
     * @return the element stored at the node
     */
    public String getElement() { return element; }
    
    public Position<Node<E>> getNodePosition() {return pos;}

    /**
     * Returns the node that precedes this one (or null if no such node).
     * @return the preceding node
     */
    public Node<E> getNorth() { return north; }

    public Node<E> getSouth() { return south; }
    
    public Node<E> getEast() { return east; }
    
    public Node<E> getWest() { return west; }

    // Update methods
    public void setNorth(Node<E> n) { north = n; }

    public void setSouth(Node<E> s) { south = s; }
    
    public void setEast(Node<E> e) { east = e; }
    
    public void setWest(Node<E> w) { west = w; }
    
    public void setElement(String e) {element = e;}
    
  } //----------- end of nested Node class -----------
  

  // instance variables of the QuadruplyLinkedList
  private Node<E> northEnd;         

  private Node<E> southEnd;  
  
  private Node<E> eastEnd; 
  
  private Node<E> westEnd;
  
  private Node<E> root;
  
  private Position<Node<E>> currentPos;
  
  private Position<Node<E>> prevPos = new Position<Node<E>>(new Node<E>(null, null, null, null, null));

  /** Number of elements in the list (not including sentinels) */
  private int size = 0;                      // number of elements in the list

  /** Constructs a new empty list. */
  public QuadruplyLinkedList() {
    westEnd = new Node<>(null, null, null, null, null);
    eastEnd = new Node<>(null, null, null, null, westEnd);
    westEnd.setEast(eastEnd);
    northEnd = new Node<>(null, null, null, null, null);
    southEnd = new Node<>(null, northEnd, null, null, null);
    northEnd.setSouth(southEnd);
    root = new Node<E>(null, null, null, null, null);
    currentPos = new Position<Node<E>>(root);
  }

  // public accessor methods
  /**
   * Returns the number of elements in the linked list.
   * @return number of elements in the linked list
   */
  public int size() { return size; }

  /**
   * Tests whether the linked list is empty.
   * @return true if the linked list is empty, false otherwise
   */
  public boolean isEmpty() { return size == 0; }
  
  public String getCurrentNode() {return currentPos.getElement().getElement();}

  public void Root(String value) {
	  root.setElement(value);
	  root.setEast(eastEnd);
	  eastEnd.setWest(root);
	  root.setWest(westEnd);
	  westEnd.setEast(root);
	  root.setNorth(northEnd);
	  northEnd.setSouth(root);
	  root.setSouth(southEnd);
	  southEnd.setNorth(root);
	  currentPos.setElement(root);
  }
  
  public void Add(String value, String direction) {
	  if(direction.equals("North")) {
		  Node<E> newNode = new Node<E>(value, currentPos.getElement().getNorth(), currentPos.getElement(), new Node<E>(null, null, null, null, null), new Node<E>(null, null, null, null, null));
		  newNode.getEast().setWest(newNode);
		  newNode.getWest().setEast(newNode);
		  currentPos.getElement().getNorth().setSouth(newNode);
		  currentPos.getElement().setNorth(newNode);
		  currentPos.setElement(newNode);
	  }
	  else if(direction.equals("South")) {
		  Node<E> newNode = new Node<E>(value, currentPos.getElement(), currentPos.getElement().getSouth(), new Node<E>(null, null, null, null, null), new Node<E>(null, null, null, null, null));
		  newNode.getEast().setWest(newNode);
		  newNode.getWest().setEast(newNode);
		  currentPos.getElement().getSouth().setNorth(newNode);
		  currentPos.getElement().setSouth(newNode);
		  currentPos.setElement(newNode);
	  }
	  else if(direction.equals("East")) {
		  Node<E> newNode = new Node<E>(value, new Node<E>(null, null, null, null, null), new Node<E>(null, null, null, null, null), currentPos.getElement().getEast(), currentPos.getElement());
		  newNode.getNorth().setSouth(newNode);
		  newNode.getSouth().setNorth(newNode);
		  currentPos.getElement().getEast().setWest(newNode);
		  currentPos.getElement().setEast(newNode);
		  currentPos.setElement(newNode);
	  }
	  else if(direction.equals("West")) {
		  Node<E> newNode = new Node<E>(value, new Node<E>(null, null, null, null, null), new Node<E>(null, null, null, null, null), currentPos.getElement(), currentPos.getElement().getWest());
		  newNode.getNorth().setSouth(newNode);
		  newNode.getSouth().setNorth(newNode);
		  currentPos.getElement().getWest().setEast(newNode);
		  currentPos.getElement().setWest(newNode);
		  currentPos.setElement(newNode);
	  }
  }
  
  public void Move(String direction) {
	  if(direction.equals("North")) {
		  if(currentPos.getElement().getNorth().getElement().equals(null)) {
			  System.out.println("There is no node north of " + currentPos.getElement().getElement());
		  }
		  else {
		  	currentPos.setElement(currentPos.getElement().getNorth());
		  }
	  }
	  else if(direction.equals("South")) {
		  if(currentPos.getElement().getSouth().getElement().equals(null)) {
			  System.out.println("There is no node south of " + currentPos.getElement().getElement());
		  }
		  else {
		  	currentPos.setElement(currentPos.getElement().getSouth());
		  }
	  }
	  else if(direction.equals("East")) {
		  if(currentPos.getElement().getEast().getElement().equals(null)) {
			  System.out.println("There is no node east of " + currentPos.getElement().getElement());
		  }
		  else {
		  	currentPos.setElement(currentPos.getElement().getEast());
		  }
	  }
	  else if(direction.equals("West")) {
		  if(currentPos.getElement().getWest().getElement().equals(null)) {
			  System.out.println("There is no node west of " + currentPos.getElement().getElement());
		  }
		  else {
		  	currentPos.setElement(currentPos.getElement().getWest());
		  }
	  }
  }
  
  public void Print() {
	  Position<Node<E>> tempCurrentPos = currentPos;
	  Position<Node<E>> tempPrevPos = prevPos;
	  System.out.print(currentPos.getElement().getElement() + " ");
	  if(currentPos.getElement().getNorth().getElement() != null && currentPos.getElement().getNorth() != prevPos.getElement()) {
		  prevPos = currentPos;
		  currentPos = currentPos.getElement().getNorth().getNodePosition();
		  Print();
		  currentPos = tempCurrentPos;
		  prevPos = tempPrevPos;
	  }
	  if(currentPos.getElement().getSouth().getElement() != null && currentPos.getElement().getSouth() != prevPos.getElement()) {
		  prevPos = currentPos;
		  currentPos = currentPos.getElement().getSouth().getNodePosition();
		  Print();
		  currentPos = tempCurrentPos;
		  prevPos = tempPrevPos;
	  }
	  if(currentPos.getElement().getEast().getElement() != null && currentPos.getElement().getEast() != prevPos.getElement()) {
		  prevPos = currentPos;
		  currentPos = currentPos.getElement().getEast().getNodePosition();
		  Print();
		  currentPos = tempCurrentPos;
		  prevPos = tempPrevPos;
	  }
	  if(currentPos.getElement().getWest().getElement() != null && currentPos.getElement().getWest() != prevPos.getElement()) {
		  prevPos = currentPos;
		  currentPos = currentPos.getElement().getWest().getNodePosition();
		  Print();
		  currentPos = tempCurrentPos;
		  prevPos = tempPrevPos;
	  }
  }
  
} //----------- end of DoublyLinkedList class -----------


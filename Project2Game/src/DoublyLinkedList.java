import java.util.Arrays;

public class DoublyLinkedList<E> {

  private static class Player {
	  private String player;
	  private int score;
	  
	  public Player(String p) {
		  player = p;
		  score = 0;
	  }
	  
	  public String getPlayer() { return player; }
	  
	  public int getScore() { return score; }
	  
	  public void addToScore(int points) {
		  score += points;
	  }
	  
	  public void clearScore() {score = 0;}
	  
	  public String toString() {
		  return player;
	  }
  }
  
  //---------------- nested Node class ----------------
  /**
   * Node of a doubly linked list, which stores a reference to its
   * element and to both the previous and next node in the list.
   */
  private static class Node<E> {

    /** The element stored at this node */
    private E element;               // reference to the element stored at this node

    /** A reference to the preceding node in the list */
    private Node<E> prev;            // reference to the previous node in the list

    /** A reference to the subsequent node in the list */
    private Node<E> next;            // reference to the subsequent node in the list
    
    private String players;

    /**
     * Creates a node with the given element and next node.
     *
     * @param e  the element to be stored
     * @param p  reference to a node that should precede the new node
     * @param n  reference to a node that should follow the new node
     */
    public Node(E e, Node<E> p, Node<E> n) {
      element = e;
      prev = p;
      next = n;
      players = "";
    }

    // public accessor methods
    /**
     * Returns the element stored at the node.
     * @return the element stored at the node
     */
    public E getElement() { return element; }

    /**
     * Returns the node that precedes this one (or null if no such node).
     * @return the preceding node
     */
    public Node<E> getPrev() { return prev; }

    /**
     * Returns the node that follows this one (or null if no such node).
     * @return the following node
     */
    public Node<E> getNext() { return next; }

    // Update methods
    /**
     * Sets the node's previous reference to point to Node n.
     * @param p    the node that should precede this one
     */
    public void setPrev(Node<E> p) { prev = p; }

    /**
     * Sets the node's next reference to point to Node n.
     * @param n    the node that should follow this one
     */
    public void setNext(Node<E> n) { next = n; }
    
    public void addPlayer(String p) { 
    	if(players.equals("")) {
    		players = p;
    	}
    	else {
    		players += ", " + p;
    	}
    }
    
    public void removePlayer(String p) {
    	if(players.length() == 1) {
    		players = "";
    	}
    	else {
    		int playerIndex = players.indexOf(p);
    		if(playerIndex == 0) {
    			players = players.substring(3);
    		}
    		else {
    			players = players.substring(0, playerIndex - 2) + players.substring(playerIndex + 1);
    		}
    	}
    }
    
    public void removeAllPlayers() {
    	players = "";
    }
    
    public String getPlayers() { return players; }
  } //----------- end of nested Node class -----------

  // instance variables of the DoublyLinkedList
  /** Sentinel node at the beginning of the list */
  private Node<E> header;                    // header sentinel

  /** Sentinel node at the end of the list */
  private Node<E> trailer;                   // trailer sentinel

  /** Number of elements in the list (not including sentinels) */
  private int size = 0; // number of elements in the list
  
  private Player[] playersInGame;
  
  private boolean gameWon = false;
  
  /** Constructs a new empty list. */
  public DoublyLinkedList() {
    header = new Node<>(null, null, null);      // create header
    trailer = new Node<>(null, header, null);   // trailer is preceded by header
    header.setNext(trailer);                    // header is followed by trailer
  }
  
  public void setGame(int numPlayers) {
	  try {
		  Player[] players = {new Player("A"), new Player("B"), new Player("C"), new Player("D")};
		  gameWon = false;
		  playersInGame = new Player[numPlayers];
		  for(int i = 0; i < numPlayers; i++) {
			  header.getNext().addPlayer(players[i].getPlayer());
			  playersInGame[i] = players[i];
			  playersInGame[i].clearScore();
		  }
	  } 
	  catch(ArrayIndexOutOfBoundsException e) {
		  System.out.println("You can only play with 1-4 players!");
	  }
  }
  
  public void clearBoard() {
	  Node<E> walk = header.getNext();
	  for(int i = 0; i < this.size(); i++) {
		  walk.removeAllPlayers();
		  walk = walk.getNext();
	  }
  }

  // public accessor methods
  public boolean getGameWon() { return gameWon; }
  
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

  /**
   * Returns (but does not remove) the first element of the list.
   * @return element at the front of the list (or null if empty)
   */
  public E first() {
    if (isEmpty()) return null;
    return header.getNext().getElement();   // first element is beyond header
  }

  /**
   * Returns (but does not remove) the last element of the list.
   * @return element at the end of the list (or null if empty)
   */
  public E last() {
    if (isEmpty()) return null;
    return trailer.getPrev().getElement();    // last element is before trailer
  }

  // public update methods
  /**
   * Adds an element to the front of the list.
   * @param e   the new element to add
   */
  public void addFirst(E e) {
    addBetween(e, header, header.getNext());    // place just after the header
  }

  /**
   * Adds an element to the end of the list.
   * @param e   the new element to add
   */
  public void addLast(E e) {
    addBetween(e, trailer.getPrev(), trailer);  // place just before the trailer
  }

  /**
   * Removes and returns the first element of the list.
   * @return the removed element (or null if empty)
   */
  public E removeFirst() {
    if (isEmpty()) return null;                  // nothing to remove
    return remove(header.getNext());             // first element is beyond header
  }

  /**
   * Removes and returns the last element of the list.
   * @return the removed element (or null if empty)
   */
  public E removeLast() {
    if (isEmpty()) return null;                  // nothing to remove
    return remove(trailer.getPrev());            // last element is before trailer
  }

  // private update methods
  /**
   * Adds an element to the linked list in between the given nodes.
   * The given predecessor and successor should be neighboring each
   * other prior to the call.
   *
   * @param predecessor   node just before the location where the new element is inserted
   * @param successor     node just after the location where the new element is inserted
   */
  private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
    // create and link a new node
    Node<E> newest = new Node<>(e, predecessor, successor);
    predecessor.setNext(newest);
    successor.setPrev(newest);
    size++;
  }

  /**
   * Removes the given node from the list and returns its element.
   * @param node    the node to be removed (must not be a sentinel)
   */
  private E remove(Node<E> node) {
    Node<E> predecessor = node.getPrev();
    Node<E> successor = node.getNext();
    predecessor.setNext(successor);
    successor.setPrev(predecessor);
    size--;
    return node.getElement();
  }

  /**
   * Produces a string representation of the contents of the list.
   * This exists for debugging purposes only.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder("(");
    Node<E> walk = header.getNext();
    while (walk != trailer) {
      sb.append(walk.getElement());
      walk = walk.getNext();
      if (walk != trailer)
        sb.append(", ");
    }
    sb.append(")");
    return sb.toString();
  }
  
  public void movePlayer(int spaces, String player) {
	  Player playerObj = new Player("");
	  for(Player p : playersInGame) {
		  if(p.getPlayer().equals(player)) {
			  playerObj = p;
			  break;
		  }
	  }
	  Node<E> walk = header.getNext();
	  int count = 0;
	  while(walk != trailer) {
		  if(walk.getPlayers().contains(player)) {
			  break;
		  }
		  walk = walk.getNext();
	  }
	  walk.removePlayer(player);
	  while(walk != trailer.getPrev() && count < spaces) {
		  walk = walk.getNext();
		  count++;
	  }
	  walk.addPlayer(player);
	  playerObj.addToScore((int) walk.getElement());
	  if(walk == trailer.getPrev()) {
		  if(playerObj.getScore() >= 44) {
			  gameWon = true;
		  }
		  else {
			  walk.removePlayer(player);
			  header.getNext().addPlayer(player);
		  }
	  }
	  if(walk.getPlayers().length() > 1) {
		  String removedPlayer = walk.getPlayers().substring(0,1);
		  count = 0;
		  walk.removePlayer(removedPlayer);
		  while(walk != header.getNext() && count != 7) {
			  walk = walk.getPrev();
			  count++;
		  }
		  walk.addPlayer(removedPlayer);
	  }
  }
  
  public void printBoard() {
	  System.out.println("---------------------------------------------------------------------------------------\n");
	  System.out.print("|");
	  Node<E> walk = header.getNext();
	  int counter = 0;
	  while(walk != trailer) {
		  if(walk == header.getNext()) {
			  System.out.print(" Start" + " (" + walk.getPlayers() + ") |");
		  }
		  else if(walk == trailer.getPrev()) {
			  System.out.println(" End" + " (" + walk.getPlayers() + ") |");
		  }
		  else if((int) walk.getElement()/10 == 0) {
		  	System.out.print(" " + walk.getElement() + " (" + walk.getPlayers() + ") |");
		  }
		  else {
			System.out.print(walk.getElement() + " (" + walk.getPlayers() + ") |");
		  }
		  walk = walk.getNext();
		  counter++;
		  if(counter == 9 || counter == 16) {
			  System.out.println("\n");
			  System.out.print("|");
		  }
	  }
	  System.out.println("\n---------------------------------------------------------------------------------------\n");
  }
}

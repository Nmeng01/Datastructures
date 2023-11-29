
import net.datastructures.*;
import java.util.Comparator;
import java.util.Arrays;


public class MyAVLTreeMap<K,V> extends TreeMap<K,V> {
	
  /** Constructs an empty map using the natural ordering of keys. */
  public MyAVLTreeMap() { super(); }

  /**
   * Constructs an empty map using the given comparator to order keys.
   * @param comp comparator defining the order of keys in the map
   */
  public MyAVLTreeMap(Comparator<K> comp) { super(comp); }

  /** Returns the height of the given tree position. */
  protected int height(Position<Entry<K,V>> p) {
    return tree.getAux(p);
  }

  /** Recomputes the height of the given position based on its children's heights. */
  protected void recomputeHeight(Position<Entry<K,V>> p) {
    tree.setAux(p, 1 + Math.max(height(left(p)), height(right(p))));
  }

  /** Returns whether a position has balance factor between -1 and 1 inclusive. */
  protected boolean isBalanced(Position<Entry<K,V>> p) {
    return Math.abs(height(left(p)) - height(right(p))) <= 1;
  }

  /** Returns a child of p with height no smaller than that of the other child. */
  protected Position<Entry<K,V>> tallerChild(Position<Entry<K,V>> p) {
    if (height(left(p)) > height(right(p))) return left(p);     // clear winner
    if (height(left(p)) < height(right(p))) return right(p);    // clear winner
    // equal height children; break tie while matching parent's orientation
    if (isRoot(p)) return left(p);                 // choice is irrelevant
    if (p == left(parent(p))) return left(p);      // return aligned child
    else return right(p);
  }

  /**
   * Utility used to rebalance after an insert or removal operation. This traverses the
   * path upward from p, performing a trinode restructuring when imbalance is found,
   * continuing until balance is restored.
   */
  protected void rebalance(Position<Entry<K,V>> p) {
    int oldHeight, newHeight;
    do {
      oldHeight = height(p);                       // not yet recalculated if internal
      if (!isBalanced(p)) {                        // imbalance detected
        // perform trinode restructuring, setting p to resulting root,
        // and recompute new local heights after the restructuring
        p = restructure(tallerChild(tallerChild(p)));
        recomputeHeight(left(p));
        recomputeHeight(right(p));
      }
      recomputeHeight(p);
      newHeight = height(p);
      p = parent(p);
    } while (oldHeight != newHeight && p != null);
  }

  /** Overrides the TreeMap rebalancing hook that is called after an insertion. */
  @Override
  protected void rebalanceInsert(Position<Entry<K,V>> p) {
    rebalance(p);
  }

  /** Overrides the TreeMap rebalancing hook that is called after a deletion. */
  @Override
  protected void rebalanceDelete(Position<Entry<K,V>> p) {
    if (!isRoot(p))
      rebalance(parent(p));
  }

  /** Ensure that current tree structure is valid AVL (for debug use only). */
  private boolean sanityCheck() {
    for (Position<Entry<K,V>> p : tree.positions()) {
      if (isInternal(p)) {
        if (p.getElement() == null)
          System.out.println("VIOLATION: Internal node has null entry");
        else if (height(p) != 1 + Math.max(height(left(p)), height(right(p)))) {
          System.out.println("VIOLATION: AVL unbalanced node with key " + p.getElement().getKey());
          dump();
          return false;
        }
      }
    }
    return true;
  }
  
  public void printTree() {
	  String[][] keys = new String[6][10];
	  int spaces = this.height(this.root()) * 20;
	  int midway = (int) (spaces * 0.75);
	  String rootFormating = " ".repeat(midway) + this.root().getElement().getKey() + " ".repeat(midway);
	  keys = printTree1(0, this.root(), keys);
	  System.out.println(rootFormating + "\n");
	    for (int i = 1; i < keys.length; i++) {
	        midway = (int) (midway * 0.75);
	        int midwayLetters = (int) (midway * 0.75);
	    	boolean allNulls = true;
	        for (String pos : keys[i]) {
	            if (pos != null && !pos.equals("")) {
	                allNulls = false;
	                break;
	            }
	        }

	        if (allNulls) {
	            break;
	        }
	        if(i == 1) {
	        	System.out.print((" ".repeat(midway) + "|" + " ".repeat((int) Math.round(midway * 0.75)) + "|") + "\n");
	        }
	        else {
	        	System.out.print(" ".repeat(midway));
	        	for(int j = 0; j < (int) Math.pow(2, i-1); j++) {
	        		System.out.print("|" + " ".repeat((int) Math.round(midway*0.75)) + "|");
	        		if(j % 2 == 0) {
	        				System.out.print(" ".repeat((int) Math.round(midway * 1.2)));
	        		}
	        		else {
	        			System.out.print(" ".repeat((int) Math.round(midway)));
	        		}
	        	}
	        }
	        System.out.println("\n");
	        for (int j = 0; j < keys[i].length; j++) {
	        	if (keys[i][j] == null) {
	                break;
	            }
	        	if(j == 0) {
	        		System.out.print(" ".repeat((int) Math.round(midwayLetters)));
	        		midwayLetters = (int) Math.round(midwayLetters * 0.75); 
	        	}
	            if (j % 2 == 0) {
	                System.out.print(keys[i][j] + " ".repeat((int) Math.round(midway * 1.25)));
	            } else {
	                System.out.print(keys[i][j] + " ".repeat(((int) Math.round(midwayLetters * 1.75))));
	            }
	            midway = (int) Math.round(midwayLetters * 1.33);
	        }

	        System.out.println("\n");
	    }
	  
  }
  
  public String[][] printTree1(int level, Position<Entry<K,V>> root, String[][] keys) {
	  int column = Arrays.asList(keys[level]).indexOf(null);
	  keys[level][column] = (String) root.getElement().getKey();
	  if(this.left(root).getElement() != null) {
		  level++;
		  printTree1(level, this.left(root), keys);
		  level--;
	  }
	  else {
		  int tempLevel = level + 1;
		  int temp = Arrays.asList(keys[tempLevel]).indexOf(null);
		  keys[tempLevel][temp] = "";
	  }
	  if(this.right(root).getElement() != null) {
		  level++;
		  printTree1(level, this.right(root), keys);
		  level--;
	  }
	  else {
		  int tempLevel = level + 1;
		  int temp = Arrays.asList(keys[tempLevel]).indexOf(null);
		  keys[tempLevel][temp] = "";
	  }
	  return keys;
  }
}

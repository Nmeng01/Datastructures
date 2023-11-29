public class TreeNode  {
    private int value = 0;
    public TreeNode leftChild, rightChild, parent;
    TreeNode(int value, TreeNode inleft, TreeNode inright, TreeNode inparent) {
        this.value = value;
        this.leftChild=inleft;
        this.rightChild =inright;
        this.parent=inparent;
    }
    public TreeNode setRoot(int  element) {
    	TreeNode  root;
    	root = new TreeNode(element, null, null,null) ;
    	return root;
    }
    public void AddElement(int element){
        TreeNode  node = new TreeNode(element,null,null,null);
        
        if( element>(int)value){
            if(rightChild==null)
            {
                rightChild=node;
                return;
            }
            rightChild.AddElement(element);
        }
        else{
            if(leftChild==null)
            {
                leftChild=node;
                return;
            }
            leftChild.AddElement(element);
        }
    }

    

    public int GetHeight(){
    	int leftHeight=0;
    	int rightHeight = 0;
        if(rightChild==null && leftChild==null)
            return 0;
        if (leftChild != null) {leftHeight = leftChild.GetHeight();}
        if (rightChild != null) {rightHeight = rightChild.GetHeight();}
        if (leftHeight > rightHeight) {
        	 return 1  + leftHeight;
        } else {
        return 1  + rightHeight;
        }
    }

   
}


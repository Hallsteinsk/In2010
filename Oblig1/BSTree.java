public class BSTree implements BSTOper
{

  //Constructors
  public BSTree()
  {
    root = null;
    size = 0;
  }

  //Public methods
  public void add(int value)
  {
    add(root, value);
  }

  public boolean remove(int value)
  {
      if (value == root.value)
      {
        root = null;
        return true;
      }
      else
      {
        return root.remove(value)
      }

      /*
      if (!existsInTree(value))
      {
        return false;
      }
      else
      {
        Node remove = fid(value);
        return remove(remove);
      }*/

  }

  public int size()
  {
    return size;
  }

  public boolean existsInTree(int value)
  {
    return existsInTree(root, value);
  }

  public int findNearestSmallerThan(int value)
  {
    return null;
  }
  public void addAll (int[] integers){return null;}
  public int[] sortedArray(){return 0;} //inorder
  public int[] findInRange(int low, int high){return 0;}

  //Private methods
  private Node findParent(Node n)
  {
    if (n == root)
    {
      return false;
    }
    else
    {
      root.findParent(n);
    }
  }



  private Node findGrandParent(Node n)
  {
    Node parent = findParent(n);
    return findParent(parent);
  }

  private Node find(int value)
  {
    return root.find(value);
  }

  private void add(Node nodeRef, int value)
  {
    if (nodeRef == null)
    {
      nodeRef = new Node(value);
    }
    else
    {
        if (value < nodeRef.value)
        {
          add(nodeRef.left, value);
        }
        else
        {
          add(nodeRef.right, value);
        }
    }
  }

  private boolean remove(Node remove)
  {
    removeParent = findParent(remove);
    if (remove.value > removeParent.value)
    {
      Node smallest = findSmallestInSubtree(nodeRef);
      if (remove == smallest)
      {
          removeParent.right = null;
          return true;
      }
      else
      {
        Node smallestParent = findParent(smallest);
        if (smallestParent == remove)
        smallestParent.left
      }
    }
  }

  private Node findSmallestInSubtree(Node nodeRef)
  {
    if (nodeRef.left != null)
    {
      return findSmallestInSubtree(nodeRef.left);
    }
    else if (nodeRef.right != null)
    {
      return findSmallestInSubtree(nodeRef.right);
    }
    else
    {
      return nodeRef;
    }
  }

  private boolean existsInTree(Node nodeRef, int value)
  {
    if (nodeRef == null)
    {
      return false;
    }
    else
    {
      if (value < nodeRef.value)
      {
        return existsInTree(nodeRef.left, value);
      }
      else if (value == nodeRef.value)
      {
        return true;
      }
      else if (value > nodeRef.value)
      {
        return existsInTree(nodeRef.right, value);
      }
    }
  }

  //Private members
  private Node root;
  private int size;

  //Internal Node class
  private class Node
  {
    //Constructors
    Node()
    {
      value = 0;
      left = null;
      right = null;
    }

    Node(int v)
    {
      value = v;
      left = null;
      right = null;
    }

    boolean remove(int value)
    {
      //If the node is a leaf, the value is not in the tree
      if (this.isLeaf())
      {
        return false;
      }
      //Remove in right subtree
      if (this.value < value)
      {
        if (right.value != value)
        {
          //The right child is not the node to be removed. Recursive call
          return right.remove(value);
        }
        else
        {
          //Found node to be removed, and this is its parent
          Node remove = right;
          if (remove.isLeaf())
          {
            //Sett right to null, and let garbage collection deletes the node
            right = null;
            return true;
          }
          else
          {
            Node parentToSmallest = remove.right.findParentToSmallestInSubtree();
            if (parentToSmallest != null)
            {
            //node to be removed is not parent to smallest
              Node smallest = parentToSmallest.left;

              if (smallest.isLeaf())
              {
                parentToSmallest.left = null;
              }
              else
              {
                parentToSmallest.left = smallest.right;
              }

              smallest.right = remove.right;
            }
            else
            {
              //Node to be removed is parrent to smallest node in right subtree
              Node smallest = remove.right;
            }
            smallest.left = remove.left;
            this.right = smallest;
            return true;
          }
        }
      }
    }

    void removeRightChild()
    {

    }

    void removeLeftChild()
    {
      
    }

    Node findParent(Node n)
    {
      if (n.value < value)
      {
        if (n == right)
        {
          return this;
        }
        else
        {
          return right.findParent(n);
        }
      }
      else
      {
        if (n == left)
        {
          return this;
        }
        else
        {
          return left.findParent(n);
        }
      }
    }

    Node find(int value)
    {
      if (this.value == value)
      {
        return this;
      }
      else if (this.value < value)
      {
        return right.find(value);
      }
      else
      {
        return left.find(value);
      }
    }

    Node left, right;
    /** Values in left subtree is smaller than the value in the node.
    * Values in right subtree is larger than the valeu in the node.
    */
    int value;
  }

}

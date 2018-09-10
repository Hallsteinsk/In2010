import java.util.ArrayList;

//Internal Node class
class Node
{
  //Constructors
  public Node()
  {
    value = 0;
    left = null;
    right = null;
  }

  public Node(int v)
  {
    value = v;
    left = null;
    right = null;
  }

  //Getters
  public int value()
  {
    return value;
  }

  public Node left()
  {
    return left;
  }

  public Node right()
  {
    return right;
  }

  //Setters
  public void setLeft(Node left)
  {
    this.left = left;
  }

  public void setRight(Node right)
  {
    this.right = right;
  }

  //Public methods
  public Node findParent(Node n)
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

  public Node find(int value)
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

  public void inOrder(ArrayList<Integer> integers)
  {
    if (this.left() != null)
    {
      this.left().inOrder(integers);
    }
    integers.add(this.value());
    if (this.right() != null)
    {
      this.right().inOrder(integers);
    }
  }

  public boolean remove(int value)
  {
    //If this node is a leaf, the value is not in the tree
    if (this.isLeaf())
    {
      return false;
    }
    //Remove in right subtree
    if (this.value < value)
    {
      if (right.value == value)
      {
        //Found node to be removed, and this is its parent
        removeRightChild();
        return true;
      }
      else
      {
        //The right child is not the node to be removed. Recursive call
        return right.remove(value);
      }
    }
    else if(this.value > value)
    {
      //Node to be removed is in left subtree
      if (left.value == value)
      {
        removeLeftChild();
        return true;
      }
      else
      {
        return left.remove(value);
      }
    }
    else
    {
      //This will never happen... But is kept for debugging.
      return false;
    }
  }

  public boolean isLeaf()
  {
    return (left == null && right == null);
  }

  public Node findParentToLargestInSubtree()
  {
    if (this.right() == null)
    {
      return null;
    }
    else if (this.right().right() == null)
    {
      return this;
    }
    else
    {
      return this.right().findParentToLargestInSubtree();
    }
  }

  public Node findParentToSmallestInSubtree()
  {
    if (this.left() == null)
    {
      return null;
    }
    else if (this.left().left() == null)
    {
      return this;
    }
    else
    {
      return this.left().findParentToSmallestInSubtree();
    }
  }

  //Private methods
  private void removeRightChild()
  {
    Node remove = right;
    if (remove.isLeaf())
    {
      //Sett right to null, and let garbage collection deletes the node
      this.setRight(null);
    }
    else
    {
      Node parentToSmallest = remove.right().findParentToSmallestInSubtree();
      Node smallest;
      if (parentToSmallest == null)
      {
        //node to be removed is parent to smallest
        smallest = remove.right();
      }
      else
      {
        smallest = parentToSmallest.left();
        if (smallest.isLeaf())
        {
          parentToSmallest.setLeft(null);
        }
        else
        {
          parentToSmallest.setLeft(smallest.right());
        }
        smallest.setRight(remove.right());
      }
      smallest.setLeft(remove.left());
      this.setRight(smallest);
    }
  }

  private  void removeLeftChild()
  {
    Node remove = this.left();
    if (left.isLeaf())
    {
      //Sett left to null, and let garbage collection deletes the node
      this.setLeft(null);
    }
    else
    {
      Node parentToLargest = remove.left().findParentToLargestInSubtree();
      Node largest;
      if (parentToLargest == null)
      {
        //Node to be removed is parent to largest in sub tree
        largest = remove.left();
      }
      else
      {
        largest = parentToLargest.right();
        if (largest.isLeaf())
        {
          parentToLargest.setRight(null);
        }
        else
        {
          parentToLargest.setRight(largest.left());
        }
        largest.setLeft(remove.left());
      }
      largest.setRight(remove.right());
      this.setLeft(largest);
    }

  }

  //Private members

  /** Values in left subtree is smaller than the value in the node.
  * Values in right subtree is larger than the valeu in the node.
  */
  private Node left, right;
  private int value;
}

import java.util.ArrayList;

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
      if (value == root.value())
      {
        root = null;
        return true;
      }
      else
      {
        return root.remove(value);
      }
  }

  public int size()
  {
    return size;
  }

  public boolean existsInTree(int value)
  {
    return existsInTree(root, value);
  }

  /**Returns the value that is smaller than value and is closest to value.
  * If the value is smaller than all valuest in the tree the value itself
  * is returned.
  */
  public int findNearestSmallerThan(int value)
  {
    Node nodeRef = root;
    while (nodeRef.value() > value)
    {
      nodeRef = nodeRef.left();
      if (nodeRef == null)
      {
        return value;
      }
    }
    return nodeRef.value();
  }

  public void addAll (int[] integers)
  {
      for (int i=0; i<integers.length; i++)
      {
        add(integers[i]);
      }
  }

  public int[] sortedArray() //inorder
  {
    ArrayList<Integer> integers = new ArrayList<>();
    root.inOrder(integers);
    int index = 0;
    int[] sorted = new int[integers.size()];
    for (int integer : integers)
    {
      sorted[index++] = integer;
    }
    return sorted;
  }

  void inorder()
  {

  }

  public int[] findInRange(int low, int high)
  {
    int maxSizeOfArray = high - low;
    if (maxSizeOfArray < 1)
    {
      return null;
    }
    int[] numbersFound = new int[maxSizeOfArray];
    int index = 0;
    int count = low;
    while (count <= high)
    {
      if (existsInTree(count))
      {
        numbersFound[index] = count;
        index++;
      }
      count++;
    }
    return numbersFound;
  }

  //Private methods
  private Node findParent(Node n)
  {
    if (n == root)
    {
      return null;
    }
    else
    {
      return root.findParent(n);
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
        if (value < nodeRef.value())
        {
          add(nodeRef.left(), value);
        }
        else
        {
          add(nodeRef.right(), value);
        }
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
      if (value < nodeRef.value())
      {
        return existsInTree(nodeRef.left(), value);
      }
      else if (value == nodeRef.value())
      {
        return true;
      }
      else //if (value > nodeRef.value())
      {
        return existsInTree(nodeRef.right(), value);
      }
    }
  }

  //Private members
  private Node root;
  private int size;
}

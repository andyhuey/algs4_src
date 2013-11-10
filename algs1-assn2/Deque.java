/*----------------------------------------------------------------
 *  Author:        Andrew Huey
 *  Written:       11/09/2013
 *  Last updated:  11/09/2013
 *
 *  needs stdlib.jar and algs4.jar in classpath.
 *  
 *  https://class.coursera.org/algs4partI-003/assignment/view?assignment_id=3
 *  http://coursera.cs.princeton.edu/algs4/assignments/queues.html
 *  http://coursera.cs.princeton.edu/algs4/checklists/queues.html
 *
 *
 *----------------------------------------------------------------*/

public class Deque<Item> implements Iterable<Item> {
    // construct an empty deque
    public Deque()
    {
        //todo
    }
    
    // is the deque empty?
    public boolean isEmpty()
    {
        return true;
    }
    
    // return the number of items on the deque
    public int size()
    {
        return 0;
    }
    
    // insert the item at the front
    public void addFirst(Item item)
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        //todo
    }
    
    // insert the item at the end
    public void addLast(Item item)
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        //todo
    }
    
    // delete and return the item at the front
    public Item removeFirst()
    {
        // throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque
        //todo
    }
    
    // delete and return the item at the end
    public Item removeLast()
    {
        //throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque
        //todo
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator()
    {
        //todo
        boolean hasNext()
        {
            //todo
        }
        Item next()
        {
            // throw a java.util.NoSuchElementException if the client calls the next() method in the iterator and there are no more items to return. 
            //todo
        }
        void remove()
        {
            throw new java.lang.UnsupportedOperationException();
        }
        
    }

    public static void main(String[] args)
    {
    }
}
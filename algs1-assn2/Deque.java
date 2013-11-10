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
    
    private Node<Item> first, last;
    int size;
    
    // construct an empty deque
    public Deque()
    {
        first = null;
        last = null;
        size = 0;
    }
    
    // is the deque empty?
    public boolean isEmpty()
    {
        return first == null;
    }
    
    // return the number of items on the deque
    public int size()
    {
        return size;
    }
    
    // insert the item at the front
    public void addFirst(Item item)
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;
        size++;
    }
    
    // insert the item at the end
    public void addLast(Item item)
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        size++;
    }
    
    // delete and return the item at the front
    public Item removeFirst()
    {
        // throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque
        if (first == null)
            throw new java.util.NoSuchElementException();
        Item item = first.item;
        first = first.next;
        size--;
        // edge case
        if (last == item)
            last = null;
        return item;
    }
    
    // delete and return the item at the end
    public Item removeLast()
    {
        //throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque
        if (last == null)
            throw new java.util.NoSuchElementException();
        Item item = last.item;
        size--;
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
        
    private class Node<Item>
    {
        Item item;
        Node next;
        Node prev;
    }
        

    public static void main(String[] args)
    {
    }
}
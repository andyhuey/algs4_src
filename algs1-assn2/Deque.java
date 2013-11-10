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
 *----------------------------------------------------------------*/
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> 
{
    private Node first, last;
    private int size;
    
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
        // edge case
        if (oldFirst == null)
            last = first;
        else
            oldFirst.prev = first;
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
        // edge case
        if (oldLast == null)
            first = last;
        else
            oldLast.next = last;
        size++;
    }
    
    // delete and return the item at the front
    public Item removeFirst()
    {
        if (first == null)
            throw new java.util.NoSuchElementException();
        Item item = first.item;
        first = first.next;
        size--;
        // edge case
        if (size == 0)
            last = null;
        else
            first.prev = null;  // break link to prev
        return item;
    }
    
    // delete and return the item at the end
    public Item removeLast()
    {
        if (last == null)
            throw new java.util.NoSuchElementException();
        Item item = last.item;
        last = last.prev;        
        size--;
        // edge case
        if (size == 0)
            first = null;
        else
            last.next = null;  // break link to next
        return item;
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() 
    { 
        return new ListIterator(); 
    }

    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;
        
        public boolean hasNext()
        {
            return current != null;
        }
        
        public Item next()
        {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
        
        public void remove()
        {
            throw new java.lang.UnsupportedOperationException();
        }
    }
        
    private class Node
    {
        private Item item;
        private Node next;
        private Node prev;
    }
        

    public static void main(String[] args)
    {
        String s;
        Deque<String> dq = new Deque<String>();
        assert dq.isEmpty();
        dq.addFirst("Item one");
        assert !dq.isEmpty();
        s = dq.removeFirst();
        assert s.equals("Item one");
        assert dq.isEmpty();
        
        dq.addLast("Item one");
        assert !dq.isEmpty();
        s = dq.removeFirst();
        assert s.equals("Item one");
        assert dq.isEmpty();
        
        dq.addLast("Item one");
        assert !dq.isEmpty();
        s = dq.removeLast();
        assert s.equals("Item one");
        assert dq.isEmpty();
        
        dq.addLast("Item one");
        dq.addLast("Item two");
        assert !dq.isEmpty();
        s = dq.removeFirst();
        assert s.equals("Item one");
        s = dq.removeFirst();
        assert s.equals("Item two");
        assert dq.isEmpty();
        
        dq.addLast("Item one");
        dq.addLast("Item two");
        for (String s1 : dq)
        {
            StdOut.println(s1);
        }
    }
}
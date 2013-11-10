/*----------------------------------------------------------------
 *  Author:        Andrew Huey
 *  Written:       11/10/2013
 *  Last updated:  11/10/2013
 *
 *  needs stdlib.jar and algs4.jar in classpath.
 *  
 *  https://class.coursera.org/algs4partI-003/assignment/view?assignment_id=3
 *  http://coursera.cs.princeton.edu/algs4/assignments/queues.html
 *  http://coursera.cs.princeton.edu/algs4/checklists/queues.html
 * 
 *  http://introcs.cs.princeton.edu/java/stdlib/StdRandom.java.html
 * 
 *----------------------------------------------------------------*/
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private Node first, last;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue()
    {
        first = null;
        last = null;
        size = 0;
    }
    
    // is the queue empty?
    public boolean isEmpty()
    {
        return first == null;
    }
    
    // return the number of items on the queue
    public int size()
    {
        return size;
    }
    
    private int randomNodeIndex()
    {
        return StdRandom.uniform(size);
    }
    
    // add the item
    public void enqueue(Item item)
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        // edge case
        if (oldLast == null)
            first = last;
        else
            oldLast.next = last;
        size++;
    }
    
    // delete and return a random item
    public Item dequeue()
    {        
        if (first == null)
            throw new java.util.NoSuchElementException();
        Node rPrevNode = null;
        Node rNode = first;
        int i = 0;
        int r = randomNodeIndex();
        //StdOut.printf("dequeing node %d of %d.\n", r, size());
        while (i < r)
        {
            rPrevNode = rNode;
            rNode = rNode.next;
            i++;
        }
        size--;
        
        if (rPrevNode != null)
            rPrevNode.next = rNode.next;
        else
        {
            // we're returning the first node.
            first = rNode.next;
        }
            
        // we're returning the last node.
        if (rNode == last)
            last = rPrevNode;
        
        if (size == 0)
            first = null;

        return rNode.item;
    }
    
    // return (but do not delete) a random item
    public Item sample()
    {
        if (first == null)
            throw new java.util.NoSuchElementException();
        int r = randomNodeIndex();
        return getItemByIndex(r);
    }
    
    private Item getItemByIndex(int r)
    {
        int i = 0;
        Node rNode = first;
        while (i++ < r)
            rNode = rNode.next;
        return rNode.item;
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() 
    { 
        return new ListIterator(); 
    }

    private class ListIterator implements Iterator<Item>
    {
        private int currentIndex;
        private int[] shuffledOrder;
        
        public ListIterator()
        {
            // start at the beginning
            currentIndex = 0;
            // create a shuffled array.
            if (size > 0)
            {
                shuffledOrder = new int[size()];
                for (int i = 0; i < size(); i++)
                    shuffledOrder[i] = i;
                StdRandom.shuffle(shuffledOrder);
            }
        }

        public boolean hasNext()
        {
            return currentIndex < size(); 
        }
        
        public Item next()
        {
            //todo
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            
            return getItemByIndex(shuffledOrder[currentIndex++]);
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
        //private Node prev;
    }
    
    public static void main(String[] args)
    {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("one");
        rq.enqueue("two");
        rq.enqueue("three");

//        for (int i = 0; i < 3; i++)
//            StdOut.println(rq.sample()); 
//        assert !rq.isEmpty();

//        for (int i = 0; i < 3; i++)
//            StdOut.println(rq.dequeue()); 
//        assert rq.isEmpty();
        
        for (String s1 : rq)
        {
            StdOut.println(s1);
        }
        
    }   
}
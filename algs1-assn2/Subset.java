/*----------------------------------------------------------------
 *  Author:        Andrew Huey
 *  Written:       11/10/2013
 *  Last updated:  11/10/2013
 *
 *----------------------------------------------------------------*/

public class Subset {
    public static void main(String[] args)
    {
        //  takes one command line arg, k.
        if (args.length != 1)
        {
            usage(); 
            return;
        }
        int k = Integer.parseInt(args[0]);
        // reads in N strings from std input.
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        String s;
        while (!StdIn.isEmpty())
        {
            s = StdIn.readString();
            rq.enqueue(s);
        }
        
        //  print out exactly k strings, uniformly at random.
        int i = 0;
        for (String s1 : rq)
        {
            if (++i > k)
                break;
            StdOut.println(s1);
        }
        
//        while (i++ < k)
//            StdOut.println(rq.dequeue());
    }
    
    private static void usage()
    {
        StdOut.println("Usage: java Subset k");
    }
}

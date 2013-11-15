/*************************************************************************
 *  Author:        Andrew Huey
 *  Written:       11/15/2013
 *  Last updated:  11/15/2013
 *
 * Description: 
 * 
 *  https://class.coursera.org/algs4partI-003/assignment/view?assignment_id=4
 *  http://coursera.cs.princeton.edu/algs4/assignments/collinear.html
 *  http://coursera.cs.princeton.edu/algs4/checklists/collinear.html
 *
 *************************************************************************/

public class Fast {
    
    private Point[] p;
    int N;
    
    public Fast(Point[] p)
    {
        this.p = p;
        this.N = p.length;
    }
    
    public void runChecks()
    {
    }

    public static Point[] getInputArray(String filename)
    {
        In in = new In(filename);
        
        int N = in.readInt();
        Point[] p = new Point[N];
        
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            p[i] = new Point(x, y);
        }
        return p;
    }

    public static void main(String[] args)
    {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        // defer drawing until the end
        //StdDraw.show(0);
        
        // read in the input
        String filename = args[0];
        Point[] p = getInputArray(filename);
        int N = p.length;        
        
        // we want to draw all the points.
        for (int i = 0; i < N; i++) {
            p[i].draw();
        }
        
        Fast myFast = new Fast(p);
        myFast.runChecks();
        
        // display to screen all at once
        //StdDraw.show(0);
    }

}
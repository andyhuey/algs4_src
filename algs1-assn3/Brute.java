/*************************************************************************
 *  Author:        Andrew Huey
 *  Written:       11/12/2013
 *  Last updated:  11/12/2013
 *
 * Description: "Write a program Brute.java that examines 4 points at a time
 * and checks whether they all lie on the same line segment,
 * printing out any such line segments to standard output
 * and drawing them using standard drawing."
 * 
 *  https://class.coursera.org/algs4partI-003/assignment/view?assignment_id=4
 *  http://coursera.cs.princeton.edu/algs4/assignments/collinear.html
 *  http://coursera.cs.princeton.edu/algs4/checklists/collinear.html
 *
 *************************************************************************/

public class Brute {

    private Point[] p;
    int N;
    
    public Brute(Point[] p)
    {
        this.p = p;
        this.N = p.length;
    }
    
    private void checkLine(int i, int j, int k, int l)
    {
        // are these four points on a line?
        // To check whether the 4 points p, q, r, and s are collinear,
        // check whether the slopes between p and q, between p and r,
        // and between p and s are all equal.
        double s1 = p[i].slopeTo(p[j]);
        double s2 = p[i].slopeTo(p[k]);
        double s3 = p[i].slopeTo(p[l]);
        if (s1 == s2 && s2 == s3)
        {
            // they're collinear.
            // figure min & max points.
            // (should really be sorting this...)
            Point pMin = p[i];
            Point pMax = p[i];
            int c;
            c = pMin.compareTo(p[j]);
            if (c < 0) pMin = p[j];
            if (c > 0) pMax = p[j];
            
            c = pMin.compareTo(p[k]);
            if (c < 0) pMin = p[k];
            if (c > 0) pMax = p[k];

            c = pMin.compareTo(p[l]);
            if (c < 0) pMin = p[l];
            if (c > 0) pMax = p[l];
            
            // draw the line.
            pMin.drawTo(pMax);
            // and output the line segment.
            StdOut.printf("%s -> %s -> %s -> %s\n",
                          p[i], p[j], p[k], p[l]);
        }   
    }
    
    public void runChecks()
    {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < i; j++)
                for (int k = 0; k < j; k++)
                    for (int l = 0; l < k; l++)
                        checkLine(i, j, k, l);
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
        
        // test
//        int N = 4;
//        Point[] p = new Point[4];
//        p[0] = new Point(1234, 5678);
//        p[1] = new Point(32000, 10000);
//        p[2] = new Point(18000, 10000);
//        p[3] = new Point(19000, 10000);

        // read in the input
        String filename = args[0];
        Point[] p = getInputArray(filename);
        int N = p.length;
        
        
        // we want to draw all the points.
        for (int i = 0; i < N; i++) {
            p[i].draw();
        }
        
        Brute myBrute = new Brute(p);
        myBrute.runChecks();
        
        // display to screen all at once
        //StdDraw.show(0);
    
    }
}
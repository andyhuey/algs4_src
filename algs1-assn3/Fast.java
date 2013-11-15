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

import java.util.Arrays;

public class Fast {
    
    private Point[] p;
    private int N;
    
    public Fast(Point[] p)
    {
        this.p = p;
        this.N = p.length;
    }
    
    private void drawLine(Point[] pLine)
    {
        // input shoud be an array of 4 indices into p.
        // assert idxs.length == 4;
        int n = pLine.length;
        Arrays.sort(pLine);
        // draw the line.
        pLine[0].drawTo(pLine[n-1]);
        // and output the line segment.
        for (int i = 0; i < n; i++)
        {
            StdOut.print(pLine[i]);
            if (i < n-1)
                StdOut.print(" -> ");
        }
        StdOut.println();
    }
    
    public void runChecks()
    {
        for (int i = 0; i < N; i++)
        {
            Point[] q = p.clone(); 
            Arrays.sort(q, p[i].SLOPE_ORDER);
            
//            StdOut.printf("%s -> ", p[i]);
//            for (Point pt : q)
//            {
//                StdOut.printf("%s, ", pt);
//            }
//            StdOut.println();
//            return;
            
            // any 3 or more points with equal slopes are collinear.
            for (int j = 0; j <= N - 3; j++)
            {                
                double s1 = p[i].slopeTo(q[j]);
                double s2 = p[i].slopeTo(q[j + 1]);
                double s3 = p[i].slopeTo(q[j + 2]);
                if (s1 == s2 && s2 == s3)
                {
                    // they're collinear.
                    Point[] pLine = new Point[4];
                    pLine[0] = p[i];
                    for (int k = 0; k < 3; k++)
                        pLine[k + 1] = q[j + k];
                    drawLine(pLine);
                }
            }
        }
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
        //StdOut.printf("We're done.\n");
        
        // display to screen all at once
        //StdDraw.show(0);
    }

}
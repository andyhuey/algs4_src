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
    
    private static Point[] p;
    private static int N;
    
    private static void drawLine(Point[] pLine)
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
    
    private static void runChecks()
    {
        //Arrays.sort(p);
        for (int i = 0; i < N; i++)
        {
            Point[] q = p.clone(); 
            Arrays.sort(q, p[i].SLOPE_ORDER);
            
            // any 3 or more points with equal slopes are collinear.
            int j = 0;
            while (j <= N - 3)
            {
                double s1 = p[i].slopeTo(q[j]);
                int pts = 1;
                int k = j + 1;
                while (k < N)
                {
                    if (s1 != p[i].slopeTo(q[k]))
                        break;
                    pts++;
                    k++;
                }
                if (pts >= 3)
                {
                    // they're collinear.
                    Point[] pLine = new Point[pts+1];
                    pLine[0] = p[i];
                    int k1;
                    for (k1 = 0; k1 < pts; k1++)
                        pLine[k1 + 1] = q[j + k1];
                    drawLine(pLine);
                    // we can skip forward, too.
                    j += k1;
                }
                else
                {
                    j++;
                }
                    
            }
        }
    }

    private static void getInputArray(String filename)
    {
        In in = new In(filename);
        
        N = in.readInt();
        p = new Point[N];
        
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            p[i] = new Point(x, y);
        }
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
        getInputArray(filename);
        
        // we want to draw all the points.
        for (int i = 0; i < N; i++) {
            p[i].draw();
        }
        
        runChecks();
        //StdOut.printf("We're done.\n");
        
        // display to screen all at once
        //StdDraw.show(0);
    }

}
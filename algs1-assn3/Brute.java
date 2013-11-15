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

import java.util.Arrays;

public class Brute {

    private static Point[] p;
    private static int N;
    
    private static void drawLine(Point[] pLine)
    {
        // input shoud be an array of 4 (or more) indices into p.
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

    private static void checkLine(int i, int j, int k, int l)
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
            Point[] pLine = new Point[4];
            pLine[0] = p[i];
            pLine[1] = p[j];
            pLine[2] = p[k];
            pLine[3] = p[l];
            drawLine(pLine);
        }   
    }
    
    private static void runChecks()
    {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < i; j++)
                for (int k = 0; k < j; k++)
                    for (int l = 0; l < k; l++)
                        checkLine(i, j, k, l);
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
        
        // display to screen all at once
        //StdDraw.show(0);
    
    }
}
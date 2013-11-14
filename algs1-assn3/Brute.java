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
    
    private static void checkLine(int i, int j int k, int l)
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
            private Point[4] pLine = new Point[4];
            p[0] = p[i];
            p[1] = p[j];
            p[2] = p[k];
            p[3] = p[l];
            // TODO: sort this, then draw p[0] -> p[3].
            //pLine.sort(pLine)
        }   
    }
    
    public static void main(String[] args)
    {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        
        int N = in.readInt();
        p = new Point[N];
        
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p[i] = new Point(x, y);
            //p[i].draw();
        }
        
        for (int i = 0; i < N; i++)
            for (int j = 0; j < i; j++)
                for (int k = 0; k < j; k++)
                    for (int l = 0; l < k; l++)
                        this.checkLine(i, j, k, l);

        // display to screen all at once
        StdDraw.show(0);
    
    }
}
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
//import java.util.Comparator;

public class Fast {
    
    private class SlopeRef implements Comparable<SlopeRef> {
        public int idx;
        public double slope;
        
        public SlopeRef(int i, double s) {
            this.idx = i;
            this.slope = s;
        }

        // we will sort by slope.
        public int compareTo(SlopeRef that) {
            if (this.slope < that.slope) return -1;
            if (this.slope > that.slope) return +1;
            return 0;
        }
    }
    
    private Point[] p;
    int N;
    
    public Fast(Point[] p)
    {
        this.p = p;
        this.N = p.length;
    }
    
    private void drawLine(int[] idxs)
    {
        // input shoud be an array of 4 indices into p.
        assert idxs.length == 4;
        
        // figure min & max points.
        Point pMin = p[idxs[0]];
        Point pMax = p[idxs[0]];
        int c;
        
        for (int x = 1; x <= 3; x++)
        {
            c = pMin.compareTo(p[idxs[x]]);
            if (c < 0) pMin = p[idxs[x]];
            c = pMax.compareTo(p[idxs[x]]);
            if (c > 0) pMax = p[idxs[x]];
        }
        
        // draw the line.
        pMin.drawTo(pMax);
        // and output the line segment.
        StdOut.printf("%s -> %s -> %s -> %s\n",
            p[idxs[0]], p[idxs[1]], p[idxs[2]], p[idxs[3]]);
        
    }
    
    public void runChecks()
    {
        for (int i = 0; i < N; i++)
        {
            // figure out all the slopes.
            SlopeRef[] slopes = new SlopeRef[N];
            for (int j=0; j < N; j++)
            {
                slopes[j] = new SlopeRef(j, p[i].slopeTo(p[j]));
            }
            
            // note that the slope on the origin point itself should be
            // negative infinity, so it should sort to be the first element.
            Arrays.sort(slopes);
            
            // any 3 or more points with equal slopes are collinear.
            for (int j=1; j < N - 3; j++)
            {
                if (slopes[j].slope == slopes[j+1].slope
                        && slopes[j+1].slope == slopes[j+2].slope)
                {
                    // they're collinear.
                    int idxs[] = new int[4];
                    idxs[0] = i;
                    idxs[1] = slopes[j].idx;
                    idxs[2] = slopes[j + 1].idx;
                    idxs[3] = slopes[j + 2].idx;
                    drawLine(idxs);
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
        StdOut.printf("We're done.\n");
        
        // display to screen all at once
        //StdDraw.show(0);
    }

}
/*************************************************************************
 *  Author:        Andrew Huey
 *  Written:       11/12/2013
 *  Last updated:  11/12/2013
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 * 
 *  https://class.coursera.org/algs4partI-003/assignment/view?assignment_id=4
 *  http://coursera.cs.princeton.edu/algs4/assignments/collinear.html
 *  http://coursera.cs.princeton.edu/algs4/checklists/collinear.html
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new BySlopeOrder();

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        // degenerate line seqment - negative infinity
        if (this.equals(that))
            return double.NEGATIVE.INFINITY;
        // vertical line - positive infinity
        if (that.x == this.x)
            return double.POSITIVE_INFINITY;
        // horizontal line - should return positive zero
        if (that.y == this.y)
            return +0.0;
        // all else
        return (that.y - this.y) / (that.x - this.x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
    
    private static class BySlopeOrder implements Comparator<Point>
    {
        // Formally, the point (x1, y1) is less than the point (x2, y2)
        // if and only if the slope (y1 − y0) / (x1 − x0)
        // is less than the slope (y2 − y0) / (x2 − x0).
        public int compare(Point p1, Point p2)
        {
            // degenerate line seqment - negative infinity
            if (p1.equals(p2))
                return double.NEGATIVE.INFINITY;
            // vertical line - positive infinity
            if (p1.x == p2.x)
                return double.POSITIVE_INFINITY;
            // horizontal line - should return positive zero
            if (p1.y == p2.y)
                return +0.0;
            // all else
            double slope1 = this.slopeTo(p1);
            double slope2 = this.slopeTo(p2);
            return slope1.compareTo(slope2);
        }
    }    
}

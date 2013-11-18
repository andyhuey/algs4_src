/*************************************************************************
 *  Author:        Andrew Huey
 *  Written:       11/16/2013
 *  Last updated:  11/16/2013
 *
 * Description: 
 * 
 *  https://class.coursera.org/algs4partI-003/assignment/view?assignment_id=5
 *  http://coursera.cs.princeton.edu/algs4/assignments/8puzzle.html
 *  http://coursera.cs.princeton.edu/algs4/checklists/8puzzle.html
 *
 *************************************************************************/


public class Board {
    // probably:
    private int N;
    private int[][] tiles;
    // ...and a MinPQ priority queue?
    
    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks)
    {
        //todo
    }
    
    // board dimension N
    public int dimension()
    {
        //todo
    }
    
    // number of blocks out of place
    public int hamming()
    {
        //todo
    }
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan()
    {
        //todo
    }
    
    // is this board the goal board?
    public boolean isGoal()
    {
        //todo
    }
    
    // a board obtained by exchanging two adjacent blocks in the same row
    public Board twin()
    {
        //todo
    }
    
    // does this board equal y?
    public boolean equals(Object y)
    {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        //todo
        
    }
    
    // all neighboring boards
    public Iterable<Board> neighbors()
    {
        //todo
    }
    
    // string representation of the board (in the output format specified below)
    public String toString()
    {
        //todo
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();        
    }
}
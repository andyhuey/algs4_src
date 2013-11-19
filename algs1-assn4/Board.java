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
    
    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    // the empty cell is represented by a zero.
    public Board(int[][] blocks)
    {
        this.tiles = blocks.clone();    // maybe?
        this.N = blocks[0].length;
    }
    
    // board dimension N
    public int dimension()
    {
        return this.N;  // ?
    }
    
    private int getCell(int n)
    {
        // return the cell value for cell n, where n=1..N^2-1
        int i = (n-1) / N;
        int j = (n-1) % N;
        return tiles[i][j];
    }
    
    // number of blocks out of place
    public int hamming()
    {
        int nOut = 0;
        for (int n = 1; n < N*N; n++)
            if (getCell(n) != n)
                nOut++;
        return nOut;
    }
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan()
    {
        int nDist = 0;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                int currVal = tiles[i][j];
                if (CurrVal == 0)
                    continue;
                int i1 = (currVal-1) / N;
                int j1 = (CurrVal-1) % N;
                nDist += Math.abs(i - i1);
                nDist += Math.abs(j - j1);
            }
        }
        return nDist;
    }
    
    // is this board the goal board?
    public boolean isGoal()
    {
        for (int n = 1; n < N*N; n++)
            if (getCell(n) != n)
                return false;
        return true;
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
        // probably...?
        return Arrays.deepEquals(y.tiles, this.tiles);
        
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
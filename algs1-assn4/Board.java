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

import java.util.Arrays;

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
    
    // exchange tile i with tile j, where i or j=1..N^2-1
    private void exchTiles(int i, int j) 
    {
        int swap = getCell(i);
        putCell(i, getCell(j));
        putCell(j, swap);
    }
    
    private int getCell(int n)
    {
        // return the cell value for cell n, where n=1..N^2-1
        int i = (n-1) / N;
        int j = (n-1) % N;
        return tiles[i][j];
    }

    private void putCell(int n, int value)
    {
        // set the cell value for cell n, where n=1..N^2-1
        int i = (n-1) / N;
        int j = (n-1) % N;
        tiles[i][j] = value;
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
                if (currVal == 0)
                    continue;
                int i1 = (currVal-1) / N;
                int j1 = (currVal-1) % N;
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
        int[][] newTiles = this.tiles.clone();
        if (newTiles[0][0] != 0)
        {
            // exchange 1st two cells in 1st row
            int swap = newTiles[0][0];
            newTiles[0][0] = newTiles[0][1];
            newTiles[0][1] = swap;
        }
        else
        {
            // exchange 1st two cells in second row
            int swap = newTiles[1][0];
            newTiles[1][0] = newTiles[1][1];
            newTiles[1][1] = swap;
        }
        return new Board(newTiles);
    }
    
    // does this board equal y?
    public boolean equals(Object y)
    {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        // probably...?
        return Arrays.deepEquals(((Board)y).tiles, this.tiles);
        
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
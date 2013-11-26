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
        this.N = blocks[0].length;
        // deep copy
        this.tiles = new int[N][];
        for (int i = 0; i < N; i++)
            this.tiles[i] = Arrays.copyOf(blocks[i], N);
    }
    
    private int[][] copyOfTiles()
    {
        // return a deep copy of the tiles array.
        int[][] newTiles = new int[N][];
        for (int i = 0; i < N; i++)
            newTiles[i] = Arrays.copyOf(tiles[i], N);
        return newTiles;
    }
    
    // board dimension N
    public int dimension()
    {
        return this.N;
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
        int[][] newTiles = this.copyOfTiles();
        if (newTiles[0][0] != 0 && newTiles[0][1] != 0)
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
        return Arrays.deepEquals(((Board) y).tiles, this.tiles);
    }
    
    // all neighboring boards
    public Iterable<Board> neighbors()
    {
        // find the empty cell
        int i0 = 0, j0 = 0;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if (tiles[i][j] == 0)
                {
                    i0 = i;
                    j0 = j;
                    break;
                }
            }
        }
        //System.out.printf("i0=%d, j0=%d\n", i0, j0);
        
        // create the list of neighbors
        Stack<Board> neighbors = new Stack<Board>();
        // move uo
        if (i0 > 0)
        {
            int[][] newTiles = this.copyOfTiles();
            newTiles[i0][j0] = newTiles[i0-1][j0];
            newTiles[i0-1][j0] = 0;
            neighbors.push(new Board(newTiles));
        }
        // move down
        if (i0 < N-1)
        {
            int[][] newTiles = this.copyOfTiles();
            newTiles[i0][j0] = newTiles[i0+1][j0];
            newTiles[i0+1][j0] = 0;
            neighbors.push(new Board(newTiles));
        }
        // move left
        if (j0 > 0)
        {
            int[][] newTiles = this.copyOfTiles();
            newTiles[i0][j0] = newTiles[i0][j0-1];
            newTiles[i0][j0-1] = 0;
            neighbors.push(new Board(newTiles));
        }
        // move right
        if (j0 < N-1)
        {
            int[][] newTiles = this.copyOfTiles();
            newTiles[i0][j0] = newTiles[i0][j0+1];
            newTiles[i0][j0+1] = 0;
            neighbors.push(new Board(newTiles));
        }
        return neighbors;
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
    
    public static void main(String[] args) 
    {
        //String filename = "8puzzle\\puzzle3x3-unsolvable.txt";
        String filename = "8puzzle\\ajh_ham_test1.txt";
        // read in the board
        In in = new In(filename);
        int N = in.readInt();
        int[][] tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        Board initial = new Board(tiles);
        System.out.println(initial.toString());
        
        // test neighbors
//        for (Board b : initial.neighbors())
//        {
//            System.out.println(b.toString());
//        }

        // test equals
        Board b2 = new Board(tiles);
        assert b2.equals(initial);
        // test isGoal
        assert !b2.isGoal();
        
        // test twin
        b2 = initial.twin();
        System.out.println(b2.toString());
        System.out.println(initial.toString());
    
        System.out.printf("Hamming=%d\n", initial.hamming());
        System.out.printf("Manhattan=%d\n", initial.manhattan());
                          
    }   
    
}
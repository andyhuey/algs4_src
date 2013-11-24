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

public class Solver {

    // MinPQ priority queue?
    MinPQ<SearchNode> myMinPQ;
    
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)
    {
        //todo
        myMinPQ = new MinPQ<SearchNode>();
        SearchNode firstNode = new SearchNode(initial, 0, null);
        myMinPQ.insert(firstNode);
    }
    
    // is the initial board solvable?
    public boolean isSolvable()
    {
        //todo
        return true;
    }
    
    // min number of moves to solve initial board; -1 if no solution
    public int moves()
    {
        if (!isSolvable())
            return -1
                
        //todo
        return 0;
    }
    
    // sequence of boards in a shortest solution; null if no solution
    public Iterable<Board> solution()
    {
        //todo
        return new Stack<Board>();
    }

    // solve a slider puzzle 
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
    
        // solve the puzzle
        Solver solver = new Solver(initial);
    
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    // interable and/or comparable?
    private class SearchNode /*implements Comparable<SearchNode>*/
    {
        private Board currentBoard;
        private int moveCount;          // # of moves to get here
        private SearchNode prevNode;    // previous search node
        
        public SearchNode(Board currentBoard, int moveCount, SearchNode prevNode)
        {
            this.currentBoard = currentBoard;
            this.moveCount = moveCount;
            this.prevNode = prevNode;
        }
    }

}
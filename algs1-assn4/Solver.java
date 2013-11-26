/*************************************************************************
 *  Author:        Andrew Huey
 *  Written:       11/16/2013
 *  Last updated:  11/16/2013
 *
 * Description: 
 * "First, insert the initial search node (the initial board, 0 moves, 
 * and a null previous search node) into a priority queue. 
 * Then, delete from the priority queue the search node with the minimum 
 * priority, and insert onto the priority queue all neighboring search nodes 
 * (those that can be reached in one move from the dequeued search node). 
 * Repeat this procedure until the search node dequeued corresponds 
 * to a goal board."
 * 
 *  https://class.coursera.org/algs4partI-003/assignment/view?assignment_id=5
 *  http://coursera.cs.princeton.edu/algs4/assignments/8puzzle.html
 *  http://coursera.cs.princeton.edu/algs4/checklists/8puzzle.html
 *
 *************************************************************************/

public class Solver {
    private Board initial;
    private Boolean isSolvable;
    private Stack<Board> soln;
    
    // MinPQ priority queue
    private MinPQ<SearchNode> myMinPQ;
    // save the goal node, with the backlinks...
    private SearchNode goalNode;
    
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)
    {
        this.initial = initial;
        this.isSolvable = null;
        this.soln = null;
        
//        myMinPQ = new MinPQ<SearchNode>();
//        SearchNode myNode = new SearchNode(initial, 0, null);
//        myMinPQ.insert(myNode);
//        // not sure if I should do this right here...
//        myNode = myMinPQ.delMin();
//        Board myBoard = myNode.currentBoard;
//        while (!myBoard.isGoal())
//        {
//            Board prevBoard = myNode.prevNode.currentBoard;
//            for (Board b : myBoard.neighbors())
//            {
//                if (!b.equals(prevBoard))
//                    myMinPQ.insert(
//                        new SearchNode(b, myNode.moveCount + 1, myNode));
//            }
//            myNode = myMinPQ.delMin();
//            myBoard = myNode.currentBoard;
//        }
//        goalNode = myNode;
    }
    
    // is the initial board solvable?
    public boolean isSolvable()
    {
        if (this.isSolvable != null)
            return this.isSolvable;
        
        // "the current API requires you to detect infeasiblity in Solver by using two priority queues."
        Board myTwin = initial.twin();
        MinPQ<SearchNode> myTwinMinPQ;

        myMinPQ = new MinPQ<SearchNode>();
        SearchNode myNode = new SearchNode(initial, 0, null);
        myMinPQ.insert(myNode);

        myTwinMinPQ = new MinPQ<SearchNode>();
        SearchNode myTwinNode = new SearchNode(myTwin, 0, null);
        myTwinMinPQ.insert(myNode);
        
        myNode = myMinPQ.delMin();
        Board myBoard = myNode.currentBoard;
        
        myTwinNode = myTwinMinPQ.delMin();
        Board myTwinBoard = myTwinNode.currentBoard;
                
        while (!myBoard.isGoal() && !myTwinBoard.isGoal())
        {
            // process our board...
            Board prevBoard = null;
            if (myNode.prevNode != null)
                prevBoard = myNode.prevNode.currentBoard;
            
            for (Board b : myBoard.neighbors())
            {
                if (!b.equals(prevBoard))
                    myMinPQ.insert(
                        new SearchNode(b, myNode.moveCount + 1, myNode));
            }
            myNode = myMinPQ.delMin();
            myBoard = myNode.currentBoard;
            
            // process the twin board...
            Board prevTwinBoard = null;
            if (myTwinNode.prevNode != null)
                prevTwinBoard = myTwinNode.prevNode.currentBoard;
            
            for (Board b : myTwinBoard.neighbors())
            {
                if (!b.equals(prevTwinBoard))
                    myTwinMinPQ.insert(
                        new SearchNode(b, myTwinNode.moveCount + 1, myTwinNode));
            }
            myTwinNode = myTwinMinPQ.delMin();
            myTwinBoard = myTwinNode.currentBoard;
        }
        goalNode = myNode;

        this.isSolvable = myBoard.isGoal();
        return this.isSolvable;
    }
    
    // min number of moves to solve initial board; -1 if no solution
    public int moves()
    {
        if (!isSolvable())
            return -1;
                
        // probably need to have the stack here...
        solution();
        return soln.size();
    }
    
    // sequence of boards in a shortest solution; null if no solution
    public Iterable<Board> solution()
    {
        if (this.soln != null)
            return this.soln;
        
        soln = new Stack<Board>();
        SearchNode myNode = goalNode;
        while (myNode != null) 
        {
            soln.push(myNode.currentBoard);
            myNode = myNode.prevNode;
        }
        return soln;
    }

    // solve a slider puzzle 
    public static void main(String[] args) {
        // create initial board from file
        In in = new In("8puzzle\\ajh_ham_test1.txt");
        //In in = new In(args[0]);
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

    private class SearchNode implements Comparable<SearchNode>
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
        
        public int compareTo(SearchNode that) {
            int thisPri = this.currentBoard.hamming() + this.moveCount;
            int thatPri = that.currentBoard.hamming() + that.moveCount;
            
            if (thisPri < thatPri)
                return -1;
            else if (thisPri > thatPri)
                return 1;
            else
                return 0;
        }        
    }
}
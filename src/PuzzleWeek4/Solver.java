package PuzzleWeek4;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Viktor on 13.7.2017..
 */
public class Solver {
    private Move lastMove;

    private class Move implements Comparable<Move>{
        public Move parent;
        public int numberOfMoves = 0;
        public Board board;
        public Move(Move parent, Board board, int numberOfMoves){
            this.parent = parent;
            this.board = board;
            this.numberOfMoves = numberOfMoves;
        }
        public int manhattan(){
            return board.manhattan()+numberOfMoves;
        }
        public int compareTo(Move move){
            return this.manhattan() - move.manhattan();
        }
    }

    public Solver(Board initial){         // find a solution to the initial board (using the A* algorithm)
        Move original = new Move(null,initial,0);
        Move twin = new Move(null,initial.twin(),0);
        lastMove = original;

        MinPQ<Move> priorityQue = new MinPQ<>();
        MinPQ<Move> twinPrioriyQue = new MinPQ<>();

        priorityQue.insert(original);
        twinPrioriyQue.insert(twin);

        while(!lastMove.board.isGoal()){
            nextMove(twinPrioriyQue);
            if(lastMove.board.isGoal()){
                lastMove = null;
                break;
            }
            nextMove(priorityQue);

        }

    }
    private void nextMove(MinPQ<Move> priorityQue){
        Move lastVisited = priorityQue.delMin();
        int movesMade = lastVisited.numberOfMoves + 1;
        for(Board neighbor : lastVisited.board.neighbors()){
            if(lastVisited.parent == null || !lastVisited.parent.board.equals(neighbor)){
                Move nextPossible = new Move(lastVisited,neighbor,movesMade);
                priorityQue.insert(nextPossible);
            }
        }
        lastMove = lastVisited;
    }
    public boolean isSolvable(){ // is the initial board solvable?

        return lastMove != null;
    }
    public int moves(){ // min number of moves to solve initial board; -1 if unsolvable
        if(isSolvable()){
            return lastMove.numberOfMoves;
        }
        return -1;
    }

    public Iterable<Board> solution(){// sequence of boards in a shortest solution; null if unsolvable
        if(!isSolvable()){
            return null;
        }
        ArrayList<Board> solution = new ArrayList<>();
        Move move = lastMove;
        while(move != null){
            solution.add(move.board);
            move = move.parent;
        }
        Collections.reverse(solution);
        return solution;
    }
    public static void main(String[] args){
            // create initial board from file
//            In in = new In(args[0]);
//            int n = in.readInt();
//            int[][] blocks = new int[n][n];
//            for (int i = 0; i < n; i++)
//                for (int j = 0; j < n; j++)
//                    blocks[i][j] = in.readInt();
//            Board initial = new Board(blocks);
//
//            // solve the puzzle
//            Solver solver = new Solver(initial);
//
//            // print solution to standard output
//            if (!solver.isSolvable())
//                StdOut.println("No solution possible");
//            else {
//                StdOut.println("Minimum number of moves = " + solver.moves());
//                for (Board board : solver.solution())
//                    StdOut.println(board);
//            }

    } // solve a slider puzzle (given below)
}

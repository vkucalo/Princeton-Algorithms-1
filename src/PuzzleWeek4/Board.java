package PuzzleWeek4;

import java.util.ArrayList;

/**
 * Created by Viktor on 13.7.2017..
 */
public class Board {
    private int [][] board;
    private int dimension;
    private static int [][] goal;
    public Board(int[][] blocks){
        // construct a board from an n-by-n array of blocks
        // (where blocks[i][j] = block in row i, column j)
        board = copy(blocks);
        dimension();
    }
    public int dimension(){// board dimension n
        dimension = board.length;
        return dimension;
    }
    public int hamming() {// number of blocks out of place
        int priority = 0;
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                if(board[i][j] == 0){
                    continue;
                }
                if(board[i][j] != ((i*dimension) + j+1)){
                    priority++;
                }
            }
        }
        return priority;
    }
    public int manhattan(){// sum of Manhattan distances between blocks and goal
        int priority = 0;
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                priority += manhattanDistance(board[i][j], i, j);
            }
        }
        return priority;
    }

    private int manhattanDistance(int number, int i, int j){
         if(number == 0){
             return 0;
         }
         int goalRow = (number - 1) / dimension;
         int goalCol = (number - 1) % dimension;
         return Math.abs(i-goalRow) + Math.abs(j-goalCol);

    }
    public boolean isGoal(){// is this board the goal board?
        if(board[dimension-1][dimension-1] != 0){
            return false;
        }
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                if(i == dimension-1 && j == dimension-1){
                    break;
                }
                if(board[i][j] != ((i*dimension) + j+1)){
                    return false;
                }
            }
        }
        return true;
    }
    private void setGoal(){
        goal = new int [dimension][dimension];
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                goal[i][j] = (i * dimension) + j + 1;
            }
        }
    }
    public Board twin(){ // a board that is obtained by exchanging any pair of blocks
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension-1; j++){
                if(board[i][j] != 0 && board[i][j+1] != 0){
                    Board twin = new Board(swap(i, j, i,j+1));
                    return twin;
                }
            }
        }
        return null;
    }
    private int[][] swap(int oldI, int oldJ, int newI, int newJ){
        int[][] board = copy(this.board);
        int buffer = board[oldI][oldJ];
        board[oldI][oldJ] = board[newI][newJ];
        board[newI][newJ] = buffer;
        return board;
    }
    private int [][] copy(int [][] old){
        int [][] copy = new int[old.length][old.length];
        for(int i = 0; i < old.length; i++){
            for(int j = 0; j < old.length; j++){
                copy[i][j] = old[i][j];
            }
        }
        return copy;
    }
    public boolean equals(Object y){ // does this board equal y?
        if(y == null){
            return false;
        }
        if (!(y instanceof Board)){
            return false;
        }
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                if(((Board) y).board[i][j] != this.board[i][j])
                    return false;
            }
        }
        return true;

    }
    public Iterable<Board> neighbors(){   // all neighboring boards
        ArrayList<Board> neighbors = new ArrayList<>();
        int spaceRow = spaceLocation(board)[0];
        int spaceCol = spaceLocation(board)[1];
        if (spaceRow > 0){
            neighbors.add(new Board(swap(spaceRow,spaceCol,spaceRow-1,spaceCol)));
        }
        if(spaceCol > 0){
            neighbors.add(new Board(swap(spaceRow,spaceCol,spaceRow,spaceCol-1)));
        }
        if (spaceRow < dimension-1){
            neighbors.add(new Board(swap(spaceRow,spaceCol,spaceRow+1,spaceCol)));
        }
        if (spaceCol < dimension-1){
            neighbors.add(new Board(swap(spaceRow,spaceCol,spaceRow,spaceCol+1)));
        }
        return neighbors;
    }
    private int[] spaceLocation(int [][] board){
        int [] location = new int [2];
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                if(board[i][j] == 0){
                    location[0] = i;
                    location[1] = j;
                    return location;
                }
            }
        }
        return location;
    }
    public String toString(){// string representation of this board (in the output format specified below)
        StringBuilder sb = new StringBuilder();
        sb.append(this.dimension + "\n");
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                sb.append(board[i][j] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args){
//        int [][] t = {{0,1,3},{4,8,2},{7,6,5}};
//        Board test = new Board(t);
//        System.out.println("hamming = " + test.hamming());
//        System.out.println("man = "+ test.manhattan());
//        System.out.println(test);
//        test.neighbors();
    } // unit tests (not graded)
}

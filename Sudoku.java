import java.util.Scanner;

public class SudokuBoard
{
    //instance variable
    int[][] board;
    
    //constructor
    public SudokuBoard(int[][] board) {
        this.board = board;
    }
    
    //stores user's guess
    public void makeGuess(int guess, int row, int column) {
        board[row-1][column-1] = guess;
    }

    //makes sure each row has no duplicate values
    public boolean isValidRow(int row){
        for(int i = 0; i < board[row].length; i++) {
            for(int j = i+1; j < board[row].length; j++) {
                if(board[row][i] != 0 && board[row][i] == board[row][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    //makes sure each column has no duplicate values
    public boolean isValidColumn(int column) {
        for(int i = 0; i < board.length; i++) {
            for(int j = i+1; j < board.length; j++) {
                if(board[i][column] != 0 && board[i][column] == board[j][column]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    //makes sure each box has no duplicate values
    public boolean isValidBox(int box) {
        int rowBound = 0;
        int columnBound = 0;
        
        
        //find rowBound & columnBound depending on box
        //rowBound & columnBound dictate bounds in board array that it will iterate through 

       if (box >= 0 && box <= 2) {
           rowBound = 0;
       }
       if (box >= 3 && box <= 5) {
           rowBound = 3;
       }
       if (box >= 6 && box <= 8) {
           rowBound = 6;
       }
       
       if (box % 3 == 0) {
           columnBound = 0;
       } 
       if (box % 3 == 1) {
           columnBound = 3;
       }
       if (box % 3 == 2) {
           columnBound = 6;
       }
        
        int[][] prevElements = new int[3][3];
        
        //iterate through each cell in the box 
        for (int r=rowBound; r < (rowBound+3); r++) {
            for(int c=columnBound; c < (columnBound+3); c++) {
                //iterate through prevElements
                for (int i=0; i < prevElements.length; i++) {
                    for (int j=0; j < prevElements[i].length; j++) {
                        //check if the cell number is in the prevElements array
                        if(board[r][c] != 0 && board[r][c] == prevElements[i][j]) {
                            return false;
                        }
                    }
                }
                //at the end of the iteration, before moving on to the next element
                //the cell value in this iteration becomes part of prevElements
                
                //(hardcoded to put the board array values into corresponding positions in prevElements array)
                
                int rval = r;
                int cval = c;
                if (r > 2 &  r < 6) {
                    rval = r-3;
                }
                if (c > 2 &  c < 6) {
                    cval = c-3;
                }
                
                if (r > 5 &  r < 9) {
                    rval= r-6;
                }
                
                if (c > 5 &  c < 9) {
                    cval = c-6;
                }
                
                prevElements[rval][cval] = board[r][c];
                
            }
        }
        return true;
    }
    
    public boolean isGameOver(){
        for(int i=0; i < board.length; i++) {
            for(int j=0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        
        for(int i=0; i < 9; i++) {
            if (isValidRow(i) == false || isValidColumn(i) == false || isValidBox(i) == false) {
                return false;
            }
        }
        
        return true;
    }
    
    public void printBoard() {
        System.out.println("-------------------------");
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[0].length; c++) {
                if(c == 0){
                    System.out.print("|");
                }
                if (c % 3 == 0) {
                    if(board[r][c] == 0){
                        System.out.print(" _ ");
                    }
                    else{
                        System.out.print(" " + board[r][c] + " ");
                    }
                }
                else{
                    if(board[r][c] == 0){
                        System.out.print("_ ");
                    }
                    else{
                        System.out.print(board[r][c] + " ");
                    }
                }
                if(c == 8){
                    System.out.println("|");
                }
                else if (c % 3 == 2) {
                    System.out.print("|");
                }
            }
            if(r % 3 == 2){
                System.out.println("-------------------------");
            }
        }
        System.out.println();
    }
}

public class SudokuGame {
    public static void main(String[] args)
    {
        //Soduku board initial state
        int[][] puzzle = new int[][]{
                {0, 7, 1, 0, 0, 0, 0, 0, 6},
                {0, 0, 9, 0, 0, 0, 8, 0, 0},
                {5, 0, 4, 9, 0, 0, 0, 7, 1},
                {0, 0, 0, 0, 0, 0, 9, 0, 0},
                {0, 0, 0, 4, 3, 0, 0, 0, 0},
                {7, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 0, 0, 0, 6, 0, 4, 3, 5},
                {0, 0, 3, 1, 4, 0, 0, 0, 8},
                {6, 0, 0, 0, 0, 7, 0, 0, 0}
        };
        
        SudokuBoard board = new SudokuBoard(puzzle);
        board.printBoard();
        Scanner in = new Scanner(System.in);
        while (board.isGameOver() == false) {
            //uses collectInput to collect the user's guess & its location
            int guess = collectInput(in, "guess");
            int rowVal = collectInput(in, "row");
            int columnVal = collectInput(in, "column");
            board.makeGuess(guess, rowVal, columnVal);
            board.printBoard();
        }
        System.out.println("Good game!");
    }
    
    //makes sure that user's input is valid & returns the input
    public static int collectInput(Scanner input, String inputType){
        System.out.println("Enter " + inputType + ": ");
        if (input.hasNextInt() == false) {
            System.out.println("Input must be a number!");
            return -1;
        }
        int num = input.nextInt();
        if (num < 1 || num > 9) {
            System.out.println("Invalid " + inputType + "! Must be between 1 and 9.");
            System.out.println();
            return -1;
        }
        return num;
    }
}
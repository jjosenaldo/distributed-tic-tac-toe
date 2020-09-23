package model;

import java.io.Serializable;

public class TicTacToe implements Serializable {
    private final String[][] board;
    
    public TicTacToe(){
        this.board = new String[3][3];
        clear();
    }
    
    public final void clear(){
        for(int row = 0; row < 3; ++row){
            for(int col = 0; col < 3; ++col){
                board[row][col] = "";
            }
        }
    }
    
    public String getLabelAt(int row, int col){
        return board[row][col];
    }
    
    /**
     * 
     * @param row
     * @param col
     * @param label
     * @return true if the play was valid, false otherwise
     */
    public boolean applyPlayIfValid(int row, int col, String label){
        if(row >= 3 || col >= 3 || row < 0 || col < 0 || !board[row][col].isEmpty() || label.isEmpty()){
            return false;
        } else{
            board[row][col] = label;
            return true;
        }
    }
    
    /**
     * Gets the winner label if the game has ended.
     * @return the label of the winner player if the game has ended with a winner,
     *         the empty string if the game has ended without a winner,
     *         null if the game has not ended
     */
    public String getWinnerLabelIfGameHasEnded(){
        for(int rowOrCol = 0; rowOrCol < 3; ++rowOrCol){
            String possibleHorizontallWinner = board[rowOrCol][0];
            
            if(!possibleHorizontallWinner.isEmpty()){
                if(board[rowOrCol][1].equals(possibleHorizontallWinner) && 
                    board[rowOrCol][2].equals(possibleHorizontallWinner)){
                    return possibleHorizontallWinner;
                }
            }
            
            String possibleVerticalWinner = board[0][rowOrCol];
            
            if(!possibleVerticalWinner.isEmpty()){
                if(board[1][rowOrCol].equals(possibleVerticalWinner) && 
                    board[2][rowOrCol].equals(possibleVerticalWinner)){
                    return possibleVerticalWinner;
                }
            }
        }
        
        for(int row = 0; row < 3; ++row)
            for(int col = 0; col < 3; ++col)
                if(board[row][col].isEmpty())
                    return null;
        
        return "";
    }
    
    public int[][] getWinCoordinates(){
        for(int rowOrCol = 0; rowOrCol < 3; ++rowOrCol){
            String possibleHorizontallWinner = board[rowOrCol][0];
            
            if(!possibleHorizontallWinner.isEmpty()){
                if(board[rowOrCol][1].equals(possibleHorizontallWinner) && 
                    board[rowOrCol][2].equals(possibleHorizontallWinner)){
                    return new int[][]{{rowOrCol, 0},{rowOrCol, 1},{rowOrCol, 2}};
                }
            }
            
            String possibleVerticalWinner = board[0][rowOrCol];
            
            if(!possibleVerticalWinner.isEmpty()){
                if(board[1][rowOrCol].equals(possibleVerticalWinner) && 
                    board[2][rowOrCol].equals(possibleVerticalWinner)){
                    return new int[][]{{0, rowOrCol},{1, rowOrCol},{2, rowOrCol}};
                }
            }
        }
        
        return null;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author satan
 */
public class Board {
    private Square[][] squares;
    
    private static Board instance = null;
    
    private Board(){
        squares = new Square[3][3];
        for(int i = 0; i < 3; ++i) for(int j = 0; j < 3; ++j) squares[i][j] = Square.EMPTY;
    }
    
    public static Board getInstance(){
        if(instance == null)
            instance = new Board();
        
        return instance;
    }
    
    public Square[][] getSquares(){
        return squares;
    }
    
    public SquareCode setSquareAt(int i, int j, Square s){
        if(i < 0 || j < 0 || i > 2 || j > 2)
            return SquareCode.INVALID;
        
        switch (squares[i][j]) {
            case CROSS: {
                if(s == Square.CROSS) return SquareCode.OCCUPIED_BY_YOU;
                if(s == Square.CIRCLE) return SquareCode.OCCUPIED_BY_OPPONENT;
                break;
            }

            case CIRCLE:{
                if(s == Square.CIRCLE) return SquareCode.OCCUPIED_BY_YOU;
                if(s == Square.CROSS) return SquareCode.OCCUPIED_BY_OPPONENT;
                break;
            }
            
            case EMPTY:{
                if(s == Square.CIRCLE || s == Square.CROSS){
                    squares[i][j] = s;
                    return SquareCode.OK;
                }
                break;
            }
                
        }
        
        return SquareCode.INVALID;
    }
    
    @Override
    public String toString(){
        return squares[0][0] + " " + squares[0][1] + " " + squares[0][2] + 
               squares[1][0] + " " + squares[1][1] + " " + squares[1][2] + 
               squares[2][0] + " " + squares[2][1] + " " + squares[2][2]; 
    }
}

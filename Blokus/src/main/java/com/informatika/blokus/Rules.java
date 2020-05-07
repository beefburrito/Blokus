/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.informatika.blokus;

import javax.swing.JButton;

/**
 *
 * @author Eugene
 */
public class Rules {
    public boolean isValidMove;
    public boolean isPieceDiagonal;
    public boolean isOverlap;
    public JButton[][] playerGrid = new JButton[5][5];
    Pieces pieces = new Pieces();
    
    public JButton[] controlButtons = new JButton[6];
    

    public void showPieceGrid(int x, int y)
    {
     for (int i = 0; i < 5; i++){
         for (int j = 0 ; j < 5 ; j++ ){
             
             playerGrid[i][j] = new JButton();
         }
     }
     playerGrid[x][y].setEnabled(true);
    }
    public void playerControls(){
       JButton[] controlButtons;
        controlButtons = new JButton[6];
       controlButtons[0].setText("Rotate Left");
       controlButtons[1].setText("Rotate Right");
       controlButtons[2].setText("Flip Horizontal");
       controlButtons[3].setText("Flip Vertical");
       controlButtons[4].setText("Next Piece");
       controlButtons[5].setText("Previous Piece");
    }
    public void countScore(int[][]grid,int player)
    {
         
    }
    public void firstTurn(int[][]grid,int player){
        // legal places to place at first turn
        if (player == 1){
            grid[19][0]=1;
        } else{
            if (player == 2){
                grid[0][0]=2;
            }
            if (player == 3){
                grid[0][19]=3;
            }
            if (player == 4){
                grid[19][19]=4;
            }
        }
    }
    
    public void legalMoves(int[][]grid,int player){
        for (int i = 0 ; i < 20 ; i++){
            for (int j = 0 ; j < 20 ; j++){
                
            }
        }
    }
    
      public boolean usedPieceIndex(int [][] usedPiece, int index, int player){
        
        for (int i = 0 ; i < 4 ; i++){
            for (int j = 0 ; j < 21 ; j++){
                if (i+1 == player){
                    if (j== index && usedPiece[i][j] == 1){
                        return(false);
                    }
                }
            }
        }
        return(true);
        // if piece index is used then it disables it
    }
    
    public int minPieceIndex(int index , int [][] usedPiece, int player){
        index--;
        while(!usedPieceIndex(usedPiece,index,player)){
            index--;
            if (index < 0){
                index = 20;
            }
        }
        if (index < 0){
                return(20);
            }
        //add used pieceindex
        return(index);
    }
    public int maxPieceIndex(int index, int [][] usedPiece, int player){
        index++;
        while(!usedPieceIndex(usedPiece,index,player)){
            index++;
            if(index >= 20){
                index = 0;
            }
        }
        if (index >= 20){
            return(0);
        }
        return(index);
    }
}

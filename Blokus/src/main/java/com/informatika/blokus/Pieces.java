/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.informatika.blokus;
import java.util.*;
import java.awt.event.KeyEvent;

/**
 *
 * @author Eugene
 */
public class Pieces {
    
    public int num = 0;
    public int pieceNum = 2;
    public int [][] pieceMap = {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
    public int [][] playerRemainingPieces;
    /// To distinguish player's remainding pieces
    
    public void intializingPlayerRemainingPieces(){
        playerRemainingPieces[0][0] = 1;
        playerRemainingPieces[1][0] = 2;
        playerRemainingPieces[2][0] = 3;
        playerRemainingPieces[3][0] = 4;
        for (int i = 0; i < 4 ; i++){
            for (int j = 1 ; j <= 21;j++){
                playerRemainingPieces[i][j] = 1;
            }
        }
    }
    
    /// Block index 
    public void switchBlocks(int x){
        switch (x) {
            case 0:
                pieceMap[2][2] = 1;
                break;
            case 1:
                pieceMap[2][2] = 1;
                pieceMap[3][2] = 1;
                break;
            case 2:
                pieceMap[1][1] = 1;
                pieceMap[1][2] = 1;
                pieceMap[2][2] = 1;
                break;
            case 3:
                pieceMap[1][2] = 1;
                pieceMap[2][2] = 1;
                pieceMap[3][2] = 1;
                break;
            case 4:
                pieceMap[1][1] = 1;
                pieceMap[2][1] = 1;
                pieceMap[1][2] = 1;
                pieceMap[2][2] = 1;
                break;
            case 5:
                pieceMap[3][1] = 1;
                pieceMap[2][1] = 1;
                pieceMap[2][2] = 1;
                pieceMap[2][3] = 1;
                break;
            case 6:
                pieceMap[1][2] = 1;
                pieceMap[2][2] = 1;
                pieceMap[1][1] = 1;
                pieceMap[1][3] = 1;
                break;
            case 7:
                pieceMap[1][2] = 1;
                pieceMap[2][2] = 1;
                pieceMap[2][3] = 1;
                pieceMap[3][3] = 1;
                break;
            case 8:
                pieceMap[1][2] = 1;
                pieceMap[2][2] = 1;
                pieceMap[3][2] = 1;
                pieceMap[4][2] = 1;
                break;
            case 9:
                pieceMap[1][2] = 1;
                pieceMap[2][2] = 1;
                pieceMap[1][3] = 1;
                pieceMap[2][3] = 1;
                pieceMap[1][4] = 1;
                break;
            case 10:
                pieceMap[2][1] = 1;
                pieceMap[1][1] = 1;
                pieceMap[1][2] = 1;
                pieceMap[1][3] = 1;
                pieceMap[2][3] = 1;
                break;
            case 11:
                pieceMap[0][2] = 1;
                pieceMap[1][2] = 1;
                pieceMap[2][2] = 1;
                pieceMap[2][3] = 1;
                pieceMap[2][4] = 1;
                break;
            case 12:
                pieceMap[1][1] = 1;
                pieceMap[1][2] = 1;
                pieceMap[2][2] = 1;
                pieceMap[3][2] = 1;
                pieceMap[3][3] = 1;
                break;
            case 13:
                pieceMap[1][2] = 1;
                pieceMap[3][1] = 1;
                pieceMap[2][2] = 1;
                pieceMap[3][2] = 1;
                pieceMap[3][3] = 1;
                break;
            case 14:
                pieceMap[1][1] = 1;
                pieceMap[2][1] = 1;
                pieceMap[2][2] = 1;
                pieceMap[3][2] = 1;
                pieceMap[3][3] = 1;
                break;
            case 15:
                pieceMap[1][2] = 1;
                pieceMap[2][1] = 1;
                pieceMap[2][2] = 1;
                pieceMap[3][2] = 1;
                pieceMap[3][3] = 1;
                break;
            case 16:
                pieceMap[1][2] = 1;
                pieceMap[2][1] = 1;
                pieceMap[2][2] = 1;
                pieceMap[3][2] = 1;
                pieceMap[2][3] = 1;
                break;
            case 17:
                pieceMap[1][1] = 1;
                pieceMap[2][1] = 1;
                pieceMap[1][2] = 1;
                pieceMap[1][3] = 1;
                pieceMap[1][4] = 1;
                break;
            case 18:
                pieceMap[3][1] = 1;
                pieceMap[3][2] = 1;
                pieceMap[2][2] = 1;
                pieceMap[2][3] = 1;
                pieceMap[2][4] = 1;
                break;
            case 19:
                pieceMap[1][1] = 1;
                pieceMap[1][2] = 1;
                pieceMap[2][3] = 1;
                pieceMap[1][3] = 1;
                pieceMap[1][4] = 1;
                break;
            case 20:
                pieceMap[2][0] = 1;
                pieceMap[2][1] = 1;
                pieceMap[2][2] = 1;
                pieceMap[2][3] = 1;
                pieceMap[2][4] = 1;
                break;
            default:
                break;
        }
    }
    
    public void pieceClear(){
        for (int i =0 ; i < 5 ; i++){
            for (int j = 0 ; j < 5 ; j++){
                pieceMap[i][j]=0;
            }
        }
    }
    
    public void usedBlocks(int block){
        
    }
    
    
    public void rotateRight(){
        int[][] rotatedMatrix = new int[5][5];
        for (int i = 0; i < 5; i++){
            for (int j = 0 ; j < 5 ; j++){
                rotatedMatrix[j][(5-1)-i]=pieceMap[i][j];
            }
        }
        for (int i = 0; i < 5; i++){
            for (int j = 0 ; j < 5 ; j++){
                pieceMap[i][j] = rotatedMatrix[i][j];
            }
        }
    }
    public void rotateLeft(){
        int[][] rotatedMatrix = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                rotatedMatrix[(5-1)-j][i] = pieceMap[i][j]; 
            }
        }
        for (int i = 0; i < 5; i++){
            for (int j = 0 ; j < 5 ; j++){
                pieceMap[i][j] = rotatedMatrix[i][j];
            }
        }
    }
    
    public void flipVertical(){
        Collections.reverse(Arrays.asList(pieceMap));
    }
    
    public void flipHorizontal(){
        int[][]flip = new int [5][5];
        int k;
        for (int i = 0; i < 5;i++){
            k = 4;
            for (int j = 0 ; j<5 ; j++){
                if (pieceMap[i][j]==1){
                    flip[i][k] = pieceMap[i][j];
                }
                k--;
            }

        }
        for (int i = 0; i < 5;i++){
            for (int j = 0 ; j<5 ; j++){
                pieceMap[i][j] = flip[i][j];
            }
        }
    }
    

    
    public void placePiece(){
        
    }

//    public int[][] piece21 = {{1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0}}; 
}
    


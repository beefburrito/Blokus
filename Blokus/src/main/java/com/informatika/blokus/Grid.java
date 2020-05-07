/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.informatika.blokus;

import java.awt.Color; 
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Eugene
 */
public class Grid  {
    
    Player player = new Player();
    private int usedIndex;
    public int currentPlayer = 1;
    public int blueIndex = 0;
    public int redIndex = 0;
    public int yellowIndex = 0;
    public int greenIndex = 0;
    public boolean blueSkip = false;
    public boolean greenSkip = false;
    public boolean redSkip = false;
    public boolean yellowSkip = false;
    public boolean newTurn;
    public int bluePoint;
    public int redPoint;
    public int yellowPoint;
    public int greenPoint;
    String winMessage;
    
    //current player values
    //1 = blue
    //2 = green
    //3 = red
    //4 = yellow
    
    public int[][] usedPieceIndex ={
                                    {0,0,0,0,0,0,0,0,0,0,
                                    0,0,0,0,0,0,0,0,0,0,0},
                                    {
                                    0,0,0,0,0,0,0,0,0,0,
                                    0,0,0,0,0,0,0,0,0,0,0},
                                    {
                                    0,0,0,0,0,0,0,0,0,0,
                                    0,0,0,0,0,0,0,0,0,0,0
                                    },
                                    {
                                    0,0,0,0,0,0,0,0,0,0,
                                    0,0,0,0,0,0,0,0,0,0,0
                                    }
                                    };
    public boolean firstTurn = true;
    // to determine whether or not it's the first turn for each players
    public int[][] gridCellValue = new int[20][20];
    public JButton[][] gridCell = new JButton[20][20];
    Pieces pieces = new Pieces();
    private int [][] piece = {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
    private final int OFFSETX = 2;
    private final int OFFSETY = 2;
    
//    public void __init__(boolean isValidMove){
//        isValidMove = player.isValidMove;
//    }
    public String winner(){
        if (bluePoint > redPoint && bluePoint > greenPoint && bluePoint > yellowPoint)
            winMessage="\nBlue is the winner";
       else if (redPoint > bluePoint && redPoint > greenPoint && redPoint > yellowPoint)
            winMessage="\nRed is the winner";
       else if (greenPoint > bluePoint && greenPoint > redPoint && greenPoint > yellowPoint)
            winMessage="\nGreen is the winner";
       else if (yellowPoint > bluePoint && yellowPoint > redPoint && yellowPoint > greenPoint)
            winMessage="\nYellow is the winner";
        else
            winMessage="\nDraw";
        return(winMessage);
    }
    public void createGrid(int x, int y, boolean isMainBoard){
        gridCell[x][y] = new JButton();
        if (isMainBoard){
            initializeGridValue();
//            pieces.intializingPlayerRemainingPieces();
            gridCell[x][y].setEnabled(true);
            
        }
        else
            gridCell[x][y].setEnabled(false);
    }
    public void initializeGridValue(){
        for (int i = 0; i < 20 ; i++){
            for (int j = 0 ; j < 20 ; j++){
                gridCellValue[i][j] = 0;           
            }
        }
    }
    public void countScore(){
        for (int i = 0 ; i < 20; i++){
            for (int j = 0 ; j < 20 ; j++){
                if (gridCellValue[i][j]==1){
                    bluePoint++;
                }
                else{
                    if (gridCellValue[i][j]==2){
                        greenPoint++;
                    }
                    if (gridCellValue[i][j]==3){
                        redPoint++;
                    }
                    if (gridCellValue[i][j]==4){
                        yellowPoint++;
                    }
                }
            }
        }
    }
    public void finalScore(){
        
        countScore();
        winner();
       
    }
    /// when player clicks at the grid
    public void gridhandler(int x, int y,int [][]map){
        //map is the piece map
        gridCell[x][y].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                //if (allSkip()){
                    //end game
                    //finalScore();
                //}
                if(newTurn){
                    for (int i = 0 ; i < 5 ; i ++){
                        for (int j = 0 ; j < 5 ; j++){
                            map[i][j]=0;
                        }
                    }
                }

                player.isValidMove = false;
                player.isOverlap = false;
                //condition for first turn
                if (firstTurn){
                    for (int i = 0 ; i < 5 ; i++){
                        for (int j = 0 ; j< 5 ; j++){
                            if (x+i-OFFSETX == 19 && y+j-OFFSETY == 0 && map[i][j]==1 && currentPlayer ==1){
                                player.isValidMove = true;
                                // x+i-offsetx and y+j-offsety is where the mouse handler is pointing towards
                                break;
                            }
                            else {
                                if (x+i-OFFSETX == 0 && y+j-OFFSETY == 0 && map[i][j]==1 && currentPlayer ==2){
                                    player.isValidMove = true;

                                    break;
                                }
                                if (x+i-OFFSETX == 0 && y+j-OFFSETY == 19 && map[i][j]==1 && currentPlayer ==3){
                                    player.isValidMove = true;

                                    break;
                                }
                                if (x+i-OFFSETX == 19 && y+j-OFFSETY == 19 && map[i][j]==1 && currentPlayer ==4){
                                    player.isValidMove = true;

                                    break;
                                }
                            }
                        }
                    }
                } else{
                    player.isValidMove = true;
                    player.isPieceDiagonal = false;
                    for (int k = 0 ; k < 20 ; k++){
                        System.out.println();
                        for (int l = 0 ; l < 20; l++){
                            if (currentPlayer == 1){
                                // check the whole board for the grid cell value
                                // after finding it must check if piece placed is diagonal to the value
                                // must also check if there are any pieces that are horizontal or vertical
                                // with the grid cell value
                                if (gridCellValue[k][l] == 1 ){
                                    for (int i = 0 ; i < 5 ; i++){
                                        for (int j = 0 ; j <5 ; j++){
                                            if (map[i][j]==1){
                                                // check for any horizontal and vertical blocks with the piece preview
                                                try{
                                                    if (gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY] == 1 || 
                                                            gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY] == 1 ||
                                                            gridCellValue[x+i-OFFSETX][y+j-OFFSETY + 1] == 1 ||
                                                            gridCellValue[x+i-OFFSETX][y+j-OFFSETY - 1] == 1)
                                                    {
                                                        player.isValidMove = false;
                                                        break;
                                                    }
                                                    if (gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY + 1] == 1 || 
                                                            gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY - 1] == 1 ||
                                                            gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY + 1] == 1 ||
                                                            gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY - 1] == 1){
                                                        player.isPieceDiagonal = true;
                                                    }
                                                    if (gridCellValue[x+i-OFFSETX][y+j-OFFSETY] >= 1){
                                                        player.isOverlap = true;
                                                        break;
                                                    }
                                                }
                                                catch(Exception r){
                                                    if ((x+i-OFFSETX == 0 && 
                                                            (gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY + 1] == 1 
                                                            || gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY - 1] == 1 ))
                                                            ||
                                                            (x + i - OFFSETX == 19 && 
                                                            (gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY - 1] == 1 ||
                                                            gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY + 1] == 1))
                                                            ||
                                                            (y + j - OFFSETY == 0 &&
                                                            (gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY + 1] == 1
                                                            || gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY + 1] == 1))
                                                            ||
                                                            (y + j - OFFSETY == 19 &&
                                                            (gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY - 1] == 1 
                                                            || gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY - 1] == 1))){
                                                        
                                                        player.isPieceDiagonal = true;
                                                    }
                                                    
                                                }
                                                
                                            }
                                        }
                                    }
                                }
                            } else{
                                if (currentPlayer == 2){
                                    if (gridCellValue[k][l] == 2 ){
                                    for (int i = 0 ; i < 5 ; i++){
                                        for (int j = 0 ; j <5 ; j++){
                                            if (map[i][j]==1){
                                                // check for any horizontal and vertical blocks with the piece preview
                                                try {
                                                    if (gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY] == 2 || 
                                                            gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY] == 2 ||
                                                            gridCellValue[x+i-OFFSETX][y+j-OFFSETY + 1] == 2 ||
                                                            gridCellValue[x+i-OFFSETX][y+j-OFFSETY - 1] == 2){
                                                        player.isValidMove = false;
                                                        break;
                                                    }

                                                    if (gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY + 1] == 2 || 
                                                            gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY - 1] == 2 ||
                                                            gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY + 1] == 2 ||
                                                            gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY - 1] == 2){
                                                        player.isPieceDiagonal = true;
                                                    }
                                                    if (gridCellValue[x+i-OFFSETX][y+j-OFFSETY] >= 1){
                                                        player.isOverlap = true;
                                                        break;
                                                    }
                                                }
                                                catch(Exception r){
                                                    if ((x+i-OFFSETX == 0 && 
                                                            (gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY + 1] == 2 
                                                            || gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY - 1] == 2 ))
                                                            ||
                                                            (x + i - OFFSETX == 19 && 
                                                            (gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY - 1] == 2 ||
                                                            gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY + 1] == 2))
                                                            ||
                                                            (y + j - OFFSETY == 0 &&
                                                            (gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY + 1] == 2
                                                            || gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY + 1] == 2))
                                                            ||
                                                            (y + j - OFFSETY == 19 &&
                                                            (gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY - 1] == 2 
                                                            || gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY - 1] == 2))){
                                                        
                                                        player.isPieceDiagonal = true;
                                                    }
                                                    
                                                }
                                            }
                                        }
                                    }
                                }
                                }
                                if (currentPlayer == 3){
                                    if (gridCellValue[k][l] == 3 ){
                                    for (int i = 0 ; i < 5 ; i++){
                                        for (int j = 0 ; j <5 ; j++){
                                            if (map[i][j]==1){
                                                // check for any horizontal and vertical blocks with the piece preview
                                                try {
                                                    if (gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY] == 3 || 
                                                            gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY] == 3 ||
                                                            gridCellValue[x+i-OFFSETX][y+j-OFFSETY + 1] == 3 ||
                                                            gridCellValue[x+i-OFFSETX][y+j-OFFSETY - 1] == 3){
                                                        player.isValidMove = false;
                                                        break;
                                                    }
                                                    if (gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY + 1] == 3 || 
                                                            gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY - 1] == 3 ||
                                                            gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY + 1] == 3 ||
                                                            gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY - 1] == 3){
                                                        player.isPieceDiagonal = true;
                                                    }
                                                    if (gridCellValue[x+i-OFFSETX][y+j-OFFSETY] >= 1){
                                                        player.isOverlap = true;
                                                        break;
                                                    }
                                                }
                                                catch(Exception r){
                                                    if ((x+i-OFFSETX == 0 && 
                                                            (gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY + 1] == 3 
                                                            || gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY - 1] == 3 ))
                                                            ||
                                                            (x + i - OFFSETX == 19 && 
                                                            (gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY - 1] == 3 ||
                                                            gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY + 1] == 3))
                                                            ||
                                                            (y + j - OFFSETY == 0 &&
                                                            (gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY + 1] == 3
                                                            || gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY + 1] == 3))
                                                            ||
                                                            (y + j - OFFSETY == 19 &&
                                                            (gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY - 1] == 3 
                                                            || gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY - 1] == 3))){
                                                        
                                                        player.isPieceDiagonal = true;
                                                    }
                                                    
                                                }
                                            }
                                        }
                                    }
                                }
                                }
                                if (currentPlayer == 4){
                                    if (gridCellValue[k][l] == 4 ){
                                    for (int i = 0 ; i < 5 ; i++){
                                        for (int j = 0 ; j <5 ; j++){
                                            if (map[i][j]==1){
                                                // check for any horizontal and vertical blocks with the piece preview
                                                try {
                                                    if (gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY] == 4 || 
                                                            gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY] == 4 ||
                                                            gridCellValue[x+i-OFFSETX][y+j-OFFSETY + 1] == 4 ||
                                                            gridCellValue[x+i-OFFSETX][y+j-OFFSETY - 1] == 4){
                                                        player.isValidMove = false;
                                                        break;
                                                    }
                                                    if (gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY + 1] == 4 || 
                                                            gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY - 1] == 4 ||
                                                            gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY + 1] == 4 ||
                                                            gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY - 1] == 4){
                                                        player.isPieceDiagonal = true;
                                                    }
                                                    if (gridCellValue[x+i-OFFSETX][y+j-OFFSETY] >= 1){
                                                        player.isOverlap = true;
                                                        break;
                                                    }
                                                }
                                                catch(Exception r){
                                                    if ((x+i-OFFSETX == 0 && 
                                                            (gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY + 1] == 4 
                                                            || gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY - 1] == 4 ))
                                                            ||
                                                            (x + i - OFFSETX == 19 && 
                                                            (gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY - 1] == 4 ||
                                                            gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY + 1] == 4))
                                                            ||
                                                            (y + j - OFFSETY == 0 &&
                                                            (gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY + 1] == 4
                                                            || gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY + 1] == 4))
                                                            ||
                                                            (y + j - OFFSETY == 19 &&
                                                            (gridCellValue[x+i-OFFSETX + 1][y+j-OFFSETY - 1] == 4 
                                                            || gridCellValue[x+i-OFFSETX - 1][y+j-OFFSETY - 1] == 4))){
                                                        
                                                        player.isPieceDiagonal = true;
                                                    }
                                                    
                                                }
                                            }
                                        }
                                    }
                                }
                                }
                            }
                        }
                    }
                }
                
                for (int i = 0 ; i < 5 ; i++){
                    /// places the pieces
                    for (int j = 0 ; j < 5 ; j++){                      
                        if (map[i][j]==1){
                            if ((player.isValidMove == true && firstTurn) || (player.isValidMove && player.isPieceDiagonal)){
                                if(currentPlayer == 1){
                                
                                    gridCell[x+i-OFFSETX][y+j-OFFSETY].setBackground(Color.BLUE);
                                    gridCellValue[x+i-OFFSETX][y+j-OFFSETY] = 1;
                                    gridCell[x+i-OFFSETX][y+j-OFFSETY].setEnabled(false);
                                    usedIndex = blueIndex;
                                } 
                            
                            else{
                                if (currentPlayer == 2){
                                    gridCell[x+i-OFFSETX][y+j-OFFSETY].setBackground(Color.GREEN);
                                    gridCellValue[x+i-OFFSETX][y+j-OFFSETY] = 2;
                                    gridCell[x+i-OFFSETX][y+j-OFFSETY].setEnabled(false); 
                                    usedIndex = greenIndex;
                                }
                                if (currentPlayer == 3){
                                    gridCell[x+i-OFFSETX][y+j-OFFSETY].setBackground(Color.RED);
                                    gridCellValue[x+i-OFFSETX][y+j-OFFSETY] = 3;
                                    gridCell[x+i-OFFSETX][y+j-OFFSETY].setEnabled(false); 
                                    usedIndex = redIndex;
                                }
                                if (currentPlayer == 4){
                                    gridCell[x+i-OFFSETX][y+j-OFFSETY].setBackground(Color.YELLOW);
                                    gridCellValue[x+i-OFFSETX][y+j-OFFSETY] = 4;
                                    gridCell[x+i-OFFSETX][y+j-OFFSETY].setEnabled(false);
                                    usedIndex = yellowIndex;
                                }
                                
                            }
                            }
                        }
                    }       
                }
                if (firstTurn && player.isValidMove == true){
                    updateUsedPiece(currentPlayer-1,usedIndex);
                    
                    nextTurn();
                    player.isValidMove = false;
                }
                else{
                    if (player.isValidMove == true && player.isPieceDiagonal == true && !player.isOverlap){
                        updateUsedPiece(currentPlayer-1,usedIndex);
                        nextTurn();
                        player.isValidMove = false;
                        
                        
                    }
                }
                
            }
        });
    }
    
    public void potentialMoves(){
        for (int i = 0; i < 20 ; i++){
            for (int j = 0 ; j < 20 ; j++){
                if (currentPlayer == 1 && gridCellValue[i][j]==0 && !firstTurn){
                    try{
                        //piece is diagonal
                        if (gridCellValue[i+1][j+1]==1 ||
                                gridCellValue[i+1][j-1]==1 ||
                                gridCellValue[i-1][j+1]==1 ||
                                gridCellValue[i-1][j-1] == 1){
                            
                            if (gridCellValue[i+1][j]!=1 &&
                                   gridCellValue[i-1][j]!=1&&
                                    gridCellValue[i][j+1] !=1 &&
                                    gridCellValue[i][j-1]!=1){
                                gridCell[i][j].setBackground(Color.GRAY);
                            }
                        }
                    }
                    catch(Exception e){
                        if ((i == 0 && 
                            (gridCellValue[i+ 1][j + 1] == 1 
                            || gridCellValue[i+ 1][j- 1] == 1 ))
                            ||
                            (i == 19 && 
                            (gridCellValue[i- 1][j- 1] == 1 ||
                            gridCellValue[i - 1][j + 1] == 1))
                            ||
                            (j  == 0 &&
                            (gridCellValue[i- 1][j] == 1
                            || gridCellValue[i+ 1][j + 1] == 1))
                            ||
                            (j == 19 &&
                            (gridCellValue[i + 1][j- 1] == 1 
                            || gridCellValue[i- 1][j- 1] == 1))){
                            if ((i == 0 && (gridCellValue[i+1][j]!=1 &&
                                    gridCellValue[i][j+1]!=1    &&
                                    gridCellValue[i][j-1]!=1))
                                    ||
                                    (i == 19 && (gridCellValue[i-1][j]!=1 &&
                                    gridCellValue[i][j+1]!=1 &&
                                    gridCellValue[i][j-1]!=1))
                                    || 
                                    (j == 0 && (gridCellValue[i][j+1] != 1 &&
                                    gridCellValue[i+1][j]!=1 &&
                                    gridCellValue[i-1][j]!=1))
                                    || 
                                    (j == 19 && (gridCellValue[i][j-1] != 1 &&
                                    gridCellValue[i+1][j]!=1) &&
                                    gridCellValue[i-1][j]!=1)
                                    ){
                                gridCell[i][j].setBackground(Color.GRAY);
                            }
                            
                    }
                    }
                } else {
                    if (currentPlayer == 1 && gridCellValue[i][j]==0 && firstTurn){
                        gridCell[19][0].setBackground(Color.GRAY);
                    }
                    if (currentPlayer == 2 && gridCellValue[i][j]==0 && !firstTurn){
                    try{
                        //piece is diagonal
                        if (gridCellValue[i+1][j+1]==2 ||
                                gridCellValue[i+1][j-1]==2 ||
                                gridCellValue[i-1][j+1]==2 ||
                                gridCellValue[i-1][j-1] == 2){
                            
                            if (gridCellValue[i+1][j]!=2 &&
                                   gridCellValue[i-1][j]!=2 &&
                                    gridCellValue[i][j+1] !=2 &&
                                    gridCellValue[i][j-1]!=2){
                                gridCell[i][j].setBackground(Color.GRAY);
                            }
                        }
                    }
                    catch(Exception e){
                        if ((i == 0 && 
                            (gridCellValue[i+ 1][j + 1] == 2 
                            || gridCellValue[i+ 1][j- 1] == 2 ))
                            ||
                            (i == 19 && 
                            (gridCellValue[i- 1][j- 1] == 2 ||
                            gridCellValue[i - 1][j + 1] == 2))
                            ||
                            (j  == 0 &&
                            (gridCellValue[i- 1][j+1] == 2
                            || gridCellValue[i+ 1][j + 1] == 2))
                            ||
                            (j == 19 &&
                            (gridCellValue[i + 1][j- 1] == 2 
                            || gridCellValue[i- 1][j- 1] == 2))){
                            if ((i == 0 && (gridCellValue[i+1][j]!=2 &&
                                    gridCellValue[i][j+1]!=2    &&
                                    gridCellValue[i][j-1]!=2))
                                    ||
                                    (i == 19 && (gridCellValue[i-1][j]!=2 &&
                                    gridCellValue[i][j+1]!=2 &&
                                    gridCellValue[i][j-1]!=2))
                                    || 
                                    (j == 0 && (gridCellValue[i][j+1] != 2 &&
                                    gridCellValue[i+1][j]!=2 &&
                                    gridCellValue[i-1][j]!=2))
                                    || 
                                    (j == 19 && (gridCellValue[i][j-1] != 2 &&
                                    gridCellValue[i+1][j]!=2) &&
                                    gridCellValue[i-1][j]!=2)
                                    ){
                                gridCell[i][j].setBackground(Color.GRAY);
                            }
                            
                    }
                    }
                }
                    if (currentPlayer == 2 && gridCellValue[i][j]==0 && firstTurn){
                        gridCell[0][0].setBackground(Color.GRAY);
                    }
                    if (currentPlayer == 3 && gridCellValue[i][j]==0 && !firstTurn){
                    try{
                        //piece is diagonal
                        if (gridCellValue[i+1][j+1]==3 ||
                                gridCellValue[i+1][j-1]==3 ||
                                gridCellValue[i-1][j+1]==3 ||
                                gridCellValue[i-1][j-1] == 3){
                            
                            if (gridCellValue[i+1][j]!=3 &&
                                   gridCellValue[i-1][j]!=3 &&
                                    gridCellValue[i][j+1] !=3 &&
                                    gridCellValue[i][j-1]!=3){
                                gridCell[i][j].setBackground(Color.GRAY);
                            }
                        }
                    }
                    catch(Exception e){
                        if ((i == 0 && 
                            (gridCellValue[i+ 1][j + 1] == 3 
                            || gridCellValue[i+ 1][j- 1] == 3 ))
                            ||
                            (i == 19 && 
                            (gridCellValue[i- 1][j- 1] == 3 ||
                            gridCellValue[i - 1][j + 1] == 3))
                            ||
                            (j  == 0 &&
                            (gridCellValue[i- 1][j + 1] == 3
                            || gridCellValue[i+ 1][j + 1] == 3))
                            ||
                            (j == 19 &&
                            (gridCellValue[i + 1][j- 1] == 3 
                            || gridCellValue[i- 1][j- 1] == 3))){
                            if ((i == 0 && (gridCellValue[i+1][j]!=3 &&
                                    gridCellValue[i][j+1]!=3    &&
                                    gridCellValue[i][j-1]!=3))
                                    ||
                                    (i == 19 && (gridCellValue[i-1][j]!=3 &&
                                    gridCellValue[i][j+1]!=3 &&
                                    gridCellValue[i][j-1]!=3))
                                    || 
                                    (j == 0 && (gridCellValue[i][j+1] != 3 &&
                                    gridCellValue[i+1][j]!=3 &&
                                    gridCellValue[i-1][j]!=3))
                                    || 
                                    (j == 19 && (gridCellValue[i][j-1] != 3 &&
                                    gridCellValue[i+1][j]!=3) &&
                                    gridCellValue[i-1][j]!=3)
                                    ){
                                gridCell[i][j].setBackground(Color.GRAY);
                            }
                            
                    }
                    }
                }
                    if (currentPlayer == 3 && gridCellValue[i][j]==0 && firstTurn){
                        gridCell[0][19].setBackground(Color.GRAY);
                    }
                    if (currentPlayer == 4 && gridCellValue[i][j]==0 && !firstTurn){
                    try{
                        //piece is diagonal
                        if (gridCellValue[i+1][j+1]==4 ||
                                gridCellValue[i+1][j-1]==4 ||
                                gridCellValue[i-1][j+1]==4 ||
                                gridCellValue[i-1][j-1] == 4){
                            
                            if (gridCellValue[i+1][j]!=4 &&
                                   gridCellValue[i-1][j]!=4 &&
                                    gridCellValue[i][j+1] !=4 &&
                                    gridCellValue[i][j-1]!=4){
                                gridCell[i][j].setBackground(Color.GRAY);
                            }
                        }
                    }
                    catch(Exception e){
                        if ((i == 0 && 
                            (gridCellValue[i+ 1][j + 1] == 4 
                            || gridCellValue[i+ 1][j- 1] == 4 ))
                            ||
                            (i == 19 && 
                            (gridCellValue[i- 1][j- 1] == 4 ||
                            gridCellValue[i - 1][j + 1] == 4))
                            ||
                            (j  == 0 &&
                            (gridCellValue[i- 1][j+1] == 4
                            || gridCellValue[i+ 1][j + 1] == 4))
                            ||
                            (j == 19 &&
                            (gridCellValue[i + 1][j- 1] == 4 
                            || gridCellValue[i- 1][j- 1] == 4))){
                            if ((i == 0 && (gridCellValue[i+1][j]!=4 &&
                                    gridCellValue[i][j+1]!=4    &&
                                    gridCellValue[i][j-1]!=4))
                                    ||
                                    (i == 19 && (gridCellValue[i-1][j]!=4 &&
                                    gridCellValue[i][j+1]!=4 &&
                                    gridCellValue[i][j-1]!=4))
                                    || 
                                    (j == 0 && (gridCellValue[i][j+1] != 4 &&
                                    gridCellValue[i+1][j]!=4 &&
                                    gridCellValue[i-1][j]!=4))
                                    || 
                                    (j == 19 && (gridCellValue[i][j-1] != 4 &&
                                    gridCellValue[i+1][j]!=4) &&
                                    gridCellValue[i-1][j]!=4)
                                    ){
                                gridCell[i][j].setBackground(Color.GRAY);
                            }
                            
                    }
                    }
                }
                    if (currentPlayer == 4 && gridCellValue[i][j]==0 && firstTurn){
                        gridCell[19][19].setBackground(Color.GRAY);
                    }
                }
            }
        }
    }
    public void updateUsedPiece(int player, int index){
        usedPieceIndex[player][index]=1;
    }
    public void getPlayerPiece(int[][]playerPiece){
        for (int i = 0 ; i < 5 ; i++){
            for (int j = 0 ; j < 5 ; j++){
                piece[i][j] = playerPiece[i][j];
            }
        }
    }
    
    public void mousehandler(int x,int y, int[][]map, int player){
        gridCell[x][y].addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                potentialMoves();
                if(newTurn){
                    for (int i = 0 ; i < 5 ; i ++){
                        for (int j = 0 ; j < 5 ; j++){
                            map[i][j]=0;
                        }
                    }
                }
                for (int i = 0 ; i < 5 ; i++){
                    for (int j = 0 ; j < 5 ; j++){
                        if (map[i][j] == 1){
                            if (currentPlayer == 1){
                                gridCell[x+i-OFFSETX][y+j-OFFSETY].setBackground(Color.blue);
                            }
                            else{
                                if (currentPlayer == 2){
                                    gridCell[x+i-OFFSETX][y+j-OFFSETY].setBackground(Color.GREEN);
                                }
                                if (currentPlayer == 3){
                                    gridCell[x+i-OFFSETX][y+j-OFFSETY].setBackground(Color.RED);
                                }
                                if (currentPlayer == 4){
                                    gridCell[x+i-OFFSETX][y+j-OFFSETY].setBackground(Color.YELLOW);
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                for (int i = 0 ; i < 5 ; i++){
                    for (int j = 0 ; j < 5 ; j++){
                        if (map[i][j] == 1 &&  gridCellValue[x+i-2][y+j-2]<1){
                                gridCell[x+i-2][y+j-2].setBackground(Color.WHITE);
                        }
                    }
                }
                for (int i = 0 ; i < 20 ; i++){
                    for (int j = 0 ; j < 20 ; j++){
                        if (gridCell[i][j].getBackground() != Color.white && gridCellValue[i][j]==0){
                            gridCell[i][j].setBackground(Color.WHITE);
                        } else{
                            if (gridCell[i][j].getBackground() != Color.blue && gridCellValue[i][j]==1){
                                gridCell[i][j].setBackground(Color.BLUE);
                            }
                            if (gridCell[i][j].getBackground() != Color.green && gridCellValue[i][j]==2){
                                gridCell[i][j].setBackground(Color.green);
                            }
                            if (gridCell[i][j].getBackground() != Color.red && gridCellValue[i][j]==3){
                                gridCell[i][j].setBackground(Color.red);
                            }
                            if (gridCell[i][j].getBackground() != Color.yellow && gridCellValue[i][j]==4){
                                gridCell[i][j].setBackground(Color.yellow);
                            }
                        }
                    }
                }
            }
            }
            );
    }
    
    public boolean allSkip(){
        if (blueSkip && redSkip && yellowSkip && greenSkip){
            return true;
        }
        else
            return false;
    }
    
    public void gridColor(int x, int y){
        gridCell[x][y].setBackground(Color.WHITE);
    }
    
    public void setCellColor(int x, int y, Color color){
        gridCell[x][y].setBackground(color);
    }
    public void nextTurn(){
        //nextTurn calls the maps of each array
        currentPlayer++;
        

            if (blueSkip && currentPlayer == 1){
                currentPlayer++;
            }
            if (greenSkip && currentPlayer == 2){
                currentPlayer++;
            }
            if (redSkip && currentPlayer == 3){
                currentPlayer++;
            }
            if (yellowSkip && currentPlayer == 4){
                currentPlayer++;
                
            }
            if (currentPlayer > 4){                
                currentPlayer = 1;
                
                firstTurn = false;
                if (blueSkip){
                    currentPlayer = 2;
                } 
                if (blueSkip && greenSkip){
                    currentPlayer = 3;
                }
                if (blueSkip && greenSkip && yellowSkip){
                    currentPlayer = 4;
                }
            }
            
        
        newTurn = true;
    }
}

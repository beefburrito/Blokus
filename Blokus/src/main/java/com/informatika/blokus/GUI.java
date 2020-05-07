/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.informatika.blokus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author Eugene
 */
public class GUI extends JFrame    {
    private int pieceIndex = 0;
    JFrame frame;
    Grid redGrid = new Grid();
    Grid grid = new Grid();
    Grid greenGrid = new Grid();
    Grid blueGrid = new Grid();
    Grid yellowGrid = new Grid();
    Pieces pieces = new Pieces();
    Pieces bluePiece = new Pieces();
    Pieces redPiece = new Pieces();
    Pieces yellowPiece = new Pieces();
    Pieces greenPiece = new Pieces();
    JLabel winner = new JLabel();
    JLabel blueScore = new JLabel();
    JLabel greenScore = new JLabel();
    JLabel redScore = new JLabel();
    JLabel yellowScore = new JLabel();
    
    public int blueUsedPiece[];
    public int greenUsedPiece[];
    public int yellowUsedPiece[];
    public int redUsedPiece[];
    private final int SIDEPADDINGY = 250;
    private final int SIDEPADDINGX = 25;
    private final int BOTTOMPADDINGY = 50;
    private final int TOPPADDINGY = 25;
    private final int TOP = 0;
    private final int CENTER = 1;
    private final int BOTTOM = 2;
    private final int LEFT = 0;
    private final int RIGHT = 2;
    private final int CENTERPADDINGX = 25;
    public GUI() {
        frame = new JFrame("Blokus");
        JPanel pane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        frame.setSize(500, 400);
       
        JPanel layout = new JPanel(new GridLayout(20, 10));
  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel skip = new JPanel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = LEFT;
        c.ipady = TOPPADDINGY;
        c.gridy = TOP;
        c.ipadx = CENTERPADDINGX;
        c.weightx = 1.0;
        
        JButton blueSkipButton = new JButton();
        JButton greenSkipButton = new JButton();
        JButton redSkipButton = new JButton();
        JButton yellowSkipButton = new JButton();
        
        blueSkipButton.setText("Skip Blue");
        greenSkipButton.setText("Skip Green");
        redSkipButton.setText("Skip Red");
        yellowSkipButton.setText("Skip Yellow");
        
        JPanel skipButton = new JPanel(new GridLayout(4, 1));
        
        skipButton.add(blueSkipButton);
        skipButton.add(greenSkipButton);
        skipButton.add(redSkipButton);
        skipButton.add(yellowSkipButton);
        
        blueSkipButton.addActionListener(new SkipBlue());
        greenSkipButton.addActionListener(new SkipGreen());
        redSkipButton.addActionListener(new SkipRed());
        yellowSkipButton.addActionListener(new SkipYellow());
        
        
        skip.add(skipButton);
        pane.add(skip);
        
        
        
        
        JPanel red = new JPanel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = CENTER;
        c.ipady = TOPPADDINGY;      //make this component tall
	c.gridy = TOP;
        c.ipadx = CENTERPADDINGX;
        c.weightx = 1.0;
        
        red.setBackground(Color.red);
        
        JPanel redGridLayout = new JPanel(new GridLayout(5, 5));
        JPanel redMoveBar = new JPanel(new GridLayout(3, 2));
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                redGrid.createGrid(i, j, false);
                redGridLayout.add(redGrid.gridCell[i][j]);
                redGrid.gridColor(i, j);
            }
        }
        JButton redRotateLeft = new JButton();
        JButton redRotateRight = new JButton();
        JButton redFlipHorizontal = new JButton();
        JButton redFlipVertical = new JButton();
        JButton redNextPiece = new JButton();
        JButton redPreviousPiece = new JButton();
        redRotateLeft.setText("Rotate Left");
        redRotateRight.setText("Rotate Right");
        redFlipHorizontal.setText("Flip Horizontal");
        redFlipVertical.setText("Flip Vertical");
        redNextPiece.setText("Next Piece");
        redPreviousPiece.setText("Previous Piece");
        redMoveBar.add(redPreviousPiece);
        redMoveBar.add(redNextPiece);
        redMoveBar.add(redRotateLeft);
        redMoveBar.add(redRotateRight);
        redMoveBar.add(redFlipHorizontal);
        redMoveBar.add(redFlipVertical);
        redFlipVertical.addActionListener(new FlipVerticalActionListenerRed());
        redFlipHorizontal.addActionListener(new FlipHorizontalActionListenerRed());
        redNextPiece.addActionListener(new NextPieceActionListenerRed());
        redPreviousPiece.addActionListener(new PreviousPieceActionListenerRed());
        redRotateLeft.addActionListener(new RotateLeftActionListenerRed());
        redRotateRight.addActionListener(new RotateRightActionListenerRed());

        red.add(redGridLayout, BorderLayout.CENTER);
        red.add(redMoveBar, BorderLayout.WEST);
        pane.add(red, c);
        
        JPanel green = new JPanel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = LEFT;
        c.ipady = SIDEPADDINGY;      //make this component tall
        c.ipadx = SIDEPADDINGX;
	c.gridy = CENTER;
        c.weighty = 1; 
        c.weightx = 0.5;

        green.setBackground(Color.green);

        JPanel greenGridLayout = new JPanel(new GridLayout(5, 5));
        JPanel greenMoveBar = new JPanel(new GridLayout(3, 2));
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                greenGrid.createGrid(i, j, false);
                greenGridLayout.add(greenGrid.gridCell[i][j]);
                greenGrid.gridColor(i, j);
            }
        }
        
        JButton greenRotateLeft = new JButton();
        JButton greenRotateRight = new JButton();
        JButton greenFlipHorizontal = new JButton();
        JButton greenFlipVertical = new JButton();
        JButton greenNextPiece = new JButton();
        
        JButton greenPreviousPiece = new JButton();
        greenRotateLeft.setText("Rotate Left");
        greenRotateRight.setText("Rotate Right");
        greenFlipHorizontal.setText("Flip Horizontal");
        greenFlipVertical.setText("Flip Vertical");
        greenNextPiece.setText("Next Piece");
        greenPreviousPiece.setText("Previous Piece");
        greenMoveBar.add(greenPreviousPiece);
        greenMoveBar.add(greenNextPiece);
        greenMoveBar.add(greenRotateLeft);
        greenMoveBar.add(greenRotateRight);
        greenMoveBar.add(greenFlipHorizontal);
        greenMoveBar.add(greenFlipVertical);
        greenFlipVertical.addActionListener(new FlipVerticalActionListenerGreen());
        greenFlipHorizontal.addActionListener(new FlipHorizontalActionListenerGreen());
        greenNextPiece.addActionListener(new NextPieceActionListenerGreen());
        greenPreviousPiece.addActionListener(new PreviousPieceActionListenerGreen());
        greenRotateLeft.addActionListener(new RotateLeftActionListenerGreen());
        greenRotateRight.addActionListener(new RotateRightActionListenerGreen());
        green.add(greenGridLayout, BorderLayout.CENTER);
        green.add(greenMoveBar, BorderLayout.WEST);
        pane.add(green, c);
          
        JPanel blue = new JPanel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = CENTER;
        c.ipady = BOTTOMPADDINGY;      //make this component tall
	c.gridy = BOTTOM;
        c.ipadx = CENTERPADDINGX;
        
        blue.setBackground(Color.blue);
        
        JPanel blueGridLayout = new JPanel(new GridLayout(5, 5));
        JPanel blueMoveBar = new JPanel(new GridLayout(3, 2));
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                blueGrid.createGrid(i, j, false);
                blueGridLayout.add(blueGrid.gridCell[i][j]);
                blueGrid.gridColor(i, j);
            }
        }
        JButton blueRotateLeft = new JButton();
        JButton blueRotateRight = new JButton();
        JButton blueFlipHorizontal = new JButton();
        JButton blueFlipVertical = new JButton();
        JButton blueNextPiece = new JButton();
        blueFlipHorizontal.addActionListener(new FlipHorizontalActionListenerBlue());
        blueNextPiece.addActionListener(new NextPieceActionListenerBlue());
        JButton bluePreviousPiece = new JButton();
        bluePreviousPiece.addActionListener(new PreviousPieceActionListenerBlue());
        blueRotateLeft.setText("Rotate Left");
        blueRotateLeft.addActionListener(new RotateLeftActionListenerBlue());

        blueRotateRight.setText("Rotate Right");
        blueRotateRight.addActionListener(new RotateRightActionListenerBlue());
        blueFlipHorizontal.setText("Flip Horizontal");
        blueFlipVertical.setText("Flip Vertical");
        blueFlipVertical.addActionListener(new FlipVerticalActionListenerBlue());
        blueNextPiece.setText("Next Piece");
        bluePreviousPiece.setText("Previous Piece");
        blueMoveBar.add(bluePreviousPiece);
        blueMoveBar.add(blueNextPiece);
        blueMoveBar.add(blueRotateLeft);
        blueMoveBar.add(blueRotateRight);
        blueMoveBar.add(blueFlipHorizontal);
        blueMoveBar.add(blueFlipVertical);
        blue.add(blueGridLayout, BorderLayout.CENTER);
        blue.add(blueMoveBar, BorderLayout.WEST);
        pane.add(blue, c);
        
        JPanel yellow = new JPanel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = RIGHT;
        c.ipady = SIDEPADDINGY;      //make this component tall
        c.ipadx = SIDEPADDINGX;
	c.gridy = CENTER;
        c.weighty = 1; 
        c.weightx = 0.5;
        yellow.setBackground(Color.yellow);
        JPanel yellowGridLayout = new JPanel(new GridLayout(5, 5));
        JPanel yellowMoveBar = new JPanel(new GridLayout(3, 2));
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                yellowGrid.createGrid(i, j, false);
                yellowGridLayout.add(yellowGrid.gridCell[i][j]);
                yellowGrid.gridColor(i, j);
            }
        }
        JButton yellowRotateLeft = new JButton();
        JButton yellowRotateRight = new JButton();
        JButton yellowFlipHorizontal = new JButton();
        JButton yellowFlipVertical = new JButton();
        JButton yellowNextPiece = new JButton();
        JButton yellowPreviousPiece = new JButton();
        yellowRotateLeft.setText("Rotate Left");
        yellowRotateRight.setText("Rotate Right");
        yellowFlipHorizontal.setText("Flip Horizontal");
        yellowFlipVertical.setText("Flip Vertical");
        yellowNextPiece.setText("Next Piece");
        yellowPreviousPiece.setText("Previous Piece");
        yellowMoveBar.add(yellowPreviousPiece);
        yellowMoveBar.add(yellowNextPiece);
        yellowMoveBar.add(yellowRotateLeft);
        yellowMoveBar.add(yellowRotateRight);
        yellowMoveBar.add(yellowFlipHorizontal);
        yellowMoveBar.add(yellowFlipVertical);
        yellowFlipVertical.addActionListener(new FlipVerticalActionListenerYellow());
        yellowFlipHorizontal.addActionListener(new FlipHorizontalActionListenerYellow());
        yellowNextPiece.addActionListener(new NextPieceActionListenerYellow());
        yellowPreviousPiece.addActionListener(new PreviousPieceActionListenerYellow());      
        yellowRotateLeft.addActionListener(new RotateLeftActionListenerYellow());
        yellowRotateRight.addActionListener(new RotateRightActionListenerYellow());
        yellow.add(yellowGridLayout, BorderLayout.CENTER);
        yellow.add(yellowMoveBar, BorderLayout.SOUTH);
        pane.add(yellow, c);
        
        
        
//        yellowNextPiece.setEnabled(false);
//        redNextPiece.setEnabled(false);
//        blueNextPiece.setEnabled(false);
//        greenNextPiece.setEnabled(false);
//        
//        yellowPreviousPiece.setEnabled(false);
//        redPreviousPiece.setEnabled(false);
//        bluePreviousPiece.setEnabled(false);
//        greenPreviousPiece.setEnabled(false);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = CENTER;
        c.gridy = CENTER;
        c.ipadx = CENTERPADDINGX;
        pane.add(layout, c);
        
        for (int i = 0 ; i< 20; i++){
            for (int j =0 ;j<20;j++){
                grid.createGrid(i,j,true);
                grid.mousehandler(i, j,pieces.pieceMap,grid.currentPlayer);
                grid.gridhandler(i, j, pieces.pieceMap);

                layout.add(grid.gridCell[i][j]);
                grid.gridColor(i,j);
            }
        }
        frame.setContentPane(pane);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setVisible(true);       
    }
    public void cellClearOnButtonHandler(int player){
//        greenNextPiece.setEnabled(true);
        for (int i = 0; i < 5; i++){
                    for (int j = 0; j < 5 ; j++){
                        if (player == 1){
                         blueGrid.setCellColor(i, j, Color.WHITE);   
                        }
                        else{
                            if (player == 2){
                                greenGrid.setCellColor(i,j,Color.WHITE);
                            }
                            if (player == 3){
                                redGrid.setCellColor(i,j,Color.WHITE);
                            }
                            if(player == 4){
                                yellowGrid.setCellColor(i,j,Color.WHITE);
                            }
                        }
                    }
        }
    }
    public void setCellColorOnHover(int player){
        for (int i = 0; i < 5 ; i++){
            for (int j=0; j<5; j++){
                if (player == 1){
                    if (bluePiece.pieceMap[i][j] >= 1){
                        blueGrid.setCellColor(i, j, Color.BLUE);
                    }
                    else{
                        blueGrid.setCellColor(i, j, Color.WHITE);
                    }
                }else{
                    if (player == 2){
                        if (greenPiece.pieceMap[i][j] >= 1){
                        greenGrid.setCellColor(i, j, Color.GREEN);
                    }
                        else{
                            greenGrid.setCellColor(i, j, Color.WHITE);
                        }
                    }
                    if (player == 3){
                        if (redPiece.pieceMap[i][j] >= 1){
                        redGrid.setCellColor(i, j, Color.RED);
                    }
                        else{
                            redGrid.setCellColor(i, j, Color.WHITE);
                        }
                    }
                    if (player == 4){
                        if (yellowPiece.pieceMap[i][j] >= 1){
                        yellowGrid.setCellColor(i, j, Color.YELLOW);
                    }
                        else{
                            yellowGrid.setCellColor(i, j, Color.WHITE);
                        }
                    }
                }
            }
        }     
    }
    class RotateLeftActionListenerBlue implements ActionListener{
          @Override
          public void actionPerformed(ActionEvent e) {
              clearNextPlayerCell();
            cellClearOnButtonHandler(grid.currentPlayer);
                bluePiece.rotateLeft();

            pieces.rotateLeft();
            setCellColorOnHover(grid.currentPlayer);
      }
    }
    class RotateLeftActionListenerGreen implements ActionListener{
          public void actionPerformed(ActionEvent e) {
              clearNextPlayerCell();
            cellClearOnButtonHandler(grid.currentPlayer);
                    greenPiece.rotateLeft();
            pieces.rotateLeft();
            setCellColorOnHover(grid.currentPlayer);
      }
    }
    class RotateLeftActionListenerRed implements ActionListener{
          public void actionPerformed(ActionEvent e) {
              clearNextPlayerCell();
            cellClearOnButtonHandler(grid.currentPlayer);
                    redPiece.rotateLeft();
            
            pieces.rotateLeft();
            setCellColorOnHover(grid.currentPlayer);
      }
    }
    class RotateLeftActionListenerYellow implements ActionListener{
          public void actionPerformed(ActionEvent e) {
              clearNextPlayerCell();
            cellClearOnButtonHandler(grid.currentPlayer);
                    yellowPiece.rotateLeft();

            pieces.rotateLeft();
            setCellColorOnHover(grid.currentPlayer);
      }
    }
    
    class RotateRightActionListenerBlue implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          clearNextPlayerCell();
            cellClearOnButtonHandler(grid.currentPlayer);
                bluePiece.rotateRight();

            pieces.rotateRight();
            setCellColorOnHover(grid.currentPlayer);
      }
    }
    class RotateRightActionListenerGreen implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          clearNextPlayerCell();
            cellClearOnButtonHandler(grid.currentPlayer);

                    greenPiece.rotateRight();

            pieces.rotateRight();
            setCellColorOnHover(grid.currentPlayer);
      }
    }
    class RotateRightActionListenerRed implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          clearNextPlayerCell();
            cellClearOnButtonHandler(grid.currentPlayer);

                    redPiece.rotateRight();

            pieces.rotateRight();
            setCellColorOnHover(grid.currentPlayer);
      }
    }
    class RotateRightActionListenerYellow implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          clearNextPlayerCell();
            cellClearOnButtonHandler(grid.currentPlayer);

                    yellowPiece.rotateRight();

            pieces.rotateRight();
            setCellColorOnHover(grid.currentPlayer);
      }
    }

    class PreviousPieceActionListenerBlue implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          clearNextPlayerCell();
          if (grid.currentPlayer == 1){
              grid.newTurn = false;
          }
          grid.potentialMoves();
            grid.blueIndex = minPieceIndex(grid.blueIndex,grid.usedPieceIndex,1);
                bluePiece.pieceClear();
                bluePiece.switchBlocks(grid.blueIndex);
                pieceIndex = grid.blueIndex;
                pieces.pieceClear();
                pieces.switchBlocks(grid.blueIndex);
            setCellColorOnHover(grid.currentPlayer);
      }
    }
    class PreviousPieceActionListenerGreen implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          clearNextPlayerCell();
          if (grid.currentPlayer == 2){
              grid.newTurn = false;
          }
          grid.potentialMoves();
            grid.greenIndex = minPieceIndex(grid.greenIndex,grid.usedPieceIndex,2);
                    greenPiece.pieceClear();
                    greenPiece.switchBlocks(grid.greenIndex);
                    pieceIndex = grid.greenIndex;
                    pieces.pieceClear();
                    pieces.switchBlocks(grid.greenIndex);
            setCellColorOnHover(grid.currentPlayer);
      }
     }
    
    class PreviousPieceActionListenerRed implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          clearNextPlayerCell();
          if (grid.currentPlayer == 3){
              grid.newTurn = false;
          }
          grid.potentialMoves();
            grid.redIndex = minPieceIndex(grid.redIndex,grid.usedPieceIndex,3);
                    redPiece.pieceClear();
                    redPiece.switchBlocks(grid.redIndex);
                    pieceIndex = grid.redIndex;
                    pieces.pieceClear();
                    pieces.switchBlocks(grid.redIndex);
            setCellColorOnHover(grid.currentPlayer);
      }
     }
    class PreviousPieceActionListenerYellow implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          clearNextPlayerCell();
          if (grid.currentPlayer == 4){
              grid.newTurn = false;
          }
          grid.potentialMoves();
            grid.yellowIndex = minPieceIndex(grid.yellowIndex,grid.usedPieceIndex,4);
                    yellowPiece.pieceClear();
                    yellowPiece.switchBlocks(grid.yellowIndex);
                    pieceIndex = grid.yellowIndex;
                    pieces.pieceClear();
                    pieces.switchBlocks(grid.yellowIndex);
            setCellColorOnHover(grid.currentPlayer);
      }
     }
    class NextPieceActionListenerBlue implements ActionListener{
      public void actionPerformed(ActionEvent e) {  
          clearNextPlayerCell();
          if (grid.currentPlayer == 1){
              grid.newTurn = false;
          }
          grid.potentialMoves();
            grid.blueIndex = maxPieceIndex(grid.blueIndex,grid.usedPieceIndex,1);
            bluePiece.pieceClear();
            bluePiece.switchBlocks(grid.blueIndex);
            pieceIndex = grid.blueIndex;
            pieces.pieceClear();
            pieces.switchBlocks(grid.blueIndex);
            setCellColorOnHover(grid.currentPlayer);
      }
    }
    class NextPieceActionListenerGreen implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          clearNextPlayerCell();
          if (grid.currentPlayer == 2){
              grid.newTurn = false;
          }
          grid.potentialMoves();
            grid.greenIndex = maxPieceIndex(grid.greenIndex,grid.usedPieceIndex,2);
            greenPiece.pieceClear();
            greenPiece.switchBlocks(grid.greenIndex);
            pieceIndex = grid.greenIndex;
            pieces.pieceClear();
            pieces.switchBlocks(grid.greenIndex);
            setCellColorOnHover(grid.currentPlayer);
      }
     }
    
    class NextPieceActionListenerRed implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          clearNextPlayerCell();
          if (grid.currentPlayer == 3){
              grid.newTurn = false;
          }
          grid.potentialMoves();
            grid.redIndex = maxPieceIndex(grid.redIndex,grid.usedPieceIndex,3);
            redPiece.pieceClear();
            redPiece.switchBlocks(grid.redIndex);
            pieceIndex = grid.redIndex;
            pieces.pieceClear();
            pieces.switchBlocks(grid.redIndex);
            setCellColorOnHover(grid.currentPlayer);
      }
     }
    class NextPieceActionListenerYellow implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          clearNextPlayerCell();
          if (grid.currentPlayer == 4){
              grid.newTurn = false;
          }
          grid.potentialMoves();
            grid.yellowIndex = maxPieceIndex(grid.yellowIndex,grid.usedPieceIndex,4);
            yellowPiece.pieceClear();
            yellowPiece.switchBlocks(grid.yellowIndex);
            pieceIndex = grid.yellowIndex;
            pieces.pieceClear();
            pieces.switchBlocks(grid.yellowIndex);
            setCellColorOnHover(grid.currentPlayer);
      }
     }

    class FlipHorizontalActionListenerBlue implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            cellClearOnButtonHandler(grid.currentPlayer);           
            bluePiece.flipHorizontal();
            pieces.flipHorizontal();
            setCellColorOnHover(grid.currentPlayer);
      }
    }
    class FlipHorizontalActionListenerGreen implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            cellClearOnButtonHandler(grid.currentPlayer);           
            greenPiece.flipHorizontal();
            pieces.flipHorizontal();
            setCellColorOnHover(grid.currentPlayer);
      }
    }
    class FlipHorizontalActionListenerRed implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            cellClearOnButtonHandler(grid.currentPlayer);           
            redPiece.flipHorizontal();
            pieces.flipHorizontal();
            setCellColorOnHover(grid.currentPlayer);
      }
    }
    class FlipHorizontalActionListenerYellow implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            cellClearOnButtonHandler(grid.currentPlayer);           
            yellowPiece.flipHorizontal();
            pieces.flipHorizontal();
            setCellColorOnHover(grid.currentPlayer);
      }
    }
    class FlipVerticalActionListenerBlue implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            cellClearOnButtonHandler(grid.currentPlayer);           
            bluePiece.flipVertical();
            pieces.flipVertical();
            setCellColorOnHover(grid.currentPlayer);
      }
    }
    class FlipVerticalActionListenerGreen implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            cellClearOnButtonHandler(grid.currentPlayer);           
            greenPiece.flipVertical();
            pieces.flipVertical();
            setCellColorOnHover(grid.currentPlayer);
      }
    }
    class FlipVerticalActionListenerRed implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            cellClearOnButtonHandler(grid.currentPlayer);           
            redPiece.flipVertical();
            pieces.flipVertical();
            setCellColorOnHover(grid.currentPlayer);
      }
    }
    class FlipVerticalActionListenerYellow implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            clearNextPlayerCell();
            cellClearOnButtonHandler(grid.currentPlayer);           
            yellowPiece.flipVertical();
            pieces.flipVertical();
            setCellColorOnHover(grid.currentPlayer);
      }
    }
    
    class SkipBlue implements ActionListener{
        public void actionPerformed(ActionEvent e){
            clearNextPlayerCell();
            System.out.println(grid.currentPlayer);
            if (grid.currentPlayer == 1 && !grid.firstTurn){
                grid.blueSkip = true;
                grid.nextTurn();
                System.out.print("Blue has skipped");
            }
            itsTheEndGameNow();
        }
    }
    
    class SkipGreen implements ActionListener{
        public void actionPerformed(ActionEvent e){
            clearNextPlayerCell();
            System.out.println(grid.currentPlayer);
            if (grid.currentPlayer == 2 && !grid.firstTurn){
                grid.greenSkip = true;
                grid.nextTurn();
                System.out.print("Green has skipped");
            }
            itsTheEndGameNow();
        }
    }
    class SkipRed implements ActionListener{
        public void actionPerformed(ActionEvent e){
            clearNextPlayerCell();
            System.out.println(grid.currentPlayer);
            if (grid.currentPlayer == 3 && !grid.firstTurn){
                grid.redSkip = true;
                grid.nextTurn();
                System.out.print("Red has skipped");
            }
            itsTheEndGameNow();
        }
    }
    class SkipYellow implements ActionListener{
        public void actionPerformed(ActionEvent e){
            clearNextPlayerCell();
            System.out.println(grid.currentPlayer);
            if (grid.currentPlayer == 4 && !grid.firstTurn){
                grid.yellowSkip = true;
                grid.nextTurn();
                System.out.print("Yellow has skipped");
            }
            itsTheEndGameNow();
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
    public void clearNextPlayerCell(){
        if (grid.currentPlayer == 1){
            for (int i = 0 ; i < 5 ; i++){
                for (int j = 0 ; j < 5 ; j++){
                    yellowGrid.gridCell[i][j].setBackground(Color.white);
                }
            }
        }
        else {
            if (grid.currentPlayer == 2){
                for (int i = 0 ; i < 5 ; i++){
                    for (int j = 0 ; j < 5 ; j++){
                        blueGrid.gridCell[i][j].setBackground(Color.white);
                    }
                }
            }
            if (grid.currentPlayer == 3){
                for (int i = 0 ; i < 5 ; i++){
                    for (int j = 0 ; j < 5 ; j++){
                        greenGrid.gridCell[i][j].setBackground(Color.white);
                    }
                }
            }
            if (grid.currentPlayer == 4){
                for (int i = 0 ; i < 5 ; i++){
                    for (int j = 0 ; j < 5 ; j++){
                        redGrid.gridCell[i][j].setBackground(Color.white);
                    }
                }
            }
        }
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
    

    public void itsTheEndGameNow(){
        if(grid.redSkip && grid.blueSkip && grid.greenSkip && grid.yellowSkip){
            frame.setVisible(false);
            JFrame scoreFrame = new JFrame("Score");
            scoreFrame.setVisible(true);
            
            
            grid.countScore();
        scoreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel scoreBoard = new JPanel();
        
       winner.setText(grid.winner());
        
        
        blueScore.setText(" Blue Score: " + grid.bluePoint);
        greenScore.setText(" Green Score: " + grid.greenPoint);
        redScore.setText(" Red Score: " + grid.redPoint);
        yellowScore.setText(" Yellow Score: " + grid.yellowPoint);
        scoreBoard.add(blueScore);
        scoreBoard.add(greenScore);
        scoreBoard.add(redScore);
        scoreBoard.add(yellowScore);
        scoreBoard.add(winner);
        
        scoreFrame.setContentPane(scoreBoard);
        scoreFrame.setVisible(true);
            System.out.print(grid.bluePoint);
            System.out.print(grid.redPoint);
            System.out.print(grid.greenPoint);
            System.out.print(grid.yellowPoint);
            System.out.print(grid.winner());
            
        }
            
    }
    

    
    public static void main(String args[]) {
        GUI test;
        test = new GUI();
        
    }
}

package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.AbstractMap;
import java.util.EnumMap;

/**
 * Created by David on 2016-02-10.
 */
public class TetrisComponent extends JComponent implements BoardListener {

    //Pekare
    private final Board board;
    private final int tileSize;
    private AbstractMap<SquareType, Color> colorMap;

    //konstruktor
    public TetrisComponent(Board board) {
        this.board = board;
        this.tileSize = 30;
        createEnumMap();
        makeRight();
        makeLeft();
    }

    public Dimension getPreferredSize() {
        int xSize = board.getWidth() * tileSize;
        int ySize = board.getHeight() * tileSize;
        return new Dimension(xSize, ySize);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

        //Går igenom board
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                //tittar vilken SquareType som finns i rutan och sätter rätt färg
                g2d.setColor(colorMap.get(board.getCell(i+2, j+2)));
                //och skapar rektangel
                g2d.fillRect(i * tileSize, j * tileSize, tileSize - 1, tileSize - 1);
            }
        }
        //Skriver ut falling
        if (board.getFalling() != null) {
            //genom att hitta och sätta rätt färg
            for (int k = 0; k < board.getFalling().getPolyArray().length; k++) {
                for (int l = 0; l < board.getFalling().getPolyArray()[0].length; l++) {
                    if (board.getFalling().getPolyArray()[k][l] != SquareType.EMPTY) {
                        g2d.setColor(colorMap.get(board.getFalling().getPolyArray()[k][l]));

                        //Och sedan skapa rektangeln
                        g2d.fillRoundRect((board.getFallingX() + k) * tileSize, (board.getFallingY() + l) * tileSize,
                                tileSize - 1, tileSize - 1, tileSize / 10, tileSize / 10);

                    }
                }
            }
        }
    }

    private void createEnumMap() {
        colorMap = new EnumMap<>(SquareType.class);
        colorMap.put(SquareType.EMPTY, Color.black);
        colorMap.put(SquareType.OUTSIDE, Color.white);
        colorMap.put(SquareType.I, Color.cyan);
        colorMap.put(SquareType.J, Color.blue);
        colorMap.put(SquareType.L, Color.orange);
        colorMap.put(SquareType.O, Color.yellow);
        colorMap.put(SquareType.S, Color.green);
        colorMap.put(SquareType.T, Color.magenta);
        colorMap.put(SquareType.Z, Color.red);

    }
    //Uppdatera hur spelplanen ser ut
    public void boardChanged() {
        repaint();
    }

    //Flytta falling höger
    private void makeRight(){
        final Action rightAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.moveRight();
            }
        };
        getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"moveRight");
        getInputMap().put(KeyStroke.getKeyStroke("D"),"moveRight");
        getActionMap().put("moveRight",rightAction);
    }
    //flytta falling vänster
    private void makeLeft() {
        final Action leftAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.moveLeft();
            }
        };
        getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        getInputMap().put(KeyStroke.getKeyStroke("A"), "moveLeft");
        getActionMap().put("moveLeft", leftAction);
    }



}

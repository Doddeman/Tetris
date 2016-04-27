package tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 2016-02-06.
 */
class Board {

    //Fält
    private final int width;
    private final int height;
    private static final int OUTSIDE_SIZE = 2;
    //Skapa pekare till arrayen
    private final SquareType[][] squares;
    //Fallande tetromino
    private Poly falling = null;
    private int fallingX;
    private int fallingY;
    //BoardListeners
    private final List<BoardListener> listeners;
    //Poly-fabrik
    private final TetrominoMaker myMaker;
    //Timer
    private Timer theTimer;

    //konstruktor
    Board(int width, int height) {
        startTimer();
        this.width = width;
        this.height = height;
        int newWidth = width + 4;
        int newHeight = height + 4;
        myMaker = new TetrominoMaker();
        //Skapa Listan av listeners
        listeners = new ArrayList<>();
        //Skapa själva arrayen för spelplanen
        squares = new SquareType[newWidth][newHeight];

        //Sätt alla rutor till OUTSIDE
        for (int i = 0; i < newWidth; i++) {
            for (int j = 0; j < newHeight; j++) {
                squares[i][j] = SquareType.OUTSIDE;
            }
        }
        //Skriv över alla utom de två yttersta raderna och kolumnerna med EMPTY
        for (int i = OUTSIDE_SIZE; i < newWidth - OUTSIDE_SIZE; i++) {
            for (int j = OUTSIDE_SIZE; j < newHeight - OUTSIDE_SIZE; j++) {
                    squares[i][j] = SquareType.EMPTY;

            }
        }
    }

    //För spelet frammåt
    private void tick(){
        if (falling != null ){
            fallingY++;
            if(hasCollision()){
                fallingY--;
                fallingToBoard();
                falling = null;
            }
        }
        else{
            this.falling = myMaker.getRandomPoly();
            fallingX = width/2-1; //ger mitten av planen
            fallingY = 0;

            if(hasCollision()){
                falling = null;
                System.out.println("Game over");
                theTimer.stop();
            }
        }
        notifyListeners();
    }
    //tar bort vald rad
    private void removeRow(int row){
        for (int i = row; i > OUTSIDE_SIZE; i--){
            for(int j = OUTSIDE_SIZE; j < squares.length - OUTSIDE_SIZE; j++){
                setCell(j,i,getCell(j,i-1));
            }
        }
    }

    //Kollar om någon rad ska tas bort, kallar på removeRow
    private void removeRows() {

        for (int i = OUTSIDE_SIZE; i < squares[0].length - OUTSIDE_SIZE; i++){
            boolean fullRow = true;
            for(int j = OUTSIDE_SIZE; j < squares.length - OUTSIDE_SIZE; j++){
                if(getCell(j,i) == SquareType.EMPTY){
                    fullRow = false;
                    break;
                }
            }
            if (fullRow){
                removeRow(i);
            }
        }
        notifyListeners();
    }

    //Flytta falling ett steg till höger
    public void moveRight(){
        fallingX++;
        if(hasCollision()){
            fallingX--;
        }
        notifyListeners();
    }
    //Flytta falling ett steg till vänster
    public void moveLeft(){
        fallingX--;
        if(hasCollision()){
            fallingX++;
        }
        notifyListeners();
    }

    //Upptäcker kollision
    private boolean hasCollision(){
        if(falling != null){
            for (int i = 0; i < falling.getPolyArray().length; i++) {
                for (int j = 0; j < falling.getPolyArray()[0].length; j++) {
                    if(falling.getPolyCell(i,j) != SquareType.EMPTY){
                        if(getCell(fallingX + i + OUTSIDE_SIZE, fallingY + j + OUTSIDE_SIZE) != SquareType.EMPTY){
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }
    //konverterar falling till en del av spelplanen och kallar på removeRows
    private void fallingToBoard(){
        for (int i = 0; i < falling.getPolyArray().length; i++) {
            for (int j = 0; j < falling.getPolyArray()[0].length; j++) {
                if(falling.getPolyCell(i,j) != SquareType.EMPTY){
                    setCell(fallingX + i + OUTSIDE_SIZE, fallingY + j + OUTSIDE_SIZE, falling.getPolyCell(i, j));
                }
            }
        }
        removeRows();
    }
    //Tillsätter en cell i spelplanen ett Squaretype-värde. Används i fallingToBoard bland annat
    private void setCell(int x, int y, SquareType s){
        squares[x][y] = s;
        notifyListeners();
    }

    //Hämta SquareType från viss position på spelplanen
    public SquareType getCell(int x, int y) {
        if (x >= 0 && x < width+4 && y >= 0 && y < height+4) {
            return squares[x][y];
        } else {
            return SquareType.OUTSIDE;
        }
    }

    //Lägg till BoardListener (används i testklass)
    public void addBoardListener(BoardListener bl){
        listeners.add(bl);
    }

    //Notify Listeners
    private void notifyListeners(){
        listeners.forEach(BoardListener::boardChanged);
    }

    //Getters
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Poly getFalling() {
        return falling;
    }
    public int getFallingX() {
        return fallingX;
    }
    public int getFallingY() {
        return fallingY;
    }

    private void startTimer(){
        final Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tick();
            }
        };
        theTimer = new Timer(200, action); //Godtycklig tid för timern
        theTimer.setCoalesce(true);
        theTimer.start();

    }

// --Commented out by Inspection START (2016-04-01 11:58):
// --Commented out by Inspection START (2016-04-01 12:35):
////    //Slumpad spelplan (används inte längre)
////    public void slumpBoard() {
////
////        for (int i = 0; i < width; i++) {
////            for (int j = 0; j < height; j++) {
////                squares[i][j] = slumpSquareType();
////            }
////        }
////        notifyListeners();
////    }
//// --Commented out by Inspection STOP (2016-04-01 11:58)
//    //Slumpad squaretype (används inte längre)
//    private SquareType slumpSquareType(){
//
//        Random random = new Random();
//        int n = random.nextInt(SquareType.values().length);
//        //SquareType.values() = lista av alla enumerationer
//        //SquareType.values().length = 9
//
//        return SquareType.values()[n];
//    }
// --Commented out by Inspection STOP (2016-04-01 12:35)


}




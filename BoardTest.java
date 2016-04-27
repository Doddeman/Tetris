package tetris;

/**
 * Created by David on 2016-02-11.
 */
public final class BoardTest {

    private BoardTest() {}

    public static void main(String[] args) {
        Board myBoard = new Board(10,16); //Gör ett 10x16 rutor stort bräde
        TetrisFrame myFrame = new TetrisFrame(myBoard);
        TetrisComponent myComponent = new TetrisComponent(myBoard);
        myBoard.addBoardListener(myComponent);
        myFrame.add(myComponent);

    }
}

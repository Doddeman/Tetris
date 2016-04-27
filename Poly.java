package tetris;

/**
 * Created by David on 2016-02-07.
 */
public class Poly {

    private final SquareType[][] polyArray;

    //konstruktor
    public Poly(SquareType[][] polyArray) {
        this.polyArray = polyArray;
    }

    //Return the 2D-array
    public SquareType[][] getPolyArray() {
        return polyArray;
    }

    //Return specific cell in the poly
    public SquareType getPolyCell(int x, int y){
        return polyArray[x][y];
    }

}

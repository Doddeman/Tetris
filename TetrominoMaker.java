package tetris;

import java.util.Random;

/**
 * Created by David on 2016-02-07.
 */
class TetrominoMaker {

    private SquareType[][] tetroMino = null;

    //Skapar random Poly genom att kalla på getPoly
    public Poly getRandomPoly() {
        final Random random = new Random();
        int randomType = random.nextInt(getNumberOfTypes());
        //return getPoly(1); //Genererar endast kuber för lättare testning
        return getPoly(randomType);
    }
    //Hur många olika Polys som kan skapas (7)
    private static int getNumberOfTypes(){return SquareType.values().length - 2;}

    //Return new empty 2D-array to be filled by a Poly
    private SquareType[][] fillEmpty(int size){
        tetroMino = new SquareType[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tetroMino[i][j] = SquareType.EMPTY;
            }
        }
        return tetroMino;
    }
    //Make poly
    private Poly getPoly(int n){
        switch (n){
            case 0: //I
                return makeIPoly();
            case 1: //O
                return makeOPoly();
            case 2: //T
                return makeTPoly();
            case 3: //S
                return makeSPoly();
            case 4: //Z
                return makeZPoly();
            case 5: //J
                return makeJPoly();
            case 6: //L
                return makeLPoly();
            default:
                throw new IllegalArgumentException("Unvalid Poly int: " + n);
        }
    }

    private Poly makeIPoly(){
        tetroMino = fillEmpty(4);
        tetroMino[1][0] = SquareType.I;
        tetroMino[1][1] = SquareType.I;
        tetroMino[1][2] = SquareType.I;
        tetroMino[1][3] = SquareType.I;
        return new Poly(tetroMino);
    }
    private Poly makeOPoly(){
        tetroMino = fillEmpty(2);
        tetroMino[0][0] = SquareType.O;
        tetroMino[0][1] = SquareType.O;
        tetroMino[1][0] = SquareType.O;
        tetroMino[1][1] = SquareType.O;
        return new Poly(tetroMino);
    }
    private Poly makeTPoly() {
        tetroMino = fillEmpty(3);
        tetroMino[1][0] = SquareType.T;
        tetroMino[1][1] = SquareType.T;
        tetroMino[0][1] = SquareType.T;
        tetroMino[1][2] = SquareType.T;
        return new Poly(tetroMino);
    }
    private Poly makeSPoly() {
        tetroMino = fillEmpty(3);
        tetroMino[1][0] = SquareType.S;
        tetroMino[1][1] = SquareType.S;
        tetroMino[0][1] = SquareType.S;
        tetroMino[0][2] = SquareType.S;
        return new Poly(tetroMino);
    }
    private Poly makeZPoly() {
        tetroMino = fillEmpty(3);
        tetroMino[0][0] = SquareType.Z;
        tetroMino[0][1] = SquareType.Z;
        tetroMino[1][1] = SquareType.Z;
        tetroMino[1][2] = SquareType.Z;
        return new Poly(tetroMino);
    }
    private Poly makeJPoly() {
        tetroMino = fillEmpty(3);
        tetroMino[0][0] = SquareType.J;
        tetroMino[1][0] = SquareType.J;
        tetroMino[1][1] = SquareType.J;
        tetroMino[1][2] = SquareType.J;
        return new Poly(tetroMino);
    }
    private Poly makeLPoly() {
        tetroMino = fillEmpty(3);
        tetroMino[1][0] = SquareType.L;
        tetroMino[1][1] = SquareType.L;
        tetroMino[1][2] = SquareType.L;
        tetroMino[0][2] = SquareType.L;
        return new Poly(tetroMino);
    }
}

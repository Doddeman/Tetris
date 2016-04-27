package tetris;

/**
 * Created by David on 2016-02-06.
 */

/*
final class BoardToTextConverter {

    public static String convertToText(Board board){
        //skapar en stringbuilder för att kunna appenda text
        StringBuilder builder = new StringBuilder();

        //går igenom board
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                //Appendar falling
                if (board.getFalling() != null && j == board.getFallingX() && i == board.getFallingY()) {
                    //SquareType cell = board.getFalling()[board.getFallingX()][board.getFallingY()];
                    //builder.append(board.getCell(board.getFalling().getFallingY(),board.getFallingX()));
                }
                //appendar det som ligger i board
                else {
                    //kollar vilken squaretype varje element är och appendar det till builder
                    SquareType cell = board.getCell(j, i);
                    switch (cell) {
                        case EMPTY:
                            builder.append("#");
                            break;
                        case I:
                            builder.append("I");
                            break;
                        case O:
                            builder.append("O");
                            break;
                        case T:
                            builder.append("T");
                            break;
                        case S:
                            builder.append("S");
                            break;
                        case Z:
                            builder.append("Z");
                            break;
                        case J:
                            builder.append("J");
                            break;
                        case L:
                            builder.append("L");
                            break;
                        case OUTSIDE:
                            builder.append("X");
                            break;
                    }
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
*/
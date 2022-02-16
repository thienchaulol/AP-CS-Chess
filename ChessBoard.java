import java.util.*;
//Kevin was here
//so was Evan
public class ChessBoard{
    ArrayList<ArrayList<ChessPiece>> board;
    ChessRules obj;
    String move;
    String lastMove;
    boolean isWhiteMove = true;

    /*
     * Function that returns true if a path is valid.
     * Only knights can move over pieces.
     */
    public boolean checkPath(String startBox, String endBox){
        /*
         * 1. See if there is any piece in between starting point and destination.
         * Assume piece is a queen. Valid check for piece-specific moves is in Kevin's
         * function
         * 
         * startBox is 1,1 (Rook move)
         * endBox 8,1 *Have to check rows between*
         * 
         * startBox is 1,3 (Bishop move)
         * endBox is 7,8 *Have to check diagonals between*
         * 
         * Assuming a piece is a queen, check 
         */
        /*
         * Check if row move
         * Check if diagonal move
         * Check if column move
         */
        System.out.println(startBox);
        System.out.println(endBox);
        
        if(startBox.charAt(startBox.length()-1) == endBox.charAt(startBox.length()-1)){
            //Row Move, check row for obstructions
            //System.out.println("Checking column move");
            int columnNum = Integer.parseInt(startBox.substring(startBox.length()-1, startBox.length()));
            /*
             * check 7 to 4 if start is larger than end
             * check 4 to 7 if end is larger than start
             */
            int startRow = Integer.parseInt(startBox.substring(0, 1));
            int endRow = Integer.parseInt(endBox.substring(0, 1));
            if(startRow < endRow){ //Up to down
                for(int i = startRow; i < endRow; i++){
                    //System.out.println(board.get(i+1).get(columnNum+1).getPieceName());
                    //System.out.println((i+1) + ", " + (columnNum+1));
                    if(!board.get(i+1).get(columnNum+1).getPieceName().equals("☐")) return false;
                }
            } else if(startRow > endRow){ //Down to up
                for(int i = startRow; i > endRow; i--){
                    //System.out.println(board.get(i-1).get(columnNum+1).getPieceName());
                    //System.out.println((i-1) + ", " + (columnNum+1));
                    if(!board.get(i-1).get(columnNum+1).getPieceName().equals("☐")) return false;
                }
            }
        } else if(startBox.charAt(0) == endBox.charAt(0)){
            //Column Move, check row for obstructions
            int startColumn = Integer.parseInt(startBox.substring(2,startBox.length()));
            int endColumn = Integer.parseInt(endBox.substring(2, startBox.length()));
            //System.out.println("Checking row move");
            int rowNum = Integer.parseInt(startBox.substring(0, 1));
            if(startColumn < endColumn){ //Left to right
                for(int i = startColumn; i < endColumn; i++){
                    //System.out.println(board.get(rowNum).get(i+2).getPieceName());
                    if(!board.get(rowNum).get(i+2).getPieceName().equals("☐")) return false;
                }
            } else if(startColumn > endColumn){ //Right to left
                for(int i = startColumn; i > endColumn; i--){
                    //System.out.println(board.get(rowNum).get(i-1).getPieceName());
                    if(!board.get(rowNum).get(i).getPieceName().equals("☐")) return false;
                }
            }
        } else {
            //Diagonal Move, check diagonals for obstructions
            //TODO
        }
        return true;
    }

    public String parseMove(String move) { 
        String col_index = "";
        String row_index = "";
        String col = move.substring(0,1);
        String row = move.substring(1);

        if (col.equals("a")) {
            col_index = "0";
        } else if (col.equals("b")) {
            col_index = "1";
        } else if (col.equals("c")) {
            col_index = "2";
        } else if (col.equals("d")) {
            col_index = "3";
        } else if (col.equals("e")) {
            col_index = "4";
        } else if (col.equals("f")) {
            col_index = "5";
        } else if (col.equals("g")) {
            col_index = "6";
        } else if (col.equals("h")) {
            col_index = "7";
        }

        if (row.equals("1")) {
            row_index = "7";
        } else if (row.equals("2")) {
            row_index = "6";
        } else if (row.equals("3")) {
            row_index = "5";
        } else if (row.equals("4")) {
            row_index = "4";
        } else if (row.equals("5")) {
            row_index = "3";
        } else if (row.equals("6")) {
            row_index = "2";
        } else if (row.equals("7")) {
            row_index = "1";
        } else if (row.equals("8")) {
            row_index = "0";
        }
        //everything is 0-7
        return row_index + "," + col_index;
    } // will return the row and col of the imputted move, ex. a7 will give 1,0

    public void coolPrint(String str){
        for(int i = 0; i < str.length(); i++){
            try{
                System.out.print(str.substring(i, i+1));
                //Thread.sleep(50);
            } catch(Exception e){

            }
        }
        System.out.println();
    }

    public void printBoard(){
        System.out.println("    a    b    c   d    e    f   g    h");
        System.out.println("  ----------------------------------------");
        int count = 8;
        for (int i = 0; i < 8; i++) {
            System.out.print(count + " ");
            System.out.print("| ");
            for (int j = 1; j < 9; j++) {
                if (board.get(i).get(j).getPieceName().equals("☐")) {
                    if(j == 0 || j == 5 || j == 7) System.out.print("  | ");
                    else System.out.print("  |  ");
                } else {
                    System.out.print(board.get(i).get(j).getPieceName() + " | ");
                }
            }
            System.out.print(count);
            count--;
            System.out.println();
            System.out.println("  ----------------------------------------");
        }
        System.out.println("    a    b    c   d    e    f   g    h");
        System.out.println();
    }

    public void createStartingBoard(){
        for(int i = 0; i < 9; i++){
            ArrayList<ChessPiece> x = new ArrayList<ChessPiece>();
            for(int j = 0; j < 9; j++){
                x.add(new ChessPiece("☐", false));
            }
            board.add(x);
        }

        // char start = 'a'; //populate bottom letters
        // for(int i = 1; i < 9; i++){

        // board.get(8).set(i, new ChessPiece(String.valueOf(start++), false));
        // }

        // for(int i = 1; i < 9; i++){
        // board.get(8-i).set(0, new ChessPiece(String.valueOf((char)(i+'0')),false));
        // }

        //Row 1 and Row 6 are 'P's (pawns)
        for(int i = 1; i < 9; i++){
            board.get(1).set(i, new ChessPiece("♟", true));
            board.get(6).set(i, new ChessPiece("♙", false));
        }

        //Corners are Rooks
        board.get(0).set(1, new ChessPiece("♜", true));
        board.get(0).set(8, new ChessPiece("♜", true));
        board.get(7).set(1, new ChessPiece("♖", false));
        board.get(7).set(8, new ChessPiece("♖", false));
        //Next to corners are Knights
        board.get(0).set(2, new ChessPiece("♞", true));
        board.get(0).set(7, new ChessPiece("♞", true));
        board.get(7).set(2, new ChessPiece("♘", false));
        board.get(7).set(7, new ChessPiece("♘", false));
        //Next to Knights are Bishops
        board.get(0).set(3, new ChessPiece("♝", true));
        board.get(0).set(6, new ChessPiece("♝", true));
        board.get(7).set(3, new ChessPiece("♗", false));
        board.get(7).set(6, new ChessPiece("♗", false));
        //White will always be bottom, black on top
        board.get(0).set(4, new ChessPiece("♛", true));
        board.get(0).set(5, new ChessPiece("♚", true));
        board.get(7).set(4, new ChessPiece("♕", false));
        board.get(7).set(5, new ChessPiece("♔", false));
    }

    public void main(){
        board = new ArrayList();

        createStartingBoard();
        printBoard();

        //obj = new ChessRules(board);
        playGame();
    }

    public void movePiece(ArrayList<ArrayList<ChessPiece>> board, String move) {
        // first check if possible
        ChessPiece blank = new ChessPiece("☐", false);
        String start = move.substring(0,2);
        String end = move.substring(2);
        
        String start_row_str = parseMove(start).substring(0,1);
        String start_col_str = parseMove(start).substring(2);
        String end_row_str = parseMove(end).substring(0,1);
        String end_col_str = parseMove(end).substring(2);
        int start_row = Integer.parseInt(start_row_str);
        int start_col = Integer.parseInt(start_col_str) + 1;
        int end_row = Integer.parseInt(end_row_str);
        int end_col = Integer.parseInt(end_col_str) + 1;
        
        if (isWhiteMove) { // checks if its whites move
            if(board.get(start_row).get(start_col).isB) { // checks if piece is white
                System.out.println("Not yo move :/"); // its very self explanitory
                playGame();
            } else {
                System.out.println("White's move");
                isWhiteMove = false;
            }
        } else {
            if (!board.get(start_row).get(start_col).isB) { // checks if the piece is black
                System.out.println("Not yo move :/");
                playGame();                
            } else {
                System.out.println("Black's move");
                isWhiteMove = true;
            }
        }
        
        while(!CheckValidMove(Integer.parseInt(start_row_str), Integer.parseInt(start_col_str), Integer.parseInt(end_row_str), Integer.parseInt(end_col_str))){
            playGame();
        }
        
        // converts move into seperate intergers that are used to index

        ChessPiece temp = board.get(start_row).get(start_col);
        if(checkPath(parseMove(start), parseMove(end))){
            //swaps pieces
            board.get(end_row).set(end_col, temp);
            board.get(start_row).set(start_col, blank);
        } else {
            System.out.println("Not a valid move, please try again.");
        }
    }

    public void playGame(){
        do{
            System.out.println("Please input your chess move :)!");
            Scanner obj = new Scanner(System.in);
            lastMove = move;
            move = obj.nextLine();
            movePiece(board, move);
            printBoard();
        }while(true);
    }

    public boolean CheckValidMove(int startI, int startJ, int endI, int endJ){
        startI = startI;
        startJ = startJ+1;
        endI = endI;
        endJ = endJ+1;
        //System.out.print("Start index: " + startI + ", " + startJ);
        //System.out.print("End index: " + endI + ", " + endJ);
        //checking if piece is out of bounds
        if(startI < 0 || startI > 8) return false;
        else if(startJ < 1 || startJ > 9) return false;
        else if(endI < 0 || endI > 8) return false;
        else if(endJ < 1 || endJ > 9) return false;
        if(board.get(startI).get(startJ).name.equals("♟") || board.get(startI).get(startJ).name.equals("♙")){
            if(board.get(startI).get(startJ).isB){//for black pieces
                if(startJ == endJ){
                    if(startI == endI - 1){
                        if(board.get(endI).get(endJ).name.equals("☐")){// change to whatever is the new board empty character
                            return true;
                        }
                    } else if (startI == endI - 2 && startI == 1){
                        if(board.get(endI).get(endJ).name.equals("☐")){
                            return true;
                        }
                    }
                } else if(startJ == endJ + 1 || startJ == endJ - 1){
                    if(startI == endI - 1){
                        if(!board.get(endI).get(endJ).name.equals("☐") || !board.get(endI).get(endJ).isB){
                            return true;
                        }/*else if(parseMove(lastMove.substring(0,1))){ // code enpassant

                        }*/
                    }
                }
            } else{
                if(startJ == endJ){
                    if(startI == endI + 1){
                        if(board.get(endI).get(endJ).name.equals("☐")){// change to whatever is the new board empty character
                            return true;
                        }
                    } else if (startI == endI + 2 && startI == 6){
                        if(board.get(endI).get(endJ).name.equals("☐")){
                            return true;
                        }
                    }
                } else if(startJ == endJ + 1 || startJ == endJ - 1){
                    if(startI == endI + 1){
                        if(!board.get(endI).get(endJ).name.equals("☐") || board.get(endI).get(endJ).isB){
                            return true;
                        }/*else if(parseMove(lastMove.substring(0,1))){ // code enpassant

                        }*/
                    }
                }
            }
        } else if(board.get(startI).get(startJ).name.equals("♜") || board.get(startI).get(startJ).name.equals("♖")){
            if(startI == endI){
                board.get(startI).get(startJ).hasMoved = true;
                return true;
            }else if(startJ == endJ){
                //check for obstacles with Mr. chau's method
                board.get(startI).get(startJ).hasMoved = true;
                return true;
            }
        } else if(board.get(startI).get(startJ).name.equals("♞") || board.get(startI).get(startJ).name.equals("♘")){
            if(startI == endI+1 || startI == endI -1){
                if(startJ == endJ + 2 || startJ == endJ -2) return true; 
            } else if(startI == endI+2 || startI == endI-2){
                if(startJ == endJ + 1 || startJ == endJ -1) return true;
            }
        } else if(board.get(startI).get(startJ).name.equals("♝") || board.get(startI).get(startJ).name.equals("♗")){
            if(Math.abs(startI - endI) == Math.abs(startJ - endJ)){
                return true;
            }
        } else if(board.get(startI).get(startJ).name.equals("♛") || board.get(startI).get(startJ).name.equals("♕")){
            if(startI == endI){
                return true;
            } else if(startJ == endJ){
                return true;
            } else if(Math.abs(startI - endI) == Math.abs(startJ - endJ)){
                return true;
            }
        } else if(board.get(startI).get(startJ).name.equals("♚") || board.get(startI).get(startJ).name.equals("♔")){
            if((startI == endI + 1 || startI == endI -1 || startJ == endJ + 1 ||startJ == endJ - 1)){
                board.get(startI).get(startJ).hasMoved = true;
                return true;
            } else if(startI == endI){
                if(startI == endI+2 && !board.get(startI).get(0).hasMoved && !board.get(startI).get(startJ).hasMoved){
                    return true;
                } else if(startI == endI-2 && !board.get(startI).get(0).hasMoved && !board.get(startI).get(startJ).hasMoved){
                    return true;
                }
            }
        }
        /*System.out.println("Didn't go through");
        System.out.println(board.get(startI).get(startJ).name);
        for(ArrayList<ChessPiece> x: board){
            for(ChessPiece y: x){
                System.out.print(y.name + " ");
            }
            System.out.println("");
        }*/
        return false;
    }
}